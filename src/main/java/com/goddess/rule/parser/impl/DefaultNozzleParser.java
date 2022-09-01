package com.goddess.rule.parser.impl;

import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.handler.loader.ObjectLoaderFactory;
import com.goddess.rule.executer.handler.nozzle.Nozzle;
import com.goddess.rule.executer.handler.source.Source;
import com.goddess.rule.parser.NozzleParser;
import com.goddess.rule.parser.SourceParser;

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
            synchronized (ObjectLoaderFactory.class){
                if(instance==null){
                    instance=new DefaultNozzleParser();
                    baseNozzleParser = nozzleParser;
                }
            }
        }
        return instance;
    }

    @Override
    public Nozzle parse(Object dataObj) {
        if(baseNozzleParser==null){
            return new Nozzle() {
                @Override
                public void setSource(Source source){

                }

                @Override
                public void extend(DecisionContext context) {

                }
            };
        }else {
            return baseNozzleParser.parse(dataObj);
        }
    }
}
