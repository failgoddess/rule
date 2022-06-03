package com.goddess.rule.executer.operation.impl.compose;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;
import com.goddess.rule.executer.operation.impl.simple.Eq;

import java.util.List;

/**
 * 大于等于
 */
public class Ge extends RelationOperation {
    @Override
    public String getOperationCode() {
        return Constant.OperationType.GE;
    }
    @Override
    public boolean execute(Integer flag,Integer dataTypeCode,Object data,
                               List<String> params){
        RelationOperation lt = RelationFactory.getOperation(Constant.OperationType.LT);
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.NUMBER:
                return !lt.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_YMD:
                return !lt.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_YMDHMS:
                return !lt.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_HMS:
                return !lt.execute(flag,dataTypeCode,data,params);
            default:
                throw new IllegalStateException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }

}
