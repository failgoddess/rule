package com.goddess.rule.executer.operation.impl.simple;



import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationOperation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 在集合,包含于
 */
public class In extends RelationOperation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.IN;
    }
    @Override
    public boolean execute(Integer flag,Integer dataTypeCode,Object data,
                           List<String> params){
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.NUMBER:
                return number(flag,data,params);
            case Constant.DataType.STRING:
                return string(flag,data,params);
            case Constant.DataType.TIME_YMD:
                return timeYmd(flag,data,params);
            case Constant.DataType.TIME_YMDHMS:
                return timeYmdhms(flag,data,params);
            case Constant.DataType.TIME_HMS:
                return timeHms(flag,data,params);
            default:
                throw new IllegalStateException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }
    private boolean timeHms(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<LocalTime> t1s = getTimeHmsList(data);
            List<LocalTime> t2 = getTimeHmsList(params);
            for(LocalTime t1:t1s){
                //有没在的就为否
                if(!t2.contains(t1)){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            LocalTime t1 = getTimeHms(data);
            List<LocalTime> t2 = getTimeHmsList(params);
            //有没在的就为否
            if(!t2.contains(t1)){
                return false;
            }else {
                return true;
            }
        }
    }
    private boolean timeYmdhms(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<LocalDateTime> t1s = getTimeYdmhmsList(data);
            List<LocalDateTime> t2 = getTimeYdmhmsList(params);
            for(LocalDateTime t1:t1s){
                //有没在的就为否
                if(!t2.contains(t1)){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            LocalDateTime t1 = getTimeYdmhms(data);
            List<LocalDateTime> t2 = getTimeYdmhmsList(params);
            //有没在的就为否
            if(!t2.contains(t1)){
                return false;
            }else {
                return true;
            }
        }
    }
    private boolean timeYmd(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<LocalDate> t1s = getTimeYdmList(data);
            List<LocalDate> t2 = getTimeYdmList(params);
            for(LocalDate t1:t1s){
                //有没在的就为否
                if(!t2.contains(t1)){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            LocalDate t1 = getTimeYdm(data);
            List<LocalDate> t2 = getTimeYdmList(params);
            //有没在的就为否
            if(!t2.contains(t1)){
                return false;
            }else {
                return true;
            }
        }
    }
    private boolean number(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<BigDecimal> t1s = getNumberList(data);
            List<BigDecimal> t2 = getNumberList(params);
            for(BigDecimal t1:t1s){
                //有没在的就为否
                if(!t2.contains(t1)){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            BigDecimal t1 = getNumber(data);
            List<BigDecimal> t2 = getNumberList(params);
            //有没在的就为否
            if(!t2.contains(t1)){
                return false;
            }else {
                return true;
            }
        }
    }
    private boolean string(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<String> t1s = getList(data);
            List<String> t2 = params;
            for(String t1:t1s){
                //有没在的就为否
                if(!t2.contains(t1)){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            String t1 = data.toString();
            List<String> t2 = params;
            //有没在的就为否
            if(!t2.contains(t1)){
                return false;
            }else {
                return true;
            }
        }
    }
}
