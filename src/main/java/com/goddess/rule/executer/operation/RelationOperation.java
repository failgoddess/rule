package com.goddess.rule.executer.operation;


import com.alibaba.fastjson2.JSONArray;
import com.goddess.rule.constant.Constant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class RelationOperation {
    private static DateTimeFormatter ydmhms = DateTimeFormatter.ofPattern(Constant.DateFormatter.YYYY_MM_DD_HH_MM_SS);
    private static DateTimeFormatter ydm = DateTimeFormatter.ofPattern(Constant.DateFormatter.YYYY_MM_DD);
    private static DateTimeFormatter hdm = DateTimeFormatter.ofPattern(Constant.DateFormatter.HH_MM_SS);

    public abstract String getOperationCode();

    public abstract boolean execute(Integer complex,String dataTypeCode,Object data, List<String> params);
    //获取字符串列表
    protected List<String> getList(Object data){
        List<String> result = new ArrayList<>();
        if (data instanceof JSONArray) {
            for (Object o : (JSONArray) data) {
                result.add(o.toString());
            }
        }else {
            result.add(data.toString());
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }


    //获取布尔值
    protected Boolean getBoll(Object data){
        return new Boolean(data.toString());
    }
    protected List<Boolean> getBollList(Object data){
        List<String> params = getList(data);
        List<Boolean> result = new ArrayList<>();
        for (String temp:params){
            result.add(getBoll(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }
    protected List<Boolean> getBollList(List<String> params){
        List<Boolean> result = new ArrayList<>();
        for (String temp:params){
            result.add(getBoll(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }


    //获取时间 年月日时分秒
    protected LocalTime getTimeHms(Object data){
        return LocalTime.parse(data.toString(),hdm);
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
        return LocalDate.parse(data.toString(), ydm);
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
    protected List<LocalDate> getTimeYdmList(List<String> params){
        List<LocalDate> result = new ArrayList<>();
        for (String temp:params){
            result.add(getTimeYdm(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }


    //获取数值
    protected BigDecimal getNumber(Object data){
        return new BigDecimal(data.toString());
    }
    //获取数值列表
    protected List<BigDecimal> getNumberList(Object data){
        List<BigDecimal> result = new ArrayList<>();
        for (String temp:getList(data)){
            result.add(new BigDecimal(temp));
        }
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }
    protected List<BigDecimal> getNumberList(List<String> params){
        List<BigDecimal> result = new ArrayList<>();
        for (String temp:params){
            result.add(new BigDecimal(temp));
        }
        //这里会过滤去重
        return result.stream().distinct().sorted().collect(Collectors.toList());
    }

    protected boolean checkData(Object data,List<String> params){
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


}
