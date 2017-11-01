/*     */ package org.jeecgframework.web.cgform.entity.button;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Transient;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cgform_button_sql", schema="")
/*     */ public class CgformButtonSqlEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String formId;
/*     */   private String buttonCode;
/*     */   private String cgbSqlName;
/*     */   private byte[] cgbSql;
/*     */   private String cgbSqlStr;
/*     */   private String content;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  49 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  57 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="FORM_ID", nullable=true, length=32)
/*     */   public String getFormId()
/*     */   {
/*  65 */     return this.formId;
/*     */   }
/*     */ 
/*     */   public void setFormId(String formId)
/*     */   {
/*  73 */     this.formId = formId;
/*     */   }
/*     */ 
/*     */   @Column(name="BUTTON_CODE", nullable=true, length=50)
/*     */   public String getButtonCode()
/*     */   {
/*  81 */     return this.buttonCode;
/*     */   }
/*     */ 
/*     */   public void setButtonCode(String buttonCode)
/*     */   {
/*  89 */     this.buttonCode = buttonCode;
/*     */   }
/*     */ 
/*     */   @Column(name="CGB_SQL_NAME", nullable=true, length=50)
/*     */   public String getCgbSqlName()
/*     */   {
/*  97 */     return this.cgbSqlName;
/*     */   }
/*     */ 
/*     */   public void setCgbSqlName(String cgbSqlName)
/*     */   {
/* 105 */     this.cgbSqlName = cgbSqlName;
/*     */   }
/*     */ 
/*     */   @Column(name="CGB_SQL", nullable=true)
/*     */   public byte[] getCgbSql()
/*     */   {
/* 113 */     return this.cgbSql;
/*     */   }
/*     */ 
/*     */   public void setCgbSql(byte[] cgbSql)
/*     */   {
/* 121 */     this.cgbSql = cgbSql;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getCgbSqlStr() {
/* 126 */     if (this.cgbSql != null) {
/* 127 */       this.cgbSqlStr = new String(this.cgbSql);
/*     */     }
/* 129 */     return this.cgbSqlStr;
/*     */   }
/*     */ 
/*     */   public void setCgbSqlStr(String cgbSqlStr) {
/* 133 */     this.cgbSqlStr = cgbSqlStr;
/* 134 */     if (cgbSqlStr != null)
/* 135 */       this.cgbSql = cgbSqlStr.getBytes();
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=true, length=1000)
/*     */   public String getContent()
/*     */   {
/* 145 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 153 */     this.content = content;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity
 * JD-Core Version:    0.6.2
 */