package com.goddess.rule.executer.base.formula;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.goddess.rule.executer.context.DecisionContext;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:13
 */
public class PathNode extends FormulaNode{
    public String path;
    public PathNode(String text){
        this.type ="PATH";
        this.path = "$." +text.substring(2,text.length()-1);
        this.text = text;
    }
    @Override
    public Object apply(DecisionContext context) {
        return JSONPath.eval(context.getData(), path,false);
    }
}
