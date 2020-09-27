package com.NorthKingSys.jbf.cn.controller;


import com.NorthKingSys.jbf.cn.biz.BeanResult;
import com.NorthKingSys.jbf.cn.biz.CustInfo;
import com.NorthKingSys.jbf.cn.domain.JbfCustInfo;
import com.NorthKingSys.jbf.cn.mapper.JbfCustInfoMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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


    @PostMapping("/queryForList")
    public BeanResult queryCustInfoForList(@RequestBody CustInfo custInfo){
        String option = custInfo.getOption();
        List<CustInfo> custInfos = new ArrayList<>();
        if(option != null && !option.equals("")){
            // 01查询客户姓名下拉列表
            if(option.equals("01")){
                List<String>  custNames = jbfCustInfoMapper.getJbfCustInfoName();
                if(custNames != null && custNames.size()>0){
                    for (int i = 0; i < custNames.size(); i++) {
                        CustInfo custInfo1 = new CustInfo();
                        custInfo1.setCustname(custNames.get(i));
                        custInfos.add(custInfo1);
                    }
                }
            }
            // 02查询 项目 下拉列表
            if(option.equals("02")){
                Map<String,Object> map = new HashMap();
                map.put("custName",custInfo.getCustname());
                List<String>  custCompanys = jbfCustInfoMapper.getJbfCompanyName(map);
                if(custCompanys != null && custCompanys.size()>0){
                    for (int i = 0; i < custCompanys.size(); i++) {
                        CustInfo custInfo1 = new CustInfo();
                        custInfo1.setCompany(custCompanys.get(i));
                        custInfos.add(custInfo1);
                    }
                }
            }
            // 02查询 区域下拉列表
            if(option.equals("03")){

            }
            // 04查询 维系人下拉列表
            if(option.equals("04")){

            }
        }

        return new BeanResult(custInfos);
    }





    /**
     * 查询客户信息
     * @param custInfo
     * @return
     */
    @PostMapping("/query")
    public BeanResult queryCustInfo(@RequestBody CustInfo custInfo){
        List<JbfCustInfo> jbfCustInfos = new ArrayList<>();
        List<CustInfo> queryClients = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("custname",custInfo.getCustname());
        map.put("company",custInfo.getCompany());
        map.put("region",custInfo.getRegion());
        map.put("relationname",custInfo.getRelationname());
        Page page =  PageHelper.startPage(custInfo.getPage(),custInfo.getSize());
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
                queryClient1.setTotalpages(page.getPages());
                queryClient1.setTotalsize(page.getTotal());
                queryClients.add(queryClient1);
            }
        }
        return new BeanResult(queryClients);

    }

    /**
     *  增加客户信息
     * @param custInfo
     * @return
     */


    @PostMapping("/add")
    public  BeanResult addCustInfo(@RequestBody CustInfo custInfo){
        String clientno = null;
        clientno = getMaxCustNo();
        JbfCustInfo jbfCustInfo = new JbfCustInfo();
        jbfCustInfo.setCustNo(clientno);
        jbfCustInfo.setCustName(custInfo.getCustname());
        jbfCustInfo.setCompany(custInfo.getCompany());
        jbfCustInfo.setMobileNo(custInfo.getMobileno());
        jbfCustInfo.setAdress(custInfo.getAdress());
        jbfCustInfo.setRegion(custInfo.getRegion());
        jbfCustInfo.setRelationName(custInfo.getRelationname());
        jbfCustInfo.setRelateMobilNo(custInfo.getRelatemobilNo());
        jbfCustInfo.setCustStatus("A");
        int num =  jbfCustInfoMapper.insertSelective(jbfCustInfo);
        log.info("num的值是"+num);
        custInfo.setCustno(clientno);
        return new BeanResult(custInfo);
    }


    /**
     * 修改客户信息
     * @param custInfo
     */
    @PostMapping("/update")
    public BeanResult updateClientInfo(@RequestBody CustInfo custInfo){

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

        return new BeanResult(custInfo);
    }

    /**
     * 删除客户信息
     * @param custInfo
     */
    @PostMapping("/del")
    public BeanResult delClientInfo(@RequestBody  CustInfo custInfo){

//        Integer id = Integer.valueOf(custInfo.getCustno());
//        jbfCustInfoMapper.deleteByPrimaryKey(id);
        JbfCustInfo jbfCustInfo = new JbfCustInfo();
        jbfCustInfo.setCustNo(custInfo.getCustno());
        jbfCustInfo.setCustStatus("E");
        jbfCustInfoMapper.updateByPrimaryKeySelective(jbfCustInfo);
        return new BeanResult();

    }


    public  synchronized String  getMaxCustNo(){

        String maxCustNo = jbfCustInfoMapper.getMaxCustNo();
        if(maxCustNo == null){
            return maxCustNo = "100000";
        }else {
            int maxNo1 = Integer.valueOf(maxCustNo);
            maxNo1++;
            maxCustNo = String.valueOf(maxNo1);
            return maxCustNo;
        }
    }

}
