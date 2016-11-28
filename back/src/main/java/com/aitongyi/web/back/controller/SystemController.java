package com.aitongyi.web.back.controller;

import com.aitongyi.web.back.security.Intercept;
import com.aitongyi.web.bean.User;
import com.aitongyi.web.cache.CacheKey;
import com.aitongyi.web.cache.CacheService;
import com.aitongyi.web.service.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户请求处理器
 * Created by admin on 16/8/6.
 */
@Controller
public class SystemController {
    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;


//    @Intercept(value = "HOME",name = "登录首页")
    @RequestMapping(value = "/getname")
    public String login(){
        return "index/index";
    }


    @Intercept(value = "HOME",name = "登录首页")
    @RequestMapping(value = "/home")
//    @PreAuthorize("isAuthenticated()")// isAuthenticated 如果用户不是匿名用户就返回true
    public String showHomePage() {
        try {
            
            User user = userService.loadUserByUsername("admin");

//            测试缓存服务
//            缓存用户对象到redis,以用户ID区分
            cacheService.set(CacheKey.LOGIN_USER_KEY + user.getId(), JSON.toJSONString(user));
//            从缓存中取出
            String userStr = cacheService.get(CacheKey.LOGIN_USER_KEY + user.getId());
//            进行反序列化
            User u = JSON.parseObject(userStr, User.class);
            if(u != null){
                logger.info("user:{}", u);
            }
            logger.info("load user ");
        }catch (Exception e){
            logger.error(e.getLocalizedMessage(), e);
        }

        return "/index/index";
    }
}
