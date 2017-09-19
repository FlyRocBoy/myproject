<%-- 资源测试jsp --%>
<%@ page import="com.etiantian.basicObject.Resource" %>
<%@ page import="com.etiantian.dayuwen.util.ETTUtil" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.etiantian.action.ResourceAccessInterface" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    public class ETTUrilTest {
        private Long resId = new Long(501775L);//123 548768L
        private String resPath = "";
        ETTUtil ettUtil = new ETTUtil();

        /**
         * 获取资源测试
         * @Author kangxuefeng@etiantian.com
         * @Date 2017/4/10 14:33
         * @return
         */
        public void getResourceTest(){
            Resource res = getResourceInfo();
            String path = "";
            if(res!= null)
                path = res.getPath();
            System.out.println(path);
        }

        private Resource getResourceInfo(){
            return  ettUtil.fetchEttResourceInfo(resId);
        }

        /**
         * 获取资源访问信息
         * @Author kangxuefeng@etiantian.com
         * @Date 2017/4/10 14:39
         * @return
         */
        public ETTUtil.ETTVideoResourceAccessInfo getResourceAccessInfoTest(){
            Resource res = getResourceInfo();
            ETTUtil ettUtil = new ETTUtil();
            ETTUtil.ETTVideoResourceAccessInfo t = ettUtil.generateEttVideoResourceAccessInfo(resId, res.getPath());
            System.out.println(t);
            return t;
        }

        /**
         * 获取非视频资源访问路径
         * @Author kangxuefeng@etiantian.com
         * @Date 2017/5/11 15:15
         * @return
         */
        public ETTUtil.ETTResourceAccessInfo getResourceAccessUrl (){
            Resource res = getResourceInfo();
            return ettUtil.generateEttResourceAccessInfo(res.getPath());
        }

    }
%>
<%
//    out.write(new ETTUrilTest().getResourceAccessUrl().getUrl());
    ETTUtil.ETTVideoResourceAccessInfo a = new ETTUrilTest().getResourceAccessInfoTest();
    out.write(a.getVideoUrl());
    out.write("\r\n");
    out.write(a.getImgUrl());


%>