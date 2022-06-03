package com.goddess.rule.constant;

/**
 * 业务异常
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:29
 */
public class BlException extends RuntimeException {
    private String msg;
    private String code = "500";
    public BlException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMsg());
        this.msg = exceptionCode.getMsg();
        this.code = exceptionCode.getCode();
    }
    public BlException(ExceptionCode exceptionCode,String... params) {
        super(handlerMsg(exceptionCode.getMsg(),params));
        this.msg = exceptionCode.getMsg();
        this.code = exceptionCode.getCode();
    }
    public BlException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public BlException(String msg,String[] params) {
        super(handlerMsg(msg,params));
        this.msg =super.getMessage();
    }

    public BlException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }
    public BlException(String msg,String[] params, Throwable e) {
        super(handlerMsg(msg,params), e);
        this.msg = handlerMsg(msg,params);
    }

    public BlException(String msg, String code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
    public BlException(String msg,String[] params, String code) {
        super(handlerMsg(msg,params));
        this.msg =super.getMessage();
        this.code = code;
    }

    public BlException(String msg, String code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
    public BlException(String msg,String[] params, String code, Throwable e) {
        super(handlerMsg(msg,params), e);
        this.msg = super.getMessage();
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    private static String handlerMsg(String msg,String[] params){
        String reMsg = new String(msg);
        int i=0;
        for (String param:params){
            reMsg.replace("["+i+"]",param);
            i++;
        }
        return reMsg;
    }
}
