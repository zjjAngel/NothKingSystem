//package com.NorthKingSys.jbf.cn.cust.controller;
//
//
//import com.NorthKingSys.jbf.cn.biz.BusinessException;
//import com.NorthKingSys.jbf.cn.cust.api.AddClient;
//import com.NorthKingSys.jbf.cn.cust.api.DelClient;
//import com.NorthKingSys.jbf.cn.cust.api.QueryClient;
//import com.NorthKingSys.jbf.cn.cust.api.UpClient;
//import com.NorthKingSys.jbf.cn.domain.JbfCustInfo;
//import com.NorthKingSys.jbf.cn.mapper.JbfCustInfoMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.apache.log4j.Logger;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@RestController
//@RequestMapping(value = "/cust", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//public class CustInfoController {
//    private static Logger log = Logger.getLogger(CustInfoController.class.getClass());
//
//    private static final String FM4001 = "FM4001 查询无返回记录";
//
//    @Autowired
//    JbfCustInfoMapper jbfCustInfoMapper;
//
//
//
//    /**
//     * 查询客户信息
//     * @param queryClient
//     * @return
//     */
//    @PostMapping("/query")
//    private List<QueryClient> queryCustInfo(QueryClient queryClient){
//
//        List<JbfCustInfo> jbfCustInfos = new ArrayList<>();
//        List<QueryClient> queryClients = new ArrayList<>();
//        jbfCustInfos = jbfCustInfoMapper.selectByClientInfo(queryClient.getClientname(),
//                                                            queryClient.getCompany(),
//                                                            queryClient.getArea(),
//                                                            queryClient.getUsername());
//        if(jbfCustInfos ==null || jbfCustInfos.size()<=0){
//            //  抛出异常 查询无返回记录
//            throw new BusinessException(FM4001);
//        }else {
//            for(JbfCustInfo jbfCustInfo : jbfCustInfos){
//                QueryClient queryClient1 = new QueryClient();
//                queryClient1.setClientno(jbfCustInfo.getCustNo()); //客户编号
//                queryClient1.setClientname(jbfCustInfo.getCustName()); // 客户姓名
//                queryClient1.setContacttel(jbfCustInfo.getMobileNo()); // 联系方式
//                queryClient1.setCompany(jbfCustInfo.getCompany()); // 公司
//                queryClient1.setAddress(jbfCustInfo.getAdress()); // 地址
//                queryClient1.setArea(jbfCustInfo.getRegion()); // 区域
//                queryClient1.setUsername(jbfCustInfo.getRelationName()); // 维系人姓名
//                queryClient1.setUsertel(jbfCustInfo.getRelateMobilNo()); // 维系人电话
//                queryClients.add(queryClient1);
//            }
//        }
//        return queryClients;
//    }
//
//    /**
//     *  增加客户信息
//     * @param addClient
//     * @return
//     */
//
//
//    @PostMapping("/add")
//    private String addCustInfo(AddClient addClient){
//        String clientno = null;
//
//        String maxNo = getMaxCustNo();
//        int maxNo1 = Integer.valueOf(maxNo);
//        maxNo1++;
//        clientno = String.valueOf(maxNo1);
//        JbfCustInfo jbfCustInfo = new JbfCustInfo();
//        jbfCustInfo.setCustNo(clientno);
//        jbfCustInfo.setCustName(addClient.getClientname());
//        jbfCustInfo.setCompany(addClient.getCompany());
//        jbfCustInfo.setMobileNo(addClient.getContacttel());
//        jbfCustInfo.setAdress(addClient.getAddress());
//        jbfCustInfo.setRegion(addClient.getArea());
//        jbfCustInfo.setRelationName(addClient.getUsername());
//        jbfCustInfo.setRelateMobilNo(addClient.getUsertel());
//        int num =  jbfCustInfoMapper.insertSelective(jbfCustInfo);
//        log.info("num的值是"+num);
//        return clientno;
//    }
//
//
//    /**
//     * 修改客户信息
//     * @param upClient
//     */
//    @PostMapping("/update")
//    private void updateClientInfo(UpClient upClient){
//
//        JbfCustInfo jbfCustInfo = new JbfCustInfo();
//        jbfCustInfo.setCustNo(upClient.getClientno());
//        jbfCustInfo.setCustName(upClient.getClientname());
//        jbfCustInfo.setCompany(upClient.getCompany());
//        jbfCustInfo.setMobileNo(upClient.getContacttel());
//        jbfCustInfo.setAdress(upClient.getAddress());
//        jbfCustInfo.setRegion(upClient.getArea());
//        jbfCustInfo.setRelationName(upClient.getUsername());
//        jbfCustInfo.setRelateMobilNo(upClient.getUsertel());
//
//        jbfCustInfoMapper.updateByPrimaryKeySelective(jbfCustInfo);
//
//    }
//
//    /**
//     * 删除客户信息
//     * @param delClient
//     */
//        @PostMapping("/del")
//        private void delClientInfo(DelClient delClient){
//
//        Integer id = Integer.valueOf(delClient.getClientno());
//        jbfCustInfoMapper.deleteByPrimaryKey(id);
//
//    }
//
//
//    private  synchronized String  getMaxCustNo(){
//
//        String maxCustNo = jbfCustInfoMapper.getMaxCustNo();
//        if(maxCustNo == null){
//            return maxCustNo = "100000";
//        }else {
//            return maxCustNo;
//        }
//    }
//
//}
