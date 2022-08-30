package com.goddess.rule.executer.meta;

/**
 * 规则对象属性项
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:12
 */
public class MetaProperty {

    //编码
    private String code;
    //名称
    private String name;
    //备注
    private String remark;
    //是否主键 1是0否
    private boolean primary;
    //数据类型
    private String dataType;
    //自定义类编码
    private String metaClassCode;
    private MetaClass metaClass;
    //列表 1是0否
    private Integer complex;
    //枚举范围
    private String enumCode;


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

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getMetaClassCode() {
        return metaClassCode;
    }

    public void setMetaClassCode(String metaClassCode) {
        this.metaClassCode = metaClassCode;
    }

    public Integer getComplex() {
        return complex;
    }

    public void setComplex(Integer complex) {
        this.complex = complex;
    }

    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

}
