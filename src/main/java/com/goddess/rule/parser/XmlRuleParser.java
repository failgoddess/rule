package com.goddess.rule.parser;

import cn.hutool.core.io.resource.ResourceUtil;
import com.goddess.rule.executer.*;
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
public class XmlRuleParser implements RuleParser{

    private XmlRuleParser(){}
    private static XmlRuleParser instance = null;
    public static XmlRuleParser getInstance() {
        if (instance != null) {
            return instance;
        }else {
            synchronized (XmlRuleParser.class){
                if(instance==null){
                    instance=new XmlRuleParser();
                }
            }
        }
        return instance;
    }

    @Override
    public Rule parse(String path) throws Exception {
        return parseRule(DocumentHelper.parseText(ResourceUtil.readUtf8Str(path)));
    }
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

            rule.setCode(code);
            rule.setName(name);
            rule.setGraphs(graphs);
            rule.setResults(results);
        }
        return rule;
    }
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
            branch.setLinkExecutes(links);
            branches.add(branch);
        }
        return branches;
    }
    private List<Link> parseLinks(Element element,Branch branch){
        List<Link> links = new ArrayList<>();
        List<Element> items = element.elements("link");
        for (Element item:items){
            Link link = new Link();
            String code,name,priority,nextType,nextResultCode,nextBranchCode;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            priority = item.attributeValue("priority");
            nextType = item.attributeValue("nextType");
            nextResultCode = item.attributeValue("nextResultCode");
            nextBranchCode = item.attributeValue("nextBranchCode");

            List<Condition> conditions = parseConditions(item.element("conditions"));
            link.setCode(code);
            link.setName(name);
            link.setBranchCode(branch.getCode());
            link.setPriority(Integer.parseInt(priority));
            link.setNextType(nextType);
            link.setNextBranchCode(nextBranchCode);
            link.setNextResultCode(nextResultCode);
            link.setConditionExecutes(conditions);
            links.add(link);
        }
        return links;
    }
    private List<Condition> parseConditions(Element element){
        List<Condition> conditions = new ArrayList<>();
        List<Element> items = element.elements("link");
        for (Element item:items){
            Condition condition = new Condition();
            String name,priority,coverComplex,coverType,cover,operationCode,dataType,thresholdComplex,thresholdType,threshold;

            name = item.attributeValue("name");
            priority = item.attributeValue("priority");
            coverComplex = item.attributeValue("coverComplex");
            coverType = item.attributeValue("coverType");
            cover = item.attributeValue("cover");
            operationCode = item.attributeValue("operationCode");
            dataType = item.attributeValue("dataType");
            thresholdComplex = item.attributeValue("thresholdComplex");
            thresholdType = item.attributeValue("thresholdType");
            threshold = item.attributeValue("threshold");

            condition.setName(name);
            condition.setPriority(Integer.parseInt(priority));
            condition.setCoverComplex(Integer.parseInt(coverComplex));
            condition.setCoverType(coverType);
            condition.setCover(cover);
            condition.setOperationCode(operationCode);
            condition.setDataType(dataType);
            condition.setThresholdComplex(Integer.parseInt(thresholdComplex));
            condition.setThresholdType(thresholdType);
            condition.setThreshold(threshold);
            condition.setCoverComplex(Integer.parseInt(coverComplex));
            conditions.add(condition);
        }
        return conditions;
    }
    private List<Result> parseResults(Element element){
        List<Result> results = new ArrayList<>();
        List<Element> items = element.elements("result");
        for (Element item:items){
            Result result = new Result();
            String code,name,resultType,dataType;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            resultType = item.attributeValue("resultType");
            dataType = item.attributeValue("dataType");
            result.setCode(code);
            result.setName(name);
            result.setResultType(resultType);
            result.setDataType(dataType);
            results.add(result);
        }
        return results;
    }
}
