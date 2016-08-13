package com.aitongyi.web.back.controller;

import com.aitongyi.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户请求处理器
 * Created by hushuang on 16/8/6.
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")// isAuthenticated 如果用户不是匿名用户就返回true
    public String showHomePage() {
        try {
            userService.loadUserByUsername("admin");
//            User user = new User();
//            user.setUsername("test"+ new Random().nextInt(123456));
//            user.setPassword("pass");
//            user.setEnabled(true);
//            user.setCreateDate(new Date());
//            userService.saveUser(user);

            logger.info("load user ");
        }catch (Exception e){
            logger.error(e.getLocalizedMessage(), e);
        }

        return "/index/index";
    }
}
