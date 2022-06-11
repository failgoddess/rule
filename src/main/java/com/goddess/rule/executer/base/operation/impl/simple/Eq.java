package com.goddess.rule.executer.base.operation.impl.simple;

import com.goddess.rule.constant.BlException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.base.operation.Operation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 等于
 */
public class Eq extends Operation {
    @Override
    public String getOperationCode() {
        return Constant.OperationType.EQ;
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
            case Constant.DataType.BOLL:
                return boll(coverComplex,cover,thresholdComplex,threshold);
            case Constant.DataType.STRING:
                return string(coverComplex,cover,thresholdComplex,threshold);
            case Constant.DataType.TIME_YMD:
                return timeYmd(coverComplex,cover,thresholdComplex,threshold);
            case Constant.DataType.TIME_YMDHMS:
                return timeYmdhms(coverComplex,cover,thresholdComplex,threshold);
            case Constant.DataType.TIME_HMS:
                return timeHms(coverComplex,cover,thresholdComplex,threshold);
            default:
                throw new BlException(getOperationCode()+"不支持数据类型: " + dataTypeCode);
        }
    }
    private boolean timeHms(Integer coverComplex, Object cover,
                            Integer thresholdComplex, Object threshold){
        List<LocalTime> t1s = getTimeHmsList(cover);
        List<LocalTime> t2s = getTimeHmsList(threshold);

        if(isList(coverComplex)&&isList(thresholdComplex)){
            //  均是列表
            if(t1s.size()!=t2s.size()){
                //数量不同
                return false;
            }else {
                //数量相同
                for(int i=0;i<t1s.size();i++){
                    LocalTime t1 = getTimeHms(t1s.get(i));
                    LocalTime t2 = getTimeHms(t2s.get(i));
                    boolean flag = timeHms(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            LocalTime t1 = getTimeHms(t1s.get(0));
            LocalTime t2 = getTimeHms(t2s.get(0));
            return timeHms(t1,t2);
        }
        return false;
    }
    private boolean timeHms(LocalTime t1,LocalTime t2){
        if(t1!=null&&t2!=null){
            if (t1.compareTo(t2)==0) {
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
    private boolean timeYmdhms(Integer coverComplex, Object cover,
                               Integer thresholdComplex, Object threshold){
        List<LocalDateTime> t1s = getTimeYdmhmsList(cover);
        List<LocalDateTime> t2s = getTimeYdmhmsList(threshold);

        if(isList(coverComplex)&&isList(thresholdComplex)){
            //  均是列表
            if(t1s.size()!=t2s.size()){
                //数量不同
                return false;
            }else {
                //数量相同
                for(int i=0;i<t1s.size();i++){
                    LocalDateTime t1 = getTimeYdmhms(t1s.get(i));
                    LocalDateTime t2 = getTimeYdmhms(t2s.get(i));
                    boolean flag = timeYmdhms(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            LocalDateTime t1 = getTimeYdmhms(t1s.get(0));
            LocalDateTime t2 = getTimeYdmhms(t2s.get(0));
            return timeYmdhms(t1,t2);
        }
        return false;
    }
    private boolean timeYmdhms(LocalDateTime t1,LocalDateTime t2){
        if(t1!=null&&t2!=null){
            if (t1.compareTo(t2)==0) {
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
    private boolean timeYmd(Integer coverComplex, Object cover,
                            Integer thresholdComplex, Object threshold){
        List<LocalDate> t1s = getTimeYdmList(cover);
        List<LocalDate> t2s = getTimeYdmList(threshold);

        if(isList(coverComplex)&&isList(thresholdComplex)){
            //  均是列表
            if(t1s.size()!=t2s.size()){
                //数量不同
                return false;
            }else {
                //数量相同
                for(int i=0;i<t1s.size();i++){
                    LocalDate t1 = getTimeYdm(t1s.get(i));
                    LocalDate t2 = getTimeYdm(t2s.get(i));
                    boolean flag = timeYmd(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            LocalDate t1 = getTimeYdm(t1s.get(0));
            LocalDate t2 = getTimeYdm(t2s.get(0));
            return timeYmd(t1,t2);
        }
        return false;
    }
    private boolean timeYmd(LocalDate t1,LocalDate t2){
        if(t1!=null&&t2!=null){
            if (t1.compareTo(t2)==0) {
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
    private boolean number(Integer coverComplex, Object cover,
                Integer thresholdComplex, Object threshold){

        List<BigDecimal> t1s = getNumberList(cover);
        List<BigDecimal> t2s = getNumberList(threshold);

        if(isList(coverComplex)&&isList(thresholdComplex)){
            //  均是列表
            if(t1s.size()!=t2s.size()){
                //数量不同
                return false;
            }else {
                //数量相同
                for(int i=0;i<t1s.size();i++){
                    BigDecimal t1 = getNumber(t1s.get(i));
                    BigDecimal t2 = getNumber(t2s.get(i));
                    boolean flag = number(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            BigDecimal t1 = getNumber(t1s.get(0));
            BigDecimal t2 = getNumber(t2s.get(0));
            return number(t1,t2);
        }
        return false;
    }
    private boolean number(BigDecimal t1 ,BigDecimal t2){
        if(t1!=null&&t2!=null){
            if (t1.compareTo(t2)==0) {
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
    private boolean string(Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold){
        List<String> t1s = getList(cover);
        List<String> t2s = getList(threshold);
        if(isList(coverComplex)&&isList(thresholdComplex)){
            //  均是列表
            if(t1s.size()!=t2s.size()){
                //数量不同
                return false;
            }else {
                //数量相同
                for(int i=0;i<t1s.size();i++){
                    if(t1s.get(i)!=null){
                        if (t1s.get(i).equals(t2s.get(i))) {
                            return true;
                        }else {
                            return false;
                        }
                    }else if(t2s.get(i)==null){
                        //空对空
                        return true;
                    }else {
                        //cover 空 threshold 不空
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            if(t1s.get(0)!=null){
                if (t1s.get(0).equals(t2s.get(0))) {
                    return true;
                }else {
                    return false;
                }
            }else if(t2s.get(0)==null){
                //空对空
                return true;
            }else {
                //cover 空 threshold 不空
                return false;
            }
        }
        return false;
    }

    private boolean boll(Integer coverComplex, Object cover,
                         Integer thresholdComplex, Object threshold){
        List<Boolean> t1s = getBollList(cover);
        List<Boolean> t2s = getBollList(threshold);

        if(isList(coverComplex)&&isList(thresholdComplex)){
            //  均是列表
            if(t1s.size()!=t2s.size()){
                //数量不同
                return false;
            }else {
                //数量相同
                for(int i=0;i<t1s.size();i++){
                    Boolean t1 = getBoll(t1s.get(i));
                    Boolean t2 = getBoll(t2s.get(i));
                    boolean flag = boll(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            Boolean t1 = getBoll(t1s.get(0));
            Boolean t2 = getBoll(t2s.get(0));
            return boll(t1,t2);
        }
        return false;
    }
    private boolean boll(Boolean t1 ,Boolean t2){
        if(t1!=null&&t2!=null){
            if (t1.compareTo(t2)==0) {
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
}
