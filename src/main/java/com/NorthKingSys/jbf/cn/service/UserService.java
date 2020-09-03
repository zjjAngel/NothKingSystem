package com.NorthKingSys.jbf.cn.service;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.biz.JBFErrorCode;
import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.domain.UserInfo;
import com.NorthKingSys.jbf.cn.mapper.UserInfoMapper;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

   @Autowired
   UserInfoMapper userInfoMapper;
    /**
     *
     */
    public Result<Object> insertUser(String username, String password){
        return ResultUtil.success(userInfoMapper.insertUsrPwd(username,password));
    }

    public List<Map> selectUser(String username, String password){
       List<Map> res= userInfoMapper.selectByUsername(username,password);
        if(null==res ||  res.size()==0){
        throw new BusinessException(JBFErrorCode.NULL_OBJ);
        }
        return res;
    }

    /**
     * 是否过期
     * @return
     */
    public int perTime(String username, String password){
        List<Map> ls= selectUser(username,password);
        if(ls.size()==1){
         Date date=   new Date(System.currentTimeMillis());
         int h= (int) ls.get(0).get("EFFECT_DATE");
         Date date1=   new Date((Long) ls.get(0).get("LAST_SIGN_TIME"));
         if(date.getHours()-date1.getHours()<h){
               return -1;
         };
        }else {
            throw new BusinessException(-1,"该用户登录数据冗余请联系管理员");
        }
        return 1;
    }
}
