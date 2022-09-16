package com.goddess.rule.executer.mode.bound;

import com.goddess.rule.executer.context.DecisionContext;

public class Else extends Bound {
    public Compose compose;

    @Override
    public String build(DecisionContext context) {
        return compose.build(context);
    }

    public Compose getCompose() {
        return compose;
    }

    public void setCompose(Compose compose) {
        this.compose = compose;
    }
}
