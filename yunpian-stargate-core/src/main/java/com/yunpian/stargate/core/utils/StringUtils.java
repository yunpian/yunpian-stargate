package com.yunpian.stargate.core.utils;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/9/26 Time:下午4:10
 */
public class StringUtils {
    public static boolean isEmpty(String s){
        if (s == null || "".equals(s)) {
            return true;
        }
        return false;
    }

    public static boolean isBlank(String s){
        if (isEmpty(s)) {
            return true;
        }
        return s.trim().isEmpty();
    }
    //首字母转小写
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
