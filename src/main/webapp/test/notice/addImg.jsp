<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DAYUWEN-APPtoCDS-DYWHT18-V1.0 上传资源</title>
    <script type="text/javascript" src="../../resource/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var imgType=$("[name=imgType]").val();
            var time=$("#txt_s_time").val();
            var param;
            if(imgType.length==0){
                param={userId:userId,time:time};
            }else{
                param={userId:userId,time:time,imgType:imgType};
            }

            param.method=methodName;
            $.ajax({
                url:'../_manager/GenderCourseRecordSign.jsp',
                type:'post',
                dataType:'text',
                data:param,
                success:function(rps){
                    $("#txt_s_sign").val($.trim(rps));
                }
            })
        }
        $(function () {
           $("#interfaceName").text(document.forms[0].action);
            $("#title").text(document.title);
        });

        function formOnsubmit(t){
            var action = $(t).attr("action")+"?";
            var imgType=$("[name=imgType]").val();
            if(imgType.length==0){
                action+="userId="+$("[name=userId]").val()+"&"+"time="+$("#txt_s_time").val()+"&sign="+$("#txt_s_sign").val();
            }else{
                action+="userId="+$("[name=userId]").val()+"&"+"time="+$("#txt_s_time").val()+"&sign="+$("#txt_s_sign").val()+"&imgType="+$("[name=imgType]").val();
            }
            $(t).attr("action",action);
            return true;
        }
        function iframeOnload(){
            $("form").attr("action","../../upload/uploadNoticeImg.jsp");
        }
    </script>
</head>
<body>
<div id="dv_xuanxiang">
    <div id="add" align="center">

            <table style="width:650px;">
                <tr>
                    <td colspan="3" align="center"><h3 id="title"></h3></td>
                </tr>
                <tr>
                <td colspan="3" align="center"><h3 id="interfaceName"></h3></td>
                </tr>
                <tr>
                    <td style="width:150px">userId:</td>
                    <td><input type="text" name="userId" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">imgType(图片类型,“”:通知图片,1:心得图片):</td>
                    <td><input type="text" name="imgType" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td>time:</td>
                    <td><input type="text" name="time" id="txt_s_time" value=""/></td>
                    <td><input type="button" value="当前时间" onclick="txt_s_time.value=new Date().getTime()+'';"/>
                    </td>
                </tr>
                <tr>
                    <td>sign:</td>
                    <td><input type="text" name="sign" id="txt_s_sign" value=""/></td>
                    <td><input type="button" value="生成" onclick="genderSign('uploadNoticeImg.do')"/>
                    </td>
                </tr>
                <form action="../../upload/uploadNoticeImg.jsp" target="ifm_result" method="post" onsubmit="formOnsubmit(this)" enctype="multipart/form-data">
                    <tr>
                        <td style="width:150px">file:</td>
                        <td><input type="file" name="file"/></td>
                        <td style="width:250px"></td>
                    </tr>
                    <tr>
                        <td colspan="3"  align="center"><input type="submit" value="提交--测试"/>
                        </td>
                    </tr>
                </form>
            </table>
    </div>
</div>


<div id="dv_result" align="center">
    <br/><br/><br/><br/>

    <h3>结果：</h3>
    <iframe id="ifm_result" name="ifm_result" onload="iframeOnload()" width="650px;height:300px;"></iframe>
</div>

</body>
</html>
