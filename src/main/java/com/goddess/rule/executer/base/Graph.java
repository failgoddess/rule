package com.goddess.rule.executer.base;

import com.goddess.rule.constant.BlException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.context.PathNode;

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
public class Graph {
    //编码
    private String code;
    //名称
    private String name;
    //备注
    private String remark;
    //第一跳
    private String firstBranchCode;

    private List<Branch> branches;
    private Map<String, Branch> branchMap;

    /**
     * 决策返回一个结果编码
     * @return
     */
    public String decision(DecisionContext decisionContext){
        //查找第一跳 作为当前处理分支
        Branch thisBranch = branchMap.get(this.getFirstBranchCode());
        if (thisBranch==null) {
            //找不到第一跳
            throw new BlException(ExceptionCode.EC_0101,this.getCode(),this.getName(),this.getFirstBranchCode());
        }
        Link next= null;
        int start = 0;//回溯指针，用于回溯同一分支执行下一个链接
        do{
            next=thisBranch.decision(decisionContext,start);
            if(next == null){
                int index = -1;
                do {
                    //没有找到符合的链接 要考虑进行回溯
                    PathNode pathNode = decisionContext.revertLink();
                    if(pathNode == null){
                        //没有在可以回溯的分支了
                        throw new BlException(ExceptionCode.EC_0103,this.getCode(),this.getName());
                    }
                    Branch branch = branchMap.get(pathNode.getBranchCode());
                    //获取当前链接的下一个链接
                    index = branch.getNextLinkExecuteIndex(pathNode.getLinkCode());
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
        throw  new BlException(ExceptionCode.EC_0102,this.getCode(),this.getName());
    }


    public List<Branch> getBranches() {
        return branches;
    }
    public void setBranches(List<Branch> branches) {
        branchMap = branches.stream().collect(Collectors.toMap(Branch::getCode, o->o));
        this.branches = branches;
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
    public String getFirstBranchCode() {
        return firstBranchCode;
    }
    public void setFirstBranchCode(String firstBranchCode) {
        this.firstBranchCode = firstBranchCode;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
