package com.ms.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author Eshan
 * for more information (jdis): https://www.baeldung.com/jedis-java-redis-client-library
 * for more information (Redis): https://www.baeldung.com/spring-data-redis-tutorial
 */
@Configuration
public class RedisConfiguration {

    @Autowired
    private Environment env;

    private String springRedisHost="";
    private String springRedisPort="";

    @PostConstruct
    private void init() {
        springRedisHost = env.getProperty("spring.redis.host") == null ? "" : env.getProperty("spring.redis.host");
        springRedisPort = env.getProperty("spring.redis.port") == null ? "" : env.getProperty("spring.redis.port");
    }

    @Bean
    RedisConnectionFactory redisConnectionFactory() {
        final JedisConnectionFactory connectionFactory = new JedisConnectionFactory(buildPoolConfig());
        connectionFactory.setUsePool(true);
        connectionFactory.setHostName(springRedisHost);
        connectionFactory.setPort(Integer.parseInt(springRedisPort));

        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    public JedisPool jedisPool() {
        final JedisPoolConfig poolConfig = buildPoolConfig();
        return new JedisPool(poolConfig, env.getProperty("spring.redis.host"));
    }

    private JedisPoolConfig buildPoolConfig() {

        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);

        return poolConfig;
    }
}
