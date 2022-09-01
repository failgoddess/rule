package com.goddess.rule.parser.impl.xml;

import cn.hutool.core.io.resource.ResourceUtil;
import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.base.*;
import com.goddess.rule.executer.context.RuleConfig;
import com.goddess.rule.parser.RuleParser;
import com.goddess.rule.parser.impl.DefaultActionDefaultParser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 12:26
 */
public class XMLRuleParser implements RuleParser {


    private static XMLRuleParser instance = null;
    private DefaultActionDefaultParser xmlActionDefaultParser = null;

    private XMLRuleParser(DefaultActionDefaultParser xmlActionDefaultParser){
        this.xmlActionDefaultParser = xmlActionDefaultParser;
    }
    public static XMLRuleParser getInstance(DefaultActionDefaultParser actionParser) {
        if (instance != null) {
            return instance;
        }else {
            synchronized (XMLRuleParser.class){
                if(instance==null){
                    instance=new XMLRuleParser(actionParser);
                }
            }
        }
        return instance;
    }

    @Override
    public Rule parse(String path) throws Exception {
        return parseRule(DocumentHelper.parseText(ResourceUtil.readUtf8Str(path)));
    }

    /**
     * 处理rule根节点
     * @param document
     * @return
     */
    private Rule parseRule(Document document){
        Rule rule = null;
        //根结点必须要是 rule
        if(document.getRootElement().getName().equals("rule")){
            rule = new Rule();
            String code, name;
            code = document.getRootElement().attributeValue("code");
            name = document.getRootElement().attributeValue("name");
            List<Graph> graphs = parseGraphs(document.getRootElement().element("graphs"));
            List<Result> results = parseResults(document.getRootElement().element("results"));
            List<Param> params = parseParams(document.getRootElement().element("params"));
            List<InitData> initDatas = parseInitDatas(document.getRootElement().element("initDatas"));

            rule.setCode(code);
            rule.setName(name);
            rule.setParams(params);
            rule.setInitDatas(initDatas);
            rule.setGraphs(graphs);
            rule.setResults(results);
        }
        return rule;
    }

    /**
     * 处理决策图节点
     * @param element
     * @return
     */
    private List<Graph> parseGraphs(Element element){
        List<Graph> graphs = new ArrayList<>();
        List<Element> items = element.elements("graph");
        for (Element item:items){
            Graph graph = new Graph();
            String code, name,firstBranchCode;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            firstBranchCode = item.attributeValue("firstBranchCode");
            List<Branch> branches = parseBranches(item.element("branches"));
            graph.setCode(code);
            graph.setName(name);
            graph.setFirstBranchCode(firstBranchCode);
            graph.setBranches(branches);
            graphs.add(graph);
        }
        return graphs;
    }

    /**
     * 处理分支节点
     * @param element
     * @return
     */
    private List<Branch> parseBranches(Element element){
        List<Branch> branches = new ArrayList<>();
        List<Element> items = element.elements("branch");
        for (Element item:items){
            Branch branch = new Branch();
            String code, name;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            List<Link> links = parseLinks(item.element("links"),branch);
            branch.setCode(code);
            branch.setName(name);
            branch.setLinks(links);
            branch.setActions(parseActions(item.element("actions")));
            branches.add(branch);
        }
        return branches;
    }

    /**
     * 处理action
     * @return
     */
    private List<Action> parseActions(Element element){
        List<Action> actions = new ArrayList<>();
        if(element==null){
            return actions;
        }
        List<Element> items = element.elements("action");
        for (Element item:items){
            Action action =xmlActionDefaultParser.parse(item);
            if(action!=null){
                actions.add(action);
            }

        }
        return actions;
    }

    /**
     * 处理链接节点
     * @param element
     * @param branch
     * @return
     */
    private List<Link> parseLinks(Element element,Branch branch){
        List<Link> links = new ArrayList<>();
        List<Element> items = element.elements("link");
        for (Element item:items){
            Link link = new Link();
            String code,name,priority,nextType,nextResultCode,nextBranchCode,eternal;
            eternal = item.attributeValue("eternal");
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            priority = item.attributeValue("priority");
            nextType = item.attributeValue("nextType");
            nextResultCode = item.attributeValue("nextResultCode");
            nextBranchCode = item.attributeValue("nextBranchCode");

            link.setCode(code);
            link.setName(name);
            link.setBranchCode(branch.getCode());
            link.setPriority(Integer.parseInt(priority));
            link.setNextType(nextType);
            link.setNextBranchCode(nextBranchCode);
            link.setNextResultCode(nextResultCode);
            if("true".equalsIgnoreCase(eternal)){
                link.setEternal(true);
            }else {
                link.setEternal(false);
                link.setExpression(parseExpression(item.element("expression")));
            }

            links.add(link);
        }
        return links;
    }

    /**
     * 处理条件节点
     * @param element
     * @return
     */
    public static Expression parseExpression(Element element){

        if(element ==null){
            return null;
        }
        Expression expression = new Expression();
        String name,coverComplex,expressionType,cover,operationCode,dataType,thresholdComplex,thresholdType,threshold;

        name = element.attributeValue("name");
        expressionType = element.attributeValue("expressionType");
        operationCode = element.attributeValue("operationCode");
        dataType = element.attributeValue("dataType");
        if(Constant.ExpressionType.RELATION.equalsIgnoreCase(expressionType)){
            expressionType = Constant.ExpressionType.RELATION;

            coverComplex = element.attributeValue("coverComplex");
            cover = element.attributeValue("cover");
            thresholdComplex = element.attributeValue("thresholdComplex");
            threshold = element.attributeValue("threshold");


            if(StringUtils.isNotEmpty(coverComplex)){
                expression.setCoverComplex(Integer.parseInt(coverComplex));
            }else {
                expression.setCoverComplex(0);
            }
            if(StringUtils.isNotEmpty(cover)){
                expression.setCover(cover);
            }

            if(StringUtils.isNotEmpty(thresholdComplex)){
                expression.setThresholdComplex(Integer.parseInt(thresholdComplex));
            }else {
                expression.setThresholdComplex(0);
            }
            if(StringUtils.isNotEmpty(threshold)){
                expression.setThreshold(threshold);
            }

        }else if(Constant.ExpressionType.LOGIC.equalsIgnoreCase(expressionType)){
            expressionType = Constant.ExpressionType.LOGIC;
            List<Expression> subConditions = new ArrayList<>();
            List<Element>items = element.element("subExpressions").elements("expression");
            for(Element item:items){
                subConditions.add(parseExpression(item));
            }
            expression.setSubExpression(subConditions);
        }
        expression.setName(name);
        expression.setExpressionType(expressionType);
        expression.setOperationCode(operationCode);
        expression.setDataType(dataType);
        return expression;
    }

    /**
     * 处理结果节点
     * @param element
     * @return
     */
    private List<Result> parseResults(Element element){
        List<Result> results = new ArrayList<>();
        List<Element> items = element.elements("result");
        for (Element item:items){
            Result result = new Result();
            String code,name,resultType,dataType,data;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            dataType = item.attributeValue("dataType");
            data = item.attributeValue("data");
            result.setCode(code);
            result.setName(name);
            result.setDataType(dataType);
            result.setData(data);
            results.add(result);
        }
        return results;
    }

    /**
     * 处理参数节点
     * @param element
     * @return
     */
    private List<Param> parseParams(Element element){
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

    /**
     * 处理初始数据节点
     * @param element
     * @return
     */
    private List<InitData> parseInitDatas(Element element){
        RuleConfig ruleConfig = RuleConfig.getInstance();
        List<InitData> initDatas = new ArrayList<>();
        List<Element> items = element.elements("initData");
        for (Element item:items){
            InitData initData = new InitData();
            String code,name,metaClassCode,params;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            metaClassCode = item.attributeValue("metaClass");
            params = item.attributeValue("params");
            initData.setCode(code);
            initData.setName(name);
            initData.setMetaClassCode(metaClassCode);
            initData.setParams(params);
            if(StringUtils.isEmpty(metaClassCode)){
                initData.setFormulaNode(ruleConfig.getFormulaBuilder().getFormulaNode(params));
            }else {
                String text = "@{"+metaClassCode+"("+params+")}";
                initData.setFormulaNode(ruleConfig.getFormulaBuilder().getFormulaNode(text));
            }
            initDatas.add(initData);
        }
        return initDatas;
    }
}
