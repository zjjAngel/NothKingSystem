package com.NorthKingSys.jbf.cn.biz;

import lombok.Data;

@Data
public class CustInfo {

    private String custno;

    private String custname;

    private String company;

    private String mobileno;

    private String adress;

    private String region;

    private String relationname;

    private String relatemobilNo;

    private int page;

    private int size;

    private int totalpages;

    private long totalsize;

}
