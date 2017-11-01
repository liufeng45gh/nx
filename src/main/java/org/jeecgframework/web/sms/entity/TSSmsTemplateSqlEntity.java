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
/*     */ @Table(name="t_s_sms_template_sql", schema="")
/*     */ public class TSSmsTemplateSqlEntity
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
/*     */   @Excel(name="配置CODE")
/*     */   private String code;
/*     */ 
/*     */   @Excel(name="配置名称")
/*     */   private String name;
/*     */ 
/*     */   @Excel(name="业务SQLID")
/*     */   private String sqlId;
/*     */ 
/*     */   @Excel(name="消息模本ID")
/*     */   private String templateId;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  69 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  77 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  85 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/*  93 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/* 101 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 109 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 117 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 125 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 133 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 141 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 149 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 157 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 165 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 173 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="CODE", nullable=true, length=32)
/*     */   public String getCode()
/*     */   {
/* 181 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 189 */     this.code = code;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=true, length=32)
/*     */   public String getName()
/*     */   {
/* 197 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 205 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="SQL_ID", nullable=true, length=32)
/*     */   public String getSqlId()
/*     */   {
/* 213 */     return this.sqlId;
/*     */   }
/*     */ 
/*     */   public void setSqlId(String sqlId)
/*     */   {
/* 221 */     this.sqlId = sqlId;
/*     */   }
/*     */ 
/*     */   @Column(name="TEMPLATE_ID", nullable=true, length=32)
/*     */   public String getTemplateId()
/*     */   {
/* 229 */     return this.templateId;
/*     */   }
/*     */ 
/*     */   public void setTemplateId(String templateId)
/*     */   {
/* 237 */     this.templateId = templateId;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.entity.TSSmsTemplateSqlEntity
 * JD-Core Version:    0.6.2
 */