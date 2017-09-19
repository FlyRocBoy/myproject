package com.gaopeng.project.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by gaopeng on 2017/7/3.
 */
public class InitService {
    Logger logger = LogManager.getLogger(getClass());
    //    @PostConstruct
    public void init(){
        logger.info("===initializing ...");
        logger.info("\n");
        logger.info("===initialized!!!");
    }

    /**
     *
     * 输入参数:
     * @Author gaopeng
     * @Date 2017/7/3 10:37
     * @return
     */
    public void destory(){
        try {
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
