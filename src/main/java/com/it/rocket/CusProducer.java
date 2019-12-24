package com.it.rocket;

import com.it.plus.wcw.entity.CustomerInfo;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author wang_zy
 * @Date 2019/12/19 10:27
 */
@Component
@Order(2)
public class CusProducer {

    @Autowired
    private RocketMQTemplate rocketmqtemplate;

    public void produce(List<CustomerInfo> list){
        String name = "test";
        rocketmqtemplate.convertAndSend("test-topic-1", name);
        rocketmqtemplate.send("cus-topic-2", MessageBuilder.withPayload(list).build());
        System.out.println("发送成功...");
    }

}
