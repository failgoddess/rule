package com.goddess.rule.executer.base.formula;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/11 16:11
 */
public class LoaderNode extends FuncNode{

    public LoaderNode(String text) {
        super(text);
        this.type = "LOADER";
    }

    @Override
    public Object apply() {
        return null;
    }
}
