package cn.cola.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 服务客户端启动类
 *
 * @author ColaBlack
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.cola.service"})
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
