/*     */ package org.jeecgframework.web.cgform.entity.button;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cgform_button", schema="")
/*     */ public class CgformButtonEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String formId;
/*     */   private String buttonCode;
/*     */   private String buttonName;
/*     */   private String buttonStyle;
/*     */   private String optType;
/*     */   private String exp;
/*     */   private String buttonStatus;
/*     */   private Integer orderNum;
/*     */   private String buttonIcon;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  54 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  62 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="FORM_ID", nullable=true, length=32)
/*     */   public String getFormId()
/*     */   {
/*  70 */     return this.formId;
/*     */   }
/*     */ 
/*     */   public void setFormId(String formId)
/*     */   {
/*  78 */     this.formId = formId;
/*     */   }
/*     */ 
/*     */   @Column(name="BUTTON_CODE", nullable=true, length=50)
/*     */   public String getButtonCode()
/*     */   {
/*  86 */     return this.buttonCode;
/*     */   }
/*     */ 
/*     */   public void setButtonCode(String buttonCode)
/*     */   {
/*  94 */     this.buttonCode = buttonCode;
/*     */   }
/*     */ 
/*     */   @Column(name="BUTTON_NAME", nullable=true, length=50)
/*     */   public String getButtonName()
/*     */   {
/* 102 */     return this.buttonName;
/*     */   }
/*     */ 
/*     */   public void setButtonName(String buttonName)
/*     */   {
/* 110 */     this.buttonName = buttonName;
/*     */   }
/*     */ 
/*     */   @Column(name="BUTTON_STYLE", nullable=true, length=20)
/*     */   public String getButtonStyle()
/*     */   {
/* 118 */     return this.buttonStyle;
/*     */   }
/*     */ 
/*     */   public void setButtonStyle(String buttonStyle)
/*     */   {
/* 126 */     this.buttonStyle = buttonStyle;
/*     */   }
/*     */ 
/*     */   @Column(name="OPT_TYPE", nullable=true, length=20)
/*     */   public String getOptType()
/*     */   {
/* 134 */     return this.optType;
/*     */   }
/*     */ 
/*     */   public void setOptType(String optType)
/*     */   {
/* 142 */     this.optType = optType;
/*     */   }
/*     */ 
/*     */   @Column(name="EXP", nullable=true, length=255)
/*     */   public String getExp()
/*     */   {
/* 150 */     return this.exp;
/*     */   }
/*     */ 
/*     */   public void setExp(String exp)
/*     */   {
/* 158 */     this.exp = exp;
/*     */   }
/*     */ 
/*     */   @Column(name="BUTTON_STATUS", nullable=true, length=2)
/*     */   public String getButtonStatus()
/*     */   {
/* 166 */     return this.buttonStatus;
/*     */   }
/*     */ 
/*     */   public void setButtonStatus(String buttonStatus)
/*     */   {
/* 174 */     this.buttonStatus = buttonStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="order_num", nullable=true, length=4)
/*     */   public Integer getOrderNum()
/*     */   {
/* 183 */     return this.orderNum;
/*     */   }
/*     */ 
/*     */   public void setOrderNum(Integer orderNum) {
/* 187 */     this.orderNum = orderNum;
/*     */   }
/*     */ 
/*     */   @Column(name="button_icon", nullable=true, length=20)
/*     */   public String getButtonIcon() {
/* 192 */     return this.buttonIcon;
/*     */   }
/*     */ 
/*     */   public void setButtonIcon(String buttonIcon) {
/* 196 */     this.buttonIcon = buttonIcon;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.button.CgformButtonEntity
 * JD-Core Version:    0.6.2
 */