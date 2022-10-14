package com.goddess.rule.executer.mode.base.action;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.meta.MetaClass;
import com.goddess.rule.executer.meta.MetaObject;
import com.goddess.rule.executer.mode.BasePo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 行为
 */

public abstract class Action extends BasePo {


    private List<Param> params;
    private Map<String,Param> paramMap;
    private List<Mapping> mappings;
    private Map<String,Mapping> mappingMap;


    private int complex;
    private String dataType;
    private MetaClass metaClass;


    public abstract void extend(Context context, JSONObject data,Execute execute,List<Map<String,Object>>resultMapList);
    public Result<Object> execute(Context context,Execute execute, JSONObject paramsData){
        Result<Object> result = new Result<>();
        //验证参数
        Param.checkParam(paramsData,getParams());
        JSONObject data = null;
        if(paramsData.isEmpty()){
            data = Param.buildParams(context,getParams(),context.getData());
        }else {
            data = Param.buildParams(context,getParams(),paramsData);
        }
        context.pushData(data);
        List<Map<String,Object>>resultMapList = new ArrayList<>();
        try {
            extend(context,data,execute,resultMapList);
        }catch (Exception e) {
            result.setException(e);
        }

        context.removeData();
        if(complex>0){
            JSONArray jsonArray = new JSONArray();
            for(Map<String,Object> map:resultMapList){
                jsonArray.add(initOne(context,map));
            }
            result.setContent(jsonArray);
        }else {
            JSONObject reJson = new JSONObject();
            if(resultMapList.size()>0){
                reJson = initOne(context,resultMapList.get(0));
            }
            result.setContent(reJson);
        }
        return result;
    }
    private JSONObject initOne(Context context,Map<String,Object> map){
        JSONObject reJson =null;
        if(CollectionUtil.isEmpty(mappings)){
            reJson = new JSONObject(map);
        }else {
            reJson = new JSONObject();
            for(Mapping mapping:mappings){
                reJson.put(mapping.getResult(),map.get(mapping.getCode()));
            }
        }
        return new JSONObject(MetaObject.getMetaObject(context,reJson.getInnerMap(),this.dataType));
    }



    public int getComplex() {
        return complex;
    }

    public void setComplex(int complex) {
        this.complex = complex;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public MetaClass getMetaClass() {
        return metaClass;
    }

    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }


    public List<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<Mapping> mappings) {
        this.mappingMap = mappings.stream().collect(Collectors.toMap(Mapping::getCode, param -> param));
        this.mappings = mappings;
    }

    public Map<String, Param> getParamMap() {
        return paramMap;
    }

    public Map<String, Mapping> getMappingMap() {
        return mappingMap;
    }


    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
        this.paramMap = params.stream().collect(Collectors.toMap(Param::getCode, param -> param));
    }

}
