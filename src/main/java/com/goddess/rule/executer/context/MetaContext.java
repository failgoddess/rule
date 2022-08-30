package com.goddess.rule.executer.context;

import com.goddess.rule.executer.meta.MetaClass;
import com.goddess.rule.executer.meta.MetaProperty;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/8/30 21:41
 */
public class MetaContext {
    private Map<String, MetaProperty> propertyMap;
    private Map<String, MetaClass> classMap;
    private List<MetaClass> metaClasses;

    public MetaContext(List<MetaClass> metaClasses,Map<String, MetaClass> metaClassMap) {
        this.metaClasses = metaClasses;
        this.propertyMap = new HashMap<>();
        this.classMap = new HashMap<>();
        List<String> paths = new ArrayList<>();
        for (MetaClass metaClass:metaClasses){
            classMap.put(metaClass.getCode(),metaClass);
            paths.add(metaClass.getCode());
            init(metaClass,paths,metaClassMap);
            paths.remove(paths.size()-1);
        }
    }

    private void init(MetaClass metaClass, List<String> paths,Map<String, MetaClass> metaClassMap){
        for (MetaProperty property:metaClass.getProperties()){
            MetaProperty pElement = propertyMap.get(Joiner.on(".").join(paths));
            if(pElement!=null){
                property.setComplex(property.getComplex()+pElement.getComplex());
            }
            paths.add(property.getCode());
            propertyMap.put(Joiner.on(".").join(paths),property);
            if(property.getMetaClassCode()!=null){
                init(metaClassMap.get(property.getMetaClassCode()),paths,metaClassMap);
            }
            paths.remove(paths.size()-1);
        }
    }

    public Map<String, MetaProperty> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, MetaProperty> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public Map<String, MetaClass> getClassMap() {
        return classMap;
    }

    public void setClassMap(Map<String, MetaClass> classMap) {
        this.classMap = classMap;
    }

    public List<MetaClass> getMetaClasses() {
        return metaClasses;
    }

    public void setMetaClasses(List<MetaClass> metaClasses) {
        this.metaClasses = metaClasses;
    }
}
