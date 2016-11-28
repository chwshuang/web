package com.aitongyi.web.back.controller;

import com.aitongyi.web.back.security.Intercept;
import com.aitongyi.web.bean.RequestData;
import com.aitongyi.web.bean.Result;
import com.aitongyi.web.bean.User;
import com.aitongyi.web.cache.CacheKey;
import com.aitongyi.web.cache.CacheService;
import com.aitongyi.web.service.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户请求处理器
 * Created by admin on 16/8/6.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserList", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getUserList(@RequestParam("currentPage") Integer currentPage,
                              @RequestParam("itemsPerPage") Integer itemsPerPage,
                              HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(currentPage == null){
           currentPage = 1;
       }
       if(itemsPerPage == null){
           itemsPerPage = 10;
       }
        int offsize = (currentPage - 1) * itemsPerPage;
        Integer total = userService.getUserListCount();
        List<User> list = userService.getUserList(offsize, itemsPerPage);
        Result result = new Result();
        result.setTotal(total);
        result.setData(list);
        return JSON.toJSONString(result);
    }

}
