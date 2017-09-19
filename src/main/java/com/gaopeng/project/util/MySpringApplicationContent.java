package com.gaopeng.project.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by gaopeng on 2017/7/3.
 */
public class MySpringApplicationContent implements ApplicationContextAware {
    private  static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext arg) throws BeansException {
        applicationContext=arg;
    }
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
