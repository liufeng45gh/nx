/*     */ package ssb.warmline.business.entity.wcategory;
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
/*     */ @Table(name="w_category", schema="")
/*     */ public class WCategoryEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="类别名称")
/*     */   private String categoryName;
/*     */ 
/*     */   @Excel(name="categoryCode")
/*     */   private String categoryCode;
/*     */ 
/*     */   @Excel(name="类别排序")
/*     */   private Integer categorySort;
/*     */ 
/*     */   @Excel(name="类别父id")
/*     */   private String categoryParentid;
/*     */ 
/*     */   @Excel(name="子级类目排列")
/*     */   private Integer categoryLevel;
/*     */ 
/*     */   @Excel(name="是否为父节点")
/*     */   private String isParent;
/*     */ 
/*     */   @Excel(name="是否展开节点")
/*     */   private String open;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  56 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  64 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY_NAME", nullable=true, length=50)
/*     */   public String getCategoryName()
/*     */   {
/*  72 */     return this.categoryName;
/*     */   }
/*     */ 
/*     */   public void setCategoryName(String categoryName)
/*     */   {
/*  80 */     this.categoryName = categoryName;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY_CODE", nullable=true, length=50)
/*     */   public String getCategoryCode()
/*     */   {
/*  88 */     return this.categoryCode;
/*     */   }
/*     */ 
/*     */   public void setCategoryCode(String categoryCode)
/*     */   {
/*  96 */     this.categoryCode = categoryCode;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY_SORT", nullable=true, length=50)
/*     */   public Integer getCategorySort()
/*     */   {
/* 104 */     return this.categorySort;
/*     */   }
/*     */ 
/*     */   public void setCategorySort(Integer categorySort)
/*     */   {
/* 112 */     this.categorySort = categorySort;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY_PARENTID", nullable=true, length=36)
/*     */   public String getCategoryParentid()
/*     */   {
/* 120 */     return this.categoryParentid;
/*     */   }
/*     */ 
/*     */   public void setCategoryParentid(String categoryParentid)
/*     */   {
/* 128 */     this.categoryParentid = categoryParentid;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY_LEVEL", nullable=true, length=50)
/*     */   public Integer getCategoryLevel()
/*     */   {
/* 136 */     return this.categoryLevel;
/*     */   }
/*     */ 
/*     */   public void setCategoryLevel(Integer categoryLevel)
/*     */   {
/* 144 */     this.categoryLevel = categoryLevel;
/*     */   }
/*     */ 
/*     */   @Column(name="ISPARENT", nullable=true, length=50)
/*     */   public String getIsParent()
/*     */   {
/* 152 */     return this.isParent;
/*     */   }
/*     */ 
/*     */   public void setIsParent(String isParent)
/*     */   {
/* 160 */     this.isParent = isParent;
/*     */   }
/*     */   @Column(name="OPEN", nullable=true, length=32)
/*     */   public String getOpen() {
/* 164 */     return this.open;
/*     */   }
/*     */ 
/*     */   public void setOpen(String open)
/*     */   {
/* 171 */     this.open = open;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wcategory.WCategoryEntity
 * JD-Core Version:    0.6.2
 */