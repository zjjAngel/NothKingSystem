package com.NorthKingSys.jbf.cn.service;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.biz.MenuInfo;
import com.NorthKingSys.jbf.cn.biz.UsrPwdInfo;
import com.NorthKingSys.jbf.cn.domain.RoleInfo;
import com.NorthKingSys.jbf.cn.mapper.MenuMngerMapper;
import com.NorthKingSys.jbf.cn.mapper.RoleMngMeNuMapper;
import com.NorthKingSys.jbf.cn.mapper.SysUsrMngerMapper;
import com.NorthKingSys.jbf.cn.mapper.UserInfoMapper;
import com.NorthKingSys.jbf.cn.util.SnowflakeIdWorkerUntil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RoleMngMeNuService {
    @Autowired
   private RoleMngMeNuMapper roleMngMeNuMapper;
    @Autowired
   private SysUsrMngerMapper sysUsrMngerMapper;
    @Autowired
    private MenuMngerMapper menuMngerMapper;

   public Object insertRoleMngMeNuInfo(Map re){
       String role_id = SnowflakeIdWorkerUntil.roleIdNextId();
       String role_name = String.valueOf(re.get("ROLE_NAME"));
       String back_up = String.valueOf(re.get("BACK_UP"));
       String feign_ids = String.valueOf(re.get("FEIGN_IDS"));//menuIds
       if(feign_ids.contains(",")){
           return  roleMngMeNuMapper.insertRoleMenu(role_id,role_name,back_up,feign_ids);
       }else if(feign_ids.split(",").length==1){
           return  roleMngMeNuMapper.insertRoleMenu(role_id,role_name,back_up,feign_ids);
       }else {
           throw new BusinessException("FEIGN_IDS 该字段为非法字段，必须用逗号相隔");
       }

   }

    public Object modifyRoleMngMeNuInfo(Map re){
        String role_id =String.valueOf(re.get("ROLE_ID"));
        String role_name = String.valueOf(re.get("ROLE_NAME"));
        String back_up = String.valueOf(re.get("BACK_UP"));
        String feign_ids = String.valueOf(re.get("FEIGN_IDS"));//menuIds 用逗号分隔
        return roleMngMeNuMapper.modifyRoleMngMeNuInfo(role_id,role_name,back_up,feign_ids);
    }

   public Object queryRoleMngMenuInfo(String roleName,String pageNum,String pageSize){
       PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
       List<RoleInfo> roleInfos = roleMngMeNuMapper.queryRoleMngMenuInfo(roleName);
       PageInfo<RoleInfo> pageInfo= new PageInfo<>(roleInfos);
       return pageInfo;
   }

   public Object deleteRoleMenuUsr(String role_id){
       List<UsrPwdInfo> roleInfos=  sysUsrMngerMapper.queryUsrInfo(null,role_id);//查询出来该角色下挂了多少用户
       List<String> collect=new ArrayList<>();
       if(null!=roleInfos&&roleInfos.size()>0){
            collect = roleInfos.stream().map(UsrPwdInfo::getUser_id).collect(Collectors.toList());
       }
       if(collect.size()>0) {
           sysUsrMngerMapper.deleteUsr(collect);// 删除该角色下挂的用户
       }
        int num= roleMngMeNuMapper.deletebyRoleId(role_id);//删除角色表
        return num;
   }

    /**
     * 数据结构无法确定
     * @param role_Id
     * @return
     */
//   public  Object queryRoleEditMenuInfo( String role_id){
//        Map res=new HashMap();
//       //通过角色id 去查询该角色已经分配了的菜单
//       List<RoleInfo> roleInfos = roleMngMeNuMapper.queryRoleEditMenuInfo(role_id);
//       if(roleInfos.size()==1){
//         List<String> roles=  Arrays.asList(roleInfos.get(0).getFEIGN_IDS().split(","));// 分配的菜单ids
//         List<MenuInfo> allMenus=menuMngerMapper.queryAllMenu(null);//按照等级查询
//         List<MenuInfo>  levelInfos=   menuMngerMapper.queryAllMenuLevel();//查询所有级别
//           levelInfos.stream().forEach(e->{
//               String  currLevel=e.getMenu_level();
//               List<MenuInfo> menuInfos = menuMngerMapper.queryAllMenu(currLevel);
//               for (MenuInfo mu: menuInfos) {
//                   List<String> collect = roles.stream().filter(Predicate.isEqual(mu.getMenu_id())).collect(Collectors.toList());
//                   if (null!=collect&&collect.size()>0){
//                       mu.setChecked("0");
//                   }
//               };
//               res.put(currLevel,menuInfos);
//           });
//         //标记已分配菜单
//       }else if(roleInfos.size()>=1){
//         throw new BusinessException("异常数据");
//       }else {
//         throw new BusinessException("异常数据");
//       }
//   }

   public Object queryMenuByRoleId(String role_Id){
       // 按等级查询出每一等级所有的菜单
       List<MenuInfo> menuInfosPer = menuMngerMapper.selectPerLevelMenus();
       List<RoleInfo> menuInfos= roleMngMeNuMapper.selectFein_ids(role_Id);//查询出该角色拥有的所有菜单的menu_id
       //查出所有拥有权限的菜单
       List<String> sortMnInfos=Arrays.asList(menuInfos.get(0).getFEIGN_IDS().split(","));
       List<MenuInfo>  resultMenu= new ArrayList<>();//存放有权限的菜单
       for (int i = 0; i < menuInfosPer.size(); i++) {

          StringBuffer stringBuffer= new StringBuffer();
           int finalI = i;
           sortMnInfos.forEach(mnInfo->{
               if (menuInfosPer.get(finalI).getMenu_ids().contains(mnInfo)){
                   stringBuffer.append(mnInfo).append(",");

               }
           });
           menuInfosPer.get(finalI).setMenu_ids(String.valueOf(stringBuffer));

       } //得到清洗后的数据（去掉没有权限的）

       // 翻译出List<menuIdNameRel>
       for (int i = 0; i < menuInfosPer.size(); i++) {
           //每一个级别的menuId
           List<String> strings = Arrays.asList(menuInfosPer.get(i).getMenu_ids().split(","));
           List<Map> explain= new ArrayList<>();
           strings.stream().forEach(e->{
               Map<String, String> resut = new HashMap<>();
               resut.put(e,menuMngerMapper.selectMenusName(e));
               explain.add(resut);
           });
           menuInfosPer.get(i).setMenuIdNameRelList(explain);
       }
       return menuInfosPer;
   }

    /**
     * 翻译PerMenuIdsName
     * @param menuInfosPer
     * @return
     */
   private Object queryMenuByRoleIdNames(List<MenuInfo> menuInfosPer){
       menuInfosPer.stream().forEach(e->{
           Map relation=new HashMap();
           relation.put(e.getMenu_id(),e.getMenu_name());
           e.setMenuIdNameRel(relation);
       });
       return menuInfosPer;
   }

   public Object queryMenuByCurrentLevel(String level){
     return    roleMngMeNuMapper.selectCurrentLevel(level);
   }

   public Object queryMenuByNextLevel(String level,String menu_Id){
      return roleMngMeNuMapper.queryMenuByNextLevel(String.valueOf(Integer.valueOf(level)+1),menu_Id);
   }

}
