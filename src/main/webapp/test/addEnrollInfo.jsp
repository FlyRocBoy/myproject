<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DAYUWEN-APPtoCDS-DYWHT44-V1.0 创建招生信息</title>
    <script type="text/javascript" src="../resource/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var studentLoginId =$("[name=studentLoginId ]").val();
            var studentName=$("[name=studentName]").val();
            var studentBirth=$("[name=studentBirth]").val();
            var studentSex=$("[name=studentSex]").val();
            var curSchoolName=$("[name=curSchoolName]").val();
            var gradeId=$("[name=gradeId]").val();
            var parentName=$("[name=parentName]").val();
            var phoneNum=$("[name=phoneNum]").val();
            var address=$("[name=address]").val();
            var payAmount=$("[name=payAmount]").val();
            var schoolId=$("[name=schoolId]").val();
            var classIds=$("[name=classIds]").val();
            var ignorePhoneNum=$("[name=ignorePhoneNum]").val();
            var time=$("#txt_s_time").val();
            var param={userId:userId,studentLoginId:studentLoginId,studentName:studentName,
                studentBirth:studentBirth,studentSex:studentSex,
                curSchoolName:curSchoolName,gradeId:gradeId,parentName:parentName,phoneNum:phoneNum,
                address:address,payAmount:payAmount,schoolId:schoolId,classIds:classIds,ignorePhoneNum:ignorePhoneNum,
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
        <form action="../em/addEnrollInfo.do" target="ifm_result" method="post">
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
                    <td style="width:150px">studentLoginId :</td>
                    <td><input type="text" name="studentLoginId" value="s1@010FX10"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">studentName:</td>
                    <td><input type="text" name="studentName" value="stu1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">studentBirth:</td>
                    <td><input type="text" name="studentBirth" value="2016-01-01"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">studentSex:</td>
                    <td>
                        <select name="studentSex">
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
                    <td style="width:150px">curSchoolName:</td>
                    <td><input type="text" name="curSchoolName" value="xxx学校"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">address:</td>
                    <td><input type="text" name="address" value="asd"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">payAmount:</td>
                    <td><input type="text" name="payAmount" value="1.11"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">schoolId:</td>
                    <td><input type="text" name="schoolId" value="13"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">classIds:</td>
                    <td><input type="text" name="classIds" value="50:2016002001"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">ignorePhoneNum:</td>
                    <td><input type="text" name="ignorePhoneNum" value="0"/></td>
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
                    <td><input type="button" value="生成" onclick="genderSign('addEnrollInfo.do')"/>
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
