//package com.goddess.rule.ruleline;
//
//import com.goddess.rule.constant.RuleException;
//import com.goddess.rule.executer.handler.function.FunctionHandler;
//import com.goddess.rule.executer.handler.function.FunctionHandlerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.ServiceLoader;
//
///**
// * @author: 失败女神-vinc
// * @email: 18733123202@163.com
// * @date: 2022/8/30 21:27
// */
//public class RelationFactory {
//    private RelationFactory(){}
//    private static RelationFactory instance = null;
//    public static RelationFactory getInstance() {
//        if (instance != null) {
//            return instance;
//        }else {
//            synchronized (FunctionHandlerFactory.class){
//                if(instance==null){
//                    instance=new RelationFactory();
//                    ServiceLoader<RelationOperation> operations = ServiceLoader.load(RelationOperation.class);
//                    operations.forEach(operation->{
//                        handlerMap.put(operation.getName(),operation);
//                    });
//                }
//            }
//        }
//        return instance;
//    }
//    private static Map<String, FunctionHandler> handlerMap = new HashMap<>();
//
//    public FunctionHandler getFunctionHandler(String loaderCode) {
//        if (handlerMap.containsKey(loaderCode)) {
//            return handlerMap.get(loaderCode);
//        } else {
//            throw new RuleException("扩展方法："+loaderCode);
//        }
//    }
//}
