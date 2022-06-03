package com.goddess.rule.executer;

import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.constant.BlException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.data.Graph;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.context.PathNode;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 决策图执行器
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:05
 */
public class GraphExecute extends Graph {
    private List<BranchExecute> branchExecutes;
    private Map<String,BranchExecute> branchExecuteMap;

    /**
     * 决策返回一个结果编码
     * @param dataJson
     * @return
     */
    public String decision(DecisionContext decisionContext, JSONObject dataJson){
        //查找第一跳 作为当前处理分支
        BranchExecute thisBranch = branchExecuteMap.get(this.getFirstBranchCode());
        if (thisBranch==null) {
            //找不到第一跳
            throw new BlException(ExceptionCode.EC_0101,this.getCode(),this.getName(),this.getFirstBranchCode());
        }
        LinkExecute next= null;
        int start = 0;//回溯指针，用于回溯同一分支执行下一个链接
        do{
            next=thisBranch.decision(decisionContext,dataJson,start);
            if(next == null){
                int index = -1;
                do {
                    //没有找到符合的链接 要考虑进行回溯
                    PathNode pathNode = decisionContext.revertLink();
                    if(pathNode == null){
                        //没有在可以回溯的分支了
                        throw new BlException(ExceptionCode.EC_0103,this.getCode(),this.getName());
                    }
                    BranchExecute branchExecute = branchExecuteMap.get(pathNode.getBranchCode());
                    //获取当前链接的下一个链接
                    index = branchExecute.getNextLinkExecuteIndex(pathNode.getLinkCode());
                    if(index!=-1){
                        //不为-1表示 回溯栈中的路径节点不是所在分支的最后一个链接可以继续处理这个分支的下一个链接
                        //为-1表示 回溯栈中的路径节点是所在分支的最后一个链接要 继续回溯
                        start = index;
                        thisBranch = branchExecute;
                        next = branchExecute.getLinkExecutes().get(start);
                    }
                }while (index!=-1);
            }else {
                //找到了符合条件的链接
                if(Objects.equals(Constant.NextType.BRANCH,next.getNextType())){
                    //下一跳是分支
                    String nextBranchCode = next.getNextBranchCode();
                    thisBranch = branchExecutes.stream().filter(o->Objects.equals(o.getCode(),nextBranchCode)).findFirst().get();
                }else {
                    //下一跳是结果
                    return next.getNextResultCode();
                }
            }
        }while (next!=null);
        throw  new BlException(ExceptionCode.EC_0102,this.getCode(),this.getName());
    }
}
