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
/*     */ 
/*     */ @Entity
/*     */ @Table(name="auto_form_db", schema="")
/*     */ public class AutoFormDbEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private String sysOrgCode;
/*     */   private String sysCompanyCode;
/*     */   private Date createDate;
/*     */   private Date updateDate;
/*     */   private String dbName;
/*     */   private String dbType;
/*     */   private String dbTableName;
/*     */   private String dbDynSql;
/*     */   private String autoFormId;
/*     */   private String dbKey;
/*     */   private String tbDbKey;
/*     */   private String tbDbTableName;
/*     */   private String dbChName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  70 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  78 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  88 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/*  96 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/* 106 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 114 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 124 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 132 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 142 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 150 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_ORG_CODE", nullable=true, length=50)
/*     */   public String getSysOrgCode()
/*     */   {
/* 160 */     return this.sysOrgCode;
/*     */   }
/*     */ 
/*     */   public void setSysOrgCode(String sysOrgCode)
/*     */   {
/* 168 */     this.sysOrgCode = sysOrgCode;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_COMPANY_CODE", nullable=true, length=50)
/*     */   public String getSysCompanyCode()
/*     */   {
/* 178 */     return this.sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public void setSysCompanyCode(String sysCompanyCode)
/*     */   {
/* 186 */     this.sysCompanyCode = sysCompanyCode;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 196 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 204 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 214 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 222 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_NAME", nullable=true, length=32)
/*     */   public String getDbName()
/*     */   {
/* 232 */     return this.dbName;
/*     */   }
/*     */ 
/*     */   public void setDbName(String dbName)
/*     */   {
/* 240 */     this.dbName = dbName;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_TYPE", nullable=true, length=32)
/*     */   public String getDbType()
/*     */   {
/* 250 */     return this.dbType;
/*     */   }
/*     */ 
/*     */   public void setDbType(String dbType)
/*     */   {
/* 258 */     this.dbType = dbType;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_TABLE_NAME", nullable=true, length=32)
/*     */   public String getDbTableName()
/*     */   {
/* 268 */     return this.dbTableName;
/*     */   }
/*     */ 
/*     */   public void setDbTableName(String dbTableName)
/*     */   {
/* 276 */     this.dbTableName = dbTableName;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_DYN_SQL", nullable=true, length=500)
/*     */   public String getDbDynSql()
/*     */   {
/* 286 */     return this.dbDynSql;
/*     */   }
/*     */ 
/*     */   public void setDbDynSql(String dbDynSql)
/*     */   {
/* 294 */     this.dbDynSql = dbDynSql;
/*     */   }
/*     */ 
/*     */   @Column(name="AUTO_FORM_ID", nullable=true, length=32)
/*     */   public String getAutoFormId()
/*     */   {
/* 304 */     return this.autoFormId;
/*     */   }
/*     */ 
/*     */   public void setAutoFormId(String autoFormId)
/*     */   {
/* 312 */     this.autoFormId = autoFormId;
/*     */   }
/*     */ 
/*     */   @Column(name="db_key", nullable=true, length=32)
/*     */   public String getDbKey()
/*     */   {
/* 323 */     return this.dbKey;
/*     */   }
/*     */ 
/*     */   public void setDbKey(String dbKey) {
/* 327 */     this.dbKey = dbKey;
/*     */   }
/*     */ 
/*     */   public void setTbDbKey(String tbDbKey)
/*     */   {
/* 333 */     this.tbDbKey = tbDbKey;
/*     */   }
/*     */ 
/*     */   @Column(name="tb_db_key", nullable=true, length=32)
/*     */   public String getTbDbKey() {
/* 338 */     return this.tbDbKey;
/*     */   }
/*     */ 
/*     */   public void setTbDbTableName(String tbDbTableName) {
/* 342 */     this.tbDbTableName = tbDbTableName;
/*     */   }
/*     */ 
/*     */   @Column(name="tb_db_table_name", nullable=true, length=32)
/*     */   public String getTbDbTableName() {
/* 347 */     return this.tbDbTableName;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_CH_NAME", nullable=true, length=32)
/*     */   public String getDbChName()
/*     */   {
/* 353 */     return this.dbChName;
/*     */   }
/*     */ 
/*     */   public void setDbChName(String dbChName) {
/* 357 */     this.dbChName = dbChName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.autoform.AutoFormDbEntity
 * JD-Core Version:    0.6.2
 */