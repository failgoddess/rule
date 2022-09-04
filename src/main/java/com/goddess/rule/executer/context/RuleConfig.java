package com.goddess.rule.executer.context;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.mode.Param;
import com.goddess.rule.executer.mode.Rule;
import com.goddess.rule.executer.handler.function.FunctionHandlerFactory;
import com.goddess.rule.executer.handler.loader.ObjectLoaderFactory;
import com.goddess.rule.executer.handler.nozzle.Nozzle;
import com.goddess.rule.executer.handler.source.Source;
import com.goddess.rule.executer.meta.MetaClass;
import com.goddess.rule.executer.meta.MetaEnum;
import com.goddess.rule.executer.mode.operation.OperationFactory;
import com.goddess.rule.parser.ActionParser;
import com.goddess.rule.parser.FormulaBuilder;
import com.goddess.rule.parser.NozzleParser;
import com.goddess.rule.parser.SourceParser;

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
    //枚举
    private List<MetaEnum> metaEnums;
    private Map<String,MetaEnum> metaEnumMap = new HashMap<>();

    //全局共参
    private List<Param> globalParams;
    //规则对象类
    private List<MetaClass> metaClasses;
    private Map<String,MetaClass> metaClassMap = new HashMap<>();
    private MetaContext metaContext;
    //执行器




    //规则缓存
    private List<Rule> rules = new ArrayList<>();
    private Map<String,Rule> ruleMap = new HashMap<>();
    //公式解析器
    private FormulaBuilder formulaBuilder;

    private List<Source> sources = new ArrayList<>();
    private Map<String,Source> sourceMap = new HashMap<>();
    private SourceParser sourceParser;

    private ActionParser actionParser;

    private List<Nozzle> nozzles = new ArrayList<>();
    private Map<String,Nozzle> nozzleMap = new HashMap<>();
    private NozzleParser nozzleParser;


    //操作符工厂
    private OperationFactory relationFactory;
    //加载器工厂
    private ObjectLoaderFactory objectLoaderFactory;
    //扩展方法工厂
    private FunctionHandlerFactory functionHandlerFactory;


    private RuleConfig(){}
    private static RuleConfig instance = null;
    public static RuleConfig getInstance() {
        if (instance != null) {
            return instance;
        }else {
            synchronized (RuleConfig.class){
                if(instance==null){
                    instance=new RuleConfig();
                }
            }
        }
        return instance;
    }




    public DecisionContext buildeDecisionContext(String ruleCode, JSONObject data){
        return new DecisionContext(this,this.getRuleMap().get(ruleCode),data);
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

    public OperationFactory getRelationFactory() {
        return relationFactory;
    }

    public void setRelationFactory(OperationFactory relationFactory) {
        this.relationFactory = relationFactory;
    }

    public ObjectLoaderFactory getObjectLoaderFactory() {
        return objectLoaderFactory;
    }

    public void setObjectLoaderFactory(ObjectLoaderFactory objectLoaderFactory) {
        this.objectLoaderFactory = objectLoaderFactory;
    }

    public List<MetaEnum> getMetaEnums() {
        return metaEnums;
    }

    public List<MetaClass> getMetaClasses() {
        return metaClasses;
    }

    public void setMetaClasses(List<MetaClass> metaClasses) {
        this.metaClasses = metaClasses;
    }

    public void setMetaEnums(List<MetaEnum> metaEnums) {
        this.metaEnums = metaEnums;
    }
    public Map<String, MetaClass> getMetaClassMap() {
        return metaClassMap;
    }

    public Map<String, MetaEnum> getMetaEnumMap() {
        return metaEnumMap;
    }

    public void setMetaClassMap(Map<String, MetaClass> metaClassMap) {
        this.metaClassMap = metaClassMap;
    }

    public void setMetaEnumMap(Map<String, MetaEnum> metaEnumMap) {
        this.metaEnumMap = metaEnumMap;
    }

    public FormulaBuilder getFormulaBuilder() {
        return formulaBuilder;
    }

    public void setFormulaBuilder(FormulaBuilder formulaBuilder) {
        this.formulaBuilder = formulaBuilder;
    }

    public SourceParser getSourceParser() {
        return sourceParser;
    }

    public void setSourceParser(SourceParser sourceParser) {
        this.sourceParser = sourceParser;
    }

    public FunctionHandlerFactory getFunctionHandlerFactory() {
        return functionHandlerFactory;
    }

    public void setFunctionHandlerFactory(FunctionHandlerFactory functionHandlerFactory) {
        this.functionHandlerFactory = functionHandlerFactory;
    }

    public MetaContext getMetaContext() {
        return metaContext;
    }

    public void setMetaContext(MetaContext metaContext) {
        this.metaContext = metaContext;
    }

    public ActionParser getActionParser() {
        return actionParser;
    }

    public void setActionParser(ActionParser actionParser) {
        this.actionParser = actionParser;
    }

    public NozzleParser getNozzleParser() {
        return nozzleParser;
    }

    public void setNozzleParser(NozzleParser nozzleParser) {
        this.nozzleParser = nozzleParser;
    }


    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public Map<String, Source> getSourceMap() {
        return sourceMap;
    }

    public void setSourceMap(Map<String, Source> sourceMap) {
        this.sourceMap = sourceMap;
    }

    public List<Param> getGlobalParams() {
        return globalParams;
    }

    public void setGlobalParams(List<Param> globalParams) {
        this.globalParams = globalParams;
    }

    public List<Nozzle> getNozzles() {
        return nozzles;
    }

    public void setNozzles(List<Nozzle> nozzles) {
        this.nozzles = nozzles;
    }

    public Map<String, Nozzle> getNozzleMap() {
        return nozzleMap;
    }

    public void setNozzleMap(Map<String, Nozzle> nozzleMap) {
        this.nozzleMap = nozzleMap;
    }
}
