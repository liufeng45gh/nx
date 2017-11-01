/*     */ package org.jeecgframework.web.cgdynamgraph.entity.core;
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
/*     */ @Table(name="jform_cgdynamgraph_param", schema="")
/*     */ public class CgDynamGraphConfigParamEntity
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
/*     */   @Excel(name="参数名称")
/*     */   private String paramName;
/*     */ 
/*     */   @Excel(name="参数说明")
/*     */   private String paramDesc;
/*     */ 
/*     */   @Excel(name="数值")
/*     */   private String paramValue;
/*     */ 
/*     */   @Excel(name="排序")
/*     */   private Integer seq;
/*     */ 
/*     */   @Excel(name="动态报表ID")
/*     */   private String cgrheadId;
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
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  83 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/*  91 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
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
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 115 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 123 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 131 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 139 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
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
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 163 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 171 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_ORG_CODE", nullable=true, length=50)
/*     */   public String getSysOrgCode()
/*     */   {
/* 179 */     return this.sysOrgCode;
/*     */   }
/*     */ 
/*     */   public void setSysOrgCode(String sysOrgCode)
/*     */   {
/* 187 */     this.sysOrgCode = sysOrgCode;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_COMPANY_CODE", nullable=true, length=50)
/*     */   public String getSysCompanyCode()
/*     */   {
/* 195 */     return this.sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public void setSysCompanyCode(String sysCompanyCode)
/*     */   {
/* 203 */     this.sysCompanyCode = sysCompanyCode;
/*     */   }
/*     */ 
/*     */   @Column(name="PARAM_NAME", nullable=false, length=32)
/*     */   public String getParamName()
/*     */   {
/* 211 */     return this.paramName;
/*     */   }
/*     */ 
/*     */   public void setParamName(String paramName)
/*     */   {
/* 219 */     this.paramName = paramName;
/*     */   }
/*     */ 
/*     */   @Column(name="PARAM_DESC", nullable=true, length=32)
/*     */   public String getParamDesc()
/*     */   {
/* 227 */     return this.paramDesc;
/*     */   }
/*     */ 
/*     */   public void setParamDesc(String paramDesc)
/*     */   {
/* 235 */     this.paramDesc = paramDesc;
/*     */   }
/*     */ 
/*     */   @Column(name="PARAM_VALUE", nullable=true, length=32)
/*     */   public String getParamValue()
/*     */   {
/* 243 */     return this.paramValue;
/*     */   }
/*     */ 
/*     */   public void setParamValue(String paramValue)
/*     */   {
/* 251 */     this.paramValue = paramValue;
/*     */   }
/*     */ 
/*     */   @Column(name="SEQ", nullable=true, length=32)
/*     */   public Integer getSeq()
/*     */   {
/* 259 */     return this.seq;
/*     */   }
/*     */ 
/*     */   public void setSeq(Integer seq)
/*     */   {
/* 267 */     this.seq = seq;
/*     */   }
/*     */ 
/*     */   @Column(name="CGRHEAD_ID", nullable=true, length=36)
/*     */   public String getCgrheadId()
/*     */   {
/* 276 */     return this.cgrheadId;
/*     */   }
/*     */ 
/*     */   public void setCgrheadId(String cgrheadId) {
/* 280 */     this.cgrheadId = cgrheadId;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigParamEntity
 * JD-Core Version:    0.6.2
 */