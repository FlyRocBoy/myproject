package com.gaopeng.project.GPWXUtil;


import com.gaopeng.project.http.MyHttpsClient;
import com.gaopeng.project.model.ScopeType;
import com.gaopeng.project.model.WXResultBean.AccessToken;
import com.gaopeng.project.model.WXResultBean.OAuth2AccessTokenReturnBean;
import net.sf.json.JSONObject;

import java.util.Date;

/**
 * Created by gaopeng on 2017/7/18.
 */
public class WXOAuthUtil {
    private static String OAUTH2_AUTHORIZE_URL="OAUTH2_AUTHORIZE_URL";
    private static String OAUTH2_ACCESS_TOKEN_URL="OAUTH2_ACCESS_TOKEN_URL";
    private static String MP_ACCESSTOKEN_URL="MP_ACCESSTOKEN_URL";
    private static String APP_ID="APP_ID";
    private static String APP_SECRET="APP_SECRET";
    static {
        OAUTH2_AUTHORIZE_URL=WXHelper.getValue("OAUTH2_AUTHORIZE_URL");
        OAUTH2_ACCESS_TOKEN_URL=WXHelper.getValue("OAUTH2_ACCESS_TOKEN_URL");
        APP_ID=WXHelper.getValue("APP_ID");
        APP_SECRET=WXHelper.getValue("APP_SECRET");
    }
/**
 *
 * 输入参数:
 * @Author gaopeng
 * @Date 2017/7/18 17:05
 * @return
 */
    public static String getRedirectToAuthorizeUrl(ScopeType scopeType,String url,String state){
        String redirectUrl=WXOAuthUtil.replaceAppId(OAUTH2_AUTHORIZE_URL).replace("", scopeType.toString()).replace("",url).replace("",state);
        return redirectUrl;
    }
/**
 * code和state从微信跳转后的url中获取
 * 输入参数:code
 * @Author gaopeng
 * @Date 2017/7/18 17:05
 * @return
 */
    public static OAuth2AccessTokenReturnBean getAccess_token(String code){
        String url=WXOAuthUtil.replaceAppIdAndSecret(OAUTH2_ACCESS_TOKEN_URL).replace("code", code);
        MyHttpsClient myHttpsClient=new MyHttpsClient(url);
        JSONObject jsonObject=JSONObject.fromObject(myHttpsClient.get());
        OAuth2AccessTokenReturnBean oAuth2AccessTokenReturnBean=(OAuth2AccessTokenReturnBean)JSONObject.toBean(jsonObject,OAuth2AccessTokenReturnBean.class);
        return oAuth2AccessTokenReturnBean;
    }
    public static AccessToken getAccess_token(){
       /* String url=WXOAuthUtil.replaceAppIdAndSecret(MP_ACCESSTOKEN_URL);
        MyHttpsClient myHttpsClient=new MyHttpsClient(url);
        JSONObject jsonObject=JSONObject.fromObject(myHttpsClient.get());
        AccessToken accessToken=(AccessToken)JSONObject.toBean(jsonObject,AccessToken.class);*/
        AccessToken accessToken=new AccessToken();
        accessToken.setAccess_token((new Date().toString()));
        accessToken.setExpires_in("7200");
        return accessToken;
    }
    private static String replaceAppIdAndSecret(String url){
        return url.replace("APPID",APP_ID).replace("APPSECRET",APP_SECRET);
    }
    private static String replaceAppId(String url){
        return url.replace("APPID",APP_ID);
    }

}
