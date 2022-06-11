package com.goddess.rule.executer.base.formula;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:04
 */
public class DataNode extends FormulaNode {
    private List<String> datas;
    public DataNode(String text){
        this.type = "DATA";
        this.datas = Arrays.asList(text.split(","));
        this.text = text;
    }


    @Override
    public Object apply(DecisionContext context) {
       if(this.datas.size()>1){
           return this.datas;
       }else {
           return this.datas.get(0);
       }
    }
}
