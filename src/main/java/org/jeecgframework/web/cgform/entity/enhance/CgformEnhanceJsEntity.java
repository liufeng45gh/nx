/*     */ package org.jeecgframework.web.cgform.entity.enhance;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Transient;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cgform_enhance_js", schema="")
/*     */ public class CgformEnhanceJsEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String formId;
/*     */   private String cgJsType;
/*     */   private byte[] cgJs;
/*     */   private String cgJsStr;
/*     */   private String content;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  51 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  59 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="FORM_ID", nullable=true, length=32)
/*     */   public String getFormId()
/*     */   {
/*  67 */     return this.formId;
/*     */   }
/*     */ 
/*     */   public void setFormId(String formId)
/*     */   {
/*  75 */     this.formId = formId;
/*     */   }
/*     */ 
/*     */   @Column(name="CG_JS_TYPE", nullable=true, length=20)
/*     */   public String getCgJsType()
/*     */   {
/*  83 */     return this.cgJsType;
/*     */   }
/*     */ 
/*     */   public void setCgJsType(String cgJsType)
/*     */   {
/*  91 */     this.cgJsType = cgJsType;
/*     */   }
/*     */ 
/*     */   @Column(name="CG_JS", nullable=true, length=65535)
/*     */   public byte[] getCgJs()
/*     */   {
/*  99 */     return this.cgJs;
/*     */   }
/*     */ 
/*     */   public void setCgJs(byte[] cgJs)
/*     */   {
/* 107 */     this.cgJs = cgJs;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=true, length=1000)
/*     */   public String getContent()
/*     */   {
/* 115 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 123 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getCgJsStr() {
/* 128 */     if (this.cgJs != null)
/*     */       try {
/* 130 */         this.cgJsStr = new String(this.cgJs, "utf-8");
/*     */       }
/*     */       catch (Exception localException) {
/*     */       }
/* 134 */     return this.cgJsStr;
/*     */   }
/*     */ 
/*     */   public void setCgJsStr(String cgJsStr) {
/* 138 */     this.cgJsStr = cgJsStr;
/* 139 */     if (cgJsStr != null)
/*     */       try {
/* 141 */         this.cgJs = cgJsStr.getBytes("utf-8");
/*     */       } catch (UnsupportedEncodingException e) {
/* 143 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public CgformEnhanceJsEntity deepCopy()
/*     */     throws Exception
/*     */   {
/* 155 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 156 */     ObjectOutputStream oos = new ObjectOutputStream(bos);
/* 157 */     oos.writeObject(this);
/*     */ 
/* 160 */     ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
/* 161 */     ObjectInputStream ois = new ObjectInputStream(bis);
/* 162 */     return (CgformEnhanceJsEntity)ois.readObject();
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity
 * JD-Core Version:    0.6.2
 */