/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_data_log", schema="")
/*     */ public class TSDatalogEntity extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private Date updateDate;
/*     */   private String sysOrgCode;
/*     */   private String sysCompanyCode;
/*     */ 
/*     */   @Excel(name="表名")
/*     */   private String tableName;
/*     */ 
/*     */   @Excel(name="数据ID")
/*     */   private String dataId;
/*     */ 
/*     */   @Excel(name="数据内容")
/*     */   private String dataContent;
/*     */ 
/*     */   @Excel(name="版本号")
/*     */   private Integer versionNumber;
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  57 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/*  65 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/*  73 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/*  81 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/*  89 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/*  97 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 105 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 113 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 121 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 129 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 137 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 145 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_ORG_CODE", nullable=true, length=50)
/*     */   public String getSysOrgCode()
/*     */   {
/* 153 */     return this.sysOrgCode;
/*     */   }
/*     */ 
/*     */   public void setSysOrgCode(String sysOrgCode)
/*     */   {
/* 161 */     this.sysOrgCode = sysOrgCode;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_COMPANY_CODE", nullable=true, length=50)
/*     */   public String getSysCompanyCode()
/*     */   {
/* 169 */     return this.sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public void setSysCompanyCode(String sysCompanyCode)
/*     */   {
/* 177 */     this.sysCompanyCode = sysCompanyCode;
/*     */   }
/*     */ 
/*     */   @Column(name="TABLE_NAME", nullable=true, length=32)
/*     */   public String getTableName()
/*     */   {
/* 185 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   public void setTableName(String tableName)
/*     */   {
/* 193 */     this.tableName = tableName;
/*     */   }
/*     */ 
/*     */   @Column(name="DATA_ID", nullable=true, length=32)
/*     */   public String getDataId()
/*     */   {
/* 201 */     return this.dataId;
/*     */   }
/*     */ 
/*     */   public void setDataId(String dataId)
/*     */   {
/* 209 */     this.dataId = dataId;
/*     */   }
/*     */ 
/*     */   @Column(name="DATA_CONTENT", nullable=true, length=32)
/*     */   public String getDataContent()
/*     */   {
/* 217 */     return this.dataContent;
/*     */   }
/*     */ 
/*     */   public void setDataContent(String dataContent)
/*     */   {
/* 225 */     this.dataContent = dataContent;
/*     */   }
/*     */ 
/*     */   @Column(name="VERSION_NUMBER", nullable=true, length=4)
/*     */   public Integer getVersionNumber()
/*     */   {
/* 233 */     return this.versionNumber;
/*     */   }
/*     */ 
/*     */   public void setVersionNumber(Integer versionNumber)
/*     */   {
/* 241 */     this.versionNumber = versionNumber;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSDatalogEntity
 * JD-Core Version:    0.6.2
 */