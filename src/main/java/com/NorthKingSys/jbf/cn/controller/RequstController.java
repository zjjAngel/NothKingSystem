package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.service.RequstService;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("需求管理")
@RestController
@RequestMapping("/api/NothKingSystem/require")
public class RequstController {
    @Autowired
    private RequstService requstService;

    /**
     *  获取岗位下拉
     * @return
     */
    @GetMapping("/queryPointWhere")
    public Result<?> queryPointWhere(@RequestParam(required = false,value = "require_cust") String require_cust ,
                                     @RequestParam(required = false,value = "num_no")String num_no ,
                                     @RequestParam(required = false,value = "poject")String poject){
       return ResultUtil.success(requstService.queryPointWhere(require_cust,num_no,poject));
    }

    /**
     * 获取优先级
     * @param require_cust
     * @param num_no
     * @param poject
     * @return
     */
    @GetMapping("/queryPriority")
    public Result<?> queryProfile(@RequestParam(required = false,value = "require_cust") String require_cust ,
                                     @RequestParam(required = false,value = "num_no")String num_no ,
                                     @RequestParam(required = false,value = "poject")String poject){
        return ResultUtil.success(requstService.queryProfile(require_cust,num_no,poject));
    }
}
