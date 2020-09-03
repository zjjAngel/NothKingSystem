package com.NorthKingSys.jbf.cn.domain;

import java.util.Date;

public class JbfRequireInfo {
    private Integer id;

    private String number;

    private String requireCust;

    private String project;

    private String position;

    private Integer requreNum;

    private String request;

    private String priority;

    private String stutas;

    private Date creatTime;

    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
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

    public Integer getRequreNum() {
        return requreNum;
    }

    public void setRequreNum(Integer requreNum) {
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

    public String getStutas() {
        return stutas;
    }

    public void setStutas(String stutas) {
        this.stutas = stutas == null ? null : stutas.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}