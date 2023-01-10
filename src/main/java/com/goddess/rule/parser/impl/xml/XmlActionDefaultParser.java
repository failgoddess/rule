package com.goddess.rule.parser.impl.xml;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.context.RuleConfig;
import com.goddess.rule.executer.mode.base.action.*;
import com.goddess.rule.parser.ActionParser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlActionDefaultParser implements ActionParser {
    private static ActionParser instance=null;

    public XmlActionDefaultParser(ActionParser instance){
        this.instance = instance;
    }


    public Action parse(Object dataObj){
        Element item =(Element)dataObj;
        String code, name,remark,complex,dataType;
        code = item.attributeValue("code");
        name = item.attributeValue("name");
        remark = item.attributeValue("remark");
        complex = item.attributeValue("complex");
        dataType = item.attributeValue("dataType");

        List<Param> params = XMLRuleConfigBuilder.parseParams(item.element("params"));
        List<Mapping> mappings =  parseMappings(item.element("mappings"));
        Action action = null;
        if(instance!=null){
            action = instance.parse(dataObj);
        }else {
            action = new Action() {
                @Override
                public void extend(Context context, JSONObject data, Execute execute,List<Map<String,Object>>resultMapList) {

                }
            };
        }
        action.setCode(code);
        action.setName(name);
        action.setRemark(remark);
        action.setParams(params);
        action.setMappings(mappings);
        action.setDataType(dataType);
        action.setMetaClass(dataType==null?null:RuleConfig.getInstance().getMetaClassByDataType(dataType));
        action.setComplex(StringUtils.isNotEmpty(complex)?Integer.parseInt(complex):0);

        return action;

    }

    public static List<Mapping> parseMappings(Element element){
        if(element==null){
            return new ArrayList<>();
        }
        List<Mapping> mappings = new ArrayList<>();
        List<Element> items = element.elements("mapping");
        for(Element item :items){
            Mapping mapping = new Mapping();
            String code,result;
            code = item.attributeValue("code");
            result = item.attributeValue("result");
            mapping.setCode(code);
            mapping.setResult(result);
            mappings.add(mapping);
        }
        return mappings;
    }
}
