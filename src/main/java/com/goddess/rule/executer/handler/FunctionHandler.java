package com.goddess.rule.executer.handler;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;

import java.util.Map;

public interface FunctionHandler {
    String getName();
    Object execute(DecisionContext context, JSONObject data);
}
