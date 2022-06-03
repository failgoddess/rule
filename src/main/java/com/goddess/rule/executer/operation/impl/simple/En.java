package com.goddess.rule.executer.operation.impl.simple;


import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationOperation;

import java.util.List;

/**
 * 为空
 */
public class En extends RelationOperation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.EN;
    }
    @Override
    public boolean execute(Integer flag,Integer dataTypeCode,Object data,
                           List<String> params){
        if(data==null){
            return true;
        }
        if(data==""){
            return true;
        }
        return false;
    }

}
