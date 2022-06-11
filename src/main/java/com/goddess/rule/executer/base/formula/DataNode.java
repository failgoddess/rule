package com.goddess.rule.executer.base.formula;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:04
 */
public class DataNode extends FormulaNode {
    private String data;
    public DataNode(String text){
        this.type = "DATA";
        this.data = text;
        this.text = text;
    }


    @Override
    public Object apply(DecisionContext context) {
        return this.data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
