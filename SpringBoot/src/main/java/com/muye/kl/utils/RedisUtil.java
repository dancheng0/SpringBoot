package com.muye.kl.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.Set;

/**
 * Created by ldf on 2018/6/26.
 */
public class RedisUtil {

    public static final String KEY_PREFIX = "tjs-redis";

    /**
     * 获取reids连接
     *
     * @param ip
     * @param port
     * @return
     */
    public static Jedis getJedis(String ip, int port) {
        return new Jedis(ip, port);
    }

    /**
     * 获取redis连接
     *
     * @param jedisPool
     * @return
     */
    public static Jedis getJedis(JedisPool jedisPool) {
        return jedisPool.getResource();
    }

    /**
     * 关闭redis连接
     *
     * @param jedis
     */
    public static void closeJedis(Jedis jedis) {
        if (jedis != null && !jedis.isConnected()) jedis.close();
    }

    /**
     * 生成缓存的key值
     *
     * @param className
     * @param methodName
     * @param args
     * @return
     */
    public static String generateKey(String className, String methodName, Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append(className);
        sb.append("_" + methodName);
        for (Object o1 : args) {
            sb.append("_" + o1);
        }
        return sb.toString();
    }

    /**
     * 添加缓存
     *
     * @param key
     * @param obj
     * @param redisTemplate
     */
    public static void addCache(String key, Object obj, RedisTemplate redisTemplate) {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set(key, obj);
    }

    /**
     * 移除缓存
     *
     * @param key
     * @param template
     */
    public static void removeCache(String key, RedisTemplate template) {
        template.delete(key);
    }

    /**
     * 批量移除缓存
     *
     * @param keys
     * @param template
     */
    public static void removeCacheBatch(Collections keys, RedisTemplate template) {
        template.delete(keys);
    }

    /**
     * 批量移除缓存
     *
     * @param keys
     * @param template
     */
    public static void removeCacheBatch(Set keys, RedisTemplate template) {
        template.delete(keys);
    }

    /**
     * 获取redis中的全部key值 支持正则
     *
     * @param jedis
     */
    public static Set getKeys(Jedis jedis, String expre) {
        return jedis.keys(expre);
    }

    /**
     * 获取redis中的数据
     *
     * @param jedis
     * @param key
     */
    public static String getStringValue(Jedis jedis, String key) {

        return jedis.get(key);
    }


    /**
     * redis key对应的value 自增
     *
     * @param jedis
     * @param key
     * @return
     */
    public static long incrStringkey(Jedis jedis, String key) {

        return jedis.incr(key);
    }

    /**
     * 获取redis中的数据
     *
     * @param template
     * @param key
     */
    public static Object getValue(RedisTemplate template, String key) {
        ValueOperations operations = template.opsForValue();
        return operations.get(key);
    }

    /**
     * 移除缓存
     *
     * @param expre
     */
    public static void removeCache(String expre, Jedis jedis, RedisTemplate redisTemplate) {
        try {
            Set keys = RedisUtil.getKeys(jedis, expre);
            if (!CheckParamUtil.collectionIsEmpty(keys)) RedisUtil.removeCacheBatch(keys, redisTemplate);
        } finally {
            if (jedis != null) RedisUtil.closeJedis(jedis);
        }
    }


}
