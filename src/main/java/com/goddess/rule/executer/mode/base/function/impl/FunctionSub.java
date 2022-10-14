package com.goddess.rule.executer.mode.base.function.impl;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.mode.base.function.FunctionHandler;

import java.math.BigDecimal;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/11 21:16
 */
public class FunctionSub implements FunctionHandler {
    @Override
    public String getName() {
        return "sub";
    }

    @Override
    public Object execute(Context context, JSONObject data) {
        if(data==null){
            return null;
        }
        BigDecimal attr1 = data.getBigDecimal("attr1");
        BigDecimal attr2 = data.getBigDecimal("attr2");
        return attr1.subtract(attr2);
    }
}
