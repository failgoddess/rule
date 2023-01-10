package com.goddess.rule.executer.mode.rule;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.meta.MetaClass;
import com.goddess.rule.executer.meta.MetaEnum;
import com.goddess.rule.executer.mode.BasePo;
import com.goddess.rule.executer.mode.base.action.Action;
import com.goddess.rule.executer.mode.base.action.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //枚举
    private Map<String, MetaEnum> metaEnumMap;

    //规则对象类
    private Map<String, MetaClass> metaClassMap;
    //行为
    private Map<String, Action> actionMap;


    public abstract T decision(Context Context, JSONObject data);




    public Rule(){}




    public List<Param> getParams() {
        return params;
    }
    public void setParams(List<Param> params) {
        this.params = params;
    }

    public Action getAction(String code){
        return actionMap.get(code);
    }
    public void setActions(List<Action> actions) {
        this.actionMap = new HashMap<>();
        for (Action action : actions) {
            this.actionMap.put(action.getCode(),action);
        }
    }

    public MetaClass getMetaClassByDataType(String dataType){
        return metaClassMap.get(dataType);
    }

    public void setMetaClasses(List<MetaClass > metaClasses) {
        this.metaClassMap = new HashMap<>();
        for (MetaClass metaClass : metaClasses) {
            this.metaClassMap.put(metaClass.getCode(),metaClass);
        }
    }

    public void setMetaEnums(List<MetaEnum > metaEnums) {
        this.metaEnumMap = new HashMap<>();
        for (MetaEnum metaEnum : metaEnums) {
            this.metaEnumMap.put(metaEnum.getCode(),metaEnum);
        }
    }

    public Map<String, MetaEnum> getMetaEnumMap() {
        return metaEnumMap;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }



}
