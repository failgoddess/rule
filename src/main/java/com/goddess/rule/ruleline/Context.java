//package com.goddess.rule.ruleline;
//
//import cn.hutool.core.collection.CollectionUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.JSONPath;
//import com.goddess.rule.executer.meta.MetaObject;
//import com.google.common.base.Joiner;
//
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Context implements Serializable {
//    private static final long serialVersionUID = 8294180014912103005L;
//
//    private transient Map<String, MetaObject.Element> attributesMap;
//    private transient Map<String, MetaObject> objectMap;
//    @Getter
//    private List<MetaObject> metaObjects;
//
//    private void init(MetaObject metaObject,List<String> paths){
//        for (MetaObject.Element element:metaObject.getElements()){
//            MetaObject.Element pElement = attributesMap.get(Joiner.on(".").join(paths));
//            if(pElement!=null){
//                element.setComplex(element.getComplex()+pElement.getComplex());
//            }
//            paths.add(element.getCode());
//            attributesMap.put(Joiner.on(".").join(paths),element);
//            if(element.getMetaObject()!=null){
//                init(element.getMetaObject(),paths);
//            }
//            paths.remove(paths.size()-1);
//        }
//    }
//
//    public void setMetaObjects(List<MetaObject> metaObjects) {
//        this.metaObjects = metaObjects;
//        this.attributesMap = new HashMap<>();
//        this.objectMap = new HashMap<>();
//        List<String> paths = new ArrayList<>();
//        for (MetaObject metaObject:metaObjects){
//            objectMap.put(metaObject.getCode(),metaObject);
//            paths.add(metaObject.getCode());
//            init(metaObject,paths);
//            paths.remove(paths.size()-1);
//        }
//    }
//
//    private Map<String, List<String>> loadRootAttributesObjectMap() {
//        Map<String, List<String>> re =new HashMap<>();
//        for (MetaObject metaObject:metaObjects){
//            List<String> rs = new ArrayList<>();
//            for (String key: loadAttributesMap().keySet()){
//                MetaObject.Element element = loadAttributesMap().get(key);
//                if(key.indexOf(metaObject.getCode()+".")==0&&key.split("\\.").length==2&&element.getMetaObject()!=null){
//                    rs.add(key);
//                }
//            }
//            re.put(metaObject.getCode(),rs);
//        }
//        return re;
//    }
//    public Map<String, MetaObject> loadObjectMap() {
//        return objectMap;
//    }
//    public Map<String, MetaObject.Element> loadAttributesMap() {
//        if (CollectionUtil.isEmpty(this.attributesMap)){
//            List<String> paths = new ArrayList<>();
//            for (MetaObject metaObject:this.metaObjects){
//                paths.add(metaObject.getCode());
//                init(metaObject,paths);
//                paths.remove(paths.size()-1);
//            }
//        }
//        return attributesMap;
//    }
//    private Map<String, MetaObject.Element> loadAllMetaObjectAttributesMap() {
//        Map<String, MetaObject.Element> reMap = new HashMap<>();
//        for (String key: loadAttributesMap().keySet()){
//            MetaObject.Element element = loadAttributesMap().get(key);
//            if(element.getMetaObject()!=null){
//                reMap.put(key,element);
//            }
//        }
//        return reMap;
//    }
//
//
//    //获取某对象 属性类型为对象并且需要自动加载的属性项
//    private Map<String, MetaObject.Element> getAutoAttributesMap(String key){
//        Map<String, MetaObject.Element> reMap = new HashMap<>();
//        MetaObject.Element element = loadAttributesMap().get(key);
//        if(element == null||element.getMetaObject()==null){
//            return reMap;
//        }
//        for (MetaObject.Element el:element.getMetaObject().getElements()){
//            if(el.isCanload()){
//                reMap.put(key,el);
//            }
//        }
//        return reMap;
//    }
//
//    private void builder(JSONObject nowDataMap,String key){
//        MetaObject.Element element = loadAttributesMap().get(key);
//        Object val = JSONPath.eval(nowDataMap,"$."+key,false);
//        if (!(val!=null && val instanceof String)||!(element.isCanload())) {
//            //没有被加载成对象 数据不满足不可以加载
//            //log.error("没有被加载成对象 数据不满足不可以加载 "+key);
//            return;
//        }
//        if (val instanceof Map || val instanceof List) {
//            //要是列表或者就说明已经加载成对象了
//            return;
//        }
//        if(element.isList()){
//            //是
//        }else {
//            //不是
//            ITargetType targetType = TargetFactory.getHandler(element.getMetaObject().getTargetTypeCode());
//            assert targetType != null;
//            Object target = targetType.getObject(val.toString());
//            if(target!=null) {
//                JSONObject targetObject = (JSONObject) JSONObject.toJSON(target);
//                //看看当前对象需不需要继续加载 ,也就是当前对象的 属性项还有没有 需要加载的对象
//                Map<String, MetaObject.Element> attributesMap = getAutoAttributesMap(key);
//                for (String ikey : attributesMap.keySet()) {
//                    builder(targetObject, ikey);
//                }
//                JSONPath.set(nowDataMap, "$." + key, targetObject);
//            }
//
//        }
//    }
//    /**
//     * {
//     *   "orderNo": "700381",
//     *   "tradeType": 1,
//     *   "skuQuantity": 20,
//     *   "paymentAmount": 1000,
//     *   "createTime": "SDF0001",
//     *   "shishouzhekou": 0.5,
//     *   "jifenzhekou": 1.1,
//     *   "skuInfo": "N8AFASG001A148",
//     *   "storeCode": "1002",
//     *   "member": 1
//     * }
//     */
//    public void builderAll(JSONObject data) {
//        Map<String, List<String>> rootAttributesObjectMap = this.loadRootAttributesObjectMap();
//        for(String objectCode:rootAttributesObjectMap.keySet()){
//            List<String> ls = rootAttributesObjectMap.get(objectCode);
//            for (String key:ls){
//                this.builder(data,key);
//            }
//        }
//    }
//    public void cacheClearObject(String targetTypeCode,String targetCode){
//        //ITargetType targetType = TargetFactory.getHandler(targetTypeCode);
//        //targetType.clearObject(targetCode);
//    }
//
//    public List<MetaObject> getMetaObjects() {
//        return metaObjects;
//    }
//}
