package cn.ws.config;


import cn.ws.constants.OrderConstants;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        if (expiredKey.indexOf(OrderConstants.ORDER_OVER_TIME_KEY) != -1) {
            String[] orderOn = expiredKey.split(":");
            System.out.println("订单号: " + orderOn[1] + "超时");
        }
        System.out.println(expiredKey);
    }
}
