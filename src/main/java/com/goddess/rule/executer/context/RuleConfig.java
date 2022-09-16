package com.goddess.rule.executer.context;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.handler.function.FunctionHandlerFactory;
import com.goddess.rule.executer.handler.loader.ObjectLoaderFactory;
import com.goddess.rule.executer.handler.nozzle.Nozzle;
import com.goddess.rule.executer.meta.MetaClass;
import com.goddess.rule.executer.meta.MetaEnum;
import com.goddess.rule.executer.mode.action.Param;
import com.goddess.rule.executer.mode.graph.Rule;
import com.goddess.rule.executer.mode.operation.OperationFactory;
import com.goddess.rule.parser.ActionParser;
import com.goddess.rule.parser.FormulaBuilder;
import com.goddess.rule.parser.NozzleParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private JSONObject globalParamsObject;
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

    public List<Param> getGlobalParams() {
        return globalParams;
    }

    public void setGlobalParams(List<Param> globalParams) {
        this.globalParamsObject = new JSONObject();
        for (Param param:globalParams) {
            //这里一定当作固定值用
            this.globalParamsObject.put(param.getCode(),param.getData());
        }
        this.globalParams = globalParams;
    }

    public JSONObject getGlobalParamsObject() {
        return globalParamsObject;
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

    public Rule getRule(String ruleCode){
        return getRuleMap().get(ruleCode);
    }
    public DecisionContext buildeDecisionContext(String ruleCode){
        return new DecisionContext(this,this.getRuleMap().get(ruleCode));
    }
}
