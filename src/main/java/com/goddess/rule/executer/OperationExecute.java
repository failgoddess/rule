package com.goddess.rule.executer;

import com.goddess.rule.data.Operation;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;


/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:13
 */
public class OperationExecute extends Operation {

    public boolean decision(DecisionContext decisionContext, String coverThreshold, Integer coverThresholdDataType,
                            String threshold, Integer thresholdDataType) {
//        RelationOperation handler = RelationFactory.getOperation(this.getCode());
//        return handler.execute(0, element.getDataTypeCode(),attrVal,params);
        return true;
    }
}
