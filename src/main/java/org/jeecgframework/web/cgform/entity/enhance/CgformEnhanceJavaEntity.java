/*     */ package org.jeecgframework.web.cgform.entity.enhance;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cgform_enhance_java", schema="")
/*     */ public class CgformEnhanceJavaEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String buttonCode;
/*     */ 
/*     */   @Excel(name="类型")
/*     */   private String cgJavaType;
/*     */ 
/*     */   @Excel(name="数值")
/*     */   private String cgJavaValue;
/*     */ 
/*     */   @Excel(name="表单ID")
/*     */   private String formId;
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
/*     */   @Column(name="CG_JAVA_TYPE", nullable=true, length=32)
/*     */   public String getCgJavaType()
/*     */   {
/*  74 */     return this.cgJavaType;
/*     */   }
/*     */ 
/*     */   public void setCgJavaType(String cgJavaType)
/*     */   {
/*  82 */     this.cgJavaType = cgJavaType;
/*     */   }
/*     */ 
/*     */   @Column(name="CG_JAVA_VALUE", nullable=false, length=200)
/*     */   public String getCgJavaValue()
/*     */   {
/*  90 */     return this.cgJavaValue;
/*     */   }
/*     */ 
/*     */   public void setCgJavaValue(String cgJavaValue)
/*     */   {
/*  98 */     this.cgJavaValue = cgJavaValue;
/*     */   }
/*     */ 
/*     */   @Column(name="FORM_ID", nullable=false, length=32)
/*     */   public String getFormId()
/*     */   {
/* 106 */     return this.formId;
/*     */   }
/*     */ 
/*     */   public void setFormId(String formId)
/*     */   {
/* 114 */     this.formId = formId;
/*     */   }
/*     */ 
/*     */   @Column(name="BUTTON_CODE", nullable=true, length=50)
/*     */   public String getButtonCode()
/*     */   {
/* 123 */     return this.buttonCode;
/*     */   }
/*     */ 
/*     */   public void setButtonCode(String buttonCode) {
/* 127 */     this.buttonCode = buttonCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 132 */     return "CgformEnhanceJavaEntity [id=" + this.id + ", buttonCode=" + 
/* 133 */       this.buttonCode + ", cgJavaType=" + this.cgJavaType + ", cgJavaValue=" + 
/* 134 */       this.cgJavaValue + ", formId=" + this.formId + "]";
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity
 * JD-Core Version:    0.6.2
 */