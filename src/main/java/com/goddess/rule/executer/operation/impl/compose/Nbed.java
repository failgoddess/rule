package com.goddess.rule.executer.operation.impl.compose;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;

import java.util.List;

/**
 * 不介于(开区间)
 */
public class Nbed extends RelationOperation {
    @Override
    public String getOperationCode() {
        return Constant.OperationType.NBED;
    }
    @Override
    public boolean execute(Integer flag,String dataTypeCode,Object data,
                               List<String> params){
        RelationOperation bed = RelationFactory.getOperation(Constant.OperationType.BED);
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.NUMBER:
                return !bed.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_YMD:
                return !bed.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_YMDHMS:
                return !bed.execute(flag,dataTypeCode,data,params);
            case Constant.DataType.TIME_HMS:
                return !bed.execute(flag,dataTypeCode,data,params);
            default:
                throw new IllegalStateException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }

}
