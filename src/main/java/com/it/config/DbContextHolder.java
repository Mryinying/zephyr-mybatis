package com.it.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author wang_zy
 * @Date 2019/12/16 10:23
 */
@Slf4j
public class DbContextHolder {

    public static final String MASTER = "master";
    public static final String SLAVE = "slave";

    private static ThreadLocal<String> contextHolder= new ThreadLocal<>();

    public static void setDbType(String dbType) {
        if (dbType == null) {
            log.error("========dbType null=========");
            throw new NullPointerException();
        }
        log.debug("=======set dbType：{}========",dbType);
        contextHolder.set(dbType);
    }

    public static String getDbType() {
        return contextHolder.get() == null ? SLAVE : contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
