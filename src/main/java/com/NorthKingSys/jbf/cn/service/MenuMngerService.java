package com.NorthKingSys.jbf.cn.service;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.biz.MenuInfo;

import com.NorthKingSys.jbf.cn.domain.RoleInfo;
import com.NorthKingSys.jbf.cn.mapper.MenuMngerMapper;
import com.NorthKingSys.jbf.cn.mapper.RoleMngMeNuMapper;
import com.NorthKingSys.jbf.cn.util.SnowflakeIdWorkerUntil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuMngerService {
    @Autowired
   private MenuMngerMapper menuMngerMapper;
    @Autowired
    private RoleMngMeNuMapper roleMngMeNuMapper;
    public Object insertMenuMngerInfo(Map re){
      String menuId= SnowflakeIdWorkerUntil.menuNextId();
      String menuName= String.valueOf(re.get("MENU_NAME"));
      String menu_level_parent = String.valueOf(re.get("MENU_LEVEL_PARENT"));
        String menu_level = String.valueOf(re.get("MENU_LEVEL"));
        String path = String.valueOf(re.get("PATH"));//菜单路径
        String back_up = String.valueOf(re.get("BACK_UP"));//备注非必输
        String status = String.valueOf(re.get("STATUS"));//菜单状态\
       return menuMngerMapper.insertMenu(menuId,menuName,Integer.valueOf(StringUtils.isEmpty(menu_level_parent)?"0":menu_level_parent),
                menu_level,path,back_up,status);
    }
    public Object updateMenuMngerInfo(String menuId,String menuName,
                                      String menu_level_parent,String menu_level,
                                      String path,String back_up,String status ){
        List<MenuInfo> menuInfos= menuMngerMapper.selectIfexist(menuId,menuName,menu_level_parent,status);// 查看该菜单是否存在并返回结果
        if (menuInfos==null ||menuInfos.size()==0){
            throw new BusinessException("该菜单不存在");
        }
     return    menuMngerMapper.updateMenu(menuId,menuName,menu_level_parent,
                menu_level,path,back_up,status);
    }

    public PageInfo queryMenu(String menu_id,String menuName,String menu_level_parent,
                            String status,Integer pageSize,Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List<MenuInfo> menuInfos = menuMngerMapper.selectIfexist(menu_id, menuName, menu_level_parent, status);

        PageInfo<MenuInfo> pageInfo= new PageInfo<>(menuInfos);
        return pageInfo;
    }

    public Object deleteMenu(String menu_id){
        //根据菜单id 去查询所有角色
        List<RoleInfo>  roleInfos= roleMngMeNuMapper.selectRoleByMenuId(menu_id);
       List requ= roleInfos.stream().filter(e->!org.springframework.util.StringUtils.isEmpty(e.getROLE_ID()) ).map(e-> {
         Map req= new HashMap();
           String newRe = e.getFEIGN_IDS().replace(menu_id, "");
           req.put( e.getROLE_ID(),newRe);
           roleMngMeNuMapper.modifyRoleMngMeNuInfo(e.getROLE_ID(),null,null, newRe);//
          return req;
        }).collect(Collectors.toList());  //此种写法在角色数据比较大的情况下需要优化
        System.out.println("多少个角色受到影响"+ requ.size());
        return    menuMngerMapper.deleteMenu(menu_id);//删除菜单
    }
}
