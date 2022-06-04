package com.goddess.rule.executer.context;

import cn.hutool.core.io.resource.ResourceUtil;
import com.goddess.rule.constant.BlException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.executer.Rule;
import com.goddess.rule.executer.builder.RuleConfigBuilder;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.parser.RuleParser;
import com.goddess.rule.parser.XmlRuleParser;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import java.util.Arrays;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 15:51
 */
public class XMLRuleConfigBuilder extends RuleConfigBuilder {

    public RuleConfig build(String configPath) throws Exception{
        Document document = DocumentHelper.parseText(ResourceUtil.readUtf8Str(configPath));
        if(!document.getRootElement().getName().equals("configuration")){
            throw new BlException(ExceptionCode.EC_0002);
        }
        String rulePath =document.getRootElement().element("rulePath").getTextTrim();
        //初始化
        RuleConfig ruleConfig = new RuleConfig();
        ruleConfig.setRulePath(rulePath);
        //解析规则
        List<String> rulePaths = Arrays.asList(ruleConfig.getRulePath().split(","));
        RuleParser parser = null;
        for (String path : rulePaths) {
            String type = path.substring(path.lastIndexOf(".")+1).toLowerCase();
            switch (type) {
                case Constant.ConfigType.XML:
                    parser = XmlRuleParser.getInstance();
                    break;
                default:
                    throw new BlException(ExceptionCode.EC_0001,path);
            }
            Rule rule = parser.parse(path);
            ruleConfig.getRules().add(rule);
            ruleConfig.getRuleMap().put(rule.getCode(),rule);
        }
        ruleConfig.setRelationFactory(RelationFactory.getInstance());
        return ruleConfig;
    }
}
