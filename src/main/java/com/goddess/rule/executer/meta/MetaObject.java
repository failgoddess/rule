package com.goddess.rule.executer.meta;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.mode.action.Mapping;
import com.goddess.rule.executer.mode.operation.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 18:33
 */
public class MetaObject {

    public static Object transformation(DecisionContext context, List<Map<String,String>> reDataset, int complex, String dataType, List<Mapping> mappings){
        if(complex>0){
            JSONArray jsonArray = new JSONArray();
            for(Map<String,String> map:reDataset){
                JSONObject reJson = new JSONObject();
                for(Mapping mapping:mappings){
                    reJson.put(mapping.getResult(),map.get(mapping.getCode()));
                }
                jsonArray.add(reJson);
            }
            return jsonArray;
        }else {
            JSONObject reJson = new JSONObject();
            Map<String,String> map = reDataset.get(0);
            for(Mapping mapping:mappings){
                reJson.put(mapping.getResult(),getData(context,0,"",""));
            }
            return reJson;
        }
    }
    public static Object getData(DecisionContext context,int complex,String dataType,Object data){
       if( Operation.isList(complex)){
           switch (dataType){
               case Constant.DataType.BOLL:
                   return Operation.getBoll(data);
               case Constant.DataType.NUMBER:
                   return Operation.getNumber(data);
               case Constant.DataType.STRING:
                   return Operation.getList(data).get(0);
               case Constant.DataType.TIME_YMD:
                   return Operation.getTimeYdm(data);
               case Constant.DataType.TIME_YMDHMS:
                   return Operation.getTimeYdmhms(data);
               case Constant.DataType.TIME_HMS:
                   return Operation.getTimeHms(data);
           }
           return data;
           //MetaClass metaClas = context.getRuleConfig().getMetaClassMap().get(dataType);
           //if(metaClas==null){
           //    return data;
           //}else {
           //    JSONObject reJson = new JSONObject();
           //    for(MetaProperty property:metaClas.getProperties()){
           //        Object val =getData(context,property.getComplex(),property.getDataType(),);
           //        JSONPath.set(reJson,"$."+property.getCode(),)
           //    }
           //
           //    return reJson;
           //}
       }else {
           switch (dataType){
               case Constant.DataType.BOLL:
                   return Operation.getBollList(data);
               case Constant.DataType.NUMBER:
                   return Operation.getNumberList(data);
               case Constant.DataType.STRING:
                   return Operation.getList(data);
               case Constant.DataType.TIME_YMD:
                   return Operation.getTimeYdmList(data);
               case Constant.DataType.TIME_YMDHMS:
                   return Operation.getTimeYdmhmsList(data);
               case Constant.DataType.TIME_HMS:
                   return Operation.getTimeHmsList(data);
               default:
                   List<Object> reObjects = new ArrayList<>();
                   reObjects.add(data);
                   return reObjects;
           }
       }

    }



}
