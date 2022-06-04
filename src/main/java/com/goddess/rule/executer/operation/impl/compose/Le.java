package com.goddess.rule.executer.operation.impl.compose;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;

import java.util.List;

/**
 * 小于等于
 */
public class Le extends RelationOperation {
    @Override
    public String getOperationCode() {
        return Constant.OperationType.LE;
    }
    @Override
    public boolean execute(Integer flag,String dataTypeCode,Object data,
                               List<String> params){
        RelationOperation gt = RelationFactory.getOperation(Constant.OperationType.GT);
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.NUMBER:
                return !gt.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_YMD:
                return !gt.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_YMDHMS:
                return !gt.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_HMS:
                return !gt.execute(flag,dataTypeCode,data,params);
            default:
                throw new IllegalStateException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }

}
