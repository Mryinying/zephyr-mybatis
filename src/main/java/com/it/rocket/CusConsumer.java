package com.it.rocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @Author wang_zy
 * @Date 2019/12/19 16:23
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "cus-topic-2", consumerGroup = "my-consumer_test-topic-2",consumeMode = ConsumeMode.CONCURRENTLY)
public class CusConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener{

    @Override
    public void onMessage(MessageExt messageExt) {
        String msg = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        log.info("-------CusConsumer received:{}", msg);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {

    }
}