package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.BeanResult;
import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.domain.JbfRequireInfo;
import com.NorthKingSys.jbf.cn.domain.JbfRequireModel;
import com.NorthKingSys.jbf.cn.mapper.JbfCustInfoMapper;
import com.NorthKingSys.jbf.cn.service.JbfCustInfoService;
import com.NorthKingSys.jbf.cn.service.JbfProdInfoService;
import com.NorthKingSys.jbf.cn.service.RequireService;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * @description 需求信息操作
 */
@RestController
@RequestMapping(value = "/require", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
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
    @ResponseBody
    public BeanResult selectRequire(@RequestBody JbfRequireModel jbfRequireModel){
        BeanResult out  = new BeanResult();
        String option = jbfRequireModel.getOption();
//        if(option != null){
//            // 查询所有已经录入的客户信息 （有效的）
//            if(option.equals("01")){
//                List<String> custName = requireService.getJbfCustInfoName();
//                out.setData(custName);
//            }
//            // 当时02请求时 查询已经录入的项目信息 （有效的）
//            if(option.equals("02")){
//                List<String> list = requireService.getJbfCustInfoName();
//                out.setData(list);
//            }
//        }
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
        if(option != null){
            // 查询所有的客户信息 （有效的）
            if(option.equals("01")){
             List<String> custName = jbfCustInfoService.getJbfCustInfoName();
                out.setData(custName);
             }
            // 当时02请求时 查询项目信息
            if(option.equals("02")){
                List<String> list = jbfProdInfoService.getJbfCustInfoName();
                out.setData(list);
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
