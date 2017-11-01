//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.service.businessprocessor.app;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ssb.warmline.business.commons.config.ResponseObject;
import ssb.warmline.business.commons.enums.CommissionType;
import ssb.warmline.business.commons.enums.HelpMessageState;
import ssb.warmline.business.commons.enums.HelpMessageType;
import ssb.warmline.business.commons.enums.Orderstatu;
import ssb.warmline.business.commons.enums.ReadingState;
import ssb.warmline.business.commons.enums.capitalType;
import ssb.warmline.business.commons.shortMessage.PhoneCode;
import ssb.warmline.business.commons.utils.CommonUtils;
import ssb.warmline.business.commons.utils.PayStatus;
import ssb.warmline.business.entity.wcapital.WCapitalEntity;
import ssb.warmline.business.entity.wcomment.WCommentEntity;
import ssb.warmline.business.entity.wcommission.WCommissionEntity;
import ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity;
import ssb.warmline.business.entity.worder.WOrderEntity;
import ssb.warmline.business.entity.worderrecord.WOrderRecordEntity;
import ssb.warmline.business.service.businessprocessor.BaseInterface;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.wcapital.WCapitalServiceI;
import ssb.warmline.business.service.wcomment.WCommentServiceI;
import ssb.warmline.business.service.wcommission.WCommissionServiceI;
import ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;
import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;
import ssb.warmline.business.utils.JpushClientUtil;

@Service
public class MyOrderServiceProcessor extends BaseInterface {
    @Autowired
    private WOrderServiceI wOrderService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private WHelpMessageServiceI wHelpMessageService;
    @Autowired
    private WCommentServiceI wCommentService;
    @Autowired
    private WCommissionServiceI commissionService;
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;
    @Autowired
    private WCapitalServiceI wCapitalService;
    @Autowired
    private WOrderRecordServiceI wOrderRecordService;

    public MyOrderServiceProcessor() {
    }

    public ResponseObject mySeekHelpOrder(String uid, String token, Integer orderType, Integer page, Integer rows) {
        Map<String, Object> jsonMap = new HashMap();
        this.invalidOrder(uid);
        String types = "";
        String orderStatus = "";
        String sql = "";
        if(orderType.intValue() == 1) {
            types = "ORDERSTATU_001";
        } else if(orderType.intValue() == 2) {
            types = "ORDERSTATU_002";
        } else if(orderType.intValue() == 3) {
            types = "ORDERSTATU_003";
        } else if(orderType.intValue() == 4) {
            types = "ORDERSTATU_004";
        } else if(orderType.intValue() == 5) {
            types = "ORDERSTATU_005";
        } else if(orderType.intValue() == 6) {
            types = "ORDERSTATU_006";
        }

        String OrderTypes = String.valueOf(orderType);
        if("0".equals(OrderTypes)) {
            sql = "SELECT wo.id AS id,wo.order_Number AS orderNumber, wo.state AS state ,wo.price AS price,wo.order_time AS orderTime, wo.city AS city, wo.seek_status AS seekStatus ,wo.order_status AS orderStatus , wo.start_time AS startTime , \two.title AS title,wo.issuer_id AS issuerId, wo.end_time AS endTime  FROM w_order wo WHERE wo.issuer_id='" + uid + "' AND wo.buy_status in ('PAY_002','PAY_004') ORDER BY start_time DESC ";
        } else if(!"1".equals(OrderTypes) && !"2".equals(OrderTypes) && !"6".equals(OrderTypes)) {
            if(!"3".equals(OrderTypes) && !"4".equals(OrderTypes)) {
                if("5".equals(OrderTypes)) {
                    sql = "SELECT wo.id AS id,wo.price AS price,wo.order_Number AS orderNumber, wo.state AS state, wo.order_time AS orderTime, wo.city AS city, wo.seek_status AS seekStatus ,wo.order_status AS orderStatus , wo.start_time AS startTime , \two.title AS title,wo.issuer_id AS issuerId, wo.end_time AS endTime FROM w_order wo WHERE wo.issuer_id='" + uid + "' AND wo.order_status in('ORDERSTATU_005','ORDERSTATU_007')  AND wo.buy_status in ('PAY_004') ORDER BY start_time DESC ";
                }
            } else {
                sql = "SELECT wo.id AS id,wo.price AS price,wo.order_Number AS orderNumber, wo.state AS state, wo.order_time AS orderTime, wo.city AS city, wo.seek_status AS seekStatus ,wo.order_status AS orderStatus , wo.start_time AS startTime , \two.title AS title,wo.issuer_id AS issuerId, wo.end_time AS endTime FROM w_order wo WHERE wo.issuer_id='" + uid + "' AND wo.order_status in('ORDERSTATU_003','ORDERSTATU_004')   AND wo.buy_status ='PAY_002' ORDER BY start_time DESC ";
            }
        } else {
            sql = "SELECT wo.id AS id,wo.price AS price,wo.order_Number AS orderNumber, wo.state AS state, wo.order_time AS orderTime, wo.city AS city, wo.seek_status AS seekStatus ,wo.order_status AS orderStatus , wo.start_time AS startTime , \two.title AS title,wo.issuer_id AS issuerId, wo.end_time AS endTime FROM w_order wo WHERE wo.issuer_id='" + uid + "' AND wo.order_status='" + types + "'  AND wo.buy_status in ('PAY_002') ORDER BY start_time DESC ";
        }

        List<WOrderEntity> findObjForJdbc = this.wOrderService.findObjForJdbc(sql, page.intValue(), rows.intValue(), WOrderEntity.class);
        List<Map> orderList = new ArrayList();
        if(findObjForJdbc != null && findObjForJdbc.size() > 0) {
            for(int a = 0; a < findObjForJdbc.size(); ++a) {
                Map map = new HashMap();
                WOrderEntity bmg = (WOrderEntity)findObjForJdbc.get(a);
                TSBaseUser tsBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", bmg.getIssuerId());
                if(tsBaseUser == null || "".equals(tsBaseUser)) {
                    return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
                }

                if(tsBaseUser.getHeadPortrait() != null && !"".equals(tsBaseUser.getHeadPortrait())) {
                    map.put("headPortrait", tsBaseUser.getHeadPortrait());
                } else {
                    map.put("headPortrait", (Object)null);
                }

                map.put("orderId", bmg.getId());
                map.put("orderNumber", bmg.getOrderNumber());
                map.put("price", bmg.getPrice());
                map.put("OrderTime", bmg.getOrderTime());
                map.put("state", bmg.getState());
                map.put("city", bmg.getCity());
                map.put("startTime", bmg.getStartTime());
                map.put("endTime", bmg.getEndTime());
                map.put("seekStatus", bmg.getSeekStatus());
                map.put("title", bmg.getTitle());
                String orderStatus2 = bmg.getOrderStatus();
                if("ORDERSTATU_001".equals(orderStatus2)) {
                    orderStatus = "待接单";
                } else if("ORDERSTATU_002".equals(orderStatus2)) {
                    orderStatus = "服务中";
                } else if("ORDERSTATU_003".equals(orderStatus2)) {
                    orderStatus = "待评价";
                } else if("ORDERSTATU_004".equals(orderStatus2)) {
                    orderStatus = "已评价";
                } else if("ORDERSTATU_005".equals(orderStatus2)) {
                    orderStatus = "取消";
                } else if("ORDERSTATU_006".equals(orderStatus2)) {
                    orderStatus = "已完成";
                } else if("ORDERSTATU_007".equals(orderStatus2)) {
                    orderStatus = "已失效";
                }

                map.put("orderStatus2", orderStatus2);
                map.put("orderStatus", orderStatus);
                orderList.add(map);
            }
        }

        jsonMap.put("mySeekOrderList", orderList);
        jsonMap.put("uid", uid);
        jsonMap.put("token", token);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject mySeekHelpOrderDetails(String uid, String token, String orderPosition, String orderId) {
        Map<String, Object> jsonMap = new HashMap();
        if("help".equals(orderPosition)) {
            jsonMap.put("orderPosition", "帮助订单详情");
        } else if("seekHelp".equals(orderPosition)) {
            jsonMap.put("orderPosition", "求助订单详情");
        }

        WOrderEntity worder = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
        if(worder == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20025", (Object)null, new String[0]);
        } else {
            TSBaseUser tSBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
            if(tSBaseUser == null) {
                return CommonUtils.repsonseOjbectToClientWithBody("20020", (Object)null, new String[0]);
            } else {
                List<Map> orderList = new ArrayList();
                Map map = new HashMap();
                map.put("orderId", worder.getId());
                map.put("orderNumber", worder.getOrderNumber());
                map.put("seekStatus", worder.getSeekStatus());
                map.put("title", worder.getTitle());
                map.put("description", worder.getDescription());
                map.put("city", worder.getCity());
                map.put("state", worder.getState());
                map.put("price", worder.getPrice());
                map.put("orderStatus", worder.getOrderStatus());
                map.put("orderTime", worder.getOrderTime());
                map.put("stateTime", worder.getStartTime());
                map.put("endTime", worder.getEndTime());
                if("alipay".equals(worder.getPaymentMode())) {
                    map.put("payMode", "支付宝");
                } else if("weChat".equals(worder.getPaymentMode())) {
                    map.put("payMode", "微信");
                } else if("wallet".equals(worder.getPaymentMode())) {
                    map.put("payMode", "钱包");
                } else if("bank".equals(worder.getPaymentMode())) {
                    map.put("payMode", "银行卡");
                } else {
                    map.put("payMode", worder.getPaymentMode());
                }

                map.put("orderPersonId", worder.getOrderPersonId());
                if(worder.getPaymentTime() != null && !"".equals(worder.getPaymentTime())) {
                    map.put("paymentTime", worder.getPaymentTime());
                } else {
                    map.put("paymentTime", (Object)null);
                }

                if(worder.getDetermineServiceTime() != null && !"".equals(worder.getDetermineServiceTime())) {
                    map.put("determineServiceTime", worder.getDetermineServiceTime());
                } else {
                    map.put("determineServiceTime", (Object)null);
                }

                if(worder.getServiceCompletionTime() != null && !"".equals(worder.getServiceCompletionTime())) {
                    map.put("serviceCompletionTime", worder.getServiceCompletionTime());
                } else {
                    map.put("serviceCompletionTime", (Object)null);
                }

                if(worder.getCompletionTime() != null && !"".equals(worder.getCompletionTime())) {
                    map.put("completionTime", worder.getCompletionTime());
                } else {
                    map.put("completionTime", (Object)null);
                }

                if(worder.getCancellationTime() != null && !"".equals(worder.getCancellationTime())) {
                    map.put("cancellationTime", worder.getCancellationTime());
                } else {
                    map.put("cancellationTime", worder.getCancellationTime());
                }

                if(!"".equals(worder.getOrderPersonName()) && worder.getOrderPersonName() != null) {
                    map.put("orderPersonName", worder.getOrderPersonName());
                } else {
                    map.put("orderPersonName", (Object)null);
                }

                if(tSBaseUser.getBalance() != null && !"".equals(tSBaseUser.getBalance())) {
                    map.put("balance", tSBaseUser.getBalance());
                } else {
                    map.put("balance", "0");
                }

                orderList.add(map);
                jsonMap.put("orderListDetails", orderList);
                jsonMap.put("uid", uid);
                jsonMap.put("token", token);
                return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
            }
        }
    }

    public ResponseObject myChoiceConnect(String uid, String token, String orderId, Integer page, Integer rows) {
        Map<String, Object> jsonMap = new HashMap();
        WOrderEntity wOrderEntity = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
        Date currentTime = new Date();
        Date endTime = wOrderEntity.getEndTime();
        if(endTime.getTime() < currentTime.getTime()) {
            return CommonUtils.repsonseOjbectToClientWithBody("20037", (Object)null, new String[0]);
        } else {
            String sql = "SELECT whm.id AS id , whm.help_people AS helpPeople , whm.help_people_id AS helpPeopleId ,whm.create_date AS createDate ,whm.order_id AS orderid  FROM w_help_message whm WHERE whm.order_id ='" + orderId + "' AND message_state='SEEKHELP' AND message_type ='APPLICATION' ORDER BY whm.create_date DESC";
            List<WHelpMessageEntity> helpMessage = this.wHelpMessageService.findObjForJdbc(sql, page.intValue(), rows.intValue(), WHelpMessageEntity.class);
            List<Map> helpMessagePeopleList = new ArrayList();
            if(helpMessage != null && helpMessage.size() > 0) {
                Iterator var14 = helpMessage.iterator();

                while(var14.hasNext()) {
                    WHelpMessageEntity helpMessages = (WHelpMessageEntity)var14.next();
                    Map map = new HashMap();
                    TSBaseUser tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", helpMessages.getHelpPeopleId());
                    if(tsBaseUser == null) {
                        return CommonUtils.repsonseOjbectToClientWithBody("20036", (Object)null, new String[0]);
                    }

                    Long helepCount1 = this.commonService.getCountForJdbc("SELECT IFNULL(SUM(wc.comment_star),0) FROM w_comment wc WHERE wc.issuer_id='" + tsBaseUser.getId() + "'");
                    List<WCommentEntity> WCommentEntity = this.wCommentService.findByProperty(WCommentEntity.class, "issuerId", tsBaseUser.getId());
                    int helepCount2 = (new Long(helepCount1.longValue())).intValue();
                    if(helepCount2 > 0) {
                        int size = helepCount2 / WCommentEntity.size();
                        DecimalFormat df = new DecimalFormat("0.0");
                        String score = df.format((long)size);
                        map.put("score", score);
                    } else {
                        map.put("score", Integer.valueOf(0));
                    }

                    if(tsBaseUser.getHeadPortrait() == null) {
                        map.put("HeadPortrait", (Object)null);
                    } else {
                        map.put("HeadPortrait", tsBaseUser.getHeadPortrait());
                    }

                    if(tsBaseUser.getUserName() != null && !"".equals(tsBaseUser.getUserName())) {
                        map.put("userName", tsBaseUser.getUserName());
                    } else {
                        map.put("userName", tsBaseUser.getPhone());
                    }

                    map.put("certification", tsBaseUser.getCertification());
                    map.put("helpPeople", helpMessages.getHelpPeople());
                    map.put("helpPeopleId", helpMessages.getHelpPeopleId());
                    map.put("createDate", helpMessages.getCreateDate());
                    map.put("orderId", helpMessages.getOrderid());
                    map.put("phone", tsBaseUser.getPhone());
                    map.put("orderPhone", wOrderEntity.getOrderPhone());
                    helpMessagePeopleList.add(map);
                }
            }

            int size = helpMessage.size();
            jsonMap.put("helpMessageSize", Integer.valueOf(size));
            jsonMap.put("helpMessagePeople", helpMessagePeopleList);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        }
    }

    public ResponseObject orderCancellation(String uid, String token, String orderId) {
        Map<String, Object> jsonMap = new HashMap();
        WOrderEntity WOrder = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
        if(WOrder == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20025", (Object)null, new String[0]);
        } else if("ORDERSTATU_005".equals(WOrder.getOrderStatus())) {
            return CommonUtils.repsonseOjbectToClientWithBody("20026", (Object)null, new String[0]);
        } else {
            WOrder.setOrderStatus(Orderstatu.ORDERSTATU_005.toString());
            WOrder.setBuyStatus(PayStatus.PAY_004.toString());
            WOrder.setCancellationTime(new Date());
            TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", WOrder.getIssuerId());
            if(tSBaseUser == null) {
                return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
            } else {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("commission");
                String cst_rate = resourceBundle.getString("cst_rate");
                String rate = resourceBundle.getString("rate");
                double price = Double.parseDouble(WOrder.getPrice());
                double tax = 0.9D;
                if(cst_rate != null && !"".equals(cst_rate)) {
                    double cst_rate1 = Double.parseDouble(resourceBundle.getString("cst_rate"));
                    tax = cst_rate1;
                }

                BigDecimal b1 = new BigDecimal(Double.toString(price));
                BigDecimal b2 = new BigDecimal(Double.toString(tax));
                double doubleValue = b1.multiply(b2).doubleValue();
                String balance = String.valueOf(doubleValue);
                double price1 = Double.parseDouble(WOrder.getPrice());
                double tax1 = 0.1D;
                if(rate != null && !"".equals(rate)) {
                    double rate1 = Double.parseDouble(resourceBundle.getString("rate"));
                    tax1 = rate1;
                }

                BigDecimal c1 = new BigDecimal(Double.toString(price1));
                BigDecimal c2 = new BigDecimal(Double.toString(tax1));
                WCapitalEntity capitalEntity;
                double singulars;
                if("0".equals(WOrder.getSeekStatus())) {
                    WCommissionEntity commissionEntity = new WCommissionEntity();
                    commissionEntity.setId(UUID.randomUUID().toString());
                    commissionEntity.setUserId(WOrder.getIssuerId());
                    commissionEntity.setUserName(WOrder.getIssuer());
                    commissionEntity.setPhone(WOrder.getPhone());
                    commissionEntity.setCreateTime(new Date());
                    commissionEntity.setOrderNumber(WOrder.getOrderNumber());
                    singulars = c1.multiply(c2).doubleValue();
                    String balance1 = String.valueOf(singulars);
                    commissionEntity.setAmount(balance1);
                    commissionEntity.setCommissionType(CommissionType.CANCEL.toString());
                    commissionEntity.setOrderPersonId(WOrder.getOrderPersonId());
                    commissionEntity.setOrderPersonName(WOrder.getOrderPersonName());
                    commissionEntity.setSeekStatus(WOrder.getSeekStatus());
                    if(tSBaseUser.getBalance() == null) {
                        tSBaseUser.setBalance("0");
                    }

                    singulars = Double.parseDouble(tSBaseUser.getBalance());
                    BigDecimal bigDecimals2 = new BigDecimal(Double.toString(singulars));
                    BigDecimal bigDecimals = new BigDecimal(balance);
                    BigDecimal bigDecimalAdd1 = bigDecimals.add(bigDecimals2);
                    double add1 = bigDecimalAdd1.doubleValue();
                    String singulars3 = String.valueOf(add1);
                    tSBaseUser.setBalance(singulars3);
                    capitalEntity = new WCapitalEntity();
                    capitalEntity.setId(UUID.randomUUID().toString());
                    capitalEntity.setUserId(uid);
                    capitalEntity.setUserName(tSBaseUser.getRealName());
                    capitalEntity.setPhone(tSBaseUser.getPhone());
                    capitalEntity.setAmout(balance);
                    capitalEntity.setTradeTime(new Date());
                    capitalEntity.setType(capitalType.income.toString());
                    capitalEntity.setOrderId(orderId);
                    capitalEntity.setOrderNumber(WOrder.getOrderNumber());
                    capitalEntity.setDescription("取消订单退款");
                    this.commissionService.save(commissionEntity);
                } else {
                    List<WHelpMessageEntity> helpMessageEntities = this.wHelpMessageService.findByProperty(WHelpMessageEntity.class, "orderid", orderId);
                    BigDecimal bigDecimals2;
                    BigDecimal bigDecimals;
                    if(helpMessageEntities.size() > 0) {
                        capitalEntity = new WCapitalEntity();
                        capitalEntity.setId(UUID.randomUUID().toString());
                        capitalEntity.setUserId(uid);
                        capitalEntity.setUserName(tSBaseUser.getRealName());
                        capitalEntity.setPhone(tSBaseUser.getPhone());
                        capitalEntity.setAmout(balance);
                        capitalEntity.setTradeTime(new Date());
                        capitalEntity.setType(capitalType.income.toString());
                        capitalEntity.setOrderId(orderId);
                        capitalEntity.setOrderNumber(WOrder.getOrderNumber());
                        capitalEntity.setDescription("取消订单退款");
                        if(tSBaseUser.getBalance() == null) {
                            tSBaseUser.setBalance("0");
                        }

                        singulars = Double.parseDouble(tSBaseUser.getBalance());
                        bigDecimals2 = new BigDecimal(Double.toString(singulars));
                        bigDecimals = new BigDecimal(balance);
                        BigDecimal bigDecimalAdd1 = bigDecimals.add(bigDecimals2);
                        double add1 = bigDecimalAdd1.doubleValue();
                        String singulars3 = String.valueOf(add1);
                        tSBaseUser.setBalance(singulars3);
                        WCommissionEntity commissionEntity = new WCommissionEntity();
                        commissionEntity.setId(UUID.randomUUID().toString());
                        commissionEntity.setUserId(WOrder.getIssuerId());
                        commissionEntity.setUserName(WOrder.getIssuer());
                        commissionEntity.setPhone(WOrder.getPhone());
                        commissionEntity.setCreateTime(new Date());
                        commissionEntity.setOrderNumber(WOrder.getOrderNumber());
                        double doubleValue1 = c1.multiply(c2).doubleValue();
                        String balance1 = String.valueOf(doubleValue1);
                        commissionEntity.setAmount(balance1);
                        commissionEntity.setCommissionType(CommissionType.CANCEL.toString());
                        commissionEntity.setOrderPersonId(WOrder.getOrderPersonId());
                        commissionEntity.setOrderPersonName(WOrder.getOrderPersonName());
                        commissionEntity.setSeekStatus(WOrder.getSeekStatus());
                        this.commissionService.save(commissionEntity);

                        for(int a = 0; a < helpMessageEntities.size(); ++a) {
                            WHelpMessageEntity wHelpMessage = (WHelpMessageEntity)helpMessageEntities.get(a);
                            wHelpMessage.setMessageType(HelpMessageType.CANCEL.toString());
                            this.wHelpMessageService.saveOrUpdate(wHelpMessage);
                            String content;
                            if(wHelpMessage.getSeekHelpPeople() != null && !"".equals(wHelpMessage.getSeekHelpPeople())) {
                                content = wHelpMessage.getSeekHelpPeople() + " 取消了求助订单。";
                            } else {
                                TSBaseUser issuerIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wHelpMessage.getHelpPeopleId());
                                if(issuerIds != null) {
                                    content = issuerIds.getPhone() + " 取消了求助订单。";
                                } else {
                                    content = "   取消了求助订单。";
                                }
                            }

                            WHelpMessageEntity helpMessage = new WHelpMessageEntity();
                            helpMessage.setId(UUID.randomUUID().toString());
                            helpMessage.setSeekHelpPeopleId(wHelpMessage.getSeekHelpPeopleId());
                            helpMessage.setSeekHelpPeople(wHelpMessage.getSeekHelpPeople());
                            helpMessage.setHelpPeople(wHelpMessage.getHelpPeople());
                            helpMessage.setHelpPeopleId(wHelpMessage.getHelpPeopleId());
                            helpMessage.setContent(content);
                            helpMessage.setCreateDate(new Date());
                            helpMessage.setReadingState(ReadingState.No.toString());
                            helpMessage.setOrderid(wHelpMessage.getOrderid());
                            helpMessage.setMessageType(HelpMessageType.CANCEL.toString());
                            helpMessage.setMessageStatus(HelpMessageType.CANCEL.toString());
                            helpMessage.setMessageState(HelpMessageState.HELP.toString());
                            this.wHelpMessageService.save(helpMessage);
                            TSBaseUser helpPeopleId = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wHelpMessage.getHelpPeopleId());
                            if(helpPeopleId == null) {
                                return CommonUtils.repsonseOjbectToClientWithBody("20049", (Object)null, new String[0]);
                            }

                            if("IOS".equals(helpPeopleId.getAppType())) {
                                JpushClientUtil.jiGuangPushIos(WOrder.getId(), "HELP", "帮助订单新消息", content, helpPeopleId.getPhone());
                            } else if("Android".equals(helpPeopleId.getAppType())) {
                                JpushClientUtil.jiGuangPush(WOrder.getId(), "HELP", "帮助订单新消息", content, helpPeopleId.getPhone());
                            }
                        }
                    } else {
                        if(tSBaseUser.getBalance() == null) {
                            tSBaseUser.setBalance("0");
                        }

                        singulars = Double.parseDouble(tSBaseUser.getBalance());
                        bigDecimals2 = new BigDecimal(Double.toString(singulars));
                        bigDecimals = b1.add(bigDecimals2);
                        double add1 = bigDecimals.doubleValue();
                        String singulars3 = String.valueOf(add1);
                        tSBaseUser.setBalance(singulars3);
                        capitalEntity = new WCapitalEntity();
                        capitalEntity.setId(UUID.randomUUID().toString());
                        capitalEntity.setUserId(uid);
                        capitalEntity.setUserName(tSBaseUser.getRealName());
                        capitalEntity.setPhone(tSBaseUser.getPhone());
                        capitalEntity.setAmout(b1.toString());
                        capitalEntity.setTradeTime(new Date());
                        capitalEntity.setType(capitalType.income.toString());
                        capitalEntity.setOrderId(orderId);
                        capitalEntity.setOrderNumber(WOrder.getOrderNumber());
                        capitalEntity.setDescription("取消订单退款");
                    }
                }

                this.wOrderService.saveOrUpdate(WOrder);
                this.tSBaseUserService.saveOrUpdate(tSBaseUser);
                this.wCapitalService.save(capitalEntity);
                WOrderRecordEntity wOrderRecord = (WOrderRecordEntity)this.wOrderRecordService.findUniqueByProperty(WOrderRecordEntity.class, "orderId", orderId);
                wOrderRecord.setOrderStatus(Orderstatu.ORDERSTATU_005.toString());
                wOrderRecord.setBuyStatus(PayStatus.PAY_004.toString());
                wOrderRecord.setDescription("订单取消！");
                wOrderRecord.setStatusTime(new Date());
                this.wOrderRecordService.saveOrUpdate(wOrderRecord);
                jsonMap.put("orderId", orderId);
                jsonMap.put("uid", uid);
                jsonMap.put("token", token);
                return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
            }
        }
    }

    public ResponseObject definiteReceiver(String uid, String token, String orderId, String helpPeopleId) {
        Map<String, Object> jsonMap = new HashMap();
        List<WHelpMessageEntity> helpMessageEntities = this.wHelpMessageService.findByProperty(WHelpMessageEntity.class, "orderid", orderId);
        TSBaseUser tSBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        if(tSBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20020", (Object)null, new String[0]);
        } else {
            WOrderEntity wOrder = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
            if(wOrder == null) {
                return CommonUtils.repsonseOjbectToClientWithBody("20025", (Object)null, new String[0]);
            } else {
                TSBaseUser helpPeopleIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", helpPeopleId);
                if(helpPeopleIds == null) {
                    return CommonUtils.repsonseOjbectToClientWithBody("20049", (Object)null, new String[0]);
                } else if(helpMessageEntities.size() <= 0) {
                    return CommonUtils.repsonseOjbectToClientWithBody("20015", jsonMap, new String[0]);
                } else {
                    for(int a = 0; a < helpMessageEntities.size(); ++a) {
                        WHelpMessageEntity wHelpMessage = (WHelpMessageEntity)helpMessageEntities.get(a);
                        String content;
                        String contents;
                        TSBaseUser helpIds;
                        if(helpPeopleId.equals(wHelpMessage.getHelpPeopleId())) {
                            wHelpMessage.setMessageType(HelpMessageType.AGREE.toString());
                            this.wHelpMessageService.saveOrUpdate(wHelpMessage);
                            if(wHelpMessage.getSeekHelpPeople() != null && !"".equals(wHelpMessage.getSeekHelpPeople())) {
                                content = "恭喜您，" + wHelpMessage.getSeekHelpPeople() + "选择了您帮助他。";
                                contents = "【暖行】 恭喜您，" + wHelpMessage.getSeekHelpPeople() + "选择了您帮助他，请及时查看。";
                            } else {
                                helpIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wHelpMessage.getSeekHelpPeopleId());
                                if(helpIds != null) {
                                    content = "恭喜您，" + helpIds.getPhone() + "选择了您帮助他。";
                                    contents = "【暖行】 恭喜您，" + helpIds.getPhone() + "选择了您帮助他，请及时查看。";
                                } else {
                                    content = "恭喜您，  选择了您帮助他。";
                                    contents = "【暖行】 恭喜您，  选择了您帮助他，请及时查看。";
                                }
                            }

                            WHelpMessageEntity helpMessage = new WHelpMessageEntity();
                            helpMessage.setId(UUID.randomUUID().toString());
                            helpMessage.setSeekHelpPeopleId(wHelpMessage.getSeekHelpPeopleId());
                            helpMessage.setSeekHelpPeople(wHelpMessage.getSeekHelpPeople());
                            helpMessage.setHelpPeople(wHelpMessage.getHelpPeople());
                            helpMessage.setHelpPeopleId(wHelpMessage.getHelpPeopleId());
                            helpMessage.setContent(content);
                            helpMessage.setCreateDate(new Date());
                            helpMessage.setReadingState(ReadingState.No.toString());
                            helpMessage.setOrderid(wHelpMessage.getOrderid());
                            helpMessage.setMessageStatus(HelpMessageType.AGREE.toString());
                            helpMessage.setMessageState(HelpMessageState.HELP.toString());
                            this.wHelpMessageService.save(helpMessage);
                            wOrder.setOrderPersonId(wHelpMessage.getHelpPeopleId());
                            wOrder.setOrderPersonName(wHelpMessage.getHelpPeople());
                            wOrder.setOrderPersonPhone(helpPeopleIds.getPhone());
                            wOrder.setOrderStatus(Orderstatu.ORDERSTATU_002.toString());
                            wOrder.setDetermineServiceTime(new Date());
                            this.wOrderService.saveOrUpdate(wOrder);
                            WOrderRecordEntity wOrderRecord = (WOrderRecordEntity)this.wOrderRecordService.findUniqueByProperty(WOrderRecordEntity.class, "orderId", wOrder.getId());
                            wOrderRecord.setOrderStatus(Orderstatu.ORDERSTATU_002.toString());
                            wOrderRecord.setDescription(helpMessage.getHelpPeople() + " 正在服务中！");
                            wOrderRecord.setStatusTime(new Date());
                            this.wOrderRecordService.saveOrUpdate(wOrderRecord);
                            if("IOS".equals(helpPeopleIds.getAppType())) {
                                JpushClientUtil.jiGuangPushIos(wOrder.getId(), "HELP", "帮助订单新消息", content, helpPeopleIds.getPhone());
                            } else if("Android".equals(helpPeopleIds.getAppType())) {
                                JpushClientUtil.jiGuangPush(wOrder.getId(), "HELP", "帮助订单新消息", content, helpPeopleIds.getPhone());
                            }

                            PhoneCode.getLoginPhoneOrderCode(helpPeopleIds.getAreaCode() + helpPeopleIds.getPhone(), contents);
                        } else {
                            wHelpMessage.setMessageType(HelpMessageType.REFUSE.toString());
                            this.wHelpMessageService.saveOrUpdate(wHelpMessage);
                            helpIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wHelpMessage.getHelpPeopleId());
                            if(wHelpMessage.getSeekHelpPeople() != null && !"".equals(wHelpMessage.getSeekHelpPeople())) {
                                content = "很遗憾，" + wHelpMessage.getSeekHelpPeople() + "选择别人帮助他。不要灰心，你可以继续帮助别人。";
                                contents = "【暖行】 很遗憾，" + wHelpMessage.getSeekHelpPeople() + "选择别人帮助他。不要灰心，你可以继续帮助别人。";
                            } else {
                                TSBaseUser issuerIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wHelpMessage.getSeekHelpPeopleId());
                                if(issuerIds != null) {
                                    content = "很遗憾，" + issuerIds.getPhone() + "选择别人帮助他。不要灰心，你可以继续帮助别人。";
                                    contents = "【暖行】 很遗憾，" + issuerIds.getPhone() + "选择别人帮助他。不要灰心，你可以继续帮助别人。";
                                } else {
                                    content = "很遗憾， 选择别人帮助他。不要灰心，你可以继续帮助别人。";
                                    contents = "【暖行】 很遗憾，  选择别人帮助他。不要灰心，你可以继续帮助别人。";
                                }
                            }

                            WHelpMessageEntity helpMessage = new WHelpMessageEntity();
                            helpMessage.setId(UUID.randomUUID().toString());
                            helpMessage.setSeekHelpPeopleId(wHelpMessage.getSeekHelpPeopleId());
                            helpMessage.setSeekHelpPeople(wHelpMessage.getSeekHelpPeople());
                            helpMessage.setHelpPeople(wHelpMessage.getHelpPeople());
                            helpMessage.setHelpPeopleId(wHelpMessage.getHelpPeopleId());
                            helpMessage.setContent(content);
                            helpMessage.setCreateDate(new Date());
                            helpMessage.setReadingState(ReadingState.No.toString());
                            helpMessage.setOrderid(wHelpMessage.getOrderid());
                            helpMessage.setMessageStatus(HelpMessageType.REFUSE.toString());
                            helpMessage.setMessageState(HelpMessageState.HELP.toString());
                            this.wHelpMessageService.save(helpMessage);
                            if("IOS".equals(helpPeopleIds.getAppType())) {
                                JpushClientUtil.jiGuangPushIos(wOrder.getId(), "HELP", "帮助订单新消息", content, helpIds.getPhone());
                            } else if("Android".equals(helpPeopleIds.getAppType())) {
                                JpushClientUtil.jiGuangPush(wOrder.getId(), "HELP", "帮助订单新消息", content, helpIds.getPhone());
                            }

                            PhoneCode.getLoginPhoneOrderCode(helpPeopleIds.getAreaCode() + helpIds.getPhone(), contents);
                        }
                    }

                    WOrderEntity wOrders = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
                    jsonMap.put("seekStatus", wOrders.getSeekStatus());
                    jsonMap.put("orderNumber", wOrders.getOrderNumber());
                    jsonMap.put("title", wOrders.getTitle());
                    jsonMap.put("description", wOrders.getDescription());
                    jsonMap.put("orderNumber", wOrders.getOrderNumber());
                    jsonMap.put("city", wOrders.getCity());
                    jsonMap.put("price", wOrders.getPrice());
                    jsonMap.put("orderStatus", wOrders.getOrderStatus());
                    jsonMap.put("orderId", wOrders.getId());
                    jsonMap.put("orderTime", wOrders.getOrderTime());
                    jsonMap.put("paymentMode", wOrders.getPaymentMode());
                    jsonMap.put("OrderPersonName", wOrders.getOrderPersonName());
                    jsonMap.put("OrderPersonId", wOrders.getOrderPersonId());
                    jsonMap.put("uid", uid);
                    jsonMap.put("token", token);
                    return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
                }
            }
        }
    }

    public ResponseObject definiteReceivers(String uid, String token, String orderId) {
        Map<String, Object> jsonMap = new HashMap();
        WOrderEntity WOrder = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
        if(WOrder == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20025", (Object)null, new String[0]);
        } else if(!"ORDERSTATU_002".equals(WOrder.getOrderStatus())) {
            return CommonUtils.repsonseOjbectToClientWithBody("20029", (Object)null, new String[0]);
        } else {
            String sql = "FROM WHelpMessageEntity WHERE helpPeopleId='" + WOrder.getOrderPersonId() + "' AND orderid = '" + orderId + "' AND messageType='AGREE' ";
            List<WHelpMessageEntity> helpMessage = this.wHelpMessageService.findByQueryString(sql);
            if(helpMessage.size() == 0) {
                return CommonUtils.repsonseOjbectToClientWithBody("20036", (Object)null, new String[0]);
            } else if(helpMessage.size() <= 0) {
                return CommonUtils.repsonseOjbectToClientWithBody("20004", (Object)null, new String[0]);
            } else {
                for(int a = 0; a < helpMessage.size(); ++a) {
                    WHelpMessageEntity helpMessages = (WHelpMessageEntity)helpMessage.get(a);
                    helpMessages.setMessageType(HelpMessageType.COMPLETE.toString());
                    WOrder.setOrderStatus(Orderstatu.ORDERSTATU_003.toString());
                    WOrder.setServiceCompletionTime(new Date());
                    this.wHelpMessageService.saveOrUpdate(helpMessages);
                    this.wOrderService.saveOrUpdate(WOrder);
                    WOrderRecordEntity wOrderRecord = (WOrderRecordEntity)this.wOrderRecordService.findUniqueByProperty(WOrderRecordEntity.class, "orderId", orderId);
                    wOrderRecord.setOrderStatus(Orderstatu.ORDERSTATU_003.toString());
                    wOrderRecord.setDescription("订单完成，待评价！");
                    wOrderRecord.setStatusTime(new Date());
                    this.wOrderRecordService.saveOrUpdate(wOrderRecord);
                }

                jsonMap.put("uid", uid);
                jsonMap.put("token", token);
                return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
            }
        }
    }

    public ResponseObject evaluate(String uid, String token, String orderId) {
        Map<String, Object> jsonMap = new HashMap();
        WOrderEntity WOrder = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
        if(WOrder != null) {
            jsonMap.put("orderPersonId", WOrder.getOrderPersonId());
            jsonMap.put("orderPersonName", WOrder.getOrderPersonName());
            jsonMap.put("orderId", orderId);
            jsonMap.put("uid", uid);
            jsonMap.put("token", token);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        } else {
            return CommonUtils.repsonseOjbectToClientWithBody("20025", (Object)null, new String[0]);
        }
    }

    public ResponseObject evaluateSubmit(String uid, String token, String orderId, String commentStar) throws UnsupportedEncodingException {
        Map<String, Object> jsonMap = new HashMap();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        request.setCharacterEncoding("UTF-8");
        String content = request.getParameter("content");
        WOrderEntity WOrder = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
        if(WOrder == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20025", (Object)null, new String[0]);
        } else {
            WCommentEntity commentEntity = new WCommentEntity();
            commentEntity.setId(UUID.randomUUID().toString());
            commentEntity.setCommentTime(new Date());
            commentEntity.setCommentStar(commentStar);
            if(!"".equals(content) && content != null) {
                commentEntity.setContent(content);
            } else {
                commentEntity.setContent("该评论没有内容！");
            }

            commentEntity.setIssuer(WOrder.getOrderPersonName());
            commentEntity.setIssuerId(WOrder.getOrderPersonId());
            commentEntity.setOrderId(orderId);
            commentEntity.setCriticId(uid);
            TSBaseUser baseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
            if(baseUser == null) {
                return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
            } else {
                commentEntity.setCriticName(baseUser.getUserName());
                WOrderRecordEntity wOrderRecord = (WOrderRecordEntity)this.wOrderRecordService.findUniqueByProperty(WOrderRecordEntity.class, "orderId", orderId);
                wOrderRecord.setOrderStatus(Orderstatu.ORDERSTATU_004.toString());
                wOrderRecord.setDescription("订单已经评价未点击完成！");
                wOrderRecord.setStatusTime(new Date());
                this.wOrderRecordService.saveOrUpdate(wOrderRecord);
                WOrder.setOrderStatus(Orderstatu.ORDERSTATU_004.toString());
                this.wOrderService.saveOrUpdate(WOrder);
                this.wCommentService.save(commentEntity);
                jsonMap.put("seekStatus", WOrder.getSeekStatus());
                jsonMap.put("title", WOrder.getTitle());
                jsonMap.put("description", WOrder.getDescription());
                jsonMap.put("city", WOrder.getCity());
                jsonMap.put("price", WOrder.getPrice());
                jsonMap.put("orderStatus", WOrder.getOrderStatus());
                jsonMap.put("orderTime", WOrder.getOrderTime());
                jsonMap.put("paymentMode", WOrder.getPaymentMode());
                jsonMap.put("OrderPersonName", WOrder.getOrderPersonName());
                jsonMap.put("OrderPersonId", WOrder.getOrderPersonId());
                jsonMap.put("orderId", orderId);
                jsonMap.put("uid", uid);
                jsonMap.put("token", token);
                return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
            }
        }
    }

    public ResponseObject myHelpOrderList(String uid, String token, Integer orderType, Integer page, Integer rows) {
        Map<String, Object> jsonMap = new HashMap();
        String orderStatus = "";
        String sql = "";
        String sql1 = "";
        String OrderTypes = String.valueOf(orderType);
        if("0".equals(OrderTypes)) {
            sql = " SELECT wo.id,wo.order_Number AS orderNumber,wo.price AS price,wo.order_time AS orderTime, wo.city AS city,wo.seek_status AS seekStatus,wo.order_status AS orderStatus ,wo.title AS title ,wo.state AS state ,  wo.order_person_id  AS orderPersonId , wo.order_person_name AS orderPersonName, wo.order_person_phone AS orderPersonPhone , wo.issuer_id AS issuerId FROM w_help_message whm LEFT JOIN w_order wo ON whm.order_id = wo.id WHERE whm.message_type IN ('APPLICATION') AND wo.order_status in ('ORDERSTATU_001') AND wo.buy_status in('PAY_002') AND help_people_id = '" + uid + "'";
            sql1 = " SELECT \two.id,wo.order_Number AS orderNumber,wo.price AS price,wo.order_time AS orderTime, wo.city AS city,wo.seek_status AS seekStatus,wo.order_status AS orderStatus , wo.title AS title ,wo.state AS state , wo.order_person_id  AS orderPersonId , wo.order_person_name AS orderPersonName,  wo.order_person_phone AS orderPersonPhone , wo.issuer_id AS issuerId FROM w_order wo WHERE wo.order_status in ('ORDERSTATU_001','ORDERSTATU_002','ORDERSTATU_003','ORDERSTATU_004','ORDERSTATU_005','ORDERSTATU_006','ORDERSTATU_007') AND wo.buy_status in ('PAY_002','PAY_004') AND  wo.order_person_id = '" + uid + "'";
        } else if("1".equals(OrderTypes)) {
            sql = "SELECT wo.id,wo.order_Number AS orderNumber,wo.price AS price,wo.order_time AS orderTime,wo.city AS city,wo.seek_status AS seekStatus,wo.order_status AS orderStatus , wo.title AS title ,wo.state AS state , wo.order_person_id  AS orderPersonId , wo.order_person_name AS orderPersonName, wo.order_person_phone AS orderPersonPhone  , wo.issuer_id AS issuerId  FROM w_help_message whm LEFT JOIN w_order wo ON whm.order_id = wo.id WHERE whm.message_type IN ('APPLICATION')  AND wo.buy_status in('PAY_002')  AND wo.order_status in ('ORDERSTATU_001')  AND help_people_id = '" + uid + "'";
        } else if("2".equals(OrderTypes)) {
            sql = " SELECT wo.id,wo.order_Number AS orderNumber,wo.price AS price,wo.order_time AS orderTime,wo.city AS city,wo.seek_status AS seekStatus,wo.order_status AS orderStatus , wo.title AS title ,wo.state AS state , wo.order_person_id  AS orderPersonId , wo.order_person_name AS orderPersonName, wo.order_person_phone AS orderPersonPhone  , wo.issuer_id AS issuerId FROM w_order wo WHERE wo.order_status in ('ORDERSTATU_002') AND wo.buy_status = 'PAY_002' AND  wo.order_person_id = '" + uid + "'";
        } else if("3".equals(OrderTypes)) {
            sql = " SELECT wo.id,wo.order_Number AS orderNumber,wo.price AS price,wo.order_time AS orderTime,wo.city AS city,wo.seek_status AS seekStatus,wo.order_status AS orderStatus , wo.title AS title ,wo.state AS state , wo.order_person_id  AS orderPersonId , wo.order_person_name AS orderPersonName, wo.order_person_phone AS orderPersonPhone  , wo.issuer_id AS issuerId  FROM w_order wo WHERE wo.order_status in ('ORDERSTATU_003','ORDERSTATU_004') AND wo.buy_status = 'PAY_002' AND   wo.order_person_id = '" + uid + "'";
        } else if("4".equals(OrderTypes)) {
            sql = " SELECT wo.id,wo.order_Number AS orderNumber,wo.price AS price,wo.order_time AS orderTime,wo.city AS city,wo.seek_status AS seekStatus,wo.order_status AS orderStatus , wo.title AS title ,wo.state AS state , wo.order_person_id  AS orderPersonId , wo.order_person_name AS orderPersonName, wo.order_person_phone AS orderPersonPhone  , wo.issuer_id AS issuerId  FROM w_order wo WHERE wo.order_status in ('ORDERSTATU_006') AND wo.buy_status = 'PAY_002' AND   wo.order_person_id = '" + uid + "'";
        } else if("5".equals(OrderTypes)) {
            sql = " SELECT wo.id,wo.order_Number AS orderNumber,wo.price AS price,wo.order_time AS orderTime,wo.city AS city,wo.seek_status AS seekStatus,wo.order_status AS orderStatus , wo.title AS title ,wo.state AS state , wo.order_person_id  AS orderPersonId , wo.order_person_name AS orderPersonName, wo.order_person_phone AS orderPersonPhone  , wo.issuer_id AS issuerId FROM w_order wo WHERE wo.order_status in ('ORDERSTATU_005','ORDERSTATU_007') AND wo.buy_status = 'PAY_004' AND   wo.order_person_id = '" + uid + "'";
        } else if("6".equals(OrderTypes)) {
            sql = " SELECT wo.id,wo.order_Number AS orderNumber,wo.price AS price,wo.order_time AS orderTime,wo.city AS city,wo.seek_status AS seekStatus,wo.order_status AS orderStatus , wo.title AS title ,wo.state AS state , wo.order_person_id  AS orderPersonId , wo.order_person_name AS orderPersonName, wo.order_person_phone AS orderPersonPhone  , wo.issuer_id AS issuerId  FROM w_order wo WHERE wo.order_status in ('ORDERSTATU_006') AND wo.buy_status = 'PAY_002' AND   wo.order_person_id = '" + uid + "'";
        }

        List<WOrderEntity> findObjForJdbc = this.wOrderService.findObjForJdbc(sql, page.intValue(), rows.intValue(), WOrderEntity.class);
        List<Map> orderList = new ArrayList();
        List<Map> orderList1 = new ArrayList();
        if(findObjForJdbc.size() > 0) {
            for(int a = 0; a < findObjForJdbc.size(); ++a) {
                Map map = new HashMap();
                WOrderEntity bmg = (WOrderEntity)findObjForJdbc.get(a);
                map.put("orderId", bmg.getId());
                map.put("orderNumber", bmg.getOrderNumber());
                map.put("price", bmg.getPrice());
                map.put("OrderTime", bmg.getOrderTime());
                map.put("city", bmg.getCity());
                map.put("title", bmg.getTitle());
                map.put("state", bmg.getState());
                map.put("seekStatus", bmg.getSeekStatus());
                String orderStatus2 = bmg.getOrderStatus();
                if("ORDERSTATU_001".equals(orderStatus2)) {
                    orderStatus = "待确定";
                } else if("ORDERSTATU_002".equals(orderStatus2)) {
                    orderStatus = "服务中";
                } else if("ORDERSTATU_003".equals(orderStatus2)) {
                    orderStatus = "待评价";
                } else if("ORDERSTATU_004".equals(orderStatus2)) {
                    orderStatus = "已评价";
                } else if("ORDERSTATU_005".equals(orderStatus2)) {
                    orderStatus = "取消";
                } else if("ORDERSTATU_006".equals(orderStatus2)) {
                    orderStatus = "已完成";
                } else if("ORDERSTATU_007".equals(orderStatus2)) {
                    orderStatus = "已失效";
                }

                map.put("orderStatus", orderStatus);
                map.put("orderStatus2", orderStatus2);
                TSBaseUser baseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", bmg.getIssuerId());
                if(baseUser == null) {
                    return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
                }

                if(baseUser.getHeadPortrait() != null && !"".equals(baseUser.getHeadPortrait())) {
                    map.put("headPortrait", baseUser.getHeadPortrait());
                } else {
                    map.put("headPortrait", (Object)null);
                }

                orderList.add(map);
            }
        }

        if("0".equals(OrderTypes)) {
            List<WOrderEntity> WOrderEntity2 = this.wOrderService.findObjForJdbc(sql1, page.intValue(), rows.intValue(), WOrderEntity.class);
            if(WOrderEntity2.size() > 0) {
                for(int a = 0; a < WOrderEntity2.size(); ++a) {
                    Map map = new HashMap();
                    WOrderEntity bmg = (WOrderEntity)WOrderEntity2.get(a);
                    map.put("orderId", bmg.getId());
                    map.put("orderNumber", bmg.getOrderNumber());
                    map.put("price", bmg.getPrice());
                    map.put("OrderTime", bmg.getOrderTime());
                    map.put("city", bmg.getCity());
                    map.put("title", bmg.getTitle());
                    map.put("state", bmg.getState());
                    map.put("seekStatus", bmg.getSeekStatus());
                    String orderStatus2 = bmg.getOrderStatus();
                    if("ORDERSTATU_001".equals(orderStatus2)) {
                        orderStatus = "待确定";
                    } else if("ORDERSTATU_002".equals(orderStatus2)) {
                        orderStatus = "服务中";
                    } else if("ORDERSTATU_003".equals(orderStatus2)) {
                        orderStatus = "待评价";
                    } else if("ORDERSTATU_004".equals(orderStatus2)) {
                        orderStatus = "已评价";
                    } else if("ORDERSTATU_005".equals(orderStatus2)) {
                        orderStatus = "已取消";
                    } else if("ORDERSTATU_006".equals(orderStatus2)) {
                        orderStatus = "已完成";
                    }

                    map.put("orderStatus2", orderStatus2);
                    map.put("orderStatus", orderStatus);
                    TSBaseUser baseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", bmg.getIssuerId());
                    if(baseUser == null) {
                        return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
                    }

                    if(baseUser.getHeadPortrait() != null && !"".equals(baseUser.getHeadPortrait())) {
                        map.put("headPortrait", baseUser.getHeadPortrait());
                    } else {
                        map.put("headPortrait", (Object)null);
                    }

                    orderList1.add(map);
                }
            }

            orderList.addAll(orderList1);
        }

        jsonMap.put("mySeekOrderList", orderList);
        jsonMap.put("uid", uid);
        jsonMap.put("token", token);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject serviceCompletion(String uid, String token, String orderId) {
        Map<String, Object> jsonMap = new HashMap();
        WOrderEntity wOrderEntity = (WOrderEntity)this.wOrderService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
        if(!"ORDERSTATU_004".equals(wOrderEntity.getOrderStatus())) {
            return CommonUtils.repsonseOjbectToClientWithBody("20041", (Object)null, new String[0]);
        } else if("ORDERSTATU_006".equals(wOrderEntity.getOrderStatus())) {
            return CommonUtils.repsonseOjbectToClientWithBody("20042", (Object)null, new String[0]);
        } else {
            WOrderRecordEntity wOrderRecord = (WOrderRecordEntity)this.wOrderRecordService.findUniqueByProperty(WOrderRecordEntity.class, "orderId", orderId);
            wOrderRecord.setOrderStatus(Orderstatu.ORDERSTATU_006.toString());
            wOrderRecord.setDescription("订单完成！");
            wOrderRecord.setStatusTime(new Date());
            this.wOrderRecordService.saveOrUpdate(wOrderRecord);
            wOrderEntity.setOrderStatus(Orderstatu.ORDERSTATU_006.toString());
            wOrderEntity.setCompletionTime(new Date());
            this.wOrderService.saveOrUpdate(wOrderEntity);
            TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", wOrderEntity.getOrderPersonId());
            if(tSBaseUser == null) {
                return CommonUtils.repsonseOjbectToClientWithBody("20036", (Object)null, new String[0]);
            } else {
                WCapitalEntity capitalEntity = new WCapitalEntity();
                capitalEntity.setId(UUID.randomUUID().toString());
                capitalEntity.setUserId(wOrderEntity.getOrderPersonId());
                capitalEntity.setUserName(wOrderEntity.getOrderPersonName());
                capitalEntity.setPhone(wOrderEntity.getOrderPersonPhone());
                capitalEntity.setTradeTime(new Date());
                capitalEntity.setType(capitalType.income.toString());
                capitalEntity.setOrderId(orderId);
                capitalEntity.setOrderNumber(wOrderEntity.getOrderNumber());
                capitalEntity.setDescription("完成求助订单");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("commission");
                String cst_rate = resourceBundle.getString("cst_rate");
                String rate = resourceBundle.getString("rate");
                double price = Double.parseDouble(wOrderEntity.getPrice());
                double tax = 0.9D;
                if(cst_rate != null && !"".equals(cst_rate)) {
                    double cst_rate1 = Double.parseDouble(resourceBundle.getString("cst_rate"));
                    tax = cst_rate1;
                }

                BigDecimal b1 = new BigDecimal(Double.toString(price));
                BigDecimal b2 = new BigDecimal(Double.toString(tax));
                double tax1 = 0.1D;
                if(rate != null && !"".equals(rate)) {
                    double rate1 = Double.parseDouble(resourceBundle.getString("rate"));
                    tax1 = rate1;
                }

                BigDecimal c1 = new BigDecimal(Double.toString(price));
                BigDecimal c2 = new BigDecimal(Double.toString(tax1));
                WCommissionEntity commission = new WCommissionEntity();
                commission.setId(UUID.randomUUID().toString());
                commission.setUserId(wOrderEntity.getOrderPersonId());
                commission.setUserName(wOrderEntity.getOrderPersonName());
                commission.setPhone(wOrderEntity.getOrderPersonPhone());
                commission.setCreateTime(new Date());
                commission.setOrderNumber(wOrderEntity.getOrderNumber());
                commission.setCommissionType(CommissionType.COMPLETIONOFORDER.toString());
                commission.setOrderPersonId(wOrderEntity.getOrderPersonId());
                commission.setOrderPersonName(wOrderEntity.getOrderPersonName());
                commission.setSeekStatus(wOrderEntity.getSeekStatus());
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
                this.tSBaseUserService.saveOrUpdate(tSBaseUser);
                this.wCapitalService.save(capitalEntity);
                this.commissionService.save(commission);
                WOrderEntity wOrders = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
                if(wOrders == null) {
                    return CommonUtils.repsonseOjbectToClientWithBody("20025", (Object)null, new String[0]);
                } else {
                    jsonMap.put("seekStatus", wOrders.getSeekStatus());
                    jsonMap.put("title", wOrders.getTitle());
                    jsonMap.put("description", wOrders.getDescription());
                    jsonMap.put("city", wOrders.getCity());
                    jsonMap.put("price", wOrders.getPrice());
                    jsonMap.put("orderStatus", wOrders.getOrderStatus());
                    jsonMap.put("orderId", wOrders.getId());
                    jsonMap.put("orderNumber", wOrders.getOrderNumber());
                    jsonMap.put("orderTime", wOrders.getOrderTime());
                    jsonMap.put("paymentMode", wOrders.getPaymentMode());
                    jsonMap.put("orderPersonName", wOrders.getOrderPersonName());
                    jsonMap.put("orderPersonId", wOrders.getOrderPersonId());
                    jsonMap.put("uid", uid);
                    jsonMap.put("token", token);
                    jsonMap.put("orderId", orderId);
                    return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
                }
            }
        }
    }

    public ResponseObject invalidOrder(String uid) {
        String sql = " FROM  WOrderEntity WHERE issuerId = '" + uid + "' and buyStatus in('PAY_002') AND seekStatus in('1') AND orderStatus in('ORDERSTATU_001')";
        List<WOrderEntity> incalidOrder = this.wOrderService.findByQueryString(sql);
        TSBaseUser baseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        if(incalidOrder.size() > 0) {
            for(int i = 0; i < incalidOrder.size(); ++i) {
                WOrderEntity wOrder = (WOrderEntity)incalidOrder.get(i);
                Date endTime = wOrder.getEndTime();
                Date nowDate = new Date();
                if(nowDate.getTime() >= endTime.getTime()) {
                    List<WHelpMessageEntity> wHelpMessage = this.wHelpMessageService.findByProperty(WHelpMessageEntity.class, "orderid", wOrder.getId());
                    WHelpMessageEntity wHelpMessageEntity;
                    TSBaseUser issuerId;
                    if(wHelpMessage.size() > 0) {
                        for(int j = 0; j < wHelpMessage.size(); ++j) {
                            wHelpMessageEntity = (WHelpMessageEntity)wHelpMessage.get(j);
                            String content;
                            if(wHelpMessageEntity.getSeekHelpPeople() != null && !"".equals(wHelpMessageEntity.getSeekHelpPeople())) {
                                content = wHelpMessageEntity.getSeekHelpPeople() + " 订单失效。";
                            } else {
                                TSBaseUser issuerIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wHelpMessageEntity.getSeekHelpPeopleId());
                                if(issuerIds != null) {
                                    content = issuerIds.getPhone() + " 订单失效。";
                                } else {
                                    content = " 订单失效。";
                                }
                            }

                            WHelpMessageEntity helpMessage = new WHelpMessageEntity();
                            helpMessage.setId(UUID.randomUUID().toString());
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
                            this.wHelpMessageService.save(helpMessage);
                            issuerId = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wHelpMessageEntity.getHelpPeopleId());
                            if(issuerId != null) {
                                if("IOS".equals(issuerId.getAppType())) {
                                    JpushClientUtil.jiGuangPushIos(wOrder.getId(), "HELP", "帮助订单新消息", content, issuerId.getPhone());
                                } else if("Android".equals(issuerId.getAppType())) {
                                    JpushClientUtil.jiGuangPush(wOrder.getId(), "HELP", "帮助订单新消息", content, issuerId.getPhone());
                                }
                            }
                        }
                    }

                    String content1 = "尊敬的用户" + wOrder.getIssuer() + "您好，你又一条订单失效。";
                    wHelpMessageEntity = new WHelpMessageEntity();
                    wHelpMessageEntity.setId(UUID.randomUUID().toString());
                    wHelpMessageEntity.setSeekHelpPeopleId(wOrder.getIssuerId());
                    wHelpMessageEntity.setSeekHelpPeople(wOrder.getIssuer());
                    wHelpMessageEntity.setContent(content1);
                    wHelpMessageEntity.setCreateDate(new Date());
                    wHelpMessageEntity.setReadingState(ReadingState.No.toString());
                    wHelpMessageEntity.setOrderid(wOrder.getId());
                    wHelpMessageEntity.setMessageType(HelpMessageType.INVALID.toString());
                    wHelpMessageEntity.setMessageStatus(HelpMessageType.INVALID.toString());
                    wHelpMessageEntity.setMessageState(HelpMessageState.SEEKHELP.toString());
                    this.wHelpMessageService.save(wHelpMessageEntity);
                    TSBaseUser issuerUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wOrder.getIssuerId());
                    if(issuerUser != null) {
                        if("IOS".equals(baseUser.getAppType())) {
                            JpushClientUtil.jiGuangPushIos(wOrder.getId(), "SEEKHELP", "求助订单新消息", content1, issuerUser.getPhone());
                        } else if("Android".equals(baseUser.getAppType())) {
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
                    issuerId = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", wOrder.getIssuerId());
                    if(issuerId == null) {
                        return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
                    }

                    double price = Double.parseDouble(wOrder.getPrice());
                    BigDecimal b1 = new BigDecimal(Double.toString(price));
                    String balance = String.valueOf(b1);
                    if(issuerId.getBalance() == null) {
                        issuerId.setBalance("0");
                    }

                    double singulars = Double.parseDouble(issuerId.getBalance());
                    BigDecimal bigDecimals2 = new BigDecimal(Double.toString(singulars));
                    BigDecimal bigDecimals = new BigDecimal(balance);
                    BigDecimal bigDecimalAdd1 = bigDecimals.add(bigDecimals2);
                    double add1 = bigDecimalAdd1.doubleValue();
                    String singulars3 = String.valueOf(add1);
                    issuerId.setBalance(singulars3);
                    WCapitalEntity capitalEntity = new WCapitalEntity();
                    capitalEntity.setId(UUID.randomUUID().toString());
                    capitalEntity.setUserId(uid);
                    capitalEntity.setUserName(issuerId.getRealName());
                    capitalEntity.setPhone(issuerId.getPhone());
                    capitalEntity.setAmout(balance);
                    capitalEntity.setTradeTime(new Date());
                    capitalEntity.setType(capitalType.income.toString());
                    capitalEntity.setOrderId(wOrder.getId());
                    capitalEntity.setOrderNumber(wOrder.getOrderNumber());
                    capitalEntity.setDescription("订单失效退款");
                    this.wOrderService.saveOrUpdate(wOrder);
                    this.tSBaseUserService.saveOrUpdate(issuerId);
                    this.wCapitalService.save(capitalEntity);
                }
            }
        }

        return null;
    }
}
