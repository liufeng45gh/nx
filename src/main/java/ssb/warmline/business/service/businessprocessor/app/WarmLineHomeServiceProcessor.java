//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.service.businessprocessor.app;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssb.warmline.business.commons.config.GeoTool;
import ssb.warmline.business.commons.config.ResponseObject;
import ssb.warmline.business.commons.enums.HelpMessageState;
import ssb.warmline.business.commons.enums.HelpMessageType;
import ssb.warmline.business.commons.enums.OrderType;
import ssb.warmline.business.commons.utils.CommonUtils;
import ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity;
import ssb.warmline.business.entity.worder.WOrderEntity;
import ssb.warmline.business.entity.worderphoto.WOrderPhotoEntity;
import ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity;
import ssb.warmline.business.service.businessprocessor.BaseInterface;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;
import ssb.warmline.business.service.worderphoto.WOrderPhotoServiceI;
import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;
import ssb.warmline.business.utils.JpushClientUtil;

@Service
public class WarmLineHomeServiceProcessor extends BaseInterface {
    @Autowired
    private WOrderServiceI wOrderService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;
    @Autowired
    private WOrderPhotoServiceI wOrderPhotoService;
    @Autowired
    private WOrderPhotoMainServiceI wOrderPhotoMainService;
    @Autowired
    private WHelpMessageServiceI wHelpMessageService;
    @Autowired
    private TSBaseUserServiceI c;
    @Autowired
    private WHelpMessageServiceI helpMessageService;
    static final String separator;

    static {
        separator = File.separator;
    }

    public WarmLineHomeServiceProcessor() {
    }

    public ResponseObject homePage(String uid, String token, Integer page, Integer rows, String latitude, String longitude) {
        System.out.println("***********************************纬度（latitude）：" + latitude + "*****************" + "经度（longitude）：" + longitude + "***********************************");
        Map<String, Object> jsonMap = new HashMap();
        if(uid != null && !"".equals(uid)) {
            this.ordinaryOrder(uid);
        }

        new GeoTool();
        List<Map<String, Object>> orderList = new ArrayList();
        List<Map<String, Object>> orderList1 = new ArrayList();
        int i = 0;
        String sql2;
        List wOrder1;
        int a;
        double orderLatitude;
        double orderLongitude;
        double actualLatitude;
        double actualLongitude;
        TSBaseUser tsBaseUser;
        List findByProperty;
        int a1;
        WOrderPhotoMainEntity bmg1;
        if(page.intValue() == 1) {
            sql2 = "SELECT wo.id AS id, wo.order_Number AS orderNumber, wo.title AS title, wo.subtitle AS subtitle, wo.description AS description,wo.category AS category, wo.photos AS photos, wo.price AS price, wo.`issuer` AS ISSUER, wo.location AS location, wo.state AS state,wo.city AS city, wo.order_status AS orderStatus, wo.seek_status AS seekStatus, wo.issuer_id AS issuerId, wo.start_time AS startTime,\two.end_time AS endTime, wo.latitude AS latitude, wo.longitude AS longitude ,ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((  '" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2) + COS('" + latitude + "'  * PI() / 180) * COS(wo.latitude * PI() / 180) * POW(" + "\tSIN(('" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2) )) * 1000) AS juli FROM w_order wo WHERE ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((" + " '" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2) +  COS('" + latitude + "'  * PI() / 180) * COS(wo.latitude * PI() / 180) * POW(" + "SIN(('" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2))) * 1000)<=50000 AND  ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((" + "'" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2) + COS('" + latitude + "'  * PI() / 180) * COS(wo.latitude * PI() / 180) * POW(" + "SIN(('" + longitude + "'* PI() / 180 - wo.longitude * PI() / 180) / 2),2))) * 1000)>=0 AND wo.seek_status = '0' AND wo.order_status = 'ORDERSTATU_001'  " + "AND wo.buy_status ='PAY_002'  ORDER BY juli ASC";
            wOrder1 = this.wOrderService.findObjForJdbc(sql2, page.intValue(), rows.intValue(), WOrderEntity.class);
            if(wOrder1 != null && wOrder1.size() > 0) {
                for(a = 0; a < wOrder1.size(); ++a) {
                    WOrderEntity bmg = (WOrderEntity)wOrder1.get(a);
                    Map<String, Object> map = new HashMap();
                    if(i < 3) {
                        ++i;
                        map.put("orderId", bmg.getId());
                        map.put("titel", bmg.getTitle());
                        map.put("subtitle", bmg.getSubtitle());
                        map.put("price", bmg.getPrice());
                        map.put("subtitle", bmg.getSubtitle());
                        map.put("seekStatus", bmg.getSeekStatus());
                        map.put("startTime", bmg.getStartTime());
                        map.put("endTime", bmg.getEndTime());
                        map.put("issuerId", bmg.getIssuerId());
                        orderLatitude = Double.valueOf(bmg.getLatitude()).doubleValue();
                        orderLongitude = Double.valueOf(bmg.getLongitude()).doubleValue();
                        actualLatitude = Double.valueOf(latitude).doubleValue();
                        actualLongitude = Double.valueOf(longitude).doubleValue();
                        map.put("distance", Double.valueOf(GeoTool.getPointDistance(actualLatitude, actualLongitude, orderLatitude, orderLongitude)));
                        tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", bmg.getIssuerId());
                        if(tsBaseUser != null) {
                            if(!"".equals(tsBaseUser.getHeadPortrait()) && tsBaseUser.getHeadPortrait() != null) {
                                map.put("HeadPortrait", tsBaseUser.getHeadPortrait());
                            } else {
                                map.put("HeadPortrait", (Object)null);
                            }

                            if(!"".equals(tsBaseUser.getUserName()) && tsBaseUser.getUserName() != null) {
                                map.put("issuer", tsBaseUser.getUserName());
                            } else {
                                map.put("issuer", " ");
                            }

                            findByProperty = this.commonService.findByProperty(WOrderPhotoMainEntity.class, "orderId", bmg.getId());
                            if(findByProperty.size() > 0) {
                                for(a1 = 0; a1 < findByProperty.size(); ++a1) {
                                    bmg1 = (WOrderPhotoMainEntity)findByProperty.get(0);
                                    map.put("orderPhoto", bmg1.getPhotoUrl());
                                }
                            } else {
                                map.put("orderPhoto", (Object)null);
                            }

                            map.put("certification", tsBaseUser.getCertification());
                            orderList.add(map);
                        }
                    }
                }
            }
        }

        sql2 = "SELECT wo.id AS id, wo.order_Number AS orderNumber, wo.title AS title, wo.subtitle AS subtitle, wo.description AS description,wo.category AS category, wo.photos AS photos, wo.price AS price, wo.`issuer` AS ISSUER, wo.location AS location, wo.state AS state,wo.city AS city, wo.order_status AS orderStatus, wo.seek_status AS seekStatus, wo.issuer_id AS issuerId, wo.start_time AS startTime,\two.end_time AS endTime, wo.latitude AS latitude, wo.longitude AS longitude ,ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((  '" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2) + COS('" + latitude + "'  * PI() / 180) * COS(wo.latitude * PI() / 180) * POW(" + "\tSIN(('" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2) )) * 1000) AS juli FROM w_order wo WHERE ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((" + " '" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2) +  COS('" + latitude + "'  * PI() / 180) * COS(wo.latitude * PI() / 180) * POW(" + "SIN(('" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2))) * 1000)<=50000 AND  ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((" + "'" + longitude + "' * PI() / 180 - wo.longitude * PI() / 180) / 2),2) + COS('" + latitude + "'  * PI() / 180) * COS(wo.latitude * PI() / 180) * POW(" + "SIN(('" + longitude + "'* PI() / 180 - wo.longitude * PI() / 180) / 2),2))) * 1000)>=0 AND wo.seek_status = '1' AND wo.order_status = 'ORDERSTATU_001'  " + "AND wo.buy_status ='PAY_002'  and wo.end_time > NOW()  ORDER BY juli ASC";
        wOrder1 = this.wOrderService.findObjForJdbc(sql2, page.intValue(), rows.intValue(), WOrderEntity.class);
        if(wOrder1 != null && wOrder1.size() > 0) {
            for(a = 0; a < wOrder1.size(); ++a) {
                Map<String, Object> map1 = new HashMap();
                WOrderEntity bmg = (WOrderEntity)wOrder1.get(a);
                map1.put("orderId", bmg.getId());
                map1.put("titel", bmg.getTitle());
                map1.put("subtitle", bmg.getSubtitle());
                map1.put("price", bmg.getPrice());
                map1.put("subtitle", bmg.getSubtitle());
                map1.put("seekStatus", bmg.getSeekStatus());
                map1.put("startTime", bmg.getStartTime());
                map1.put("endTime", bmg.getEndTime());
                map1.put("issuerId", bmg.getIssuerId());
                orderLatitude = Double.valueOf(bmg.getLatitude()).doubleValue();
                orderLongitude = Double.valueOf(bmg.getLongitude()).doubleValue();
                actualLatitude = Double.valueOf(latitude).doubleValue();
                actualLongitude = Double.valueOf(longitude).doubleValue();
                map1.put("distance", Double.valueOf(GeoTool.getPointDistance(actualLatitude, actualLongitude, orderLatitude, orderLongitude)));
                tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", bmg.getIssuerId());
                if(tsBaseUser != null) {
                    if(!"".equals(tsBaseUser.getHeadPortrait()) && tsBaseUser.getHeadPortrait() != null) {
                        map1.put("HeadPortrait", tsBaseUser.getHeadPortrait());
                    } else {
                        map1.put("HeadPortrait", (Object)null);
                    }

                    if(!"".equals(tsBaseUser.getUserName()) && tsBaseUser.getUserName() != null) {
                        map1.put("issuer", tsBaseUser.getUserName());
                    } else {
                        map1.put("issuer", " ");
                    }

                    findByProperty = this.commonService.findByProperty(WOrderPhotoMainEntity.class, "orderId", bmg.getId());
                    if(findByProperty.size() > 0) {
                        for(a1 = 0; a1 < findByProperty.size(); ++a1) {
                            bmg1 = (WOrderPhotoMainEntity)findByProperty.get(0);
                            map1.put("orderPhoto", bmg1.getPhotoUrl());
                        }
                    } else {
                        map1.put("orderPhoto", (Object)null);
                    }

                    map1.put("certification", tsBaseUser.getCertification());
                    orderList1.add(map1);
                }
            }
        }

        orderList.addAll(orderList1);
        jsonMap.put("orderList", orderList);
        jsonMap.put("uid", uid);
        jsonMap.put("token", token);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject orderDetails(String uid, String token, String longitude, String latitude, String orderId) {
        Map<String, Object> jsonMap = new HashMap();
        WOrderEntity orderEntity = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
        if(orderEntity == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20025", (Object)null, new String[0]);
        } else {
            TSBaseUser loginBaseUsers = null;
            if(uid != null && !"".equals(uid)) {
                loginBaseUsers = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
            }

            List<Map> orderList = new ArrayList();
            Map map = new HashMap();
            map.put("seekStatus", orderEntity.getSeekStatus());
            map.put("price", orderEntity.getPrice());
            TSBaseUser orderSendUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", orderEntity.getIssuerId());
            if(orderSendUser != null && !"".equals(orderSendUser)) {
                if(orderSendUser.getHeadPortrait() != null && !"".equals(orderSendUser.getHeadPortrait())) {
                    map.put("HeadPortrait", orderSendUser.getHeadPortrait());
                } else {
                    map.put("HeadPortrait", (Object)null);
                }

                if(orderSendUser.getUserName() != null && !"".equals(orderSendUser.getUserName())) {
                    map.put("userName", orderSendUser.getUserName());
                } else {
                    map.put("userName", (Object)null);
                }

                map.put("issuerId", orderEntity.getIssuerId());
                new GeoTool();
                double orderLatitude = Double.valueOf(orderEntity.getLatitude()).doubleValue();
                double orderLongitude = Double.valueOf(orderEntity.getLongitude()).doubleValue();
                double actualLatitude = Double.valueOf(latitude).doubleValue();
                double actualLongitude = Double.valueOf(longitude).doubleValue();
                map.put("distance", Double.valueOf(GeoTool.getPointDistance(actualLatitude, actualLongitude, orderLatitude, orderLongitude)));
                List<String> list = new ArrayList();
                List<WOrderPhotoMainEntity> findByProperty = this.commonService.findByProperty(WOrderPhotoMainEntity.class, "orderId", orderEntity.getId());
                if(findByProperty.size() > 0) {
                    for(int a1 = 0; a1 < findByProperty.size(); ++a1) {
                        WOrderPhotoMainEntity bmg1 = (WOrderPhotoMainEntity)findByProperty.get(a1);
                        list.add(bmg1.getPhotoUrl());
                    }

                    map.put("orderPhoto", list);
                } else {
                    map.put("orderPhoto", (Object)null);
                }

                if(loginBaseUsers == null) {
                    map.put("orderPerson", "others");
                } else if(loginBaseUsers != null && loginBaseUsers.getId().equals(orderEntity.getIssuerId())) {
                    map.put("orderPerson", "own");
                } else {
                    map.put("orderPerson", "others");
                }

                map.put("certification", orderSendUser.getCertification());
                map.put("orderid", orderEntity.getId());
                map.put("city", orderEntity.getCity());
                map.put("state", orderEntity.getState());
                map.put("Category", orderEntity.getCategory());
                String sql;
                if("1".equals(orderEntity.getSeekStatus())) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String StartTime = dateFormat.format(orderEntity.getStartTime());
                    sql = dateFormat.format(orderEntity.getEndTime());
                    map.put("time", StartTime + " -- " + sql);
                } else if("0".equals(orderEntity.getSeekStatus())) {
                    map.put("time", orderEntity.getStartTime() + " -- " + orderEntity.getEndTime());
                }

                map.put("location", orderEntity.getLocation());
                map.put("title", orderEntity.getTitle());
                map.put("subtitle", orderEntity.getSubtitle());
                map.put("description", orderEntity.getDescription());
                map.put("phone", orderEntity.getPhone());
                map.put("orderPhone", orderEntity.getOrderPhone());
                map.put("isVirtualUser", orderEntity.getIsVirtualUser());
                Long issuerCount = this.commonService.getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where issuer_id = '" + orderEntity.getIssuerId() + "' AND buy_status='PAY_002'  ");
                map.put("issuerCount", issuerCount);
                Long helepCount = this.commonService.getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where order_person_id = '" + orderEntity.getIssuerId() + "'  AND order_status in ('ORDERSTATU_003','ORDERSTATU_004','ORDERSTATU_006') ");
                map.put("helepCount", helepCount);
                if(loginBaseUsers != null) {
                    sql = "FROM WHelpMessageEntity WHERE helpPeopleId='" + loginBaseUsers.getId() + "' AND orderid ='" + orderId + "' and messageStatus = 'APPLICATION'";
                    List<WHelpMessageEntity> WHelpMessage = this.helpMessageService.findByQueryString(sql);
                    if(WHelpMessage.size() > 0) {
                        map.put("applicationStatus", "1");
                    } else {
                        map.put("applicationStatus", "0");
                    }
                } else {
                    map.put("applicationStatus", "0");
                }

                orderList.add(map);
                jsonMap.put("orderList", orderList);
                jsonMap.put("uid", uid);
                jsonMap.put("token", token);
                return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
            } else {
                return CommonUtils.repsonseOjbectToClientWithBody("20043", (Object)null, new String[0]);
            }
        }
    }

    public ResponseObject applyHellp(String uid, String token, String orderId) throws ParseException {
        Map<String, Object> jsonMap = new HashMap();
        TSBaseUser TSBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        WOrderEntity orderEntity = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
        if(TSBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
        } else if(orderEntity == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20025", (Object)null, new String[0]);
        } else {
            String sql = "FROM WHelpMessageEntity WHERE helpPeopleId='" + uid + "' AND orderid ='" + orderId + "'";
            List<WHelpMessageEntity> helpMessageEntities = this.helpMessageService.findByQueryString(sql);
            if(helpMessageEntities.size() > 0) {
                return CommonUtils.repsonseOjbectToClientWithBody("20002", (Object)null, new String[0]);
            } else if(orderEntity.getIssuerId().equals(TSBaseUser.getId())) {
                return CommonUtils.repsonseOjbectToClientWithBody("20014", (Object)null, new String[0]);
            } else {
                if("0".equals(orderEntity.getSeekStatus())) {
                    if("0".equals(TSBaseUser.getCertification())) {
                        return CommonUtils.repsonseOjbectToClientWithBody("20008", (Object)null, new String[0]);
                    }
                } else if("1".equals(orderEntity.getSeekStatus()) && "0".equals(TSBaseUser.getCertification())) {
                    return CommonUtils.repsonseOjbectToClientWithBody("20011", (Object)null, new String[0]);
                }

                String sql1 = "FROM WHelpMessageEntity WHERE helpPeopleId='" + uid + "' AND messageType in('AGREE','APPLICATION')";
                List<WHelpMessageEntity> helpMessageEntitie1 = this.helpMessageService.findByQueryString(sql1);
                if(helpMessageEntitie1.size() >= 2) {
                    return CommonUtils.repsonseOjbectToClientWithBody("20003", (Object)null, new String[0]);
                } else {
                    WHelpMessageEntity helpMessage = new WHelpMessageEntity();
                    if(helpMessageEntitie1.size() == 0) {
                        helpMessage.setId(UUID.randomUUID().toString());
                        helpMessage.setCreateDate(new Date());
                        helpMessage.setHelpPeopleId(uid);
                        helpMessage.setHelpPeople(TSBaseUser.getUserName());
                        helpMessage.setSeekHelpPeopleId(orderEntity.getIssuerId());
                        helpMessage.setSeekHelpPeople(orderEntity.getIssuer());
                        helpMessage.setReadingState("No");
                        helpMessage.setContent("申请了您发出的求助订单。");
                        helpMessage.setMessageType(HelpMessageType.APPLICATION.toString());
                        helpMessage.setOrderid(orderId);
                        helpMessage.setOrderType(OrderType.ORDERTYPE_000.toString());
                        helpMessage.setMessageStatus(HelpMessageType.APPLICATION.toString());
                        helpMessage.setMessageState(HelpMessageState.SEEKHELP.toString());
                        this.wHelpMessageService.save(helpMessage);
                        TSBaseUser issuerId = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", orderEntity.getIssuerId());
                        if(issuerId == null) {
                            return CommonUtils.repsonseOjbectToClientWithBody("20049", (Object)null, new String[0]);
                        }

                        String message;
                        TSBaseUser issuerIds;
                        if("IOS".equals(issuerId.getAppType())) {
                            if(helpMessage.getHelpPeople() != null && !"".equals(helpMessage.getHelpPeople())) {
                                message = helpMessage.getHelpPeople();
                            } else {
                                issuerIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", helpMessage.getHelpPeopleId());
                                if(issuerIds != null) {
                                    message = issuerIds.getPhone();
                                } else {
                                    message = "  ";
                                }
                            }

                            JpushClientUtil.jiGuangPushIos(orderEntity.getId(), "SEEKHELP", "求助订单新消息", message + " 申请了您发出的求助订单。", issuerId.getPhone());
                        } else if("Android".equals(issuerId.getAppType())) {
                            message = null;
                            if(helpMessage.getHelpPeople() != null && !"".equals(helpMessage.getHelpPeople())) {
                                message = helpMessage.getHelpPeople();
                            } else {
                                issuerIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", helpMessage.getHelpPeopleId());
                                if(issuerIds != null) {
                                    message = issuerIds.getPhone();
                                } else {
                                    message = "  ";
                                }
                            }

                            JpushClientUtil.jiGuangPush(orderEntity.getId(), "SEEKHELP", "求助订单新消息", message + " 申请了您发出的求助订单。", issuerId.getPhone());
                        }
                    }

                    if(helpMessageEntitie1.size() == 1) {
                        for(int i = 0; i < helpMessageEntitie1.size(); ++i) {
                            WHelpMessageEntity wHelpMessageEntity = (WHelpMessageEntity)helpMessageEntitie1.get(i);
                            String orderid2 = wHelpMessageEntity.getOrderid();
                            WOrderEntity orderEntit1 = (WOrderEntity)this.commonService.findUniqueByProperty(WOrderEntity.class, "id", orderid2);
                            if(orderEntit1 != null && !"".equals(orderEntit1)) {
                                Date endTime = orderEntit1.getEndTime();
                                Date startTime = orderEntity.getStartTime();
                                if(endTime.getTime() >= startTime.getTime()) {
                                    return CommonUtils.repsonseOjbectToClientWithBody("20019", (Object)null, new String[0]);
                                }

                                helpMessage.setId(UUID.randomUUID().toString());
                                helpMessage.setCreateDate(new Date());
                                helpMessage.setHelpPeopleId(uid);
                                helpMessage.setHelpPeople(TSBaseUser.getUserName());
                                helpMessage.setSeekHelpPeopleId(orderEntity.getIssuerId());
                                helpMessage.setSeekHelpPeople(orderEntity.getIssuer());
                                helpMessage.setReadingState("No");
                                helpMessage.setContent("申请了您发出的求助订单。");
                                helpMessage.setMessageType(HelpMessageType.APPLICATION.toString());
                                helpMessage.setOrderid(orderId);
                                helpMessage.setOrderType(OrderType.ORDERTYPE_001.toString());
                                helpMessage.setMessageStatus(HelpMessageType.APPLICATION.toString());
                                helpMessage.setMessageState(HelpMessageState.SEEKHELP.toString());
                                this.wHelpMessageService.save(helpMessage);
                                TSBaseUser issuerId = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", orderEntity.getIssuerId());
                                if(issuerId == null) {
                                    return CommonUtils.repsonseOjbectToClientWithBody("20049", (Object)null, new String[0]);
                                }

                                String message;
                                TSBaseUser issuerIds;
                                if("IOS".equals(issuerId.getAppType())) {
                                    message = null;
                                    if(helpMessage.getHelpPeople() != null && !"".equals(helpMessage.getHelpPeople())) {
                                        message = helpMessage.getHelpPeople();
                                    } else {
                                        issuerIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", helpMessage.getHelpPeopleId());
                                        if(issuerIds != null) {
                                            message = issuerIds.getPhone();
                                        } else {
                                            message = "  ";
                                        }
                                    }

                                    JpushClientUtil.jiGuangPushIos(orderEntity.getId(), "SEEKHELP", "求助订单新消息", message + " 申请了您发出的求助订单。", issuerId.getPhone());
                                } else if("Android".equals(issuerId.getAppType())) {
                                    message = null;
                                    if(helpMessage.getHelpPeople() != null && !"".equals(helpMessage.getHelpPeople())) {
                                        message = helpMessage.getHelpPeople();
                                    } else {
                                        issuerIds = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", helpMessage.getHelpPeopleId());
                                        if(issuerIds != null) {
                                            message = issuerIds.getPhone();
                                        } else {
                                            message = "  ";
                                        }
                                    }

                                    JpushClientUtil.jiGuangPush(orderEntity.getId(), "SEEKHELP", "求助订单新消息", message + " 申请了您发出的求助订单。", issuerId.getPhone());
                                }
                            }
                        }
                    }

                    jsonMap.put("uid", uid);
                    jsonMap.put("token", token);
                    return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
                }
            }
        }
    }

    public String ordinaryOrder(String uid) {
        String sql = "from WOrderPhotoEntity where uid='" + uid + "' and photoType ='PHOTOTYPE_001' ";
        List<WOrderPhotoEntity> orderPhoto = this.wOrderPhotoService.findByQueryString(sql);
        if(orderPhoto != null && orderPhoto.size() > 0) {
            for(int i = 0; i < orderPhoto.size(); ++i) {
                WOrderPhotoEntity wOrderPhoto = (WOrderPhotoEntity)orderPhoto.get(i);
                WOrderPhotoMainEntity photoUrl = (WOrderPhotoMainEntity)this.wOrderPhotoMainService.findUniqueByProperty(WOrderPhotoMainEntity.class, "photoUrl", wOrderPhoto.getPhotoUrl());
                if(photoUrl == null) {
                    String path_temp = this.request.getSession().getServletContext().getRealPath("");
                    String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
                    String imgPath = separator + "upload" + separator + "orderImage";
                    String filePath = path + imgPath;
                    File fileDir = new File(filePath + separator + wOrderPhoto.getPhotoName());
                    fileDir.delete();
                }

                this.wOrderPhotoService.delete(wOrderPhoto);
            }
        }

        String orderSql = "from WOrderEntity where issuerId='" + uid + "' and buyStatus ='PAY_001' and orderStatus='ORDERSTATU_000' ";
        List<WOrderEntity> order = this.wOrderService.findByQueryString(orderSql);
        if(order != null && order.size() > 0) {
            for(int i = 0; i < order.size(); ++i) {
                WOrderEntity wOrder1 = (WOrderEntity)order.get(i);
                this.wOrderService.delete(wOrder1);
            }
        }

        return null;
    }
}
