package com.goddess.rule.executer.mode.ruleLine;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.mode.operation.Operation;
import com.goddess.rule.executer.mode.operation.OperationFactory;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.meta.MetaProperty;

import java.util.List;

/**
 * @author vinc
 */

public class JavaActuator {

    private Expression expression;

    public JavaActuator(Expression expression){
        this.expression = expression;
    }

    public boolean execute( DecisionContext context, boolean logFlag){
        //逻辑
        if(Constant.ExpressionType.LOGIC.equals(expression.getExpressionType())){
            return execute(expression.getOperationCode(), expression.getSubExpression(),context,logFlag);
        }else {

            // 1>2  1被比较的阀值 >操作符 2阀值
            Operation operation = OperationFactory.getOperation(expression.getOperationCode());

            Object cover = expression.getCoverFormula().apply(context);
            Object threshold = null;
            if(!operation.isOneOp()){
                threshold = expression.getThresholdFormula().apply(context);
            }

            boolean execute = operation.execute(expression.getDataType(), expression.getCoverComplex(),cover, expression.getThresholdComplex(),threshold);

            if(logFlag){
                //log.info("path["+ruleExpression.getPropertyPath()+"]"+"dataType["+element.getDataTypeCode()+"]"+"complex["+element.getComplex()+"]被比较数["+(attrVal==null?"空":attrVal.toString())+"]"+"操作符["+ruleExpression.getOperationCode()+"]比较数["+ Joiner.on(",").join(ruleExpression.getParams()) +"]==结果["+execute+"]");
            }
            //关系
            return execute;
        }
    }


    public boolean execute(DecisionContext context){
        return execute(context,true);
    }
    //关系
    private boolean execute(String operationCode,MetaProperty property,Object attrVal, List<String> params){
        Operation operation = OperationFactory.getOperation(operationCode);
        return operation.execute(property.getDataType(),1,attrVal,1,params);
    }
    //逻辑
    private boolean execute(String operationCode, List<Expression> subExpressions,DecisionContext context, boolean logFlag){
        if("or".equals(operationCode)){
            for (Expression expression :subExpressions){
                boolean flag = new JavaActuator(expression).execute(context,logFlag);
                if (flag){return true;}
            }
            return false;
        }else {
            for (Expression expression :subExpressions){
                boolean flag = new JavaActuator(expression).execute(context,logFlag);
                if (!flag){return false;}
            }
            return true;
        }
    }
}
