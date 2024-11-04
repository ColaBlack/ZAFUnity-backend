package cn.cola.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户模块启动类
 *
 * @author ColaBlack
 */
//@EnableDiscoveryClient
//@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"cn.cola"})
@EntityScan(basePackages = {"cn.cola.service.user.model.entity"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
