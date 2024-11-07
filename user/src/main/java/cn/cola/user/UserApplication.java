package cn.cola.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户模块启动类
 *
 * @author ColaBlack
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.cola"})
@MapperScan("cn.cola.user.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.cola.service"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
