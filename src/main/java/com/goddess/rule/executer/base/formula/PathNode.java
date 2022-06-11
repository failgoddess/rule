package com.goddess.rule.executer.base.formula;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:13
 */
public class PathNode extends FormulaNode{
    public String path;
    public PathNode(String text){
        this.type ="PATH";
        this.path = text;
        this.text = text;
    }
    @Override
    public Object apply() {
        return null;
    }
}
