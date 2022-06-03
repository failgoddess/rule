package com.goddess.rule.executer;

import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.constant.BlException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.data.Condition;
import com.goddess.rule.executer.context.DecisionContext;

import java.util.Objects;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:13
 */
public class ConditionExecute extends Condition {
    private OperationExecute operationExecute;

    public boolean decision(DecisionContext decisionContext, JSONObject dataJson) {
        // 1>2  1被比较的阀值 >操作符 2阀值
        String coverThreshold = this.getThreshold(dataJson,decisionContext,true);
        String threshold = this.getThreshold(dataJson,decisionContext,false);
        return operationExecute.decision(decisionContext,coverThreshold,this.getCoverThresholdDataType(),threshold,this.getThresholdDataType());
    }
    private String getThreshold(JSONObject dataJson, DecisionContext decisionContext,boolean flag){
        if(flag){
            switch (this.getCoverThresholdType()){
                case Constant.ThresholdType.FIXED:return this.getCoverThreshold();
            }
            throw new BlException(ExceptionCode.EC_0104,this.getName());
        }else {
            switch (this.getThresholdType()){
                case Constant.ThresholdType.FIXED:return this.getThreshold();
            }
            throw new BlException(ExceptionCode.EC_0105,this.getName());
        }
    }
}
