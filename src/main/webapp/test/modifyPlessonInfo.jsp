<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DAYUWEN-APPtoCDS-DYWHT31-V1.0 G6.2  更新备课信息</title>
    <script type="text/javascript" src="../resource/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var plessonId=$("[name=plessonId]").val();
            var courseId=$("[name=courseId]").val();
            var plessonDuration=$("[name=plessonDuration]").val();
            var plessonCredits=$("[name=plessonCredits]").val();
            var plessonDesc=$("[name=plessonDesc]").val();
            var resIds=$("[name=resIds]").val();
            var liveList=$("[name=liveList]").val();
            var time=$("#txt_s_time").val();
            var param={userId:userId,plessonId:plessonId,courseId:courseId,plessonDuration:plessonDuration,
                plessonCredits:plessonCredits,plessonDesc:plessonDesc,
                resIds:resIds,liveList:liveList,time:time};
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
        <form action="../plm/modifyPlessonInfo.do" target="ifm_result" method="post">
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
                    <td style="width:150px">plessonId:</td>
                    <td><input type="text" name="plessonId" value="1"/></td>
                    <td style="width:250px"></td>
                </tr> <tr>
                    <td style="width:150px">courseId:</td>
                    <td><input type="text" name="courseId" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">plessonDuration:</td>
                    <td><input type="text" name="plessonDuration" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">plessonCredits:</td>
                    <td><input type="text" name="plessonCredits" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">plessonDesc:</td>
                    <td><input type="text" name="plessonDesc" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">liveList:</td>
                    <td><input type="text" name="liveList" value='[{"liveId":1,"liveName":"直播名称","liveStartTime":"2017-01-01 11:11","liveEndTime":"2017-06-01 11:11"},{"liveId":2,"liveName":"直播名称2update","liveStartTime":"2017-04-02 11:11","liveEndTime":"2017-05-03 11:11"}]'/></td>
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
                    <td><input type="button" value="生成" onclick="genderSign('modifyPlessonInfo.do')"/>
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
