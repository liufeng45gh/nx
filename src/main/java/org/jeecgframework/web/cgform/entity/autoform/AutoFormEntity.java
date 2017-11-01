/*     */ package org.jeecgframework.web.cgform.entity.autoform;
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
/*     */ @Table(name="auto_form", schema="")
/*     */ public class AutoFormEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="表单名称")
/*     */   private String formName;
/*     */ 
/*     */   @Excel(name="表单描述")
/*     */   private String formDesc;
/*     */ 
/*     */   @Excel(name="模板样式")
/*     */   private String formStyleId;
/*     */ 
/*     */   @Excel(name="表单内容")
/*     */   private String formContent;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private Date updateDate;
/*     */ 
/*     */   @Excel(name="所属部门")
/*     */   private String sysOrgCode;
/*     */ 
/*     */   @Excel(name="所属公司")
/*     */   private String sysCompanyCode;
/*     */   private String formParse;
/*     */   private String dbId;
/*     */   private String autoFormId;
/*     */   private String mainTableSource;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  86 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  94 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="FORM_NAME", nullable=true, length=100)
/*     */   public String getFormName()
/*     */   {
/* 102 */     return this.formName;
/*     */   }
/*     */ 
/*     */   public void setFormName(String formName)
/*     */   {
/* 110 */     this.formName = formName;
/*     */   }
/*     */ 
/*     */   @Column(name="FORM_DESC", nullable=true, length=200)
/*     */   public String getFormDesc()
/*     */   {
/* 118 */     return this.formDesc;
/*     */   }
/*     */ 
/*     */   public void setFormDesc(String formDesc)
/*     */   {
/* 126 */     this.formDesc = formDesc;
/*     */   }
/*     */ 
/*     */   @Column(name="FORM_STYLE_ID", nullable=true, length=36)
/*     */   public String getFormStyleId()
/*     */   {
/* 134 */     return this.formStyleId;
/*     */   }
/*     */ 
/*     */   public void setFormStyleId(String formStyleId)
/*     */   {
/* 142 */     this.formStyleId = formStyleId;
/*     */   }
/*     */ 
/*     */   @Column(name="FORM_CONTENT", nullable=true)
/*     */   public String getFormContent()
/*     */   {
/* 150 */     return this.formContent;
/*     */   }
/*     */ 
/*     */   public void setFormContent(String formContent)
/*     */   {
/* 158 */     this.formContent = formContent;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/* 166 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 174 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/* 182 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 190 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 198 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 206 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 214 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 222 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 230 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 238 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 246 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 254 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_ORG_CODE", nullable=true, length=50)
/*     */   public String getSysOrgCode()
/*     */   {
/* 262 */     return this.sysOrgCode;
/*     */   }
/*     */ 
/*     */   public void setSysOrgCode(String sysOrgCode)
/*     */   {
/* 270 */     this.sysOrgCode = sysOrgCode;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_COMPANY_CODE", nullable=true, length=50)
/*     */   public String getSysCompanyCode()
/*     */   {
/* 278 */     return this.sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public void setSysCompanyCode(String sysCompanyCode)
/*     */   {
/* 286 */     this.sysCompanyCode = sysCompanyCode;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getDbId() {
/* 291 */     return this.dbId;
/*     */   }
/*     */ 
/*     */   public void setDbId(String dbId) {
/* 295 */     this.dbId = dbId;
/*     */   }
/*     */ 
/*     */   @Column(name="FORM_PARSE", nullable=true)
/*     */   public String getFormParse() {
/* 300 */     return this.formParse;
/*     */   }
/*     */ 
/*     */   public void setFormParse(String formParse) {
/* 304 */     this.formParse = formParse;
/*     */   }
/*     */   @Transient
/*     */   public String getAutoFormId() {
/* 308 */     return this.autoFormId;
/*     */   }
/*     */ 
/*     */   public void setAutoFormId(String autoFormId) {
/* 312 */     this.autoFormId = autoFormId;
/*     */   }
/*     */ 
/*     */   @Column(name="main_table_source", nullable=true)
/*     */   public String getMainTableSource() {
/* 317 */     return this.mainTableSource;
/*     */   }
/*     */ 
/*     */   public void setMainTableSource(String mainTableSource) {
/* 321 */     this.mainTableSource = mainTableSource;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.autoform.AutoFormEntity
 * JD-Core Version:    0.6.2
 */