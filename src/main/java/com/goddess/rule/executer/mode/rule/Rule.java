package com.goddess.rule.executer.mode.rule;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.mode.BasePo;
import com.goddess.rule.executer.mode.base.action.Param;

import java.util.List;

/**
 * 规则执行器
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:01
 */
public abstract class Rule<T> extends BasePo {

    // 模式 Graph 、Flow
    private String model;

    private List<Param> params;


    public abstract T decision(Context Context, JSONObject data);




    public Rule(){}




    public List<Param> getParams() {
        return params;
    }


    public void setParams(List<Param> params) {
        this.params = params;
    }



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


}
