package com.gaopeng.project.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
     * Created by liuyw on 2016/5/27.
     */
    public class BaseInterceptor implements HandlerInterceptor {
        Logger logger = LogManager.getLogger(getClass());
        @Override
        public void afterCompletion(HttpServletRequest request,
                                    HttpServletResponse response, Object arg2, Exception arg3)
                throws Exception {
        }


        @Override
        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
            try {
                String method = httpServletRequest.getMethod();
                String uri = httpServletRequest.getRequestURI();
                String url = httpServletRequest.getRequestURL().toString();
                if(httpServletRequest.getQueryString()!= null && httpServletRequest.getQueryString().length()>0)
                    url+=("?")+(httpServletRequest.getQueryString()).toString();
                String ip = httpServletRequest.getRemoteAddr();
                String version = httpServletRequest.getHeader("version");
                /*Map<String,String> paramMap = UtilTool.getRequestParam(httpServletRequest);
                logger.info("REQ_INFO:\n"+
                            "\n===REQ_IP:"+ip+
                            "\n===REQ_METHOD:"+method+
                            "\n===REQ_URI:"+uri+
                            "\n===REQ_URL:"+url+
                            "\n===REQ_HEADER_VERSION:"+version+
                            "\n===PARAM:"+ new JSONObject(paramMap).toString());*/
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        }
}
