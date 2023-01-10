package com.goddess.rule.executer.context;

import com.goddess.rule.constant.ConstantUtil;
import com.goddess.rule.constant.RuleException;
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
import com.goddess.rule.parser.impl.DefaultFormulaBuilder;

import java.util.ArrayList;
import java.util.Collections;
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
    private Map<String,MetaEnum> metaEnumMap = new ConcurrentHashMap<>();

    //规则对象类
    private Map<String,MetaClass> metaClassMap = new ConcurrentHashMap<>();
    private MetaContext metaContext;

    //默认运行时全局公参
    private List<Param> defGlobalParams = Collections.synchronizedList(new ArrayList<>());
    //临时指定的全局公参
    private Map<String,List<Param>> globalParamMap = new ConcurrentHashMap<>();

    //行为
    private Map<String,Action> actionMap = new ConcurrentHashMap<>();

    //规则缓存
    private Map<String,Rule> ruleMap = new ConcurrentHashMap<>();

    //行为解析器
    private ActionParser actionParser;
    //执行解析器
    private ExecuteParser executeParser;
    //公式解析器
    private FormulaBuilder formulaBuilder;

    //操作符工厂
    private OperationFactory relationFactory;
    //扩展方法工厂
    private FunctionHandlerFactory functionHandlerFactory;

    //基础数据类型
    public static List<String> dataTypes = ConstantUtil.dataTypes;

    private RuleConfig(){
        relationFactory = OperationFactory.getInstance();
        formulaBuilder = new DefaultFormulaBuilder();
        functionHandlerFactory = FunctionHandlerFactory.getInstance();
    }
    private static RuleConfig instance = null;
    public static RuleConfig getInstance() {
        if (instance != null) {
            return instance;
        }else {
            synchronized (com.goddess.rule.executer.context.RuleConfig.class){
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

    public void setMetaEnums(List<MetaEnum> metaEnums) {
        this.metaEnumMap = new ConcurrentHashMap<>();
        for (MetaEnum metaEnum : metaEnums) {
            this.metaEnumMap.put(metaEnum.getCode(),metaEnum);
        }
    }
    public void addMetaEnum(MetaEnum metaEnum) {
        this.metaEnumMap.put(metaEnum.getCode(),metaEnum);
    }
    public MetaEnum getMetaEnum(String code){
        return this.metaEnumMap.get(code);
    }
    public List<MetaEnum> getMetaEnums(){
        return this.metaEnumMap.values().stream().collect(Collectors.toList());
    }

    public MetaContext getMetaContext() {
        return metaContext;
    }
    public MetaClass getMetaClass(String code) {
        return metaClassMap.get(code);
    }
    public List<MetaClass> getMetaClasses() {
        return this.metaClassMap.values().stream().collect(Collectors.toList());
    }
    public void addMetaClass(MetaClass metaClass) {

        this.metaClassMap.put(metaClass.getCode(),metaClass);
        metaContext = new MetaContext(this.metaClassMap.values().stream().collect(Collectors.toList()),metaClassMap);

    }
    public void setMetaClasses(List<MetaClass> metaClasses) {
        this.metaClassMap = new ConcurrentHashMap<>();
        for (MetaClass metaClass : metaClasses) {
            this.metaClassMap.put(metaClass.getCode(),metaClass);
        }
        metaContext = new MetaContext(this.metaClassMap.values().stream().collect(Collectors.toList()),metaClassMap);
    }

    public List<Param> getDefGlobalParams() {
        return defGlobalParams;
    }
    public void setDefGlobalParams(List<Param> defGlobalParams) {
        this.defGlobalParams = Collections.synchronizedList(defGlobalParams);
    }
    public List<Param> getParams(String code){
        List<Param> params  = globalParamMap.get(code);
        return params;
    }
    public void setParams(String type,List<Param> params){
        globalParamMap.put(type,params);
    }

    public Map<String, List<Param>> getGlobalParamMap() {
        return globalParamMap;
    }

    public void setActions(List<Action> actions) {
        this.actionMap = new ConcurrentHashMap<>();
        for (Action action : actions) {
            this.actionMap.put(action.getCode(),action);
        }
    }
    public void addAction(Action action) {
        this.actionMap.put(action.getCode(),action);
    }
    public Action getAction(String code){
        return actionMap.get(code);
    }
    public List<Action> getActions() {
        return this.actionMap.values().stream().collect(Collectors.toList());
    }


    public void addRule(Rule rule) {
        this.ruleMap.put(rule.getCode(),rule);
    }
    public void setRules(List<Rule> rules) {
        this.ruleMap = new ConcurrentHashMap<>();
        for (Rule rule : rules) {
            if(this.ruleMap.containsKey(rule.getCode())){
                throw new RuleException("规则编码重复"+rule.getCode());
            }
            this.ruleMap.put(rule.getCode(),rule);
        }
    }
    public List<Rule> getRules() {
        return this.ruleMap.values().stream().collect(Collectors.toList());
    }
    public Rule getRule(String code) {
        return this.ruleMap.get(code);
    }
    public RuleFlow getRuleFlow(String ruleCode){
        return (RuleFlow) getRule(ruleCode);
    }
    public RuleGraph getRuleGraph(String ruleCode){
        return (RuleGraph) getRule(ruleCode);
    }


    public OperationFactory getRelationFactory() {
        return relationFactory;
    }

    public void setRelationFactory(OperationFactory relationFactory) {
        this.relationFactory = relationFactory;
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

    public ActionParser getActionParser() {
        return actionParser;
    }

    public void setActionParser(ActionParser actionParser) {
        this.actionParser = actionParser;
    }


    public Context buildContext(String ruleCode, String paramGroup){
        return new Context(this,this.ruleMap.get(ruleCode),paramGroup);
    }
    public Context buildContext(String ruleCode){
        return new Context(this,this.ruleMap.get(ruleCode),null);
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

}
