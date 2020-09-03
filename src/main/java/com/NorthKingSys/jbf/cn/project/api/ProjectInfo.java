package com.NorthKingSys.jbf.cn.project.api;

import java.util.Date;

/**
 * 项目管理接口
 */

public class ProjectInfo {

    /**项目编号*/

    private String prodno;

    /**项目名称*/

    private String prodname;

    /*项目类型*/

    private String prodtype;

    /*开始时间*/

    private Date starttime;

    /*状态*/

    private String status;

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdtype() {
        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype;
    }


    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
