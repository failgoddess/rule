package com.goddess.rule.executer.mode;

import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.mode.base.BasePo;

/**
 * 行为
 */

public abstract class Action extends BasePo {

    public abstract void execute(DecisionContext decisionContext) ;

}
