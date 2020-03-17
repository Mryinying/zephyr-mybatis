package com.it.aspect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author wang_zy
 * @Date 2019/12/17 13:45
 */
@Component
@Aspect
@Slf4j
@Order(1)
public class LogAspect {

    @Pointcut("execution(* com.it.plus.*.controller.*.*(..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object entry(ProceedingJoinPoint joinpoint) throws Throwable {

        // 方法名
        String method = joinpoint.getSignature().getName();
        String name = joinpoint.getSignature().getDeclaringType().getName();

        // 接口入参
        Object[] args = joinpoint.getArgs();
        log.info("LogAspect: enter method={},args={}",name + "." + method, args);

        // 调用实际接口耗时
        long start = System.currentTimeMillis();
        Object result = joinpoint.proceed();
        long end = System.currentTimeMillis();
        long cost = end - start;


        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> {
            return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // "yyyy-mm-dd"
        }).create();
        String json = gson.toJson(result);

        // 记录日志
        log.info("LogAspect: quit method={}, cost={}ms, result={}", name + "." + method, cost, json);

        // 返回调用接口结果
        return result;
    }

}
