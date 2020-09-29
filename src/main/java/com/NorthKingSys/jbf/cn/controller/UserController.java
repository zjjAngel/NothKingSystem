package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.biz.JBFErrorCode;
import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.biz.UsrPwdInfo;
import com.NorthKingSys.jbf.cn.config.RedisConfig;
import com.NorthKingSys.jbf.cn.domain.RoleInfo;
import com.NorthKingSys.jbf.cn.domain.UserBack;
import com.NorthKingSys.jbf.cn.domain.UserInfo;
import com.NorthKingSys.jbf.cn.mapper.UserInfoMapper;
import com.NorthKingSys.jbf.cn.service.UserService;
import com.NorthKingSys.jbf.cn.util.JedisUtil;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/NothKingSystem")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @PostMapping("/demo")
   public Result<Object> getTest(){
       return ResultUtil.success(userInfoMapper.selectByPrimaryKey(1));
   }

    /**
     * 登陆界面
     */
    @GetMapping("/goto")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /**
     * 去登录 判断登录成功
     */
    @PostMapping("/tologin")
    public Result index(@RequestBody Map req, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(String.valueOf(req.get("username")))) {
            throw new BusinessException(JBFErrorCode.NULL_OBJ);
        }
        if (StringUtils.isEmpty(String.valueOf(req.get("password")))){
            throw new BusinessException(JBFErrorCode.NULL_OBJ);
        }
        UsrPwdInfo  rslt=null;
        List<Map> res= userService.selectUser(String.valueOf(req.get("username")),String.valueOf(req.get("password")));
        if(null!=res && res.size()>=0){
            if(res.size()==1){
                request.getSession().setAttribute("user", UserBack.build(String.valueOf(req.get("username")),String.valueOf(req.get("password"))));
                Object user_name = res.get(0).get("user_name");
                Object user_role = res.get(0).get("user_Role");
                Object user_id = res.get(0).get("user_id");
                Object effect_date = res.get(0).get("effect_date");
                Object role_name = res.get(0).get("role_name");
//                effect_date
                String sessionId = request.getSession().getId();
                    rslt = UsrPwdInfo.builder().user_name(String.valueOf(user_name))
                        .user_role(String.valueOf(user_role))
                        .user_id(String.valueOf(user_id)).sessionId(sessionId).role_name(String.valueOf(role_name));
                jedisUtil.set(sessionId, JSONObject.toJSON(rslt), Long.valueOf(String.valueOf(effect_date)) ,TimeUnit.HOURS);
            }else {
             throw new BusinessException("非法用户,同一用户名 密码的账号存在两个及其以上");
            }
        }else {
            throw new BusinessException(JBFErrorCode.NULL_OBJ);
        }
       return ResultUtil.success(rslt);
    }
   @GetMapping("/logout")
    public Result<?>  logout(){
       return ResultUtil.success();
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
//    @PostMapping("/registry")
//    public  Result<?> insertUser(String username, String password){
//       return userService.insertUser(username,password);
//    }

    /**
     * 修改密码 根据用户
     * @param req
     * @return
     */
    @PostMapping("/api/insertUser")
    public Result<?>  insertRole(@RequestBody Map req){
      return   userService.insertUser(String.valueOf(req.get("username")),"");
    }
    /**
     * 查看用户是否过期
     */
    @GetMapping
    public int selectUserPerio(String username){
        return   userService.perTime(username,"");
    }
}
