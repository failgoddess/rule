package com.goddess.rule.executer.mode.formula;

import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.context.RuleConfig;
import com.goddess.rule.executer.handler.loader.ObjectLoader;
import com.goddess.rule.executer.handler.loader.ObjectLoaderFactory;

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
    public Object apply(DecisionContext context) {
        RuleConfig ruleConfig  = context.getRuleConfig();
        ObjectLoaderFactory objectLoaderFactory = ruleConfig.getObjectLoaderFactory();
        ObjectLoader loader = objectLoaderFactory.getLoader(this.func);
        Object reData = loader.loader(context,paramsHandler(context));
        return resultHandler(reData);
    }
}
