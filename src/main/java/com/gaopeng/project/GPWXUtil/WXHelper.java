package com.gaopeng.project.GPWXUtil;

import java.util.ResourceBundle;

/**
 * Created by gaopeng on 2017/7/18.
 */
public class WXHelper {
    private static final String PROPERTIES_NAME="wx-config";
    private static ResourceBundle bundle=null;
    private static void init(){
        try {
            bundle = ResourceBundle.getBundle(PROPERTIES_NAME);
        }catch (Exception e){
            throw new RuntimeException("wx-config.properties  not found !",e);
        }
    }
    static {
        init();
    }
    public static String getValue(String key){
        if(bundle==null){
            init();
        }
        String value=null;
        if(bundle.containsKey(key)){
            value=bundle.getString(key);
        }
        return value;
    }

}
