package com.goddess.rule.executer.base;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.constant.BlException;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.executer.context.DecisionContext;

import java.util.ArrayList;
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
public class Rule {
    //编码
    private String code;
    //名称
    private String name;
    //规则内容
    private String content;
    //备注
    private String remark;

    private List<Param> params;
    private List<InitData> initDatas;
    private List<Graph> graphs;
    private Map<String,Result> resultMap;

    public Rule(){}

    public DecisionResult decision(DecisionContext decisionContext){
        //验证参数
        checkParam(decisionContext);
        //构建 initDatas 并将 新数据加入到 data
        buildInitData(decisionContext);

        DecisionResult decisionResult = new DecisionResult();

        //获得决策图的每一个结果 决策图编码为Key 结果为val
        Map<String,String> graphResult = new HashMap<>();
        graphs.forEach(graphExecute -> {
            graphResult.put(graphExecute.getCode(),graphExecute.decision(decisionContext));
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

    private void buildInitData(DecisionContext context){
        for(InitData initData:initDatas){
            context.getData().put(initData.getCode(),initData.getFormulaNode().apply(context));
        }
    }
    private void checkParam(DecisionContext context){
        List<String> names = new ArrayList<>();
        List<Param> params = this.getParams();
        Map<String,String> nameMap = params.stream().collect(Collectors.toMap(Param::getCode,Param::getName));
        //筛选出所有需要必须传入的的进行非空校验
        for(Param param:this.params.stream().filter(o-> o.isNecessary()).collect(Collectors.toList())){
            Object data = context.getData().get(param.getCode());
            if(data==null||data.toString().equals("")){
                names.add(param.getCode()+":"+nameMap.get(param.getCode()));
            }
        }
        if(!names.isEmpty()){
            throw new BlException(ExceptionCode.EC_0107,String.join(",",names));
        }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
