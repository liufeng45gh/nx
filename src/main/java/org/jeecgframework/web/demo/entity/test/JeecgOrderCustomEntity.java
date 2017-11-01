/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jeecg_order_custom", schema="")
/*     */ public class JeecgOrderCustomEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String goOrderCode;
/*     */   private String gocCusName;
/*     */   private String gocSex;
/*     */   private String gocIdcard;
/*     */   private String gocPassportCode;
/*     */   private String gocBussContent;
/*     */   private String gocContent;
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
/*  66 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  74 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="GO_ORDER_CODE", nullable=false, length=12)
/*     */   public String getGoOrderCode()
/*     */   {
/*  82 */     return this.goOrderCode;
/*     */   }
/*     */ 
/*     */   public void setGoOrderCode(String goOrderCode)
/*     */   {
/*  90 */     this.goOrderCode = goOrderCode;
/*     */   }
/*     */ 
/*     */   @Column(name="GOC_CUS_NAME", nullable=true, length=16)
/*     */   public String getGocCusName()
/*     */   {
/*  98 */     return this.gocCusName;
/*     */   }
/*     */ 
/*     */   public void setGocCusName(String gocCusName)
/*     */   {
/* 106 */     this.gocCusName = gocCusName;
/*     */   }
/*     */ 
/*     */   @Column(name="GOC_SEX", nullable=true)
/*     */   public String getGocSex()
/*     */   {
/* 114 */     return this.gocSex;
/*     */   }
/*     */ 
/*     */   public void setGocSex(String gocSex)
/*     */   {
/* 122 */     this.gocSex = gocSex;
/*     */   }
/*     */ 
/*     */   @Column(name="GOC_IDCARD", nullable=true, length=18)
/*     */   public String getGocIdcard()
/*     */   {
/* 130 */     return this.gocIdcard;
/*     */   }
/*     */ 
/*     */   public void setGocIdcard(String gocIdcard)
/*     */   {
/* 138 */     this.gocIdcard = gocIdcard;
/*     */   }
/*     */ 
/*     */   @Column(name="GOC_PASSPORT_CODE", nullable=true, length=10)
/*     */   public String getGocPassportCode()
/*     */   {
/* 146 */     return this.gocPassportCode;
/*     */   }
/*     */ 
/*     */   public void setGocPassportCode(String gocPassportCode)
/*     */   {
/* 154 */     this.gocPassportCode = gocPassportCode;
/*     */   }
/*     */ 
/*     */   @Column(name="GOC_BUSS_CONTENT", nullable=true, length=33)
/*     */   public String getGocBussContent()
/*     */   {
/* 162 */     return this.gocBussContent;
/*     */   }
/*     */ 
/*     */   public void setGocBussContent(String gocBussContent)
/*     */   {
/* 170 */     this.gocBussContent = gocBussContent;
/*     */   }
/*     */ 
/*     */   @Column(name="GOC_CONTENT", nullable=true, length=66)
/*     */   public String getGocContent()
/*     */   {
/* 178 */     return this.gocContent;
/*     */   }
/*     */ 
/*     */   public void setGocContent(String gocContent)
/*     */   {
/* 186 */     this.gocContent = gocContent;
/*     */   }
/*     */ 
/*     */   @Column(name="CRTUSER", nullable=true, length=12)
/*     */   public String getCrtuser()
/*     */   {
/* 194 */     return this.crtuser;
/*     */   }
/*     */ 
/*     */   public void setCrtuser(String crtuser)
/*     */   {
/* 202 */     this.crtuser = crtuser;
/*     */   }
/*     */ 
/*     */   @Column(name="CRTUSER_NAME", nullable=true, length=10)
/*     */   public String getCrtuserName()
/*     */   {
/* 210 */     return this.crtuserName;
/*     */   }
/*     */ 
/*     */   public void setCrtuserName(String crtuserName)
/*     */   {
/* 218 */     this.crtuserName = crtuserName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DT", nullable=true)
/*     */   public Date getCreateDt()
/*     */   {
/* 226 */     return this.createDt;
/*     */   }
/*     */ 
/*     */   public void setCreateDt(Date createDt)
/*     */   {
/* 234 */     this.createDt = createDt;
/*     */   }
/*     */ 
/*     */   @Column(name="MODIFIER", nullable=true, length=12)
/*     */   public String getModifier()
/*     */   {
/* 242 */     return this.modifier;
/*     */   }
/*     */ 
/*     */   public void setModifier(String modifier)
/*     */   {
/* 250 */     this.modifier = modifier;
/*     */   }
/*     */ 
/*     */   @Column(name="MODIFIER_NAME", nullable=true, length=10)
/*     */   public String getModifierName()
/*     */   {
/* 258 */     return this.modifierName;
/*     */   }
/*     */ 
/*     */   public void setModifierName(String modifierName)
/*     */   {
/* 266 */     this.modifierName = modifierName;
/*     */   }
/*     */ 
/*     */   @Column(name="MODIFY_DT", nullable=true)
/*     */   public Date getModifyDt()
/*     */   {
/* 274 */     return this.modifyDt;
/*     */   }
/*     */ 
/*     */   public void setModifyDt(Date modifyDt)
/*     */   {
/* 282 */     this.modifyDt = modifyDt;
/*     */   }
/*     */ 
/*     */   @Column(name="DELFLAG", nullable=true, precision=10, scale=0)
/*     */   public Integer getDelflag()
/*     */   {
/* 290 */     return this.delflag;
/*     */   }
/*     */ 
/*     */   public void setDelflag(Integer delflag)
/*     */   {
/* 298 */     this.delflag = delflag;
/*     */   }
/*     */ 
/*     */   @Column(name="DEL_DT", nullable=true)
/*     */   public Date getDelDt()
/*     */   {
/* 306 */     return this.delDt;
/*     */   }
/*     */ 
/*     */   public void setDelDt(Date delDt)
/*     */   {
/* 314 */     this.delDt = delDt;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgOrderCustomEntity
 * JD-Core Version:    0.6.2
 */