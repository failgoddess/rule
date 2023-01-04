package com.goddess.rule.executer.mode.rule.flow;

import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.constant.RuleException;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.mode.BasePo;
import com.goddess.rule.executer.mode.base.action.Execute;
import com.goddess.rule.executer.mode.base.action.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/18 13:47
 */
public class Flow extends BasePo {
    List<Execute> executes;

    public List<Object> decision(Context context){
        context.setFlow(this);
        List<Object> reData = new ArrayList<>();
        Map<String,Object> execData = new HashMap<>();
        for (Execute exec : executes) {
            try {
                Result<Object> result = exec.execute(context);
                if (exec.isInit()) {
                    execData.put(exec.getCode(),result.getContent());
                    context.putRuleData(this.getCode(),execData);
                    //context.putRuleData(exec.getCode(),result.getContent());
                    reData.add(result.getContent());
                }
                //System.out.println(exec.getCode());
                //System.out.println(JSONObject.toJSONString(result));
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
        context.removeFlow();
        return reData;
    }


    public List<Execute> getExecutes() {
        return executes;
    }

    public void setExecutes(List<Execute> executes) {
        this.executes = executes;
    }
}
