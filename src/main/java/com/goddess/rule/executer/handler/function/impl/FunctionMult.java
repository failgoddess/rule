package com.goddess.rule.executer.handler.function.impl;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.handler.function.FunctionHandler;

import java.math.BigDecimal;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/11 21:16
 */
public class FunctionMult implements FunctionHandler {
    @Override
    public String getName() {
        return "div";
    }

    @Override
    public Object execute(DecisionContext context, JSONObject data) {
        if(data==null){
            return null;
        }
        BigDecimal result = BigDecimal.ONE;
        for(String key:data.keySet()){
            result = result.multiply(data.getBigDecimal(key));
        }
        return result;
    }
}
