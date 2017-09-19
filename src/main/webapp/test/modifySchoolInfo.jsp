<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DAYUWEN-APPtoCDS-DYWHT9-V1.0 G2.2 更新分校信息</title>
    <script type="text/javascript" src="../resource/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var schoolId=$("[name=schoolId]").val();
            var schoolName=$("[name=schoolName]").val();
            var relatePersonName=$("[name=relatePersonName]").val();
            var phoneNum=$("[name=phoneNum]").val();
            var schoolPhoneNum=$("[name=schoolPhoneNum]").val();
            var address=$("[name=address]").val();
            var schoolType=$("[name=schoolType]").val();
            var provinceId=$("[name=provinceId]").val();
            var cityId=$("[name=cityId]").val();
            var time=$("#txt_s_time").val();
            var param={userId:userId,schoolId:schoolId,schoolName:schoolName,relatePersonName:relatePersonName,
                phoneNum:phoneNum,schoolPhoneNum:schoolPhoneNum,address:address,
                schoolType:schoolType,provinceId:provinceId,
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
        <form action="../sm/modifySchoolInfo.do" target="ifm_result" method="post">
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
                    <td style="width:150px">schoolId:</td>
                    <td><input type="text" name="schoolId" value="5"/></td>
                    <td style="width:250px"></td>
                </tr><tr>
                    <td style="width:150px">schoolName:</td>
                    <td><input type="text" name="schoolName" value="测试分校1"/></td>
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
                    <td style="width:150px">schoolPhoneNum:</td>
                    <td><input type="text" name="schoolPhoneNum" value="13111111111"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">address:</td>
                    <td><input type="text" name="address" value="北京市西城区xxx"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">schoolType:</td>
                    <td>
                        <select  name="schoolType">
                            <option value="1">1</option>
                            <option value="2">2</option>
                        </select>
                    </td>
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
                    <td><input type="button" value="生成" onclick="genderSign('modifySchoolInfo.do')"/>
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
