package com.goddess.rule.parser.impl.xml;

import com.goddess.rule.executer.mode.base.action.Execute;
import com.goddess.rule.executer.mode.base.action.Inject;
import com.goddess.rule.parser.ExecuteParser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class XmlExecuteDefaultParser extends ExecuteParser {


    public XmlExecuteDefaultParser(ExecuteParser executeParser){
        super(executeParser);
    }

    @Override
    public Execute parse(Object dataObj, Execute execute) {
       if (execute==null){
           execute = new Execute();
       }
        Element item =(Element)dataObj;
        String code, name,block,remark,init,actionCode;
        code = item.attributeValue("code");
        name = item.attributeValue("name");
        remark = item.attributeValue("remark");
        block = item.attributeValue("block");
        init = item.attributeValue("init");
        actionCode = item.attributeValue("actionCode");

        execute.setCode(code);
        execute.setName(name);
        execute.setRemark(remark);
        execute.setActionCode(actionCode);
        execute.setInjects(parseInjects(item.element("injects")));

        if(StringUtils.isNotEmpty(block)&&block.equalsIgnoreCase("true")){
            execute.setBlock(true);
        }else {
            execute.setBlock(false);
        }
        if(StringUtils.isEmpty(init)||init.equalsIgnoreCase("true")){
            execute.setInit(true);
        }else {
            execute.setInit(false);
        }
        return execute;
    }
    private List<Inject> parseInjects(Element element){
        List<Inject> injects= new ArrayList<>();
        if(element==null){
            return injects;
        }
        List<Element> items = element.elements("inject");
        for (Element item:items) {
            Inject inject = new Inject();
            String code, name,remark,data;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            remark = item.attributeValue("remark");
            data = item.attributeValue("data");
            inject.setCode(code);
            inject.setName(name);
            inject.setRemark(remark);
            inject.setData(data);
            injects.add(inject);
        }
        return injects;
    }
}
