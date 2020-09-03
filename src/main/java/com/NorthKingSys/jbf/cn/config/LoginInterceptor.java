package com.NorthKingSys.jbf.cn.config;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.controller.UserController;
import com.NorthKingSys.jbf.cn.domain.UserBack;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserController userController;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
        return     loginOrnot(request,response,handler);
        }else{
            //赋值超级用户

            return true;
//            //判断用户是否过期
//            Object usr= request.getSession().getAttribute("user");
//           if (usr instanceof UserBack){
//               UserBack userBack= (UserBack) usr;
//              int t= userController.selectUserPerio(userBack.getUsername());
//              if (t==-1){ // 用户过期session失效
//                  request.getSession().setAttribute("user",null);
//                return   loginOrnot(request,response,handler);
//              }else {
//                  return true;
//              }
//           }else {
//              throw new BusinessException(usr,"非平台用户");
//           }
        }

    }

   private boolean loginOrnot(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
       response.setCharacterEncoding("UTF-8");
       response.setContentType("application/json; charset=utf-8");
       PrintWriter out = null;
       try {
           JSONObject res = new JSONObject();
           res.put("success", false);
           res.put("message", "用户未登录！");
           out = response.getWriter();
           out.append(res.toString());
           return false;
       } catch (Exception e) {
           e.printStackTrace();
           response.sendError(500);
           return false;
       }
   }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
