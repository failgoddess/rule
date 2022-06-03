package com.goddess.rule.executer.operation;

import com.goddess.rule.constant.BlException;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 01:50
 */
public class RelationFactory {

    private RelationFactory(){}
    private static RelationFactory instance = null;
    public static RelationFactory getInstance() {
        if (instance != null) {
            return instance;
        }else {
            synchronized (RelationFactory.class){
                if(instance==null){
                    instance=new RelationFactory();
                }
            }
        }
        return instance;
    }

    private static Map<String, RelationOperation> handlerMap = new HashMap<>();
    public static RelationOperation getOperation(String operationCode) {
        if (handlerMap.containsKey(operationCode)) {
            return handlerMap.get(operationCode);
        } else {
            throw new BlException("找不到操作符："+operationCode);
        }
    }

    static {
        //获取该路径下所有类
        Reflections reflections = new Reflections("com.goddess.rule");
        //获取继承了RelationOperation的所有类
        Set<Class<? extends RelationOperation>> classSet = reflections.getSubTypesOf(RelationOperation.class);
        for (Class<? extends RelationOperation> clazz:classSet){
            try {
                RelationOperation operation = clazz.newInstance();
                handlerMap.put(operation.getOperationCode(),operation);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
