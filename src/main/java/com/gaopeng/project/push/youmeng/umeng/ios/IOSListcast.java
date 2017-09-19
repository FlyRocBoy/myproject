package com.gaopeng.project.push.youmeng.umeng.ios;


import com.gaopeng.project.push.youmeng.umeng.IOSNotification;

/**
 * listcast-列播(要求不超过500个device_token)
 */
public class IOSListcast extends IOSNotification {
	public IOSListcast(String appkey, String appMasterSecret) throws Exception {
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