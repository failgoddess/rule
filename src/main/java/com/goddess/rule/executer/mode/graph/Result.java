package com.goddess.rule.executer.mode.graph;

import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.mode.base.BaseDataPo;

/**
 * 结果执行器
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:04
 */
public class Result extends BaseDataPo {


    public Object decision(DecisionContext context){
        return getDataFormulaNode().apply(context);
    }



}
