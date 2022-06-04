package com.goddess.rule.executer;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:10
 */
public class Branch {
    //编码
    private String code;
    //名称
    private String name;
    //备注
    private String remark;

    private List<Link> linkExecutes;

    private Map<String, Link> linkExecuteMap;
    private Map<String,Integer> codeIndexMap;

    public Link decision(DecisionContext decisionContext, JSONObject dataJson, int start){
        for(int i=0;i<linkExecutes.size();i++){
            if(start<=i){
                Link linkExecute = linkExecutes.get(i);
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

    public List<Link> getLinkExecutes() {
        return linkExecutes;
    }


    public void setLinkExecutes(List<Link> linkExecutes) {
        this.linkExecuteMap = linkExecutes.stream().collect(Collectors.toMap(Link::getCode, o->o));
        this.linkExecutes = ListUtil.sortByProperty(linkExecutes,"priority");
        Map<String,Integer> codeIndexMap = new HashMap<>(linkExecutes.size());
        int index=0;
        for (Link link:this.linkExecutes){
            codeIndexMap.put(link.getCode(),index);
            index++;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
