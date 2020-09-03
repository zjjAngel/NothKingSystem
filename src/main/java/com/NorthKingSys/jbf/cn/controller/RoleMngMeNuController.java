package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.service.MenuMngerService;
import com.NorthKingSys.jbf.cn.service.RoleMngMeNuService;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/NothKingSystem/roleMngMenu")
public class RoleMngMeNuController {

    @Autowired
    private RoleMngMeNuService roleMngMeNuService;

    /**
     * 系统管理
     *     菜单管理
     *      新增菜单
     * @param re
     * @return
     */
    @PostMapping("/insertRoleMngMeNuInfo")
    public Result<?> insertRoleMngMeNuInfo(@RequestBody Map re){
        return ResultUtil.success(roleMngMeNuService.insertRoleMngMeNuInfo(re));
    }
    /**
     * 角色接口查询
     */
    @GetMapping("queryRoleMngMenuInfo")
    public Result<?> queryRoleMngMenuInfo(@RequestParam(value = "roleName",required = false) String roleName,
                                          @RequestParam(value = "pageNum",required = false,defaultValue = "1") String pageNum,
                                          @RequestParam(value = "pageSize",required = false,defaultValue = "10") String pageSize
                                          ){
        return ResultUtil.success(roleMngMeNuService.queryRoleMngMenuInfo(roleName,pageNum,pageSize));
    }

    /**
     * 角色菜单修改
     * @param re
     * @return
     */
    @PostMapping("/modifyRoleMngMeNuInfo")
    public Result<?> modifyRoleMngMeNuInfo(@RequestBody Map re){
        return ResultUtil.success(roleMngMeNuService.modifyRoleMngMeNuInfo(re));
    }

    /**
     *  删除角色
     * @param role_id
     * @return
     */
    @GetMapping("/deleteRoleById")
    public Result<?> deleteRoleMenu(@RequestParam("role_Id")String role_id){
        return ResultUtil.success(roleMngMeNuService.deleteRoleMenuUsr(role_id));
    }

//    /**
//     * 角色编辑查询接口
//     * menuIds 已经分配的menuIds
//     */
//    @GetMapping("queryRoleEditMenuInfo")
//    public Result<?> queryRoleEditMenuInfo(
//            @RequestParam(value = "role_id",required = false) String role_id
//           ){
//        return ResultUtil.success(roleMngMeNuService.queryRoleEditMenuInfo(role_id));
//       }

    /**
     * 根据角色查询已经拥有的菜单权限   一般有2级权限必定有一级权限
     * @param role_Id
     * @return
     */
    @GetMapping("/queryMenuByRoleId")
    public Result<?> queryMenuByRoleId(@RequestParam("role_Id") String role_Id){
       return ResultUtil.success(roleMngMeNuService.queryMenuByRoleId(role_Id));
    }

    /**
     * 左侧一级菜单查询
     * @param level
     * @return
     */
    @GetMapping("/queryMenuByCurrentLevel")
    public Result<?> queryMenuByCurrentLevel(@RequestParam(value = "level",required = false,defaultValue = "1") String level){
        return ResultUtil.success(roleMngMeNuService.queryMenuByCurrentLevel(level));
    }

    /**
     * 左侧下一级菜单查询
     * @param level 本级等级
     * @param menu_Id 本级菜单 菜单menue_id
     * @return
     */
    @GetMapping("/queryMenuByNextLevel")
    public Result<?> queryMenuByNextLevel(@RequestParam(value = "level",defaultValue = "1") String level,
                                          @RequestParam(value = "menu_Id",defaultValue = "M_919679489") String menu_Id){
        return ResultUtil.success(roleMngMeNuService.queryMenuByNextLevel(level,menu_Id));
    }

}
