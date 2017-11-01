/*     */ package ssb.warmline.business.commons.utils.JMessage;
/*     */ 
/*     */ import cn.jiguang.common.resp.APIConnectionException;
/*     */ import cn.jiguang.common.resp.APIRequestException;
/*     */ import cn.jiguang.common.resp.ResponseWrapper;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class JMessageUserUtils
/*     */ {
/*     */   protected static final String APP_KEY = "a63545fccaa2de66e1fe585b";
/*     */   protected static final String MASTER_SECRET = "0a64dac040059403d260c7e7";
/*  17 */   private static Logger LOG = LoggerFactory.getLogger(JMessageUserUtils.class);
/*  18 */   private static UserClient userClient = new UserClient("a63545fccaa2de66e1fe585b", "0a64dac040059403d260c7e7");
/*     */ 
/*     */   public static ResponseWrapper registerUsers(String userName, String passWord)
/*     */   {
/*  25 */     ResponseWrapper res = null;
/*     */     try {
/*  27 */       List users = new ArrayList();
/*     */ 
/*  29 */       RegisterInfo user = RegisterInfo.newBuilder().setUsername(userName).setPassword(passWord).build();
/*  30 */       users.add(user);
/*  31 */       RegisterInfo[] regUsers = new RegisterInfo[users.size()];
/*     */ 
/*  33 */       RegisterPayload payload = RegisterPayload.newBuilder().addUsers((RegisterInfo[])users.toArray(regUsers)).build();
/*     */ 
/*  35 */       res = userClient.registerUsers(payload);
/*     */     }
/*     */     catch (APIConnectionException e) {
/*  38 */       LOG.error("连接错误。应该稍后重试. ", e);
/*     */     } catch (APIRequestException e) {
/*  40 */       LOG.error("从JPush服务器错误响应。应该检查并修复它。 ", e);
/*  41 */       LOG.info("HTTP Status: " + e.getStatus());
/*  42 */       LOG.info("Error Message: " + e.getMessage());
/*     */     }
/*  44 */     return res;
/*     */   }
/*     */ 
/*     */   public static ResponseWrapper updateUserInfo(String username, UserPayload payload)
/*     */   {
/*  54 */     ResponseWrapper res = null;
/*     */     try {
/*  56 */       res = userClient.updateUserInfo(username, payload);
/*     */     } catch (APIConnectionException e) {
/*  58 */       LOG.error("连接错误。应该稍后重试. ", e);
/*     */     } catch (APIRequestException e) {
/*  60 */       LOG.error("从JPush服务器错误响应。应该检查并修复它。 ", e);
/*  61 */       LOG.info("HTTP Status: " + e.getStatus());
/*  62 */       LOG.info("Error Message: " + e.getMessage());
/*     */     }
/*  64 */     return res;
/*     */   }
/*     */ 
/*     */   public static ResponseWrapper updatePassword(String username, String newPassWord)
/*     */   {
/*  73 */     ResponseWrapper res = null;
/*     */     try {
/*  75 */       res = userClient.updatePassword(username, newPassWord);
/*     */     } catch (APIConnectionException e) {
/*  77 */       LOG.error("连接错误。应该稍后重试. ", e);
/*     */     } catch (APIRequestException e) {
/*  79 */       LOG.error("从JPush服务器错误响应。应该检查并修复它。 ", e);
/*  80 */       LOG.info("HTTP Status: " + e.getStatus());
/*  81 */       LOG.info("Error Message: " + e.getMessage());
/*     */     }
/*  83 */     return res;
/*     */   }
/*     */ 
/*     */   public static ResponseWrapper deleteUser(String userName)
/*     */   {
/*  91 */     ResponseWrapper res = null;
/*     */     try {
/*  93 */       res = userClient.deleteUser(userName);
/*     */     } catch (APIConnectionException e) {
/*  95 */       LOG.error("连接错误。应该稍后重试. ", e);
/*     */     } catch (APIRequestException e) {
/*  97 */       LOG.error("从JPush服务器错误响应。应该检查并修复它。 ", e);
/*  98 */       LOG.info("HTTP Status: " + e.getStatus());
/*  99 */       LOG.info("Error Message: " + e.getMessage());
/*     */     }
/* 101 */     return res;
/*     */   }
/*     */ 
/*     */   public static UserInfoResult getUserInfo(String userName)
/*     */   {
/* 112 */     UserInfoResult res = null;
/*     */     try {
/* 114 */       res = userClient.getUserInfo(userName);
/*     */     } catch (APIConnectionException e) {
/* 116 */       LOG.error("连接错误。应该稍后重试. ", e);
/*     */     } catch (APIRequestException e) {
/* 118 */       LOG.error("从JPush服务器错误响应。应该检查并修复它。 ", e);
/* 119 */       LOG.info("HTTP Status: " + e.getStatus());
/* 120 */       LOG.info("Error Message: " + e.getMessage());
/*     */     }
/* 122 */     return res;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 128 */     String userName = "15611112050";
/* 129 */     String passWord = "123456";
/* 130 */     UserPayload.Builder bb = UserPayload.newBuilder();
/* 131 */     bb.setNickname("lazy");
/* 132 */     bb.setAvatar("http://192.168.1.180:900/upload/head/20170512161840YjnO4UN5.jpg");
/* 133 */     bb.setGender(1);
/* 134 */     UserPayload uPayload = bb.build();
/* 135 */     ResponseWrapper res = updateUserInfo(userName, uPayload);
/*     */ 
/* 137 */     System.out.println(res.toString());
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.JMessage.JMessageUserUtils
 * JD-Core Version:    0.6.2
 */