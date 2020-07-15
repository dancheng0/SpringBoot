package com.muye.kl.controller;

import com.muye.kl.entity.User;
import com.muye.kl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : gwh
 * @Desc:
 * @date : 2020-07-02 10:12
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getTest")
    public void test() {
        final User user = userService.saveOrUpdate(new User("5L", "u5", "p5"));
        log.info("[saveOrUpdate] - [{}]", user);
        final User user1 = userService.get(5L);
        log.info("[get] - [{}]", user1);
        final User user2 = userService.get(5L);
        log.info("[get] - [{}]", user2);
        userService.delete(5L);
    }
}
