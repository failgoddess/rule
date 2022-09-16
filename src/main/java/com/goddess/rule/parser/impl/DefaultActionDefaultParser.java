package com.goddess.rule.parser.impl;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.mode.Action;
import com.goddess.rule.executer.mode.Param;
import com.goddess.rule.parser.ActionParser;
import com.goddess.rule.parser.impl.xml.XMLRuleConfigBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.List;

public class DefaultActionDefaultParser implements ActionParser {
    private static ActionParser baseActionParser=null;
    private static DefaultActionDefaultParser instance=null;

    private DefaultActionDefaultParser(){}

    public static ActionParser getInstance(ActionParser actionParser) {
        if (instance != null) {
            return instance;
        }else {
            synchronized (DefaultActionDefaultParser.class){
                if(instance==null){
                    instance=new DefaultActionDefaultParser();
                    baseActionParser = actionParser;
                }
            }
        }
        return instance;
    }
    public static ActionParser getInstance() {
        return instance;
    }

    public Action parse(Object dataObj){

        if(baseActionParser==null){
            return new Action() {
                @Override
                public Object extend(DecisionContext decisionContext, JSONObject data) {
                    return null;
                }
            };
        }else {
            Element item =(Element)dataObj;
            String code, name,block,remark;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            remark = item.attributeValue("remark");
            block = item.attributeValue("block");
            List<Param> params = XMLRuleConfigBuilder.parseParams(item.element("params"));
            Action action = baseActionParser.parse(dataObj);
            action.setCode(code);
            action.setName(name);
            action.setRemark(remark);
            action.setParams(params);
            if(StringUtils.isNotEmpty(block)&&block.equalsIgnoreCase("true")){
                action.setBlock(true);
            }else {
                action.setBlock(false);
            }
            return action;
        }
    }

}
