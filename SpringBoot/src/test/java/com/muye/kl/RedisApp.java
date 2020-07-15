package com.muye.kl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Desc:  Redis 直接创建连接池
 *
 * @author : gwh
 * @date : 2019-10-28 11:45
 **/
public class RedisApp {

    private String host = "localhost";

    private int port = 6379;

    private Jedis jedis;

    @Before
    public void setUp(){
        jedis = new Jedis(host, port);
    }

    @Test
    public void test1(){
        jedis.set("info", "nihao");
        Assert.assertEquals("nihao", jedis.get("info"));
    }

    @Test
    public void test2(){
        //创建连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大空闲数
        jedisPoolConfig.setMaxIdle(10);
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(100);
        //设置最长等待时间
        jedisPoolConfig.setMaxWaitMillis(1000);
        //对连接进行有效性检查
        jedisPoolConfig.setTestOnBorrow(true);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port);
        Jedis jedis = jedisPool.getResource();
        Assert.assertEquals("nihao", jedis.get("info"));
    }

    @After
    public void tearDown(){
        jedis.close();
    }
}
