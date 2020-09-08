package com.NorthKingSys.jbf.cn.util;

import com.NorthKingSys.jbf.cn.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


public class JedisUtil {
    private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);
    @Autowired
    @Qualifier("redisTemplate")
   private RedisTemplate redisTemplate;

    //////////////////   下面为 Redis基础操作方法 可根据情况扩展  //////////////////

    /**
     * 添加一个key，无过期时间
     * @param key
     * @param value
     * @author cheng2839
     * @date 2018年11月16日
     */
    public void set(String key, Object value) {
        logger.info("[redis.set:({},{})]", key, value);
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 添加一个key，并设置过期时间
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @author cheng2839
     * @date 2018年11月16日
     */
    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        logger.info("[redis.set:({},{})-({} {})]", key, value, time, timeUnit);
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /**
     * get redis value
     * @param key
     * @return
     * @author cheng2839
     * @date 2018年11月16日
     */
    public Object get(String key) {
        logger.info("[redis.get:({})]", key);
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置key的过期时间
     * @param key
     * @param time
     * @param timeUnit
     * @author cheng2839
     * @date 2018年11月16日
     */
    public void expire(String key, long time, TimeUnit timeUnit) {
        logger.info("[redis.expire:({})-({} {})]", key, time, timeUnit);
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 删除key
     * @param key
     * @author cheng2839
     * @date 2018年11月16日
     */
    public void delete(String key){
        logger.info("[redis.delete:({})]", key);
        redisTemplate.delete(key);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     * @author cheng2839
     * @date 2018年11月16日
     */
    public boolean hasKey(String key) {
        logger.info("[redis.hasKey:({})]", key);
        return redisTemplate.hasKey(key);
    }

    //////////////////   上面为 Redis基础操作方法 可根据情况扩展  //////////////////

}
