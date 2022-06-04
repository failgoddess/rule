package com.goddess.rule.executer.operation.impl.compose;


import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;
import com.goddess.rule.executer.operation.impl.simple.Eq;

import java.util.List;

public class Neq extends RelationOperation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.NEQ;
    }
    @Override
    public boolean execute(Integer flag,String dataTypeCode,Object data,
                           List<String> params){
        Eq eq = (Eq)RelationFactory.getOperation(Constant.OperationType.EQ);
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.NUMBER:
                return !eq.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.STRING:
                return !eq.execute(flag,dataTypeCode,data,params);
            default:
                throw new IllegalStateException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }
}
