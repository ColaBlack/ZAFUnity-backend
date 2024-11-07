package cn.cola.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@MapperScan("cn.cola.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
