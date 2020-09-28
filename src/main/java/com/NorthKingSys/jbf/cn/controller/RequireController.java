package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.BeanResult;
import com.NorthKingSys.jbf.cn.biz.CustInfo;
import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.domain.JbfRequireInfo;
import com.NorthKingSys.jbf.cn.domain.JbfRequireModel;
import com.NorthKingSys.jbf.cn.mapper.JbfCustInfoMapper;
import com.NorthKingSys.jbf.cn.service.JbfCustInfoService;
import com.NorthKingSys.jbf.cn.service.JbfProdInfoService;
import com.NorthKingSys.jbf.cn.service.RequireService;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * @description 需求信息操作
 */
@RestController
@RequestMapping(value = "/api/require", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//@CacheConfig(cacheNames = "emp")
public class RequireController {
    @Autowired
    RequireService requireService;
    @Autowired
    JbfCustInfoService jbfCustInfoService;
    @Autowired
    JbfProdInfoService jbfProdInfoService;


    private static Logger log = Logger.getLogger(RequireController.class.getClass());

    /**
     * @descition 查询一：查询需求客户信息 （提供给下拉列表）
     */
    @PostMapping("/selectRequire")
//    @Cacheable(cacheNames = {"emp"})
    @ResponseBody
    public BeanResult selectRequireForName(@RequestBody JbfRequireModel jbfRequireModel){

        log.info("查询下拉列表："+jbfRequireModel.toString());
        BeanResult out  = new BeanResult();
        String option = jbfRequireModel.getOption();
        //在需求管理界面 联动查询 客户信息+项目信息 供下拉列表使用
        List<JbfRequireModel> jbfRequireModels1 = new ArrayList<>();
        if(option != null){
            // 查询所有已经录入的客户信息 （有效的）
            if(option.equals("01")){
                List<String> custName = requireService.selectRequireCustName();
                if(custName!= null && custName.size()>0){
                    for (int i = 0; i < custName.size(); i++) {
                        JbfRequireModel jbfRequireModel1 = new JbfRequireModel();
                        jbfRequireModel1.setRequireCust(custName.get(i));
                        jbfRequireModels1.add(jbfRequireModel1);
                    }
                }
                out.setData(jbfRequireModels1);
            }
            // 当时02请求时 根据选择的客户名称查询出 “项目名称”
            if(option.equals("02")){
                if(jbfRequireModel.getRequireCust() != null && !jbfRequireModel.getRequireCust().equals("")){
                    List<String> list = requireService.selectRequireProject(jbfRequireModel.getRequireCust());
                    List<JbfRequireModel> jbfRequireModels2 = new ArrayList<>();
                    if(list!= null && list.size()>0){
                        for (int i = 0; i < list.size(); i++) {
                            JbfRequireModel jbfRequireModel1 = new JbfRequireModel();
                            jbfRequireModel1.setProject(list.get(i));
                            jbfRequireModels2.add(jbfRequireModel1);
                        }
                    }
                    out.setData(jbfRequireModels2);

                }
            }

            // 当option = 03 时，即为录入条件查询 点击搜索按钮时，进行的查询


            if(option.equals("03")){
                Page page =  PageHelper.startPage(jbfRequireModel.getPage(),jbfRequireModel.getSize());
                List<JbfRequireInfo> jbfRequireInfos = requireService.selectRequireList(jbfRequireModel.getRequireCust(),
                                                                jbfRequireModel.getProject(),
                                                                jbfRequireModel.getPosition(),
                                                                jbfRequireModel.getPriority());
                List<JbfRequireModel> jbfRequireModels = new ArrayList<>();
                if(jbfRequireInfos != null && jbfRequireInfos.size()>0){


                    for (JbfRequireInfo jbfRequireInfo : jbfRequireInfos){
                        JbfRequireModel jbfRequireModel1 = new JbfRequireModel();
                        jbfRequireModel1.setNumNo(jbfRequireInfo.getNumNo()); //需求编号
                        jbfRequireModel1.setRequireCust(jbfRequireInfo.getRequireCust());// 需求客户
                        jbfRequireModel1.setProject(jbfRequireInfo.getProject());  // 项目
                        jbfRequireModel1.setPosition(jbfRequireInfo.getPosition()); // 岗位
                        jbfRequireModel1.setRequreNum(jbfRequireInfo.getRequreNum()); // 需求人数
                        jbfRequireModel1.setRequest(jbfRequireInfo.getRequest());   // 要求
                        jbfRequireModel1.setPriority(jbfRequireInfo.getPriority()); // 优先级
                        jbfRequireModel1.setStatus(jbfRequireInfo.getStatus()); // 招聘状态
                        jbfRequireModel1.setTotalpages(page.getPages());
                        jbfRequireModel1.setTotalsize(page.getTotal());
                        jbfRequireModels.add(jbfRequireModel1);
                    }

                }
                out.setData(jbfRequireModels);
                }

        }
        return out;
    }

    /**
     * @descition 在新增需求信息时，需要查询出录入的客户信息 和 项目信息
     */
    @PostMapping("/selectForAdd")
    @ResponseBody
    public BeanResult selectRequireByCustName(@RequestBody JbfRequireModel jbfRequireModel){
        BeanResult out  = new BeanResult();
        String option = jbfRequireModel.getOption();
        List<CustInfo> custInfos = new ArrayList<>();
        if(option != null){
            // 查询所有的客户信息 （有效的）
            if(option.equals("01")){
             List<String> custName = jbfCustInfoService.getJbfCustInfoName();
                if(custName!= null && custName.size()>0){
                    for (int i = 0; i < custName.size(); i++) {
                        CustInfo custInfo = new CustInfo();
                        custInfo.setCustname(custName.get(i));
                        custInfos.add(custInfo);
                    }
                }
                out.setData(custInfos);
             }
            // 当时02请求时 查询项目信息
            if(option.equals("02")){
                List<String> list = jbfProdInfoService.getJbfCustInfoName();
                if(list!= null && list.size()>0){
                    for (int i = 0; i < list.size(); i++) {
                        CustInfo custInfo = new CustInfo();
                        custInfo.setCustname(list.get(i));
                        custInfos.add(custInfo);
                    }
                }
                out.setData(custInfos);
            }
        }
        return out;
    }


    /**
     * @description 添加操作
     */
    @PostMapping("/addRequire")
    public BeanResult addRequire(@RequestBody JbfRequireModel requireInfo){

        String requireNo = getMaxRequireNo();
        JbfRequireInfo requireInfo1 = new JbfRequireInfo();
        //BeanUtils.copyProperties(requireInfo,requireInfo1);
        requireInfo1.setCreatTime(new Date());
        requireInfo1.setNumNo(requireNo); //客户编号
        requireInfo1.setRequireCust(requireInfo.getRequireCust());// 需求客户
        requireInfo1.setProject(requireInfo.getProject());  // 项目
        requireInfo1.setPosition(requireInfo.getPosition()); // 岗位
        requireInfo1.setRequreNum(requireInfo.getRequreNum()); // 需求人数
        requireInfo1.setRequest(requireInfo.getRequest());   // 要求
        requireInfo1.setPriority(requireInfo.getPriority()); // 优先级
        requireInfo1.setStatus(requireInfo.getStatus()); // 招聘状态
        requireInfo1.setRequestStatus("A"); //纪录状态  A-生效  E-失效
        requireService.insertRequireInfo(requireInfo1);
        return new BeanResult();
    }

    /**
     * @descition 更新操作
     */
    @PostMapping("/updateRequire")
    @ResponseBody
    public BeanResult updateRequire(@RequestBody JbfRequireModel requireInfo){
        BeanResult result = new BeanResult();
        String requireNo = requireInfo.getNumNo();//编号
        JbfRequireInfo requireInfo1 = requireService.selectNoRequire(requireNo);
        if(requireInfo1!=null){
            requireInfo1.setRequireCust(requireInfo.getRequireCust());// 需求客户
            requireInfo1.setProject(requireInfo.getProject());  // 项目
            requireInfo1.setPosition(requireInfo.getPosition()); // 岗位
            requireInfo1.setRequreNum(requireInfo.getRequreNum()); // 需求人数
            requireInfo1.setRequest(requireInfo.getRequest());   // 要求
            requireInfo1.setPriority(requireInfo.getPriority()); // 优先级
            requireInfo1.setStatus(requireInfo.getStatus()); // 招聘状态
            requireService.updateRequireInfo(requireInfo1);
        }else{
            result.setCode("400");
            result.setMsg("需求信息不存在");
        }
        return result;
    }

    /**
     * @descition 查询需求客户信息
     */
    @PostMapping("/selectRequireCust")
    @ResponseBody
    public List<String> selectRequireCust(){
        List<String> requireInfoList = requireService.queryRequireCust();
        log.debug("需求客户信息"+requireInfoList.size());
        return requireInfoList;
    }

    /**
     * @descition 删除需求客户信息
     */
    @PostMapping("/deleteRequire")
    @ResponseBody
    public BeanResult deleteRequire(@RequestBody JbfRequireModel requireInfo){
        BeanResult result = new BeanResult();
        String requireNo = requireInfo.getNumNo();//编号
        JbfRequireInfo requireInfo1 = requireService.selectNoRequire(requireNo);
        if(requireInfo1!=null){
            requireInfo1.setRequestStatus("E"); // 纪录状态
            requireService.updateRequireInfo(requireInfo1);
        }else{
            result.setCode("400");
            result.setMsg("需求信息不存在");
        }
        return result;
    }

    public synchronized String  getMaxRequireNo(){

        String maxCustNo = requireService.getMaxRequireNo();
        if(maxCustNo == null){
            return maxCustNo = "100000";
        }else {
            int maxNo1 = Integer.valueOf(maxCustNo);
            maxNo1++;
            maxCustNo = String.valueOf(maxNo1);
            return maxCustNo;
        }
    }

}
