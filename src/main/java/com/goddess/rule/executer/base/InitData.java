package com.goddess.rule.executer.base;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/5 15:50
 */
public class InitData {
    //编码
    private String code;
    //名称
    private String name;
    //规则对象编码
    private String metaClassCode;
    //参数映射
    private String params;

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

    public String getMetaClassCode() {
        return metaClassCode;
    }

    public void setMetaClassCode(String metaClassCode) {
        this.metaClassCode = metaClassCode;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
