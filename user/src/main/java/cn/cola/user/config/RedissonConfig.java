package cn.cola.user.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置类
 *
 * @author ColaBlack
 */
@Configuration
public class RedissonConfig {

//    @Value("${spring.redis.password}")
//    private String password;

//    @Value("${spring.redis.host}")
    private String host="localhost";

//    @Value("${spring.redis.port}")
    private int port=6379;

//    @Value("${spring.redis.database}")
    private int database=0;

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer() // 使用单机模式
                // 设置要连接的数据库
                .setDatabase(database)
                // 设置redis密码
//                .setPassword(password)
                // 设置redis服务器地址
                .setAddress("redis://" + host + ":" + port);

        // 创建Redisson客户端
        return Redisson.create(config);
    }
}
