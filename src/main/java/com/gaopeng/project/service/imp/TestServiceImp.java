package com.gaopeng.project.service.imp;

import com.gaopeng.project.service.iface.TestService;
import com.gaopeng.project.util.ReturnEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by gaopeng on 2017/7/3.
 */
@Service
public class TestServiceImp implements TestService {
    private Logger logger = LogManager.getLogger(getClass());
    public ReturnEntity test(Map<String,Object> paramMap){
        logger.info("this is test service");
        return new ReturnEntity();
    }
}
