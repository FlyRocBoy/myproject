/**
 * File: PrivilegeProperties
 * DATE: 2016/11/20
 * Created by kangxuefeng
 */
package com.gaopeng.project.util.config;

import java.util.Properties;

/**
 * @desc 获取privilege.properties配置信息
 * @date 2016/11/20 17:07
 * @author kangxuefeng@etiantian.com
 *
 */
public class PrivilegeProperties {
    private static Properties prop;
    private static String fileName = "properties/privilege.properties";
    public static Properties getInstance(){
        if(prop == null)
             prop = PropertiesUtil.getPropertiesByStream(fileName);
        return prop;
    }
}
