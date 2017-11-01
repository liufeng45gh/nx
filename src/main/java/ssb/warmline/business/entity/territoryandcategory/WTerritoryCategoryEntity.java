/*     */ package ssb.warmline.business.entity.territoryandcategory;
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
/*     */ @Table(name="w_territory_category", schema="")
/*     */ public class WTerritoryCategoryEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="分类id")
/*     */   private String categoryId;
/*     */ 
/*     */   @Excel(name="区域id")
/*     */   private String territoryId;
/*     */ 
/*     */   @Excel(name="价钱范围")
/*     */   private Double price;
/*     */ 
/*     */   @Excel(name="分类图片")
/*     */   private String categoryImage;
/*     */ 
/*     */   @Excel(name="分类名称")
/*     */   private String categoryName;
/*     */ 
/*     */   @Excel(name="区域名称")
/*     */   private String territoryName;
/*     */ 
/*     */   @Excel(name="类别父id")
/*     */   private String categoryparentid;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
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
/*     */   @Column(name="CATEGORY_ID", nullable=false, length=36)
/*     */   public String getCategoryId()
/*     */   {
/*  84 */     return this.categoryId;
/*     */   }
/*     */ 
/*     */   public void setCategoryId(String categoryId)
/*     */   {
/*  92 */     this.categoryId = categoryId;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORY_ID", nullable=false, length=36)
/*     */   public String getTerritoryId()
/*     */   {
/* 100 */     return this.territoryId;
/*     */   }
/*     */ 
/*     */   public void setTerritoryId(String territoryId)
/*     */   {
/* 108 */     this.territoryId = territoryId;
/*     */   }
/*     */ 
/*     */   @Column(name="PRICE", nullable=false, length=50)
/*     */   public Double getPrice()
/*     */   {
/* 116 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(Double price)
/*     */   {
/* 124 */     this.price = price;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY_IMAGE", nullable=true, length=100)
/*     */   public String getCategoryImage()
/*     */   {
/* 132 */     return this.categoryImage;
/*     */   }
/*     */ 
/*     */   public void setCategoryImage(String categoryImage)
/*     */   {
/* 140 */     this.categoryImage = categoryImage;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY_NAME", nullable=true, length=50)
/*     */   public String getCategoryName()
/*     */   {
/* 149 */     return this.categoryName;
/*     */   }
/*     */ 
/*     */   public void setCategoryName(String categoryName) {
/* 153 */     this.categoryName = categoryName;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORY_NAME", nullable=true, length=50)
/*     */   public String getTerritoryName()
/*     */   {
/* 161 */     return this.territoryName;
/*     */   }
/*     */ 
/*     */   public void setTerritoryName(String territoryName) {
/* 165 */     this.territoryName = territoryName;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY_PARENTID", nullable=true, length=36)
/*     */   public String getCategoryparentid()
/*     */   {
/* 174 */     return this.categoryparentid;
/*     */   }
/*     */ 
/*     */   public void setCategoryparentid(String categoryparentid)
/*     */   {
/* 182 */     this.categoryparentid = categoryparentid;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.territoryandcategory.WTerritoryCategoryEntity
 * JD-Core Version:    0.6.2
 */