package com.it.plus.wcw.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.plus.wcw.entity.CustomerInfo;
import com.it.plus.wcw.service.impl.CustomerInfoServiceImpl;
import com.it.rocket.CusProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 万才经理人信息表 前端控制器
 * </p>
 *
 * @author zzzz
 * @since 2019-12-18
 */
@RestController
@RequestMapping("/wcw/customer-info")
public class CustomerInfoController {

    @Autowired
    private CustomerInfoServiceImpl customerInfoService;
    @Autowired
    private CusProducer cusProducer;

    @GetMapping("/get")
    public PageInfo<CustomerInfo> get(@RequestParam(required = false,defaultValue = "1") int pageNum,@RequestParam(required = false,defaultValue = "20")  int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<CustomerInfo> customerInfos = customerInfoService.list();
        return new PageInfo<>(customerInfos);
    }
    @GetMapping("/insert")
    public PageInfo<CustomerInfo> insert(){
        new CustomerInfo().setAddress("ssssss").setCreatedAt(LocalDateTime.now()).insert();
        return new PageInfo<>();
    }

}


