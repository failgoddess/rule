package com.goddess.rule.data;

/**
 * 链接
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:10
 */
public class Link {
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
     * 编码
     */
    private String code;
    /**
     * 名称
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
     * 下一跳分支
     */
    private String nextBranchCode;
    /**
     * 下一跳结果编码
     */
    private String nextResultCode;

    /**
     * 下一跳类型：1分支 2结果 int4 Constant.NextType
     */
    private Integer nextType;

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

    public String getNextBranchCode() {
        return nextBranchCode;
    }

    public void setNextBranchCode(String nextBranchCode) {
        this.nextBranchCode = nextBranchCode;
    }

    public String getNextResultCode() {
        return nextResultCode;
    }

    public void setNextResultCode(String nextResultCode) {
        this.nextResultCode = nextResultCode;
    }

    public Integer getNextType() {
        return nextType;
    }

    public void setNextType(Integer nextType) {
        this.nextType = nextType;
    }
}
