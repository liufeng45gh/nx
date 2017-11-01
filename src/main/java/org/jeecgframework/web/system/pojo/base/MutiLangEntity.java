/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.DynamicInsert;
/*     */ import org.hibernate.annotations.DynamicUpdate;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_muti_lang", schema="")
/*     */ @DynamicUpdate(true)
/*     */ @DynamicInsert(true)
/*     */ public class MutiLangEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String langKey;
/*     */   private String langContext;
/*     */   private String langCode;
/*     */   private Date createDate;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date updateDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  62 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  70 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="LANG_KEY", nullable=false, length=50)
/*     */   public String getLangKey()
/*     */   {
/*  78 */     return this.langKey;
/*     */   }
/*     */ 
/*     */   public void setLangKey(String langKey)
/*     */   {
/*  86 */     this.langKey = langKey;
/*     */   }
/*     */ 
/*     */   @Column(name="LANG_CONTEXT", nullable=false, length=500)
/*     */   public String getLangContext()
/*     */   {
/*  94 */     return this.langContext;
/*     */   }
/*     */ 
/*     */   public void setLangContext(String langContext)
/*     */   {
/* 102 */     this.langContext = langContext;
/*     */   }
/*     */ 
/*     */   @Column(name="LANG_CODE", nullable=false, length=50)
/*     */   public String getLangCode()
/*     */   {
/* 110 */     return this.langCode;
/*     */   }
/*     */ 
/*     */   public void setLangCode(String langCode)
/*     */   {
/* 118 */     this.langCode = langCode;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=false)
/*     */   public Date getCreateDate()
/*     */   {
/* 126 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 134 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=false, length=50)
/*     */   public String getCreateBy()
/*     */   {
/* 142 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 150 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=false, length=50)
/*     */   public String getCreateName()
/*     */   {
/* 158 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 166 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=false)
/*     */   public Date getUpdateDate()
/*     */   {
/* 174 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 182 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=false, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 190 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 198 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=false, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 206 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 214 */     this.updateName = updateName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.MutiLangEntity
 * JD-Core Version:    0.6.2
 */