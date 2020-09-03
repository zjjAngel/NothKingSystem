package com.NorthKingSys.jbf.cn.project.controller;

import com.NorthKingSys.jbf.cn.domain.JbfProduct;
import com.NorthKingSys.jbf.cn.mapper.JbfProductMapper;
import com.NorthKingSys.jbf.cn.project.api.ProjectInfo;
import com.NorthKingSys.jbf.cn.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auto 朱建雄
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/project", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
public class ProjectInfoController {

    @Autowired
    JbfProductMapper jbfProductMapper;
    @Autowired
    DateUtils dateUtils;

    /**
     * 新增项目
     * @param projectInfo
     * @return
     */
    @PostMapping("/add")
    private String addProdInfo(ProjectInfo projectInfo){
        String prodno = null;
        prodno = getMaxCustNo();
        int maxNo1 = Integer.valueOf(prodno);
        maxNo1++;
        prodno = String.valueOf(maxNo1);
        JbfProduct jbfProduct = new JbfProduct();
        jbfProduct.setProdNo(prodno);
        jbfProduct.setProdName(projectInfo.getProdname());
        jbfProduct.setProdType(projectInfo.getProdtype());
        jbfProduct.setStartTime(dateUtils.parse(projectInfo.getStarttime()));
        jbfProduct.setStatus(projectInfo.getStatus());
        jbfProductMapper.insertSelective(jbfProduct);

        return prodno;
    }

    /**
     * 修改项目
     * @param projectInfo
     * @return
     */
    @PostMapping("/upd")
    private void updProdInfo(ProjectInfo projectInfo){
        JbfProduct jbfProduct = new JbfProduct();
        jbfProduct.setProdNo(projectInfo.getProdno());
        jbfProduct.setProdName(projectInfo.getProdname());
        jbfProduct.setProdType(projectInfo.getProdtype());
        jbfProduct.setStartTime(dateUtils.parse(projectInfo.getStarttime()));
        jbfProduct.setStatus(projectInfo.getStatus());
        jbfProductMapper.updateByPrimaryKeySelective(jbfProduct);
    }

    /**
     * 查询项目
     * @param projectInfo
     * @return
     */
    @PostMapping("/query")
    private List<ProjectInfo> queryProdInfo(ProjectInfo projectInfo){
        List<ProjectInfo> projectInfoss = new ArrayList<>();

        String startTime= null;
        if(projectInfo.getStarttime() != null && !projectInfo.getStarttime().equals("")){
            startTime = projectInfo.getStarttime();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("prodname",projectInfo.getProdname());
        map.put("prodtype",projectInfo.getProdtype());
        map.put("starttime",startTime);
        map.put("status",projectInfo.getStatus());

        List<ProjectInfo> projectInfos = jbfProductMapper.getProductInfo(map);

        if(projectInfos != null && projectInfos.size()>0){
            for (ProjectInfo projectInfo1 : projectInfos) {
                ProjectInfo projectInfo2 = new ProjectInfo();
                projectInfo2.setProdno(projectInfo1.getProdno());
                projectInfo2.setProdname(projectInfo1.getProdname());
                projectInfo2.setProdtype(projectInfo1.getProdtype());
                projectInfo2.setStarttime(projectInfo1.getStarttime());
                projectInfo2.setStatus(projectInfo1.getStatus());
                projectInfoss.add(projectInfo2);
            }
        }
        return projectInfoss;

    }

    /**
     * 删除项目
     * @param projectInfo
     * @return
     */
    @PostMapping("/del")
    private void delProdInfo(ProjectInfo projectInfo){
        jbfProductMapper.deleteByPrimaryKey(projectInfo.getProdno());
    }


    private  synchronized String  getMaxCustNo(){

        String maxCustNo = jbfProductMapper.getMaxCustNo();
        if(maxCustNo == null){
            return maxCustNo = "100000";
        }else {
            return maxCustNo;
        }
    }

}
