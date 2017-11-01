/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Inheritance;
/*     */ import javax.persistence.InheritanceType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_attachment")
/*     */ @Inheritance(strategy=InheritanceType.JOINED)
/*     */ public class TSAttachment extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private TSUser TSUser;
/*     */   private String businessKey;
/*     */   private String subclassname;
/*     */   private String attachmenttitle;
/*     */   private byte[] attachmentcontent;
/*     */   private String realpath;
/*     */   private Timestamp createdate;
/*     */   private String note;
/*     */   private String swfpath;
/*     */   private String extend;
/*     */ 
/*     */   @Column(name="extend", length=32)
/*     */   public String getExtend()
/*     */   {
/*  47 */     return this.extend;
/*     */   }
/*     */ 
/*     */   public void setExtend(String extend) {
/*  51 */     this.extend = extend;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="userid")
/*     */   public TSUser getTSUser() {
/*  57 */     return this.TSUser;
/*     */   }
/*     */ 
/*     */   public void setTSUser(TSUser TSUser) {
/*  61 */     this.TSUser = TSUser;
/*     */   }
/*     */ 
/*     */   @Column(name="businesskey", length=32)
/*     */   public String getBusinessKey() {
/*  66 */     return this.businessKey;
/*     */   }
/*     */ 
/*     */   public void setBusinessKey(String businessKey) {
/*  70 */     this.businessKey = businessKey;
/*     */   }
/*     */ 
/*     */   @Column(name="attachmenttitle", length=100)
/*     */   public String getAttachmenttitle() {
/*  75 */     return this.attachmenttitle;
/*     */   }
/*     */ 
/*     */   public void setAttachmenttitle(String attachmenttitle) {
/*  79 */     this.attachmenttitle = attachmenttitle;
/*     */   }
/*     */   @Column(name="attachmentcontent", length=3000)
/*     */   @Lob
/*     */   public byte[] getAttachmentcontent() {
/*  85 */     return this.attachmentcontent;
/*     */   }
/*     */ 
/*     */   public void setAttachmentcontent(byte[] attachmentcontent) {
/*  89 */     this.attachmentcontent = attachmentcontent;
/*     */   }
/*     */ 
/*     */   @Column(name="realpath", length=100)
/*     */   public String getRealpath() {
/*  94 */     return this.realpath;
/*     */   }
/*     */ 
/*     */   public void setRealpath(String realpath) {
/*  98 */     this.realpath = realpath;
/*     */   }
/*     */ 
/*     */   @Column(name="createdate", length=35)
/*     */   public Timestamp getCreatedate()
/*     */   {
/* 104 */     return this.createdate;
/*     */   }
/*     */ 
/*     */   public void setCreatedate(Timestamp createdate) {
/* 108 */     this.createdate = createdate;
/*     */   }
/*     */ 
/*     */   @Column(name="note", length=300)
/*     */   public String getNote() {
/* 113 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 117 */     this.note = note;
/*     */   }
/*     */ 
/*     */   @Column(name="swfpath", length=300)
/*     */   public String getSwfpath() {
/* 122 */     return this.swfpath;
/*     */   }
/*     */ 
/*     */   public void setSwfpath(String swfpath) {
/* 126 */     this.swfpath = swfpath;
/*     */   }
/*     */   @Column(name="subclassname", length=300)
/*     */   public String getSubclassname() {
/* 130 */     return this.subclassname;
/*     */   }
/*     */ 
/*     */   public void setSubclassname(String subclassname) {
/* 134 */     this.subclassname = subclassname;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSAttachment
 * JD-Core Version:    0.6.2
 */