<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> DAYUWEN-APPtoCDS-DYWT4-V1.0 T1.1 提交心得</title>
    <script type="text/javascript" src="../../resource/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var time=$("#txt_s_time").val();
            var trainId=$("[name=trainId]").val();
            var expImgs=$("[name=expImgs]").val();
            var expContent=$("[name=expContent]").val();
            var param={userId:userId,time:time,trainId:trainId,expImgs:expImgs,expContent:expContent};
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
    </script>
</head>
<body>
<div id="dv_xuanxiang">
    <div id="add" align="center">
        <form action="../../t/submitExp.do" target="ifm_result" method="post">
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
                    <td style="width:150px">trainId:</td>
                    <td><input type="text" name="trainId" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">expImgs:</td>
                    <td><input type="text" name="expImgs" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">expContent:</td>
                    <td><input type="text" name="expContent" value="1"/></td>
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
                    <td><input type="button" value="生成" onclick="genderSign('submitExp.do')"/>
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
