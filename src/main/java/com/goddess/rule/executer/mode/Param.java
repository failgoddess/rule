package com.goddess.rule.executer.mode;

import com.goddess.rule.executer.mode.base.BaseDataPo;

/**
 * 规则入参
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 19:28
 */
public class Param extends BaseDataPo {

    //比传参数
    private boolean necessary;


    public boolean isNecessary() {
        return necessary;
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }

}
