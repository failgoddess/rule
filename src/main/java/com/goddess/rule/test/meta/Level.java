package com.goddess.rule.test.meta;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/7/6 19:11
 */
public enum Level {
    L10("10","甲"),
    L20("20","乙"),
    L30("30","丙");






    Level(String code, String msg) {
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
