package com.gaopeng.project.push.youmeng.umeng.android;


import com.gaopeng.project.push.youmeng.umeng.AndroidNotification;

/**
 * listcast-列播(要求不超过500个device_token)
 */
public class AndroidListcast extends AndroidNotification {
	public AndroidListcast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "listcast");
	}
	/**
	 * 设置token
	 * @param tokens listcast-列播(要求不超过500个device_token), 多个device_token用英文逗号分隔
	 * @Author kangxuefeng@etiantian.com
	 * @Date 2016/12/26 12:06
	 * @return 
	 */
	public void setDeviceToken(String tokens) throws Exception {
    	setPredefinedKeyValue("device_tokens", tokens);
    }

}