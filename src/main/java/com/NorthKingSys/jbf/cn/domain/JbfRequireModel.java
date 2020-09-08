package com.NorthKingSys.jbf.cn.domain;

import java.util.Date;

public class JbfRequireModel {

    private String number;

    private String requireCust;

    private String project;

    private String position;

    private String requreNum;

    private String request;

    private String priority;

    private String stutas;

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

    public String getStutas() {
        return stutas;
    }

    public void setStutas(String stutas) {
        this.stutas = stutas == null ? null : stutas.trim();
    }
}
