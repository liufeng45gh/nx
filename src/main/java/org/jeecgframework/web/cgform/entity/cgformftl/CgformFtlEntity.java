/*     */ package org.jeecgframework.web.cgform.entity.cgformftl;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cgform_ftl", schema="")
/*     */ public class CgformFtlEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String cgformId;
/*     */   private String cgformName;
/*     */   private Integer ftlVersion;
/*     */   private String ftlContent;
/*     */   private String ftlStatus;
/*     */   private String ftlWordUrl;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date createDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*     */   private Date updateDate;
/*     */   private String editorType;
/*     */ 
/*     */   @Column(name="EDITOR_TYPE", nullable=false, length=32)
/*     */   public String getEditorType()
/*     */   {
/*  54 */     return this.editorType;
/*     */   }
/*     */ 
/*     */   public void setEditorType(String editorType) {
/*  58 */     this.editorType = editorType;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  71 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  79 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CGFORM_ID", nullable=false, length=36)
/*     */   public String getCgformId()
/*     */   {
/*  87 */     return this.cgformId;
/*     */   }
/*     */ 
/*     */   public void setCgformId(String cgformId)
/*     */   {
/*  95 */     this.cgformId = cgformId;
/*     */   }
/*     */ 
/*     */   @Column(name="CGFORM_NAME", nullable=false, length=100)
/*     */   public String getCgformName()
/*     */   {
/* 103 */     return this.cgformName;
/*     */   }
/*     */ 
/*     */   public void setCgformName(String cgformName)
/*     */   {
/* 111 */     this.cgformName = cgformName;
/*     */   }
/*     */ 
/*     */   @Column(name="FTL_VERSION", nullable=false, length=10)
/*     */   public Integer getFtlVersion()
/*     */   {
/* 119 */     return this.ftlVersion;
/*     */   }
/*     */ 
/*     */   public void setFtlVersion(Integer ftlVersion)
/*     */   {
/* 127 */     this.ftlVersion = ftlVersion;
/*     */   }
/*     */ 
/*     */   @Lob
/*     */   @Column(name="FTL_CONTENT", nullable=true)
/*     */   public String getFtlContent()
/*     */   {
/* 136 */     return this.ftlContent;
/*     */   }
/*     */ 
/*     */   public void setFtlContent(String ftlContent)
/*     */   {
/* 144 */     this.ftlContent = ftlContent;
/*     */   }
/*     */ 
/*     */   @Column(name="FTL_STATUS", nullable=true, length=50)
/*     */   public String getFtlStatus()
/*     */   {
/* 152 */     return this.ftlStatus;
/*     */   }
/*     */ 
/*     */   public void setFtlStatus(String ftlStatus)
/*     */   {
/* 160 */     this.ftlStatus = ftlStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="FTL_WORD_URL", nullable=true, length=200)
/*     */   public String getFtlWordUrl()
/*     */   {
/* 168 */     return this.ftlWordUrl;
/*     */   }
/*     */ 
/*     */   public void setFtlWordUrl(String ftlWordUrl)
/*     */   {
/* 176 */     this.ftlWordUrl = ftlWordUrl;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=36)
/*     */   public String getCreateBy()
/*     */   {
/* 184 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 192 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=32)
/*     */   public String getCreateName()
/*     */   {
/* 200 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 208 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/* 216 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 224 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=36)
/*     */   public String getUpdateBy()
/*     */   {
/* 232 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 240 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=32)
/*     */   public String getUpdateName()
/*     */   {
/* 248 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 256 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true)
/*     */   public Date getUpdateDate()
/*     */   {
/* 264 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 272 */     this.updateDate = updateDate;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.cgformftl.CgformFtlEntity
 * JD-Core Version:    0.6.2
 */