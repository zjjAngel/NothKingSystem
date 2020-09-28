package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.BeanResult;
import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.domain.JbfProduct;
import com.NorthKingSys.jbf.cn.mapper.JbfProductMapper;
import com.NorthKingSys.jbf.cn.biz.ProjectInfo;
import com.NorthKingSys.jbf.cn.util.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auto 朱建雄
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/project", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
public class ProjectInfoController {
    private static Logger log = Logger.getLogger(ProjectInfoController.class.getClass());
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
    public BeanResult addProdInfo(@RequestBody ProjectInfo projectInfo){
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
        jbfProduct.setProdStatus("A");
        jbfProductMapper.insertSelective(jbfProduct);
        projectInfo.setProdno(prodno);
        return new BeanResult(projectInfo);
    }

    /**
     * 修改项目
     * @param projectInfo
     * @return
     */
    @PostMapping("/upd")
    public BeanResult updProdInfo(@RequestBody  ProjectInfo projectInfo){
        JbfProduct jbfProduct = new JbfProduct();
        jbfProduct.setProdNo(projectInfo.getProdno());
        jbfProduct.setProdName(projectInfo.getProdname());
        jbfProduct.setProdType(projectInfo.getProdtype());
        log.info("projectInfo==="+projectInfo.toString());
        jbfProduct.setStartTime(dateUtils.parse(projectInfo.getStarttime()));
        jbfProduct.setStatus(projectInfo.getStatus());
        log.info("jbfProduct==="+jbfProduct.getStartTime());
        jbfProductMapper.updateByPrimaryKeySelective(jbfProduct);

        return new BeanResult(projectInfo);
    }

    /**
     * 查询项目
     * @param projectInfo
     * @return  List<ProjectInfo>
     */
    @PostMapping("/query")
    public BeanResult queryProdInfo(@RequestBody ProjectInfo projectInfo){


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

        Page page =PageHelper.startPage(projectInfo.getPage(),projectInfo.getSize());
        List<JbfProduct> JbfProducts = jbfProductMapper.getProductInfo(map);
        int pages =  page.getPages(); // 总页数
        long total =  page.getTotal(); // 总条数

        if(JbfProducts != null && JbfProducts.size()>0){
            for (JbfProduct JbfProduct1 : JbfProducts) {
                ProjectInfo projectInfo2 = new ProjectInfo();
                projectInfo2.setProdno(JbfProduct1.getProdNo());
                projectInfo2.setProdname(JbfProduct1.getProdName());
                projectInfo2.setProdtype(JbfProduct1.getProdType());
                projectInfo2.setStarttime(dateUtils.format(JbfProduct1.getStartTime()));
                projectInfo2.setStatus(JbfProduct1.getStatus());
                projectInfo2.setPage(projectInfo.getPage());
                projectInfo2.setSize(page.getPageNum());
                projectInfo2.setTotalpages(pages);
                projectInfo2.setTotalsize(total);
                projectInfoss.add(projectInfo2);
            }

        }
//        if(projectInfoss != null && projectInfoss.size()>0){
//            result.setData(projectInfoss);
//            result.setCode(0000);
//            result.setMsg("SUCCESS");
//        }else {
//            result.setCode(4000);
//            result.setMsg("没有纪录");
//        }

        return new BeanResult(projectInfoss);
    }

    /**
     * 删除项目
     * @param projectInfo
     * @return
     */
    @PostMapping("/del")
    public BeanResult delProdInfo(@RequestBody  ProjectInfo projectInfo){

        JbfProduct jbfProduct = new JbfProduct();
        jbfProduct.setProdNo(projectInfo.getProdno());
        jbfProduct.setProdStatus("E");
        //jbfProductMapper.deleteByPrimaryKey(projectInfo.getProdno());
        jbfProductMapper.updateByPrimaryKeySelective(jbfProduct);

        return new BeanResult();
    }


    public  synchronized String  getMaxCustNo(){

        String maxCustNo = jbfProductMapper.getMaxCustNo();
        if(maxCustNo == null){
            return maxCustNo = "100000";
        }else {
            return maxCustNo;
        }
    }

}
