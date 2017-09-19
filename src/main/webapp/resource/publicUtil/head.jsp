<%@ page import="com.etiantian.basicObject.Student" %>
<!--
Created by IntelliJ IDEA.
User: zhengzhou
Date: 15-2-12
Time: 下午8:03
To change this template use File | Settings | File Templates.

公用引用
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/circleTag" prefix="ct"%>



<%@page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Student student=new Student();
    student.setUserID(2310605l);
    student.setCTime("00111744350784");
    session.setAttribute("student", student);
%>
<head>
    <title>北京四中网校－北京四中远程教育网</title>
    <meta name="description" content="中国最大的远程基础教育服务机构和网络学习社区，面向全国的中、小学生提供远程教育辅导。"/>
    <meta name="keywords" content="网校,远程教育,四中"/>
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width; initial-scale=1.0">
    <link rel="stylesheet" href="../css/jzq2015.css" type="text/css" media="screen"/>
    <script src="../js/jquery-2.1.3.min.js" type="text/javascript" media="screen"></script>
    <script type="text/javascript">
                $(function(){
                    $("img").each(function(){
                        var src=$(this).attr("src");
                        if(src.length>0&&src.indexOf("http:")!=0&&src.indexOf("https:")!=0&&src.indexOf("data:")!=0)
                            $(this).attr("src",'../'+$(this).attr("src"))
                    })
                })
    </script>
</head>