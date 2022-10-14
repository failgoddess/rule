package com.goddess.rule.executer.mode.base.action;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/27 19:02
 */
public class Result<T> {
    private Exception exception;
    private T content;
    public Result(){}
    public Result(Exception exception,T content){
        this.exception = exception;
        this.content = content;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
