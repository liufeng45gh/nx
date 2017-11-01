/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jform_inner_mail_receiver", schema="")
/*     */ public class JformInnerMailReceiverEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private Date createDate;
/*     */   private Date updateDate;
/*     */ 
/*     */   @Excel(name="收件状态")
/*     */   private String status;
/*  40 */   private TSUser TSUser = new TSUser();
/*  41 */   private JformInnerMailEntity JMail = new JformInnerMailEntity();
/*     */ 
/*  46 */   @ManyToOne(fetch=FetchType.EAGER)
/*     */   @JoinColumn(name="user_id")
/*     */   public TSUser getTSUser() { return this.TSUser; }
/*     */ 
/*     */   public void setTSUser(TSUser TSUser)
/*     */   {
/*  50 */     this.TSUser = TSUser;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.EAGER)
/*     */   @JoinColumn(name="mail_id")
/*     */   public JformInnerMailEntity getJMail() {
/*  56 */     return this.JMail;
/*     */   }
/*     */ 
/*     */   public void setJMail(JformInnerMailEntity jMail) {
/*  60 */     this.JMail = jMail;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  72 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  80 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/*  88 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/*  96 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 104 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 112 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="STATUS", nullable=true, length=50)
/*     */   public String getStatus()
/*     */   {
/* 121 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status)
/*     */   {
/* 129 */     this.status = status;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.JformInnerMailReceiverEntity
 * JD-Core Version:    0.6.2
 */