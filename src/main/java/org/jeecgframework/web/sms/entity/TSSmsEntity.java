/*     */ package org.jeecgframework.web.sms.entity;
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
/*     */ @Table(name="t_s_sms", schema="")
/*     */ public class TSSmsEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private Date updateDate;
/*     */ 
/*     */   @Excel(name="消息标题")
/*     */   private String esTitle;
/*     */ 
/*     */   @Excel(name="消息类型")
/*     */   private String esType;
/*     */ 
/*     */   @Excel(name="发送人")
/*     */   private String esSender;
/*     */ 
/*     */   @Excel(name="接收人")
/*     */   private String esReceiver;
/*     */ 
/*     */   @Excel(name="内容")
/*     */   private String esContent;
/*     */ 
/*     */   @Excel(name="发送时间")
/*     */   private Date esSendtime;
/*     */ 
/*     */   @Excel(name="发送状态")
/*     */   private String esStatus;
/*     */ 
/*     */   @Excel(name="备注")
/*     */   private String remark;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  81 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  89 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  97 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 105 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/* 113 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 121 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 129 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 137 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 145 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 153 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 161 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 169 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 177 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 185 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="ES_TITLE", nullable=true, length=32)
/*     */   public String getEsTitle()
/*     */   {
/* 193 */     return this.esTitle;
/*     */   }
/*     */ 
/*     */   public void setEsTitle(String esTitle)
/*     */   {
/* 201 */     this.esTitle = esTitle;
/*     */   }
/*     */ 
/*     */   @Column(name="ES_TYPE", nullable=true, length=1)
/*     */   public String getEsType()
/*     */   {
/* 209 */     return this.esType;
/*     */   }
/*     */ 
/*     */   public void setEsType(String esType)
/*     */   {
/* 217 */     this.esType = esType;
/*     */   }
/*     */ 
/*     */   @Column(name="ES_SENDER", nullable=true, length=50)
/*     */   public String getEsSender()
/*     */   {
/* 225 */     return this.esSender;
/*     */   }
/*     */ 
/*     */   public void setEsSender(String esSender)
/*     */   {
/* 233 */     this.esSender = esSender;
/*     */   }
/*     */ 
/*     */   @Column(name="ES_RECEIVER", nullable=true, length=50)
/*     */   public String getEsReceiver()
/*     */   {
/* 241 */     return this.esReceiver;
/*     */   }
/*     */ 
/*     */   public void setEsReceiver(String esReceiver)
/*     */   {
/* 249 */     this.esReceiver = esReceiver;
/*     */   }
/*     */ 
/*     */   @Column(name="ES_CONTENT", nullable=true, length=1000)
/*     */   public String getEsContent()
/*     */   {
/* 257 */     return this.esContent;
/*     */   }
/*     */ 
/*     */   public void setEsContent(String esContent)
/*     */   {
/* 265 */     this.esContent = esContent;
/*     */   }
/*     */ 
/*     */   @Column(name="ES_SENDTIME", nullable=true, length=32)
/*     */   public Date getEsSendtime()
/*     */   {
/* 273 */     return this.esSendtime;
/*     */   }
/*     */ 
/*     */   public void setEsSendtime(Date esSendtime)
/*     */   {
/* 281 */     this.esSendtime = esSendtime;
/*     */   }
/*     */ 
/*     */   @Column(name="ES_STATUS", nullable=true, length=1)
/*     */   public String getEsStatus()
/*     */   {
/* 289 */     return this.esStatus;
/*     */   }
/*     */ 
/*     */   public void setEsStatus(String esStatus)
/*     */   {
/* 297 */     this.esStatus = esStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="remark", nullable=true, length=1)
/*     */   public String getRemark()
/*     */   {
/* 306 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 314 */     this.remark = remark;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.entity.TSSmsEntity
 * JD-Core Version:    0.6.2
 */