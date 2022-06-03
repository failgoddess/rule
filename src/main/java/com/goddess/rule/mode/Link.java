package com.goddess.rule.mode;

/**
 * 链接
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
     * 下一跳结果ID int8
     */
    private Long nextResultCode;

    /**
     * 下一跳类型：1分支 2结果 int4
     */
    private Integer nextType;

}
