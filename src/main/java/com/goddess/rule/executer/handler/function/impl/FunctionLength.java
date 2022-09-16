package com.goddess.rule.executer.handler.function.impl;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.handler.function.FunctionHandler;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/11 21:16
 */
public class FunctionLength implements FunctionHandler {
    @Override
    public String getName() {
        return "length";
    }

    @Override
    public Object execute(DecisionContext context, JSONObject data) {
        if(data==null){
            return null;
        }
        String attr1 = data.getString("attr1");
        return attr1.length();
    }
}
