package com.goddess.rule.executer.handler;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.meta.MetaClass;

import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 18:39
 */
public abstract class ObjectLoader {
    //编码
    private String code;
    //名称
    private String name;
    //类路径
    private String classPath;

    public abstract Object loader(DecisionContext context,JSONObject params);

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
