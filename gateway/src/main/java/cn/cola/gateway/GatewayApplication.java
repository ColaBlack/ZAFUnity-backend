package cn.cola.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关项目启动类
 *
 * @author ColaBlack
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.cola"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
