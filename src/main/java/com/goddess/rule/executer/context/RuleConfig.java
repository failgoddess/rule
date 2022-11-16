package com.goddess.rule.executer.context;

import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.meta.MetaClass;
import com.goddess.rule.executer.meta.MetaEnum;
import com.goddess.rule.executer.mode.base.action.Action;
import com.goddess.rule.executer.mode.base.action.Param;
import com.goddess.rule.executer.mode.base.function.FunctionHandlerFactory;
import com.goddess.rule.executer.mode.rule.Rule;
import com.goddess.rule.executer.mode.rule.flow.RuleFlow;
import com.goddess.rule.executer.mode.rule.graph.RuleGraph;
import com.goddess.rule.executer.operation.OperationFactory;
import com.goddess.rule.parser.ActionParser;
import com.goddess.rule.parser.ExecuteParser;
import com.goddess.rule.parser.FormulaBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    //运行时全局共参
    private List<Param> runGlobalParams;
    private JSONObject runGlobalParamsObject;

    private String activate = "ALL";
    private Map<String,List<Param>> globalParamMap = new ConcurrentHashMap<>();
    //基础数据类型
    private List<String> dataTypes = new ArrayList<>();
    //规则对象类
    private List<MetaClass> metaClasses;
    private Map<String,MetaClass> metaClassMap = new HashMap<>();
    private MetaContext metaContext;

    //行为
    private List<Action> actions;
    private Map<String,Action> actionMap;


    //规则缓存
    private List<Rule> rules = new ArrayList<>();
    private Map<String,Rule> ruleMap = new HashMap<>();
    //公式解析器
    private FormulaBuilder formulaBuilder;

    private ActionParser actionParser;
    private ExecuteParser executeParser;


    //操作符工厂
    private OperationFactory relationFactory;
    //扩展方法工厂
    private FunctionHandlerFactory functionHandlerFactory;


    private RuleConfig(){
        dataTypes.add(Constant.DataType.NUMBER);
        dataTypes.add(Constant.DataType.STRING);
        dataTypes.add(Constant.DataType.BOLL);
        dataTypes.add(Constant.DataType.TIME_YMD);
        dataTypes.add(Constant.DataType.TIME_YMDHMS);
        dataTypes.add(Constant.DataType.TIME_HMS);
    }
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

    public List<Param> getRunGlobalParams() {
        return runGlobalParams;
    }

    public void setRunGlobalParams(List<Param> runGlobalParams) {
        this.runGlobalParamsObject = new JSONObject();
        for (Param param: runGlobalParams) {
            //这里一定当作固定值用
            this.runGlobalParamsObject.put(param.getCode(),param.getData());
        }
        this.runGlobalParams = runGlobalParams;
    }

    public JSONObject getRunGlobalParamsObject() {
        return runGlobalParamsObject;
    }

    public Rule getRule(String ruleCode){
        return getRuleMap().get(ruleCode);
    }
    public RuleFlow getRuleFlow(String ruleCode){
        return (RuleFlow) getRuleMap().get(ruleCode);
    }
    public RuleGraph getRuleGraph(String ruleCode){
        return (RuleGraph) getRuleMap().get(ruleCode);
    }
    public Context buildeContext(String ruleCode){
        return new Context(this,this.getRuleMap().get(ruleCode));
    }

    public ExecuteParser getExecuteParser() {
        return executeParser;
    }

    public void setExecuteParser(ExecuteParser executeParser) {
        this.executeParser = executeParser;
    }

    public MetaClass getMetaClassByDataType(String dataType){
        if (dataTypes.contains(dataType)) {
            return null;
        }else {
            return metaClassMap.get(dataType);
        }
    }
    public void setActions(List<Action> actions) {
        this.actions = actions;
        this.actionMap = actions.stream().collect(Collectors.toMap(Action::getCode, o->o));
    }
    public Action getAction(String code){
        return actionMap.get(code);
    }
    public Map<String, Action> getActionMap() {
        return actionMap;
    }
    public List<Action> getActions() {
        return actions;
    }

    public List<Param> getParams(String activate){
        List<Param> params  = globalParamMap.get(activate);
        return params;
    }
    public void setParams(String activate,List<Param> params){
        globalParamMap.put(activate,params);
    }

    public Map<String, List<Param>> getGlobalParamMap() {
        return globalParamMap;
    }
    public String getActivate(){
        return activate;
    }
    public void setActivate(String activate){
        this.activate = activate;
    }
}
