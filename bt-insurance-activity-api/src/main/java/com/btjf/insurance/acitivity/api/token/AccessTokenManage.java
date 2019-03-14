package com.btjf.insurance.acitivity.api.token;

import com.alibaba.fastjson.JSON;
import com.btjf.application.security.entity.CachePacketVo;
import com.btjf.business.common.exception.BusinessException;
import com.btjf.common.utils.JSONUtils;
import com.btjf.insurance.config.enums.ClientType;
import com.btjf.insurance.user.bo.AccessTokenBo;
import com.btjf.insurance.user.bo.UserBo;
import com.btjf.insurance.user.domain.AccessTokenDomain;
import com.btjf.insurance.user.domain.UserDomain;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author gantianming
 * @date 2019/2/18
 * @time 5:24 PM
 * @function 功能：
 * @describe 版本描述：accessToken管理工具 后台工程只负责存放
 * @modifyLog 修改日志：
 */
@Component
public class AccessTokenManage {


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Reference(version = "1.0.0")
    private AccessTokenDomain accessTokenDomain;

    @Reference(version = "1.0.0")
    private UserDomain userDomain;

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenManage.class);

    public static final String ACTIVITY_USER_ACCESS_TOKEN_CACHE_KEY = "activity_user_access_token_cache_key_";
    public static final String ACTIVITY_USERBO_USERID_CACHE_KEY = "activity_userBo_userID_cache_key_";

    /**
     * 设置token缓存
     *
     * @param accessToken   用户token
     * @param userBo        用户id
     */
    public void put(String accessToken, UserBo userBo, long expTime){
        if(StringUtils.isEmpty(accessToken) || userBo == null) {
            return;
        }
        // 若用户有老token删除无用的缓存
        AccessTokenBo accessTokenBo = accessTokenDomain.getByUserId(userBo.getID(), ClientType.PC.getValue());
        if(accessTokenBo != null) {
            redisTemplate.opsForValue().getOperations().delete(this.getTokenUserIDKey(accessTokenBo.getToken()));
            logger.info("清理用户accessToken：" + accessTokenBo.getToken());
        }

        this.putCacheUserID(accessToken, userBo.getID(), expTime);
        this.putCacheUserBo(userBo);
        logger.info("写入用户accessToken：" + accessToken);


    }

    /**
     * 缓存用户id  使用对象包含过期时间 防止缓存雪崩什么的
     * @param accessToken token
     * @param Id 用户id
     * @param expTime 过期时间
     */
    private void putCacheUserID(String accessToken, Integer Id, long expTime) {
        if(StringUtils.isNotBlank(accessToken)) {
            CachePacketVo cacheInfoVo = new CachePacketVo();
            cacheInfoVo.setExpire_second(expTime);
            cacheInfoVo.setObj(Id);
            this.redisTemplate.opsForValue().set(this.getTokenUserIDKey(accessToken), JSONUtils.toJSON(cacheInfoVo));
        }
    }

    /**
     * 缓存用户信息 直接覆盖
     * @param userBo
     */
    private void putCacheUserBo(UserBo userBo) {
        this.redisTemplate.opsForValue().set(this.getTokenUserBOKey(userBo.getID()), JSONUtils.toJSON(userBo));
    }

    /**
     * 根据accessToken获取员工信息
     *
     * @param accessToken
     * @return
     */
    public UserBo get(String accessToken){
        if(StringUtils.isEmpty(accessToken)){
            return null;
        }
        Integer userId = getUserId(accessToken);
        if(userId != null){
            return getUserBoById(userId);
        }
        return null;
    }


    /**
     * 根据accessToken 获取 empId
     *
     * @param accessToken
     * @return
     */
    private Integer getUserId(String accessToken) {
        if(StringUtils.isEmpty(accessToken)){
            return null;
        }
        String userIDObj = redisTemplate.opsForValue().get(this.getTokenUserIDKey(accessToken));
        if (StringUtils.isBlank(userIDObj)) {
            return null;
        }
        CachePacketVo cacheInfoVo = JSON.parseObject(userIDObj, CachePacketVo.class);
        if (null != cacheInfoVo && System.currentTimeMillis() < cacheInfoVo.getExpire_second()) {
            return (Integer) cacheInfoVo.getObj();
        }
        return null;
    }

    /**
     * 根据id获取用户信息
     *
     * @param userID
     * @return
     */
    private UserBo getUserBoById(Integer userID) {
        if (userID == null) {
            return null;
        }
        String userBoStr = this.redisTemplate.opsForValue().get(this.getTokenUserBOKey(userID));
        UserBo userBo = null;
        try {
            userBo = StringUtils.isEmpty(userBoStr) ? null : JSON.parseObject(userBoStr, UserBo.class);
            if (userBo == null) {
                // 如果用户信息不在缓存中，则从数据库中获取并同步到缓存
                userBo = userDomain.get(userID);
                if (userBo != null) {
                    // 同步缓存
                    putCacheUserBo(userBo);
                }

            }
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("fail to parse json to object,json:" + userBoStr);
        }
        return userBo;
    }


    public static String getTokenUserIDKey(String accessToken) {
        return ACTIVITY_USER_ACCESS_TOKEN_CACHE_KEY + accessToken;
    }

    public static String getTokenUserBOKey(Integer userID) {
        return ACTIVITY_USERBO_USERID_CACHE_KEY + userID;
    }




}
