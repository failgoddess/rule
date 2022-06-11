package com.goddess.rule.executer.operation.impl.simple;



import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationOperation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public class Gt extends RelationOperation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.GT;
    }
    @Override
    public boolean execute(Integer flag,String dataTypeCode,Object data,
                               List<String> params){
        if(checkData(data,params)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.NUMBER:
                return number(flag,data,params);
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
            LocalTime t2 = getTimeHms(params.get(0));
            if(t1s.get(0).compareTo(t2)>0){
                //最小的都比这个大
                return true;
            }else {
                return false;
            }
        }else {
            //非列表
            LocalTime t1 = getTimeHms(data);
            LocalTime t2 = getTimeHms(params.get(0));
            if (t1.compareTo(t2)>0) {
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
            if(t1s.get(0).compareTo(t2)>0){
                //最小的都比这个大
                return true;
            }else {
                return false;
            }
        }else {
            //非列表
            LocalDateTime t1 = getTimeYdmhms(data);
            LocalDateTime t2 = getTimeYdmhms(params.get(0));
            if (t1.compareTo(t2)>0) {
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
            if(t1s.get(0).compareTo(t2)>0){
                //最小的都比这个大
                return true;
            }else {
                return false;
            }
        }else {
            //非列表
            LocalDate t1 = getTimeYdm(data);
            LocalDate t2 = getTimeYdm(params.get(0));
            if (t1.compareTo(t2)>0) {
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
            BigDecimal t2 = getNumber(params.get(0));
            if(t1s.get(0).compareTo(t2)>0){
                //最小的都比这个大
                return true;
            }else {
                return false;
            }
        }else {
            //非列表
            BigDecimal t1 = getNumber(data);
            BigDecimal t2 = getNumber(params.get(0));
            if (t1.compareTo(t2)>0) {
                return true;
            }else {
                return false;
            }
        }
    }
}