package com.goddess.rule.executer.mode.graph;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.mode.action.Param;
import com.goddess.rule.executer.mode.base.BasePo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 规则执行器
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:01
 */
public class Rule extends BasePo {
    //规则内容
    private String content;

    private List<Param> params;
    private List<InitData> initDatas;
    private List<Graph> graphs;
    private Map<String,Result> resultMap;

    public Rule(){}

    public DecisionResult decision(DecisionContext decisionContext, JSONObject data){

        //验证参数
        Param.checkParam(data,this.params);
        //解析参数
        decisionContext.putRuleData(Param.buildParams(decisionContext,this.params,data));
        //构建 initDatas 并将 新数据加入到 data
        decisionContext.putRuleData(buildInitData(decisionContext));

        DecisionResult decisionResult = new DecisionResult();

        //获得决策图的每一个结果 决策图编码为Key 结果为val
        Map<String,String> graphResult = new HashMap<>();
        graphs.forEach(graph -> {
            graphResult.put(graph.getCode(),graph.decision(decisionContext));
        });

        //只有一棵树 直接返回
        if(graphs.size()==1){
            Result result = resultMap.get(graphResult.get(graphs.get(0).getCode()));
            decisionResult.setDataType(result.getDataType());
            decisionResult.setContent(result.decision(decisionContext));
        }
//        for (String graphCode:graphResult.keySet()){
//            reMap.put(graphCode,resultMap.get(graphCode).decision(decisionContext,dataJson));
//        }

        return decisionResult;
    }

    private JSONObject buildInitData(DecisionContext context){
        JSONObject json = new JSONObject();
        if(initDatas!=null){
            for(InitData initData:initDatas){
                json.put(initData.getCode(),initData.getDataFormulaNode().apply(context));
            }
        }
        return json;
    }




    public List<Param> getParams() {
        return params;
    }
    public void setGraphs(List<Graph> graphs) {
        this.graphs = graphs;
    }

    public void setResults(List<Result> results) {
        this.resultMap = results.stream().collect(Collectors.toMap(Result::getCode,o->o));
    }

    public List<Graph> getGraphs() {
        return graphs;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public List<InitData> getInitDatas() {
        return initDatas;
    }

    public void setInitDatas(List<InitData> initDatas) {
        this.initDatas = initDatas;
    }

}
