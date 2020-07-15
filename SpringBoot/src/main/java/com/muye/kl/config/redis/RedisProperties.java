package com.muye.kl.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by ldf on 2018/7/9.
 */
@Configuration
@Data
@PropertySource(value = "classpath:tjs-redis-dev.properties")
@ConfigurationProperties(prefix = "redis.server")
public class RedisProperties {
    private String host;
    private int port;
    private String password;
    private int maxActive;
    private int timeOut;
    private int minIdle;
    private int maxIdle;
    private int maxWaitMillis;
    private int maxTotal;
    private int cacheInvalid;
    private int cacheInvalid2;
    private int cacheInvalid3;

}
