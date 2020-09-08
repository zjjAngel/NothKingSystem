package com.NorthKingSys.jbf.cn.config.aop.exception.exconf;

import com.NorthKingSys.jbf.cn.biz.JBFErrorCode;

public class DescribeException  extends RuntimeException{
    private Integer code;
    /**
     6      * 继承exception，加入错误状态值
     7      * @param exceptionEnum
     8      */
      public DescribeException(JBFErrorCode exceptionEnum) {
         super(exceptionEnum.getDesc());
          this.code = exceptionEnum.getCode();
        }

    /**
      * 自定义错误信息
      * @param message
      * @param code
      */

    public DescribeException(String message, Integer code) {
          super(message);
          this.code = code;
        }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
