package com.NorthKingSys.jbf.cn.domain;

public class JbfRole {
    private Integer id;

    private String roleId;

    private String roleName;

    private String backUp;

    private String feignIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }

    public String getFeignIds() {
        return feignIds;
    }

    public void setFeignIds(String feignIds) {
        this.feignIds = feignIds == null ? null : feignIds.trim();
    }
}