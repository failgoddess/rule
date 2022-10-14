package com.goddess.rule.executer.mode.rule.graph;

import com.goddess.rule.constant.RuleException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.context.JudgePath;
import com.goddess.rule.executer.mode.BasePo;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 决策图执行器
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:05
 */
public class Graph extends BasePo {

    //第一跳
    private String firstBranchCode;

    private List<Branch> branches;
    private Map<String, Branch> branchMap;

    /**
     * 决策返回一个结果编码
     * @return
     */
    public String decision(Context Context){
        Context.setNowGraph(this);
        //查找第一跳 作为当前处理分支
        Branch thisBranch = branchMap.get(this.getFirstBranchCode());
        if (thisBranch==null) {
            //找不到第一跳
            throw new RuleException(ExceptionCode.EC_0101,this.getCode(),this.getName(),this.getFirstBranchCode());
        }
        Link next= null;
        int start = 0;//回溯指针，用于回溯同一分支执行下一个链接
        do{
            next=thisBranch.decision(Context,start);
            if(next == null){
                int index = -1;
                do {
                    //没有找到符合的链接 要考虑进行回溯
                    JudgePath judgePath = Context.revertLink();
                    if(judgePath == null){
                        //没有在可以回溯的分支了
                        throw new RuleException(ExceptionCode.EC_0103,this.getCode(),this.getName());
                    }
                    Branch branch = branchMap.get(judgePath.getBranchCode());
                    //获取当前链接的下一个链接
                    index = branch.getNextLinkExecuteIndex(judgePath.getLinkCode());
                    if(index!=-1){
                        //不为-1表示 回溯栈中的路径节点不是所在分支的最后一个链接可以继续处理这个分支的下一个链接
                        //为-1表示 回溯栈中的路径节点是所在分支的最后一个链接要 继续回溯
                        start = index;
                        thisBranch = branch;
                        next = branch.getLinks().get(start);
                    }
                }while (index!=-1);
            }else {
                //找到了符合条件的链接
                if(Objects.equals(Constant.NextType.BRANCH,next.getNextType())){
                    //下一跳是分支
                    String nextBranchCode = next.getNextBranchCode();
                    thisBranch = branchMap.get(nextBranchCode);
                }else {
                    //下一跳是结果
                    return next.getNextResultCode();
                }
            }
        }while (next!=null);
        throw  new RuleException(ExceptionCode.EC_0102,this.getCode(),this.getName());
    }


    public List<Branch> getBranches() {
        return branches;
    }
    public void setBranches(List<Branch> branches) {
        branchMap = branches.stream().collect(Collectors.toMap(Branch::getCode, o->o));
        this.branches = branches;
    }

    public String getFirstBranchCode() {
        return firstBranchCode;
    }
    public void setFirstBranchCode(String firstBranchCode) {
        this.firstBranchCode = firstBranchCode;
    }
}
