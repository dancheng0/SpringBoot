package com.muye.kl.service.impl;

import com.muye.kl.entity.User;
import com.muye.kl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * '#' 号代表这是一个 SpEL 表达式，此表达式可以遍历方法的参数对象,具体语法可以参考 Spring 的相关文档手册
 *
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final Map<String, User> DATABASES = new HashMap<>();

    static {
        DATABASES.put("1L", new User("1L", "u1", "p1"));
        DATABASES.put("2L", new User("2L", "u2", "p2"));
        DATABASES.put("3L", new User("3L", "u3", "p3"));
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public User saveOrUpdate(User user) {
        DATABASES.put(user.getId(), user);
        log.info("进入 saveOrUpdate 方法");
        return user;
    }

    @Cacheable(value = "user", key = "#id")
    @Override
    public User get(Long id) {
        // TODO 我们就假设它是从数据库读取出来的
        log.info("进入 get 方法");
        return DATABASES.get(id);
    }

    @CacheEvict(value = "user", key = "#id")
    @Override
    public void delete(Long id) {
        DATABASES.remove(id);
        log.info("进入 delete 方法");

    }

    @Override
    public User getUser() {
        // 实际中会根据具体的业务场景，从数据库中查询对应的信息
        return new User("1", "倪升武", "123456");
    }
}