package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.biz.UsrPwdInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUsrMngerMapper {
   List<Map> selectUsr(@Param("username")String username);
   int insertUser(@Param("username")String username,@Param("backup")String backup,
                  @Param("transRole")String  transRole,@Param("userId")String userId
                  );
   int insertPassWord(@Param("userId")String userId,@Param("username")String username,
                      @Param("password")String password);
   int updateUsrByUsrId(@Param("username")String username,@Param("backup")String backup,
                        @Param("transRole")String  transRole,@Param("userId")String userId);
    int updateUsrByPWD(@Param("userId")String userId,@Param("username")String username,
                       @Param("password")String password);
    int deleteUsrInfo(@Param("userId")String userId);
    int deletePwdInfo(@Param("userId")String userId);
    List<UsrPwdInfo> queryUsrInfo(@Param("userId")String userId, @Param("roleId")String roleId);
    List<UsrPwdInfo> querySelect();
    List<UsrPwdInfo> querySelectUsr();
    int deleteUsr(@Param("list")List<String> userId);
}
