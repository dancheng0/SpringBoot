package cn.ws.constants;

/**
 * TODO  订单常量类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/21 0021 16:02
 */
public class OrderConstants {


    /**
     * 订单生成redis-key前缀
     */
    public static final String ORDER_ON = "order-on";
    /**
     * 订单生成redis-key前缀
     */
    public static final String ORDER_OVER_TIME_KEY = "order-over-time:";
    /**
     * 订单有效期（秒）
     */
    // public static final Long ORDER_OVER_TIME = 30L * 60L;
    public static final Long ORDER_OVER_TIME = 10L;
}
