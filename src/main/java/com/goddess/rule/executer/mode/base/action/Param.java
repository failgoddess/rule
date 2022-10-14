package com.goddess.rule.executer.mode.base.action;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.constant.RuleException;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.mode.BaseDataPo;
import com.goddess.rule.executer.mode.base.formula.DataNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static void checkParam(JSONObject data, List<Param> params){
        if(ArrayUtil.isEmpty(params)){
            return;
        }
        List<String> names = new ArrayList<>();
        Map<String,String> nameMap = params.stream().collect(Collectors.toMap(Param::getCode,Param::getName));
        //筛选出所有需要必须传入的的进行非空校验
        for(Param param:params.stream().filter(o-> o.isNecessary()).collect(Collectors.toList())){
            Object value = data.get(param.getCode());
            if(value==null||value.toString().equals("")){
                names.add(param.getCode()+":"+nameMap.get(param.getCode()));
            }
        }
        if(!names.isEmpty()){
            throw new RuleException(ExceptionCode.EC_0107,String.join(",",names));
        }
    }
    public static JSONObject buildParams(Context Context,List<Param> params){
        return buildParams(Context,params,new JSONObject());
    }
    public static JSONObject buildParams(Context Context,List<Param> params, JSONObject data){
        JSONObject json = new JSONObject();
        if(ArrayUtil.isEmpty(params)){
            return json;
        }
        for (Param param : params) {
            if(param.getDataFormulaNode()!=null&& DataNode.class.equals(param.getDataFormulaNode().getClass())){
                json.put(param.getCode(),param.getDataFormulaNode().apply(Context));
            }else if(data !=null && data.get(param.getCode())!=null){
                json.put(param.getCode(),data.get(param.getCode()));
            }
        }
        for (Param param : params) {
            if(param.getDataFormulaNode()!=null&&!DataNode.class.equals(param.getDataFormulaNode().getClass())){
                json.put(param.getCode(),param.getDataFormulaNode().apply(Context));
            }
        }
        return json;
    }

}
