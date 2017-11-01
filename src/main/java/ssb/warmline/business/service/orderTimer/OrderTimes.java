//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.service.orderTimer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ssb.warmline.business.commons.enums.CommissionType;
import ssb.warmline.business.commons.enums.HelpMessageState;
import ssb.warmline.business.commons.enums.HelpMessageType;
import ssb.warmline.business.commons.enums.Orderstatu;
import ssb.warmline.business.commons.enums.ReadingState;
import ssb.warmline.business.commons.enums.capitalType;
import ssb.warmline.business.commons.utils.PayStatus;
import ssb.warmline.business.entity.wcapital.WCapitalEntity;
import ssb.warmline.business.entity.wcommission.WCommissionEntity;
import ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity;
import ssb.warmline.business.entity.worder.WOrderEntity;
import ssb.warmline.business.entity.worderrecord.WOrderRecordEntity;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.wcapital.WCapitalServiceI;
import ssb.warmline.business.service.wcommission.WCommissionServiceI;
import ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;
import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;
import ssb.warmline.business.utils.JpushClientUtil;

@Component
public class OrderTimes {
    @Autowired
    WOrderServiceI wOrderService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private WHelpMessageServiceI wHelpMessageService;
    @Autowired
    private WCapitalServiceI wCapitalService;
    @Autowired
    private WOrderRecordServiceI wOrderRecordService;
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;
    @Autowired
    private WCommissionServiceI commissionService;

    public OrderTimes() {
    }

    @Scheduled(
            cron = "0 0 0/1 * * ?"
    )
    public synchronized void publishMessages() throws ParseException {
        System.out.println("********************************订单查询调度开始执行******************************************************");
        String sql = " FROM  WOrderEntity WHERE buyStatus ='PAY_002' AND seekStatus ='1' AND orderStatus ='ORDERSTATU_001'";
        List<WOrderEntity> incalidOrder = this.wOrderService.findByQueryString(sql);
        if(incalidOrder.size() > 0) {
            for(int i = 0; i < incalidOrder.size(); ++i) {
                WOrderEntity wOrder = (WOrderEntity)incalidOrder.get(i);
                if(wOrder.getIsRefund() == null) {
                    Date newDate = new Date();
                    Date paymentTime = wOrder.getPaymentTime();
                    Date endTime = wOrder.getEndTime();
                    long nowTime_interval = newDate.getTime() / 1000L;
                    long endTime_interval = endTime.getTime() / 1000L;
                    long paymentTime_interval = (newDate.getTime() - paymentTime.getTime()) / 1000L;
                    if(nowTime_interval > endTime_interval || paymentTime_interval > 3600L) {
                        String orderId = wOrder.getId();
                        List<WHelpMessageEntity> wHelpMessageList = this.wHelpMessageService.findByQueryString("FROM  WHelpMessageEntity WHERE messageStatus ='APPLICATION' AND  messageType ='APPLICATION' AND orderid= '" + orderId + "'");
                        if(wHelpMessageList.size() > 0) {
                            WHelpMessageEntity wHelpMessageEntity;
                            TSBaseUser helpId;
                            for(int j = 0; j < wHelpMessageList.size(); ++j) {
                                wHelpMessageEntity = (WHelpMessageEntity)wHelpMessageList.get(j);
                                wHelpMessageEntity.setMessageType(HelpMessageType.INVALID.toString());
                                wHelpMessageEntity.setMessageStatus(HelpMessageType.INVALID.toString());
                                this.wHelpMessageService.saveOrUpdate(wHelpMessageEntity);
                                String content;
                                if(wHelpMessageEntity.getSeekHelpPeople() != null && !"".equals(wHelpMessageEntity.getSeekHelpPeople())) {
                                    content = wHelpMessageEntity.getSeekHelpPeople() + " 订单失效。";
                                } else {
                                    TSBaseUser issuerIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wHelpMessageEntity.getSeekHelpPeopleId());
                                    if(issuerIds != null) {
                                        content = issuerIds.getPhone() + " 订单失效。";
                                    } else {
                                        content = "订单失效。";
                                    }
                                }

                                WHelpMessageEntity helpMessage = new WHelpMessageEntity();
                                helpMessage.setSeekHelpPeopleId(wHelpMessageEntity.getSeekHelpPeopleId());
                                helpMessage.setSeekHelpPeople(wHelpMessageEntity.getSeekHelpPeople());
                                helpMessage.setHelpPeople(wHelpMessageEntity.getHelpPeople());
                                helpMessage.setHelpPeopleId(wHelpMessageEntity.getHelpPeopleId());
                                helpMessage.setContent(content);
                                helpMessage.setCreateDate(new Date());
                                helpMessage.setReadingState(ReadingState.No.toString());
                                helpMessage.setOrderid(wHelpMessageEntity.getOrderid());
                                helpMessage.setMessageType(HelpMessageType.INVALID.toString());
                                helpMessage.setMessageStatus(HelpMessageType.INVALID.toString());
                                helpMessage.setMessageState(HelpMessageState.HELP.toString());
                                this.wHelpMessageService.saveOrUpdate(helpMessage);
                                helpId = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wHelpMessageEntity.getHelpPeopleId());
                                if(helpId != null) {
                                    if("IOS".equals(helpId.getAppType())) {
                                        JpushClientUtil.jiGuangPushIos(wOrder.getId(), "HELP", "帮助订单新消息", content, helpId.getPhone());
                                    } else if("Android".equals(helpId.getAppType())) {
                                        JpushClientUtil.jiGuangPush(wOrder.getId(), "HELP", "帮助订单新消息", content, helpId.getPhone());
                                    }
                                }
                            }

                            String content1 = "尊敬的用户" + wOrder.getIssuer() + "您好，你有一条求助订单失效。";
                            wHelpMessageEntity = new WHelpMessageEntity();
                            wHelpMessageEntity.setSeekHelpPeopleId(wOrder.getIssuerId());
                            wHelpMessageEntity.setSeekHelpPeople(wOrder.getIssuer());
                            wHelpMessageEntity.setContent(content1);
                            wHelpMessageEntity.setCreateDate(new Date());
                            wHelpMessageEntity.setReadingState(ReadingState.No.toString());
                            wHelpMessageEntity.setOrderid(wOrder.getId());
                            wHelpMessageEntity.setMessageType(HelpMessageType.INVALID.toString());
                            wHelpMessageEntity.setMessageStatus(HelpMessageType.INVALID.toString());
                            wHelpMessageEntity.setMessageState(HelpMessageState.SEEKHELP.toString());
                            this.wHelpMessageService.saveOrUpdate(wHelpMessageEntity);
                            TSBaseUser issuerUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wOrder.getIssuerId());
                            if(issuerUser != null) {
                                if("IOS".equals(issuerUser.getAppType())) {
                                    JpushClientUtil.jiGuangPushIos(wOrder.getId(), "SEEKHELP", "求助订单新消息", content1, issuerUser.getPhone());
                                } else if("Android".equals(issuerUser.getAppType())) {
                                    JpushClientUtil.jiGuangPush(wOrder.getId(), "SEEKHELP", "求助订单新消息", content1, issuerUser.getPhone());
                                }
                            }

                            WOrderRecordEntity wOrderRecord = (WOrderRecordEntity)this.wOrderRecordService.findUniqueByProperty(WOrderRecordEntity.class, "orderId", wOrder.getId());
                            wOrderRecord.setOrderStatus(Orderstatu.ORDERSTATU_007.toString());
                            wOrderRecord.setDescription("订单已失效！");
                            wOrderRecord.setStatusTime(new Date());
                            this.wOrderRecordService.saveOrUpdate(wOrderRecord);
                            wOrder.setOrderStatus(Orderstatu.ORDERSTATU_007.toString());
                            wOrder.setBuyStatus(PayStatus.PAY_004.toString());
                            wOrder.setCancellationTime(new Date());
                            wOrder.setIsRefund("yes");
                            this.wOrderService.saveOrUpdate(wOrder);
                            helpId = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", wOrder.getIssuerId());
                            if(helpId != null) {
                                double price = Double.parseDouble(wOrder.getPrice());
                                BigDecimal b1 = new BigDecimal(Double.toString(price));
                                String balance = String.valueOf(b1);
                                if(helpId.getBalance() == null) {
                                    helpId.setBalance("0");
                                }

                                double singulars = Double.parseDouble(helpId.getBalance());
                                BigDecimal bigDecimals2 = new BigDecimal(Double.toString(singulars));
                                BigDecimal bigDecimals = new BigDecimal(balance);
                                BigDecimal bigDecimalAdd1 = bigDecimals.add(bigDecimals2);
                                double add1 = bigDecimalAdd1.doubleValue();
                                String singulars3 = String.valueOf(add1);
                                helpId.setBalance(singulars3);
                                this.tSBaseUserService.saveOrUpdate(helpId);
                                WCapitalEntity capitalEntity = new WCapitalEntity();
                                capitalEntity.setUserId(helpId.getId());
                                capitalEntity.setUserName(helpId.getRealName());
                                capitalEntity.setPhone(helpId.getPhone());
                                capitalEntity.setAmout(balance);
                                capitalEntity.setTradeTime(new Date());
                                capitalEntity.setType(capitalType.income.toString());
                                capitalEntity.setOrderId(wOrder.getId());
                                capitalEntity.setOrderNumber(wOrder.getOrderNumber());
                                capitalEntity.setDescription("订单失效退款");
                                this.wCapitalService.saveOrUpdate(capitalEntity);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("********************************订单查询调度执行结束******************************************************");
    }

    @Scheduled(
            cron = "0 0 0/48 * * ?"
    )
    public synchronized void submitOrder() {
        System.out.println("********************************未点击完成订单查询调度开始执行******************************************************");
        String sql = " FROM  WOrderEntity WHERE buyStatus in('PAY_002') AND orderStatus in('ORDERSTATU_003','ORDERSTATU_004')";
        List<WOrderEntity> incalidOrder = this.wOrderService.findByQueryString(sql);
        if(incalidOrder.size() > 0) {
            for(int i = 0; i < incalidOrder.size(); ++i) {
                WOrderEntity wOrder = (WOrderEntity)incalidOrder.get(i);
                wOrder.setOrderStatus(Orderstatu.ORDERSTATU_006.toString());
                wOrder.setCompletionTime(new Date());
                TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", wOrder.getOrderPersonId());
                if(tSBaseUser != null) {
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("commission");
                    String cst_rate = resourceBundle.getString("cst_rate");
                    String rate = resourceBundle.getString("rate");
                    double price = Double.parseDouble(wOrder.getPrice());
                    double tax = 0.9D;
                    if(cst_rate != null && !"".equals(cst_rate)) {
                        double cst_rate1 = Double.parseDouble(resourceBundle.getString("cst_rate"));
                        tax = cst_rate1;
                    }

                    BigDecimal b1 = new BigDecimal(Double.toString(price));
                    BigDecimal b2 = new BigDecimal(Double.toString(tax));
                    double price1 = Double.parseDouble(wOrder.getPrice());
                    double tax1 = 0.1D;
                    if(rate != null && !"".equals(rate)) {
                        double rate1 = Double.parseDouble(resourceBundle.getString("rate"));
                        tax1 = rate1;
                    }

                    BigDecimal c1 = new BigDecimal(Double.toString(price1));
                    BigDecimal c2 = new BigDecimal(Double.toString(tax1));
                    WCapitalEntity capitalEntity = new WCapitalEntity();
                    capitalEntity.setUserId(wOrder.getOrderPersonId());
                    capitalEntity.setUserName(wOrder.getOrderPersonName());
                    capitalEntity.setPhone(wOrder.getOrderPersonPhone());
                    capitalEntity.setTradeTime(new Date());
                    capitalEntity.setType(capitalType.income.toString());
                    capitalEntity.setOrderId(wOrder.getId());
                    capitalEntity.setOrderNumber(wOrder.getOrderNumber());
                    capitalEntity.setDescription("完成求助订单");
                    WCommissionEntity commission = new WCommissionEntity();
                    commission.setUserId(wOrder.getOrderPersonId());
                    commission.setUserName(wOrder.getOrderPersonName());
                    commission.setPhone(wOrder.getOrderPersonPhone());
                    commission.setCreateTime(new Date());
                    commission.setOrderNumber(wOrder.getOrderNumber());
                    commission.setCommissionType(CommissionType.COMPLETIONOFORDER.toString());
                    commission.setOrderPersonId(wOrder.getOrderPersonId());
                    commission.setOrderPersonName(wOrder.getOrderPersonName());
                    commission.setSeekStatus(wOrder.getSeekStatus());
                    String singular2 = tSBaseUser.getSingular();
                    int valueOf = Integer.valueOf(singular2).intValue();
                    tSBaseUser.setSingular(String.valueOf(valueOf + 1));
                    double doubleValue = b1.multiply(b2).doubleValue();
                    String balance = String.valueOf(doubleValue);
                    double doubleValue1 = c1.multiply(c2).doubleValue();
                    String balance1 = String.valueOf(doubleValue1);
                    capitalEntity.setAmout(balance);
                    if(tSBaseUser.getBalance() == null) {
                        tSBaseUser.setBalance("0");
                    }

                    double singulars = Double.parseDouble(tSBaseUser.getBalance());
                    BigDecimal bigDecimals2 = new BigDecimal(Double.toString(singulars));
                    BigDecimal bigDecimals = new BigDecimal(balance);
                    BigDecimal bigDecimalAdd1 = bigDecimals.add(bigDecimals2);
                    double add1 = bigDecimalAdd1.doubleValue();
                    String singulars3 = String.valueOf(add1);
                    tSBaseUser.setBalance(singulars3);
                    commission.setAmount(balance1);
                    this.wOrderService.saveOrUpdate(wOrder);
                    this.tSBaseUserService.saveOrUpdate(tSBaseUser);
                    this.wCapitalService.saveOrUpdate(capitalEntity);
                    this.commissionService.saveOrUpdate(commission);
                }
            }
        }

        System.out.println("********************************未点击完成订单查询调度执行结束******************************************************");
    }
}
