package com.goddess.rule.executer.base;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.base.formula.FormulaNode;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.context.RuleConfig;
import com.goddess.rule.parser.impl.DefaultFormulaBuilder;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:13
 */
public class Condition {
    //条件名称
    private String name;
    //永真条件
    private boolean eternal;
    //优先级
    private Integer priority;
    //被比较的阀值维度
    private Integer coverComplex;
    //被比较的阀值
    private String cover;
    private FormulaNode coverFormula;
    //操作符编码
    private String operationCode;
    //数据类型
    private String dataType;
    //阀值维度
    private Integer thresholdComplex;
    //阀值
    private String threshold;

    private FormulaNode thresholdFormula;

    private Operation operation;

    public boolean decision(DecisionContext decisionContext) {
        //永真条件的处理
        if(this.isEternal()){
            return true;
        }
        // 1>2  1被比较的阀值 >操作符 2阀值
        Object cover = coverFormula.apply(decisionContext);
        Object threshold = thresholdFormula.apply(decisionContext);
        return false;
//        return operation.decision(decisionContext,this.getDataType(),cover,this.getCoverComplex(),threshold,this.getThresholdComplex());
    }
    private String getThreshold(DecisionContext decisionContext,boolean flag){
//        if(flag){
//            return coverFormula
//        }else {
//            switch (this.getThresholdType()){
//                case Constant.DataSourceType.FIXED:return this.getThreshold();
//            }
//            // EN NN 单操作数操作符
//            if(operation.getCode().equals(Constant.OperationType.EN)||operation.getCode().equals(Constant.OperationType.NN)){
//                return this.getThreshold();
//            }
//            //其他情况认为是异常
//            throw new BlException(ExceptionCode.EC_0105,this.getName());
//        }
        return "";
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
        RuleConfig ruleConfig = RuleConfig.getInstance();
        this.coverFormula = ruleConfig.getFormulaBuilder().getFormulaNode(cover);
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
        RuleConfig ruleConfig = RuleConfig.getInstance();
        this.thresholdFormula = ruleConfig.getFormulaBuilder().getFormulaNode(threshold);
        this.threshold = threshold;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public boolean isEternal() {
        return eternal;
    }

    public void setEternal(boolean eternal) {
        this.eternal = eternal;
    }
}
