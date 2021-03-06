<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DAYUWEN-APPtoCDS-DYWHT14-V1.0 G3.2 修改教师信息</title>
    <script type="text/javascript" src="../resource/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var teacherUserId=$("[name=teacherUserId]").val();
            var realName=$("[name=realName]").val();
            var phoneNum=$("[name=phoneNum]").val();
            var idNum=$("[name=idNum]").val();
            var teacherCERTNum=$("[name=teacherCERTNum]").val();
            var workCertSignDate=$("[name=workCertSignDate]").val();
            var sex=$("[name=sex]").val();
            var teacherStatus=$("[name=teacherStatus]").val();
            var schoolId=$("[name=schoolId]").val();
            var subSchoolId=$("[name=subSchoolId]").val();
            var teacherType=$("[name=teacherType]").val();
            var address=$("[name=address]").val();
            var isLecture=$("[name=isLecture]").val();
            var isBaseSkill=$("[name=isBaseSkill]").val();
            var isBK=$("[name=isBK]").val();
            var workCertNum=$("[name=workCertNum]").val();
            var time=$("#txt_s_time").val();
            var param={userId:userId,teacherUserId:teacherUserId,realName:realName,
                phoneNum:phoneNum,idNum:idNum,
                teacherCERTNum:teacherCERTNum,
                workCertSignDate:workCertSignDate,
                sex:sex,
                teacherStatus:teacherStatus,schoolId:schoolId,
                subSchoolId:subSchoolId,teacherType:teacherType,
                address:address,isLecture:isLecture,isBaseSkill:isBaseSkill,isBK:isBK,workCertNum:workCertNum,
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
        <form action="../tm/modifyTeacherInfo.do" target="ifm_result" method="post">
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
                    <td style="width:150px">teacherUserId:</td>
                    <td><input type="text" name="teacherUserId" value="7"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">realName:</td>
                    <td><input type="text" name="realName" value="教师1"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">phoneNum:</td>
                    <td><input type="text" name="phoneNum" value="13111111111"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">idNum:</td>
                    <td><input type="text" name="idNum" value="111111199011111111"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">teacherCERTNum:</td>
                    <td><input type="text" name="teacherCERTNum" value="11111111111111111"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">sex:</td>
                    <td>
                        <select  name="sex">
                            <option value="1">1</option>
                            <option value="2">2</option>
                        </select>
                    </td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">teacherStatus:</td>
                    <td>
                            <select  name="teacherStatus">
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                            </select>
                    </td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">schoolId:</td>
                    <td><input type="text" name="schoolId" value="2"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">subSchoolId:</td>
                    <td><input type="text" name="subSchoolId" value=""/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">teacherType:</td>
                    <td>
                        <select  name="teacherType">
                            <option value="1">1</option>
                            <option value="2">2</option>
                        </select>
                    </td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">address:</td>
                    <td><input type="text" name="address" value="北京市西城区xxx"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">isLecture:</td>
                    <td>
                        <select  name="isLecture">
                            <option value="0">0</option>
                            <option value="1">1</option>
                        </select>
                    </td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">isBaseSkill:</td>
                    <td>
                        <select  name="isBaseSkill">
                            <option value="0">0</option>
                            <option value="1">1</option>
                        </select>
                    </td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">isBK:</td>
                    <td>
                        <select  name="isBK">
                            <option value="0">0</option>
                            <option value="1">1</option>
                        </select>
                    </td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">workCertNum:</td>
                    <td><input type="text" name="workCertNum" value="000000001"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">workCertSignDate:</td>
                    <td><input type="text" name="workCertSignDate" value=""  ondblclick="this.value = new Date().getTime()"/></td>
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
                    <td><input type="button" value="生成" onclick="genderSign('modifyTeacherInfo.do')"/>
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
