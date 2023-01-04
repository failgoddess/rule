package com.goddess.rule.executer.mode.base.bound;

import com.goddess.rule.executer.context.Context;

public abstract class Bound {
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract String build(Context context);
}
