package com.goddess.rule.parser.impl;

import com.goddess.rule.executer.handler.loader.ObjectLoaderFactory;
import com.goddess.rule.executer.handler.source.Source;
import com.goddess.rule.parser.SourceParser;
import org.dom4j.Element;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 15:51
 */
public class DefaultSourceParser implements SourceParser {
    private static SourceParser baseSourceParser=null;
    private static DefaultSourceParser instance=null;

    private DefaultSourceParser(){}

    public static DefaultSourceParser getInstance(SourceParser sourceParser) {
        if (instance != null) {
            return instance;
        }else {
            synchronized (ObjectLoaderFactory.class){
                if(instance==null){
                    instance=new DefaultSourceParser();
                    baseSourceParser = sourceParser;
                }
            }
        }
        return instance;
    }

    @Override
    public Source parse(Object dataObj) {
        if(baseSourceParser==null){
            return new Source();
        }else {
            return baseSourceParser.parse(dataObj);
        }
    }
}
