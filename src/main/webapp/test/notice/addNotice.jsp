<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DAYUWEN-APPtoCDS-DYWHT55-V1.0 G11.2 通知中心-创建通知</title>
    <script type="text/javascript" src="../../resource/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
        //生成加密串
        function genderSign(methodName){
            var userId=$("[name=userId]").val();
            var receiver=$("[name=receiver]").val();
            var noticeTitle=$("[name=noticeTitle]").val();
            var noticeImg=$("[name=noticeImg]").val();
            var noticeContent=$("[name=noticeContent]").val();
            var time=$("#txt_s_time").val();
            var param={userId:userId,receiver:receiver,noticeTitle:noticeTitle,noticeContent:noticeContent,time:time};
            if(String(noticeImg).length>0)
                param.noticeImg=noticeImg;
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
        <form action="../../notice/addNoticeInfo.do" target="ifm_result" method="post">
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
                <tr id="tr_tea">
                    <td></td>
                    <td>toAllTeacher:<input type="text" name="toAllTeacher" id="toAllTeacher" value="0"/><br/>
                        toTeacherSchoolIds:<input type="text" name="toTeacherSchoolIds" id="toTeacherSchoolIds" value="50044"/><br/>
                        toTeacherIds:<input type="text" name="toTeacherIds" id="toTeacherIds" value=""/><br/>


                        toAllStudent:<input type="text" name="toAllStudent" id="toAllStudent" value="0"/><br/>
                        toStudentSchoolIds:<input type="text" name="toStudentSchoolIds" id="toStudentSchoolIds" value="50044"/><br/>
                        toStudentIds:<input type="text" name="toStudentIds" id="toStudentIds" value=""/><br/>


                        toAllAdmin:<input type="text" name="toAllAdmin" id="toAllAdmin" value="0"/><br/>
                        toAdminSchoolIds:<input type="text" name="toAdminSchoolIds" id="toAdminSchoolIds" value="50044"/><br/>
                        toAdminIds:<input type="text" name="toAdminIds" id="toAdminIds" value=""/><br/>


                        <input type="button" value="生成message" onclick="genderMessageByType(this)"/>
                    </td>
                </tr>

                <tr id="tr_message">
                    <td>message:</td>
                    <td>
                        <textarea id="txt_message" readonly name="receiver">
                        </textarea>
                    </td>
                </tr>

                <tr>
                    <td style="width:150px">noticeTitle:</td>
                    <td><input type="text" name="noticeTitle" value="noticeTitle"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">noticeImg:</td>
                    <td><input type="text" name="noticeImg" value="noticeImg"/></td>
                    <td style="width:250px"></td>
                </tr>
                <tr>
                    <td style="width:150px">noticeContent:</td>
                    <td><input type="text" name="noticeContent" value="noticeContent"/></td>
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
                    <td><input type="button" value="生成" onclick="genderSign('addNoticeInfo.do')"/>
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

<script type="text/javascript">
    function genderMessageByType(tObj){
        var obj={};
        $(tObj).parent().find("input[type='text'],input[type='hidden'],input[type='radio']:checked").each(function(idx,itm){
            eval("obj."+$(itm).attr("name")+"='"+ $.trim(itm.value)+"';");
        })
        console.log(obj);
        //$("#txt_message").val("["+$.toJSON(obj)+"]");
        $("#txt_message").val(""+JSON.stringify(obj)+"");


        $(tObj).parent().parent().remove();
    }
</script>
</html>
