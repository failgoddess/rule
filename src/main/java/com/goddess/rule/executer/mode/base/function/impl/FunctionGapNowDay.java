package com.goddess.rule.executer.mode.base.function.impl;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.mode.base.function.FunctionHandler;

import java.time.LocalDate;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/11/16 19:43
 */
public class FunctionGapNowDay implements FunctionHandler {
    @Override
    public String getName() {
        return "gapNowDay";
    }

    @Override
    public Object execute(Context context, JSONObject data) {
        LocalDate now = LocalDate.now();
        return -1;
    }
}
