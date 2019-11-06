package com.yunpian.stargate.core.utils;

import com.yunpian.stargate.core.exception.StargateException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/12/5 Time:7:04 PM
 */
public class EncodAndDncodeFactory {
    private static Map<Class,Object> encodAndDncode = new ConcurrentHashMap<Class,Object>();
    public static Object getEncodOrDncod(Class aClass){
        Object o = encodAndDncode.get(aClass);
        if (o==null){
            synchronized (encodAndDncode) {
                try {
                    o = encodAndDncode.get(aClass);
                    if (o==null) {
                        o = aClass.newInstance();
                        encodAndDncode.put(aClass,o);
                    }
                } catch (Exception e) {
                    throw new StargateException(e);
                }
            }
        }
        return o;
    }
}
