package com.gaopeng.project.push.youmeng.umeng.android;


import com.gaopeng.project.push.youmeng.umeng.AndroidNotification;

/**
 * filecast-文件播
 (多个device_token可通过文件形式批量发送）
 */
public class AndroidFilecast extends AndroidNotification {
	public AndroidFilecast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "filecast");	
	}
	
	public void setFileId(String fileId) throws Exception {
    	setPredefinedKeyValue("file_id", fileId);
    }

}