package com.gaopeng.project.util;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * junit用到
 *
 */
public class PropertiesFileConfig {
    //几个配置文件的相对地址，
    public static final String[] propertiesConfigPath={
            "/aixuepad_serv/profiles/dev/resources/properties/util.properties"
    };
    public static String getPropertiesConfigPath(int i){
        ServletRequestAttributes srabutes =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =null;
        if(srabutes==null){//
            request=new MockHttpServletRequest();
        }else
            request=((ServletRequestAttributes)srabutes).getRequest();
        File f=new File(request.getRealPath("/")+""+propertiesConfigPath[i]);
        if(f.exists())
            return f.getPath();
        return request.getRealPath("/")+"/../"+propertiesConfigPath[i];
    }
}
