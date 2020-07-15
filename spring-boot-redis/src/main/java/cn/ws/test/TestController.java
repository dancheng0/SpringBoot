package cn.ws.test;

import cn.ws.constants.OrderConstants;
import cn.ws.utils.RedisOrderIDGenerate;
import cn.ws.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO  生成订单号并测试类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/21 0021 16:03
 */
@RestController
public class TestController {

    @Autowired
    private RedisOrderIDGenerate redisOrderIDGenerate;

    @Autowired
    private RedisUtil redisUtil;


    @GetMapping("/test")
    public String test() {
        // 获取订单号
        String orderOn = redisOrderIDGenerate.getOrderId(OrderConstants.ORDER_ON, 1L);
        // 设置订单有效期30秒
        redisUtil.set(OrderConstants.ORDER_OVER_TIME_KEY + orderOn, 0, OrderConstants.ORDER_OVER_TIME);
        return orderOn;
    }
}
