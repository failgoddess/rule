package com.goddess.rule.executer.base;

import com.goddess.rule.executer.base.operation.Operation;
import com.goddess.rule.executer.base.operation.OperationFactory;
import com.goddess.rule.executer.context.DecisionContext;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:13
 */
public class Condition extends Expression {
    //条件名称
    private String name;
    //永真条件
    private boolean eternal;


    public boolean decision(DecisionContext context) {
        //永真条件的处理
        if(this.isEternal()){
            return true;
        }
        // 1>2  1被比较的阀值 >操作符 2阀值
        Operation operation = OperationFactory.getOperation(getOperationCode());

        Object cover = getCoverFormula().apply(context);
        Object threshold = null;
        if(!operation.isOneOp()){
            threshold = getThresholdFormula().apply(context);
        }

        boolean flag = operation.execute(this.getDataType(),this.getCoverComplex(),cover,this.getThresholdComplex(),threshold);
        return flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isEternal() {
        return eternal;
    }

    public void setEternal(boolean eternal) {
        this.eternal = eternal;
    }
}
