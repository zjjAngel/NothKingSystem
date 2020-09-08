package com.NorthKingSys.jbf.cn.biz;

public enum JBFErrorCode {
    NULL_OBJ("LUO001","对象为空"),
    ERROR_ADD_USER("LUO002","加入用户失败"),
    UNKNOWN_ERROR("LUO999","非业务的未知错误",500);
    private String value;
    private String desc;
    private int code;
     JBFErrorCode(String value, String desc, int code) {
        this.value = value;
        this.desc = desc;
        this.code = code;
    }

    private JBFErrorCode(String value, String desc){
        this.setValue(value);
        this.setDesc(desc);
        this.code=500;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "JBFErrorCode{" +
                "value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                ", code=" + code +
                '}';
    }
}
