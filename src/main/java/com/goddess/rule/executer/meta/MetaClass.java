package com.goddess.rule.executer.meta;

import java.util.List;

/**
 * 规则对象类
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:11
 */
public class MetaClass {
    //编码
    private String code;
    //名称
    private String name;
    //备注
    private String remark;
    //加载器编码
    private String loaderCode;

    private List<MetaProperty> properties;


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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLoaderCode() {
        return loaderCode;
    }

    public void setLoaderCode(String loaderCode) {
        this.loaderCode = loaderCode;
    }

    public List<MetaProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<MetaProperty> properties) {
        this.properties = properties;
    }
}
