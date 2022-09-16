package com.goddess.rule.parser.impl.xml;

import cn.hutool.core.io.resource.ResourceUtil;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.constant.RuleException;
import com.goddess.rule.executer.context.MetaContext;
import com.goddess.rule.executer.context.RuleConfig;
import com.goddess.rule.executer.handler.function.FunctionHandlerFactory;
import com.goddess.rule.executer.handler.loader.ObjectLoader;
import com.goddess.rule.executer.handler.loader.ObjectLoaderFactory;
import com.goddess.rule.executer.handler.nozzle.Nozzle;
import com.goddess.rule.executer.meta.MetaClass;
import com.goddess.rule.executer.meta.MetaEnum;
import com.goddess.rule.executer.meta.MetaProperty;
import com.goddess.rule.executer.mode.action.Param;
import com.goddess.rule.executer.mode.graph.Rule;
import com.goddess.rule.executer.mode.operation.OperationFactory;
import com.goddess.rule.parser.*;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 15:51
 */
public  class XMLRuleConfigBuilder implements RuleConfigBuilder {

    public RuleConfig build(String configPath) throws Exception{
        Document document = DocumentHelper.parseText(ResourceUtil.readUtf8Str(configPath));
        if(!document.getRootElement().getName().equals("configuration")){
            throw new RuleException(ExceptionCode.EC_0002);
        }
        String rulePath =document.getRootElement().element("rulePath").getTextTrim();
        //初始化
        RuleConfig ruleConfig = RuleConfig.getInstance();
        ruleConfig.setRulePath(rulePath);

        //操作符工厂
        ruleConfig.setRelationFactory(OperationFactory.getInstance());

        //解析 公式解析器
        ruleConfig.setFormulaBuilder(parseFormulaBuilder(document.getRootElement().element("metaEnvironment").element("formulaBuilder")));

        //解析自定义 actionParser解析器
        ActionParser actionParser = parseActionParser(document.getRootElement().element("metaEnvironment").element("actionParser"));
        ruleConfig.setActionParser(XmlDefaultActionDefaultParser.getInstance(actionParser));

        //解析自定义 nozzleParser解析器
        ruleConfig.setNozzleParser(parseNozzleParser(document.getRootElement().element("metaEnvironment").element("nozzleParser")));

        //解析扩展方法
        ruleConfig.setFunctionHandlerFactory(FunctionHandlerFactory.getInstance());

        //解析枚举配置
        List<MetaEnum> metaEnums = parseMetaEnums(document.getRootElement().element("metaEnvironment").element("metaEnums"));
        ruleConfig.setMetaEnums(metaEnums);
        ruleConfig.setMetaEnumMap(metaEnums.stream().collect(Collectors.toMap(MetaEnum::getCode,o->o)));

        //解析元数据配置
        List<MetaClass> metaClasses = parseMetaClasses(document.getRootElement().element("metaEnvironment").element("metaClasses"));
        ruleConfig.setMetaClasses(metaClasses);
        ruleConfig.setMetaClassMap(metaClasses.stream().collect(Collectors.toMap(MetaClass::getCode,o->o)));

        ruleConfig.setMetaContext(new MetaContext(ruleConfig.getMetaClasses(),ruleConfig.getMetaClassMap()));

        //解析加载器
        ObjectLoaderFactory objectLoaderFactory = parseObjectLoaderFactory(document.getRootElement().element("metaEnvironment").element("objectLoaders"));
        ruleConfig.setObjectLoaderFactory(objectLoaderFactory);

        //解析管道
        List<Nozzle> nozzles = parseNozzles(document.getRootElement().element("metaEnvironment").element("nozzles"),ruleConfig.getNozzleParser());
        ruleConfig.setNozzles(nozzles);
        ruleConfig.setNozzleMap(nozzles.stream().collect(Collectors.toMap(Nozzle::getCode,o->o)));

        //解析公共参数
        List<Param> globalParams = parseParams(document.getRootElement().element("metaEnvironment").element("globalParams"));
        ruleConfig.setGlobalParams(globalParams);


        //解析规则配置
        List<Rule> rules = parseRules(ruleConfig.getRulePath());
        ruleConfig.setRules(rules);
        ruleConfig.setRuleMap(rules.stream().collect(Collectors.toMap(Rule::getCode,o->o)));

        return ruleConfig;
    }
    /**
     * 处理参数节点
     * @param element
     * @return
     */
    public static List<Param> parseParams(Element element){
        if(element == null){
            return new ArrayList<>();
        }
        List<Param> params = new ArrayList<>();
        List<Element> items = element.elements("param");
        for (Element item:items){
            Param param = new Param();
            String code,name,dataType,necessary,data;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            dataType = item.attributeValue("dataType");
            necessary = item.attributeValue("necessary");
            data = item.attributeValue("data");
            param.setCode(code);
            param.setName(name);
            param.setDataType(dataType);
            if(StringUtils.isNotEmpty(necessary)&&necessary.equalsIgnoreCase("true")){
                param.setNecessary(true);
            }else {
                param.setNecessary(false);
            }
            if(StringUtils.isNotEmpty(data)){
                param.setData(data);
            }
            params.add(param);
        }
        return params;
    }

    private List<Nozzle> parseNozzles(Element element,NozzleParser nozzleParser){
        List<Nozzle> nozzles = new ArrayList<>();
        List<Element> items = element.elements("nozzle");
        for (Element item:items){
            XmlDefaultNozzleParser defaultNozzleParser = XmlDefaultNozzleParser.getInstance(nozzleParser);
            Nozzle nozzle = defaultNozzleParser.parse(item);

            String code, name;
            code = item.attributeValue("code");
            name = item.attributeValue("name");

            nozzle.setCode(code);
            nozzle.setName(name);
            nozzles.add(nozzle);
        }
        return nozzles;
    }
    private List<MetaClass> parseMetaClasses(Element element){
        List<MetaClass> metaClasses = new ArrayList<>();
        List<Element> items = element.elements("metaClass");
        for (Element item:items) {
            String code, name, loaderCode;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            loaderCode = item.attributeValue("loaderCode");

            MetaClass metaClass = new MetaClass();
            metaClass.setCode(code);
            metaClass.setName(name);
            metaClass.setLoaderCode(loaderCode);

            List<MetaProperty> properties = new ArrayList<>();
            List<Element> items2 = item.element("properties").elements("property");
            for (Element item2:items2){
                String code2, name2,dataType,primary,enumCode,metaClassCode;
                code2 = item2.attributeValue("code");
                name2 = item2.attributeValue("name");
                dataType = item2.attributeValue("dataType");
                primary = item2.attributeValue("primary");
                enumCode = item2.attributeValue("enumCode");
                metaClassCode = item2.attributeValue("metaClassCode");

                MetaProperty property = new MetaProperty();
                property.setCode(code2);
                property.setName(name2);
                property.setDataType(dataType);
                if(StringUtils.isNotEmpty(primary)&&primary.equalsIgnoreCase("true")){
                    property.setPrimary(true);
                }else {
                    property.setPrimary(false);
                }
                property.setDataType(dataType);
                property.setEnumCode(enumCode);
                property.setMetaClassCode(metaClassCode);

                properties.add(property);
            }
            metaClass.setProperties(properties);
            metaClasses.add(metaClass);
        }
        return metaClasses;
    }
    private List<MetaEnum> parseMetaEnums(Element element){
        List<MetaEnum> metaEnums = new ArrayList<>();
        List<Element> items = element.elements("metaEnum");
        for (Element item:items){
            String code, name,type;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            type = item.attributeValue("type");

            MetaEnum metaEnum = new MetaEnum();
            metaEnum.setCode(code);
            metaEnum.setName(name);
            metaEnum.setType(type);
            if(Constant.EnumType.FIXED.equalsIgnoreCase(type)){
                List<MetaEnum.Item> itemList = new ArrayList<>();
                //固定的要解析明细项
                List<Element> items2 = item.element("items").elements("item");
                for (Element item2:items2){
                    String code2, name2,pCode2;
                    code2 = item2.attributeValue("code");
                    name2 = item2.attributeValue("name");
                    pCode2 = item2.attributeValue("pCode");
                    MetaEnum.Item temp = new MetaEnum.Item();
                    temp.setCode(code2);
                    temp.setName(name2);
                    temp.setpCode(pCode2);
                    itemList.add(temp);
                }
                metaEnum.setItems(itemList);
            }else {
                //动态的要解析 加载路径
                String classPath = item.attributeValue("classPath");
                metaEnum.setClassPath(classPath);
            }
            metaEnums.add(metaEnum);
        }
        return metaEnums;
    }
    private FormulaBuilder parseFormulaBuilder(Element element){
        String classPath = element.attributeValue("classPath");
        try {
            Class<? extends FormulaBuilder> clszz = (Class<? extends FormulaBuilder>) Class.forName(classPath);
            FormulaBuilder formulaBuilder = clszz.newInstance();
            return formulaBuilder;
        }catch (Exception e){
            throw new RuleException(ExceptionCode.EC_0006,classPath);
        }
    }
    private ActionParser parseActionParser(Element element){
        String classPath = element.attributeValue("classPath");
        try {
            Class<? extends ActionParser> clszz = (Class<? extends ActionParser>) Class.forName(classPath);
            ActionParser actionParser = clszz.newInstance();
            return actionParser;
        }catch (Exception e){
            throw new RuleException(ExceptionCode.EC_0008,classPath);
        }
    }

    private NozzleParser parseNozzleParser(Element element){
        String classPath = element.attributeValue("classPath");
        try {
            Class<? extends NozzleParser> clszz = (Class<? extends NozzleParser>) Class.forName(classPath);
            NozzleParser instance = clszz.newInstance();
            return instance;
        }catch (Exception e){
            throw new RuleException(ExceptionCode.EC_0009,classPath);
        }
    }

    private ObjectLoaderFactory parseObjectLoaderFactory(Element element){
        ObjectLoaderFactory factory = ObjectLoaderFactory.getInstance();
        List<Element> items = element.elements("objectLoader");
        for (Element item:items){
            String code, name,classPath;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            classPath = item.attributeValue("classPath");
            try {
                Class<? extends ObjectLoader> clszz = (Class<? extends ObjectLoader>) Class.forName(classPath);
                ObjectLoader loader = clszz.newInstance();
                loader.setCode(code);
                loader.setName(name);
                loader.setClassPath(classPath);
                factory.register(loader);
            }catch (ClassNotFoundException e){
                throw new RuleException(ExceptionCode.EC_0003,code,name);
            } catch (InstantiationException e) {
                throw new RuleException(ExceptionCode.EC_0004,code,name);
            } catch (IllegalAccessException e) {
                throw new RuleException(ExceptionCode.EC_0004,code,name);
            }
        }
        return factory;
    }
    private List<Rule> parseRules(String rulePath) throws Exception{
        List<Rule> rules = new ArrayList<>();
        //解析规则
        List<String> rulePaths = Arrays.asList(rulePath.split(","));
        RuleParser parser = null;
        for (String path : rulePaths) {
            String type = path.substring(path.lastIndexOf(".")+1).toLowerCase();
            switch (type) {
                case Constant.ConfigType.XML:
                    parser = XMLRuleParser.getInstance();
                    break;
                default:
                    throw new RuleException(ExceptionCode.EC_0001,path);
            }
            Rule rule = parser.parse(path);
            rules.add(rule);
        }
        return rules;
    }
}
