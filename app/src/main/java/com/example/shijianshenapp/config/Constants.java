package com.example.shijianshenapp.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Constants {

    public static final List<String> TYPE_NAME = new ArrayList<String>(){
        {
            add("特色");
            add("出行");
            add("文娱");
            add("美食");
            add("日用");
            add("电子虚拟产品");
        }
    };
    public static final TreeMap<String,String> TYPE_NAME_MAP = new TreeMap<String,String>(){
        {
            put("0","特色");
            put("1","出行");
            put("2","文娱");
            put("3","美食");
            put("4","日用");
            put("5","电子虚拟产品");
        }
    };
}
