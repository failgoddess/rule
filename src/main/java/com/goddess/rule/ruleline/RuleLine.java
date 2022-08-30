package com.goddess.rule.ruleline;

import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/8/30 15:52
 */
public class RuleLine {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer priority;

    private JSONObject result;
    private JSONObject condition;

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public JSONObject getCondition() {
        return condition;
    }

    public void setCondition(JSONObject condition) {
        this.condition = condition;
    }
}
