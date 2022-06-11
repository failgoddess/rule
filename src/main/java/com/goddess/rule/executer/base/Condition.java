package com.goddess.rule.executer.base;

import com.goddess.rule.executer.base.formula.FormulaNode;
import com.goddess.rule.executer.base.operation.Operation;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.context.RuleConfig;
import com.goddess.rule.executer.base.operation.OperationFactory;

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

    public boolean decision(DecisionContext context) {
        //永真条件的处理
        if(this.isEternal()){
            return true;
        }
        // 1>2  1被比较的阀值 >操作符 2阀值
        Operation operation = OperationFactory.getOperation(operationCode);

        Object cover = coverFormula.apply(context);
        Object threshold = null;
        if(!operation.isOneOp()){
            threshold = thresholdFormula.apply(context);
        }

        boolean flag = operation.execute(this.getDataType(),this.getCoverComplex(),cover,this.getThresholdComplex(),threshold);
        return flag;
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

    public boolean isEternal() {
        return eternal;
    }

    public void setEternal(boolean eternal) {
        this.eternal = eternal;
    }
}
