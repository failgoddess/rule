package com.goddess.rule.executer.handler;

import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.executer.base.Param;
import com.goddess.rule.executer.base.formula.*;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.operation.impl.simple.In;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/5 20:26
 */
public class FormulaHandler {
    /**
     * 解析 参数 parameter 字段 里边的数据返回 map
     * 构建 解析 参数的过程
     * {
     * "storeCode":%{内置方法(attr1:21,attr2:32)}
     * "requirement":${XXX}  data 中的值
     * }
     */
    public static JSONObject buildParams(JSONObject dataJson, DecisionContext decisionContext, String paramsStr){
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isEmpty(paramsStr)){
            return jsonObject;
        }
        JSONObject params = JSONObject.parseObject(paramsStr);
        for (String attrName:params.keySet()){
            String attrValFormula = params.getString(attrName);
            jsonObject.put(attrName, FormulaHandler.execute(attrValFormula,dataJson,decisionContext));
        }
        return jsonObject;
    }
    public static FormulaNode getFormulaNode(String text){
        if(text.startsWith("@{")){
            return new LoaderNode(text);
        }else if(text.startsWith("%{")){
            return new FuncNode(text);
        }else if(text.startsWith("${")){
            return new PathNode(text);
        }else {
            return new DataNode(text);
        }
    }
    public static DataNode getDataNode(String text){
        return new DataNode(text);
    }

    public static String execute(String formula, JSONObject dataJson, DecisionContext decisionContext){
        return "";
    }
}
