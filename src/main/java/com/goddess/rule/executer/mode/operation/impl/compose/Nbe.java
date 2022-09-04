package com.goddess.rule.executer.mode.operation.impl.compose;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.mode.operation.OperationFactory;
import com.goddess.rule.executer.mode.operation.Operation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 不介于(闭区间)
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/12 01:16
 */
public class Nbe extends Operation {
    @Override
    public String getOperationCode() {
        return Constant.OperationType.NBE;
    }
    @Override
    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        Operation operation = OperationFactory.getOperation(Constant.OperationType.BE);
        return !operation.execute(dataTypeCode,coverComplex,cover,thresholdComplex,threshold);
    }
    @Override
    public boolean timeHms(LocalTime t1, LocalTime t2) {
        return !OperationFactory.getOperation(Constant.OperationType.BE).timeHms(t1,t2);
    }

    @Override
    public boolean timeYmdhms(LocalDateTime t1, LocalDateTime t2) {
        return !OperationFactory.getOperation(Constant.OperationType.BE).timeYmdhms(t1,t2);
    }

    @Override
    public boolean timeYmd(LocalDate t1, LocalDate t2) {
        return !OperationFactory.getOperation(Constant.OperationType.BE).timeYmd(t1,t2);
    }

    @Override
    public boolean number(BigDecimal t1, BigDecimal t2) {
        return !OperationFactory.getOperation(Constant.OperationType.BE).number(t1,t2);
    }

    @Override
    public boolean boll(Boolean t1, Boolean t2) {
        return !OperationFactory.getOperation(Constant.OperationType.BE).boll(t1,t2);
    }

}
