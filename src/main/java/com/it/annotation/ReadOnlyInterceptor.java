package com.it.annotation;

import com.it.config.DbContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author wang_zy
 * @Date 2019/12/17 11:39
 */
@Slf4j
@Aspect
@Component
public class ReadOnlyInterceptor implements Ordered {

    @Around("@annotation(readOnly)")
    public Object setRead(ProceedingJoinPoint joinPoint, ReadOnly readOnly) throws Throwable{
        try{
            DbContextHolder.setDbType(DbContextHolder.SLAVE);
            return joinPoint.proceed();
        }finally {
            //清楚DbType一方面为了避免内存泄漏，更重要的是避免对后续在本线程上执行的操作产生影响
            DbContextHolder.clearDbType();
            log.info("=====clear threadLocal dbType=====");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
