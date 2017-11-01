/*     */ package org.jeecgframework.web.cgform.entity.template;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Transient;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cgform_template", schema="")
/*     */ public class CgformTemplateEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private Date updateDate;
/*     */   private String sysOrgCode;
/*     */   private String sysCompanyCode;
/*     */ 
/*     */   @Excel(name="模板名称")
/*     */   private String templateName;
/*     */ 
/*     */   @Excel(name="模板编码")
/*     */   private String templateCode;
/*     */ 
/*     */   @Excel(name="模板类型")
/*     */   private String templateType;
/*     */ 
/*     */   @Excel(name="是否共享")
/*     */   private String templateShare;
/*     */ 
/*     */   @Excel(name="预览图")
/*     */   private String templatePic;
/*     */ 
/*     */   @Excel(name="模板描述")
/*     */   private String templateComment;
/*     */   private String templateZipName;
/*     */   private String templateListName;
/*     */   private String templateAddName;
/*     */   private String templateUpdateName;
/*     */   private String templateDetailName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  81 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  89 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  97 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 105 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/* 113 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 121 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 129 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 137 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 145 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 153 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 161 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 169 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 177 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 185 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_ORG_CODE", nullable=true, length=50)
/*     */   public String getSysOrgCode()
/*     */   {
/* 193 */     return this.sysOrgCode;
/*     */   }
/*     */ 
/*     */   public void setSysOrgCode(String sysOrgCode)
/*     */   {
/* 201 */     this.sysOrgCode = sysOrgCode;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_COMPANY_CODE", nullable=true, length=50)
/*     */   public String getSysCompanyCode()
/*     */   {
/* 209 */     return this.sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public void setSysCompanyCode(String sysCompanyCode)
/*     */   {
/* 217 */     this.sysCompanyCode = sysCompanyCode;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_NAME", nullable=true, length=100)
/*     */   public String getTemplateName()
/*     */   {
/* 225 */     return this.templateName;
/*     */   }
/*     */ 
/*     */   public void setTemplateName(String templateName)
/*     */   {
/* 233 */     this.templateName = templateName;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_CODE", nullable=true, length=50)
/*     */   public String getTemplateCode()
/*     */   {
/* 241 */     return this.templateCode;
/*     */   }
/*     */ 
/*     */   public void setTemplateCode(String templateCode)
/*     */   {
/* 249 */     this.templateCode = templateCode;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_TYPE", nullable=true, length=32)
/*     */   public String getTemplateType()
/*     */   {
/* 257 */     return this.templateType;
/*     */   }
/*     */ 
/*     */   public void setTemplateType(String templateType)
/*     */   {
/* 265 */     this.templateType = templateType;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_SHARE", nullable=true, length=10)
/*     */   public String getTemplateShare()
/*     */   {
/* 273 */     return this.templateShare;
/*     */   }
/*     */ 
/*     */   public void setTemplateShare(String templateShare)
/*     */   {
/* 281 */     this.templateShare = templateShare;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_PIC", nullable=true, length=100)
/*     */   public String getTemplatePic()
/*     */   {
/* 289 */     return this.templatePic;
/*     */   }
/*     */ 
/*     */   public void setTemplatePic(String templatePic)
/*     */   {
/* 297 */     this.templatePic = templatePic;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_COMMENT", nullable=true, length=200)
/*     */   public String getTemplateComment()
/*     */   {
/* 305 */     return this.templateComment;
/*     */   }
/*     */ 
/*     */   public void setTemplateComment(String templateComment)
/*     */   {
/* 313 */     this.templateComment = templateComment;
/*     */   }
/*     */   @Transient
/*     */   public String getTemplateZipName() {
/* 317 */     return this.templateZipName;
/*     */   }
/*     */ 
/*     */   public void setTemplateZipName(String templateZipName) {
/* 321 */     this.templateZipName = templateZipName;
/*     */   }
/*     */ 
/*     */   @Column(name="template_list_name", nullable=true, length=200)
/*     */   public String getTemplateListName() {
/* 326 */     return this.templateListName;
/*     */   }
/*     */ 
/*     */   public void setTemplateListName(String templateListName) {
/* 330 */     this.templateListName = templateListName;
/*     */   }
/*     */   @Column(name="template_add_name", nullable=true, length=200)
/*     */   public String getTemplateAddName() {
/* 334 */     return this.templateAddName;
/*     */   }
/*     */ 
/*     */   public void setTemplateAddName(String templateAddName) {
/* 338 */     this.templateAddName = templateAddName;
/*     */   }
/*     */ 
/*     */   @Column(name="template_update_name", nullable=true, length=200)
/*     */   public String getTemplateUpdateName() {
/* 343 */     return this.templateUpdateName;
/*     */   }
/*     */ 
/*     */   public void setTemplateUpdateName(String templateUpdateName) {
/* 347 */     this.templateUpdateName = templateUpdateName;
/*     */   }
/*     */ 
/*     */   @Column(name="template_detail_name", nullable=true, length=200)
/*     */   public String getTemplateDetailName() {
/* 352 */     return this.templateDetailName;
/*     */   }
/*     */ 
/*     */   public void setTemplateDetailName(String templateDetailName) {
/* 356 */     this.templateDetailName = templateDetailName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity
 * JD-Core Version:    0.6.2
 */