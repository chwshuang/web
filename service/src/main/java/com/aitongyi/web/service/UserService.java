package com.aitongyi.web.service;

/**
 * Created by admin on 16/8/8.
 */

import com.aitongyi.web.bean.User;
import com.aitongyi.web.dao.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务接口
 *
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
   @Autowired
   private UserMapper userMapper;


    @Transactional
    public User loadUserByUsername(String username) {
        return userMapper.loadUserByUsername(username);
    }

    @Transactional
    public void saveUser(User user) {
        userMapper.saveUser(user);
//        测试异常后数据是否回滚
//        getError();
    }

    private void getError() {
        int i = 1 / 0;
        logger.info("i:{}" , i);
    }
}
