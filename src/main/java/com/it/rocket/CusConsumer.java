package com.it.rocket;

import com.google.gson.Gson;
import com.it.plus.wcw.entity.CustomerInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author wang_zy
 * @Date 2019/12/19 16:23
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "cus-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class CusConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener{

    private static ConsumeFromWhere consumeFromWhere;

    @Override
    public void onMessage(MessageExt messageExt) {
        String msg = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        log.info("-------CusConsumer received:{}", msg);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        consumeFromWhere = defaultMQPushConsumer.getConsumeFromWhere();
        defaultMQPushConsumer.getConsumerGroup();

    }
}