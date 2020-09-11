package com.NorthKingSys.jbf.cn.domain;

import java.util.Date;

public class JbfRequireInfo {
    private Integer id;

    private String numNo;

    private String requireCust;

    private String project;

    private String position;

    private String requreNum;

    private String request;

    private String priority;

    private Date creatTime;

    private String status;//招聘状态

    private String requestStatus; // 纪录状态

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumNo() {
        return numNo;
    }

    public void setNumNo(String numNo) {
        this.numNo = numNo;
    }

    public String getRequireCust() {
        return requireCust;
    }

    public void setRequireCust(String requireCust) {
        this.requireCust = requireCust == null ? null : requireCust.trim();
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getRequreNum() {
        return requreNum;
    }

    public void setRequreNum(String requreNum) {
        this.requreNum = requreNum;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }


    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}