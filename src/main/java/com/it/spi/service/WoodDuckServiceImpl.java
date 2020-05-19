package com.it.spi.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author admin
 * @Date 2020/4/29 14:23
 */
@Slf4j
public class WoodDuckServiceImpl implements DuckService {
    @Override
    public void sayHello() {
        log.info("Wood   Duck   For Moment............");
    }
}
