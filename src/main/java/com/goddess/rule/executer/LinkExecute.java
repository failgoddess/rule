package com.goddess.rule.executer;

import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.data.Link;
import com.goddess.rule.executer.context.DecisionContext;

import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:11
 */
public class LinkExecute extends Link {
    private List<ConditionExecute> conditionExecutes;

    public boolean decision(DecisionContext decisionContext, JSONObject dataJson) {
        //链接上边的所有条件之间是 且的关系 必须全部为真 才能让链接生效，如果需要多个条件是或的关系可以用两个连接指向同一个下一跳
        boolean flag = true;
        for(ConditionExecute conditionModel:conditionExecutes){
            boolean temp = conditionModel.decision(decisionContext,dataJson);
            if(temp == false){
                //有不满足的可以直接结束这个链接了
                flag = false;
                break;
            }
        }
        return flag;
    }
}
