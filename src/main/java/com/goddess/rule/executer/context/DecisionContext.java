package com.goddess.rule.executer.context;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.goddess.rule.executer.mode.Link;
import com.goddess.rule.executer.mode.Rule;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 决策上下文
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 23:01
 */
public class DecisionContext {
    private Rule rule;
    private RuleConfig ruleConfig;
    private MetaContext metaContext;

    //params graph action
    private JSONObject data;
    //回溯栈
    private Stack<PathNode> pathNodeStack = new Stack<>();
    //执行路径
    private Queue<PathNode> pathNodeQueue = new LinkedList<>();

    public DecisionContext(RuleConfig ruleConfig,Rule rule, JSONObject data){
        this.data = data;
        this.rule = rule;
        this.ruleConfig = ruleConfig;
    }
    /**
     * 回溯栈出栈
     * @return
     */
    public PathNode revertLink(){
        if(this.pathNodeStack.empty()){
            return null;
        }
        PathNode pathNode = this.pathNodeStack.pop();
        return pathNode;
    }
    /**
     * 当flag等于true链接生效 记录 回溯栈，
     * @return
     */
    public void execLink(Link link, boolean flag){
        PathNode pathNode = new PathNode(link.getBranchCode(),link.getCode(),flag);
        if (flag){
            //加入回溯栈
            pathNodeStack.push(pathNode);
        }
        //记录执行路径
        pathNodeQueue.add(pathNode);
    }

    public JSONObject getData() {
        //为了不让返回值被修改
        return JSONObject.parseObject(JSONObject.toJSONString(data));
    }
    public void putData(String key,Object values) {
        if(JSONPath.contains(data,key)){

        }else {
            data.put(key,values);
        }

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
}
