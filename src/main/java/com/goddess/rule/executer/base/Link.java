package com.goddess.rule.executer.base;

import com.goddess.rule.executer.context.DecisionContext;

/**
 * 链接
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:11
 */
public class Link {
    //编码
    private String code;
    //名称
    private String name;
    //分支编码
    private String branchCode;
    //优先级
    private Integer priority;
    //下一跳分支
    private String nextBranchCode;
    //下一跳结果编码
    private String nextResultCode;
    //下一跳类型：分支 结果  Constant.NextType
    private String nextType;

    //永真条件
    private boolean eternal;

    private Expression expression;

    public boolean decision(DecisionContext decisionContext) {
        //永真条件的处理
        if(isEternal()){
            return true;
        }
        boolean temp =  new JavaActuator(expression).execute(decisionContext,true);
        if(temp == false){
            //有不满足的可以直接结束这个链接了
            return false;
        }
        return true;
    }


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNextType() {
        return nextType;
    }

    public void setNextType(String nextType) {
        this.nextType = nextType;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public boolean isEternal() {
        return eternal;
    }

    public void setEternal(boolean eternal) {
        this.eternal = eternal;
    }
}
