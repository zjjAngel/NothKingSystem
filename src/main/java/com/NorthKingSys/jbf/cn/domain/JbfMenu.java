package com.NorthKingSys.jbf.cn.domain;

public class JbfMenu {
    private Integer id;

    private String menuId;

    private String menuName;

    private Integer menuLevel;

    private String menuLevelDesc;

    private Integer menuLevelParent;

    private String path;

    private String backUp;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuLevelDesc() {
        return menuLevelDesc;
    }

    public void setMenuLevelDesc(String menuLevelDesc) {
        this.menuLevelDesc = menuLevelDesc == null ? null : menuLevelDesc.trim();
    }

    public Integer getMenuLevelParent() {
        return menuLevelParent;
    }

    public void setMenuLevelParent(Integer menuLevelParent) {
        this.menuLevelParent = menuLevelParent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}