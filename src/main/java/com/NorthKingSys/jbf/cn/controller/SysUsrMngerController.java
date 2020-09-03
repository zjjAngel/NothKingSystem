package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.service.SysUsrMngerService;
import com.NorthKingSys.jbf.cn.util.ResultUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *系统管理
 *    用户管理
 */
@RestController
@RequestMapping("/api/NothKingSystem/SysUsrMnger")
public class SysUsrMngerController {
    @Autowired
    private SysUsrMngerService sysUsrMngerService;

    /**
     * 用户管理
     *  新增用户
     * @param re
     * @return
     */
    @PostMapping("/insertUsrInfo")
    public Result<?> insertInfo(@RequestBody Map re){
     return ResultUtil.success(sysUsrMngerService.insertUser(re));
    }

    /**
     * 用户管理
     * 更新用户
     * @param userId
     * @param backup
     * @param transRole
     * @param password
     * @param username
     * @return
     */
    @PutMapping("/updateUsrInfo")
    public Result<?>  updateUsrInfo(@RequestParam("userId") String userId,
                                    @RequestParam("backup") String backup,
                                    @RequestParam("transRole") String transRole,
                                    @RequestParam("password") String password,
                                    @RequestParam("username") String username
                                    ){
        return ResultUtil.success(sysUsrMngerService.updateUsrInfo(userId,backup,transRole,password,username));
    }

    @DeleteMapping("/deleteUsrInfo")
    public Result<?>  deleteUsrInfo(@RequestParam("userId") String userId){
        return ResultUtil.success(sysUsrMngerService.deleteUsrInfo(userId));
    }

    /**
     *   系统管理
     *    用户管理
     *     查询
     * @param userId 非必输
     * @param roleId 非必输
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/queryUsrInfo")
    public Result<?> queryUsrInfo(@RequestParam(value = "userId",required = false) String userId,
                                  @RequestParam(value = "roleId",required = false) String roleId,
                                  @RequestParam(value = "pageSize") Integer pageSize,
                                  @RequestParam(value = "pageNum") Integer pageNum
                                  ){
        return ResultUtil.success(sysUsrMngerService.queryUsrInfo(userId,roleId,pageNum,pageSize));
    }

    /**
     * 下拉用户角色
     * @return
     */
    @GetMapping("/querySelectRole")
    public Result<?> querySelect(){
        return ResultUtil.success(sysUsrMngerService.querySelect());
    }

    /**
     * 下拉用户名
     * @return
     */
    @GetMapping("/querySelectUsr")
    public Result<?> querySelectUsr(){
        return ResultUtil.success(sysUsrMngerService.querySelectUsr());
    }

}
