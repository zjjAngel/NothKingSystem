package com.NorthKingSys.jbf.cn.service;

import com.NorthKingSys.jbf.cn.domain.JbfRequireInfo;
import com.NorthKingSys.jbf.cn.mapper.JbfRequireInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequireService {
    @Autowired
    JbfRequireInfoMapper jbfRequireInfoMapper;
    private static Logger log = Logger.getLogger(RequireService.class.getClass());
    public void insertRequireInfo(JbfRequireInfo requireInfo){
        log.debug("requireInfo====="+requireInfo);
        jbfRequireInfoMapper.insertSelective(requireInfo);
    }

    public PageInfo queryRequireInfo(JbfRequireInfo requireInfo){
        PageHelper.startPage(requireInfo.getPageNum(),requireInfo.getPageSize());
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("requreNum",requireInfo.getNumber());
        map.put("position",requireInfo.getPosition());
        map.put("project",requireInfo.getProject());
        map.put("requireCust",requireInfo.getRequireCust());
        /*map.put("startIndex",requireInfo.getPageIndex());
        map.put("endIndex",requireInfo.getPageIndex()+);*/
        List<JbfRequireInfo> requireInfoList= jbfRequireInfoMapper.selectRequireList(map);
        PageInfo<JbfRequireInfo> pageInfo= new PageInfo<>(requireInfoList);
        return pageInfo;
    }

    public List<String> queryRequireCust(){
        return jbfRequireInfoMapper.selectRequireCustInfo();
    }

    public int updateRequireInfo(JbfRequireInfo requireInfo){
        return jbfRequireInfoMapper.updateByPrimaryKeySelective(requireInfo);
    }

    public JbfRequireInfo selectNoRequire(String number){
        return jbfRequireInfoMapper.selectByPrimaryKey(number);
    }

    public int deleteByNumber(String number){
        return jbfRequireInfoMapper.deleteByPrimaryKey(number);
    }

    public List<String> selectRequireByCustName(String custName){
        Map map = new HashMap();
        map.put("custName",custName);
        return jbfRequireInfoMapper.selectRequireByCustName(map);
    }
}
