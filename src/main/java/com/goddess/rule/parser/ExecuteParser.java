package com.goddess.rule.parser;

import com.goddess.rule.executer.mode.base.action.Execute;

/**
 * 执行器构建器
 */
public abstract class ExecuteParser {
    private ExecuteParser executeParser;
    public ExecuteParser(ExecuteParser executeParser){
        this.executeParser = executeParser;
    }
    public Execute doParse(Object dataObj){
        Execute execute = null;
        if(executeParser ==null){
            execute = new Execute();
        }else {
            execute = executeParser.parse(dataObj,null);
        }
        return parse(dataObj,execute);
    }
    public abstract Execute parse(Object dataObj,Execute execute);


    public ExecuteParser getExecuteParser() {
        return executeParser;
    }

    public void setExecuteParser(ExecuteParser executeParser) {
        this.executeParser = executeParser;
    }
}
