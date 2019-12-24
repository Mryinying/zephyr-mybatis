package com.it.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Random;

/**
 * @Author wang_zy
 * @Date 2019/12/16 11:41
 */
@Slf4j
public class MyAbstractRoutingDataSource  extends AbstractRoutingDataSource {

    @Value("${mysql.datasource.num}")
    private int num;

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DbContextHolder.getDbType();
        if (typeKey.equals(DbContextHolder.MASTER)) {
            log.info("======use master write======");
            return typeKey;
        }
        //使用随机数决定使用哪个读库
        int sum = new Random().nextInt(num+1);
        log.info("=====use slave{} read======", sum);
        return DbContextHolder.SLAVE + sum;
    }
}
