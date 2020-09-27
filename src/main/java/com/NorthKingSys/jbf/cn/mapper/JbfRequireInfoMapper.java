package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfRequireInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface JbfRequireInfoMapper {
    int updateStatusByNumber(Map map);

    int insert(JbfRequireInfo record);

    int insertSelective(JbfRequireInfo record);

    JbfRequireInfo selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(JbfRequireInfo record);

    int updateByPrimaryKey(JbfRequireInfo record);

    List<String> selectRequireCustInfo();

    List<JbfRequireInfo> selectRequireList(Map map);

    List<String> selectRequireByCustName(Map map);

    String getMaxRequireNo();

    List<String> selectRequireCustName();

    List<String> selectRequireProject(String requireCust);

    //List<JbfRequireInfo> getJbfRequireInfo(Map map);
}