/*     */ package org.jeecgframework.web.rank.entity;
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
/*     */ @Table(name="t_s_team_person", schema="")
/*     */ public class TSTeamPersonEntity
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
/*     */   @Excel(name="名称")
/*     */   private String name;
/*     */ 
/*     */   @Excel(name="头像", type=2, height=15.0D, width=20.0D)
/*     */   private String imgSrc;
/*     */ 
/*     */   @Excel(name="简介")
/*     */   private String introduction;
/*     */ 
/*     */   @Excel(name="加入时间", importFormat="yyyy-MM-dd HH:mm:ss")
/*     */   private Date jionDate;
/*     */   private Integer isJoin;
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
/*     */   @Column(name="NAME", nullable=true, length=32)
/*     */   public String getName()
/*     */   {
/* 211 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 219 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="IMG_SRC", nullable=true, length=50)
/*     */   public String getImgSrc()
/*     */   {
/* 227 */     return this.imgSrc;
/*     */   }
/*     */ 
/*     */   public void setImgSrc(String imgSrc)
/*     */   {
/* 235 */     this.imgSrc = imgSrc;
/*     */   }
/*     */ 
/*     */   @Column(name="INTRODUCTION", nullable=true, length=500)
/*     */   public String getIntroduction()
/*     */   {
/* 243 */     return this.introduction;
/*     */   }
/*     */ 
/*     */   public void setIntroduction(String introduction)
/*     */   {
/* 251 */     this.introduction = introduction;
/*     */   }
/*     */ 
/*     */   @Column(name="JION_DATE", nullable=true, length=20)
/*     */   public Date getJionDate()
/*     */   {
/* 259 */     return this.jionDate;
/*     */   }
/*     */ 
/*     */   public void setJionDate(Date jionDate)
/*     */   {
/* 267 */     this.jionDate = jionDate;
/*     */   }
/*     */ 
/*     */   @Column(name="is_join")
/*     */   public Integer getIsJoin() {
/* 272 */     return this.isJoin;
/*     */   }
/*     */ 
/*     */   public void setIsJoin(Integer isJoin) {
/* 276 */     this.isJoin = isJoin;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.rank.entity.TSTeamPersonEntity
 * JD-Core Version:    0.6.2
 */