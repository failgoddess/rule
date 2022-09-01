package com.goddess.rule.executer.handler.loader;

import com.goddess.rule.constant.RuleException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 18:41
 */
public class ObjectLoaderFactory {
    private ObjectLoaderFactory(){}
    private static ObjectLoaderFactory instance = null;
    public static ObjectLoaderFactory getInstance() {
        if (instance != null) {
            return instance;
        }else {
            synchronized (ObjectLoaderFactory.class){
                if(instance==null){
                    instance=new ObjectLoaderFactory();
                }
            }
        }
        return instance;
    }
    private static Map<String, ObjectLoader> handlerMap = new HashMap<>();
    public void register(ObjectLoader instance) {
        handlerMap.put(instance.getCode(), instance);
    }
    public ObjectLoader getLoader(String loaderCode) {
        if (handlerMap.containsKey(loaderCode)) {
            return handlerMap.get(loaderCode);
        }else if (handlerMap.containsKey(loaderCode.toUpperCase())) {
            return handlerMap.get(loaderCode.toUpperCase());
        } else {
            throw new RuleException("找不到加载器："+loaderCode);
        }
    }
}
