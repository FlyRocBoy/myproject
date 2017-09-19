<%@ page import="com.etiantian.basicObject.Resource" %>
<%@ page import="com.etiantian.dayuwen.util.ETTUtil" %>
<%--
  User: kangxuefeng
  Date: 2016/11/11 18:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private Long resId = new Long(536312L);
    private String resPath = "ysgzyw000047/";

    /**
     * 获取资源测试
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/4/10 14:33
     * @return
     */
    public void getResourceTest(){
        ETTUtil ettUtil = new ETTUtil();
        Resource res =  ettUtil.fetchEttResourceInfo(resId);
        String path = "";
        if(res!= null)
            path = res.getPath();
        System.out.println(path);
    }

    /**
     * 获取资源访问信息
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/4/10 14:39
     * @return
     */
    public void getResourceAccessInfoTest(){
        ETTUtil ettUtil = new ETTUtil();
        System.out.println(ettUtil.generateEttVideoResourceAccessInfo(resId,resPath));
    }

%>
<%
    // run
//    getResourceTest();
    getResourceAccessInfoTest();
    out.print("OK");
%>