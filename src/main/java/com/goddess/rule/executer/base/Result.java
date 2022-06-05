package com.goddess.rule.executer.base;

import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;

import java.util.Map;

/**
 * 结果执行器
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:04
 */
public class Result {
    //编码
    private String code;
    //名称
    private String name;
    //结果类型 Constant.DataSourceType
    private String resultType;
    private String dataType;
    private String data;



    public JSONObject decision(DecisionContext decisionContext, JSONObject dataJson){
        return new JSONObject().fluentPut("data",resultType);
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
