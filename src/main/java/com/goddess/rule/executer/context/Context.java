package com.goddess.rule.executer.context;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.goddess.rule.executer.mode.base.action.Param;
import com.goddess.rule.executer.mode.rule.Rule;
import com.goddess.rule.executer.mode.rule.graph.Graph;
import com.goddess.rule.executer.mode.rule.graph.Link;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 决策上下文
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 23:01
 */
public class Context {
    private Rule rule;
    private Graph nowGraph;
    private RuleConfig ruleConfig;
    private MetaContext metaContext;

    //globalParams rule graph data
    private JSONObject data;
    private Stack<JSONObject> dataStack = new Stack<>();

    private JSONObject ruleData;
    private JSONObject globalParams;
    //回溯栈
    private Stack<JudgePath> judgePathStack = new Stack<>();
    //执行路径
    private Queue<JudgePath> judgePathQueue = new LinkedList<>();

    public Context(RuleConfig ruleConfig,Rule rule,String paramGroup){
        this.globalParams = new JSONObject();
        for (Param param:ruleConfig.getDefGlobalParams()) {
            this.globalParams.put(param.getCode(),param.getData());
        }
        if(paramGroup!=null&&ruleConfig.getParams(paramGroup)!=null){
            for (Param param:ruleConfig.getParams(paramGroup)) {
                this.globalParams.put(param.getCode(),param.getData());
            }
        }
        this.rule = rule;
        this.ruleConfig = ruleConfig;
    }

    /**
     * 回溯栈出栈
     * @return
     */
    public JudgePath revertLink(){
        if(this.judgePathStack.empty()){
            return null;
        }
        JudgePath judgePath = this.judgePathStack.pop();
        return judgePath;
    }
    /**
     * 当flag等于true链接生效 记录 回溯栈，
     * @return
     */
    public void execLink(Link link, boolean flag){
        JudgePath judgePath = new JudgePath(this.nowGraph.getCode(),link.getBranchCode(),link.getCode(),flag);
        if (flag){
            //加入回溯栈
            judgePathStack.push(judgePath);
        }
        //记录执行路径
        judgePathQueue.add(judgePath);
    }
    public JSONObject removeData() {
        JSONObject re =  this.data;
        this.data = dataStack.pop();
        return  re;
    }

    public JSONObject getData() {
        return data;
    }

    public void pushData(JSONObject data) {
        dataStack.push(this.data);
        this.data = data;
    }
    public void pushData(Map<String,Object> data) {
        dataStack.push(this.data);
        this.data = new JSONObject(data);
    }

    public JSONObject getRuleData() {
        //返回值不要被修改
        return ruleData;
    }
    public void putRuleData(String key, Object values) {
        if(ruleData==null){
            ruleData = new JSONObject();
        }
        ruleData.put(key,values);
    }
    public void putRuleData(JSONObject json){
        if(ruleData==null){
            ruleData = new JSONObject();
        }
        ruleData.putAll(json);

    }
    public Object getObject(String path){
        Object data = JSONPath.eval(this.data, path,false);
        if(data==null) {
            data = JSONPath.eval(this.ruleData, path, false);
            if (data == null) {
                data = JSONPath.eval(this.globalParams, path, false);
            }
        }
        return data;
    }
    public String getString(String path){
        Object data = getObject(path);
        if(data==null){
            return null;
        }
        return data.toString();
    }

    public void setNowGraph(Graph nowGraph) {
        this.nowGraph = nowGraph;
    }

    public RuleConfig getRuleConfig() {
        return ruleConfig;
    }

    public Rule getRule() {
        return rule;
    }

    public MetaContext getMetaContext() {
        return metaContext;
    }

    public void setMetaContext(MetaContext metaContext) {
        this.metaContext = metaContext;
    }

    public JSONObject getGlobalParams() {
        return globalParams;
    }

    public void setGlobalParams(JSONObject globalParams) {
        this.globalParams = globalParams;
    }
}
