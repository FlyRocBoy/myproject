package com.gaopeng.project.controller;

import com.gaopeng.project.GPWXUtil.WXOAuthUtil;
import com.gaopeng.project.model.WXResultBean.AccessToken;
import com.gaopeng.project.util.ReturnEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gaopeng on 2017/9/19.
 */
@Controller
@RequestMapping("test/")
public class AccessTokenController {
    //单例Controller，只有一个写操作的情况下用volatile即可保证线程安全
    private static volatile String accessToken = "ACCESSTOKEN";
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(getClass());
    static {
        class refreshAccessToken implements Runnable {
            @Override
            public void run() {
            Timer timer=new Timer();
                TimerTask timerTask=new TimerTask() {
                    @Override
                    public void run() {
                        //写操作
                        AccessToken accessTokenReturnBean=WXOAuthUtil.getAccess_token();
                        accessToken=accessTokenReturnBean.getAccess_token();
                        System.out.println("change:"+accessToken);
                    }
                };

                timer.schedule(timerTask,0,30*10*1000);
            }
        }
        Thread thread = new Thread(new refreshAccessToken());
        thread.start();


    }

    @RequestMapping("getAccessToken.do")
    public ReturnEntity getAccessToken(HttpServletRequest request) {
        return new ReturnEntity(1, accessToken);
    }
}



