package com.gaopeng.project.controller;

import com.gaopeng.project.service.iface.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by gaopeng on 2017/7/3.
 */
@Controller
public class Test {
    @Autowired
    TestService testService;
    @RequestMapping("test.do")
    public void test(HttpServletRequest request){
      testService.test(new HashMap<String, Object>());
    }
}
