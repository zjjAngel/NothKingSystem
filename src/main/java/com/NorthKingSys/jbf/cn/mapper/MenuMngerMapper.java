package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.biz.MenuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMngerMapper {
  int  insertMenu(@Param("MENU_ID")String MENU_ID,@Param("MENU_NAME")String MENU_NAME,
                  @Param("MENU_LEVEL_PARENT")Integer MENU_LEVEL_PARENT,@Param("MENU_LEVEL")String MENU_LEVEL,
  @Param("PATH")String PATH,@Param("BACK_UP") String BACK_UP,@Param("STATUS") String STATUS
  );
   List<MenuInfo> selectIfexist(@Param("MENU_ID") String menuId,@Param("MENU_NAME")String menuName,
                                @Param("MENU_LEVEL_PARENT")String menu_level_parent,@Param("status")String status);
   int updateMenu (@Param("MENU_ID")String MENU_ID,@Param("MENU_NAME")String MENU_NAME,
                   @Param("MENU_LEVEL_PARENT")String MENU_LEVEL_PARENT,@Param("MENU_LEVEL")String MENU_LEVEL,
                   @Param("PATH")String PATH,@Param("BACK_UP") String BACK_UP,@Param("STATUS") String STATUS
   );
    List<MenuInfo> queryAllMenu( @Param("MENU_LEVEL")String menu_level,@Param("menu_id")String menu_id);
   List<MenuInfo> queryAllMenuLevel(@Param("list")List<String> list);
    List<MenuInfo> selectPerLevelMenus();
    String  selectMenusName(@Param("MENU_ID") String mnInfo);
    List<MenuInfo> queryFirstMenu(@Param("list") List<String> list);
    List<MenuInfo> queryNextLevelData(@Param("menu_level")Integer menu_level,@Param("list") List<String> menu_ids);
   int deleteMenu(@Param("menu_id")String menu_id);
}
