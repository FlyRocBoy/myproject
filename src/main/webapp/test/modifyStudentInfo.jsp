<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DAYUWEN-APPtoCDS-DYWHT48-V1.0 G10.2修改学生信息</title>
    <script type="text/javascript" src="../resource/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var studentId =$("[name=studentId ]").val();
            var studentName=$("[name=studentName]").val();
            var birthday=$("[name=birthday]").val();
            var sex=$("[name=sex]").val();
            var currSchoolName=$("[name=currSchoolName]").val();
            var gradeId=$("[name=gradeId]").val();
            var parentName=$("[name=parentName]").val();
            var phoneNum=$("[name=phoneNum]").val();
            var address=$("[name=address]").val();
            var classIds=$("[name=classIds]").val();
            var studentStatus=$("[name=studentStatus]").val();
            var ignorePhoneNum=$("[name=ignorePhoneNum]").val();
            var time=$("#txt_s_time").val();
            var param={userId:userId,studentId:studentId,
                birthday:birthday,sex:sex,studentName:studentName,
                currSchoolName:currSchoolName,gradeId:gradeId,parentName:parentName,phoneNum:phoneNum,
                address:address,classIds:classIds,studentStatus:studentStatus,ignorePhoneNum:ignorePhoneNum,
                time:time};
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
        <form action="../stum/modifyStudentInfo.do" target="ifm_result" method="post">
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
                    <td style="width:150px">studentId:</td>
                    <td><input type="text" name="studentId" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">studentName:</td>
                    <td><input type="text" name="studentName" value="stu1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">birthday:</td>
                    <td><input type="text" name="birthday" value="2016-01-01"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">sex:</td>
                    <td>
                        <select name="sex">
                            <option value="1">1</option>
                            <option value="2">2</option>
                        </select>

                    </td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">gradeId:</td>
                    <td><input type="text" name="gradeId" value="1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">parentName:</td>
                    <td><input type="text" name="parentName" value="我是家长"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">phoneNum:</td>
                    <td><input type="text" name="phoneNum" value="13111111111"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">currSchoolName:</td>
                    <td><input type="text" name="currSchoolName" value="xxx学校"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">address:</td>
                    <td><input type="text" name="address" value="asd"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">classIds:</td>
                    <td><input type="text" name="classIds" value="50:201600201"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">studentStatus:</td>
                    <td><select name="studentStatus">
                            <option value="0">0</option>
                            <option value="1">1</option>

                        </select>
                    </td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">ignorePhoneNum:</td>
                    <td><select name="ignorePhoneNum">
                            <option value="0">0</option>
                            <option value="1">1</option>

                        </select>
                    </td>
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
                    <td><input type="button" value="生成" onclick="genderSign('modifyStudentInfo.do')"/>
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
