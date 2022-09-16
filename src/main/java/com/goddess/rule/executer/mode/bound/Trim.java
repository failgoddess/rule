package com.goddess.rule.executer.mode.bound;

import com.goddess.rule.executer.context.DecisionContext;


public class Trim extends Bound {
    private Compose compose;

    public Compose getCompose() {
        return compose;
    }

    public void setCompose(Compose compose) {
        this.compose = compose;
    }

    @Override
    public String build(DecisionContext context) {
        String str = compose.build(context);
        if (str == null) {
            return "";
        } else{
            return "\n"+str.replace("\n","");
        }
    }
}
