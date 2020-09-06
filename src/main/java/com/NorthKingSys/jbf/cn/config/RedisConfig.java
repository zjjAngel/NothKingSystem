package com.NorthKingSys.jbf.cn.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis配置及基础方法实现封装类
 * @author cheng2839
 * @date 2018年11月16日
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Autowired
    private ConstantConfig config;
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 创建JedisConnectionFactory
     * @return
     * @author cheng2839
     * @date 2018年11月16日
     */
    @Bean(name = "factory")
    public JedisConnectionFactory createFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(config.getRedisHost());
        factory.setPort(config.getRedisPort());
        factory.setPassword(config.getRedisPassword());
        factory.setDatabase(config.getRedisDatabase());
        return factory;
    }

    /**
     * 创建RedisTemplate
     * @param factory
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> createRedisTemplate(JedisConnectionFactory factory) {
        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        //设置序列化/反序列化方式
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


}