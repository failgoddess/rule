package com.goddess.rule.executer.mode.base.action;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.constant.RuleException;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.mode.BasePo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/18 15:47
 */
public class Execute extends BasePo {
    //阻断
    private boolean block;
    //是否将返回值加入上下文
    private boolean init;
    //别名
    private String alias;
    //行为编码
    private String actionCode;
    private List<Inject> injects;
    public Result<Object> execute(Context context){
        Map<String,Object> data = new HashMap<>();
        for (Inject inject:injects) {
            data.put(inject.getCode(),inject.execute(context));
        }
        Action action = context.getRuleConfig().getAction(actionCode);
        if(action==null){
            throw new RuleException(ExceptionCode.EC_0302,getCode(),getActionCode());
        }
        return action.execute(context,this,new JSONObject(data));
    }

    public List<Inject> getInjects() {
        return injects;
    }

    public void setInjects(List<Inject> injects) {
        this.injects = injects;
    }

    public boolean isInit() {
        return init;
    }

    public boolean isBlock() {
        return block;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
