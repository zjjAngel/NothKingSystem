package com.NorthKingSys.jbf.cn.project.controller;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.cust.api.AddClient;
import com.NorthKingSys.jbf.cn.domain.JbfProduct;
import com.NorthKingSys.jbf.cn.mapper.JbfProductMapper;
import com.NorthKingSys.jbf.cn.project.api.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auto 朱建雄
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/project", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
public class ProjectInfoController {

    @Autowired
    JbfProductMapper jbfProductMapper;

    /**
     * 新增项目
     * @param projectInfo
     * @return
     */
    @PostMapping("/add")
    private String addProdInfo(ProjectInfo projectInfo){
        String prodno = null;

        prodno = "1";
        JbfProduct jbfProduct = new JbfProduct();
        jbfProduct.setProdNo(prodno);
        jbfProduct.setProdName(projectInfo.getProdname());
        jbfProduct.setProdType(projectInfo.getProdtype());
        jbfProduct.setStartTime(projectInfo.getStarttime());
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
        jbfProduct.setStartTime(projectInfo.getStarttime());
        jbfProduct.setStatus(projectInfo.getStatus());
        jbfProductMapper.updateByPrimaryKeySelective(jbfProduct);
    }

    /**
     * 查询项目
     * @param projectInfo
     * @return
     */
    @PostMapping("/query")
    private ProjectInfo queryProdInfo(ProjectInfo projectInfo){
        ProjectInfo projectInfo1 = new ProjectInfo();
        String startTime= null;
        if(projectInfo.getStarttime()!=null){
             startTime = projectInfo.getStarttime().toString();
        }

        projectInfo1 = jbfProductMapper.getProductInfo(projectInfo.getProdname(),
                projectInfo.getProdtype(),
                startTime,
                projectInfo.getStatus());

        if(projectInfo1!= null){
            return projectInfo1;
        }else {

            throw  new BusinessException("查询无纪录");
        }


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
}
