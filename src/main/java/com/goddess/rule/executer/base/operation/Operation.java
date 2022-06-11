package com.goddess.rule.executer.base.operation;


import com.alibaba.fastjson.JSONArray;
import com.goddess.rule.constant.Constant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Operation {
    protected boolean oneOp = false;
    private static DateTimeFormatter ydmhms = DateTimeFormatter.ofPattern(Constant.DateFormatter.YYYY_MM_DD_HH_MM_SS);
    private static DateTimeFormatter ydm = DateTimeFormatter.ofPattern(Constant.DateFormatter.YYYY_MM_DD);
    private static DateTimeFormatter hdm = DateTimeFormatter.ofPattern(Constant.DateFormatter.HH_MM_SS);

    public abstract String getOperationCode();

    public abstract boolean execute(String dataTypeCode,Integer coverComplex,Object cover,
                                    Integer thresholdComplex,Object threshold);


    public abstract boolean timeHms(LocalTime t1,LocalTime t2);

    public abstract boolean timeYmdhms(LocalDateTime t1,LocalDateTime t2);

    public abstract boolean timeYmd(LocalDate t1,LocalDate t2);
    public abstract boolean number(BigDecimal t1 ,BigDecimal t2);

    public abstract boolean boll(Boolean t1 ,Boolean t2);



    //获取字符串列表
    protected List<String> getList(Object data){
        List<String> result = new ArrayList<>();
        if (data instanceof List) {
            for (Object o : (List) data) {
                if(o ==null){
                    result.add(null);
                }else {
                    result.add(o.toString());
                }
            }
        }else {
            if(data ==null){
                result.add(null);
            }else {
                result.add(data.toString());
            }
        }
        return result;
    }

    //获取数值
    protected BigDecimal getNumber(Object data){
        if(data == null){
            return null;
        }else {
            return new BigDecimal(data.toString());
        }
    }
    //获取数值列表
    protected List<BigDecimal> getNumberList(Object data){
        List<BigDecimal> result = new ArrayList<>();
        for (String temp:getList(data)){
            result.add(getNumber(temp));
        }
        return result;
    }

    //获取布尔值
    protected Boolean getBoll(Object data){
        if(data ==null){
            return null;
        }else {
            return new Boolean(data.toString());
        }
    }
    protected List<Boolean> getBollList(Object data){
        List<String> params = getList(data);
        List<Boolean> result = new ArrayList<>();
        for (String temp:params){
            result.add(getBoll(temp));
        }
        return result;
    }


    //获取时间 年月日时分秒
    protected LocalTime getTimeHms(Object data){
        if(data==null){
            return null;
        }else {
            return LocalTime.parse(data.toString(),hdm);
        }
    }
    //获取时间 年月日时分秒 列表
    protected List<LocalTime> getTimeHmsList(Object data){
        List<String> params = getList(data);
        List<LocalTime> result = new ArrayList<>();
        for (String temp:params){
            result.add(getTimeHms(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }
    protected List<LocalTime> getTimeHmsList(List<String> params){
        List<LocalTime> result = new ArrayList<>();
        for (String temp:params){
            result.add(getTimeHms(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }


    //获取时间 年月日时分秒
    protected LocalDateTime getTimeYdmhms(Object data){
        return LocalDateTime.parse(data.toString(),ydmhms);
    }
    //获取时间 年月日时分秒 列表
    protected List<LocalDateTime> getTimeYdmhmsList(Object data){
        List<String> params = getList(data);
        List<LocalDateTime> result = new ArrayList<>();
        for (String temp:params){
            result.add(getTimeYdmhms(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }
    protected List<LocalDateTime> getTimeYdmhmsList(List<String> params){
        List<LocalDateTime> result = new ArrayList<>();
        for (String temp:params){
            result.add(getTimeYdmhms(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }


    //获取时间 年月日
    protected LocalDate getTimeYdm(Object data){
        if(data==null){
            return null;
        }else {
            return LocalDate.parse(data.toString(), ydm);
        }

    }
    //获取时间 年月日 列表
    protected List<LocalDate> getTimeYdmList(Object data){
        List<String> params = getList(data);
        List<LocalDate> result = new ArrayList<>();
        for (String temp:params){
            result.add(getTimeYdm(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }




    protected boolean checkData(Object data,Object params){
        if(null==data){
            return true;
        }
        if(null==params){
            return true;
        }
        return false;
    }



    //判断是不是列表
    protected boolean isList(Integer flag){
        if(flag==null){
            return false;
        }
        if(flag>0){
            return true;
        }
        return false;
    }




    protected boolean timeHms(Integer coverComplex, Object cover,
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
                    LocalTime t1 = t1s.get(i);
                    LocalTime t2 = t2s.get(i);
                    boolean flag = timeHms(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            LocalTime t1 = t1s.get(0);
            LocalTime t2 = t2s.get(0);
            return timeHms(t1,t2);
        }
        return false;
    }


    protected boolean timeYmdhms(Integer coverComplex, Object cover,
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
                    LocalDateTime t1 = t1s.get(i);
                    LocalDateTime t2 = t2s.get(i);
                    boolean flag = timeYmdhms(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            LocalDateTime t1 = t1s.get(0);
            LocalDateTime t2 = t2s.get(0);
            return timeYmdhms(t1,t2);
        }
        return false;
    }


    protected boolean timeYmd(Integer coverComplex, Object cover,
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
                    LocalDate t1 = t1s.get(i);
                    LocalDate t2 = t2s.get(i);
                    boolean flag = timeYmd(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            LocalDate t1 = t1s.get(0);
            LocalDate t2 = t2s.get(0);
            return timeYmd(t1,t2);
        }
        return false;
    }


    protected boolean number(Integer coverComplex, Object cover,
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
                    BigDecimal t1 = t1s.get(i);
                    BigDecimal t2 = t2s.get(i);
                    boolean flag = number(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            BigDecimal t1 = t1s.get(0);
            BigDecimal t2 = t2s.get(0);
            return number(t1,t2);
        }
        return false;
    }



    protected boolean boll(Integer coverComplex, Object cover,
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
                    Boolean t1 = t1s.get(i);
                    Boolean t2 = t2s.get(i);
                    boolean flag = boll(t1,t2);
                    if(flag){
                        return false;
                    }
                }
            }
        } else if(!isList(coverComplex)&&!isList(thresholdComplex)){
            //  均不是列表
            //非列表
            Boolean t1 = t1s.get(0);
            Boolean t2 = t2s.get(0);
            return boll(t1,t2);
        }
        return false;
    }

    public boolean isOneOp() {
        return oneOp;
    }
}
