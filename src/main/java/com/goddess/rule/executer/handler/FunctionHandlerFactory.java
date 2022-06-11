package com.goddess.rule.executer.handler;

import com.goddess.rule.constant.BlException;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 18:41
 */
public class FunctionHandlerFactory {
    private FunctionHandlerFactory(){}
    private static FunctionHandlerFactory instance = null;
    public static FunctionHandlerFactory getInstance() {
        if (instance != null) {
            return instance;
        }else {
            synchronized (FunctionHandlerFactory.class){
                if(instance==null){
                    instance=new FunctionHandlerFactory();
                    ServiceLoader<FunctionHandler> functionHandlers = ServiceLoader.load(FunctionHandler.class);
                    functionHandlers.forEach(function->{
                        handlerMap.put(function.getName(),function);
                    });
                }
            }
        }
        return instance;
    }
    private static Map<String, FunctionHandler> handlerMap = new HashMap<>();

    public FunctionHandler getFunctionHandler(String loaderCode) {
        if (handlerMap.containsKey(loaderCode)) {
            return handlerMap.get(loaderCode);
        } else {
            throw new BlException("扩展方法："+loaderCode);
        }
    }
}
