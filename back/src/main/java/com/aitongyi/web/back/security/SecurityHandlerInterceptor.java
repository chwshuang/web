package com.aitongyi.web.back.security;

import com.aitongyi.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Set;

/**
 * 安全处理拦截器
 * Created by admin on 16/9/1.
 */
public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityHandlerInterceptor.class);
    private UserService userService;


//    private LoginService loginService;

    public SecurityHandlerInterceptor(UserService userService){
        this.userService = userService;
    }

    public static final String SESSION_USERID = "kUSERID";
    public static final String SESSION_AUTHS = "kAUTHS";
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

//        User user = new User();
//        user.setUsername(UUID.randomUUID().toString());
//        user.setPassword("aaa");
//        user.setEnabled(false);
//        user.setCreateDate(new Date());
//        userService.saveUser(user);
        boolean flag = true;
        logger.info("method:{}, RequestURI:{}, RequestURL:{}, QueryString:{}, instanceof Method:{}", new Object[]{request.getMethod().toString(), request.getRequestURI().toString(), request.getRequestURL().toString(), request.getQueryString(), handler instanceof HandlerMethod});

        if (handler instanceof HandlerMethod) {
            Intercept auth = ((HandlerMethod) handler).getMethod().getAnnotation(Intercept.class);
            if (auth != null) {// 有权限控制的就要检查
                HttpSession session = request.getSession();
//                if (request.getSession().getAttribute(SESSION_USERID) == null) {// 没登录就要求登录
//                    response.setStatus(HttpStatus.FORBIDDEN.value());
//                    response.setContentType("text/html; charset=UTF-8");
//                    PrintWriter out=response.getWriter();
//                    out.write("{\"type\":\"nosignin\",\"msg\":\"请您先登录!\"}");
//                    out.flush();
//                    out.close();
//                    flag = false;
//                } else {// 登录了检查,方法上只是@Auth,表示只要求登录就能通过.@Auth("authority")这类型,验证用户权限
                    if (!"".equals(auth.value())) {
                        Set<String> auths = (Set<String>) request.getSession().getAttribute(SESSION_AUTHS);
                        if (auths == null || !auths.contains(auth.value())) {// 提示用户没权限
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType("text/html; charset=UTF-8");
                            PrintWriter out = response.getWriter();
                            out.write("{\"type\":\"noauth\",\"msg\":\"您没有"+auth.name()+"权限!\"}");
                            out.flush();
                            out.close();
                            flag = false;
                        }
                    }
//                }
            }else{
                if(request.getMethod().equals("POST") && request.getRequestURI().equals("/login")){
//                    登录
//                    loginService.login();
                }
            }
        }
        return flag;
    }


//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("===========HandlerInterceptor1 preHandle");
//        return true;
//    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("===========HandlerInterceptor1 postHandle");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("===========HandlerInterceptor1 afterCompletion");
    }
}