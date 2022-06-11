package com.goddess.rule.executer.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.handler.ObjectLoader;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/11 18:46
 */
public class StoreObjectLoader extends ObjectLoader {
    @Override
    public Object loader(DecisionContext context, JSONObject params) {
        JSONObject store = new JSONObject();
        if(params.getString("code").equals("1002")){
            store.put("code","1002");
            store.put("name","红松会所");
            store.put("startTime","2019-10-06");
            store.put("level","10");
            store.put("area","广东");
        }else {
            store.put("code","1004");
            store.put("name","天安会所");
            store.put("startTime","2022-01-26");
            store.put("level","20");
            store.put("area","河北");
        }
        return store;
    }
}
