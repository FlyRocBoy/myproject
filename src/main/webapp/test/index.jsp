<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String proName=request.getContextPath();
%>
<html>
<head>
    <title>大语文接口测试首页</title>
    <style>
        .width30{
            width: 30%;
            display: inline-block;
            margin: 0 10px;
            vertical-align: middle;
        }
        .block{
            display: block;
        }
    </style>
</head>

<body>
<div id="dv_xuanxiang" style="padding-top: 50px">
    <div  align="center">

        <br>
        <a href="login.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT1-V1.0 G1登录</a><br>
        <a href="modifyPwd.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT2-V1.0 G1.2.1 修改密码</a><br>
        <a href="getSchoolList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT3-V1.0 G2 获取分校列表</a><br>
        <a href="changeSchoolStatus.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT4-V1.0 G2 开启/关闭分校</a><br>
        <a href="getProvinceCityList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT5-V1.0 获取省市列表</a><br>
        <a href="getNewAccount.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT6-V1.0 获取新账号</a><br>
        <a href="addSchoolInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT7-V1.0 G2.1 创建分校</a><br>
        <a href="getSchoolInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT8-V1.0  G2.2获取分校信息</a><br>
        <a href="modifySchoolInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT9-V1.0 G2.2 更新分校信息</a><br>
        <a href="getTeacherList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT10-V1.0 G3获取教师列表</a><br>
        <a href="getSchoolAndSubList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT11-V1.0 获取分校校区列表</a><br>
        <a href="addTeacher.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT12-V1.0 G3.1 添加教师</a><br>
        <a href="getTeacherInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT13-V1.0 G3.2 获取教师信息</a><br>
        <a href="modifyTeacherInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT14-V1.0 G3.2 修改教师信息</a><br>
        <a href="getCourseList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT15-V1.0 G4 获取课程列表</a><br>
        <a href="getApplyPhaseIdNameList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT16-V1.0 获得适应性阶段列表</a><br>
        <a href="addCourseInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT17-V1.0 G4.1 创建课程</a><br>
        <a href="addResFile.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT18-V1.0 上传资源</a><br>
        <a href="delRes.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT19-V1.0 删除资源</a><br>
        <a href="getCourseInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT20-V1.0 G4.2 获取课程信息</a><br>
        <a href="modifyCourseInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT21-V1.0 G4.2 更新课程信息</a><br>
        <a href="getCourselistList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT22-V1.0 G5 获取"课程列表"列表</a><br>
        <a href="getCourseListBySubjectPhase.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT23-V1.0 G5.1 获取所属科目及适应阶段下的所有课程</a><br>
        <a href="addCourselistInfo.jsp" target="_blank">AYUWEN-APPtoCDS-DYWHT24-V1.0 G5.1 创建"课程列表"</a><br>
        <a href="getCourselistInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT25-V1.0 G5.2 获取"课程列表"信息</a><br>
        <a href="modifyCourselistInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT26-V1.0 G5.2 更新"课程列表"</a><br>
        <a href="getPlessonList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT27-V1.0 G6 获取备课列表</a><br>
        <a href="getAllCourseIdNameList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT28-V1.0 G6.1 获取所有课程列表</a><br>
        <a href="addPlessonInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT29-V1.0 G6.1  添加备课</a><br>
        <a href="getPlessonInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT30-V1.0 G6.2 获取备课信息</a><br>
        <a href="modifyPlessonInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT31-V1.0 G6.2  更新备课信息</a><br>
        <a href="getClassList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT32-V1.0  G8 班级管理-获取班级列表</a><br>
        <a href="changeClassStatus.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT33-V1.0  G8 班级管理-开启/关闭班级</a><br>
        <a href="getCourselistIdNameCourseList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT34-V1.0  G8.1 班级管理-获取"课程列表"Id名称课列表</a><br>
        <a href="getTeacherIdNameList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT35-V1.0  G8.1 班级管理- 查询教师Id名称列表</a><br>
        <a href="getClassroomIdNameList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT36-V1.0  G8.1 班级管理- 查询教室Id名称列表</a><br>
        <a href="addClassInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT37-V1.0 G8.1 创建班级</a><br>
        <a href="getClassInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT38-V1.0 G8.2 获取班级信息</a><br>
        <a href="modifyClassInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT39-V1.0  G8.2 更新班级信息</a><br>
        <a href="getClassmatelist.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT40-V1.0 G8.3 获取班级成员列表</a><br>
        <a href="getClassModifyDetail.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT41-V1.0  G8.4 获取班级变更明细</a><br>
        <a href="getPhaseList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT59-V1.0 G12.3适用阶段-获取适用阶段列表</a><br>
        <a href="addPhaseInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT60-V1.0  G12.3.1适用阶段-创建适用阶段</a><br>
        <a href="getPhaseInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT61-V1.0 G12.3.1适用阶段-获取适用阶段信息</a><br>
        <a href="modifyPhaseInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT62-V1.0  G12.3.1适用阶段-修改适用阶段</a><br>
        <a href="getClassStudentCountList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT63-V1.0 G12.5 班级人数管理-获取班级人数列表</a><br>
        <a href="modifyClassMaxCapacity.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT64-V1.0 G12.5 班级人数管理-修改班级最大人数</a><br>
        <a href="getStudentScoreList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT65-V1.0  G12.7 积分管理-获取学生积分列表</a><br>
        <a href="modifyStudentScore.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT66-V1.0 G12.7 积分管理-修改学生积分</a><br>
        <a href="getClassroomList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT67-V1.0  G12.4 教室管理-获取教室列表</a><br>
        <a href="addClassroom.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT68-V1.0 G12.4.1 教室管理-创建教室</a><br>
        <a href="getClassroomInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT69-V1.0 G12.4.1 教室管理-获取教室信息</a><br>
        <a href="modifyClassroom.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT70-V1.0 G12.4.1 教室管理-修改教室信息</a><br>
        <a href="getAdminAndTeacherList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT56-V1.0 G12.1  账号管理-获取账户(含管理员和教师)列表</a><br>
        <a href="resetPwd.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT49-V1.0 重置密码</a><br>
        <a href="getRoleList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT57-V1.0 12.1.1账号管理-获取角色列表</a><br>
        <a href="addAdminInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT58-V1.0 G12.1.1账号管理-创建管理员</a><br>
        <a href="modifyAdminStatus.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT78-V1.0 G12.1.1账号管理-修改管理员状态</a><br>
        <a href="getSubSchoolList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT71-V1.0  G13 校区管理-获取校区列表</a><br>
        <a href="addSubSchoolInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT72-V1.0  G13 校区管理-创建校区</a><br>
        <a href="getSubSchoolInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT73-V1.0 G13 校区管理-获取校区信息</a><br>
        <a href="modifySubSchoolInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT74-V1.0  G13 校区管理-修改校区信息</a><br>
        <a href="addEnrollInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT44-V1.0 创建招生信息</a><br>
        <a href="getClassCourselistList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT42-V1.0  G9 招生管理- 通过分校Id获取班级课程列表信息</a><br>
        <a href="getClassCourseList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT43-V1.0 G9.1 招生管理-获取课程表</a><br>
        <a href="getStudentChangeDetail.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT47-V1.0 G10.1.1获取学生信息变更明细</a><br>
        <a href="getStudentList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT45-V1.0 G10 学生管理-获取学生列表</a><br>
        <a href="getStudentInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT46-V1.0 G10.1获取学生信息</a><br>
        <a href="modifyStudentInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT48-V1.0 G10.2修改学生信息</a><br>
        <a href="addRenewEnrollInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT76-V1.0 创建学生续签信息</a><br>
        <a href="checkVersion.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT75-V1.0  获取最新版本信息</a><br>
        <a href="checkHasClass.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT79-V1.0 G9招生管理-获取当前用户所在分校下是否存在班级</a><br>
        <a href="saveTokens.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT80-V1.0 保存设备token信息(用于推送)</a><br>

        <hr>
        <a href="notice/addNotice.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT55-V1.0 G11.2 通知中心-创建通知</a><br>
        <a href="notice/addImg.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT54-V1.0 图片上传</a><br>
        <a href="notice/getAllUserByType.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT77-V1.0 G11.1 通知中心-获取通过所有管理员/教师/学生</a><br>
        <a href="notice/getNoticeList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT51-V1.0 G11 通知中心-获取通知列表</a><br>
        <a href="notice/getNoticeDetail.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT53-V1.0 G11.2 通知中心-获取通知详情</a><br>
        <a href="notice/getUnreadCount.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT81-V1.0 获取用户未读通知条数</a><br>
        <br>
        <hr>
        <a href="setCurrPhase.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT85-V1.1 G12.3 设置/取消当期适用阶段</a><br>
        <a href="getExpList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT84-V1.1 G14 获取心得提交列表</a><br>
        <a href="getPaperInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT82-V1.1 G14.1 批阅心得-获取心得详情</a><br>
        <a href="approvePaper.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT83-V1.1 G14.1 批阅心得-批阅</a><br>
        <br>
        <hr class="width30">教师端 api<hr class="width30"><br>
        <a href="teacher/getTipStatus.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT2-V1.0 T1 获取提醒状态</a><br>
        <a href="teacher/setTipStatus.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT3-V1.0 T1 设置提醒状态</a><br>
        <a href="teacher/getStudentInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT12-V1.0 T2.3 获取个人信息</a><br>
        <a href="teacher/getPlessonHistoryList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT14-V1.0 T3.1 获取历史备课列表</a><br>
        <a href="teacher/getPlessonList.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT13-V1.0 T3 获取我的备课列表</a><br>
        <a href="teacher/getPlessonInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT15-V1.0 T3.2 获取备课详情</a><br>
        <a href="teacher/setPlessonTime.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT16-V1.0 T3.2 设置备课时间</a><br>

        <a href="teacher/getTrainHomeInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT1-V1.0 T1获取培训首页信息</a><br>
        <a href="teacher/submitExp.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT4-V1.0 T1.1 提交心得 </a><br>
        <a href="teacher/getTrainInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT5-V1.0 T1.1 获取培训详情  </a><br>
        <a href="teacher/getAtClassHomeInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT6-V1.0 T2 获取上课首页信息 </a><br>
        <a href="teacher/setAtClassStatus.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT7-V1.0 T2.1 设置课堂状态</a><br>
        <a href="teacher/getAtClassInfo.jsp" target="_blank"> DAYUWEN-APPtoCDS-DYWT8-V1.0 T2.1 获取课堂信息</a><br>
        <a href="teacher/checkIn.jsp" target="_blank"> DAYUWEN-APPtoCDS-DYWT9-V1.0 T2.1  批量/单个签到/取消签到 </a><br>
        <a href="teacher/reward.jsp" target="_blank"> DAYUWEN-APPtoCDS-DYWT10-V1.0 T2.1  批量/单个发印章  </a><br>
        <a href="teacher/getClassCourseStudentInfo.jsp" target="_blank"> DAYUWEN-APPtoCDS-DYWT11-V1.0 T2.2 获取班级课程信息  </a><br>
        <a href="teacher/getTeacherInfo.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT17-V1.0 T5 获取教师个人信息</a><br>
        <a href="teacher/modifyTeacherHeadImg.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWT18-V1.0 T5 修改教师头像</a><br>
        <a href="notice/addImg.jsp" target="_blank">DAYUWEN-APPtoCDS-DYWHT54-V1.0 图片上传</a><br>
        <br>
    </div>
</div>

</body>
</html>
