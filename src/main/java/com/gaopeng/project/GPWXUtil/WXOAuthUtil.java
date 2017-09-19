package com.gaopeng.project.GPWXUtil;


import com.gaopeng.project.http.MyHttpsClient;
import com.gaopeng.project.model.ScopeType;
import com.gaopeng.project.model.WXResultBean.OAuth2AccessTokenReturnBean;
import net.sf.json.JSONObject;

/**
 * Created by gaopeng on 2017/7/18.
 */
public class WXOAuthUtil {
    private static String OAUTH2_AUTHORIZE_URL="OAUTH2_AUTHORIZE_URL";
    private static String OAUTH2_ACCESS_TOKEN_URL="OAUTH2_ACCESS_TOKEN_URL";
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
 *
 * 输入参数:code
 * @Author gaopeng
 * @Date 2017/7/18 17:05
 * @return
 */
    public static OAuth2AccessTokenReturnBean getAccess_token(String code){
        String url=WXOAuthUtil.replaceAppIdAndSecret(OAUTH2_ACCESS_TOKEN_URL).replace("code",code);
        MyHttpsClient myHttpsClient=new MyHttpsClient(url);
        JSONObject jsonObject=JSONObject.fromObject(myHttpsClient.get());
        OAuth2AccessTokenReturnBean oAuth2AccessTokenReturnBean=(OAuth2AccessTokenReturnBean)JSONObject.toBean(jsonObject,OAuth2AccessTokenReturnBean.class);
        return oAuth2AccessTokenReturnBean;
    }
    private static String replaceAppIdAndSecret(String url){
        return url.replace("appid",APP_ID).replace("secret",APP_SECRET);
    }
    private static String replaceAppId(String url){
        return url.replace("appid",APP_ID);
    }

}
