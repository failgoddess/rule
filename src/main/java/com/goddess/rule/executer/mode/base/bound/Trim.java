package com.goddess.rule.executer.mode.base.bound;

import com.goddess.rule.executer.context.Context;


public class Trim extends Bound {
    private Compose compose;

    public Compose getCompose() {
        return compose;
    }

    public void setCompose(Compose compose) {
        this.compose = compose;
    }

    @Override
    public String build(Context context) {
        String str = compose.build(context);
        if (str == null) {
            return "";
        } else{
            return "\n"+str.replace("\n","");
        }
    }
}
