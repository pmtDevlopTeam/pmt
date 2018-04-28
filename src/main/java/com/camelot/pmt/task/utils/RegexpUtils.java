package com.camelot.pmt.task.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class RegexpUtils {
	private static Logger log = LoggerFactory.getLogger(RegexpUtils.class);  
	  
    private RegexpUtils() {
    }  
    
    /*------------------ 正则表达式 ---------------------*/  
    /** 
     * 邮箱 
     */  
    public static final String REGEX_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";  
    
    /** 
     * 手机号码 
     */  
    public static final String REGEX_PHONE = "^13[0-9]{9}|15[012356789][0-9]{8}|18[0-9]{9}|(14[57][0-9]{8})|(17[015678][0-9]{8})$";  
    
    /** 
     * 仅中文 
     */  
    public static final String REGEX_CHINESE = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";  
    
    /** 
     * 整数 
     */  
    public static final String REGEX_INTEGER = "^-?[1-9]\\d*$";  
    
    /** 
     * 数字 
     */  
    public static final String REGEX_NUMBER = "^([+-]?)\\d*\\.?\\d+$";  
    
    /** 
     * 正整数 
     */  
    public static final String REGEX_INTEGER_POS = "^[1-9]\\d*$";  
    
    /** 
     * 浮点数 
     */  
    public static final String REGEX_FLOAT = "^([+-]?)\\d*\\.\\d+$";  
    
    /** 
     * 正浮点数 
     */  
    public static final String REGEX_FLOAT_POS = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";  
    
    /** 
     * 字母 
     */  
    public static final String REGEX_LETTER = "^[A-Za-z]+$";  
    
    /** 
     * 大写字母 
     */  
    public static final String REGEX_LETTER_UPPERCASE = "^[A-Z]+$";  
    
    /** 
     * 小写字母 
     */  
    public static final String REGEX_LETTER_LOWERCASE = "^[a-z]+$";  
    
    /** 
     * 邮编 
     */  
    public static final String REGEX_ZIPCODE = "^\\d{6}$";  
    
    /** 
     * ip v4地址 
     */  
    public static final String REGEX_IP4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";  
    
    /** 
     * 图片 
     */  
    public static final String REGEX_PICTURE = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";/** 
    
    /** 
     * 压缩文件 
     */  
    public static final String REGEX_RAR = "(.*)\\.(rar|zip|7zip|tgz)$";  
    
    /** 
     * QQ号码，最短5位，最长15位数字 
     */  
    public static final String REGEX_QQ = "^[1-9]\\d{4,14}$";  
    
    /** 
     * 日期（yyyy-MM-dd） 
     */  
    public static final String REGEX_DATE = "^\\d{4}\\D+\\d{2}\\D+\\d{2}$";  
    
    /** 
     * 日期（yyyy-MM-dd），精确，能检查到2月及31号 
     */  
    public static final String REGEX_DATE_PRECISE = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";  
    
    /** 
     * 时间（HH:mm:ss或HH:mm） 
     */  
    public static final String REGEX_TIME = "^((([0-1][0-9])|2[0-3]):[0-5][0-9])(:[0-5][0-9])?$";  
    
    /** 
     * 匹配图象 
     * 格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)  
     * 匹配 : /forum/head_icon/admini2005111_ff.gif 或 admini2005111.dmp  
     * 不匹配: c:/admins4512.gif  
     */  
    public static final String ICON_REGEXP = "^(/{0,1}//w){1,}//.(gif|dmp|png|jpg)$|^//w{1,}//.(gif|dmp|png|jpg)$";  
  
    /** 
     * 匹配email地址 
     * 格式: XXX@XXX.XXX.XX  
     * 匹配 : foo@bar.com 或 foobar@foobar.com.au 
     * 不匹配: foo@bar 或 $$$@bar.com  
     */  
    public static final String EMAIL_REGEXP = "(?://w[-._//w]*//w@//w[-._//w]*//w//.//w{2,3}$)";  
  
    /** 
     * 匹配并提取url  
     * 格式: XXXX://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX 
     * 匹配 : http://www.suncer.com 或news://www 
     * 不匹配: c:/window 
     */  
    public static final String URL_REGEXP = "(//w+)://([^/:]+)(://d*)?([^#//s]*)";  
  
    /** 
     * 匹配并提取http  
     * 格式: http://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX 或 ftp://XXX.XXX.XXX 或 
     * https://XXX  
     * 匹配 : http://www.suncer.com:8080/index.html?login=true 
     * 不匹配: news://www  
     */  
    public static final String HTTP_REGEXP = "(http|https|ftp)://([^/:]+)(://d*)?([^#//s]*)";  
  
    /** 
     * 匹配日期  
     * 格式(首位不为0): XXXX-XX-XX或 XXXX-X-X   
     * 范围:1900--2099   
     * 匹配 : 2005-04-04   
     * 不匹配: 01-01-01  
     */  
    public static final String DATE_BARS_REGEXP = "^((((19){1}|(20){1})\\d{2})|\\d{2})-[0,1]?\\d{1}-[0-3]?\\d{1}$";    
  
    /** 
     * 匹配日期   
     * 格式: XXXX/XX/XX   
     * 范围:  
     * 匹配 : 2005/04/04   
     * 不匹配: 01/01/01  
     */  
    public static final String DATE_SLASH_REGEXP = "^[0-9]{4}/(((0[13578]|(10|12))/(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)/(0[1-9]|[1-2][0-9]|30)))$";  
  
    /** 
     * 匹配电话   
     * 格式为: 0XXX-XXXXXX(10-13位首位必须为0) 或0XXX XXXXXXX(10-13位首位必须为0) 或  
     * (0XXX)XXXXXXXX(11-14位首位必须为0) 或 XXXXXXXX(6-8位首位不为0) 或 
     * XXXXXXXXXXX(11位首位不为0)   
     * 匹配 : 0371-123456 或 (0371)1234567 或 (0371)12345678 或 010-123456 或 
     * 010-12345678 或 12345678912   
     * 不匹配: 1111-134355 或 0123456789  
     */  
    public static final String PHONE_REGEXP = "^(?:0[0-9]{2,3}[-//s]{1}|//(0[0-9]{2,4}//))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";  
  
    /** 
     * 匹配身份证  
     * 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位) 或 
     * XXXXXXXXXXXXXXXXXX(18位)  
     * 匹配 : 0123456789123  
     * 不匹配: 0123456  
     */  
    public static final String ID_CARD_REGEXP = "^//d{10}|//d{13}|//d{15}|//d{18}$";  
  
    /** 
     * 匹配邮编代码   
     * 格式为: XXXXXX(6位)   
     * 匹配 : 012345  
     * 不匹配: 0123456  
     */  
    public static final String ZIP_REGEXP = "^[0-9]{6}$";// 匹配邮编代码  
  
    /** 
     * 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号> 左尖括号< 反斜杠/ 
     * 即空格,制表符,回车符等 )   
     * 格式为: x 或 一个一上的字符   
     * 匹配 : 012345   
     * 不匹配: 0123456 // ;,:-<>//s].+$";// 
     */  
    public static final String NON_SPECIAL_CHAR_REGEXP = "^[^'/";  
  
    /** 
     * 匹配非负整数（正整数 + 0) 
     */  
    public static final String NON_NEGATIVE_INTEGERS_REGEXP = "^//d+$";  
  
    /** 
     * 匹配不包括零的非负整数（正整数 > 0) 
     */  
    public static final String NON_ZERO_NEGATIVE_INTEGERS_REGEXP = "^[1-9]+//d*$";  
  
    /**  
     * 匹配正整数   
     */  
    public static final String POSITIVE_INTEGER_REGEXP = "^[0-9]*[1-9][0-9]*$";  
  
    /**   
     * 匹配非正整数（负整数 + 0）   
     */  
    public static final String NON_POSITIVE_INTEGERS_REGEXP = "^((-//d+)|(0+))$";  
  
    /**   
     * 匹配负整数  
     */  
    public static final String NEGATIVE_INTEGERS_REGEXP = "^-[0-9]*[1-9][0-9]*$";  
  
    /**   
     * 匹配整数  
     */  
    public static final String INTEGER_REGEXP = "^-?//d+$";  
  
    /**   
     * 匹配非负浮点数（正浮点数 + 0）  
     */  
    public static final String NON_NEGATIVE_RATIONAL_NUMBERS_REGEXP = "^//d+(//.//d+)?$";  
  
    /**   
     * 匹配正浮点数  
     */  
    public static final String POSITIVE_RATIONAL_NUMBERS_REGEXP = "^(([0-9]+//.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*//.[0-9]+)|([0-9]*[1-9][0-9]*))$";  
  
    /**   
     * 匹配非正浮点数（负浮点数 + 0）  
     */  
    public static final String NON_POSITIVE_RATIONAL_NUMBERS_REGEXP = "^((-//d+(//.//d+)?)|(0+(//.0+)?))$";  
  
    /**   
     * 匹配负浮点数   
     */  
    public static final String NEGATIVE_RATIONAL_NUMBERS_REGEXP = "^(-(([0-9]+//.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*//.[0-9]+)|([0-9]*[1-9][0-9]*)))$";
    
    /**   
     * 匹配浮点数  
     */  
    public static final String RATIONAL_NUMBERS_REGEXP = "^(-?//d+)(//.//d+)?$";  
  
    /**   
     * 匹配由26个英文字母组成的字符串   
     */  
    public static final String LETTER_REGEXP = "^[A-Za-z]+$";  
  
    /**   
     * 匹配由26个英文字母的大写组成的字符串 
     */  
    public static final String UPWARD_LETTER_REGEXP = "^[A-Z]+$";  
  
    /**   
     * 匹配由26个英文字母的小写组成的字符串   
     */  
    public static final String LOWER_LETTER_REGEXP = "^[a-z]+$";  
  
    /**   
     * 匹配由数字和26个英文字母组成的字符串  
     */  
    public static final String LETTER_NUMBER_REGEXP = "^[A-Za-z0-9]+$";  
  
    /**   
     * 匹配由数字、26个英文字母或者下划线组成的字符串   
     */  
    public static final String LETTER_NUMBER_UNDERLINE_REGEXP = "^//w+$"; 
      
    /** 
     * 校验手机号码 
     * @param mobile 
     * @return 
     * @author lqyao 
     */  
    public static final boolean isMoblie(String mobile){  
        boolean flag = false;  
        if (null != mobile && !mobile.trim().equals("") && mobile.trim().length() == 11) {  
            Pattern pattern = Pattern.compile(REGEX_PHONE);  
            Matcher matcher = pattern.matcher(mobile.trim());  
            flag = matcher.matches();  
        }  
        return flag;  
    }  
      
    /** 
     * 校验邮箱 
     * @param value 
     * @return 
     */  
    public static final boolean isEmail(String value){  
        boolean flag = false;  
        if (null != value && !value.trim().equals("")) {  
            Pattern pattern = Pattern.compile(REGEX_EMAIL);  
            Matcher matcher = pattern.matcher(value.trim());  
            flag = matcher.matches();  
        }  
        return flag;  
    }  
      
    /** 
     * 校验密码 
     * @param password 
     * @return 长度符合返回true，否则为false 
     * @since 2015-09-24 
     */  
    public static final boolean isPassword(String password){  
        boolean flag = false;  
        if (null != password && !password.trim().equals("")) {  
            password = password.trim();  
            if(password.length() >= 6 && password.length() <= 30){  
                return true;  
            }  
        }  
        return flag;  
    }  
      
    /** 
     * 校验手机验证码 
     * @param value 
     * @return 符合正则表达式返回true，否则返回false  
     * @since 2015-09-24 
     */  
    public static final boolean isPhoneValidateCode(String value){  
        boolean flag = false;  
        if (null != value && !value.trim().equals("")) {  
            Pattern pattern = Pattern.compile("^8\\d{5}$");  
            Matcher matcher = pattern.matcher(value.trim());  
            flag = matcher.matches();  
        }  
        return flag;  
    } 
      
    /**
     * 
    * @Title: isUpperCase 
    * @Description: TODO(判断是否有大写字母)
    * @param @param str
     */
    public static boolean isUpperCase(String str){  
        if(StringUtils.isEmpty(str)){  
            return false;  
        }  
        String reg = "^[A-Z]$";  
        return isMatch(reg,str);  
    }
    
    /** 
     * 正则表达式校验,符合返回True 
     * @param regex 正则表达式 
     * @param content 校验的内容 
     * @return 
     * @author gxl
     */  
    public static boolean isMatch(String regex, CharSequence content){  
        return Pattern.matches(regex, content);  
    }  
}
