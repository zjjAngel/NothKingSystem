package com.NorthKingSys.jbf.cn.controller;


import com.NorthKingSys.jbf.cn.biz.MenuInfo;
import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.service.MenuMngerService;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统管理
 *     菜单管理
 */
@RestController
@RequestMapping("/api/NothKingSystem/menu")
public class MenuMngerController {
    @Autowired
    private MenuMngerService menuMngerService;

    /**
     * 系统管理
     *     菜单管理
     *      新增菜单
     * @param re
     * @return
     */
    @PostMapping("/insertMenuMngerInfo")
    public Result<?> insertMenuMngerInfo(@RequestBody Map re){
        return ResultUtil.success(menuMngerService.insertMenuMngerInfo(re));
    }
    /**
     * 系统管理
     *     菜单管理
     *      修改菜单
     * @param re
     * @return
     */
    @PutMapping("/updateMenuMngerInfo")
    public Result<?> updateMenuMngerInfo(@RequestParam( "menuId") String menuId,
                                         @RequestParam( "menuName")String menuName,
                                         @RequestParam( "menu_level_parent")  String menu_level_parent,
                                         @RequestParam( "menu_level")String menu_level,
                                         @RequestParam( "path")String path,
                                         @RequestParam( value = "back_up",required = false) String back_up,
                                         @RequestParam( "status")String status){
        return ResultUtil.success(menuMngerService.updateMenuMngerInfo(menuId,menuName,menu_level_parent,
                menu_level,path,back_up,status));
    }

    /**
     *  菜单查询  未写完
     *
     * @param menu_id
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/queryMenu")
    public Result<?> queryMenu(
            @RequestParam(value = "menu_id",required=false ) String menu_id,
            @RequestParam( value = "menuName",required=false)String menuName,
            @RequestParam( value = "menu_level_parent", required=false)String menu_level_parent,
            @RequestParam( value = "status",required = false)String status,
            @RequestParam(value = "pageSize",required = false ,defaultValue = "10")  Integer pageSize,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum

    ){
//        return ResultUtil.success(menuMngerMapper.selectIfexist(menu_id));
        return ResultUtil.success(menuMngerService.queryMenu(menu_id,menuName,
                menu_level_parent,status,pageSize,pageNum)) ;
    }

}
