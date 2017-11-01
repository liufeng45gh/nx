//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.service.businessprocessor.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssb.warmline.business.commons.config.ResponseObject;
import ssb.warmline.business.commons.enums.capitalType;
import ssb.warmline.business.commons.utils.CommonUtils;
import ssb.warmline.business.commons.utils.UUIDUtil;
import ssb.warmline.business.entity.wcapital.WCapitalEntity;
import ssb.warmline.business.entity.wcashaccount.WCashAccountEntity;
import ssb.warmline.business.service.businessprocessor.BaseInterface;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.wcapital.WCapitalServiceI;
import ssb.warmline.business.service.wcashaccount.WCashAccountServiceI;

@Service
public class MyWalletServiceProcessor extends BaseInterface {
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;
    @Autowired
    private WCashAccountServiceI wCashAccountService;
    @Autowired
    private WCapitalServiceI wCapitalService;
    @Autowired
    private CommonService commonService;

    public MyWalletServiceProcessor() {
    }

    public ResponseObject myBalance(String uid, String token) {
        Map<String, Object> jsonMap = new HashMap();
        TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        if(tSBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
        } else {
            jsonMap.put("balance", tSBaseUser.getBalance());
            List<WCashAccountEntity> wCashAccount = this.wCashAccountService.findByProperty(WCashAccountEntity.class, "userId", uid);
            List<Map> mybalanceList = new ArrayList();
            Map map = new HashMap();
            if(wCashAccount.size() > 0) {
                for(int i = 0; i < wCashAccount.size(); ++i) {
                    WCashAccountEntity wCashAccountEntity = (WCashAccountEntity)wCashAccount.get(i);
                    map.put("alipayBindingState", wCashAccountEntity.getAlipayBindingState());
                    map.put("alipayAccount", wCashAccountEntity.getAlipayAccount());
                    map.put("realName", wCashAccountEntity.getRealName());
                    mybalanceList.add(map);
                }
            } else {
                map.put("alipayBindingState", "0");
                mybalanceList.add(map);
            }

            jsonMap.put("mybalanceList", mybalanceList);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        }
    }

    public ResponseObject myIncome(String uid, String token) {
        Map<String, Object> jsonMap = new HashMap();
        String sql = "FROM WCapitalEntity WHERE type= 'income' and userId ='" + uid + "' order by tradeTime desc ";
        List<WCapitalEntity> wCapital = this.wCapitalService.findByQueryString(sql);
        List<Map> wCapitalList = new ArrayList();
        if(wCapital != null && wCapital.size() > 0) {
            for(int a = 0; a < wCapital.size(); ++a) {
                WCapitalEntity wCapitalEntity = (WCapitalEntity)wCapital.get(a);
                Map map = new HashMap();
                map.put("description", wCapitalEntity.getDescription());
                map.put("orderid", wCapitalEntity.getOrderNumber());
                map.put("orderNumber", wCapitalEntity.getOrderNumber());
                map.put("tradeTime", wCapitalEntity.getTradeTime());
                map.put("amout", wCapitalEntity.getAmout());
                wCapitalList.add(map);
            }
        }

        jsonMap.put("uid", uid);
        jsonMap.put("token", token);
        jsonMap.put("wCapitalList", wCapitalList);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject myExpenditure(String uid, String token) {
        Map<String, Object> jsonMap = new HashMap();
        String sql = "FROM WCapitalEntity WHERE type IN('Withdrawals','expenditure') and userId ='" + uid + "' order by tradeTime desc ";
        List<WCapitalEntity> wCapital = this.wCapitalService.findByQueryString(sql);
        List<Map> wCapitalList = new ArrayList();
        if(wCapital != null && wCapital.size() > 0) {
            for(int a = 0; a < wCapital.size(); ++a) {
                WCapitalEntity wCapitalEntity = (WCapitalEntity)wCapital.get(a);
                Map map = new HashMap();
                map.put("description", wCapitalEntity.getDescription());
                map.put("tradeTime", wCapitalEntity.getTradeTime());
                map.put("orderid", wCapitalEntity.getOrderNumber());
                map.put("amout", wCapitalEntity.getAmout());
                wCapitalList.add(map);
            }
        }

        jsonMap.put("uid", uid);
        jsonMap.put("token", token);
        jsonMap.put("wCapitalList", wCapitalList);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject myBindingAlipayNumber(String uid, String token) {
        Map<String, Object> jsonMap = new HashMap();
        List<WCashAccountEntity> WCashAccount = this.wCashAccountService.findByProperty(WCashAccountEntity.class, "userId", uid);
        if(WCashAccount != null && WCashAccount.size() > 0) {
            for(int i = 0; i < WCashAccount.size(); ++i) {
                WCashAccountEntity wCashAccountEntity = (WCashAccountEntity)WCashAccount.get(i);
                jsonMap.put("alipayAccount", wCashAccountEntity.getAlipayAccount());
            }
        } else {
            jsonMap.put("alipayAccount", "");
        }

        jsonMap.put("uid", uid);
        jsonMap.put("token", token);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject myBindingAlipay(String uid, String token, String alipayAccount, String realName) {
        Map<String, Object> jsonMap = new HashMap();
        TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        if(tSBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
        } else {
            List<WCashAccountEntity> WCashAlipayAccount = this.wCashAccountService.findByProperty(WCashAccountEntity.class, "alipayAccount", alipayAccount);
            WCashAccountEntity wCashAccountEntity1;
            if(WCashAlipayAccount.size() > 0) {
                for(int i = 0; i < WCashAlipayAccount.size(); ++i) {
                    wCashAccountEntity1 = (WCashAccountEntity)WCashAlipayAccount.get(i);
                    if(!uid.equals(wCashAccountEntity1.getUserId())) {
                        return CommonUtils.repsonseOjbectToClientWithBody("20038", (Object)null, new String[0]);
                    }
                }
            }

            List<WCashAccountEntity> WCashAccount = this.wCashAccountService.findByProperty(WCashAccountEntity.class, "userId", uid);
            if(WCashAccount.size() > 0) {
                for(int i = 0; i < WCashAccount.size(); ++i) {
                    WCashAccountEntity wCashAccountEntity = (WCashAccountEntity)WCashAccount.get(i);
                    wCashAccountEntity.setAlipayAccount(alipayAccount);
                    wCashAccountEntity.setRealName(realName);
                    wCashAccountEntity.setAlipayBindingState("1");
                    this.wCashAccountService.saveOrUpdate(wCashAccountEntity);
                }
            } else {
                wCashAccountEntity1 = new WCashAccountEntity();
                wCashAccountEntity1.setId(UUIDUtil.getId());
                wCashAccountEntity1.setAlipayAccount(alipayAccount);
                wCashAccountEntity1.setRealName(realName);
                wCashAccountEntity1.setAlipayBindingState("1");
                wCashAccountEntity1.setUserId(uid);
                wCashAccountEntity1.setUserName(tSBaseUser.getUserName());
                this.wCashAccountService.save(wCashAccountEntity1);
            }

            jsonMap.put("uid", uid);
            jsonMap.put("token", token);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        }
    }

    public ResponseObject myWithdrawals(String uid, String token) {
        Map<String, Object> jsonMap = new HashMap();
        Map map = new HashMap();
        List<Map> withdrawalsList = new ArrayList();
        TSBaseUser tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        if(tsBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
        } else {
            List<WCashAccountEntity> wCashAccount = this.wCashAccountService.findByProperty(WCashAccountEntity.class, "userId", uid);
            if(wCashAccount != null && wCashAccount.size() > 0) {
                for(int i = 0; i < wCashAccount.size(); ++i) {
                    WCashAccountEntity wCashAccountEntity = (WCashAccountEntity)wCashAccount.get(i);
                    map.put("AlipayAccount", wCashAccountEntity.getAlipayAccount());
                    map.put("AlipayBindingState", wCashAccountEntity.getAlipayBindingState());
                }
            } else {
                map.put("AlipayBindingState", "0");
                map.put("AlipayAccount", "");
            }

            withdrawalsList.add(map);
            jsonMap.put("balance", tsBaseUser.getBalance());
            jsonMap.put("uid", uid);
            jsonMap.put("token", token);
            jsonMap.put("withdrawalsList", withdrawalsList);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        }
    }

    public ResponseObject determinationCash(String uid, String token, String amout, String withdrawalsType, String accounts) {
        HashMap jsonMap = new HashMap();

        try {
            int b = 100;
            int a = Integer.parseInt(amout);
            if(a < b) {
                return CommonUtils.repsonseOjbectToClientWithBody("20030", (Object)null, new String[0]);
            }
        } catch (NumberFormatException var19) {
            var19.printStackTrace();
        }

        TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        if(tSBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
        } else {
            String balance;
            if(!"".equals(tSBaseUser.getBalance()) && tSBaseUser.getBalance() != null) {
                balance = tSBaseUser.getBalance();
            } else {
                balance = "0";
            }

            double price = Double.parseDouble(balance);
            double tax = Double.parseDouble(amout);
            BigDecimal b1 = new BigDecimal(Double.toString(price));
            BigDecimal b2 = new BigDecimal(Double.toString(tax));
            double doubleValue = b1.subtract(b2).doubleValue();
            if(doubleValue < 0.0D) {
                return CommonUtils.repsonseOjbectToClientWithBody("20013", jsonMap, new String[0]);
            } else {
                String balances = String.valueOf(doubleValue);
                tSBaseUser.setBalance(String.valueOf(balances));
                this.tSBaseUserService.saveOrUpdate(tSBaseUser);
                WCapitalEntity wCapital = new WCapitalEntity();
                wCapital.setId(UUIDUtil.getId());
                wCapital.setAmout(amout);
                wCapital.setPhone(tSBaseUser.getPhone());
                wCapital.setUserId(uid);
                wCapital.setUserName(tSBaseUser.getUserName());
                wCapital.setTransferType(withdrawalsType);
                wCapital.setIntoAccount(accounts);
                wCapital.setTradeTime(new Date());
                wCapital.setDescription("提现");
                wCapital.setType(capitalType.Withdrawals.toString());
                wCapital.setApprovalType("0");
                this.wCapitalService.save(wCapital);
                jsonMap.put("uid", uid);
                jsonMap.put("token", token);
                return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
            }
        }
    }
}
