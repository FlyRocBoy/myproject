<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DAYUWEN-APPtoCDS-DYWHT26-V1.0 G5.2 更新"课程列表"</title>
    <script type="text/javascript" src="../resource/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var courselistId=$("[name=courselistId]").val();
            var courselistName=$("[name=courselistName]").val();
            var subject=$("[name=subject]").val();
            var phaseId=$("[name=phaseId]").val();
            var courseIds=$("[name=courseIds]").val();
            var schoolIds=$("[name=schoolIds]").val();
            var time=$("#txt_s_time").val();
            var param={userId:userId,courselistId:courselistId,courselistName:courselistName,subject:subject,
                phaseId:phaseId,courseIds:courseIds,
                schoolIds:schoolIds,time:time};
            param.method=methodName;
            $.ajax({
                url:'_manager/GenderCourseRecordSign.jsp',
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
    </script>
</head>
<body>
<div id="dv_xuanxiang">
    <div id="add" align="center">
        <form action="../clm/modifyCourselistInfo.do" target="ifm_result" method="post">
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
                    <td style="width:150px">courselistId:</td>
                    <td><input type="text" name="courselistId" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">courselistName:</td>
                    <td><input type="text" name="courselistName" value="修改测试课程列表1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">subject:</td>
                    <td>
                        <select name="subject">
                            <option value="1">1</option>
                        </select>
                    </td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">phaseId:</td>
                    <td><input type="text" name="phaseId" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">courseIds:</td>
                    <td><input type="text" name="courseIds" value="1,2"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">schoolIds:</td>
                    <td><input type="text" name="schoolIds" value="1,2"/></td>
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
                    <td><input type="button" value="生成" onclick="genderSign('modifyCourselistInfo.do')"/>
                    </td>
                </tr>
                <tr>
                <tr>
                    <td colspan="3"  align="center"><input type="submit" value="提交--测试"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>


<div id="dv_result" align="center">
    <br/><br/><br/><br/>

    <h3>结果：</h3>
    <iframe id="ifm_result" name="ifm_result" width="650px;height:300px;"></iframe>
</div>

</body>
</html>
