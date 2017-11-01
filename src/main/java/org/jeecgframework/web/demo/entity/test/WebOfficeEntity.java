/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Blob;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="doc", schema="")
/*     */ public class WebOfficeEntity
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String docid;
/*     */   private String doctitle;
/*     */   private String doctype;
/*     */   private Date docdate;
/*     */   private Blob doccontent;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.AUTO)
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public Integer getId()
/*     */   {
/*  46 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  54 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="DOCID", nullable=true, length=255)
/*     */   public String getDocid()
/*     */   {
/*  62 */     return this.docid;
/*     */   }
/*     */ 
/*     */   public void setDocid(String docid)
/*     */   {
/*  70 */     this.docid = docid;
/*     */   }
/*     */ 
/*     */   @Column(name="DOCTITLE", nullable=true, length=255)
/*     */   public String getDoctitle()
/*     */   {
/*  78 */     return this.doctitle;
/*     */   }
/*     */ 
/*     */   public void setDoctitle(String doctitle)
/*     */   {
/*  86 */     this.doctitle = doctitle;
/*     */   }
/*     */ 
/*     */   @Column(name="DOCTYPE", nullable=true, length=255)
/*     */   public String getDoctype()
/*     */   {
/*  94 */     return this.doctype;
/*     */   }
/*     */ 
/*     */   public void setDoctype(String doctype)
/*     */   {
/* 102 */     this.doctype = doctype;
/*     */   }
/*     */ 
/*     */   @Column(name="DOCDATE", nullable=true)
/*     */   public Date getDocdate()
/*     */   {
/* 110 */     return this.docdate;
/*     */   }
/*     */ 
/*     */   public void setDocdate(Date docdate)
/*     */   {
/* 118 */     this.docdate = docdate;
/*     */   }
/*     */ 
/*     */   @Column(name="DOCCONTENT", nullable=true)
/*     */   public Blob getDoccontent()
/*     */   {
/* 126 */     return this.doccontent;
/*     */   }
/*     */ 
/*     */   public void setDoccontent(Blob doccontent)
/*     */   {
/* 134 */     this.doccontent = doccontent;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.WebOfficeEntity
 * JD-Core Version:    0.6.2
 */