package com.NorthKingSys.jbf.cn.cust.api;

/**
 * 查询客户
 */

public class QueryClient {

    /**
     * CLIENT_NAME	String(100)	客户姓名
     * COMPANY	String(200)	公司
     * AREA	String(100)	区域
     * USER_NAME	String(100)	维系人
     */

    private String clientname;

    private String company;

    private String contacttel;

    private String address;

    private String area;

    private String username;

    private String clientno;

    private String usertel;

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientno() {
        return clientno;
    }

    public void setClientno(String clientno) {
        this.clientno = clientno;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }
}
