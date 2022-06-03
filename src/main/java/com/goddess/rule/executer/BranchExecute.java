package com.goddess.rule.executer;

import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.data.Branch;
import com.goddess.rule.executer.context.DecisionContext;

import java.util.List;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:10
 */
public class BranchExecute extends Branch {
    private List<LinkExecute> linkExecutes;

    private Map<String,LinkExecute> linkExecuteMap;
    private Map<String,Integer> codeIndexMap;

    public LinkExecute decision(DecisionContext decisionContext, JSONObject dataJson, int start){
        for(int i=0;i<linkExecutes.size();i++){
            if(start<=i){
                LinkExecute linkExecute = linkExecutes.get(i);
                boolean flag = linkExecute.decision(decisionContext,dataJson);
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
        int index = codeIndexMap.getOrDefault(linkCode,linkExecutes.size());
        //当前链接的优先级排序索引+1 小于链接个数 才有效
        if(index+1<linkExecutes.size()){
            return index+1;
        }else {
            return -1;
        }
    }

    public List<LinkExecute> getLinkExecutes() {
        return linkExecutes;
    }
}
