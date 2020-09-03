package com.NorthKingSys.jbf.cn.cust.api;

/**
 * 增加客户接口
 */
public class AddClient {


    /**
     CLIENT_NAME	String(100)	客户姓名
     CONTACT_TEL	String(20)	联系方式
     COMPANY	String(200)	公司
     ADDRESS	String(300)	详细地址
     AREA	String(100)	区域
     USER_NAME	String(100)	维系人
     USER_TEL	String(20)	维系人联系方式
     */

    private String clientname;

    private String contacttel;

    private String company;

    private String address;

    private String area;

    private String username;

    private String usertel;

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }
}
