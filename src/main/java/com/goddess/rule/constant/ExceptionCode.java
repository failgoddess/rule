package com.goddess.rule.constant;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:32
 */
public enum ExceptionCode {

    EC_0101("0101","{0}决策图{1}找不到第一跳{2}"),
    EC_0102("0102","{0}决策树{1}执行异常"),
    EC_0103("0103","{0}决策图{1}没有符合条件的链接结果"),
    EC_0104("0104","未能识别条件{0}中的被比较的阀值类型"),
    EC_0105("0105","未能识别条件{0}中的阀值类型"),
    EC_0106("0106","{0}不支持的操作类型"),


    EC_0201("0201","数据不能为空"),
    EC_0202("0202","时间转换异常"),
    EC_0203("0203","不支持的数据类型"),
    EC_0204("0204","介于类判断阀值异常"),

    EC_9999("9999","异常");

    ExceptionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public final String code;

    public final String msg;
}
