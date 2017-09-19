/**
 * File: Constants
 * DATE: 2016/11/14
 * Created by kangxuefeng
 */
package com.gaopeng.project.util;


import java.util.HashMap;
import java.util.Map;

/**
 * @desc 常量类
 * @date 2016/11/14 16:44
 * @author kangxuefeng@etiantian.com
 *
 */
public class Constants {
    /**
     * 特殊字符 查询模式
     * ' " = + - * / \ ! % ^ & ( ) ? , . : ; { } |
     */
    public final static String SPECIAL_CHAR_REGULAR =".*(\'|\"|\\=|\\+|-|\\*|\\/|\\|\\!|\\%|\\^|\\&|\\$|\\(|\\)|\\?|\\,|\\.|\\:|;|\\{|\\}|\\|).*";

    /**
     * ppt文档文件类型
     */
    public static String PPT_SUFFIX_TYPE_REGULAR ="(.ppt|.pptx)$";

    /**
     * 项目名
     */
    public final static String PROJECT_NAME = "dayuwen";
    /**
     * default pwd
     */
    public final static String DEFAULT_PWD = "dyw1111";
    /**
     * 分校类型
     * 1:普通分校
     * 2:高级分校
     * 0:总校
     */
    public final static Integer[] SCHOOL_TYPE = {0,1,2};
    /**
     * 分校或校区类型
     * 1:分校
     * 2:校区
     */
    public final static Integer[] SCHOOL_OR_SUBSCHOOL_TYPE = {0,1,2};
    /**
     * 是否为总校
     * 1:是 0:否
     */
    public final static Integer[] IS_HEAD_SCHOOL = {0,1};
    /**
     * 总校Id
     * 1
     */
    public final static Long HEAD_SCHOOL_ID = 1L;
    /**
     * 用户类型
     * 1=教师,2=管理员,3=学生,4=家长
     */
    public final static Integer[] USER_TYPE = {0,1,2,3,4};

    /**
     * 用户有效状态
     * 1:正常,0:停止
     */
    public final static Integer[] USER_STATUS = {0,1};
    /**
     * 通用状态
     * 1:正常,0:停止/关闭
     */
    public final static Integer[] COMMON_STATUS = {0,1};
    /**
     * 老师状态
     *  1:正常，2:岗前，0:停止
     */
    public final static Integer[] TEACHER_STATUS = {0,1,2};
    /**
     * 教师类型
     * 1:兼职 2:全职
     */
    public final static Integer[] TEACHER_TYPE = {0,1,2};
    /**
     * 数据有效状态
     * 1:有效,0:删除
     */
    public final static Integer[] ENABLE_STATUS = {0,1};
    /**
     * 权限类型
     * 1:菜单 2:页面 3:页面元素
     */
    public final static Integer[] PVG_TYPES = {0,1,2,3};
    /**
     * 每页数据条数
     */
    public final static Integer PAGE_SIZE = 20;
    /**
     * -1
     */
    public final static Long ID_NEGATIVE_1 = -1L;
    /**
     * -1
     */
    public final static Integer TYPE_NEGATIVE_1 = -1;
    /**
     * 学生学习状态
     * 1.学习中：课表上的课程还没结束；
     * 2.毕业：报名的课程已结束，保留上过课程的资源、作业。
     * -- 暂无用 3.停止：表示停止同步作业、资源，保留之前上过课程的资源、作业；
     */
    public final static Integer[] STUDENT_STUDY_STATUS = {0,1,2};
    /**
     * 登录id分隔符
     */
    public final static String LOGIN_ID_SPLITER="@";
    /*
     * 管理员登录Id前缀
     */
    public final static String ADMIN_LOGIN_ID_PREFIX="m";
    /**
     * 教师登录Id前缀
     */
    public final static String TEACHER_LOGIN_ID_PREFIX="t";
    /**
     * 学生登录Id前缀
     */
    public static final String STUDENT_LOGIN_ID_PREFIX = "s";
    /**
     * 时间格式
     * yyyy-MM-dd HH:mm
     */
    public final static String SDF_YYYY_MM_DD_HH_MM="yyyy-MM-dd HH:mm";
    /**
     * 时间格式
     * yyyy-MM-dd
     */
    public final static String SDF_YYYY_MM_DD="yyyy-MM-dd";

    /**
     * 性别
     * 1:男,2:女
     */
    public final static Integer[] SEX = {0,1,2};

    /**
     * 分校超级管理员角色Id
     * 拥有其他管理员权限外的重置密码权限
     */
    public final static Long SCHOOL_SUPERADMIN_ROLE = -2L;
    /**
     * 校区超级管理员角色Id
     * 拥有其他管理员权限外的重置密码权限
     */
    public final static Long SUB_SCHOOL_SUPERADMIN_ROLE = -3L;
    /**
     * 投诉与建议用户角色
     *
     */
    public final static Long COMPLAINT_ROLE_ID = 4L;

    /**
     * 是否是超级管理员
     * 1:是,0:否
     */
    public final static Integer[] IS_SUPER  = {0,1};

    /**
     * 课程类型
     * 1:课程2:备课
     */
    public final static Integer[] COURSE_TYPE  = {0,1,2};

    /**
     * 字典类别
     * 科目:SUBJECT
     */
    public final  static String DICTIONARY_TYPE_SUBJECT = "SUBJECT";
    /**
     * 字典类别
     * 科目:DOC(文档)
     */
    public final  static String DICTIONARY_TYPE_DOC = "DOC";
    /**
     * 字典类别
     * 年龄阶段:AGEPHASE
     */
    public final  static String DICTIONARY_TYPE_AGEPHASE = "AGEPHASE";
    /**
     * 字典类别
     * 班级最大容纳数:CLASSMAXCAPACITY
     */
    public final  static String DICTIONARY_TYPE_CLASSMAXCAPACITY = "CLASSMAXCAPACITY";
    /**
     * 字典类别
     * 年级:GRADE
     */
    public final  static String DICTIONARY_TYPE_GRADE = "GRADE";
    /**
     * 班级最大容纳数 99
     */
    public final  static Integer CLASSMAXCAPACITY_NUM = 99;
    /**
     * 修改信息类型
     * 1:班级信息变更
     * 2:学生信息变更
     */
    public final  static Integer[] INFO_UPDATE_TYPE = {0,1,2};
    /**
     * 积分明细来源类型
     * 来源类型，0:管理员修改 1,课程
     */
    public final  static Integer[]  SCORE_DETAIL_SOURCE_TYPE = {0,1,2};
    /**
     * 积分奖励类型
     * 0:管理员修改
     */
    public final  static Integer[]  SCORE_DETAIL_TYPE = {0};
    /**
     * 角色id
     * 总校管理员 1
     * 分校管理员 2
     * 校区管理员 3
     */
    public final  static Long[] ROLE_TYPE = {0L,1L,2L,3L};
    /**
     * 角色适用类型
     * 总校:1
     * 分校:2
     * 校区:3
     */
    public final  static Integer[] ROLE_USE_TYPE = {0,1,2,3};
    /**
     * 总校超级管理员Id
     */
    public final  static Long MAIN_SCHOOL_SUPER_ADMIN_ID = 1L;
    /**
     * 科目 1:大语文
     */
    public static Integer SUBJECT_DAYUWEN = 1;
    /**
     * 校区管理 menuIdentity
     */
    public static String MENU_IDENTITY_CAMPUS = "Campus";
    /**
     * 是否已重新登录(1:是,0:否) 默认为1,
     * 当某用户修改密码后,与该用户相关的所有记录除修改密码的记录外的其他用户的IS_RELOGIN都置为0
     */
    public final  static Integer[] IS_RELOGIN = {0,1};

    /**
     * 通知默认标题
     */
    public static final String DEFAULT_NOTICE_TITLE  = "通知标题";

    /**
     * BaseInterceptor中 不校验的请求配置
     * 含第一个"/"
     */
    public static final String[]  EXCLUDE_INTERCEPTOR_CHECK_URIS  = {"/index.do","/childLogin.do","/getUserPhone.do","/sendSMS.do","/forgetPwdChange.do","/getWXJSSDKSign.do","/getCurrWXOpenId.do"};

    /**
     * 短信发送类型
     * 1:忘记密码
     */
    public final  static Integer[] SMS_SEND_TYPE = {1};

    /**
     * 大语文短信发送模版
     */
    public static final String SMS_MSG_TEMPLATE = "【大语文】您的验证码是_SMSCODE_，请在30分钟内完成操作";
    /**
     * 短信每天发送最大次数
     */
    public static final long SMS_MAX_SEND_TIME_ONEDAY = 5;
    /**
     * 数据缓存类型
     * 1:微信mpAccessToken,2:微信mpJSApiTicket
     */
    public static final Integer[] DATA_CACHE_TYPE = {0,1,2};

    /****************************************以下配置值将从配置文件或数据库中读取***************************************************************/
    /**
     * 需初始化
     * 科目
     * 1:大语文
     */

    /**
     * 需初始化
     * 年龄阶段
     * ageMin:最小年龄
     * ageMax:最大年龄
     */
    public static Map<String,Integer> AGEPHASE = new HashMap<String,Integer>();
    /**
     * 需初始化
     * 年级阶段
     * 1:一年级,2:二年级,3:三年级,
     * 4:四年级,5:五年级,6:六年级
     */
    public static Map<Integer,String> GRADE = new HashMap<Integer,String>();
    /**
     * 需初始化
     * 班级最大容纳学生数
     */
    public static Integer CLASSMAXCAPACITY = 0;

    /**
     * 资源上传路径,临时文件夹名称
     * RESOURCE_UPLOAD_PATH 的下层文件夹
     */
    public static String RESOURCE_UPLOAD_TEMP_PATH = "/temp/";
    /**
     * 资源上传路径,课程资源文件夹名称
     * RESOURCE_UPLOAD_PATH 的下层文件夹
     */
    public static String RESOURCE_UPLOAD_COURSES_PATH = "/courses/";
    /**
     * 资源上传路径,备课资源文件夹名称
     * RESOURCE_UPLOAD_PATH 的下层文件夹
     */
    public static String RESOURCE_UPLOAD_PLESSONS_PATH = "/plessons/";
    /**
     * 资源上传路径,绝对路径
     * 从配置文件中获取
     * 资源保存路径:
     *      RESOURCE_UPLOAD_PATH/temp/时间戳.subfix(临时文件夹)
     * 或
     *      RESOURCE_UPLOAD_PATH/courses/courseId/时间戳.subfix(课程资源)
     *      RESOURCE_UPLOAD_PATH/plessons/courseId/时间戳.subfix(备课资源)
     */
    public static String RESOURCE_UPLOAD_PATH = "/opt/uploadfiles/";
    /*
    static {
        //已使用InitService 初始化
        SUBJECT.put(1,"大语文");
    }*/

    /**
     * 资源访问路径
     * 从配置文件中获取
     */
    public static String RESOURCE_ACCESS_PATH = "http://127.0.0.1/";
    /**
     * 访问登录地址URL
     * 从配置文件中获取
     */
    public static String LOGIN_URL = "http://127.0.0.1/";
    /**
     * 访问登录地址URL
     * 从配置文件中获取
     */
    public static String ACCESS_URL = "http://127.0.0.1/inedx.do";

}
