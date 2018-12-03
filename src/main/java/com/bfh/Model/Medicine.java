package com.bfh.Model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Medicine {

    Map<String,String> detail;


    public Medicine(){
        detail = new HashMap<>();
    }


    public void add(String key,String value){
        detail.put(key,value);
    }


    //获取此药都有什么属性
    public Set<String> getAttr(){
        return detail.keySet();
    }


    //根据属性名称获取属性的名字
    public String getOneAttr(String name){
        if(detail.containsKey(name))
            return detail.get(name);
        else
            return null;
    }

}
