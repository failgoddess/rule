package com.goddess.rule.executer.mode.ruleLine;

import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.operation.Operation;
import com.goddess.rule.executer.operation.OperationFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vinc
 */

public class JavaActuator {

    public static boolean execute( Context context,Expression expression,Log log){
        if(Constant.ExpressionType.LOGIC.equals(expression.getExpressionType())){
            //逻辑
            return execute(expression.getOperationCode(), expression.getSubExpression(),context,log);
        }else {

            //关系
            // 1>2  1被比较的阀值 >操作符 2阀值
            Operation operation = OperationFactory.getOperation(expression.getOperationCode());

            Object cover = expression.getCoverFormula().apply(context);
            Object threshold = null;
            if(!operation.isOneOp()){
                threshold = expression.getThresholdFormula().apply(context);
            }

            boolean execute = operation.execute(expression.getDataType(), expression.getCoverComplex(),cover, expression.getThresholdComplex(),threshold);

            if(log!=null){
                log.setDataType(expression.getDataType());
                log.setCoverComplex(expression.getCoverComplex());
                log.setCover(expression.getCoverFormula().getText());
                log.setCoverData(cover);

                log.setOperationCode(expression.getOperationCode());

                if(!operation.isOneOp()){
                    log.setThresholdComplex(expression.getThresholdComplex());
                    log.setThreshold(expression.getThresholdFormula().getText());
                    log.setThresholdData(threshold);
                }

                log.setFlag(execute);
            }
            //关系
            return execute;
        }
    }


    public static boolean execute(Context context,Expression expression){
        return execute(context,expression,null);
    }

    //逻辑
    private static boolean execute(String operationCode, List<Expression> subExpressions,Context context,Log log){
        if("or".equals(operationCode)){
            for (Expression expression :subExpressions){
                Log subLog = new Log();
                log.addSubLog(subLog);
                boolean flag = JavaActuator.execute(context,expression,subLog);
                if (flag){return true;}
            }
            return false;
        }else {
            for (Expression expression :subExpressions){
                Log subLog = new Log();
                log.addSubLog(subLog);
                boolean flag = JavaActuator.execute(context,expression,subLog);
                if (!flag){return false;}
            }
            return true;
        }
    }

    public static class Log{
        private String dataType;

        private int coverComplex;
        private String cover;
        private Object coverData;

        private String operationCode;

        private int thresholdComplex;
        private String threshold;
        private Object thresholdData;

        private boolean flag;

        private List<Log> subLog;

        public List<Log> getSubLog() {
            return subLog;
        }
        public void addSubLog(Log log){
            if (subLog == null) {
                subLog = new ArrayList<>();
            }
            subLog.add(log);
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public void setCoverComplex(int coverComplex) {
            this.coverComplex = coverComplex;
        }

        public void setOperationCode(String operationCode) {
            this.operationCode = operationCode;
        }

        public void setThresholdComplex(int thresholdComplex) {
            this.thresholdComplex = thresholdComplex;
        }


        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getDataType() {
            return dataType;
        }

        public int getCoverComplex() {
            return coverComplex;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public Object getCoverData() {
            return coverData;
        }

        public void setCoverData(Object coverData) {
            this.coverData = coverData;
        }

        public String getOperationCode() {
            return operationCode;
        }

        public int getThresholdComplex() {
            return thresholdComplex;
        }

        public String getThreshold() {
            return threshold;
        }

        public void setThreshold(String threshold) {
            this.threshold = threshold;
        }

        public Object getThresholdData() {
            return thresholdData;
        }

        public void setThresholdData(Object thresholdData) {
            this.thresholdData = thresholdData;
        }

        public boolean isFlag() {
            return flag;
        }
    }
}
