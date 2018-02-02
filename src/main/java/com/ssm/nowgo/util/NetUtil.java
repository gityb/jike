package com.ssm.nowgo.util;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;

public class NetUtil {

    @SuppressWarnings("unchecked")
    public static void clearDNSCache() {
        try {
            Class clazz=java.net.InetAddress.class;
            final Field cacheField=clazz.getDeclaredField("addressCache");
            cacheField.setAccessible(true);
            final Object o=cacheField.get(clazz);
            Class clazz2=o.getClass();
            // final Field cachePolicyField = clazz2.getDeclaredField("policy");
            final Field cacheMapField=clazz2.getDeclaredField("cache");
            cacheMapField.setAccessible(true);
            final Map cacheMap=(Map)cacheMapField.get(o);
            // cachePolicyField.setAccessible(true);
            AccessController.doPrivileged(new PrivilegedAction<Object>() {

                public Object run() {
                    try {
                        synchronized(o) {// 同步是必须的,因为o可能会有多个线程同时访问修改。
                            cacheMap.clear();// 这步比较关键，用于清除原来的缓存
                            // cachePolicyField.setInt(o,0);//设置缓存的时间，单位秒(-1,永久缓存;0,不缓存;其它>0的值为缓存的秒数)
                        }
                    } catch(Throwable te) {
                        throw new RuntimeException(te);
                    }
                    return null;
                }
            });
        } catch(Exception ex) {
        }
    }
}
