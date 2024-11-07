package cn.cola.minio.config;

import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio配置类
 *
 * @author ColaBlack
 */
@Configuration
public class MinioConfig {

    @Resource
    private MinioProps props;

    /**
     * 获取 Minio客户端对象
     *
     * @return MinioClient
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(props.getEndpoint())
                .credentials(props.getAccessKey(), props.getSecretKey())
                .build();
    }
}

