package com.goddess.rule.executer.base;

import com.goddess.rule.executer.base.formula.FormulaNode;
import com.goddess.rule.executer.context.RuleConfig;

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
    //默认值
    private String data;
    private FormulaNode dataFormulaNode;

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

    public FormulaNode getDataFormulaNode() {
        return dataFormulaNode;
    }

    public void setData(String data) {
        RuleConfig ruleConfig = RuleConfig.getInstance();
        this.dataFormulaNode = ruleConfig.getFormulaBuilder().getFormulaNode(data);
        this.data = data;
    }
}
