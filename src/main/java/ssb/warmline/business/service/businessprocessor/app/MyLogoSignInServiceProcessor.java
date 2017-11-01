/*     */ package ssb.warmline.business.service.businessprocessor.app;
/*     */ 
/*     */ import cn.jiguang.common.resp.ResponseWrapper;
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.util.PasswordUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*     */ import org.jeecgframework.web.system.pojo.base.TSRole;
/*     */ import org.jeecgframework.web.system.pojo.base.TSRoleUser;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.jeecgframework.web.system.service.UserService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.util.StringUtils;
/*     */ import ssb.warmline.business.commons.config.InterfaceServlet;
/*     */ import ssb.warmline.business.commons.config.ResponseObject;
/*     */ import ssb.warmline.business.commons.shortMessage.AccessLimitUtils;
/*     */ import ssb.warmline.business.commons.shortMessage.PhoneCode;
/*     */ import ssb.warmline.business.commons.support.spring.data.redis.ObjectRedisSerializer;
/*     */ import ssb.warmline.business.commons.utils.CommonUtils;
/*     */ import ssb.warmline.business.commons.utils.JMessage.JMessageUserUtils;
/*     */ import ssb.warmline.business.commons.utils.JedisUtil;
/*     */ import ssb.warmline.business.commons.utils.SerializeUtil;
/*     */ import ssb.warmline.business.commons.utils.UUIDUtil;
/*     */ import ssb.warmline.business.entity.fpushclient.FPushClientEntity;
/*     */ import ssb.warmline.business.entity.mobileloginlog.MobileLoginLogEntity;
/*     */ import ssb.warmline.business.entity.wip.WIpEntity;
/*     */ import ssb.warmline.business.entity.wphonecode.WPhoneCodeEntity;
/*     */ import ssb.warmline.business.service.businessprocessor.BaseInterface;
/*     */ import ssb.warmline.business.service.fpushclient.FPushClientServiceI;
/*     */ import ssb.warmline.business.service.mobileloginlog.MobileLoginLogServiceI;
/*     */ import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
/*     */ import ssb.warmline.business.service.wip.WIpServiceI;
/*     */ import ssb.warmline.business.service.wphonecode.WPhoneCodeServiceI;
/*     */ import tk.mybatis.mapper.util.StringUtil;
/*     */ 
/*     */ @Service
/*     */ public class MyLogoSignInServiceProcessor extends BaseInterface
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private HttpServletRequest request;
/*     */ 
/*     */   @Autowired
/*     */   TSBaseUserServiceI tSBaseUserService;
/*     */ 
/*     */   @Autowired
/*     */   MobileLoginLogServiceI mobileLoginLogService;
/*     */ 
/*     */   @Autowired
/*     */   FPushClientServiceI FPushClientService;
/*     */ 
/*     */   @Autowired
/*     */   WIpServiceI wIpService;
/*     */   private UserService userService;
/*     */   private SystemService systemService;
/*     */ 
/*     */   @Autowired
/*     */   WPhoneCodeServiceI wPhoneCodeService;
/*     */ 
/*     */   @Autowired
/*     */   public void setSystemService(SystemService systemService)
/*     */   {
/*  77 */     this.systemService = systemService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserService(UserService userService) {
/*  82 */     this.userService = userService;
/*     */   }
/*     */ 
/*     */   public ResponseObject doLogin(String token, String clientid, String appType, String username, String password, String devicetoken, String areaCode)
/*     */     throws Exception
/*     */   {
/*  93 */     this.request.setCharacterEncoding("UTF-8");
/*  94 */     Map jsonMap = new HashMap();
/*     */     try {
/*  96 */       ObjectRedisSerializer objectRedisSerializer = new ObjectRedisSerializer();
/*  97 */       String ip = this.request.getRemoteAddr();
/*  98 */       String userAgent = this.request.getHeader("user-agent");
/*     */ 
/* 100 */       String sql = "FROM TSBaseUser WHERE phone='" + username + "' AND status='1' and deleteFlag='0' AND areaCode='" + areaCode + "'";
/* 101 */       List tsBaseUser = this.tSBaseUserService.findByQueryString(sql);
/*     */ 
/* 103 */       if (tsBaseUser.size() == 0)
/* 104 */         return CommonUtils.repsonseOjbectToClientWithBody("20035", null, new String[0]);
/* 105 */       if (tsBaseUser.size() > 1) {
/* 106 */         return CommonUtils.repsonseOjbectToClientWithBody("20033", null, new String[0]);
/*     */       }
/* 108 */       for (int a = 0; a < tsBaseUser.size(); a++) {
/* 109 */         TSBaseUser tsBaseUser2 = (TSBaseUser)tsBaseUser.get(a);
/* 110 */         if ((tsBaseUser2.getPassword() == null) || ("".equals(tsBaseUser2.getPassword()))) {
/* 111 */           return CommonUtils.repsonseOjbectToClientWithBody("20039", null, new String[0]);
/*     */         }
/*     */ 
/* 114 */         if (!tsBaseUser2.getPassword().equals(PasswordUtil.encrypt(tsBaseUser2.getPhone(), password, PasswordUtil.getStaticSalt()))) {
/* 115 */           return CommonUtils.repsonseOjbectToClientWithBody("10073", null, new String[0]);
/*     */         }
/*     */ 
/* 118 */         if ((!"普通用户".equals(tsBaseUser2.getUserKey())) && (!"区域负责人".equals(tsBaseUser2.getUserKey())) && (!"区域总代理".equals(tsBaseUser2.getUserKey()))) {
/* 119 */           return CommonUtils.repsonseOjbectToClientWithBody("20034", null, new String[0]);
/*     */         }
/*     */ 
/* 123 */         tsBaseUser2.setLoginTime(new Date());
/* 124 */         tsBaseUser2.setToken(token);
/* 125 */         tsBaseUser2.setLogin("1");
/* 126 */         if ("".equals(tsBaseUser2.getCertification())) {
/* 127 */           tsBaseUser2.setCertification("0");
/*     */         }
/* 129 */         tsBaseUser2.setAppType(appType);
/* 130 */         this.tSBaseUserService.saveOrUpdate(tsBaseUser2);
/* 131 */         InterfaceServlet.mobileUserLoginMap.put(tsBaseUser2.getId(), tsBaseUser2);
/*     */ 
/* 133 */         JedisUtil.setnx(("person" + token).getBytes(), SerializeUtil.serialize(tsBaseUser2));
/*     */ 
/* 135 */         JedisUtil.setnx(tsBaseUser2.getId().getBytes(), objectRedisSerializer.serialize(token));
/*     */ 
/* 137 */         MobileLoginLogEntity mobileLoginLog = new MobileLoginLogEntity();
/* 138 */         mobileLoginLog.setId(UUIDUtil.getId());
/* 139 */         mobileLoginLog.setUsername(tsBaseUser2.getUserName());
/* 140 */         mobileLoginLog.setIp(ip);
/* 141 */         mobileLoginLog.setLoginTime(new Date());
/* 142 */         mobileLoginLog.setUserAgent(userAgent);
/* 143 */         mobileLoginLog.setRealname(tsBaseUser2.getRealName());
/* 144 */         mobileLoginLog.setAppType(appType);
/* 145 */         this.mobileLoginLogService.save(mobileLoginLog);
/*     */ 
/* 147 */         jsonMap.put("certification", tsBaseUser2.getCertification());
/* 148 */         jsonMap.put("token", token);
/* 149 */         jsonMap.put("uid", tsBaseUser2.getId());
/*     */       }
/*     */ 
/* 153 */       List fPushClient = this.FPushClientService.findByProperty(FPushClientEntity.class, "clientId", clientid);
/* 154 */       if (fPushClient.size() > 0) {
/* 155 */         for (int i = 0; i < fPushClient.size(); i++)
/*     */         {
/* 157 */           FPushClientEntity fPushClientEntity = (FPushClientEntity)fPushClient.get(i);
/* 158 */           this.FPushClientService.delete(fPushClientEntity);
/*     */         }
/*     */       }
/* 161 */       if (tsBaseUser.size() > 0)
/* 162 */         for (int a = 0; a < tsBaseUser.size(); a++) {
/* 163 */           TSBaseUser tsBaseUser2 = (TSBaseUser)tsBaseUser.get(0);
/* 164 */           FPushClientEntity pushClient = new FPushClientEntity();
/* 165 */           pushClient.setId(UUIDUtil.getId());
/* 166 */           pushClient.setAppType(appType);
/* 167 */           pushClient.setUid(tsBaseUser2.getId());
/* 168 */           pushClient.setUsername(tsBaseUser2.getUserName());
/* 169 */           pushClient.setClientId(clientid);
/* 170 */           pushClient.setToken(token);
/* 171 */           pushClient.setAppType(appType);
/* 172 */           pushClient.setCreateTime(new Date());
/* 173 */           pushClient.setDevicetoken(devicetoken);
/* 174 */           this.FPushClientService.save(pushClient);
/*     */         }
/*     */     }
/*     */     catch (Exception e) {
/* 178 */       e.printStackTrace();
/* 179 */       return CommonUtils.repsonseOjbectToClientWithBody("10094", null, new String[0]);
/*     */     }
/* 181 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */   }
/*     */ 
/*     */   public ResponseObject exit(String uid, String token)
/*     */   {
/* 189 */     TSBaseUser tsBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
/*     */ 
/* 191 */     if (tsBaseUser == null) {
/* 192 */       return CommonUtils.repsonseOjbectToClientWithBody("10072", null, new String[0]);
/*     */     }
/*     */ 
/* 195 */     tsBaseUser.setToken(null);
/* 196 */     tsBaseUser.setLogin("0");
/* 197 */     tsBaseUser.setAppType(null);
/* 198 */     InterfaceServlet.mobileUserLoginMap.remove(uid);
/*     */ 
/* 200 */     this.tSBaseUserService.saveOrUpdate(tsBaseUser);
/*     */ 
/* 203 */     ObjectRedisSerializer objectRedisSerializer = new ObjectRedisSerializer();
/* 204 */     String tokens = (String)objectRedisSerializer.deserialize(JedisUtil.get(uid.getBytes(), new Integer[0]));
/* 205 */     if ((tokens != null) && (!tokens.equals("")))
/*     */     {
/* 207 */       JedisUtil.del(("person" + token).getBytes());
/*     */ 
/* 209 */       JedisUtil.del(tsBaseUser.getId().getBytes());
/*     */     }
/* 211 */     return CommonUtils.repsonseOjbectToClientWithBody("10077", null, new String[0]);
/*     */   }
/*     */ 
/*     */   public ResponseObject sendVerificationCode(String token, String phone, String areaCode, String type)
/*     */     throws ParseException
/*     */   {
/* 224 */     Map jsonMap = new HashMap(); String ip = this.request.getRemoteAddr();
/* 225 */     if (AccessLimitUtils.filter1(phone))
/*     */     {
/* 227 */       TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "phone", phone);
/* 228 */       if ((tSBaseUser != null) && (!"".equals(tSBaseUser)) && 
/* 229 */         (areaCode.equals(tSBaseUser.getAreaCode())) && 
/* 230 */         (!"".equals(tSBaseUser.getPassword())) && (tSBaseUser.getPassword() != null)) {
/* 231 */         return CommonUtils.repsonseOjbectToClientWithBody("20052", null, new String[0]);
/*     */       }
/*     */ 
/* 238 */       String sql = "FROM WIpEntity WHERE phone='" + phone + "' AND type = '" + type + "' ORDER BY date ASC";
/* 239 */       List tips = this.wIpService.findByQueryString(sql);
/* 240 */       if ((tips == null) || (tips.size() == 0) || (tips.size() == 1) || (tips.size() == 2)) {
/* 241 */         WIpEntity wIp = new WIpEntity();
/* 242 */         wIp.setId(UUIDUtil.getId());
/* 243 */         wIp.setIp(ip);
/* 244 */         wIp.setDate(new Date());
/* 245 */         wIp.setPhone(phone);
/* 246 */         wIp.setType(type);
/* 247 */         wIp.setCount(Integer.valueOf(1));
/* 248 */         this.wIpService.save(wIp);
/* 249 */       } else if (tips.size() == 3) {
/* 250 */         Date now = new Date();
/* 251 */         System.out.println("当前时间：" + now);
/*     */ 
/* 254 */         for (int i = 0; i < tips.size(); i++) {
/* 255 */           WIpEntity wIpEntity = (WIpEntity)tips.get(i);
/* 256 */           Date date = wIpEntity.getDate();
/* 257 */           Date afterDate = new Date(date.getTime() + 600000L);
/* 258 */           System.out.println(afterDate);
/* 259 */           if (afterDate.getTime() < now.getTime())
/*     */           {
/* 261 */             this.wIpService.delete(wIpEntity);
/*     */           } else {
/* 263 */             String sqls = "FROM WIpEntity WHERE phone='" + phone + "' AND type = '" + type + "'";
/* 264 */             List tipss = this.wIpService.findByQueryString(sqls);
/* 265 */             if (tipss.size() == 3) {
/* 266 */               return CommonUtils.repsonseOjbectToClientWithBody("20018", null, new String[0]);
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 271 */         WIpEntity wIp = new WIpEntity();
/* 272 */         wIp.setId(UUIDUtil.getId());
/* 273 */         wIp.setIp(ip);
/* 274 */         wIp.setDate(new Date());
/* 275 */         wIp.setPhone(phone);
/* 276 */         wIp.setType(type);
/* 277 */         wIp.setCount(Integer.valueOf(1));
/* 278 */         this.wIpService.save(wIp);
/*     */       }
/*     */ 
/* 282 */       List findByProperty = this.wPhoneCodeService.findByProperty(WPhoneCodeEntity.class, "phone", phone);
/* 283 */       if (findByProperty.size() > 0) {
/* 284 */         for (int i = 0; i < findByProperty.size(); i++) {
/* 285 */           WPhoneCodeEntity wPhoneCodeEntity = (WPhoneCodeEntity)findByProperty.get(i);
/* 286 */           int a = 1;
/*     */ 
/* 288 */           Integer count = wPhoneCodeEntity.getCount();
/* 289 */           Integer wrapperi = new Integer(count.intValue());
/* 290 */           int counts = wrapperi.intValue();
/*     */ 
/* 292 */           int count1 = counts + a;
/* 293 */           Integer wrapperi1 = new Integer(count1);
/*     */ 
/* 295 */           wPhoneCodeEntity.setCount(wrapperi1);
/* 296 */           this.wPhoneCodeService.saveOrUpdate(wPhoneCodeEntity);
/*     */         }
/*     */       }
/*     */       else {
/* 300 */         WPhoneCodeEntity phoneCode = new WPhoneCodeEntity();
/* 301 */         phoneCode.setId(UUIDUtil.getId());
/* 302 */         phoneCode.setPhone(phone);
/* 303 */         phoneCode.setCreatDate(new Date());
/* 304 */         phoneCode.setIp(ip);
/* 305 */         phoneCode.setCount(Integer.valueOf(1));
/* 306 */         this.wPhoneCodeService.save(phoneCode);
/*     */       }
/*     */     } else {
/* 309 */       return CommonUtils.repsonseOjbectToClientWithBody("20017", null, new String[0]);
/*     */     }
/*     */ 
/* 312 */     String phones = areaCode + phone;
/* 313 */     String verificationCode = PhoneCode.getLoginPhoneCode(phones);
/* 314 */     jsonMap.put("verificationCode", verificationCode);
/* 315 */     jsonMap.put("token", token);
/* 316 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */   }
/*     */ 
/*     */   public ResponseObject phoneLogin(String token, String country, String areaCode, String phone, String verificationCode)
/*     */   {
/* 332 */     Map jsonMap = new HashMap();
/*     */ 
/* 334 */     String phones = areaCode + phone;
/* 335 */     if (!checkMsg(verificationCode, phones).booleanValue()) {
/* 336 */       return CommonUtils.repsonseOjbectToClientWithBody("10047", null, new String[0]);
/*     */     }
/*     */ 
/* 340 */     TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "phone", phone);
/* 341 */     if ((tSBaseUser == null) || ("".equals(tSBaseUser))) {
/* 342 */       TSUser user = new TSUser();
/* 343 */       user.setId(UUIDUtil.getId());
/* 344 */       user.setPhone(phone);
/* 345 */       user.setBalance("0");
/* 346 */       user.setAreaCode(areaCode);
/* 347 */       user.setPhoneCountry(country);
/* 348 */       user.setCertification("0");
/* 349 */       user.setLoginTime(new Date());
/* 350 */       user.setToken(token);
/* 351 */       user.setUserType("0");
/* 352 */       user.setSingular("0");
/* 353 */       user.setReportCount("0");
/* 354 */       user.setStatus(Globals.User_Normal);
/* 355 */       user.setDeleteFlag(Globals.Delete_Normal);
/* 356 */       user.setCreateDate(new Date());
/*     */ 
/* 359 */       TSRole TSRole = (TSRole)this.systemService.findUniqueByProperty(TSRole.class, "roleName", "普通用户");
/* 360 */       if (StringUtil.isNotEmpty(TSRole.getId())) {
/* 361 */         user.setUserKey(TSRole.getRoleName());
/*     */       }
/* 363 */       this.systemService.save(user);
/* 364 */       jsonMap.put("uid", user.getId());
/* 365 */       jsonMap.put("token", token);
/*     */ 
/* 367 */       if (StringUtil.isNotEmpty(TSRole.getId()))
/* 368 */         saveRoleUser(user, TSRole.getId());
/*     */     }
/*     */     else {
/* 371 */       if (("".equals(tSBaseUser.getPassword())) || (tSBaseUser.getPassword() == null)) {
/* 372 */         jsonMap.put("uid", tSBaseUser.getId());
/* 373 */         jsonMap.put("token", token);
/* 374 */         return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */       }
/* 376 */       return CommonUtils.repsonseOjbectToClientWithBody("10117", null, new String[0]);
/*     */     }
/*     */ 
/* 379 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */   }
/*     */ 
/*     */   public ResponseObject updatePassword(String uid, String token, String oldPass, String newPass, String repatePass)
/*     */   {
/* 391 */     Map jsonMap = new HashMap();
/*     */ 
/* 393 */     if (!newPass.equals(repatePass)) {
/* 394 */       return CommonUtils.repsonseOjbectToClientWithBody("10082", null, new String[0]);
/*     */     }
/*     */ 
/* 397 */     TSBaseUser tsBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
/* 398 */     if (tsBaseUser == null) {
/* 399 */       return CommonUtils.repsonseOjbectToClientWithBody("10072", null, new String[0]);
/*     */     }
/*     */ 
/* 402 */     oldPass = PasswordUtil.encrypt(tsBaseUser.getPhone(), oldPass, PasswordUtil.getStaticSalt());
/* 403 */     if (!oldPass.equals(tsBaseUser.getPassword())) {
/* 404 */       return CommonUtils.repsonseOjbectToClientWithBody("10079", null, new String[0]);
/*     */     }
/*     */ 
/* 407 */     ResponseWrapper res = JMessageUserUtils.updatePassword(tsBaseUser.getPhone(), newPass);
/*     */ 
/* 409 */     newPass = PasswordUtil.encrypt(tsBaseUser.getPhone(), newPass, PasswordUtil.getStaticSalt());
/* 410 */     tsBaseUser.setPassword(newPass);
/* 411 */     this.tSBaseUserService.saveOrUpdate(tsBaseUser);
/* 412 */     System.out.println(res);
/* 413 */     jsonMap.put("uid", uid);
/* 414 */     jsonMap.put("token", token);
/* 415 */     return CommonUtils.repsonseOjbectToClientWithBody("10080", jsonMap, new String[0]);
/*     */   }
/*     */ 
/*     */   public Boolean checkMsg(String msg, String phone)
/*     */   {
/* 420 */     if (JedisUtil.get(("mobileCode" + phone).getBytes(), new Integer[] { Integer.valueOf(60) }) == null) {
/* 421 */       return Boolean.valueOf(false);
/*     */     }
/* 423 */     String mobileCode = new String(JedisUtil.get(("mobileCode" + phone).getBytes(), new Integer[] { Integer.valueOf(60) }));
/* 424 */     if ((msg + phone).equals(mobileCode)) {
/* 425 */       return Boolean.valueOf(true);
/*     */     }
/* 427 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   public ResponseObject forgetPassword(String token, String phone, String verificationCode, String newPassword, String confirmPassword)
/*     */   {
/* 439 */     Map jsonMap = new HashMap();
/*     */ 
/* 441 */     TSBaseUser tsBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "phone", phone);
/* 442 */     if (tsBaseUser == null) {
/* 443 */       return CommonUtils.repsonseOjbectToClientWithBody("20021", null, new String[0]);
/*     */     }
/*     */ 
/* 446 */     String phones = tsBaseUser.getAreaCode() + phone;
/* 447 */     if (!checkMsg(verificationCode, phones).booleanValue()) {
/* 448 */       return CommonUtils.repsonseOjbectToClientWithBody("10047", null, new String[0]);
/*     */     }
/*     */ 
/* 451 */     if (!newPassword.equals(confirmPassword)) {
/* 452 */       return CommonUtils.repsonseOjbectToClientWithBody("10082", null, new String[0]);
/*     */     }
/*     */ 
/* 455 */     tsBaseUser.setPassword(PasswordUtil.encrypt(tsBaseUser.getPhone(), newPassword, PasswordUtil.getStaticSalt()));
/* 456 */     this.tSBaseUserService.saveOrUpdate(tsBaseUser);
/*     */ 
/* 459 */     ResponseWrapper res = JMessageUserUtils.updatePassword(tsBaseUser.getPhone(), newPassword);
/* 460 */     jsonMap.put("uid", tsBaseUser.getId());
/* 461 */     jsonMap.put("token", token);
/* 462 */     return CommonUtils.repsonseOjbectToClientWithBody("10080", jsonMap, new String[0]);
/*     */   }
/*     */ 
/*     */   public ResponseObject sendVerificationCodes(String token, String phone, String type)
/*     */     throws ParseException
/*     */   {
/* 473 */     Map jsonMap = new HashMap(); String ip = this.request.getRemoteAddr();
/* 474 */     TSBaseUser tsBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "phone", phone);
/* 475 */     if ((tsBaseUser == null) || ("".equals(tsBaseUser))) {
/* 476 */       return CommonUtils.repsonseOjbectToClientWithBody("20023", null, new String[0]);
/*     */     }
/* 478 */     if (AccessLimitUtils.filter1(phone))
/*     */     {
/* 480 */       String sql = "FROM WIpEntity WHERE phone='" + phone + "' AND type = '" + type + "' ORDER BY date ASC";
/* 481 */       List tips = this.wIpService.findByQueryString(sql);
/* 482 */       if ((tips == null) || (tips.size() == 0) || (tips.size() == 1) || (tips.size() == 2)) {
/* 483 */         WIpEntity wIp = new WIpEntity();
/* 484 */         wIp.setId(UUIDUtil.getId());
/* 485 */         wIp.setIp(ip);
/* 486 */         wIp.setDate(new Date());
/* 487 */         wIp.setPhone(phone);
/* 488 */         wIp.setType(type);
/* 489 */         wIp.setCount(Integer.valueOf(1));
/* 490 */         this.wIpService.save(wIp);
/* 491 */       } else if (tips.size() == 3) {
/* 492 */         Date now = new Date();
/* 493 */         System.out.println("当前时间：" + now);
/*     */ 
/* 496 */         for (int i = 0; i < tips.size(); i++) {
/* 497 */           WIpEntity wIpEntity = (WIpEntity)tips.get(i);
/* 498 */           Date date = wIpEntity.getDate();
/* 499 */           Date afterDate = new Date(date.getTime() + 600000L);
/* 500 */           System.out.println(afterDate);
/* 501 */           if (afterDate.getTime() < now.getTime())
/*     */           {
/* 503 */             this.wIpService.delete(wIpEntity);
/*     */           } else {
/* 505 */             String sqls = "FROM WIpEntity WHERE phone='" + phone + "' AND type = '" + type + "'";
/* 506 */             List tipss = this.wIpService.findByQueryString(sqls);
/* 507 */             if (tipss.size() == 3) {
/* 508 */               return CommonUtils.repsonseOjbectToClientWithBody("20018", null, new String[0]);
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 513 */         WIpEntity wIp = new WIpEntity();
/* 514 */         wIp.setId(UUIDUtil.getId());
/* 515 */         wIp.setIp(ip);
/* 516 */         wIp.setDate(new Date());
/* 517 */         wIp.setPhone(phone);
/* 518 */         wIp.setType(type);
/* 519 */         wIp.setCount(Integer.valueOf(1));
/* 520 */         this.wIpService.save(wIp);
/*     */       }
/*     */ 
/* 524 */       List findByProperty = this.wPhoneCodeService.findByProperty(WPhoneCodeEntity.class, "phone", phone);
/* 525 */       if (findByProperty.size() > 0) {
/* 526 */         for (int i = 0; i < findByProperty.size(); i++) {
/* 527 */           WPhoneCodeEntity wPhoneCodeEntity = (WPhoneCodeEntity)findByProperty.get(i);
/* 528 */           int a = 1;
/*     */ 
/* 530 */           Integer count = wPhoneCodeEntity.getCount();
/* 531 */           Integer wrapperi = new Integer(count.intValue());
/* 532 */           int counts = wrapperi.intValue();
/*     */ 
/* 534 */           int count1 = counts + a;
/* 535 */           Integer wrapperi1 = new Integer(count1);
/*     */ 
/* 537 */           wPhoneCodeEntity.setCount(wrapperi1);
/* 538 */           this.wPhoneCodeService.saveOrUpdate(wPhoneCodeEntity);
/*     */         }
/*     */       }
/*     */       else {
/* 542 */         WPhoneCodeEntity phoneCode = new WPhoneCodeEntity();
/* 543 */         phoneCode.setId(UUIDUtil.getId());
/* 544 */         phoneCode.setPhone(phone);
/* 545 */         phoneCode.setCreatDate(new Date());
/* 546 */         phoneCode.setIp(ip);
/* 547 */         phoneCode.setCount(Integer.valueOf(1));
/* 548 */         this.wPhoneCodeService.save(phoneCode);
/*     */       }
/*     */     } else {
/* 551 */       return CommonUtils.repsonseOjbectToClientWithBody("20017", null, new String[0]);
/*     */     }
/*     */ 
/* 554 */     String phones = tsBaseUser.getAreaCode() + phone;
/* 555 */     String verificationCode = PhoneCode.getLoginPhoneCode(phones);
/* 556 */     jsonMap.put("verificationCode", verificationCode);
/* 557 */     jsonMap.put("token", token);
/* 558 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */   }
/*     */ 
/*     */   protected List<String> extractIdListByComma(String ids)
/*     */   {
/* 572 */     List result = new ArrayList();
/* 573 */     if (StringUtils.hasText(ids)) {
/* 574 */       for (String id : ids.split(",")) {
/* 575 */         if (StringUtils.hasLength(id)) {
/* 576 */           result.add(id.trim());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 581 */     return result;
/*     */   }
/*     */ 
/*     */   protected void saveRoleUser(TSUser user, String roleidstr)
/*     */   {
/* 590 */     String[] roleids = roleidstr.split(",");
/* 591 */     for (int i = 0; i < roleids.length; i++) {
/* 592 */       TSRoleUser rUser = new TSRoleUser();
/* 593 */       TSRole role = (TSRole)this.systemService.getEntity(TSRole.class, roleids[i]);
/* 594 */       rUser.setTSRole(role);
/* 595 */       rUser.setTSUser(user);
/* 596 */       this.systemService.save(rUser);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.app.MyLogoSignInServiceProcessor
 * JD-Core Version:    0.6.2
 */