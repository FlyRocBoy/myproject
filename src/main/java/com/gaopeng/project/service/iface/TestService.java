package com.gaopeng.project.service.iface;

import com.gaopeng.project.util.ReturnEntity;

import java.util.Map;

/**
 * Created by gaopeng on 2017/7/3.
 */
public interface TestService {
    public ReturnEntity test(Map<String,Object> paramMap);
}
