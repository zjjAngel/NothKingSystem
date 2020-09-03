package com.NorthKingSys.jbf.cn.biz;

public enum JBFErrorCode {
    NULL_OBJ("LUO001","对象为空"),
    ERROR_ADD_USER("LUO002","加入用户失败"),
    UNKNOWN_ERROR("LUO999","系统繁忙，请稍后再试....");
    private String value;
    private String desc;
    private JBFErrorCode(String value,String desc){
        this.setValue(value);
        this.setDesc(desc);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "JBFErrorCode{" +
                "value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
