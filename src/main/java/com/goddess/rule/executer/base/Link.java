package com.goddess.rule.executer.base;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;

import java.util.List;

/**
 * 链接
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:11
 */
public class Link {
    //编码
    private String code;
    //名称
    private String name;
    //分支编码
    private String branchCode;
    //优先级
    private Integer priority;
    //下一跳分支
    private String nextBranchCode;
    //下一跳结果编码
    private String nextResultCode;
    //下一跳类型：分支 结果  Constant.NextType
    private String nextType;

    private List<Condition> conditions;

    public boolean decision(DecisionContext decisionContext, JSONObject dataJson) {
        //链接上边的所有条件之间是 且的关系 必须全部为真 才能让链接生效，如果需要多个条件是或的关系可以用两个连接指向同一个下一跳
        boolean flag = true;
        for(Condition conditionModel: conditions){
            boolean temp = conditionModel.decision(decisionContext,dataJson);
            if(temp == false){
                //有不满足的可以直接结束这个链接了
                flag = false;
                break;
            }
        }
        return flag;
    }


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = ListUtil.sortByProperty(conditions,"priority");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextBranchCode() {
        return nextBranchCode;
    }

    public void setNextBranchCode(String nextBranchCode) {
        this.nextBranchCode = nextBranchCode;
    }

    public String getNextResultCode() {
        return nextResultCode;
    }

    public void setNextResultCode(String nextResultCode) {
        this.nextResultCode = nextResultCode;
    }

    public String getNextType() {
        return nextType;
    }

    public void setNextType(String nextType) {
        this.nextType = nextType;
    }
}
