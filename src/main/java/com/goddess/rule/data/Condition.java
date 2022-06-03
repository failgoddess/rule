package com.goddess.rule.data;

/**
 * 条件
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:10
 */
public class Condition {
    /**
     * 规则编码
     */
    private String ruleCode;
    /**
     * 决策图编码
     */
    private String graphCode;
    /**
     * 分支编码
     */
    private String branchCode;
    /**
     * 链接编码
     */
    private String linkCode;

    /**
     * 条件名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;

    /**
     * 优先级 int4
     */
    private Integer priority;

    /**
     * 被比较的阀值来源类型
     */
    private Integer coverThresholdType;
    /**
     * 被比较的阀值数据类型
     */
    private Integer coverThresholdDataType;
    /**
     * 被比较的阀值
     */
    private String coverThreshold;
    /**
     * 操作符编码
     */
    private String operationCode;
    /**
     * 阀值类型
     */
    private Integer thresholdType;
    /**
     * 阀值数据类型
     */
    private Integer thresholdDataType;
    /**
     * 阀值
     */
    private String threshold;

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getGraphCode() {
        return graphCode;
    }

    public void setGraphCode(String graphCode) {
        this.graphCode = graphCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getLinkCode() {
        return linkCode;
    }

    public void setLinkCode(String linkCode) {
        this.linkCode = linkCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getCoverThresholdType() {
        return coverThresholdType;
    }

    public void setCoverThresholdType(Integer coverThresholdType) {
        this.coverThresholdType = coverThresholdType;
    }

    public String getCoverThreshold() {
        return coverThreshold;
    }

    public void setCoverThreshold(String coverThreshold) {
        this.coverThreshold = coverThreshold;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public Integer getThresholdType() {
        return thresholdType;
    }

    public void setThresholdType(Integer thresholdType) {
        this.thresholdType = thresholdType;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public Integer getCoverThresholdDataType() {
        return coverThresholdDataType;
    }

    public void setCoverThresholdDataType(Integer coverThresholdDataType) {
        this.coverThresholdDataType = coverThresholdDataType;
    }

    public Integer getThresholdDataType() {
        return thresholdDataType;
    }

    public void setThresholdDataType(Integer thresholdDataType) {
        this.thresholdDataType = thresholdDataType;
    }
}
