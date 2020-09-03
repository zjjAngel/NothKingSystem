package com.NorthKingSys.jbf.cn.service;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.biz.UsrPwdInfo;
import com.NorthKingSys.jbf.cn.mapper.SysUsrMngerMapper;
import com.NorthKingSys.jbf.cn.util.SnowflakeIdWorker;
import com.NorthKingSys.jbf.cn.util.SnowflakeIdWorkerUntil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SysUsrMngerService {
    @Autowired
    SysUsrMngerMapper sysUsrMngerMapper;

    @Transactional
    public Object insertUser(Map re){
        //查看用户名 是否被占用
        List<Map> maps = sysUsrMngerMapper.selectUsr(String.valueOf(re.get("username")));
        Optional<List<Map>> mapsOpt = Optional.of(maps);
        if(mapsOpt.isPresent()&&maps!=null&&maps.size()>0){
            throw new BusinessException(-1,"该用户已名已经被占用");
        }
        String usrId= SnowflakeIdWorkerUntil.usrIdNextId();
      int result=  sysUsrMngerMapper.insertUser(String.valueOf(re.get("username")),
                String.valueOf(re.get("backup")),String.valueOf(re.get("transRole")),
                usrId);
        sysUsrMngerMapper.insertPassWord(usrId,
                String.valueOf(re.get("username")),String.valueOf(re.get("password")));
        //刚设置的账号默认三天内有效
        return result;
   }
    @Transactional
   public Object updateUsrInfo(String userId,  String backup,
                               String transRole, String password,String username){
      int result=  sysUsrMngerMapper.updateUsrByUsrId(username,backup,transRole,userId);
        if(result==0){
          throw new BusinessException("该用户不存在");
        }
      sysUsrMngerMapper.updateUsrByPWD(userId,username,password);
      return result;
   }

   @Transactional
   public Object deleteUsrInfo(String userId){
       sysUsrMngerMapper.deletePwdInfo(userId);
     int  res=  sysUsrMngerMapper.deleteUsrInfo(userId);
     if (res==0){
         throw new BusinessException("该用户不存在");
     }
     return res;
   }
   public  Object queryUsrInfo(String userId, String roleId,Integer pageSize,Integer pageNum){
       PageHelper.startPage(pageNum,pageSize);
      List<UsrPwdInfo> lists=  sysUsrMngerMapper.queryUsrInfo(userId,roleId);
       PageInfo<List<UsrPwdInfo>>pageInfo= new PageInfo<>(Collections.singletonList(lists));
       return pageInfo;
   }
   public Object querySelect(){
        return sysUsrMngerMapper.querySelect();
   }
   public Object querySelectUsr(){
        return sysUsrMngerMapper.querySelectUsr();
   }
}
