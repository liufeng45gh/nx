/*     */ package org.jeecgframework.web.system.pojo.base;
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
/*     */ @Table(name="jform_inner_mail", schema="")
/*     */ public class JformInnerMailEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */ 
/*     */   @Excel(name="主题")
/*     */   private String title;
/*     */ 
/*     */   @Excel(name="附件")
/*     */   private String attachment;
/*     */ 
/*     */   @Excel(name="内容")
/*     */   private String content;
/*     */   private String status;
/*     */   private String receiverNames;
/*     */ 
/*     */   @Excel(name="收件人标识列表")
/*     */   private String receiverIds;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  58 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  66 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  74 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/*  82 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/*  90 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/*  98 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 106 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 114 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="TITLE", nullable=true, length=100)
/*     */   public String getTitle()
/*     */   {
/* 122 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 130 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="ATTACHMENT", nullable=true, length=1000)
/*     */   public String getAttachment()
/*     */   {
/* 138 */     return this.attachment;
/*     */   }
/*     */ 
/*     */   public void setAttachment(String attachment)
/*     */   {
/* 146 */     this.attachment = attachment;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=true, length=2000)
/*     */   public String getContent()
/*     */   {
/* 154 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 162 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="STATUS", nullable=true, length=50)
/*     */   public String getStatus()
/*     */   {
/* 170 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status)
/*     */   {
/* 178 */     this.status = status;
/*     */   }
/*     */ 
/*     */   @Column(name="RECEIVER_NAMES", nullable=true, length=300)
/*     */   public String getReceiverNames()
/*     */   {
/* 186 */     return this.receiverNames;
/*     */   }
/*     */ 
/*     */   public void setReceiverNames(String receiverNames)
/*     */   {
/* 194 */     this.receiverNames = receiverNames;
/*     */   }
/*     */ 
/*     */   @Column(name="RECEIVER_IDS", nullable=true, length=300)
/*     */   public String getReceiverIds()
/*     */   {
/* 202 */     return this.receiverIds;
/*     */   }
/*     */ 
/*     */   public void setReceiverIds(String receiverIds)
/*     */   {
/* 210 */     this.receiverIds = receiverIds;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.JformInnerMailEntity
 * JD-Core Version:    0.6.2
 */