package com.goddess.rule.executer.operation.impl.compose;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;

import java.util.List;
/**
 * 不在集合,不包含于
 */
public class Nin extends RelationOperation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.NIN;
    }
    @Override
    public boolean execute(Integer flag,Integer dataTypeCode,Object data,
                           List<String> params){
        RelationOperation in = RelationFactory.getOperation(Constant.OperationType.IN);
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.NUMBER:
                return !in.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.STRING:
                return !in.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_HMS:
                return !in.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_YMD:
                return !in.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_YMDHMS:
                return !in.execute(flag,dataTypeCode,data,params);
            default:
                throw new IllegalStateException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }
}
