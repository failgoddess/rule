package com.goddess.rule.executer.mode.action;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.mode.base.BasePo;

import java.util.List;

/**
 * 行为
 */

public abstract class Action extends BasePo {
    private boolean block;
    private List<Param> params;

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }
    public abstract Object extend(DecisionContext context, JSONObject data);
    public Object execute(DecisionContext context){
        //解析injons
        JSONObject json = Param.buildParams(context,this.params);
        return extend(context,json);
    }

}
