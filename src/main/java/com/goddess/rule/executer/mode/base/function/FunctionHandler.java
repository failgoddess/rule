package com.goddess.rule.executer.mode.base.function;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;

public interface FunctionHandler {
    String getName();
    Object execute(Context context, JSONObject data);
}
