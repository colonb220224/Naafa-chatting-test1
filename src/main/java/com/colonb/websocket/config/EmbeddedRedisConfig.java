package com.colonb.websocket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {
    private RedisServer redisServer;

    public EmbeddedRedisConfig(@Value("${spring.redis.port}") int port) throws IOException {
        this.redisServer = RedisServer.builder().port(port).setting("maxmemory 128M").build();
    }

    @PostConstruct
    public void startRedis() {
        this.redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        this.redisServer.stop();
    }
}
