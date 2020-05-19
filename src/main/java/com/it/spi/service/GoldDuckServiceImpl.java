package com.it.spi.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author admin
 * @Date 2020/4/29 14:24
        */
@Slf4j
public class GoldDuckServiceImpl  implements  DuckService{
    @Override
    public void sayHello() {
        log.info("Gold   Duck   Forever............");
    }
}
