package com.goddess.rule.executer.base.operation.impl.simple;



import com.goddess.rule.constant.RuleException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.base.operation.Operation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 大于
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/12 01:16
 */
public class Gt extends Operation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.GT;
    }

    @Override
    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        if(checkData(cover,threshold)){
            return false;
        }
        switch (dataTypeCode){
            case Constant.DataType.NUMBER:
                return number(coverComplex,cover,thresholdComplex,threshold);
            case Constant.DataType.TIME_YMD:
                return timeYmd(coverComplex,cover,thresholdComplex,threshold);
            case Constant.DataType.TIME_YMDHMS:
                return timeYmdhms(coverComplex,cover,thresholdComplex,threshold);
            case Constant.DataType.TIME_HMS:
                return timeHms(coverComplex,cover,thresholdComplex,threshold);
            default:
                throw new RuleException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }

    @Override
    public boolean timeHms(LocalTime t1,LocalTime t2){
        if(t1!=null&&t2!=null){
            if (t1.compareTo(t2)>0) {
                return true;
            }else {
                return false;
            }
        }else if(t2==null&&t1!=null){
            //非空 对 空
            return true;
        }else if(t1==null&&t2!=null){
            //空 对 非空
            return true;
        }else {
            //空对空
            return true;
        }
    }

    @Override
    public boolean timeYmdhms(LocalDateTime t1,LocalDateTime t2){
        if(t1!=null&&t2!=null){
            if (t1.compareTo(t2)>0) {
                return true;
            }else {
                return false;
            }
        }else if(t2==null&&t1!=null){
            //非空 对 空
            return true;
        }else if(t1==null&&t2!=null){
            //空 对 非空
            return true;
        }else {
            //空对空
            return true;
        }
    }

    @Override
    public boolean timeYmd(LocalDate t1,LocalDate t2){
        if(t1!=null&&t2!=null){
            if (t1.compareTo(t2)>0) {
                return true;
            }else {
                return false;
            }
        }else if(t2==null&&t1!=null){
            //非空 对 空
            return true;
        }else if(t1==null&&t2!=null){
            //空 对 非空
            return true;
        }else {
            //空对空
            return true;
        }
    }

    @Override
    public boolean number(BigDecimal t1 ,BigDecimal t2){
        if(t1!=null&&t2!=null){
            if (t1.compareTo(t2)>0) {
                return true;
            }else {
                return false;
            }
        }else if(t2==null&&t1!=null){
            //非空 对 空
            return true;
        }else if(t1==null&&t2!=null){
            //空 对 非空
            return true;
        }else {
            //空对空
            return true;
        }
    }

    @Override
    public boolean boll(Boolean t1, Boolean t2) {
        throw new RuleException(getOperationCode()+"不支持数据类型: " + Constant.DataType.BOLL);
    }
}
