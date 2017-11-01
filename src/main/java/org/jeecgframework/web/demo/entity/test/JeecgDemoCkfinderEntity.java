/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="ck_finder", schema="")
/*     */ public class JeecgDemoCkfinderEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String image;
/*     */   private String attachment;
/*     */   private String remark;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  46 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  55 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="IMAGE", nullable=true, length=255)
/*     */   public String getImage()
/*     */   {
/*  65 */     return this.image;
/*     */   }
/*     */ 
/*     */   public void setImage(String image)
/*     */   {
/*  74 */     this.image = image;
/*     */   }
/*     */ 
/*     */   @Column(name="ATTACHMENT", nullable=true, length=255)
/*     */   public String getAttachment()
/*     */   {
/*  84 */     return this.attachment;
/*     */   }
/*     */ 
/*     */   public void setAttachment(String attachment)
/*     */   {
/*  93 */     this.attachment = attachment;
/*     */   }
/*     */ 
/*     */   @Lob
/*     */   @Basic(fetch=FetchType.LAZY)
/*     */   @Column(name="REMARK", nullable=true)
/*     */   public String getRemark()
/*     */   {
/* 105 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 114 */     this.remark = remark;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgDemoCkfinderEntity
 * JD-Core Version:    0.6.2
 */