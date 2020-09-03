package com.NorthKingSys.jbf.cn.cust.api;

/**
 * 删除客户
 */
public class DelClient {

/**
 * CLIENT_NO	String(100)	客户编号
 * CLIENT_NAME	String(100)	客户姓名
 */
    private String clientno;

    private String clientname;

    public String getClientno() {
        return clientno;
    }

    public void setClientno(String clientno) {
        this.clientno = clientno;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    @Override
    public String toString() {
        return "DelClient{" +
                "clientno='" + clientno + '\'' +
                ", clientname='" + clientname + '\'' +
                '}';
    }
}
