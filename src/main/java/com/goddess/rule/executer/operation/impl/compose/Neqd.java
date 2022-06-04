package com.goddess.rule.executer.operation.impl.compose;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;
import com.goddess.rule.executer.operation.impl.simple.Eqd;

import java.util.List;

/**
 * 不等于(不区分大小写)
 */
public class Neqd extends RelationOperation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.NEQD;
    }
    @Override
    public boolean execute(Integer flag,String dataTypeCode,Object data,
                           List<String> params){
        RelationOperation eqd = RelationFactory.getOperation(Constant.OperationType.EQD);
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.STRING:
                return !eqd.execute(flag,dataTypeCode,data,params);
            default:
                throw new IllegalStateException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }
}
