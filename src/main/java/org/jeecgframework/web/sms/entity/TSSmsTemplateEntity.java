/*     */ package org.jeecgframework.web.sms.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_sms_template", schema="")
/*     */ public class TSSmsTemplateEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private Date updateDate;
/*     */ 
/*     */   @Excel(name="模板类型")
/*     */   private String templateType;
/*     */ 
/*     */   @Excel(name="模板名称")
/*     */   private String templateName;
/*     */ 
/*     */   @Excel(name="模板内容")
/*     */   private String templateContent;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  66 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  74 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  82 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/*  90 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/*  98 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 106 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 114 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 122 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 130 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 138 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 146 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 154 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 162 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 170 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_TYPE", nullable=true, length=1)
/*     */   public String getTemplateType()
/*     */   {
/* 178 */     return this.templateType;
/*     */   }
/*     */ 
/*     */   public void setTemplateType(String templateType)
/*     */   {
/* 186 */     this.templateType = templateType;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_NAME", nullable=true, length=50)
/*     */   public String getTemplateName()
/*     */   {
/* 194 */     return this.templateName;
/*     */   }
/*     */ 
/*     */   public void setTemplateName(String templateName)
/*     */   {
/* 202 */     this.templateName = templateName;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_CONTENT", nullable=true, length=1000)
/*     */   public String getTemplateContent()
/*     */   {
/* 210 */     return this.templateContent;
/*     */   }
/*     */ 
/*     */   public void setTemplateContent(String templateContent)
/*     */   {
/* 218 */     this.templateContent = templateContent;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.entity.TSSmsTemplateEntity
 * JD-Core Version:    0.6.2
 */