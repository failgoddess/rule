package com.goddess.rule.executer.base;

import com.goddess.rule.executer.context.DecisionContext;


/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:13
 */
public class Operation {

    //编码
    private String code;
    //名称
    private String name;

    public boolean decision(DecisionContext decisionContext, String dataType,String cover,
                            Integer coverComplex, String threshold, Integer thresholdComplex) {
        return true;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
