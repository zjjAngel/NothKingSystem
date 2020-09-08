//package com.NorthKingSys.jbf.cn.config.api;
//
//import com.NorthKingSys.jbf.cn.config.LoginAspect;
//import com.NorthKingSys.jbf.cn.controller.UserController;
//import com.NorthKingSys.jbf.cn.domain.UserBack;
//import com.alibaba.fastjson.JSONObject;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@Aspect
//@Component
//public class LoginAspectApi {
//
//    @Autowired
//    UserController userController;
//    private Logger logger = LoggerFactory.getLogger(LoginAspect.class);
////    @Autowired
////    private RedisTemplate redisTemplate;
//
//    //切入点,对登录方法不进行切入
//    @Pointcut("execution(* com.NorthKingSys.jbf.cn.cust.controller.*.*(..))"//登录方法
//    )//跳转登录页
//    public void loing1(){
//    }
//
//    //切入点,对登录方法不进行切入
//    @Pointcut("execution(* com.NorthKingSys.jbf.cn.controller.project.controller.*.*(..))"//登录方法
//    )//跳转登录页
//    public void loing2(){
//    }
//
//    /**
//     * 前置增强,校验redis中用户的信息
//     */
//    @Before("loing1()")
//    public void before() throws IOException {
//        //1、得到request对象
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        HttpServletResponse response= attributes.getResponse();
//        //2、获取客户端保存的sessionId
//        Object usr=request.getSession().getAttribute("user");
//        if(usr==null){
//            loginOrnot( request,  response, null);
//        }
//        if (usr instanceof UserBack){
//            logger.warn("【登录校验密码是否过期】");
//
//        }
//        //3、判断cookie是否存在
////        if(cookie==null){
////            logger.warn("【登录校验】Cookie中查询不到token");
////            throw new AuthorizeException();
////        }
//        //4、从Redis中查询token是否存在
////        Object token = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN,cookie.getValue()));
////        if(token==null){
////            logger.warn("【登录校验】Redis中查不到token");
////            throw new AuthorizeException();
////        }
////        System.out.println(token);
//    }
//    @Before("loing2()")
//    public void loing2before() throws IOException {
//        //1、得到request对象
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        HttpServletResponse response= attributes.getResponse();
//        //2、获取客户端保存的sessionId
//        Object usr=request.getSession().getAttribute("user");
//        if(usr==null){
//            loginOrnot( request,  response, null);
//        }
//        if (usr instanceof UserBack){
//            logger.warn("【登录校验密码是否过期】");
//
//        }
//    }
//
//    private boolean loginOrnot(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        ServletOutputStream out = response.getOutputStream();
//        try {
//            JSONObject res = new JSONObject();
//            res.put("success", false);
//            res.put("message", "用户未登录！");
//            out.write(res.toJSONString().getBytes());
//            out.flush();
//            out.close();
//            return false;
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(500);
//            return false;
//        }
//    }
//}
