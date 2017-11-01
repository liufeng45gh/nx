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
/*     */ @Table(name="jeecg_order_product", schema="")
/*     */ public class JeecgOrderProductEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String goOrderCode;
/*     */   private String gopProductType;
/*     */   private String gopProductName;
/*     */   private Integer gopCount;
/*     */   private BigDecimal gopOnePrice;
/*     */   private BigDecimal gopSumPrice;
/*     */   private String gopContent;
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
/*  68 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  76 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="GO_ORDER_CODE", nullable=false, length=12)
/*     */   public String getGoOrderCode()
/*     */   {
/*  84 */     return this.goOrderCode;
/*     */   }
/*     */ 
/*     */   public void setGoOrderCode(String goOrderCode)
/*     */   {
/*  92 */     this.goOrderCode = goOrderCode;
/*     */   }
/*     */ 
/*     */   @Column(name="GOP_PRODUCT_TYPE", nullable=false, length=1)
/*     */   public String getGopProductType()
/*     */   {
/* 100 */     return this.gopProductType;
/*     */   }
/*     */ 
/*     */   public void setGopProductType(String gopProductType)
/*     */   {
/* 108 */     this.gopProductType = gopProductType;
/*     */   }
/*     */ 
/*     */   @Column(name="GOP_PRODUCT_NAME", nullable=true, length=33)
/*     */   public String getGopProductName()
/*     */   {
/* 116 */     return this.gopProductName;
/*     */   }
/*     */ 
/*     */   public void setGopProductName(String gopProductName)
/*     */   {
/* 124 */     this.gopProductName = gopProductName;
/*     */   }
/*     */ 
/*     */   @Column(name="GOP_COUNT", nullable=true, precision=10, scale=0)
/*     */   public Integer getGopCount()
/*     */   {
/* 132 */     return this.gopCount;
/*     */   }
/*     */ 
/*     */   public void setGopCount(Integer gopCount)
/*     */   {
/* 140 */     this.gopCount = gopCount;
/*     */   }
/*     */ 
/*     */   @Column(name="GOP_ONE_PRICE", nullable=true, precision=10, scale=2)
/*     */   public BigDecimal getGopOnePrice()
/*     */   {
/* 148 */     return this.gopOnePrice;
/*     */   }
/*     */ 
/*     */   public void setGopOnePrice(BigDecimal gopOnePrice)
/*     */   {
/* 156 */     this.gopOnePrice = gopOnePrice;
/*     */   }
/*     */ 
/*     */   @Column(name="GOP_SUM_PRICE", nullable=true, precision=10, scale=2)
/*     */   public BigDecimal getGopSumPrice()
/*     */   {
/* 164 */     return this.gopSumPrice;
/*     */   }
/*     */ 
/*     */   public void setGopSumPrice(BigDecimal gopSumPrice)
/*     */   {
/* 172 */     this.gopSumPrice = gopSumPrice;
/*     */   }
/*     */ 
/*     */   @Column(name="GOP_CONTENT", nullable=true, length=66)
/*     */   public String getGopContent()
/*     */   {
/* 180 */     return this.gopContent;
/*     */   }
/*     */ 
/*     */   public void setGopContent(String gopContent)
/*     */   {
/* 188 */     this.gopContent = gopContent;
/*     */   }
/*     */ 
/*     */   @Column(name="CRTUSER", nullable=true, length=12)
/*     */   public String getCrtuser()
/*     */   {
/* 196 */     return this.crtuser;
/*     */   }
/*     */ 
/*     */   public void setCrtuser(String crtuser)
/*     */   {
/* 204 */     this.crtuser = crtuser;
/*     */   }
/*     */ 
/*     */   @Column(name="CRTUSER_NAME", nullable=true, length=10)
/*     */   public String getCrtuserName()
/*     */   {
/* 212 */     return this.crtuserName;
/*     */   }
/*     */ 
/*     */   public void setCrtuserName(String crtuserName)
/*     */   {
/* 220 */     this.crtuserName = crtuserName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DT", nullable=true)
/*     */   public Date getCreateDt()
/*     */   {
/* 228 */     return this.createDt;
/*     */   }
/*     */ 
/*     */   public void setCreateDt(Date createDt)
/*     */   {
/* 236 */     this.createDt = createDt;
/*     */   }
/*     */ 
/*     */   @Column(name="MODIFIER", nullable=true, length=12)
/*     */   public String getModifier()
/*     */   {
/* 244 */     return this.modifier;
/*     */   }
/*     */ 
/*     */   public void setModifier(String modifier)
/*     */   {
/* 252 */     this.modifier = modifier;
/*     */   }
/*     */ 
/*     */   @Column(name="MODIFIER_NAME", nullable=true, length=10)
/*     */   public String getModifierName()
/*     */   {
/* 260 */     return this.modifierName;
/*     */   }
/*     */ 
/*     */   public void setModifierName(String modifierName)
/*     */   {
/* 268 */     this.modifierName = modifierName;
/*     */   }
/*     */ 
/*     */   @Column(name="MODIFY_DT", nullable=true)
/*     */   public Date getModifyDt()
/*     */   {
/* 276 */     return this.modifyDt;
/*     */   }
/*     */ 
/*     */   public void setModifyDt(Date modifyDt)
/*     */   {
/* 284 */     this.modifyDt = modifyDt;
/*     */   }
/*     */ 
/*     */   @Column(name="DELFLAG", nullable=true, precision=10, scale=0)
/*     */   public Integer getDelflag()
/*     */   {
/* 292 */     return this.delflag;
/*     */   }
/*     */ 
/*     */   public void setDelflag(Integer delflag)
/*     */   {
/* 300 */     this.delflag = delflag;
/*     */   }
/*     */ 
/*     */   @Column(name="DEL_DT", nullable=true)
/*     */   public Date getDelDt()
/*     */   {
/* 308 */     return this.delDt;
/*     */   }
/*     */ 
/*     */   public void setDelDt(Date delDt)
/*     */   {
/* 316 */     this.delDt = delDt;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgOrderProductEntity
 * JD-Core Version:    0.6.2
 */