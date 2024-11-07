package cn.cola.post;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 帖子模块启动类
 *
 * @author ColaBlack
 */
@SpringBootApplication
@MapperScan("cn.cola.post.mapper")
@ComponentScan(basePackages = {"cn.cola"})
public class PostApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }

}
