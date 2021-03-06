<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DAYUWEN-APPtoCDS-DYWHT74-V1.0  G13 校区管理-修改校区信息</title>
    <script type="text/javascript" src="../resource/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var subSchoolId=$("[name=subSchoolId]").val();
            var subSchoolName=$("[name=subSchoolName]").val();
            var relatePersonName=$("[name=relatePersonName]").val();
            var phoneNum=$("[name=phoneNum]").val();
            var subSchoolPhoneNum=$("[name=subSchoolPhoneNum]").val();
            var address=$("[name=address]").val();
            var provinceId=$("[name=provinceId]").val();
            var cityId=$("[name=cityId]").val();
            var time=$("#txt_s_time").val();
            var param={userId:userId,subSchoolId:subSchoolId,subSchoolName:subSchoolName,relatePersonName:relatePersonName,
                phoneNum:phoneNum,subSchoolPhoneNum:subSchoolPhoneNum,address:address,provinceId:provinceId,
                cityId:cityId,time:time};
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
        <form action="../subsm/modifySubSchoolInfo.do" target="ifm_result" method="post">
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
                    <td style="width:150px">subSchoolId:</td>
                    <td><input type="text" name="subSchoolId" value="5"/></td>
                    <td style="width:250px"></td>
                </tr><tr>
                    <td style="width:150px">subSchoolName:</td>
                    <td><input type="text" name="subSchoolName" value="测试校区1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">relatePersonName:</td>
                    <td><input type="text" name="relatePersonName" value="管理员1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">phoneNum:</td>
                    <td><input type="text" name="phoneNum" value="13111111111"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">subSchoolPhoneNum:</td>
                    <td><input type="text" name="subSchoolPhoneNum" value="13111111111"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">address:</td>
                    <td><input type="text" name="address" value="北京市西城区xxx"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">provinceId:</td>
                    <td><input type="text" name="provinceId" value="1733093"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">cityId:</td>
                    <td><input type="text" name="cityId" value="2"/></td>
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
                    <td><input type="button" value="生成" onclick="genderSign('modifySubSchoolInfo.do')"/>
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
