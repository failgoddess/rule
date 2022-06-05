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
//                    ServiceLoader<ObjectLoader> serviceLoader = ServiceLoader.load(ObjectLoader.class);
//                    serviceLoader.forEach(loader->{
//                        handlerMap.put(loader.getCode(),loader);
//                    });
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
        } else {
            throw new BlException("找不到加载器："+loaderCode);
        }
    }
}
