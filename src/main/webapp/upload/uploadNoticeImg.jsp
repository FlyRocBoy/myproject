<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="net.sf.json.JSONObject" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>

<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.etiantian.dayuwen.util.UtilTool" %>
<%@ page import="com.etiantian.unite.utils.UrlSignUtil" %>
<%@ page import="com.etiantian.dayuwen.service.imp.UserService" %>
<%@ page import="com.etiantian.dayuwen.service.iface.IUserService" %>
<%@ page import="com.etiantian.dayuwen.util.SpringApplicationContext" %>
<%@ page import="com.etiantian.dayuwen.model.UserInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
%>
<%!
    /**
     * 文件上传请求对象
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/4/25 11:26
     */
    private class MultiPartRequest{
        private HttpServletRequest request;
        private Map<String,String> param;
        private List<FileItem> files;

        public HttpServletRequest getRequest() {
            return request;
        }

        public void setRequest(HttpServletRequest request) {
            this.request = request;
        }

        public Map<String, String> getParam() {
            return param;
        }

        public void setParam(Map<String, String> param) {
            this.param = param;
        }

        public List<FileItem> getFiles() {
            return files;
        }

        public void setFiles(List<FileItem> files) {
            this.files = files;
        }

        public MultiPartRequest() {
        }

        public MultiPartRequest(HttpServletRequest request, Map<String, String> param, List<FileItem> files) {
            this.request = request;
            this.param = param;
            this.files = files;
        }
    }

%>
<%!

    public Logger logger = LogManager.getLogger(this.getClass());

    public static Boolean ValidateRequestParam(HttpServletRequest request) throws Exception {
        Enumeration eObj = request.getParameterNames();
        boolean returnBo = true;
        if (eObj != null) {
            while (eObj.hasMoreElements()) {
                Object obj = eObj.nextElement();
                if (obj == null || obj.toString().trim().length() < 1 || request.getQueryString().toString().equals(obj))
                    continue;

                Object val = request.getParameter(obj.toString());
                if (val == null || val.toString().trim().length() < 1) {
                    returnBo = !returnBo;
                    break;
                }
            }
        }

        return returnBo;
    }

    public static HashMap<String, String> getRequestParam(HttpServletRequest request) throws Exception {
        Enumeration eObj = request.getParameterNames();
        HashMap<String, String> returnMap = null;
        if (eObj != null) {
            returnMap = new HashMap<String, String>();
            while (eObj.hasMoreElements()) {
                Object obj = eObj.nextElement();
                if (obj == null || obj.toString().trim().length() < 1 || obj.toString().trim().equals("m") || obj.toString().equals(request.getQueryString()))
                    continue;
                Object val = request.getParameter(obj.toString());
                returnMap.put(obj.toString(), val.toString());
            }
        }
        return returnMap;
    }

    public void listSort(List<Map<String, Object>> resultList) throws Exception {

        Collections.sort(resultList, new Comparator<Map<String, Object>>() {

            public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                Integer s1 = Integer.parseInt(o1.get("idx").toString());
                Integer s2 = Integer.parseInt(o2.get("idx").toString());

                if (s1 > s2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

    }

    /**
     * 解析文件上传信息
     * @param request
     * @param tempFilePath  临时文件保存位置
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/4/25 11:28
     * @return
     */
    public MultiPartRequest parseMutilpartRequest(HttpServletRequest request,String tempFilePath){
        MultiPartRequest multiPartRequest = null;
        try {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 1024 * 20);
            factory.setRepository(new File(tempFilePath));
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

            Map<String, String> param = new HashMap<String, String>();
            List<FileItem> files = new ArrayList<FileItem>();

            List<FileItem> list = servletFileUpload.parseRequest(request);
            Iterator<FileItem> it = list.iterator();
            while (it.hasNext()) {
                FileItem item = (FileItem) it.next();
                String name = item.getFieldName();
                if (item.isFormField()) {
                    param.put(name, item.getString());
                }else if (name.contains("file")) {
                    files.add(item);
                }
            }

            multiPartRequest = new MultiPartRequest(request, param, files);
        } catch (FileUploadException e) {
            logger.error(e.getMessage(),e);
        }
        return  multiPartRequest;
    }

%>
<%
    String temp = UtilTool.utilproperty.getProperty("RESOURCE_UPLOAD_PATH") + "noticePicTemp/";
    if (!new File(temp).exists())
        new File(temp).mkdirs();
    response.setCharacterEncoding("utf-8");
%>
<%
    JSONObject returnJo = new JSONObject();
    returnJo.put("result", "0");

    MultiPartRequest multiPartRequest = parseMutilpartRequest(request,temp);
    if (!ValidateRequestParam(request) || multiPartRequest == null) {
        JSONObject jo = new JSONObject();
        jo.put("result", "0");
        jo.put("msg", "Param Error!");
        jo.put("data", "");
        response.getWriter().print(jo.toString());
        return;
    }

    List<FileItem> fileItems = multiPartRequest.getFiles();

    HashMap<String, String> paramMap = getRequestParam(request);
    paramMap.putAll(multiPartRequest.getParam());

    String userId = paramMap.get("userId");
    String time = paramMap.get("time");
    String sign = paramMap.get("sign");
    String img = paramMap.get("imgType");

    int imgType = img !=null?Integer.parseInt(img):0;

    if (userId == null || time == null || sign == null || fileItems.size()<1) {
        returnJo.put("msg", (fileItems.size()<1?"not File upload !":"Param Error!"));
        response.getWriter().println(returnJo.toString());
        return;
    }
    paramMap.remove("sign");
    if (!UrlSignUtil.verifySigSimple("uploadNoticeImg.do", paramMap, UtilTool.SECRETKEY, sign)) {
        returnJo.put("msg", "Md5 Error!");
        response.getWriter().print(returnJo.toString());
        return;
    }


    IUserService userService = SpringApplicationContext.getBean(UserService.class);
    // 获取用户
    UserInfo user = userService.getUserInfoByUserId(Long.parseLong(userId));
    if (user == null) {
        returnJo.put("msg", "User not exists!");
        response.getWriter().print(returnJo.toString());
        return;
    }

    SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");

    String dateStr = formater.format(new Date());

    String upload = UtilTool.utilproperty.getProperty("RESOURCE_UPLOAD_PATH") + "noticePic/" + dateStr + "/";



    String procUrl = UtilTool.utilproperty.getProperty("RESOURCE_ACCESS_PATH");

    String fullPicUrl = procUrl + "noticePic/" + dateStr + "/";
    //在这里判断上传类型
    if (imgType == 1) {
        /*路径变换-->心得图片保存路径*/
        upload = UtilTool.utilproperty.getProperty("RESOURCE_UPLOAD_PATH") + "expPic/" + dateStr + "/";

        temp = UtilTool.utilproperty.getProperty("RESOURCE_UPLOAD_PATH") + "expPicTemp/";

        procUrl = UtilTool.utilproperty.getProperty("RESOURCE_ACCESS_PATH");

        fullPicUrl = procUrl + "expPic/" + dateStr + "/";
    }

    if (!new File(upload).exists())
        new File(upload).mkdirs();

    List<Map<String, Object>> fileNameList = new ArrayList<Map<String, Object>>();
    for(Iterator it =fileItems.iterator();it.hasNext();) {
        FileItem item= (FileItem) it.next();
        try {
            String suffix = item.getName().substring(item.getName().lastIndexOf(".")).toLowerCase();
            if (suffix != null) {
                String dirName = System.currentTimeMillis() + UtilTool.getFixLenthString(8);
                suffix = ".jpg";
                String currentName = dirName + suffix;// +"_1"
                String descName = upload + "/" + currentName;
                String shunxu = "";

                File saveFile = new File(descName);
                try {
                    item.write(saveFile);
                    if (item.getFieldName() != null && item.getFieldName().contains("file"))
                        shunxu = item.getFieldName().replace("file", "");

                } catch (Exception e) {
                    logger.error("uploadNoticeImg or uploadExpImg draw:" + e.getMessage());
                } finally {
                    Map m = new HashMap();
                    m.put("idx", shunxu);
                    m.put("src", fullPicUrl + dirName + suffix);
                    fileNameList.add(m);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    if (fileNameList.size() > 1)
        listSort(fileNameList);

    List<String> fList = new ArrayList<String>();

    for (Map m : fileNameList) {
        if (m.containsKey("src")) {
            fList.add(m.get("src").toString());
        }
    }

    if (fList.size() > 0) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("imgUrl", fList.toArray());
        returnJo.put("result", "1");
        returnJo.put("msg", "success!");
        returnJo.put("data", resultMap);
    }
    out.print(returnJo.toString());

%>
