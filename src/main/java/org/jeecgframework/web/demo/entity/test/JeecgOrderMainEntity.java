/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jeecg_order_main", schema="")
/*     */ public class JeecgOrderMainEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String goOrderCode;
/*     */   private String goderType;
/*     */   private String usertype;
/*     */   private String goContactName;
/*     */   private String goTelphone;
/*     */   private Integer goOrderCount;
/*     */   private BigDecimal goAllPrice;
/*     */   private BigDecimal goReturnPrice;
/*     */   private String goContent;
/*     */   private String crtuser;
/*     */   private String crtuserName;
/*     */   private Date createDt;
/*     */   private String modifier;
/*     */   private String modifierName;
/*     */   private Date modifyDt;
/*     */   private Integer delflag;
/*     */   private Date delDt;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
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
/*     */   @Column(name="GO_ORDER_CODE", nullable=false, length=12)
/*     */   public String getGoOrderCode()
/*     */   {
/*  88 */     return this.goOrderCode;
/*     */   }
/*     */ 
/*     */   public void setGoOrderCode(String goOrderCode)
/*     */   {
/*  96 */     this.goOrderCode = goOrderCode;
/*     */   }
/*     */ 
/*     */   @Column(name="GODER_TYPE", nullable=true)
/*     */   public String getGoderType()
/*     */   {
/* 104 */     return this.goderType;
/*     */   }
/*     */ 
/*     */   public void setGoderType(String goderType)
/*     */   {
/* 112 */     this.goderType = goderType;
/*     */   }
/*     */ 
/*     */   @Column(name="USERTYPE", nullable=true)
/*     */   public String getUsertype()
/*     */   {
/* 120 */     return this.usertype;
/*     */   }
/*     */ 
/*     */   public void setUsertype(String usertype)
/*     */   {
/* 128 */     this.usertype = usertype;
/*     */   }
/*     */ 
/*     */   @Column(name="GO_CONTACT_NAME", nullable=true, length=16)
/*     */   public String getGoContactName()
/*     */   {
/* 136 */     return this.goContactName;
/*     */   }
/*     */ 
/*     */   public void setGoContactName(String goContactName)
/*     */   {
/* 144 */     this.goContactName = goContactName;
/*     */   }
/*     */ 
/*     */   @Column(name="GO_TELPHONE", nullable=true, length=11)
/*     */   public String getGoTelphone()
/*     */   {
/* 152 */     return this.goTelphone;
/*     */   }
/*     */ 
/*     */   public void setGoTelphone(String goTelphone)
/*     */   {
/* 160 */     this.goTelphone = goTelphone;
/*     */   }
/*     */ 
/*     */   @Column(name="GO_ORDER_COUNT", nullable=true, precision=10, scale=0)
/*     */   public Integer getGoOrderCount()
/*     */   {
/* 168 */     return this.goOrderCount;
/*     */   }
/*     */ 
/*     */   public void setGoOrderCount(Integer goOrderCount)
/*     */   {
/* 176 */     this.goOrderCount = goOrderCount;
/*     */   }
/*     */ 
/*     */   @Column(name="GO_ALL_PRICE", nullable=true, precision=10, scale=2)
/*     */   public BigDecimal getGoAllPrice()
/*     */   {
/* 184 */     return this.goAllPrice;
/*     */   }
/*     */ 
/*     */   public void setGoAllPrice(BigDecimal goAllPrice)
/*     */   {
/* 192 */     this.goAllPrice = goAllPrice;
/*     */   }
/*     */ 
/*     */   @Column(name="GO_RETURN_PRICE", nullable=true, precision=10, scale=2)
/*     */   public BigDecimal getGoReturnPrice()
/*     */   {
/* 200 */     return this.goReturnPrice;
/*     */   }
/*     */ 
/*     */   public void setGoReturnPrice(BigDecimal goReturnPrice)
/*     */   {
/* 208 */     this.goReturnPrice = goReturnPrice;
/*     */   }
/*     */ 
/*     */   @Column(name="GO_CONTENT", nullable=true, length=66)
/*     */   public String getGoContent()
/*     */   {
/* 216 */     return this.goContent;
/*     */   }
/*     */ 
/*     */   public void setGoContent(String goContent)
/*     */   {
/* 224 */     this.goContent = goContent;
/*     */   }
/*     */ 
/*     */   @Column(name="CRTUSER", nullable=true, length=12)
/*     */   public String getCrtuser()
/*     */   {
/* 232 */     return this.crtuser;
/*     */   }
/*     */ 
/*     */   public void setCrtuser(String crtuser)
/*     */   {
/* 240 */     this.crtuser = crtuser;
/*     */   }
/*     */ 
/*     */   @Column(name="CRTUSER_NAME", nullable=true, length=10)
/*     */   public String getCrtuserName()
/*     */   {
/* 248 */     return this.crtuserName;
/*     */   }
/*     */ 
/*     */   public void setCrtuserName(String crtuserName)
/*     */   {
/* 256 */     this.crtuserName = crtuserName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DT", nullable=true)
/*     */   public Date getCreateDt()
/*     */   {
/* 264 */     return this.createDt;
/*     */   }
/*     */ 
/*     */   public void setCreateDt(Date createDt)
/*     */   {
/* 272 */     this.createDt = createDt;
/*     */   }
/*     */ 
/*     */   @Column(name="MODIFIER", nullable=true, length=12)
/*     */   public String getModifier()
/*     */   {
/* 280 */     return this.modifier;
/*     */   }
/*     */ 
/*     */   public void setModifier(String modifier)
/*     */   {
/* 288 */     this.modifier = modifier;
/*     */   }
/*     */ 
/*     */   @Column(name="MODIFIER_NAME", nullable=true, length=10)
/*     */   public String getModifierName()
/*     */   {
/* 296 */     return this.modifierName;
/*     */   }
/*     */ 
/*     */   public void setModifierName(String modifierName)
/*     */   {
/* 304 */     this.modifierName = modifierName;
/*     */   }
/*     */ 
/*     */   @Column(name="MODIFY_DT", nullable=true)
/*     */   public Date getModifyDt()
/*     */   {
/* 312 */     return this.modifyDt;
/*     */   }
/*     */ 
/*     */   public void setModifyDt(Date modifyDt)
/*     */   {
/* 320 */     this.modifyDt = modifyDt;
/*     */   }
/*     */ 
/*     */   @Column(name="DELFLAG", nullable=true, precision=10, scale=0)
/*     */   public Integer getDelflag()
/*     */   {
/* 328 */     return this.delflag;
/*     */   }
/*     */ 
/*     */   public void setDelflag(Integer delflag)
/*     */   {
/* 336 */     this.delflag = delflag;
/*     */   }
/*     */ 
/*     */   @Column(name="DEL_DT", nullable=true)
/*     */   public Date getDelDt()
/*     */   {
/* 344 */     return this.delDt;
/*     */   }
/*     */ 
/*     */   public void setDelDt(Date delDt)
/*     */   {
/* 352 */     this.delDt = delDt;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgOrderMainEntity
 * JD-Core Version:    0.6.2
 */