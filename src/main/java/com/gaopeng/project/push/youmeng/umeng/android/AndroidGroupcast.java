package com.gaopeng.project.push.youmeng.umeng.android;


import com.gaopeng.project.push.youmeng.umeng.AndroidNotification;
import org.json.JSONObject;

/**
 * groupcast-组播
 */
public class AndroidGroupcast extends AndroidNotification {
	public AndroidGroupcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws Exception {
    	setPredefinedKeyValue("filter", filter);
    }
}
