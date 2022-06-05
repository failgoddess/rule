package com.goddess.rule.executer.meta;

/**
 * 指标
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:18
 */
public class Quota {
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 必要入参列表
     */
    private String necessary;
    /**
     * 数据值类型 Constant.DataType
     */
    private Integer dataType;

    /**
     * 指标类型10方法类20请求类30规则对象40公式50配置
     */
    private Integer quotaType;
    /**
     * 10方法类方法
     */
    private String javaMethod;

    /**
     * 20请求类请求方法，目前只支持get、post varchar
     */
    private String requestMethod;

    /**
     * 20请求类请求路径
     */
    private String requestUrl;
    /**
     * 10、20:入参数json
     */
    private String parameter;
    /**
     * 10、20：返回值
     */
    private String feedbackRule;
    /**
     * 30规则对象路径
     */
    private String objectCode;
    /**
     * 30规则对象编码
     */
    private String objectValCode;


    /**
     * 40公式类计算公式
     */
    private String formula;

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

    public String getNecessary() {
        return necessary;
    }

    public void setNecessary(String necessary) {
        this.necessary = necessary;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(Integer quotaType) {
        this.quotaType = quotaType;
    }

    public String getJavaMethod() {
        return javaMethod;
    }

    public void setJavaMethod(String javaMethod) {
        this.javaMethod = javaMethod;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getFeedbackRule() {
        return feedbackRule;
    }

    public void setFeedbackRule(String feedbackRule) {
        this.feedbackRule = feedbackRule;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectValCode() {
        return objectValCode;
    }

    public void setObjectValCode(String objectValCode) {
        this.objectValCode = objectValCode;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
