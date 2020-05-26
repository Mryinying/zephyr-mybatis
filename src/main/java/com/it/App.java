package com.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author wang_zy
 * @Date 2019/12/17 10:42
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.setProperty("local_ip",  getLocalIp() );
        SpringApplication.run(App.class, args);
    }

    /**
     * 获得本机的内网地址（String）
     */
    private static String getLocalIp(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown-ip";
        }
    }

    /**
     * 设置 "local-ip" system 变量，给log4j2 配置使用：
     */
    @PostConstruct
    public void postConstruct(){
        String localIp = getLocalIp();
        System.out.println("LOCAL_IP for Log4j2: " + localIp);
        System.setProperty("LOCAL_IP", localIp );
    }
}
