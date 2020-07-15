package com.muye.kl;

import com.muye.kl.entity.User;
import com.muye.kl.utils.RedisUtil;
import com.muye.kl.utils.RedisUtil1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by ldf on 2018/7/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTest {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 使用redis客户端 直接对数据进行操作
     */
    @Test
    public void test1() {
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis(jedisPool);
            jedis.set("test-key", "ldf-test");
            System.out.println("------------------" + jedis.get("test-key"));
        } finally {
            RedisUtil.closeJedis(jedis);
            System.out.println("-----------" + jedis.isConnected());
        }
    }

    /**
     * 使用模板添加缓存 获取缓存操作
     */
    @Test
    public void test2() {
        User userInfo = User.builder().name("name").id("1").build();
        RedisUtil.addCache("userInfo", userInfo, redisTemplate);
        System.out.println(RedisUtil.getValue(redisTemplate, "userInfo"));
    }

    /**
     * 工具类里直接写死
     */
    @Test
    public void test3(){
        Jedis jedis = RedisUtil1.getJedis();
        jedis.lpush("tian","乔峰", "段誉", "虚竹", "鸠摩智");
        for (String name:jedis.lrange("tian", 0,-1 )) {
            System.out.println(name);
        }
        jedis.del("柜台1");

        jedis.close();
    }

    @Test
    public void getOrderId() {
        String key = "20200715";
        Long delta = null;
        try {
            // delta为空默认值1
            if (null == delta) {
                delta = 1L;
            }
            // 生成14位的时间戳(每秒使用新的时间戳当key)
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            // 获得redis-key
            String newKey = key + ":" + timeStamp;
            // 获取自增值（时间戳+自定义key）
            Long increment = redisTemplate.opsForValue().increment(newKey, delta);
            // 设置时间戳生成的key的有效期为2秒
            redisTemplate.expire(newKey, 2, TimeUnit.SECONDS);
            // 获取订单号，时间戳 + 唯一自增Id( 6位数,不过前方补0)
            System.out.println(timeStamp + String.format("%06d", increment)+"+++++++++++");
//            return timeStamp + String.format("%06d", increment);
        } catch (Exception e) {
            // redis 宕机时采用时间戳加随机数
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            Random random = new Random();
            //14位时间戳到 + 6位随机数
            timeStamp +=(random.nextInt(10)+"") + (random.nextInt(10)+"") + (random.nextInt(10)+"");
            timeStamp +=(random.nextInt(10)+"") + (random.nextInt(10)+"") + (random.nextInt(10)+"");
//            return timeStamp;
            System.out.println(timeStamp+"----------");
        }
    }


}
