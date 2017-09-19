package com.gaopeng.project.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PrivilegeAspect {
    private Logger logger = LogManager.getLogger(getClass());

    public Object doAround(ProceedingJoinPoint pjoinPoint, Map<String, Object> paramMap) throws Throwable {
        logger.info("\n===PrivilegeAspect doBefore:" +
                //"\n===joinPoint="+pjoinPoint+
                "\n===paramMap=" + paramMap);
        String methodName = pjoinPoint.getSignature().getName();
        logger.info("this is around function--- before service");
        Object ob = pjoinPoint.proceed(new Object[]{paramMap});
        logger.info("this is around function--- after service");
        return ob;
    }

    public void before(ProceedingJoinPoint pjoinPoint, Map<String, Object> paramMap) {
        logger.info("this is before function");
    }

    public void after(ProceedingJoinPoint pjoinPoint, Map<String, Object> paramMap) {
        logger.info("this is after function");
    }
}
