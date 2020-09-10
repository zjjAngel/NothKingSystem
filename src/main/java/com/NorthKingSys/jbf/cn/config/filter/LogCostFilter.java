package com.NorthKingSys.jbf.cn.config.filter;
import com.NorthKingSys.jbf.cn.domain.UserBack;
import com.NorthKingSys.jbf.cn.util.JedisUtil;
import com.NorthKingSys.jbf.cn.util.SpringContextUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogCostFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(LogCostFilter.class);


    private JedisUtil jedisUtil;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        jedisUtil=(JedisUtil) SpringContextUtil.getBean("jedisUtil");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //前后端不分离的情况
//        Object usr=request.getSession().getAttribute("user");
        String sessionId = request.getHeader("sessionId");
        //存入session的写法
//        for (Cookie cookie : request.getCookies()) {
//                if ("sessionId".equals(cookie.getName())) {
//                    usr = cookie.getValue();
//                    break;
//                }
//        }
        //前后端分离
        if (!StringUtils.isEmpty(sessionId)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String requestURI = request.getRequestURI();
        if (!requestURI.contains("tologin")&&!requestURI.contains("verifyCode")
                &&!requestURI.contains("checkImageCode")) {
            //登出 生效
             if(requestURI.contains("logout")){
//                usr=null;
                jedisUtil.set(String.valueOf(request.getSession().getId()),null);
                request.getSession().setAttribute("user",null);
                loginOut(request,response,null);
            }
//            if(usr==null){
//                loginOrnot( request,  response, null);
//            }
            Object usr=jedisUtil.get("sessionId");
            if (usr instanceof UserBack){
                logger.warn("【登录校验密码是否过期】");
                //无redis的情况下日期的处理
//              Object obj=jedisUtil.get(String.valueOf(request.getSession().getId()));
                UserBack obj= (UserBack) usr;
           if (obj==null){
               loginOrnot( request,  response, null);
             }else {
               filterChain.doFilter(servletRequest, servletResponse);
             }
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
    private boolean loginOrnot(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ServletOutputStream out = response.getOutputStream();
        try {
            JSONObject res = new JSONObject();
            res.put("success", false);
            res.put("message", "用户未登录！");
            out.write(res.toJSONString().getBytes());
            out.flush();
            out.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
    }
    @Override
    public void destroy() {
        System.out.println("destroy");
    }


    private boolean loginOut(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ServletOutputStream out = response.getOutputStream();
        try {
            JSONObject res = new JSONObject();
            res.put("success", false);
            res.put("message", "用户已退出！");
            out.write(res.toJSONString().getBytes());
            out.flush();
            out.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
    }
}
