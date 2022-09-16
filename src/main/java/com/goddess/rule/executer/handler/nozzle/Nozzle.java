package com.goddess.rule.executer.handler.nozzle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.meta.MetaClass;
import com.goddess.rule.executer.mode.Mapping;
import com.goddess.rule.executer.mode.Param;
import com.goddess.rule.executer.mode.base.BasePo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Nozzle extends BasePo {
    private List<Param> params;
    private Map<String,Param> paramsMap;
    private List<Mapping> mappings;

    private String sourceCode;

    private int complex;
    private String resultDataType;
    private MetaClass resultMetaClass;

    public abstract Object extend(DecisionContext context);

    public Object execute(DecisionContext context, JSONObject data){
        Param.checkParam(data,this.params);
        //解析injons
        JSONObject json = Param.buildParams(context,this.params,data);
        context.pushData(json);
        Object reData = extend(context);
        context.removeData();
        if(reData==null){
            return null;
        }
        List<Map<String,String>> reDataset = (List<Map<String,String>>)reData;
        if(complex>0){
            JSONArray jsonArray = new JSONArray();
            for(Map<String,String> map:reDataset){
                JSONObject reJson = new JSONObject();
                for(Mapping mapping:mappings){
                    reJson.put(mapping.getResult(),map.get(mapping.getCode()));
                }
                jsonArray.add(reJson);
            }
            return jsonArray;
        }else {
            JSONObject reJson = new JSONObject();
            Map<String,String> map = reDataset.get(0);
            for(Mapping mapping:mappings){
                reJson.put(mapping.getResult(),map.get(mapping.getCode()));
            }
            return reJson;
        }
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
        this.paramsMap = params.stream().collect(Collectors.toMap(Param::getCode, param -> param));
    }

    public Map<String, Param> getParamsMap() {
        return paramsMap;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public int getComplex() {
        return complex;
    }

    public void setComplex(int complex) {
        this.complex = complex;
    }

    public String getResultDataType() {
        return resultDataType;
    }

    public void setResultDataType(String resultDataType) {
        this.resultDataType = resultDataType;
    }

    public MetaClass getResultMetaClass() {
        return resultMetaClass;
    }

    public void setResultMetaClass(MetaClass resultMetaClass) {
        this.resultMetaClass = resultMetaClass;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
    }
}
