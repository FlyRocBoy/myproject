package com.gaopeng.project.util;

import com.etiantian.unite.utils.StringUtil;
import com.etiantian.unite.utils.UrlSignUtil;
import com.gaopeng.project.util.config.MsgPropertie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具方法类
 * Created by zhengzhou on 15-1-13.
 */
public class UtilTool {
    //log4j
    private static Logger logger = org.apache.logging.log4j.LogManager.getLogger(UtilTool.class);

    private final static String DES = "des";
    //编码格式  程序UTF-8
    private final static String CODE_CHARTSET = "UTF-8";
    private final static String SERVLET_DEFALUT_CHARSET="ISO-8859-1";
    public final static Properties msgProperties = MsgPropertie.getInstance().prop;

    /**
     * 工具Util信息配置文件
     */
    public static Properties utilproperty=UtilProperty.getInstance().prop;

    public final static String SECRETKEY = "DYW#HONER#2016";
    /**
     * RequestParams相关参数
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static HashMap<String, String> getRequestParam(HttpServletRequest request) throws Exception {
        Enumeration eObj = request.getParameterNames();
        HashMap<String, String> returnMap = null;
        if (eObj != null) {
            returnMap = new HashMap<String, String>();
            while (eObj.hasMoreElements()) {
                Object obj = eObj.nextElement();
                if (obj == null || obj.toString().trim().length() < 1
                        || obj.toString().trim().equals("m") || obj.toString().equals(request.getQueryString()))
                    continue;

                Object val = null;
                val = request.getParameter(obj.toString());

                if(val==null||(val instanceof String && val.toString().trim().length()==0)){
                    continue;
                }
                String paramVal = val == null ? null :val.toString();
                if(val!=null && "GET".equals(request.getMethod()))
                    paramVal =  servletCharset2CodeCharset(paramVal);
                returnMap.put(obj.toString(), paramVal);
            }
        }
        return returnMap;
    }

    /**
     * 将字符串 有servlet编码转换为项目编码
     * @param src
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/2/8 14:39
     * @return
     */
    public static String servletCharset2CodeCharset(String src) throws UnsupportedEncodingException {
        return src !=null? new String(src.getBytes(SERVLET_DEFALUT_CHARSET),CODE_CHARTSET):null;
    }

    /**
     * 验证是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        if (str == null || str.trim().length() == 0)
            return false;
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    public static boolean isMobile(String str){
        if (str == null || str.trim().length() == 0)
            return false;
        return (str.trim().matches("^1[3|4|5|8][0-9]\\d{4,8}$")&&str.trim().length()==11);
    }

    /**
     * 存入Session 中。
     *
     * @param request HttpServletRequest对象
     * @param key     存入Session的key
     * @param val     存入Session的value（值）
     */
    public static void setAttributeSession(HttpServletRequest request, String key,
                                           Object val) {
        if (key == null)
            return;
        request.getSession().setAttribute(key, val);
    }

    /**
     * 得到session 中 key的值
     *
     * @param request
     * @param key
     * @return
     */
    public static Object getAttributeSession(HttpServletRequest request, String key) {
        if (key == null)
            return null;
        return request.getSession().getAttribute(key);
    }

    /**
     * 日期转换(将String类型换成时间格式)
     *
     * @param str
     * @param dt
     * @return
     */
    public static Date StringConvertToDate(String str, EnumUtil.DateType dt) {
        if (str == null || str.trim() == "")
            return null;
        if (dt == null) {
            if (str.indexOf("GMT") != -1)
                dt = EnumUtil.DateType.DATE_GMT;
            else
                dt = EnumUtil.DateType.DATE_FULL;
        }
        if (str.indexOf("年") != -1 || str.indexOf("月") != -1
                || str.indexOf("日") != -1 || str.indexOf("时") != -1
                || str.indexOf("分") != -1 || str.indexOf("秒") != -1)
            str = str.replace("年", "-").replace("月", "-").replace("日", "")
                    .replace("时", ":").replace("分", ":").replace("秒", "");

        SimpleDateFormat daformat = null;
        if (EnumUtil.DateType.DATE == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE.toString());
        } else if (EnumUtil.DateType.DATE_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_2.toString());
        } else if (EnumUtil.DateType.DATE_FULL == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_2.toString());
        } else if (EnumUtil.DateType.DATE_FULL_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_FULL_2.toString());
        } else if (EnumUtil.DateType.DATE_HOUR24 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_HOUR24.toString());
        } else if (EnumUtil.DateType.DATE_HOUR24_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_HOUR24_2.toString());
        } else if (EnumUtil.DateType.DATE_TIME == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_TIME.toString());
        } else if (EnumUtil.DateType.DATE_GMT == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_GMT.toString(), Locale.ENGLISH);
        } else if (EnumUtil.DateType.DATE_HOUR24_MIN_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_HOUR24_MIN_2.toString());
        } else if (EnumUtil.DateType.DATE_MONTH_DAY_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_MONTH_DAY_2.toString());
        }
        Date returndt = null;
        try {
            returndt = daformat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return returndt;
    }

    /**
     * 从DATE 转换成 String
     *
     * @param da
     * @param dt
     * @return
     */
    public static String DateConvertToString(Date da, EnumUtil.DateType dt) {
        if (da == null || dt == null)
            return null;
        SimpleDateFormat daformat = null;
        if (EnumUtil.DateType.DATE == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE.toString());
        } else if (EnumUtil.DateType.DATE_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_2.toString());
        } else if (EnumUtil.DateType.DATE_FULL == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_FULL.toString());
        } else if (EnumUtil.DateType.DATE_FULL_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_FULL_2.toString());
        } else if (EnumUtil.DateType.DATE_HOUR24 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_HOUR24.toString());
        } else if (EnumUtil.DateType.DATE_HOUR24_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_HOUR24_2.toString());
        }  else if (EnumUtil.DateType.DATE_HOUR24_MIN_2_ == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_HOUR24_MIN_2_.toString());
        } else if (EnumUtil.DateType.DATE_TIME == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_TIME.toString());
        }  else if (EnumUtil.DateType.DATE_SECOND_TIME == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_SECOND_TIME.toString());
        } else if (EnumUtil.DateType.DATE_GMT == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_GMT.toString(), Locale.ENGLISH);
        } else if (EnumUtil.DateType.DATE_SIMPLE_CHINA == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_SIMPLE_CHINA.toString());
        } else if (EnumUtil.DateType.DATE_FULL_CHINA == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_FULL_CHINA.toString());
        } else if (EnumUtil.DateType.DATE_2_SHORT == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_2_SHORT.toString());
        } else if (EnumUtil.DateType.DATE_SHORT == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_SHORT.toString());
        } else if (EnumUtil.DateType.DATE_HOUR24_MIN_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_HOUR24_MIN_2.toString());
        } else if (EnumUtil.DateType.DATE_MONTH_DAY_2 == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_MONTH_DAY_2.toString());
        } else if (EnumUtil.DateType.DATE_MONTHANDDAY == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.DATE_MONTHANDDAY.toString());
        }else if (EnumUtil.DateType.YEAR == dt) {
            daformat = new SimpleDateFormat(EnumUtil.DateType.YEAR.toString());
        }
        return daformat.format(da);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 从String 转换成 DATE
     *
     * @param str
     * @return
     */
    public static Date StringConvertToDate(String str) {
        if (str == null || str.trim() == "")
            return null;
        return StringConvertToDate(str, null);
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = encodeBase64(bt);
        return strs;
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws java.io.IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;

        byte[] buf = decodeBase64Byte(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }


    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }


    // 将 URL 编码
    public static String urlEncode(String str) {
        String target;
        try {
            target = URLEncoder.encode(str, CODE_CHARTSET);
        } catch (Exception e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将 URL 解码
    public static String urlDecode(String str) {
        String target;
        try {
            target = URLDecoder.decode(str, CODE_CHARTSET);
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 编码
    public static String encodeBase64(byte[] bt) {
        String target;
        try {
            target = Base64.encodeBase64String(bt);
        } catch (Exception e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 编码
    public static String encodeBase64(String str) {
        String target;
        try {
            target = Base64.encodeBase64URLSafeString(str.getBytes(CODE_CHARTSET));
        } catch (UnsupportedEncodingException e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }


    // 将字符串 Base64 解码
    public static String decodeBase64(String str) {
        String target;
        try {
            target = new String(Base64.decodeBase64(str), CODE_CHARTSET);
        } catch (UnsupportedEncodingException e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 解码
    public static String decodeBase64(byte[] bt) {
        String target;
        try {
            target = new String(Base64.decodeBase64(bt));
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 解码
    public static byte[] decodeBase64Byte(byte[] bt) {
        byte[] target;
        try {
            target = Base64.decodeBase64(bt);
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 解码
    public static byte[] decodeBase64Byte(String str) {
        byte[] target;
        try {
            target = Base64.decodeBase64(str);
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }


    // 将字符串 MD5 加密
    public static String encryptMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    // 将字符串 SHA 加密
    public static String encryptSHA(String str) {
        return DigestUtils.sha1Hex(str);
    }

    // 创建随机数
    public static String createRandom(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 获取UUID（32位）
     */
    public static String createUUID32() {
        return createUUID().replaceAll("-", "");
    }
    /**
     * 获取UUID（36位）
     */
    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 执行后台远程访问
     *
     * @param url
     * @return
     * @throws java.io.IOException
     */
    public static String getHttpResponce(String url) throws IOException {
        HttpClient client = new HttpClient();
        StringBuilder sb = new StringBuilder();
        InputStream ins = null;
        // Create a method instance.
        GetMethod method = new GetMethod(url);

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode == HttpStatus.SC_OK) {
                ins = method.getResponseBodyAsStream();
                byte[] b = new byte[1024];
                int r_len = 0;
                while ((r_len = ins.read(b)) > 0) {
                    sb.append(new String(b, 0, r_len, method
                            .getResponseCharSet()));
                }
            } else {
                //System.err.println("Response Code: " + statusCode);
                logger.info("Response Code: " + statusCode);
            }
        } catch (HttpException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            method.releaseConnection();
            if (ins != null) {
                ins.close();
            }
        }

        return sb.toString();
    }

    /**
     * 判断字符串是否不为空或者null
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    /**
     * 判断字符串是否为空或者null
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    // 若字符串为空，则取默认值
    public static String defaultIfEmpty(String str, String defaultValue) {
        return StringUtils.defaultIfEmpty(str, defaultValue);
    }

    private static boolean checkParamName(String paramName) {
        return !paramName.equals("_"); // 忽略 jQuery 缓存参数
    }

    // 转发请求
    public static void forwardRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception e) {
            logger.error("转发请求出错！", e);
            throw new RuntimeException(e);
        }
    }

    // 重定向请求
    public static void redirectRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (Exception e) {
            logger.error("重定向请求出错！", e);
            throw new RuntimeException(e);
        }
    }

    // 发送错误代码
    public static void sendError(int code, HttpServletResponse response) {
        try {
            response.sendError(code);
        } catch (Exception e) {
            logger.error("发送错误代码出错！", e);
            throw new RuntimeException(e);
        }
    }

    // 判断是否为 AJAX 请求
    public static boolean isAJAX(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null;
    }

    // 获取请求路径
    public static String getRequestPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = defaultIfEmpty(request.getPathInfo(), "");
        return servletPath + pathInfo;
    }

    // 从 Cookie 中获取数据
    public static String getCookie(HttpServletRequest request, String name) {
        String value = "";
        try {
            Cookie[] cookieArray = request.getCookies();
            if (cookieArray != null) {
                for (Cookie cookie : cookieArray) {
                    if (isNotEmpty(name) && name.equals(cookie.getName())) {
                        value = urlDecode(cookie.getValue());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取 Cookie 出错！", e);
            throw new RuntimeException(e);
        }
        return value;
    }

    // 设置 Redirect URL 到 Session 中
    public static void setRedirectURL(HttpServletRequest request, String sessionKey) {
        if (!isAJAX(request)) {
            String requestPath = getRequestPath(request);
            request.getSession().setAttribute(sessionKey, requestPath);
        }
    }

    // 创建验证码
    public static String createCaptcha(HttpServletResponse response) {
        StringBuilder captcha = new StringBuilder();
        try {
            // 参数初始化
            int width = 60;                      // 验证码图片的宽度
            int height = 25;                     // 验证码图片的高度
            int codeCount = 4;                   // 验证码字符个数
            int codeX = width / (codeCount + 1); // 字符横向间距
            int codeY = height - 4;              // 字符纵向间距
            int fontHeight = height - 2;         // 字体高度
            int randomSeed = 10;                 // 随机数种子
            char[] codeSequence = {              // 验证码中可出现的字符
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
            };
            // 创建图像
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bi.createGraphics();
            // 将图像填充为白色
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            // 设置字体
            g.setFont(new Font("Courier New", Font.BOLD, fontHeight));
            // 绘制边框
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, width - 1, height - 1);
            // 产生随机干扰线（160条）
            g.setColor(Color.WHITE);
            // 创建随机数生成器
            Random random = new Random();
            for (int i = 0; i < 160; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, x + xl, y + yl);
            }
            // 生成随机验证码
            int red, green, blue;
            for (int i = 0; i < codeCount; i++) {
                // 获取随机验证码
                String validateCode = String.valueOf(codeSequence[random.nextInt(randomSeed)]);
                // 随机构造颜色值
                red = random.nextInt(255);
                green = random.nextInt(255);
                blue = random.nextInt(255);
                // 将带有颜色的验证码绘制到图像中
                g.setColor(new Color(red, green, blue));
                g.drawString(validateCode, (i + 1) * codeX - 6, codeY);
                // 将产生的随机数拼接起来
                captcha.append(validateCode);
            }
            // 禁止图像缓存
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            // 设置响应类型为 JPEG 图片
            response.setContentType("image/jpeg");
            // 将缓冲图像写到 Servlet 输出流中
            ServletOutputStream sos = response.getOutputStream();
            ImageIO.write(bi, "jpeg", sos);
            sos.close();
        } catch (Exception e) {
            logger.error("创建验证码出错！", e);
            throw new RuntimeException(e);
        }
        return captcha.toString();
    }


    // 是否为 IE 浏览器
    public boolean isIE(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        return agent != null && agent.contains("MSIE");
    }

    /**
     * 根据一个时间得到距离当前时间多久
     *
     * @param d   时间
     * @param day 多少天后直接显示年，月,日
     * @param dt1 日期格式
     * @return
     */
    public static String getAFewTimeAgo(Date d, int day, EnumUtil.DateType dt1) {
        String timeString = "";
        if (d == null) return timeString;
        long now = System.currentTimeMillis();
        long time = now - d.getTime();
        if ((time / 1000) < 60) {
            return ((time / 1000) <= 1 ? 1 : (time / 1000)) + "秒前";
        }
        if ((time / (1000 * 60)) < 60) {
            return (time / (1000 * 60)) + "分钟前";
        }
        if ((time / (1000 * 60 * 60)) < 24) {
            return (time / (1000 * 60 * 60)) + "小时前";
        }
        if ((time / (1000 * 60 * 60 * 24)) > 0) {
            if ((time / (1000 * 60 * 60 * 24)) > day) {
                return DateConvertToString(d, dt1);
            }
            return (time / (1000 * 60 * 60 * 24)) + "天前";
        }
        return timeString;
    }

    /**
     * 活动详情页使用
     * 根据一个时间得到距离当前时间多久以后
     * 如果>=1天显示“xx天xx时xx分”；如果<1天且>=1小时显示“xx时xx分”；如果<1小时且>=1分钟显示“xx分”；
     * 如果<1分钟显示“xx秒”；如果活动已结束显示“0”
     * @param d  时间
     * @return
     */
    public static String getAFewTimeLater(Date d){
        String timeString = "";
        if (d == null) return timeString;
        long now = System.currentTimeMillis();
        long time = d.getTime() - now;
        if(time <= 0){
            return "0";
        }
        if ((time / 1000) < 60) {
            return ((time / 1000) <= 1 ? 1 : (time / 1000)) + "秒";
        }
        if ((time / (1000 * 60)) < 60) {
            return (time / (1000 * 60)) + "分";
        }
        if ((time / (1000 * 60 * 60)) < 24) {
            return (time / (1000 * 60 * 60)) + "时" + (time % (1000 * 60 * 60))/(1000 * 60) + "分" ;
        }
        if ((time / (1000 * 60 * 60 * 24)) > 0) {
            return (time / (1000 * 60 * 60 * 24)) + "天" + (time % (1000 * 60 * 60 * 24))/(1000 * 60 * 60) + "时" + (time % (1000 * 60 * 60))/(1000 * 60) + "分";
        }
        return timeString;
    }

    /**
     * 转换成JSON
     *
     * @return
     * @throws Exception
     */
    public String toJSON() {
        return toJSON(this);

    }

    /**
     * 转换成JSON
     *
     * @param obj
     * @return
     */
    public static String toJSON(Object obj) {
        if (obj == null)
            return null;
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(obj);
    }

    /**
     * JSON转换成实体
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToModel(String json, Class<T> clazz) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.fromJson(json, clazz);
    }

    /**
     * 支持列表类型转换 比如：TypeToken<List<Person>>(){}.getTypeId()
     *
     * @param json
     * @param type new TypeToken<List<Person>>(){}.getTypeId()
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToArray(String json, Type type) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.fromJson(json, type);
    }

    public static boolean isEmptyDomainObject(Object obj) throws Exception {
        if (obj == null) {
            return true;
        }
        Object ref;
        try {
            ref = obj.getClass().getMethod("getRef", null);
        } catch (NoSuchMethodException e) {
            throw new Exception("Not Domain Object.");
        }

        if (ref == null) {
            return true;
        }

        if (ref instanceof BigDecimal) {
            if (((BigDecimal) ref).longValue() < 1) {
                return true;
            }
        } else if (ref instanceof Long) {
            if (((Long) ref).longValue() < 1) {
                return true;
            }
        } else if (ref instanceof Integer) {
            if (((Integer) ref).intValue() < 1) {
                return true;
            }
        } else if (ref instanceof Short) {
            if (((Short) ref).shortValue() < 1) {
                return true;
            }
        }

        return false;
    }


    /**
     * 截取字符串长度
     * @param str
     * @param length   字符长度  一个中文算2个
     * @param suffix
     * @return
     */
    public static String substr(String str, int length, String suffix) throws Exception {
        if (str == null || str.length() == 0 || length < 1) {
            return str;
        }
        if (suffix == null) {
            suffix = "";
        }
        if (suffix.length() > 0 && length > suffix.length()) {
            length = length - suffix.length();
        } else {
            suffix = "";
        }

        int lencounter = 0;
        int index = 0;
        char[] strarray = str.toCharArray();
        try {
            for (; index < strarray.length; index++) {
                char ch = strarray[index];
                int step = 1;
                if (String.valueOf(ch).getBytes("GBK").length > 1) {
                    step = 2;
                }
                if ((lencounter = lencounter + step) > length) {
                    index--;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (index > 0 && index < str.length()) {
            str = str.substring(0, index) + suffix;
        }

        return str;
    }

    /**
     * 验证一个数组中是否存在
     * @param array
     * @param item
     * @return
     */
    public static boolean arrayIsHasItem(Object[] array,Object item){
        if(array==null||array.length<1)return false;
        int index = Arrays.binarySearch(array, item);
        return (index!=-1);
    }

    /**
     * 校验请求参数
     * @param urlPath
     * @param params
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/14 16:29
     */


    /**
     * 正则验证文件格式
     * @param expression
     * @param text
     * @return
     */
    public static boolean matchingText(String expression, String text) {
        boolean bool = false;
        if (expression != null && !"".equals(expression) && text != null
                && !"".equals(text.toLowerCase())) {
            Pattern p = Pattern.compile(expression); // 正则表达式
            Matcher m = p.matcher(text.toLowerCase()); // 操作的字符串
            bool = m.matches();
        }
        return bool;
    }

    /**
     * 检查是否存在特殊字符
     * ' " = + - * / \ ! % ^ & ( )
     */

    /**
     * 加密密码
     * md5（dayuwen+密码）
     * @param srcPwd
     * @return
     */


    /**
     * 获取默认密码
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/15 18:05
     * @return
     */
    /**
     * 检查密码格式
     * @param pwd
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/15 18:05
     * @return
     */
    public static boolean isValidPwdFormat(String pwd){
        if(pwd == null) return false;
        if(!pwd.matches("(?!^\\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{6,}")) return false;
        return true;
    }

    /**
     * 检查是否是默认密码
     * @param pwd
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/15 18:05
     * @return
     */

    /**
     * 检查字符串是否是long
     * @param longStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/15 17:31
     * @return
     */
    public static boolean isLongValue(String longStr){
        if(StringUtil.isEmpty(longStr))
            return false;
        try {
            Long.parseLong(longStr);
            return true;
        }catch (NumberFormatException e){
            logger.error(e.getMessage(),e);
        }
        return false;
    }
    /**
     * 检查字符串是否是long
     * @param longStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/15 17:31
     * @return
     */
    public static boolean isNotLongValue(String longStr){
        return !isLongValue(longStr);
    }
    /**
     * 检查字符串是否是long
     * @param intStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/15 17:31
     * @return
     */
    public static boolean isIntegerValue(String intStr){
        if(StringUtil.isEmpty(intStr))
            return false;
        try {
            Integer.parseInt(intStr);
            return true;
        }catch (NumberFormatException e){
            logger.error(e.getMessage(),e);
        }
        return false;
    }
    /**
     * 检查字符串是否是long
     * @param intStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/15 17:31
     * @return
     */
    public static boolean isNotIntegerValue(String intStr){
        return !isIntegerValue(intStr);
    }
    /**
     * 检查字符串是否是double
     * @param doubleStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/15 17:31
     * @return
     */
    public static boolean isNotDoubleValue(String doubleStr){
        return !isDoubleValue(doubleStr);
    }
    /**
     * 检查字符串是否是double
     * @param doubleStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/15 17:31
     * @return
     */
    public static boolean isDoubleValue(String doubleStr){
        if(StringUtil.isEmpty(doubleStr))
            return false;
        try {
            Double.parseDouble(doubleStr);
            return true;
        }catch (NumberFormatException e){
            logger.error(e.getMessage(),e);
        }
        return false;
    }
    /**
     * 检查字符串是否是json 数组字符串
     * @param jsonStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/30 18:39
     * @return
     */
    public static boolean isJSONArrayValue(String jsonStr){
        boolean b = false;
        if(StringUtils.isEmpty(jsonStr))
            return b;
        try {
            new JSONArray(jsonStr);
            b = true;
        }catch(Exception e){
            logger.error(e.getMessage(),e);
        }
        return b;
    }
    /**
     * 检查字符串是否是json 数组字符串
     * @param jsonStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/30 18:39
     * @return
     */
    public static boolean isNotJSONArrayValue(String jsonStr){
        return !isJSONArrayValue(jsonStr);
    }
    /**
     * 检查字符串是否是json字符串
     * @param jsonStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/30 18:39
     * @return
     */
    public static boolean isJSONValue(String jsonStr){
        boolean b = false;
        if(StringUtils.isEmpty(jsonStr))
            return b;
        try {
            new JSONObject(jsonStr);
            b = true;
        }catch(Exception e){
            logger.error(e.getMessage(),e);
        }
        return b;
    }

    /**
     * 检查字符串是否是json字符串
     * @param jsonStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/30 18:39
     * @return
     */
    public static boolean isNotJSONValue(String jsonStr){
        return !isJSONValue(jsonStr);
    }

    /**
     * 校验字母数字汉字字符串长度,不含空格
     * @param str
     * @param minLen
     * @param maxLen
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/17 17:56
     * @return
     */
    public static boolean validCharStrLen(String str,int minLen,int maxLen){
        if(str == null) return false;
//        return str.matches("[a-zA-Z0-9\\u2E80-\\u9FFF]{"+minLen+","+maxLen+"}");
        return str.matches(".{"+minLen+","+maxLen+"}");
    }
    /**
     * 校验字母数字汉字字符串长度,不含空格
     * @param str
     * @param minLen
     * @param maxLen
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/17 17:56
     * @return
     */
    public static boolean validAllCharStrLen(String str,int minLen,int maxLen){
        if(str == null) return false;
//        return str.matches("[a-zA-Z0-9\\u2E80-\\u9FFF]{"+minLen+","+maxLen+"}");
        return str.matches("[\\S\\s]{"+minLen+","+maxLen+"}");
    }
    /**
     * 将字符串中的\r\n 转换为\n
     * @param str
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/3/1 18:43
     * @return
     */
    public static String strRNToN(String str){
        //将\r\n转换为\n
        return str.replace("\r\n","\n");
    }

    /**
     * 去除emoji表情字符
     * @return
     */
    public static String replaceEmojiChar(String str){
        return replaceEmojiChar(str,"");
    }
    /**
     * 替换emoji表情字符
     * @param str 需要处理的字符串
     * @param slipStr emoji表情替换字符
     * @return
     */
    public static String replaceEmojiChar(String str,String slipStr){
        return str.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
    }

    /**
     * 校验字母数字字符串长度,不含空格
     * 字母数字都存在
     * @param str
     * @param minLen
     * @param maxLen
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/17 17:56
     * @return
     */
    public static boolean validCharNumStrLen(String str,int minLen,int maxLen){
        if(str == null) return false;
        return str.matches("[a-zA-Z0-9]{"+minLen+","+maxLen+"}");
    }
    /**
     * 校验字母数字字符串长度,不含空格
     * 字母数字都存在
     * @param str
     * @param minLen
     * @param maxLen
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/17 17:56
     * @return
     */
    public static boolean validOnlyCharNumStrLen(String str,int minLen,int maxLen){
        if(str == null) return false;
        return str.matches("(?!^\\d+$)(?!^[a-zA-Z]+$)[a-zA-Z0-9]{"+minLen+","+maxLen+"}");
    }
    /**
     * 校验字母汉字字符串长度,不含空格
     * @param str
     * @param minLen
     * @param maxLen
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/17 17:56
     * @return
     */
    public static boolean validStrLen(String str, int minLen, int maxLen){
        if(str == null) return false;
        return str.matches("\\S{"+minLen+","+maxLen+"}");
    }

    /**
     * 是否是电话或手机号,数字和-
     * @param str
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/17 18:28
     * @return
     */
    public static boolean isPhoneNum(String str){
        if(str == null) return false;
        return str.matches("[0-9\\-]{11,15}");
    }
    /**
     * 是否是电话或手机号,数字和-
     * @param str
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/17 18:28
     * @return
     */
    public static boolean isNotPhoneNum(String str){
        return !isPhoneNum(str);
    }
    /**
     * 校验用户Id有效性
     * @param str
     * @param prefix @前缀
     * @param code 分校代码
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/17 18:28
     * @return
     */
    public static boolean validUserLoginId(String str,String prefix,String code){
        if(str == null) return false;
        if(!str.endsWith(code)) return false;
        return str.matches("^"+prefix+"[0-9]+@"+code);
    }
    /**
     * 将Map&lt;String,String&gt;转换为Map&lt;String,Object&gt;
     * @param strValMap
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/11/22 11:31
     * @return
     */
    public static Map<String,Object> mapStrVal2ObjVal(Map<String, String> strValMap){
        if(strValMap == null) return null;
        Map<String,Object> map = new HashMap<String,Object>();
        Set<String> strSet =  strValMap.keySet();
        Iterator<String> strIt = strSet.iterator();
        while (strIt.hasNext()){
            String str = strIt.next();
            map.put(str,strValMap.get(str));
        }
        return map;
    }
    /**
     * 校验是否是 yyyy-MM-dd HH:mm 时间
     * @param timeStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/12/1 14:00
     * @return
     *      若是,则返回对应时间
     *      若否,则返回null
     */
    public static Date isyyyyMMDDHHmmTime(String timeStr){
        if(StringUtils.isEmpty(timeStr))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.SDF_YYYY_MM_DD_HH_MM);
        Date date = null;
        try {
            date = sdf.parse(timeStr);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return date;
    }
    /**
     * 将日期转换为yyyy-MM-dd HH:mm
     * @param date
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/12/3 22:40
     * @return
     */
    public static String toyyyyMMddHHmmTime(Date date){
        String dateStr = "";
        if(date == null)
            return dateStr;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.SDF_YYYY_MM_DD_HH_MM);
        try {
            dateStr = sdf.format(date);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return dateStr;
    }
    /**
     * 将日期转换为yyyy-MM-dd
     * @param dateStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/12/3 22:40
     * @return
     */
    public static Date toyyyyMMddTime(String dateStr){
        if(StringUtils.isEmpty(dateStr))
            return null;
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.SDF_YYYY_MM_DD);
            date = sdf.parse(dateStr);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return date;
    }
    /**
     * 字符串日期转换为date
     * @param
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/12/4 18:09
     * @return
     */
    public static Date stryyyyMMddHHmmtoDate(String str){
        Date date = null;
        if(str == null)
            return date;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.SDF_YYYY_MM_DD_HH_MM);
        try {
            date = sdf.parse(str);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return date;
    }

    /**
     * 校验 yyyy-mm-dd 类型字符串
     * @param timeStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/12/4 18:09
     * @return
     */
    public static boolean isNotyyyyMMddDate(String timeStr){
        return !isyyyyMMddDate(timeStr);
    }

    /**
     * 校验 yyyy-mm-dd 类型字符串
     * @param timeStr
     * @return
     */
    public static boolean  isyyyyMMddDate(String timeStr){
        if(StringUtils.isEmpty(timeStr))
            return false;
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.SDF_YYYY_MM_DD);
            date = sdf.parse(timeStr);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return date!=null;
    }


    public static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    /**
     * 通过生日获取年龄
     * @param stuBirth
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/12/14 10:54
     * @return
     */
    public static int getAgeByBirthDay(Date stuBirth) {
        Date date = new Date();
        SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new SimpleDateFormat("MM");
        SimpleDateFormat format_D = new SimpleDateFormat("D");

        String birth_year = format_y.format(stuBirth);
        String this_year = format_y.format(date);
        String birth_month = format_M.format(stuBirth);
        String this_month = format_M.format(date);
        String birth_day = format_D.format(stuBirth);
        String this_day = format_D.format(date);
        // 初步，估算
        int age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
        // 如果未到出生月份，则age - 1
        if (this_month.compareTo(birth_month) < 0 || (this_month.equals(birth_month) && this_day.compareTo(birth_day)<0))
            age -= 1;
        return age<0?0:age;
    }

    /**
     * 获取性别
     * @param sex 1男2女
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/12/16 10:47
     * @return
     */
    public static String getSexDesc(Integer sex){
        return sex.equals(2)?"女":"男";
    }
    /**
     * 获取年级
     * @param grade
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/12/16 10:49
     * @return
     */
    public static String getGradeDesc(Integer grade){
        for(Iterator<Integer> it = Constants.GRADE.keySet().iterator();it.hasNext();){
            Integer key = it.next();
            if(key.equals(grade))
                return Constants.GRADE.get(key);
        }
        return "";
    }

    /**
     * 字符串转double
     * @param dou
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/1/25 11:03
     * @return
     */
    public static double stringToDouble(String dou){
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        try {
            return nf.parse(dou).doubleValue();
        } catch (ParseException e) {
            logger.error(e.getMessage(),e);
        }
        return 0;
    }

    /**
     * 校验是否是最新版本号
     * 版本格式 v1.0
     * @param inputVersionCode 输入的版本号
     * @param newestVersionCode 最新版本号
     * @Author kangxuefeng@etiantian.com
     * @Date 2016/12/16 17:36
     * @return
     */

    /**
     * 如果开始时间和结束时间的年月日完全相同，则省略结束时间的年月日显示；
     * 如果不同，则开始时间和结束时间的年月日小时分钟都显示。
     * @param startTime
     * @param endTime
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/4/19 10:48
     * @return
     */
    public static String converTimeToSort2(Date startTime, Date endTime) {
        if(startTime == null || endTime == null)
            return "";
        Calendar nowCal = Calendar.getInstance();

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startTime);
        Integer startYear = startCal.get(Calendar.YEAR);
        Integer startMonth = startCal.get(Calendar.MONTH);
        Integer startDay = startCal.get(Calendar.DAY_OF_MONTH);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endTime);
        Integer endYear = endCal.get(Calendar.YEAR);
        Integer endMonth = endCal.get(Calendar.MONTH);
        Integer endDay = endCal.get(Calendar.DAY_OF_MONTH);
        if (startYear.equals(endYear)&& startMonth == endMonth && startDay == endDay)
            return DateConvertToString(startTime, EnumUtil.DateType.DATE_HOUR24_MIN_2_)+"-" +DateConvertToString(endTime, EnumUtil.DateType.DATE_SECOND_TIME);
        return DateConvertToString(startTime, EnumUtil.DateType.DATE_HOUR24_MIN_2_)+"-" +DateConvertToString(endTime, EnumUtil.DateType.DATE_HOUR24_MIN_2_);
    }
}
