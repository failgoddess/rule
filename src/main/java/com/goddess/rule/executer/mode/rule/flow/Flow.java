package com.goddess.rule.executer.mode.rule.flow;

import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.constant.RuleException;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.mode.BasePo;
import com.goddess.rule.executer.mode.base.action.Execute;
import com.goddess.rule.executer.mode.base.action.Result;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/18 13:47
 */
public class Flow extends BasePo {
    List<Execute> executes;

    public List<Object> decision(Context context){
        List<Object> reData = new ArrayList<>();
        for (Execute exec : executes) {
            try {
                Result<Object> result = exec.execute(context);
                if (exec.isInit()) {
                    context.putRuleData(exec.getCode(),result.getContent());
                    reData.add(result.getContent());
                }
                if (result.getException()!=null) {
                    throw result.getException();
                }
            }catch (RuleException e) {
                e.printStackTrace();
                if(e.getCode().equals(ExceptionCode.EC_0301)){
                    throw e;
                }
                if (exec.isBlock()) {
                    throw new RuleException(ExceptionCode.EC_0301,exec.getCode());
                }
            }catch (Exception e) {
                e.printStackTrace();
                if (exec.isBlock()) {
                    throw new RuleException(ExceptionCode.EC_0301,exec.getCode());
                }
            }

        }
        return reData;
    }


    public List<Execute> getExecutes() {
        return executes;
    }

    public void setExecutes(List<Execute> executes) {
        this.executes = executes;
    }
}
