package com.it.aspect;

import com.it.config.db.DbContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author wang_zy
 * @Date 2019/12/17 13:45
 */
@Component
@Aspect
@Slf4j
@Order(0)
public class DBAspect {

    @Pointcut("execution(* com.it.plus.*.mapper.*.*(..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object entry(ProceedingJoinPoint joinPoint) throws Throwable {

        // 方法名
        String method = joinPoint.getSignature().getName();

        // 接口入参
        String[] pre = {"get", "select", "list", "query", "page"};
        for (String s : pre) {
            if (method.contains(s)) {
                DbContextHolder.setDbType(DbContextHolder.SLAVE);
                break;
            }
        }

        DbContextHolder.setDbType(DbContextHolder.SLAVE);
        return joinPoint.proceed();
    }

    @After("pointCut()")
    public void after(){
        DbContextHolder.clearDbType();
        log.debug("=====clear threadLocal dbType=====");
    }

}
