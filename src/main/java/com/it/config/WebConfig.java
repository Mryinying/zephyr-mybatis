package com.it.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author admin
 * @Date 2020/5/22 13:53
 */
@Configuration
public class WebConfig {

    private final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //第一种使用第三方json解析框架
    //重写
    // extends WebMvcConfigurerAdapter
	/*@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//定义一个convert消息转换对象
		super.configureMessageConverters(converters);
		//添加fastjson的配置信息 是否需要格式化json数据
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		converter.setFastJsonConfig(config);
		converters.add(converter);
	}*/
	/**
	 * 第二种使用第三方json解析框架
	 * 用@bean注入FastJsonHttpMessageConverter
	 * @return
	 */
	/*@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters(){
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        config.setCharset(Charset.defaultCharset());
		converter.setFastJsonConfig(config);
		HttpMessageConverter<?> converter2 = converter;
		return new HttpMessageConverters(converter2);
	}*/
    /**
     * @return SpringBoot 内部默认使用的是jackson作为默认的HttpMessageConverter，所以这边只需配置ObjectMapper序列化与反序列化规则即可
     */
    @Bean
    public ObjectMapper objectMapper(JavaTimeModule javaTimeModule) {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(javaTimeModule);
        //===========================================Date时间转换格式===========================================
        objectMapper.setDateFormat(FORMAT);
        //忽略不知道的字段
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //字段属性为null或者字符串为空时不序列化该字段
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }

    @Bean
    public JavaTimeModule javaTimeModule(){
        //设置java8时间类LocalDateTime的格式转换格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //===========================================java8时间类序列化格式===========================================
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME));
        javaTimeModule.addSerializer(YearMonth.class, new YearMonthSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
        //===========================================java8时间类序反列化格式===========================================
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME));
        javaTimeModule.addDeserializer(YearMonth.class, new YearMonthDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));

        return javaTimeModule;
    }
}
