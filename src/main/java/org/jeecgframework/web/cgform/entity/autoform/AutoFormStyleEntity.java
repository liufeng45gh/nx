/*     */ package org.jeecgframework.web.cgform.entity.autoform;
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
/*     */ @Table(name="auto_form_style", schema="")
/*     */ public class AutoFormStyleEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="表单描述")
/*     */   private String styleDesc;
/*     */ 
/*     */   @Excel(name="样式内容")
/*     */   private String styleContent;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private Date updateDate;
/*     */   private String sysOrgCode;
/*     */   private String sysCompanyCode;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  67 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  75 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="STYLE_DESC", nullable=true, length=200)
/*     */   public String getStyleDesc()
/*     */   {
/*  83 */     return this.styleDesc;
/*     */   }
/*     */ 
/*     */   public void setStyleDesc(String styleDesc)
/*     */   {
/*  91 */     this.styleDesc = styleDesc;
/*     */   }
/*     */ 
/*     */   @Column(name="STYLE_CONTENT", nullable=true)
/*     */   public String getStyleContent()
/*     */   {
/*  99 */     return this.styleContent;
/*     */   }
/*     */ 
/*     */   public void setStyleContent(String styleContent)
/*     */   {
/* 107 */     this.styleContent = styleContent;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
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
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/* 131 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 139 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 147 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 155 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 163 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 171 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 179 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 187 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 195 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 203 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_ORG_CODE", nullable=true, length=50)
/*     */   public String getSysOrgCode()
/*     */   {
/* 211 */     return this.sysOrgCode;
/*     */   }
/*     */ 
/*     */   public void setSysOrgCode(String sysOrgCode)
/*     */   {
/* 219 */     this.sysOrgCode = sysOrgCode;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_COMPANY_CODE", nullable=true, length=50)
/*     */   public String getSysCompanyCode()
/*     */   {
/* 227 */     return this.sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public void setSysCompanyCode(String sysCompanyCode)
/*     */   {
/* 235 */     this.sysCompanyCode = sysCompanyCode;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.autoform.AutoFormStyleEntity
 * JD-Core Version:    0.6.2
 */