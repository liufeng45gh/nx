/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_user")
/*     */ @PrimaryKeyJoinColumn(name="id")
/*     */ public class TSUser extends TSBaseUser
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String signatureFile;
/*     */ 
/*     */   @Excel(name="手机")
/*     */   private String mobilePhone;
/*     */ 
/*     */   @Excel(name="办公电话")
/*     */   private String officePhone;
/*     */ 
/*     */   @Excel(name="邮箱")
/*     */   private String email;
/*     */   private Date createDate;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date updateDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*     */ 
/*     */   @Column(name="signatureFile", length=100)
/*     */   public String getSignatureFile()
/*     */   {
/*  44 */     return this.signatureFile;
/*     */   }
/*     */ 
/*     */   public void setSignatureFile(String signatureFile) {
/*  48 */     this.signatureFile = signatureFile;
/*     */   }
/*     */ 
/*     */   @Column(name="mobilePhone", length=30)
/*     */   public String getMobilePhone() {
/*  53 */     return this.mobilePhone;
/*     */   }
/*     */ 
/*     */   public void setMobilePhone(String mobilePhone) {
/*  57 */     this.mobilePhone = mobilePhone;
/*     */   }
/*     */ 
/*     */   @Column(name="officePhone", length=20)
/*     */   public String getOfficePhone() {
/*  62 */     return this.officePhone;
/*     */   }
/*     */ 
/*     */   public void setOfficePhone(String officePhone) {
/*  66 */     this.officePhone = officePhone;
/*     */   }
/*     */ 
/*     */   @Column(name="email", length=50)
/*     */   public String getEmail() {
/*  71 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/*  75 */     this.email = email;
/*     */   }
/*     */ 
/*     */   @Column(name="create_date", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/*  83 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/*  91 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="create_by", nullable=true, length=32)
/*     */   public String getCreateBy()
/*     */   {
/*  99 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 107 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="create_name", nullable=true, length=32)
/*     */   public String getCreateName()
/*     */   {
/* 115 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 123 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="update_date", nullable=true)
/*     */   public Date getUpdateDate()
/*     */   {
/* 131 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 139 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="update_by", nullable=true, length=32)
/*     */   public String getUpdateBy()
/*     */   {
/* 147 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 155 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="update_name", nullable=true, length=32)
/*     */   public String getUpdateName()
/*     */   {
/* 163 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 171 */     this.updateName = updateName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSUser
 * JD-Core Version:    0.6.2
 */