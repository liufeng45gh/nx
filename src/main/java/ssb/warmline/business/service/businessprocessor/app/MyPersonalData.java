//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.service.businessprocessor.app;

import cn.jiguang.common.resp.ResponseWrapper;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ssb.warmline.business.commons.config.InterfaceServlet;
import ssb.warmline.business.commons.config.ResponseObject;
import ssb.warmline.business.commons.support.spring.data.redis.ObjectRedisSerializer;
import ssb.warmline.business.commons.utils.CommonUtils;
import ssb.warmline.business.commons.utils.JedisUtil;
import ssb.warmline.business.commons.utils.SerializeUtil;
import ssb.warmline.business.commons.utils.UUIDUtil;
import ssb.warmline.business.commons.utils.JMessage.JMessageUserUtils;
import ssb.warmline.business.commons.utils.JMessage.UserPayload;
import ssb.warmline.business.commons.utils.JMessage.UserPayload.Builder;
import ssb.warmline.business.entity.fpushclient.FPushClientEntity;
import ssb.warmline.business.entity.mobileloginlog.MobileLoginLogEntity;
import ssb.warmline.business.entity.wcomment.WCommentEntity;
import ssb.warmline.business.entity.worderphoto.WOrderPhotoEntity;
import ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity;
import ssb.warmline.business.service.businessprocessor.BaseInterface;
import ssb.warmline.business.service.fpushclient.FPushClientServiceI;
import ssb.warmline.business.service.mobileloginlog.MobileLoginLogServiceI;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.wcomment.WCommentServiceI;
import ssb.warmline.business.service.wip.WIpServiceI;
import ssb.warmline.business.service.worderphoto.WOrderPhotoServiceI;
import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;
import ssb.warmline.business.service.wphonecode.WPhoneCodeServiceI;
import ssb.warmline.business.utils.IDCardUtil;

@Service
public class MyPersonalData extends BaseInterface {
    @Autowired
    private CommonService commonService;
    @Autowired
    private WCommentServiceI wCommentService;
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private WOrderPhotoServiceI wOrderPhotoService;
    @Autowired
    private WOrderPhotoMainServiceI wOrderPhotoMainService;
    @Autowired
    MobileLoginLogServiceI mobileLoginLogService;
    @Autowired
    FPushClientServiceI FPushClientService;
    @Autowired
    WIpServiceI wIpService;
    private UserService userService;
    private SystemService systemService;
    @Autowired
    WPhoneCodeServiceI wPhoneCodeService;
    static final String separator;

    static {
        separator = File.separator;
    }

    public MyPersonalData() {
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ResponseObject myPage(String uid, String token) {
        Map<String, Object> jsonMap = new HashMap();
        this.deletePersonalityPhotos(uid);
        TSBaseUser tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        if(tsBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
        } else {
            Long issuerCount = this.commonService.getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where issuer_id = '" + uid + "' AND buy_status='PAY_002'");
            Long helepCount = this.commonService.getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where order_person_id = '" + uid + "'  AND order_status in ('ORDERSTATU_003','ORDERSTATU_004','ORDERSTATU_006') ");
            Long helepCount1 = this.commonService.getCountForJdbc("SELECT IFNULL(SUM(wc.comment_star),0) FROM w_comment wc WHERE wc.issuer_id='" + uid + "'");
            List<WCommentEntity> WCommentEntity = this.wCommentService.findByProperty(WCommentEntity.class, "issuerId", uid);
            int helepCount2 = (new Long(helepCount1.longValue())).intValue();
            if(helepCount2 > 0) {
                BigDecimal helepCount_temp = new BigDecimal(String.valueOf(helepCount2));
                BigDecimal commentSize = new BigDecimal(String.valueOf(WCommentEntity.size()));
                BigDecimal score = helepCount_temp.divide(commentSize, 1, 4);
                jsonMap.put("score", score.toString());
            } else {
                jsonMap.put("score", Integer.valueOf(0));
            }

            List<Map> userList = new ArrayList();
            String personalityPhoto = tsBaseUser.getPersonalityPhoto();
            if(personalityPhoto != null && !"".equals(personalityPhoto)) {
                String[] sourceStrArray = personalityPhoto.split(",");

                for(int i = 0; i < sourceStrArray.length; ++i) {
                    Map map1 = new HashMap();
                    String personalityPhotoId = sourceStrArray[i];
                    WOrderPhotoMainEntity wOrderPhotoMain = (WOrderPhotoMainEntity)this.wOrderPhotoMainService.findUniqueByProperty(WOrderPhotoMainEntity.class, "id", personalityPhotoId);
                    if(wOrderPhotoMain == null) {
                        map1.put("personalityPhotoUrl", (Object)null);
                        map1.put("personalityPhotoName", (Object)null);
                    } else {
                        map1.put("personalityPhotoUrl", wOrderPhotoMain.getPhotoUrl());
                        map1.put("personalityPhotoName", wOrderPhotoMain.getPhotoName());
                    }

                    userList.add(map1);
                }
            } else {
                Map map1 = new HashMap();
                map1.put("personalityPhotoUrl", (Object)null);
                map1.put("personalityPhotoName", (Object)null);
                userList.add(map1);
            }

            jsonMap.put("personalityPhoto", userList);
            jsonMap.put("issuerCount", issuerCount);
            jsonMap.put("helepCount", helepCount);
            jsonMap.put("headPortrait", tsBaseUser.getHeadPortrait());
            jsonMap.put("userName", tsBaseUser.getUserName());
            jsonMap.put("certification", tsBaseUser.getCertification());
            jsonMap.put("uid", uid);
            jsonMap.put("token", token);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        }
    }

    public ResponseObject myPersonal(String uid, String token) throws UnsupportedEncodingException {
        Map<String, Object> jsonMap = new HashMap();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        request.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");
        TSBaseUser tsBaseUser;
        if(!"".equals(userid) && userid != null) {
            tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", userid);
        } else {
            tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        }

        new HashMap();
        Long issuerCount;
        Long helepCount;
        Long helepCount1;
        List WCommentEntity;
        if(!"".equals(userid) && userid != null) {
            issuerCount = this.commonService.getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where issuer_id = '" + userid + "' AND buy_status='PAY_002'  ");
            jsonMap.put("issuerCount", issuerCount);
            helepCount = this.commonService.getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where order_person_id = '" + userid + "'  AND order_status in ('ORDERSTATU_003','ORDERSTATU_004','ORDERSTATU_006') ");
            helepCount1 = this.commonService.getCountForJdbc("SELECT IFNULL(SUM(wc.comment_star),0) FROM w_comment wc WHERE wc.issuer_id='" + userid + "'");
            WCommentEntity = this.wCommentService.findByProperty(WCommentEntity.class, "issuerId", userid);
        } else {
            issuerCount = this.commonService.getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where issuer_id = '" + uid + "' AND buy_status='PAY_002'  ");
            jsonMap.put("issuerCount", issuerCount);
            helepCount = this.commonService.getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where order_person_id = '" + uid + "'  AND order_status in ('ORDERSTATU_003','ORDERSTATU_004','ORDERSTATU_006') ");
            helepCount1 = this.commonService.getCountForJdbc("SELECT IFNULL(SUM(wc.comment_star),0) FROM w_comment wc WHERE wc.issuer_id='" + uid + "'");
            WCommentEntity = this.wCommentService.findByProperty(WCommentEntity.class, "issuerId", uid);
        }

        int helepCount2 = (new Long(helepCount1.longValue())).intValue();
        if(helepCount2 > 0) {
            int size = helepCount2 / WCommentEntity.size();
            DecimalFormat df = new DecimalFormat("0.0");
            String score = df.format((long)size);
            jsonMap.put("score", score);
        } else {
            jsonMap.put("score", Integer.valueOf(0));
        }

        if(tsBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", jsonMap, new String[0]);
        } else {
            List<Map> userList = new ArrayList();
            String personalityPhoto = tsBaseUser.getPersonalityPhoto();
            if(personalityPhoto != null && !"".equals(personalityPhoto)) {
                String[] sourceStrArray = personalityPhoto.split(",");

                for(int i = 0; i < sourceStrArray.length; ++i) {
                    Map map1 = new HashMap();
                    String personalityPhotoId = sourceStrArray[i];
                    WOrderPhotoMainEntity wOrderPhotoMain = (WOrderPhotoMainEntity)this.wOrderPhotoMainService.findUniqueByProperty(WOrderPhotoMainEntity.class, "id", personalityPhotoId);
                    if(wOrderPhotoMain == null) {
                        map1.put("personalityPhotoUrl", (Object)null);
                        map1.put("personalityPhotoName", (Object)null);
                    } else {
                        map1.put("personalityPhotoUrl", wOrderPhotoMain.getPhotoUrl());
                        map1.put("personalityPhotoName", wOrderPhotoMain.getPhotoName());
                    }

                    userList.add(map1);
                }
            }

            jsonMap.put("personalityPhoto", userList);
            jsonMap.put("helepCount", helepCount);
            jsonMap.put("age", tsBaseUser.getAge());
            jsonMap.put("gender", tsBaseUser.getGender());
            jsonMap.put("phone", tsBaseUser.getPhone());
            jsonMap.put("headPortrait", tsBaseUser.getHeadPortrait());
            jsonMap.put("certification", tsBaseUser.getCertification());
            jsonMap.put("userName", tsBaseUser.getUserName());
            jsonMap.put("uid", uid);
            jsonMap.put("token", token);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        }
    }

    public ResponseObject mySeekHelpComment(String uid, String token, Integer page, Integer rows) throws UnsupportedEncodingException {
        Map<String, Object> jsonMap = new HashMap();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        request.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");
        String sql;
        if(!"".equals(userid) && userid != null) {
            sql = "SELECT wc.id AS id,wc.comment_star AS commentStar , wc.comment_time AS commentTime,wc.content AS content , wc.critic_id AS criticId ,wc.critic_name AS criticName  FROM w_comment wc WHERE wc.issuer_id = '" + userid + "'";
        } else {
            sql = "SELECT wc.id AS id,wc.comment_star AS commentStar , wc.comment_time AS commentTime,wc.content AS content , wc.critic_id AS criticId ,wc.critic_name AS criticName  FROM w_comment wc WHERE wc.issuer_id = '" + uid + "'";
        }

        List<WCommentEntity> wComment = this.wCommentService.findObjForJdbc(sql, page.intValue(), rows.intValue(), WCommentEntity.class);
        List<Map> commentList = new ArrayList();
        if(wComment.size() > 0) {
            for(int a = 0; a < wComment.size(); ++a) {
                Map map = new HashMap();
                WCommentEntity wc = (WCommentEntity)wComment.get(a);
                map.put("commentId", wc.getId());
                map.put("commentStar", wc.getCommentStar());
                map.put("commentTime", wc.getCommentTime());
                map.put("content", wc.getContent());
                map.put("criticName", wc.getCriticName());
                TSBaseUser tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", wc.getCriticId());
                if(tsBaseUser == null) {
                    map.put("headPortrait", (Object)null);
                } else {
                    map.put("headPortrait", tsBaseUser.getHeadPortrait());
                }

                commentList.add(map);
            }
        }

        jsonMap.put("commentList", commentList);
        jsonMap.put("uid", uid);
        jsonMap.put("token", token);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject mySubmitAuthentication(String uid, String token, String nationality, String motherTongue, String secondLanguage, String realname, String documentType, String identificationNumber) {
        HashMap jsonMap = new HashMap();

        try {
            TSBaseUser tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
            if(tsBaseUser == null) {
                return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
            } else if(tsBaseUser.getIdentificationNumber() != null && !"".equals(tsBaseUser.getIdentificationNumber())) {
                return CommonUtils.repsonseOjbectToClientWithBody("20057", (Object)null, new String[0]);
            } else {
                List<TSBaseUser> identificationNumberUserList = this.commonService.findByQueryString("FROM TSBaseUser where identificationNumber='" + identificationNumber + "'");
                if(identificationNumberUserList != null && identificationNumberUserList.size() > 0) {
                    return CommonUtils.repsonseOjbectToClientWithBody("20057", (Object)null, new String[0]);
                } else if("1".equals(tsBaseUser.getCertification())) {
                    return CommonUtils.repsonseOjbectToClientWithBody("20053", (Object)null, new String[0]);
                } else {
                    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                    request.setCharacterEncoding("UTF-8");
                    String username = request.getParameter("username");
                    String age = request.getParameter("age");
                    String gender = request.getParameter("gender");
                    if("身份证".equals(documentType)) {
                        String idCard = IDCardUtil.IDCardValidate(identificationNumber);
                        if(!"".equals(idCard)) {
                            return CommonUtils.repsonseOjbectToClientWithBody("20050", (Object)null, new String[0]);
                        }
                    }

                    Builder bb = UserPayload.newBuilder();
                    if(username != null && !"".equals(username)) {
                        List<TSBaseUser> userna = this.commonService.findByProperty(TSBaseUser.class, "userName", username);
                        if(userna.size() > 1) {
                            return CommonUtils.repsonseOjbectToClientWithBody("20032", (Object)null, new String[0]);
                        }

                        if(userna.size() != 0) {
                            TSBaseUser uu = (TSBaseUser)userna.get(0);
                            if(!uu.getId().equals(tsBaseUser.getId())) {
                                return CommonUtils.repsonseOjbectToClientWithBody("20032", (Object)null, new String[0]);
                            }

                            tsBaseUser.setUserName(username);
                            bb.setNickname(username);
                        } else {
                            tsBaseUser.setUserName(username);
                            bb.setNickname(username);
                        }
                    }

                    if(!"".equals(age) && age != null) {
                        try {
                            int a = Integer.parseInt(age);
                            tsBaseUser.setAge(Integer.valueOf(a));
                        } catch (NumberFormatException var19) {
                            var19.printStackTrace();
                        }
                    }

                    if(!"".equals(gender) && gender != null) {
                        tsBaseUser.setGender(gender);
                        if("男".equals(gender)) {
                            bb.setGender(1);
                        } else {
                            bb.setGender(2);
                        }
                    }

                    tsBaseUser.setNationality(nationality);
                    tsBaseUser.setMotherTongue(motherTongue);
                    tsBaseUser.setSecondLanguage(secondLanguage);
                    tsBaseUser.setRealName(realname);
                    tsBaseUser.setDocumentType(documentType);
                    tsBaseUser.setIdentificationNumber(identificationNumber);
                    tsBaseUser.setCertification("1");
                    this.tSBaseUserService.saveOrUpdate(tsBaseUser);
                    UserPayload uPayload = bb.build();
                    ResponseWrapper res = JMessageUserUtils.updateUserInfo(tsBaseUser.getPhone(), uPayload);
                    System.out.println(res);
                    jsonMap.put("uid", uid);
                    jsonMap.put("token", token);
                    return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
                }
            }
        } catch (Exception var20) {
            var20.printStackTrace();
            return CommonUtils.repsonseOjbectToClientWithBody("20024", (Object)null, new String[0]);
        }
    }

    public ResponseObject myProfessional(String uid, String token, String password, String confirmPassword, String appType, String clientid, String devicetoken) {
        Map<String, Object> jsonMap = new HashMap();
        TSBaseUser tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        if(!password.equals(confirmPassword)) {
            return CommonUtils.repsonseOjbectToClientWithBody("10082", (Object)null, new String[0]);
        } else if(tsBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
        } else {
            tsBaseUser.setPassword(PasswordUtil.encrypt(tsBaseUser.getPhone(), password, PasswordUtil.getStaticSalt()));
            this.tSBaseUserService.saveOrUpdate(tsBaseUser);
            ObjectRedisSerializer objectRedisSerializer = new ObjectRedisSerializer();
            InterfaceServlet.mobileUserLoginMap.put(tsBaseUser.getId(), tsBaseUser);
            JedisUtil.setnx(("person" + token).getBytes(), SerializeUtil.serialize(tsBaseUser));
            JedisUtil.setnx(tsBaseUser.getId().getBytes(), objectRedisSerializer.serialize(token));
            String ip = this.request.getRemoteAddr();
            String userAgent = this.request.getHeader("user-agent");
            MobileLoginLogEntity mobileLoginLog = new MobileLoginLogEntity();
            mobileLoginLog.setId(UUIDUtil.getId());
            mobileLoginLog.setUsername(tsBaseUser.getUserName());
            mobileLoginLog.setIp(ip);
            mobileLoginLog.setLoginTime(new Date());
            mobileLoginLog.setUserAgent(userAgent);
            mobileLoginLog.setRealname("");
            mobileLoginLog.setAppType(appType);
            this.mobileLoginLogService.save(mobileLoginLog);
            List<FPushClientEntity> fPushClient = this.FPushClientService.findByProperty(FPushClientEntity.class, "clientId", clientid);
            if(fPushClient.size() > 0) {
                for(int i = 0; i < fPushClient.size(); ++i) {
                    FPushClientEntity fPushClientEntity = (FPushClientEntity)fPushClient.get(i);
                    this.FPushClientService.delete(fPushClientEntity);
                }
            }

            FPushClientEntity pushClient = new FPushClientEntity();
            pushClient.setId(UUIDUtil.getId());
            pushClient.setAppType(appType);
            pushClient.setUid(tsBaseUser.getId());
            pushClient.setUsername(tsBaseUser.getUserName());
            pushClient.setClientId(clientid);
            pushClient.setToken(token);
            pushClient.setAppType(appType);
            pushClient.setCreateTime(new Date());
            pushClient.setDevicetoken(devicetoken);
            this.FPushClientService.save(pushClient);
            ResponseWrapper res = JMessageUserUtils.registerUsers(tsBaseUser.getPhone(), password);
            System.out.println(res);
            jsonMap.put("uid", tsBaseUser.getId());
            jsonMap.put("token", token);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        }
    }

    public ResponseObject myModifyInformation(String uid, String token) {
        Map<String, Object> jsonMap = new HashMap();
        TSBaseUser tsBaseUser = (TSBaseUser)this.systemService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        if(tsBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
        } else {
            List<Map> myModifyList = new ArrayList();
            Map map = new HashMap();
            map.put("headPortrait", tsBaseUser.getHeadPortrait());
            map.put("userName", tsBaseUser.getUserName());
            map.put("age", tsBaseUser.getAge());
            map.put("gender", tsBaseUser.getGender());
            map.put("nationality", tsBaseUser.getNationality());
            map.put("motherTongue", tsBaseUser.getMotherTongue());
            map.put("secondLanguage", tsBaseUser.getSecondLanguage());
            map.put("RealName", tsBaseUser.getRealName());
            map.put("documentType", tsBaseUser.getDocumentType());
            map.put("IdentificationNumber", tsBaseUser.getIdentificationNumber());
            List<Map> userList = new ArrayList();
            String personalityPhoto = tsBaseUser.getPersonalityPhoto();
            if(personalityPhoto != null && !"".equals(personalityPhoto)) {
                String[] sourceStrArray = personalityPhoto.split(",");

                for(int i = 0; i < sourceStrArray.length; ++i) {
                    Map map1 = new HashMap();
                    String personalityPhotoId = sourceStrArray[i];
                    WOrderPhotoMainEntity wOrderPhotoMain = (WOrderPhotoMainEntity)this.wOrderPhotoMainService.findUniqueByProperty(WOrderPhotoMainEntity.class, "id", personalityPhotoId);
                    if(wOrderPhotoMain != null) {
                        map1.put("photoMainId", wOrderPhotoMain.getId());
                        map1.put("personalityPhotoUrl", wOrderPhotoMain.getPhotoUrl());
                        map1.put("personalityPhotoName", wOrderPhotoMain.getPhotoName());
                    }

                    userList.add(map1);
                }
            } else {
                Map map1 = new HashMap();
                map1.put("photoMainId", "");
                map1.put("personalityPhotoUrl", (Object)null);
                map1.put("personalityPhotoName", (Object)null);
                userList.add(map1);
            }

            map.put("personalityPhoto", userList);
            myModifyList.add(map);
            jsonMap.put("uid", uid);
            jsonMap.put("token", token);
            jsonMap.put("myModifyList", myModifyList);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        }
    }

    public ResponseObject myPreservation(String uid, String token) throws UnsupportedEncodingException {
        Map<String, Object> jsonMap = new HashMap();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        TSBaseUser tsBaseUser = (TSBaseUser)this.commonService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        int a;
        if(username != null && !"".equals(username)) {
            List<TSBaseUser> userna = this.commonService.findByProperty(TSBaseUser.class, "userName", username);
            if(userna.size() > 0) {
                for(a = 0; a < userna.size(); ++a) {
                    TSBaseUser tsBaseUser2 = (TSBaseUser)userna.get(a);
                    if(!uid.equals(tsBaseUser2.getId())) {
                        return CommonUtils.repsonseOjbectToClientWithBody("20032", (Object)null, new String[0]);
                    }
                }
            }
        }

        if(tsBaseUser == null) {
            return CommonUtils.repsonseOjbectToClientWithBody("20012", (Object)null, new String[0]);
        } else {
            Builder bb = UserPayload.newBuilder();
            if(!"".equals(username) && username != null) {
                tsBaseUser.setUserName(username);
                bb.setNickname(username);
            } else {
                bb.setNickname(tsBaseUser.getUserName());
            }

            if(!"".equals(age) && age != null) {
                try {
                    a = Integer.parseInt(age);
                    tsBaseUser.setAge(Integer.valueOf(a));
                } catch (NumberFormatException var12) {
                    var12.printStackTrace();
                }
            }

            if(!"".equals(gender) && gender != null) {
                tsBaseUser.setGender(gender);
                if("男".equals(gender)) {
                    bb.setGender(1);
                } else {
                    bb.setGender(2);
                }
            } else if("男".equals(tsBaseUser.getGender())) {
                bb.setGender(1);
            } else {
                bb.setGender(2);
            }

            this.tSBaseUserService.saveOrUpdate(tsBaseUser);
            UserPayload uPayload = bb.build();
            ResponseWrapper res = JMessageUserUtils.updateUserInfo(tsBaseUser.getPhone(), uPayload);
            jsonMap.put("uid", uid);
            jsonMap.put("token", token);
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        }
    }

    public String deletePersonalityPhotos(String uid) {
        String sql = "from WOrderPhotoEntity where uid='" + uid + "' and photoType ='PHOTOTYPE_000' ";
        List<WOrderPhotoEntity> orderPhoto = this.wOrderPhotoService.findByQueryString(sql);
        if(orderPhoto != null && orderPhoto.size() > 0) {
            for(int i = 0; i < orderPhoto.size(); ++i) {
                WOrderPhotoEntity wOrderPhoto = (WOrderPhotoEntity)orderPhoto.get(i);
                String path_temp = this.request.getSession().getServletContext().getRealPath("");
                String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
                String imgPath = separator + "upload" + separator + "orderImage";
                String filePath = path + imgPath;
                File fileDir = new File(filePath + separator + wOrderPhoto.getPhotoName());
                fileDir.delete();
                this.wOrderPhotoService.delete(wOrderPhoto);
            }
        }

        return null;
    }
}
