package com.goddess.rule.executer.mode.rule.graph;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.mode.base.action.Param;
import com.goddess.rule.executer.mode.rule.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/18 17:47
 */
public class RuleGraph extends Rule<DecisionResult> {
    // 模式 Graph
    private String content;
    private List<Graph> graphs;
    private Map<String, Result> resultMap;

    @Override
    public DecisionResult decision(Context Context, JSONObject data){

        //验证参数
        Param.checkParam(data,getParams());
        //解析参数
        Context.putRuleData(Param.buildParams(Context,getParams(),data));

        DecisionResult decisionResult = new DecisionResult();

        //获得决策图的每一个结果 决策图编码为Key 结果为val
        Map<String,String> graphResult = new HashMap<>();
        graphs.forEach(graph -> {
            graphResult.put(graph.getCode(),graph.decision(Context));
        });

        //只有一棵树 直接返回
        if(graphs.size()==1){
            Result result = resultMap.get(graphResult.get(graphs.get(0).getCode()));
            decisionResult.setDataType(result.getDataType());
            decisionResult.setContent(result.decision(Context));
        }
        return decisionResult;
    }
    public void setGraphs(List<Graph> graphs) {
        this.graphs = graphs;
    }

    public void setResults(List<Result> results) {
        this.resultMap = results.stream().collect(Collectors.toMap(Result::getCode, o->o));
    }

    public List<Graph> getGraphs() {
        return graphs;
    }
}
