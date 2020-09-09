package com.NorthKingSys.jbf.cn.biz;

import lombok.Data;


/**
 * 项目管理接口
 */
@Data
public class ProjectInfo {

    /**项目编号*/

    private String prodno;

    /**项目名称*/

    private String prodname;

    /*项目类型*/

    private String prodtype;

    /*开始时间*/

    private String starttime;

    /*状态*/

    private String status;

    private int page;

    private int size;

    private int totalpages;

    private long totalsize;

}