package com.goddess.rule.parser.impl;

import com.goddess.rule.executer.base.formula.*;
import com.goddess.rule.executer.handler.ObjectLoaderFactory;
import com.goddess.rule.parser.FormulaBuilder;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/5 20:26
 */
public class DefaultFormulaBuilder implements FormulaBuilder {
    public FormulaNode getFormulaNode(String text){
        if(text.startsWith("@{")){
            return new LoaderNode(text);
        }else if(text.startsWith("%{")){
            return new FuncNode(text);
        }else if(text.startsWith("${")){
            return new PathNode(text);
        }else {
            return new DataNode(text);
        }
    }
}
