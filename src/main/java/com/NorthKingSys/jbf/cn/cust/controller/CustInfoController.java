package com.NorthKingSys.jbf.cn.cust.controller;


import com.NorthKingSys.jbf.cn.cust.api.CustInfo;
import com.NorthKingSys.jbf.cn.domain.JbfCustInfo;
import com.NorthKingSys.jbf.cn.mapper.JbfCustInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/cust", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
public class CustInfoController {
    private static Logger log = Logger.getLogger(CustInfoController.class.getClass());

    private static final String FM4001 = "FM4001 查询无返回记录";

    @Autowired
    JbfCustInfoMapper jbfCustInfoMapper;



    /**
     * 查询客户信息
     * @param custInfo
     * @return
     */
    @PostMapping("/query")
    private List<CustInfo> queryCustInfo(@RequestBody CustInfo custInfo){

        List<JbfCustInfo> jbfCustInfos = new ArrayList<>();
        List<CustInfo> queryClients = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("custname",custInfo.getCustname());
        map.put("company",custInfo.getCompany());
        map.put("region",custInfo.getRegion());
        map.put("relationname",custInfo.getRelationname());

        jbfCustInfos = jbfCustInfoMapper.selectByClientInfo(map);
        if(jbfCustInfos ==null || jbfCustInfos.size()<=0){
            //  抛出异常 查询无返回记录
            //throw new BusinessException(FM4001);
        }else {
            for(JbfCustInfo jbfCustInfo : jbfCustInfos){
                CustInfo queryClient1 = new CustInfo();
                queryClient1.setCustno(jbfCustInfo.getCustNo()); //客户编号
                queryClient1.setCustname(jbfCustInfo.getCustName()); // 客户姓名
                queryClient1.setMobileno(jbfCustInfo.getMobileNo()); // 联系方式
                queryClient1.setCompany(jbfCustInfo.getCompany()); // 公司
                queryClient1.setAdress(jbfCustInfo.getAdress()); // 地址
                queryClient1.setRegion(jbfCustInfo.getRegion()); // 区域
                queryClient1.setRelationname(jbfCustInfo.getRelationName()); // 维系人姓名
                queryClient1.setRelatemobilNo(jbfCustInfo.getRelateMobilNo()); // 维系人电话
                queryClients.add(queryClient1);
            }
        }
        return queryClients;
    }

    /**
     *  增加客户信息
     * @param custInfo
     * @return
     */


    @PostMapping("/add")
    private String addCustInfo(@RequestBody CustInfo custInfo){
        String clientno = null;

        String maxNo = getMaxCustNo();
        int maxNo1 = Integer.valueOf(maxNo);
        maxNo1++;
        clientno = String.valueOf(maxNo1);
        JbfCustInfo jbfCustInfo = new JbfCustInfo();
        jbfCustInfo.setCustNo(clientno);
        jbfCustInfo.setCustName(custInfo.getCustname());
        jbfCustInfo.setCompany(custInfo.getCompany());
        jbfCustInfo.setMobileNo(custInfo.getMobileno());
        jbfCustInfo.setAdress(custInfo.getAdress());
        jbfCustInfo.setRegion(custInfo.getRegion());
        jbfCustInfo.setRelationName(custInfo.getRelationname());
        jbfCustInfo.setRelateMobilNo(custInfo.getRelatemobilNo());
        int num =  jbfCustInfoMapper.insertSelective(jbfCustInfo);
        log.info("num的值是"+num);
        return clientno;
    }


    /**
     * 修改客户信息
     * @param custInfo
     */
    @PostMapping("/update")
    private void updateClientInfo(@RequestBody CustInfo custInfo){

        JbfCustInfo jbfCustInfo = new JbfCustInfo();
        jbfCustInfo.setCustNo(custInfo.getCustno());
        jbfCustInfo.setCustName(custInfo.getCustname());
        jbfCustInfo.setCompany(custInfo.getCompany());
        jbfCustInfo.setMobileNo(custInfo.getMobileno());
        jbfCustInfo.setAdress(custInfo.getAdress());
        jbfCustInfo.setRegion(custInfo.getRegion());
        jbfCustInfo.setRelationName(custInfo.getRelationname());
        jbfCustInfo.setRelateMobilNo(custInfo.getRelatemobilNo());

        jbfCustInfoMapper.updateByPrimaryKeySelective(jbfCustInfo);

    }

    /**
     * 删除客户信息
     * @param custInfo
     */
    @PostMapping("/del")
    private void delClientInfo(@RequestBody  CustInfo custInfo){

        Integer id = Integer.valueOf(custInfo.getCustno());
        jbfCustInfoMapper.deleteByPrimaryKey(id);

    }


    private  synchronized String  getMaxCustNo(){

        String maxCustNo = jbfCustInfoMapper.getMaxCustNo();
        if(maxCustNo == null){
            return maxCustNo = "100000";
        }else {
            return maxCustNo;
        }
    }

}
