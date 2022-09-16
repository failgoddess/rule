package com.goddess.rule.parser.impl;

import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.handler.nozzle.Nozzle;
import com.goddess.rule.parser.NozzleParser;
import org.dom4j.Element;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 15:51
 */
public class DefaultNozzleParser implements NozzleParser {
    private static NozzleParser baseNozzleParser=null;
    private static DefaultNozzleParser instance=null;

    private DefaultNozzleParser(){}

    public static DefaultNozzleParser getInstance(NozzleParser nozzleParser) {
        if (instance != null) {
            return instance;
        }else {
            synchronized (DefaultNozzleParser.class){
                if(instance==null){
                    instance=new DefaultNozzleParser();
                    baseNozzleParser = nozzleParser;
                }
            }
        }
        return instance;
    }
    public static DefaultNozzleParser getInstance(){
        return instance;
    }

    @Override
    public Nozzle parse(Object dataObj) {
        Nozzle nozzle = null;
        if(baseNozzleParser==null){
            nozzle = new Nozzle() {
                @Override
                public Object extend(DecisionContext context) {
                    return null;
                }
            };
        }else {
            nozzle = baseNozzleParser.parse(dataObj);
        }
        Element item =(Element)dataObj;
        String code, name,remark;
        code = item.attributeValue("code");
        name = item.attributeValue("name");
        remark = item.attributeValue("remark");
        nozzle.setCode(code);
        nozzle.setName(name);
        nozzle.setRemark(remark);
        return nozzle;
    }
}
