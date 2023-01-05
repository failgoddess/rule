package com.goddess.rule.executer.mode.base.formula;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.goddess.rule.constant.RuleException;
import com.goddess.rule.constant.ExceptionCode;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.context.RuleConfig;
import com.goddess.rule.executer.mode.base.function.FunctionHandler;
import com.goddess.rule.executer.mode.base.function.FunctionHandlerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:06
 */
public class FuncNode extends FormulaNode {
    //内置方法
    protected String func;
    //attr1:,
    protected List<Param> params;
    //.val
    protected String lastAttr;


    public FuncNode(String text){
        RuleConfig ruleConfig = RuleConfig.getInstance();
        this.type = "FUNC";
        this.text = text;
        this.params = new ArrayList<>();
        CutObj cutObj = cut(text);
        if(text.endsWith("}")){
            //%{xxxx()}
            this.lastAttr = "all";
        }else {
            //%{xxxx()}.val
            this.lastAttr = text.substring(text.lastIndexOf("}.")+2);
        }

        int leftIndex = 0;
        //%{xxxx
        this.func = cutObj.paragraphs.get(leftIndex).substring(2);
        int startIndex = leftIndex + 1;
        do {
            String nowStr = cutObj.paragraphs.get(startIndex);
            if(nowStr.startsWith("},")){
                nowStr  = nowStr.substring(2);
            }else if(nowStr.startsWith("(")){
                nowStr  = nowStr.substring(1);
            }

            int endIndex = -1;
            if (nowStr.endsWith(":") && nowStr.indexOf(",") != -1) {
                //attr1:固定值,attr2:
                List<String> ps = Arrays.asList(nowStr.split(","));
                for (int i = 0; i < ps.size(); i++) {
                    FormulaNode node = null;
                    String key = getKey(ps.get(i));
                    String val = null;
                    if (i == ps.size() - 1) {
                        //attr1:
                        val = cutObj.getStr(startIndex + 1);
                        endIndex = cutObj.getEnd(startIndex + 1);
                        node = ruleConfig.getFormulaBuilder().getFormulaNode(val);
                    } else {
                        //attr1:固定值 或者 attr1:固定值
                        val = ps.get(i).substring(ps.get(i).indexOf(":") + 1);
                        node = ruleConfig.getFormulaBuilder().getFormulaNode(val);
                    }
                    params.add(new Param(key, node));
                }
            } else if (nowStr.endsWith(":")) {
                //attr1:
                String key = getKey(nowStr);
                String val = cutObj.getStr(startIndex + 1);
                endIndex = cutObj.getEnd(startIndex + 1);
                FormulaNode node = ruleConfig.getFormulaBuilder().getFormulaNode(val);
                params.add(new Param(key, node));
            } else if (!nowStr.endsWith(":")&& !nowStr.trim().equals("")) {
                //attr1:固定值
                String key = getKey(nowStr);
                String val = nowStr.substring(nowStr.indexOf(":") + 1);
                endIndex = cutObj.getEnd(startIndex + 1);
                params.add(new Param(key, new DataNode(val)));
            } else if (!nowStr.trim().equals("")) {
                throw new RuleException(ExceptionCode.EC_0005,text);
            } else {
//                throw new BlException(ExceptionCode.EC_0005,text);
            }
            if(endIndex!=-1){
                if (cutObj.paragraphs.get(endIndex).equals("}")) {
                    //}
                    break;
                } else if (cutObj.paragraphs.get(endIndex).startsWith("},")) {
                    //},
                    startIndex = endIndex;
                } else {
                    throw new RuleException(ExceptionCode.EC_0005,text);
                }
            }else {
                break;
            }


        }while (true);
    }

    @Override
    public Object apply(Context context) {
        FunctionHandlerFactory handlerFactory = context.getRuleConfig().getFunctionHandlerFactory();
        FunctionHandler functionHandler = handlerFactory.getFunctionHandler(func);
        JSONObject attrs = paramsHandler(context);
        Object reData = functionHandler.execute(context,attrs);
        return resultHandler(reData);
    }

    /**
     * 处理入参
     * @param context
     * @return
     */
    protected JSONObject paramsHandler(Context context){
        JSONObject attrs = new JSONObject();
        for (Param param:params){
            attrs.put(param.getCode(),param.getNode().apply(context));
        }
        return attrs;
    }

    /**
     * 处理出参
     * @param reData
     * @return
     */
    protected Object resultHandler(Object reData){
        if(lastAttr.equals("all")){
            return reData;
        }else {
            return JSONPath.eval(reData, "$."+lastAttr,false);
        }
    }



    public static class Param{
        private String code;
        private FormulaNode node;

        public Param(String code, FormulaNode node){
            this.code = code;
            this.node = node;
        }
        public String getCode() {
            return code;
        }

        public FormulaNode getNode() {
            return node;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setNode(FormulaNode node) {
            this.node = node;
        }
    }
}
