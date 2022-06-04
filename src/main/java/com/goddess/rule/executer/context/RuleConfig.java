package com.goddess.rule.executer.context;

import com.goddess.rule.constant.BlException;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.executer.Rule;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.parser.RuleParser;
import com.goddess.rule.parser.XmlRuleParser;

import java.util.*;

/**
 * 配置中心
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 14:14
 */
public class RuleConfig {
    //流程定义资源地址
    private String rulePath;
    //多配置类型
//    private Boolean multipleConfigType;

    //规则缓存
    private List<Rule> rules = new ArrayList<>();
    private Map<String,Rule> ruleMap = new HashMap<>();

    private RelationFactory relationFactory;




    public DecisionContext getDecisionContext(){
        return new DecisionContext();
    }
    public String getRulePath() {
        return rulePath;
    }

    public void setRulePath(String rulePath) {
        this.rulePath = rulePath;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public Map<String, Rule> getRuleMap() {
        return ruleMap;
    }

    public void setRuleMap(Map<String, Rule> ruleMap) {
        this.ruleMap = ruleMap;
    }

    public RelationFactory getRelationFactory() {
        return relationFactory;
    }

    public void setRelationFactory(RelationFactory relationFactory) {
        this.relationFactory = relationFactory;
    }

}
