package com.goddess.rule.executer.mode.graph;

import cn.hutool.core.collection.ListUtil;
import com.goddess.rule.constant.RuleException;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.mode.action.Action;
import com.goddess.rule.executer.mode.base.BasePo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:10
 */
public class Branch extends BasePo {

    //连接
    private List<Link> links;
    //预执行动作
    private List<Action> actions;

    private Map<String, Link> linkExecuteMap;
    private Map<String,Integer> codeIndexMap;

    public Link decision(DecisionContext decisionContext,int start){
        for (Action action:actions) {
            try {
                Object reData = action.execute(decisionContext);
                decisionContext.putGraphData(action.getCode(),reData);
            }catch (RuleException e) {
                e.printStackTrace();
                if(action.isBlock()){
                    throw e;
                }
            }
        }
        for(int i = 0; i< links.size(); i++){
            if(start<=i){
                Link linkExecute = links.get(i);
                boolean flag = linkExecute.decision(decisionContext);
                decisionContext.execLink(linkExecute,flag);
                if(flag == true){
                    return linkExecute;
                }
            }
        }
        //没找到符合条件的链接了需要回溯
        return null;
    }
    //获取指定链接编码的下一个链接
    public int getNextLinkExecuteIndex(String linkCode){
        //找不到取最大的
        int index = codeIndexMap.getOrDefault(linkCode, links.size());
        //当前链接的优先级排序索引+1 小于链接个数 才有效
        if(index+1< links.size()){
            return index+1;
        }else {
            return -1;
        }
    }

    public List<Link> getLinks() {
        return links;
    }


    public void setLinks(List<Link> links) {
        this.linkExecuteMap = links.stream().collect(Collectors.toMap(Link::getCode, o->o));
        this.links = ListUtil.sortByProperty(links,"priority");
        this.codeIndexMap = new HashMap<>(links.size());
        int index=0;
        for (Link link:this.links){
            this.codeIndexMap.put(link.getCode(),index);
            index++;
        }
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

}
