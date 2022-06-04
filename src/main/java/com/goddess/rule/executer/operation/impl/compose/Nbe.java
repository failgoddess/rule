package com.goddess.rule.executer.operation.impl.compose;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;

import java.util.List;

/**
 * 不介于(闭区间)
 */
public class Nbe extends RelationOperation {
    @Override
    public String getOperationCode() {
        return Constant.OperationType.NBE;
    }
    @Override
    public boolean execute(Integer flag,String dataTypeCode,Object data,
                               List<String> params){
        RelationOperation be = RelationFactory.getOperation(Constant.OperationType.BE);
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.NUMBER:
                return !be.execute(flag,dataTypeCode,data,params);
            default:
                throw new IllegalStateException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }

}
