package com.btjf.insurance.acitivity.api.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/7
 * @time 4:55 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@NoArgsConstructor
@Data
public class CustomerAnswerListVo {

    private List<CustomerAnswerVo> answers;
}
