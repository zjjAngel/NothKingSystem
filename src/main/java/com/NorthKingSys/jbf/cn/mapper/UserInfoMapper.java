package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);
    int insertUsrPwd(@Param("username")String username,@Param("password")String password);
    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<Map> selectByUsername(@Param("username")String username, @Param("password")String password);
}