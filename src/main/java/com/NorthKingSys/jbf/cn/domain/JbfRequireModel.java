package com.NorthKingSys.jbf.cn.domain;

import lombok.Data;

@Data
public class JbfRequireModel {

    /*需求编号*/
    private String numNo;
    /*客户需求*/
    private String requireCust;
    /*项目*/
    private String project;
    /*岗位*/
    private String position;
    /*需求人数*/
    private String requreNum;
    /*要求*/
    private String request;
    /*优先级*/
    private String priority;
    /*状态*/
    private String status;

    private int page;

    private int size;
    /*总页数*/
    private int totalpages;
    /*总条数*/
    private long totalsize;

    private String option;

}
