/*    */ package ssb.warmline.business.commons.utils.JMessage;
/*    */ 
/*    */ import cn.jiguang.common.resp.BaseResult;
/*    */ import com.google.gson.annotations.Expose;
/*    */ 
/*    */ public class UserInfoResult extends BaseResult
/*    */ {
/*    */ 
/*    */   @Expose
/*    */   String username;
/*    */ 
/*    */   @Expose
/*    */   String nickname;
/*    */ 
/*    */   @Expose
/*    */   String avatar;
/*    */ 
/*    */   @Expose
/*    */   String birthday;
/*    */ 
/*    */   @Expose
/*    */   Integer gender;
/*    */ 
/*    */   @Expose
/*    */   String signature;
/*    */ 
/*    */   @Expose
/*    */   String region;
/*    */ 
/*    */   @Expose
/*    */   String address;
/*    */ 
/*    */   @Expose
/*    */   String ctime;
/*    */ 
/*    */   @Expose
/*    */   String mtime;
/*    */ 
/*    */   @Expose
/*    */   String appkey;
/*    */ 
/*    */   public String getUsername()
/*    */   {
/* 22 */     return this.username;
/*    */   }
/*    */ 
/*    */   public String getNickname() {
/* 26 */     return this.nickname;
/*    */   }
/*    */ 
/*    */   public String getAvatar() {
/* 30 */     return this.avatar;
/*    */   }
/*    */ 
/*    */   public String getBirthday() {
/* 34 */     return this.birthday;
/*    */   }
/*    */ 
/*    */   public Integer getGender() {
/* 38 */     return this.gender;
/*    */   }
/*    */ 
/*    */   public String getSignature() {
/* 42 */     return this.signature;
/*    */   }
/*    */ 
/*    */   public String getRegion() {
/* 46 */     return this.region;
/*    */   }
/*    */ 
/*    */   public String getAddress() {
/* 50 */     return this.address;
/*    */   }
/*    */ 
/*    */   public String getCtime() {
/* 54 */     return this.ctime;
/*    */   }
/*    */ 
/*    */   public String getMtime() {
/* 58 */     return this.mtime;
/*    */   }
/*    */ 
/*    */   public String getAppkey() {
/* 62 */     return this.appkey;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.JMessage.UserInfoResult
 * JD-Core Version:    0.6.2
 */