package com.goddess.rule.executer.base;

import com.goddess.rule.executer.context.DecisionContext;

/**
 * 行为
 */

public abstract class Action {
    //编码
    private String code;
    //名称
    private String name;
    //是否阻断
    private boolean block;
    //备注
    private String remark;

    public abstract void execute(DecisionContext decisionContext);


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

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
