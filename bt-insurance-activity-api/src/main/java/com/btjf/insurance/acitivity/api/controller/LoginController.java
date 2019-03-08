package com.btjf.insurance.acitivity.api.controller;

import com.btjf.application.util.XaResult;
import com.btjf.common.utils.DateUtil;
import com.btjf.common.utils.MD5Utils;
import com.btjf.insurance.acitivity.api.token.AccessTokenManage;
import com.btjf.insurance.acitivity.api.vo.LoginVo;
import com.btjf.insurance.config.enums.ClientType;
import com.btjf.insurance.user.bo.AccessTokenBo;
import com.btjf.insurance.user.bo.UserBo;
import com.btjf.insurance.user.domain.AccessTokenDomain;
import com.btjf.insurance.user.domain.UserDomain;
import com.btjf.insurance.user.enums.LoginPermissions;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author kantenmei
 * @date 2019/3/5
 * @time 8:37 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@RestController
public class LoginController {

    @Reference(version = "1.0.0")
    private UserDomain userDomain;

    @Reference(version = "1.0.0")
    private AccessTokenDomain accessTokenDomain;

    @Resource
    private AccessTokenManage accessTokenManage;

    @PostMapping("/login" )
    public XaResult<AccessTokenBo> login(@RequestBody LoginVo loginVo) {
        UserBo userBo = userDomain.getByLoginName(loginVo.getUserName());
        if (userBo != null) {
            if (!userBo.getIsEnable()) {
                return XaResult.error("该用户已被禁用！");
            }
            if (!userDomain.checkLoginPermissionsByUserId(userBo.getID(), LoginPermissions.INSURANCE_SYSTEM)) {
                return XaResult.error("无权限登录！");
            }
            if (MD5Utils.getMD5String(loginVo.getPassword()).equals(userBo.getPassword())) {

            } else {
                return XaResult.error("输入的密码有误");
            }
        } else {
            return XaResult.error("输入的用户名不存在");
        }
        AccessTokenBo accessTokenBo = generateAndSaveAccessToken(userBo);
        return XaResult.success(accessTokenBo);
    }


    /**
     * 生成accessToken并保存信息
     * @param userBo
     * @return
     */
    private AccessTokenBo generateAndSaveAccessToken(UserBo userBo) {
        //String accessToken = accessTokenDomain.generateToken();
        String accessToken = UUID.randomUUID().toString();

        Date effectTime = DateUtil.dateAfter(new Date(), Calendar.DAY_OF_MONTH, 15);

        AccessTokenBo accessTokenBo = new AccessTokenBo.Builder().token(accessToken).createTime(new Date())
                .effectiveTime(effectTime).landSource(ClientType.MANAGE.getValue()).userID(userBo.getID()).build();
        accessTokenDomain.add(accessTokenBo);
        //缓存
        accessTokenManage.put(accessTokenBo.getToken(), userBo, effectTime.getTime());
        return accessTokenBo;
    }




}
