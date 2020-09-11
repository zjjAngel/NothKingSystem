package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfRequireInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RequstMapper {
    public List<JbfRequireInfo> queryPointWhere(@Param("REQUIRE_CUST")String REQUIRE_CUST,
                                                @Param("NUM_NO")String NUM_NO,
                                               @Param("PROJECT")String PROJECT );

    public  List<String> queryProfile(@Param("REQUIRE_CUST")String REQUIRE_CUST,
                                      @Param("NUM_NO")String NUM_NO,
                                      @Param("PROJECT")String PROJECT);
}
