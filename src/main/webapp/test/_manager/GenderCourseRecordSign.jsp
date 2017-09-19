<%@ page import="com.etiantian.dayuwen.util.UtilTool" %>
<%@ page import="com.etiantian.unite.utils.UrlSignUtil" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    Logger logger = LogManager.getLogger(getClass());
/**
 * 验证RequestParams相关参数
 * @param request
 * @return
 * @throws Exception
 */
public static HashMap<String,String> getRequestParam(HttpServletRequest request) throws Exception{
    Enumeration eObj=request.getParameterNames();
    HashMap<String,String> returnMap=null;
    if(eObj!=null){
        returnMap=new HashMap<String, String>();
        while(eObj.hasMoreElements()){
            Object obj=eObj.nextElement();
            Object val = null;
            if(obj==null||obj.toString().trim().length()<1)
                continue;

            val= request.getParameter(obj.toString());

            if(obj.equals("method")){
                continue;
            }
            String paramVal = val == null ? null :val.toString();
            if(val!=null && "GET".equals(request.getMethod()))
                paramVal =  UtilTool.servletCharset2CodeCharset(paramVal);
            returnMap.put(obj.toString(),paramVal);
        }
    }
    return returnMap;
}
%>

<%
    HashMap<String,String> map=getRequestParam(request);
    String method=request.getParameter("method");
    //验证Md5-
    String val = UrlSignUtil.makeSigSimple(method, map, UtilTool.SECRETKEY);
    logger.info("===GeneralSign:\n"+
                "param:"+ new JSONObject(map).toString()+"\n"+
                "sign:"+val);
    response.getWriter().println(val);
%>