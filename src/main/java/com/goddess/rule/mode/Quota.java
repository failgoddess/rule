package com.goddess.rule.mode;

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

}
