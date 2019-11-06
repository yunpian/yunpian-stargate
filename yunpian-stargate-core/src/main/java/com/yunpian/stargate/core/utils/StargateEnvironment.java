package com.yunpian.stargate.core.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/12/5 Time:5:30 PM
 */
public class StargateEnvironment {
    private static Map<String, String> namesrvAddr = new ConcurrentHashMap<>();
    private static String namesrvDefault;
    private static int threadSize;
    private static boolean delayMsecSwitch;
    private static Class decodeClass;
    private static Class encodClass;

    public static Map<String, String> getNamesrvAddr() {
        return namesrvAddr;
    }

    public static void setNamesrvAddr(Map<String, String> namesrvAddr) {
        StargateEnvironment.namesrvAddr = namesrvAddr;
    }

    public static int getThreadSize() {
        return threadSize;
    }

    public static void setThreadSize(int threadSize) {
        StargateEnvironment.threadSize = threadSize;
    }

    public static boolean isDelayMsecSwitch() {
        return delayMsecSwitch;
    }

    public static void setDelayMsecSwitch(boolean delayMsecSwitch) {
        StargateEnvironment.delayMsecSwitch = delayMsecSwitch;
    }

    public static String getNamesrv(String key) {
        return namesrvAddr.get(key);
    }

    public static String getNamesrvDefault() {
        return namesrvDefault;
    }

    public static void setNamesrvDefault(String namesrvDefault) {
        StargateEnvironment.namesrvDefault = namesrvDefault;
    }

    public static Class getDecodeClass() {
        return decodeClass;
    }

    public static void setDecodeClass(Class decodeClass) {
        StargateEnvironment.decodeClass = decodeClass;
    }

    public static Class getEncodClass() {
        return encodClass;
    }

    public static void setEncodClass(Class encodClass) {
        StargateEnvironment.encodClass = encodClass;
    }
}
