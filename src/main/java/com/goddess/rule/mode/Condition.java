package com.goddess.rule.mode;

/**
 * 条件
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
     * 被比较的阀值
     */
    private String coverThresholdCode;
    /**
     * 操作符编码 varchar
     */
    private String operationCode;
    /**
     * 阀值
     */
    private String thresholdCode;


}
