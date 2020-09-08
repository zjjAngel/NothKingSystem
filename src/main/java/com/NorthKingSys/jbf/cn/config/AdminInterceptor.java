package com.NorthKingSys.jbf.cn.config;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.controller.UserController;
import com.NorthKingSys.jbf.cn.domain.UserBack;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    UserController userController;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (request.getSession().getAttribute("user") == null) {
////         boolean notLogin=   loginOrnot(request,response,handler);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//            PrintWriter out = null;
//
//            try {
//                JSONObject res = new JSONObject();
//                res.put("success", false);
//                res.put("message", "用户未登录！");
//                out = response.getWriter();
//                out.append(res.toString());
//                response.sendRedirect("/api/NothKingSystem/goto");
//                return false;
//            } catch (Exception e) {
//                e.printStackTrace();
//                response.sendError(500);
//                return false;
//            }
////         return notLogin;
//        }else{
//            //赋值超级用户
//
//            return true;
////            //判断用户是否过期
////            Object usr= request.getSession().getAttribute("user");
////           if (usr instanceof UserBack){
////               UserBack userBack= (UserBack) usr;
////              int t= userController.selectUserPerio(userBack.getUsername());
////              if (t==-1){ // 用户过期session失效
////                  request.getSession().setAttribute("user",null);
////                return   loginOrnot(request,response,handler);
////              }else {
////                  return true;
////              }
////           }else {
////              throw new BusinessException(usr,"非平台用户");
////           }
//        }
//

//        try {
//            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
//            UserBack user=(UserBack)request.getSession().getAttribute("USER");
//            if(user!=null){
//                return true;
//            }else {
//                loginOrnot(request,response,null);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
    }
//    private boolean loginOut(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        PrintWriter out = null;
//        try {
//            JSONObject res = new JSONObject();
//            res.put("success", false);
//            res.put("message", "用户已退出！");
//            out = response.getWriter();
//            out.append(res.toString());
//            return false;
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(500);
//            return false;
//        }
//    }

}
