package cn.cola.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * 帖子模块启动类
 *
 * @author ColaBlack
 */
@SpringBootApplication
@EntityScan(basePackages = {"cn.cola.model.entity"})
@ComponentScan(basePackages = {"cn.cola"})
public class PostApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }

}
