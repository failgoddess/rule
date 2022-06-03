package com.goddess.rule.executer.operation.impl.simple;

import com.goddess.rule.constant.BlException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * 等于
 */
public class Eq extends RelationOperation {
    @Override
    public String getOperationCode() {
        return Constant.OperationType.EQ;
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
            case Constant.DataType.BOLL:
                return boll(flag,data,params);
            case Constant.DataType.STRING:
                return string(flag,data,params);
            case Constant.DataType.TIME_YMD:
                return timeYmd(flag,data,params);
            case Constant.DataType.TIME_YMDHMS:
                return timeYmdhms(flag,data,params);
            case Constant.DataType.TIME_HMS:
                return timeHms(flag,data,params);
            default:
                throw new BlException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }
    private boolean timeHms(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<LocalTime> t1s = getTimeHmsList(data);
            LocalTime t2 = getTimeHms(params.get(0));
            for (LocalTime t1:t1s){
                if(t1.compareTo(t2)!=0){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            LocalTime t1 = getTimeHms(data);
            LocalTime t2 = getTimeHms(params.get(0));
            if (t1.compareTo(t2)==0) {
                return true;
            }else {
                return false;
            }
        }
    }
    private boolean timeYmdhms(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<LocalDateTime> t1s = getTimeYdmhmsList(data);
            LocalDateTime t2 = getTimeYdmhms(params.get(0));
            for (LocalDateTime t1:t1s){
                if(t1.compareTo(t2)!=0){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            LocalDateTime t1 = getTimeYdmhms(data);
            LocalDateTime t2 = getTimeYdmhms(params.get(0));
            if (t1.compareTo(t2)==0) {
                return true;
            }else {
                return false;
            }
        }
    }
    private boolean timeYmd(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<LocalDate> t1s = getTimeYdmList(data);
            LocalDate t2 = getTimeYdm(params.get(0));
            for (LocalDate t1:t1s){
                if(t1.compareTo(t2)!=0){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            LocalDate t1 = getTimeYdm(data);
            LocalDate t2 = getTimeYdm(params.get(0));
            if (t1.compareTo(t2)==0) {
                return true;
            }else {
                return false;
            }
        }
    }
    private boolean number(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<BigDecimal> t1s = getNumberList(data);
            BigDecimal t2 = new BigDecimal(params.get(0));
            for (BigDecimal t1:t1s){
                if(t1.compareTo(t2)!=0){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            BigDecimal t1 = getNumber(data);
            BigDecimal t2 = getNumber(params.get(0));
            if (t1.compareTo(t2)==0) {
                return true;
            }else {
                return false;
            }
        }
    }
    private boolean string(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<String> t1s = getList(data);
            for (String t1:t1s){
                if(!Objects.equals(t1,params.get(0))){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            if (Objects.equals(data.toString(),params.get(0))) {
                return true;
            }else {
                return false;
            }
        }
    }
    private boolean boll(Integer flag,Object data,List<String> params){
        if (isList(flag)){
            //列表
            List<Boolean> t1s = getBollList(data);
            for (Boolean t1:t1s){
                if(!Objects.equals(t1,getBoll(params.get(0)))){
                    return false;
                }
            }
            return true;
        }else {
            //非列表
            if (Objects.equals(getBoll(data),getBoll(params.get(0)))) {
                return true;
            }else {
                return false;
            }
        }
    }
}
