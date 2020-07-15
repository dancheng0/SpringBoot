package com.muye.kl.controller;

import com.muye.kl.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


/**
 *  测试用例：http://localhost:8081/listener/user
 *  如果正常返回 User信息 ，说明数据缓存成功
 */
@RestController
@RequestMapping("/listener")
public class ListenerController {

    @GetMapping("/user")
    public User getUser(HttpServletRequest request) {
        ServletContext application = request.getServletContext();
        return (User) application.getAttribute("user");
    }

}
