package cn.cola.post;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 帖子模块启动类
 *
 * @author ColaBlack
 */
@SpringBootApplication
@MapperScan("cn.cola.post.mapper")
@ComponentScan(basePackages = {"cn.cola"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.cola.service"})
public class PostApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }

}
