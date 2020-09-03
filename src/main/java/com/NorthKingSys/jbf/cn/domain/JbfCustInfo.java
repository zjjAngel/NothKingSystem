package com.NorthKingSys.jbf.cn.domain;

public class JbfCustInfo {
    private Integer id;

    private String custNo;

    private String custName;

    private String company;

    private String mobileNo;

    private String adress;

    private String region;

    private String relationName;

    private String relateMobilNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo == null ? null : custNo.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName == null ? null : relationName.trim();
    }

    public String getRelateMobilNo() {
        return relateMobilNo;
    }

    public void setRelateMobilNo(String relateMobilNo) {
        this.relateMobilNo = relateMobilNo == null ? null : relateMobilNo.trim();
    }
}