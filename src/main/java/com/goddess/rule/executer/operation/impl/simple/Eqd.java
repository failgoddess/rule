package com.goddess.rule.executer.operation.impl.simple;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationOperation;
import org.apache.commons.collections4.ListUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 等于(不区分大小写)
 */
public class Eqd extends RelationOperation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.EQD;
    }
    @Override
    public boolean execute(Integer flag,Integer dataTypeCode,Object data,
                           List<String> params){
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.STRING:
                return string(flag,data,params);
            default:
                throw new IllegalStateException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }
    private boolean string(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            List<String> t1 = getList(data).stream().map(String::toUpperCase).collect(Collectors.toList());
            List<String> t2 = params.stream().distinct().sorted().map(String::toUpperCase).collect(Collectors.toList());
            //列表
            return ListUtils.isEqualList(t1,t2);
        }else {
            //非列表
            if (Objects.equals(data.toString().toLowerCase(),params.get(0).toLowerCase())) {
                return true;
            }else {
                return false;
            }
        }
    }
}
