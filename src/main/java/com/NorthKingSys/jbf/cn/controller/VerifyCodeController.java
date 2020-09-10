package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.VerifyCode;
import com.NorthKingSys.jbf.cn.service.SimpleCharVerifyCodeServiceImpl;
import com.NorthKingSys.jbf.cn.service.interfac.IVerifyCodeGen;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RestController
@RequestMapping("/api/NothKingSystem")
public class VerifyCodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCharVerifyCodeServiceImpl.class);
    @ApiOperation(value = "验证码")
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeServiceImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            LOGGER.info(code);
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            LOGGER.info("", e);
        }
    }


    //验证码验证
    @RequestMapping(value = "/login/checkImageCode")
    @ResponseBody
    public String checkTcode(HttpServletRequest request,HttpServletResponse response) {
        String validateCode = request.getParameter("validateCode");
        String code = null;
        //1:获取cookie里面的验证码信息
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("imagecode".equals(cookie.getName())) {
                code = cookie.getValue();
                break;
            }
        }
        //1:获取session验证码的信息
        //String code1 = (String) request.getSession().getAttribute("");
        //2:判断验证码是否正确
        if(!StringUtils.isEmpty(validateCode)){
//            全部转化为小写
            validateCode = validateCode.toLowerCase();
            code = code.toLowerCase();
            if(validateCode.equals(code)) {
                return "ok";
            }
        }
        return "error";
    }
}
