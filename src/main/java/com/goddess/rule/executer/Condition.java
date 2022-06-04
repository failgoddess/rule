package com.goddess.rule.executer;

import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.constant.BlException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.executer.context.DecisionContext;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:13
 */
public class Condition {
    //条件名称
    private String name;
    //优先级
    private Integer priority;
    //被比较的阀值来源类型
    private String coverType;
    //被比较的阀值维度
    private Integer coverComplex;
    //被比较的阀值
    private String cover;
    //操作符编码
    private String operationCode;
    //数据类型
    private String dataType;
    //阀值类型
    private String thresholdType;
    //阀值维度
    private Integer thresholdComplex;
    //阀值
    private String threshold;

    private Operation operationExecute;

    public boolean decision(DecisionContext decisionContext, JSONObject dataJson) {
        // 1>2  1被比较的阀值 >操作符 2阀值
        String cover = this.getThreshold(dataJson,decisionContext,true);
        String threshold = this.getThreshold(dataJson,decisionContext,false);
        return operationExecute.decision(decisionContext,this.getDataType(),cover,this.getCoverComplex(),threshold,this.getThresholdComplex());
    }
    private String getThreshold(JSONObject dataJson, DecisionContext decisionContext,boolean flag){
        if(flag){
            switch (this.getCoverType()){
                case Constant.ThresholdType.FIXED:return this.getCover();
            }
            throw new BlException(ExceptionCode.EC_0104,this.getName());
        }else {
            switch (this.getThresholdType()){
                case Constant.ThresholdType.FIXED:return this.getThreshold();
            }
            throw new BlException(ExceptionCode.EC_0105,this.getName());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public Integer getCoverComplex() {
        return coverComplex;
    }

    public void setCoverComplex(Integer coverComplex) {
        this.coverComplex = coverComplex;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getThresholdType() {
        return thresholdType;
    }

    public void setThresholdType(String thresholdType) {
        this.thresholdType = thresholdType;
    }

    public Integer getThresholdComplex() {
        return thresholdComplex;
    }

    public void setThresholdComplex(Integer thresholdComplex) {
        this.thresholdComplex = thresholdComplex;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public Operation getOperationExecute() {
        return operationExecute;
    }

    public void setOperationExecute(Operation operationExecute) {
        this.operationExecute = operationExecute;
    }
}
