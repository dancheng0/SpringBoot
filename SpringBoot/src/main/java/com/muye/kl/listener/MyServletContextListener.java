package com.muye.kl.listener;

import com.muye.kl.entity.User;
import com.muye.kl.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * 使用 ApplicationListener 来初始化一些数据到 application 域中的监听器
 *
 * @author shengni ni
 * @date 2018/07/05
 */
@Component
public class MyServletContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 先获取到 application 上下文
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        // 获取对应的 service
        UserService userService = applicationContext.getBean(UserService.class);
        User user = userService.getUser();
        // 获取 application 域对象，将查到的信息放到 application 域中
        ServletContext application = applicationContext.getBean(ServletContext.class);
        application.setAttribute("user", user);
    }
}
