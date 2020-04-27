package com.it.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.it.utils.ApplicationContextUtil;
import org.bson.Document;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;

/**
 * @Author admin
 * @Date 2020/4/26 17:35
 */
public class MongoDBAppender extends UnsynchronizedAppenderBase<ILoggingEvent>  implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    protected void append(ILoggingEvent eventObject) {
        MongoTemplate mongoTemplate = ApplicationContextUtil.getBean(MongoTemplate.class);
        final Document doc = new Document();
        doc.append("date", LocalDateTime.now());
        doc.append("level", eventObject.getLevel().toString());
        doc.append("logger", eventObject.getLoggerName());
        doc.append("thread", eventObject.getThreadName());
        doc.append("message", eventObject.getFormattedMessage());
        if (eventObject.getMDCPropertyMap() != null && !eventObject.getMDCPropertyMap().isEmpty()) {
            doc.append("mdc", eventObject.getMDCPropertyMap());
        }
        mongoTemplate.getCollection("zephyr").insertOne(doc);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
