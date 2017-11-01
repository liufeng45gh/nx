/*     */ package org.jeecgframework.web.cgform.entity.autoform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class AutoFormDbPage
/*     */   implements Serializable
/*     */ {
/*  17 */   private List<AutoFormDbFieldEntity> autoFormDbFieldList = new ArrayList();
/*     */ 
/*  25 */   private List<AutoFormParamEntity> autoFormParamList = new ArrayList();
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
/*     */ 
/*     */   public List<AutoFormDbFieldEntity> getAutoFormDbFieldList()
/*     */   {
/*  19 */     return this.autoFormDbFieldList;
/*     */   }
/*     */   public void setAutoFormDbFieldList(List<AutoFormDbFieldEntity> autoFormDbFieldList) {
/*  22 */     this.autoFormDbFieldList = autoFormDbFieldList;
/*     */   }
/*     */ 
/*     */   public List<AutoFormParamEntity> getAutoFormParamList()
/*     */   {
/*  27 */     return this.autoFormParamList;
/*     */   }
/*     */   public void setAutoFormParamList(List<AutoFormParamEntity> autoFormParamList) {
/*  30 */     this.autoFormParamList = autoFormParamList;
/*     */   }
/*     */ 
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
/*     */   public String getCreateName()
/*     */   {
/*  84 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/*  92 */     this.createName = createName;
/*     */   }
/*     */ 
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
/*     */   public String getUpdateName()
/*     */   {
/* 114 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 122 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 129 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 137 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public String getSysOrgCode()
/*     */   {
/* 144 */     return this.sysOrgCode;
/*     */   }
/*     */ 
/*     */   public void setSysOrgCode(String sysOrgCode)
/*     */   {
/* 152 */     this.sysOrgCode = sysOrgCode;
/*     */   }
/*     */ 
/*     */   public String getSysCompanyCode()
/*     */   {
/* 159 */     return this.sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public void setSysCompanyCode(String sysCompanyCode)
/*     */   {
/* 167 */     this.sysCompanyCode = sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 174 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 182 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 189 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 197 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getDbName()
/*     */   {
/* 204 */     return this.dbName;
/*     */   }
/*     */ 
/*     */   public void setDbName(String dbName)
/*     */   {
/* 212 */     this.dbName = dbName;
/*     */   }
/*     */ 
/*     */   public String getDbType()
/*     */   {
/* 219 */     return this.dbType;
/*     */   }
/*     */ 
/*     */   public void setDbType(String dbType)
/*     */   {
/* 227 */     this.dbType = dbType;
/*     */   }
/*     */ 
/*     */   public String getDbTableName()
/*     */   {
/* 234 */     return this.dbTableName;
/*     */   }
/*     */ 
/*     */   public void setDbTableName(String dbTableName)
/*     */   {
/* 242 */     this.dbTableName = dbTableName;
/*     */   }
/*     */ 
/*     */   public String getDbDynSql()
/*     */   {
/* 249 */     return this.dbDynSql;
/*     */   }
/*     */ 
/*     */   public void setDbDynSql(String dbDynSql)
/*     */   {
/* 257 */     this.dbDynSql = dbDynSql;
/*     */   }
/*     */ 
/*     */   public String getAutoFormId()
/*     */   {
/* 264 */     return this.autoFormId;
/*     */   }
/*     */ 
/*     */   public void setAutoFormId(String autoFormId)
/*     */   {
/* 272 */     this.autoFormId = autoFormId;
/*     */   }
/*     */ 
/*     */   public String getDbKey() {
/* 276 */     return this.dbKey;
/*     */   }
/*     */   public void setDbKey(String dbKey) {
/* 279 */     this.dbKey = dbKey;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.autoform.AutoFormDbPage
 * JD-Core Version:    0.6.2
 */