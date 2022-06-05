package com.goddess.rule.executer.base;

/**
 * 规则入参
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 19:28
 */
public class Param {
    //编码
    private String code;
    //名称
    private String name;
    //数据类型
    private String dataType;
    //比传参数
    private boolean necessary;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isNecessary() {
        return necessary;
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }
}
