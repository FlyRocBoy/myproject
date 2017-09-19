package com.gaopeng.project.util;

import net.sf.json.JSONObject;

/**
 * @author kangxuefeng@etiantian.com
 * @desc 公共工具类
 * @date 2017/2/13 17:32
 */
public class CommonUtils {
    /**
     * 是否为json字符串
     * @param str
     * @return
     */
    public static Boolean isJSON(String str){
        try {
            JSONObject.fromObject(str);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
