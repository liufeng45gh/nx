/*    */ package ssb.warmline.business.commons.support.login;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ThirdPartyUser
/*    */   implements Serializable
/*    */ {
/*    */   private String account;
/*    */   private String userName;
/*    */   private String avatarUrl;
/*    */   private String gender;
/*    */   private String token;
/*    */   private String openid;
/*    */   private String provider;
/*    */   private Integer userId;
/*    */ 
/*    */   public Integer getUserId()
/*    */   {
/* 20 */     return this.userId;
/*    */   }
/*    */ 
/*    */   public void setUserId(Integer userId) {
/* 24 */     this.userId = userId;
/*    */   }
/*    */ 
/*    */   public String getUserName() {
/* 28 */     return this.userName;
/*    */   }
/*    */ 
/*    */   public void setUserName(String userName) {
/* 32 */     this.userName = userName;
/*    */   }
/*    */ 
/*    */   public String getProvider() {
/* 36 */     return this.provider;
/*    */   }
/*    */ 
/*    */   public void setProvider(String provider) {
/* 40 */     this.provider = provider;
/*    */   }
/*    */ 
/*    */   public String getOpenid() {
/* 44 */     return this.openid;
/*    */   }
/*    */ 
/*    */   public void setOpenid(String openid) {
/* 48 */     this.openid = openid;
/*    */   }
/*    */ 
/*    */   public String getToken() {
/* 52 */     return this.token;
/*    */   }
/*    */ 
/*    */   public void setToken(String token) {
/* 56 */     this.token = token;
/*    */   }
/*    */ 
/*    */   public String getAccount() {
/* 60 */     return this.account;
/*    */   }
/*    */ 
/*    */   public void setAccount(String account) {
/* 64 */     this.account = account;
/*    */   }
/*    */ 
/*    */   public String getAvatarUrl() {
/* 68 */     return this.avatarUrl;
/*    */   }
/*    */ 
/*    */   public void setAvatarUrl(String avatarUrl) {
/* 72 */     this.avatarUrl = avatarUrl;
/*    */   }
/*    */ 
/*    */   public String getGender() {
/* 76 */     return this.gender;
/*    */   }
/*    */ 
/*    */   public void setGender(String gender) {
/* 80 */     this.gender = gender;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.support.login.ThirdPartyUser
 * JD-Core Version:    0.6.2
 */