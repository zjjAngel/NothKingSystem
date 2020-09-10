package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.domain.JbfRequireInfo;
import com.NorthKingSys.jbf.cn.domain.JbfRequireModel;
import com.NorthKingSys.jbf.cn.service.RequireService;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/*
 * @description 需求信息操作
 */
@RestController
@RequestMapping(value = "/require", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
public class RequireController {
    @Autowired
    RequireService requireService;

    private static Logger log = Logger.getLogger(RequireController.class.getClass());
    /**
     * @description 添加操作
     */
    @PostMapping("/addRequire")
    public Result addRequire(@RequestBody JbfRequireModel requireInfo){
        Result result = new Result();
        int intFlag = (int)(Math.random() * 1000000);
        String requireNo = String.valueOf(intFlag);
        JbfRequireInfo requireInfo1 = new JbfRequireInfo();
        BeanUtils.copyProperties(requireInfo,requireInfo1);
        requireInfo1.setCreatTime(new Date());
        requireInfo1.setNumber(requireNo);
        requireInfo1.setStatus("0");//状态是"0"表示正常
        requireService.insertRequireInfo(requireInfo1);
        result.setCode(200);
        result.setData(requireNo);
        return result;
    }

    /**
     * @descition 更新操作
     */
    @PostMapping("/updateRequire")
    @ResponseBody
    public Result updateRequire(@RequestBody JbfRequireInfo requireInfo){
        Result result = new Result();
        String requireNo = requireInfo.getNumber();//编号
        JbfRequireInfo requireInfo1 = requireService.selectNoRequire(requireNo);
        if(requireInfo1!=null){
            requireInfo.setCreatTime(new Date());
            requireService.updateRequireInfo(requireInfo);
            result.setCode(200);
        }else{
            result.setCode(400);
            result.setMsg("更新信息不存在");
        }
        return result;
    }

    /**
     * @descition 查询数据操作
     */
    @PostMapping("/selectRequire")
    @ResponseBody
    public Result<?> selectRequire(@RequestBody JbfRequireInfo requireInfo){
        return ResultUtil.success(requireService.queryRequireInfo(requireInfo));
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
    public Result deleteRequire(@RequestBody JbfRequireInfo requireInfo){
        Result result = new Result();
        //状态是"1"表示删除
        int i = requireService.deleteByNumber(requireInfo.getNumber(),"1");
        return result;
    }

    /**
     * @descition 根据客户查询需求客户信息
     */
    @PostMapping("/selectRequireByCustName")
    @ResponseBody
    public Result selectRequireByCustName(@RequestBody JbfRequireInfo requireInfo){
        Result result = new Result();
        List<String> RequireList= requireService.selectRequireByCustName(requireInfo.getRequireCust());
        result.setCode(200);
        if(RequireList.size()>0){
            result.setData(RequireList);
        }
        return result;
    }
}
