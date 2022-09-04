package com.goddess.rule.ruleline;

import com.goddess.rule.executer.mode.Expression;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/8/30 15:52
 */
public class RuleLine {


    private Object result;
    private Expression expression;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
