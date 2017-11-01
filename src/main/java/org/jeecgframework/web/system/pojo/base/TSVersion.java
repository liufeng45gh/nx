/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_version")
/*    */ public class TSVersion extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String versionName;
/*    */   private String versionCode;
/*    */   private String loginPage;
/*    */   private String versionNum;
/*    */ 
/*    */   @Column(name="versionname", length=30)
/*    */   public String getVersionName()
/*    */   {
/* 22 */     return this.versionName;
/*    */   }
/*    */   public void setVersionName(String versionName) {
/* 25 */     this.versionName = versionName;
/*    */   }
/*    */ 
/*    */   @Column(name="versioncode", length=50)
/*    */   public String getVersionCode() {
/* 30 */     return this.versionCode;
/*    */   }
/*    */   public void setVersionCode(String versionCode) {
/* 33 */     this.versionCode = versionCode;
/*    */   }
/*    */   @Column(name="loginpage", length=100)
/*    */   public String getLoginPage() {
/* 37 */     return this.loginPage;
/*    */   }
/*    */   public void setLoginPage(String loginPage) {
/* 40 */     this.loginPage = loginPage;
/*    */   }
/*    */   @Column(name="versionnum", length=20)
/*    */   public String getVersionNum() {
/* 44 */     return this.versionNum;
/*    */   }
/*    */   public void setVersionNum(String versionNum) {
/* 47 */     this.versionNum = versionNum;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSVersion
 * JD-Core Version:    0.6.2
 */