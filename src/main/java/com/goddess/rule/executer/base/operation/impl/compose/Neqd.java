package com.goddess.rule.executer.base.operation.impl.compose;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.base.operation.Operation;
import com.goddess.rule.executer.base.operation.OperationFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 不等于(不区分大小写)
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/12 01:16
 */
public class Neqd extends Operation {

    @Override
    public String getOperationCode() {
        return Constant.OperationType.NEQD;
    }
    @Override
    public boolean execute(String dataTypeCode, Integer coverComplex, Object cover,
                           Integer thresholdComplex, Object threshold) {
        Operation operation = OperationFactory.getOperation(Constant.OperationType.EQD);
        return !operation.execute(dataTypeCode,coverComplex,cover,thresholdComplex,threshold);
    }
    @Override
    public boolean timeHms(LocalTime t1, LocalTime t2) {
        return !OperationFactory.getOperation(Constant.OperationType.EQD).timeHms(t1,t2);
    }

    @Override
    public boolean timeYmdhms(LocalDateTime t1, LocalDateTime t2) {
        return !OperationFactory.getOperation(Constant.OperationType.EQD).timeYmdhms(t1,t2);
    }

    @Override
    public boolean timeYmd(LocalDate t1, LocalDate t2) {
        return !OperationFactory.getOperation(Constant.OperationType.EQD).timeYmd(t1,t2);
    }

    @Override
    public boolean number(BigDecimal t1, BigDecimal t2) {
        return !OperationFactory.getOperation(Constant.OperationType.EQD).number(t1,t2);
    }

    @Override
    public boolean boll(Boolean t1, Boolean t2) {
        return !OperationFactory.getOperation(Constant.OperationType.EQD).boll(t1,t2);
    }
}
