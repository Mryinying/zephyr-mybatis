package com.it.spi.service;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @Author admin
 * @Date 2020/4/29 14:38
 */
public class DuckServiceTest {

    @Test
    public void testSpi(){
        ServiceLoader<DuckService> duckServices = ServiceLoader.load(DuckService.class);
        duckServices.forEach(DuckService::sayHello);
    }

}