package com.goddess.rule.executer.base.operation.impl.compose;


import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.base.operation.Operation;
import com.goddess.rule.executer.base.operation.OperationFactory;
import com.goddess.rule.executer.base.operation.impl.simple.Eq;

import java.util.List;

public class Neq extends Operation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.NEQ;
    }
    @Override
    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        Eq eq = (Eq) OperationFactory.getOperation(Constant.OperationType.EQ);
        return !eq.execute(dataTypeCode,coverComplex,cover,thresholdComplex,threshold);
    }
}
