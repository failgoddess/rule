package com.goddess.rule.executer.operation.impl.compose;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;

import java.util.List;

/**
 * 不为空
 */
public class Nn extends RelationOperation {
    @Override
    public String getOperationCode() {
        return Constant.OperationType.NN;
    }
    @Override
    public boolean execute(Integer flag,String dataTypeCode,Object data,
                           List<String> params){
        RelationOperation en = RelationFactory.getOperation(Constant.OperationType.EN);
        return !en.execute(flag,dataTypeCode,data,params);
    }

}
