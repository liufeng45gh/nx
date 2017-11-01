/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Blob;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_attachment", schema="")
/*     */ public class JeecgBlobDataEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String userid;
/*     */   private String businesskey;
/*     */   private String infotypeid;
/*     */   private String attachmenttitle;
/*     */   private String realpath;
/*     */   private String subclassname;
/*     */   private Date createdate;
/*     */   private Blob attachmentcontent;
/*     */   private String swfpath;
/*     */   private String note;
/*     */   private String extend;
/*     */   private String busentityname;
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
/*     */   @Column(name="USERID", nullable=true, length=32)
/*     */   public String getUserid()
/*     */   {
/*  78 */     return this.userid;
/*     */   }
/*     */ 
/*     */   public void setUserid(String userid)
/*     */   {
/*  86 */     this.userid = userid;
/*     */   }
/*     */ 
/*     */   @Column(name="BUSINESSKEY", nullable=true, length=32)
/*     */   public String getBusinesskey()
/*     */   {
/*  94 */     return this.businesskey;
/*     */   }
/*     */ 
/*     */   public void setBusinesskey(String businesskey)
/*     */   {
/* 102 */     this.businesskey = businesskey;
/*     */   }
/*     */ 
/*     */   @Column(name="INFOTYPEID", nullable=true, length=32)
/*     */   public String getInfotypeid()
/*     */   {
/* 110 */     return this.infotypeid;
/*     */   }
/*     */ 
/*     */   public void setInfotypeid(String infotypeid)
/*     */   {
/* 118 */     this.infotypeid = infotypeid;
/*     */   }
/*     */ 
/*     */   @Column(name="ATTACHMENTTITLE", nullable=true, length=100)
/*     */   public String getAttachmenttitle()
/*     */   {
/* 126 */     return this.attachmenttitle;
/*     */   }
/*     */ 
/*     */   public void setAttachmenttitle(String attachmenttitle)
/*     */   {
/* 134 */     this.attachmenttitle = attachmenttitle;
/*     */   }
/*     */ 
/*     */   @Column(name="REALPATH", nullable=true, length=100)
/*     */   public String getRealpath()
/*     */   {
/* 142 */     return this.realpath;
/*     */   }
/*     */ 
/*     */   public void setRealpath(String realpath)
/*     */   {
/* 150 */     this.realpath = realpath;
/*     */   }
/*     */ 
/*     */   @Column(name="SUBCLASSNAME", nullable=true, length=65535)
/*     */   public String getSubclassname()
/*     */   {
/* 158 */     return this.subclassname;
/*     */   }
/*     */ 
/*     */   public void setSubclassname(String subclassname)
/*     */   {
/* 166 */     this.subclassname = subclassname;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATEDATE", nullable=true)
/*     */   public Date getCreatedate()
/*     */   {
/* 174 */     return this.createdate;
/*     */   }
/*     */ 
/*     */   public void setCreatedate(Date createdate)
/*     */   {
/* 182 */     this.createdate = createdate;
/*     */   }
/*     */ 
/*     */   @Column(name="ATTACHMENTCONTENT", length=3000)
/*     */   public Blob getAttachmentcontent()
/*     */   {
/* 190 */     return this.attachmentcontent;
/*     */   }
/*     */ 
/*     */   public void setAttachmentcontent(Blob attachmentcontent)
/*     */   {
/* 198 */     this.attachmentcontent = attachmentcontent;
/*     */   }
/*     */ 
/*     */   @Column(name="SWFPATH", nullable=true, length=65535)
/*     */   public String getSwfpath()
/*     */   {
/* 206 */     return this.swfpath;
/*     */   }
/*     */ 
/*     */   public void setSwfpath(String swfpath)
/*     */   {
/* 214 */     this.swfpath = swfpath;
/*     */   }
/*     */ 
/*     */   @Column(name="NOTE", nullable=true, length=65535)
/*     */   public String getNote()
/*     */   {
/* 222 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note)
/*     */   {
/* 230 */     this.note = note;
/*     */   }
/*     */ 
/*     */   @Column(name="EXTEND", nullable=true, length=32)
/*     */   public String getExtend()
/*     */   {
/* 238 */     return this.extend;
/*     */   }
/*     */ 
/*     */   public void setExtend(String extend)
/*     */   {
/* 246 */     this.extend = extend;
/*     */   }
/*     */ 
/*     */   @Column(name="BUSENTITYNAME", nullable=true, length=100)
/*     */   public String getBusentityname()
/*     */   {
/* 254 */     return this.busentityname;
/*     */   }
/*     */ 
/*     */   public void setBusentityname(String busentityname)
/*     */   {
/* 262 */     this.busentityname = busentityname;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgBlobDataEntity
 * JD-Core Version:    0.6.2
 */