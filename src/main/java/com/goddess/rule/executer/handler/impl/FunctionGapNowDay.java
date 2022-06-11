package com.goddess.rule.executer.handler.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.handler.FunctionHandler;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/11 21:16
 */
public class FunctionGapNowDay implements FunctionHandler {
    @Override
    public String getName() {
        return "gapNowDay";
    }

    @Override
    public Object execute(DecisionContext context, JSONObject data) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date =LocalDate.parse(data.getString("time"), Constant.Formatter.ydm);
        LocalDateTime time = LocalDateTime.of(date, LocalTime.MIN);
        return Duration.between(now,time).toDays();
    }
}
