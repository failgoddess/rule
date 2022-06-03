package com.goddess.rule.executer;

import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.data.Rule;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.context.JudgeData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规则执行器
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:01
 */
public class RuleExecute extends Rule {
    private List<GraphExecute> graphExecutes;
    private List<ResultExecute> resultExecutes;
    public RuleExecute(){}

    public RuleExecute(Rule rule,List<GraphExecute> graphExecutes, List<ResultExecute> resultExecutes) {
        this.graphExecutes = graphExecutes;
        this.resultExecutes = resultExecutes;
    }
    public Map<String,Object> decision(DecisionContext decisionContext,JudgeData judgeData){
        JSONObject dataJson = new JSONObject();
        Map<String,Object> reMap = new HashMap<>();
        //获得决策图的每一个结果 决策图编码为Key 结果为val
        Map<String,String> graphResult = new HashMap<>();
        graphExecutes.forEach(graphExecute -> {
            graphResult.put(graphExecute.getCode(),graphExecute.decision(decisionContext,dataJson));
        });
        return null;
    }

}
