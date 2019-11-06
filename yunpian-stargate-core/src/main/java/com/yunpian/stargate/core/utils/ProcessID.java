package com.yunpian.stargate.core.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class ProcessID {
    public static void main(String[] args) {
        System.out.println("PID:" + getPID());
    }
    private static final int pid;
    static {
        pid = getProcessID();
    }
    public static final int getPID() {
        return pid;
    }
    private static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0])  
                .intValue();  
    }  
  
}  