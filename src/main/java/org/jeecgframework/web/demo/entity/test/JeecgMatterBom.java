/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.OrderBy;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jeecg_matter_bom")
/*     */ public class JeecgMatterBom extends IdEntity
/*     */ {
/*     */   private String code;
/*     */   private JeecgMatterBom parent;
/*  38 */   private List<JeecgMatterBom> children = new ArrayList();
/*     */   private String name;
/*     */   private String unit;
/*     */   private String weight;
/*     */   private BigDecimal price;
/*  53 */   private Integer stock = Integer.valueOf(0);
/*     */   private String address;
/*     */   private Date productionDate;
/*  62 */   private Integer quantity = Integer.valueOf(0);
/*     */ 
/*     */   @Column(length=255)
/*     */   public String getAddress()
/*     */   {
/*  76 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address)
/*     */   {
/*  90 */     this.address = address;
/*     */   }
/*     */ 
/*     */   @Column(nullable=false, length=50)
/*     */   public String getCode()
/*     */   {
/* 105 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 119 */     this.code = code;
/*     */   }
/*     */ 
/*     */   @Column(nullable=false, length=50)
/*     */   public String getName()
/*     */   {
/* 134 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 148 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   public JeecgMatterBom getParent()
/*     */   {
/* 163 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(JeecgMatterBom parent)
/*     */   {
/* 177 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="parent", fetch=FetchType.LAZY)
/*     */   @OrderBy("code asc")
/*     */   public List<JeecgMatterBom> getChildren()
/*     */   {
/* 193 */     return this.children;
/*     */   }
/*     */ 
/*     */   public void setChildren(List<JeecgMatterBom> children)
/*     */   {
/* 207 */     this.children = children;
/*     */   }
/*     */ 
/*     */   @Column(nullable=false, precision=21, scale=6)
/*     */   public BigDecimal getPrice()
/*     */   {
/* 222 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(BigDecimal price)
/*     */   {
/* 236 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public Date getProductionDate()
/*     */   {
/* 250 */     return this.productionDate;
/*     */   }
/*     */ 
/*     */   public void setProductionDate(Date productionDate)
/*     */   {
/* 264 */     this.productionDate = productionDate;
/*     */   }
/*     */ 
/*     */   @Column(nullable=false)
/*     */   public Integer getQuantity()
/*     */   {
/* 279 */     return this.quantity;
/*     */   }
/*     */ 
/*     */   public void setQuantity(Integer quantity)
/*     */   {
/* 293 */     this.quantity = quantity;
/*     */   }
/*     */ 
/*     */   @Column(nullable=false)
/*     */   public Integer getStock()
/*     */   {
/* 308 */     return this.stock;
/*     */   }
/*     */ 
/*     */   public void setStock(Integer stock)
/*     */   {
/* 322 */     this.stock = stock;
/*     */   }
/*     */ 
/*     */   @Column(length=50)
/*     */   public String getUnit()
/*     */   {
/* 337 */     return this.unit;
/*     */   }
/*     */ 
/*     */   public void setUnit(String unit)
/*     */   {
/* 351 */     this.unit = unit;
/*     */   }
/*     */ 
/*     */   @Column(length=50)
/*     */   public String getWeight()
/*     */   {
/* 366 */     return this.weight;
/*     */   }
/*     */ 
/*     */   public void setWeight(String weight)
/*     */   {
/* 380 */     this.weight = weight;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgMatterBom
 * JD-Core Version:    0.6.2
 */