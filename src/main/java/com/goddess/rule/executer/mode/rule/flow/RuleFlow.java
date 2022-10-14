package com.goddess.rule.executer.mode.rule.flow;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.mode.base.action.Param;
import com.goddess.rule.executer.mode.rule.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/18 17:49
 */
public class RuleFlow extends Rule<Map<String,List<Object>>> {
    // 模式 Flow
    private List<Flow> flows;


    public Map<String,List<Object>> decision(Context context, JSONObject data){
        //验证参数
        Param.checkParam(data,getParams());
        //解析参数
        context.putRuleData(Param.buildParams(context,getParams(),data));

        Map<String,List<Object>> reData = new HashMap<>();
        for (Flow flow : flows) {
            reData.put(flow.getCode(),flow.decision(context));
        }
        return reData;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}
