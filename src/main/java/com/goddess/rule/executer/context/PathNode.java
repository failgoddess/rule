package com.goddess.rule.executer.context;

/**
 * 执行路径节点
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 23:02
 */
public class PathNode {
    private String branchCode;
    private String linkCode;
    private boolean flag;
    public PathNode(){}
    public PathNode(String branchCode, String linkCode,boolean flag) {
        this.branchCode = branchCode;
        this.linkCode = linkCode;
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getLinkCode() {
        return linkCode;
    }

    public void setLinkCode(String linkCode) {
        this.linkCode = linkCode;
    }
}
