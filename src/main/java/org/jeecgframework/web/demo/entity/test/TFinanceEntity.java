/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_finance", schema="")
/*     */ public class TFinanceEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String category;
/*     */   private Integer happenyear;
/*     */   private Date paytime;
/*     */   private String collectorg;
/*     */   private String approfiletype;
/*     */   private String zbwno;
/*     */   private Float moneytotal;
/*     */   private String expenseaccount;
/*     */   private String moneyuse;
/*     */   private String moneyarea;
/*     */   private String moneysource;
/*     */   private String buyyear;
/*     */   private String buyprojectno;
/*     */   private String buyprojectorg;
/*     */   private Float buymoney;
/*     */   private String buyuse;
/*     */   private List<TFinanceFilesEntity> financeFiles;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  73 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  81 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY", nullable=true, length=255)
/*     */   public String getCategory()
/*     */   {
/*  89 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(String category)
/*     */   {
/*  97 */     this.category = category;
/*     */   }
/*     */ 
/*     */   @Column(name="HAPPENYEAR", nullable=true, precision=10, scale=0)
/*     */   public Integer getHappenyear()
/*     */   {
/* 105 */     return this.happenyear;
/*     */   }
/*     */ 
/*     */   public void setHappenyear(Integer happenyear)
/*     */   {
/* 113 */     this.happenyear = happenyear;
/*     */   }
/*     */ 
/*     */   @Column(name="PAYTIME", nullable=true)
/*     */   public Date getPaytime()
/*     */   {
/* 121 */     return this.paytime;
/*     */   }
/*     */ 
/*     */   public void setPaytime(Date paytime)
/*     */   {
/* 129 */     this.paytime = paytime;
/*     */   }
/*     */ 
/*     */   @Column(name="COLLECTORG", nullable=true, length=255)
/*     */   public String getCollectorg()
/*     */   {
/* 137 */     return this.collectorg;
/*     */   }
/*     */ 
/*     */   public void setCollectorg(String collectorg)
/*     */   {
/* 145 */     this.collectorg = collectorg;
/*     */   }
/*     */ 
/*     */   @Column(name="APPROFILETYPE", nullable=true, length=255)
/*     */   public String getApprofiletype()
/*     */   {
/* 153 */     return this.approfiletype;
/*     */   }
/*     */ 
/*     */   public void setApprofiletype(String approfiletype)
/*     */   {
/* 161 */     this.approfiletype = approfiletype;
/*     */   }
/*     */ 
/*     */   @Column(name="ZBWNO", nullable=true, length=255)
/*     */   public String getZbwno()
/*     */   {
/* 169 */     return this.zbwno;
/*     */   }
/*     */ 
/*     */   public void setZbwno(String zbwno)
/*     */   {
/* 177 */     this.zbwno = zbwno;
/*     */   }
/*     */ 
/*     */   @Column(name="MONEYTOTAL", nullable=true, precision=12)
/*     */   public Float getMoneytotal()
/*     */   {
/* 185 */     return this.moneytotal;
/*     */   }
/*     */ 
/*     */   public void setMoneytotal(Float moneytotal)
/*     */   {
/* 193 */     this.moneytotal = moneytotal;
/*     */   }
/*     */ 
/*     */   @Column(name="EXPENSEACCOUNT", nullable=true, length=255)
/*     */   public String getExpenseaccount()
/*     */   {
/* 201 */     return this.expenseaccount;
/*     */   }
/*     */ 
/*     */   public void setExpenseaccount(String expenseaccount)
/*     */   {
/* 209 */     this.expenseaccount = expenseaccount;
/*     */   }
/*     */ 
/*     */   @Column(name="MONEYUSE", nullable=true, length=255)
/*     */   public String getMoneyuse()
/*     */   {
/* 217 */     return this.moneyuse;
/*     */   }
/*     */ 
/*     */   public void setMoneyuse(String moneyuse)
/*     */   {
/* 225 */     this.moneyuse = moneyuse;
/*     */   }
/*     */ 
/*     */   @Column(name="MONEYAREA", nullable=true, length=255)
/*     */   public String getMoneyarea()
/*     */   {
/* 233 */     return this.moneyarea;
/*     */   }
/*     */ 
/*     */   public void setMoneyarea(String moneyarea)
/*     */   {
/* 241 */     this.moneyarea = moneyarea;
/*     */   }
/*     */ 
/*     */   @Column(name="MONEYSOURCE", nullable=true, length=255)
/*     */   public String getMoneysource()
/*     */   {
/* 249 */     return this.moneysource;
/*     */   }
/*     */ 
/*     */   public void setMoneysource(String moneysource)
/*     */   {
/* 257 */     this.moneysource = moneysource;
/*     */   }
/*     */ 
/*     */   @Column(name="BUYYEAR", nullable=true, length=255)
/*     */   public String getBuyyear()
/*     */   {
/* 265 */     return this.buyyear;
/*     */   }
/*     */ 
/*     */   public void setBuyyear(String buyyear)
/*     */   {
/* 273 */     this.buyyear = buyyear;
/*     */   }
/*     */ 
/*     */   @Column(name="BUYPROJECTNO", nullable=true, length=255)
/*     */   public String getBuyprojectno()
/*     */   {
/* 281 */     return this.buyprojectno;
/*     */   }
/*     */ 
/*     */   public void setBuyprojectno(String buyprojectno)
/*     */   {
/* 289 */     this.buyprojectno = buyprojectno;
/*     */   }
/*     */ 
/*     */   @Column(name="BUYPROJECTORG", nullable=true, length=255)
/*     */   public String getBuyprojectorg()
/*     */   {
/* 297 */     return this.buyprojectorg;
/*     */   }
/*     */ 
/*     */   public void setBuyprojectorg(String buyprojectorg)
/*     */   {
/* 305 */     this.buyprojectorg = buyprojectorg;
/*     */   }
/*     */ 
/*     */   @Column(name="BUYMONEY", nullable=true, precision=12)
/*     */   public Float getBuymoney()
/*     */   {
/* 313 */     return this.buymoney;
/*     */   }
/*     */ 
/*     */   public void setBuymoney(Float buymoney)
/*     */   {
/* 321 */     this.buymoney = buymoney;
/*     */   }
/*     */ 
/*     */   @Column(name="BUYUSE", nullable=true, length=255)
/*     */   public String getBuyuse()
/*     */   {
/* 329 */     return this.buyuse;
/*     */   }
/*     */ 
/*     */   public void setBuyuse(String buyuse)
/*     */   {
/* 337 */     this.buyuse = buyuse;
/*     */   }
/*     */ 
/*     */   @OneToMany(mappedBy="finance", cascade={javax.persistence.CascadeType.REMOVE})
/*     */   public List<TFinanceFilesEntity> getFinanceFiles() {
/* 342 */     return this.financeFiles;
/*     */   }
/*     */ 
/*     */   public void setFinanceFiles(List<TFinanceFilesEntity> financeFiles) {
/* 346 */     this.financeFiles = financeFiles;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.TFinanceEntity
 * JD-Core Version:    0.6.2
 */