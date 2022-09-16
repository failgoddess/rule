package com.goddess.rule.executer.mode.bound;


import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.mode.ruleLine.Expression;
import com.goddess.rule.executer.mode.ruleLine.JavaActuator;

public class If extends Bound {
    private Expression expression;
    private Compose compose;

    @Override
    public String build(DecisionContext context) {
        boolean flag = new JavaActuator(expression).execute(context);
        if(flag){
            return compose.build(context);
        }
        return "";
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Compose getCompose() {
        return compose;
    }

    public void setCompose(Compose compose) {
        this.compose = compose;
    }
}
