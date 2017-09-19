/**
 * File: PropertiesUtil
 * DATE: 2016/11/20
 * Created by kangxuefeng
 */
package com.gaopeng.project.util.config;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @desc 获取配置文件util类
 * @date 2016/11/20 17:02
 * @author kangxuefeng@etiantian.com
 *
 */
public class PropertiesUtil {
        /**
         *
         * 功能说明：获取properties文件对象
         * @param fileName
         * @return
         * @author: 康雪峰
         * @DATE:2014-7-7 @TIME: 下午2:21:15
         */
        public static Properties getProperties (String fileName){
            Properties pro=null;
            try {
                File file= new File(fileName);
                pro=new Properties();
                pro.load(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return pro;
        }

        /**
         *
         * 功能说明：通过流获取properties,支持获取jar中的文件
         * @param fileName
         * @return
         * @author: 康雪峰
         * @DATE:2014-7-22 @TIME: 上午9:49:05
         */
        public static Properties getPropertiesByStream(String fileName){
            ClassLoader classLoader = null;
            InputStream in = null;
            classLoader = PropertiesUtil.class.getClassLoader();
            if (classLoader != null) {
                in = classLoader.getResourceAsStream(fileName);
            }
            Properties pro=null;
            try {
                pro=new Properties();
                pro.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return pro;
        }

        /**
         *
         * 功能说明：获取properties中的值
         * @param souceName properties文件名
         * @param key	要获取值的键
         * @return
         * @author: 康雪峰
         * @DATE:2014-7-21 @TIME: 下午1:09:19
         */
        public static String getProValue(String souceName,String key){
            Properties pro=getPropertiesByStream(souceName);
            String value=(key!=null)?(pro.get(key)!=null?(String)pro.get(key):null):null;
            return value;
        }

        /**
         *
         * 方法说明:获取WEB-INF位置   Tomcat + WebSphere
         * @return
         * @author 康雪峰
         * @throws UnsupportedEncodingException
         * @2014-10-4 下午9:41:19
         */
        public static String getResourcePath() throws UnsupportedEncodingException {
            String classPath = PropertiesUtil.class.getResource("").getPath();
            classPath=classPath.substring(0,classPath.indexOf("/WEB-INF/")+9);
            try {
                classPath= URLDecoder.decode(classPath, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return classPath;
        }
}
