/**
 * File: XMPPPushHelper
 * DATE: 2016/12/21
 * Created by kangxuefeng
 */
package com.gaopeng.project.push.xmpp;

/**
 * @desc XMPP推送封装接口类 暂未实现
 *  maven jar
 *  &lt;!--xmpp--&gt;
        <dependency>
        <groupId>org.igniterealtime.smack</groupId>
        <artifactId>smack</artifactId>
        <version>3.3.0</version>
        </dependency>

        <dependency>
        <groupId>org.igniterealtime.smack</groupId>
        <artifactId>smackx</artifactId>
        <version>3.3.0</version>
        </dependency>
 *
 * @date 2016/12/21 11:15
 * @author kangxuefeng@etiantian.com
 *
 */
public class XMPPPushHelper {
   /* *//**
     * 发送单个xmpp消息
     * @param jid
     * @param toJid
     * @param param
     * @param typeId
     * @throws Exception
     *//*
    protected void sendXmppMessage(BigDecimal jid, BigDecimal toJid, Map param, int typeId) throws Exception{

        String xmpp_domain = AiXueConstants.AIXUE_XMPPDOMAIN;
        ConnectionConfiguration config = new ConnectionConfiguration(xmpp_domain, AiXueConstants.AIXUE_XMPPPORT);	//新建连接配置对象，设置服务器IP和监听端口
        config.setSASLAuthenticationEnabled(false);//不使用SASL验证，设置为false
        //config.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
        XMPPConnection connection = new XMPPConnection(config);//得到基于xmpp协议的连接对象

        connection.connect();	//连接服务器

        connection.login(String.valueOf(jid),getTigasePwd(jid.longValue()));//利用用户名和密码登录

        ChatManager cm = connection.getChatManager();//取得聊天管理器

        Chat chat = cm.createChat(toJid.longValue()+"@"+xmpp_domain, new MessageListener() {
            public void processMessage(Chat chat, Message message){
            }
        });
        //xmpp消息体
        Message xmmppMessage = SendMessageUtil.getMessage(typeId, param, toJid, jid, UtilTool.toJSON(param));
        log.info("this is typeId="+typeId+"'s xmppMessage : "+xmmppMessage.toXML());
        chat.sendMessage(xmmppMessage);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        connection.disconnect();//断
    }

    *//**
     * 发送xmpp消息
     * @param jid
     * @param toJids
     * @param param
     * @throws Exception
     *//*
    protected void sendXmppMessage(long jid, List<BigDecimal> toJids, Map param, int typeId) throws Exception{

        *//**
         * 任务提醒相关参数
         *//*
        //任务提醒内容
        String taskHint = "";
        Map<String,String> jspUrlMap = new HashMap<String, String>();
        if(typeId>300&&typeId<400){
            Map taskMap = new HashMap();
            taskMap = (Map)param.get("taskInfo");
            taskHint = taskMap.get("taskHint").toString();
            if(taskMap.containsKey("jspUrlMap")){
                jspUrlMap = (Map<String,String>)taskMap.get("jspUrlMap");
                //map中去掉jspUrlMap
                taskMap.remove("jspUrlMap");
            }
        }
        //其他提醒内容
        String content = param.get("content").toString();

        String pushType = "unicast";//推送类型
        String xmpp_domain = AiXueConstants.AIXUE_XMPPDOMAIN;
        ConnectionConfiguration config = new ConnectionConfiguration(xmpp_domain, AiXueConstants.AIXUE_XMPPPORT);	//新建连接配置对象，设置服务器IP和监听端口
        config.setSASLAuthenticationEnabled(false);//不使用SASL验证，设置为false
        //config.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
        XMPPConnection connection = new XMPPConnection(config);//得到基于xmpp协议的连接对象

        connection.connect();	//连接服务器

        connection.login(String.valueOf(jid),getTigasePwd(jid));//利用用户名和密码登录

        ChatManager cm = connection.getChatManager();//取得聊天管理器

        Map userMap;
        for(BigDecimal tojid : toJids){
            //查询发送人的信息
            Map<String,Object> userParam = new HashMap<String, Object>();
            //参数准备
            userParam.put("jid",tojid);
            userParam.put("friendId",new BigDecimal(jid));
            //查询开始
            this.mapperFacade.getUserInfoMapper().getUserInfo(userParam);
            //判断返回值
            int cnt = userParam.get("cnt")==null ? -10025 : Integer.parseInt(userParam.get("cnt").toString());
            if(cnt<=0)
                continue;

            List<Map> users = (List<Map>)userParam.get("list");
            userMap = new HashMap();
            if(users==null || users.size()<1)
                continue;
            userMap = users.get(0);
            //根据type 判断头像前面的域名
            int userType = Integer.parseInt(userMap.get("userType").toString());
            userMap.put("userPhoto",AiXueConstants.HTTP_ATTACH + UtilTool.getUserPhotoUrlByUserType(userType)+userMap.get("userPhoto"));

            Map toUser = new HashMap();
            toUser.put("jid",userMap.get("userId"));
            toUser.put("name",userMap.get("userName"));
            toUser.put("photo",userMap.get("userPhoto"));
            toUser.put("userType",userMap.get("userType"));
            param.put("user",toUser);
            //消息提醒
            String messageHint = "";
            String jspUrl = "";
            if(typeId>300&&typeId<400){
                Map taskMap = new HashMap();
                taskMap = (Map)param.get("taskInfo");
                messageHint = userMap.get("userName").toString()+taskHint;
                taskMap.put("taskHint",messageHint);
                if(!jspUrlMap.isEmpty()){
                    if(jspUrlMap.containsKey(String.valueOf(tojid))){
                        if(jspUrlMap.get(String.valueOf(tojid))!=null)
                            jspUrl = jspUrlMap.get(String.valueOf(tojid));
                    }
                    //加上jspurl参数
                    taskMap.put("jspUrl",jspUrl);
                }
                param.put("taskInfo",taskMap);
            }

            if(typeId>400){//显示内容content 是从晒晒直接过来的不需要处理
                messageHint = content;
                param.put("content",messageHint);
            }

            Chat chat = cm.createChat(tojid+"@"+xmpp_domain, new MessageListener() {
                public void processMessage(Chat chat, Message message){
                }
            });
            //xmpp消息体
            Message xmmppMessage = SendMessageUtil.getMessage(typeId, param, tojid, new BigDecimal(jid), UtilTool.toJSON(param));
            log.info(xmmppMessage.toXML());
            chat.sendMessage(xmmppMessage);
            //任务有推送
            if(typeId>300&&typeId<400){
                sendPushMessage(typeId, tojid.longValue(),pushType,messageHint,xmmppMessage.toXML());
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        connection.disconnect();//断
    }*/
}
