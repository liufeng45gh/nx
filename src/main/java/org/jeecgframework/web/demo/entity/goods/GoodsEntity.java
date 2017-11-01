/*     */ package org.jeecgframework.web.demo.entity.goods;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jfom_goods", schema="")
/*     */ public class GoodsEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private Date updateDate;
/*     */   private String sysOrgCode;
/*     */   private String sysCompanyCode;
/*     */   private String bpmStatus;
/*     */ 
/*     */   @Excel(name="商品名称")
/*     */   private String name;
/*     */ 
/*     */   @Excel(name="商品代码")
/*     */   private String code;
/*     */ 
/*     */   @Excel(name="商品全称")
/*     */   private String fullName;
/*     */ 
/*     */   @Excel(name="外部编码")
/*     */   private String outsideCode;
/*     */ 
/*     */   @Excel(name="厂家货号")
/*     */   private String manufacturersNo;
/*     */ 
/*     */   @Excel(name="供应商")
/*     */   private String supplier;
/*     */ 
/*     */   @Excel(name="单位")
/*     */   private String productUnit;
/*     */ 
/*     */   @Excel(name="货主")
/*     */   private String productOwner;
/*     */ 
/*     */   @Excel(name="品牌")
/*     */   private String brand;
/*     */ 
/*     */   @Excel(name="年度")
/*     */   private String annual;
/*     */ 
/*     */   @Excel(name="季节")
/*     */   private String season;
/*     */ 
/*     */   @Excel(name="商品分类")
/*     */   private String productType;
/*     */ 
/*     */   @Excel(name="系列名称")
/*     */   private String seriesName;
/*     */ 
/*     */   @Excel(name="长度")
/*     */   private Double sizeLength;
/*     */ 
/*     */   @Excel(name="宽度")
/*     */   private Double sizeWidth;
/*     */ 
/*     */   @Excel(name="高度")
/*     */   private Double sizeHeight;
/*     */ 
/*     */   @Excel(name="体积")
/*     */   private Double sizeVolume;
/*     */ 
/*     */   @Excel(name="上市时间", format="yyyy-MM-dd")
/*     */   private Date timeToMarket;
/*     */ 
/*     */   @Excel(name="成本价")
/*     */   private Double priceCost;
/*     */ 
/*     */   @Excel(name="吊牌价")
/*     */   private Double priceDrop;
/*     */ 
/*     */   @Excel(name="标准售价")
/*     */   private Double priceStandardSell;
/*     */ 
/*     */   @Excel(name="标准进价")
/*     */   private Double priceStandardBid;
/*     */ 
/*     */   @Excel(name="批发价")
/*     */   private Double priceTrade;
/*     */ 
/*     */   @Excel(name="代理价")
/*     */   private Double priceProxy;
/*     */ 
/*     */   @Excel(name="平台价")
/*     */   private Double pricePlatform;
/*     */ 
/*     */   @Excel(name="赠品")
/*     */   private String gift;
/*     */ 
/*     */   @Excel(name="虚拟商品")
/*     */   private String productVirtual;
/*     */ 
/*     */   @Excel(name="费用商品")
/*     */   private String productCost;
/*     */ 
/*     */   @Excel(name="打包点数")
/*     */   private String pointPack;
/*     */ 
/*     */   @Excel(name="销售点数")
/*     */   private String pointSell;
/*     */ 
/*     */   @Excel(name="唯一码商品")
/*     */   private String productUniquenessCode;
/*     */ 
/*     */   @Excel(name="批次管理")
/*     */   private String batchManage;
/*     */ 
/*     */   @Excel(name="单码商品")
/*     */   private String productSingleCode;
/*     */ 
/*     */   @Excel(name="保质期")
/*     */   private String expirationDate;
/*     */ 
/*     */   @Excel(name="供货周期")
/*     */   private String supplyOfMaterialRound;
/*     */ 
/*     */   @Excel(name="安全库存")
/*     */   private String safetyInventory;
/*     */ 
/*     */   @Excel(name="国际码")
/*     */   private String internationalCode;
/*     */ 
/*     */   @Excel(name="备注")
/*     */   private String remark;
/*     */ 
/*     */   @Excel(name="商品状态")
/*     */   private String productState;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/* 171 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 179 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/* 187 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 195 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/* 203 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 211 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 219 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 227 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 235 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 243 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 251 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 259 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 267 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 275 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_ORG_CODE", nullable=true, length=50)
/*     */   public String getSysOrgCode()
/*     */   {
/* 283 */     return this.sysOrgCode;
/*     */   }
/*     */ 
/*     */   public void setSysOrgCode(String sysOrgCode)
/*     */   {
/* 291 */     this.sysOrgCode = sysOrgCode;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_COMPANY_CODE", nullable=true, length=50)
/*     */   public String getSysCompanyCode()
/*     */   {
/* 299 */     return this.sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public void setSysCompanyCode(String sysCompanyCode)
/*     */   {
/* 307 */     this.sysCompanyCode = sysCompanyCode;
/*     */   }
/*     */ 
/*     */   @Column(name="BPM_STATUS", nullable=true, length=32)
/*     */   public String getBpmStatus()
/*     */   {
/* 315 */     return this.bpmStatus;
/*     */   }
/*     */ 
/*     */   public void setBpmStatus(String bpmStatus)
/*     */   {
/* 323 */     this.bpmStatus = bpmStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=true, length=32)
/*     */   public String getName()
/*     */   {
/* 331 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 339 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="CODE", nullable=true, length=32)
/*     */   public String getCode()
/*     */   {
/* 347 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 355 */     this.code = code;
/*     */   }
/*     */ 
/*     */   @Column(name="FULL_NAME", nullable=true, length=32)
/*     */   public String getFullName()
/*     */   {
/* 363 */     return this.fullName;
/*     */   }
/*     */ 
/*     */   public void setFullName(String fullName)
/*     */   {
/* 371 */     this.fullName = fullName;
/*     */   }
/*     */ 
/*     */   @Column(name="OUTSIDE_CODE", nullable=true, length=32)
/*     */   public String getOutsideCode()
/*     */   {
/* 379 */     return this.outsideCode;
/*     */   }
/*     */ 
/*     */   public void setOutsideCode(String outsideCode)
/*     */   {
/* 387 */     this.outsideCode = outsideCode;
/*     */   }
/*     */ 
/*     */   @Column(name="MANUFACTURERS_NO", nullable=true, length=32)
/*     */   public String getManufacturersNo()
/*     */   {
/* 395 */     return this.manufacturersNo;
/*     */   }
/*     */ 
/*     */   public void setManufacturersNo(String manufacturersNo)
/*     */   {
/* 403 */     this.manufacturersNo = manufacturersNo;
/*     */   }
/*     */ 
/*     */   @Column(name="SUPPLIER", nullable=true, length=32)
/*     */   public String getSupplier()
/*     */   {
/* 411 */     return this.supplier;
/*     */   }
/*     */ 
/*     */   public void setSupplier(String supplier)
/*     */   {
/* 419 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */   @Column(name="PRODUCT_UNIT", nullable=true, length=32)
/*     */   public String getProductUnit()
/*     */   {
/* 427 */     return this.productUnit;
/*     */   }
/*     */ 
/*     */   public void setProductUnit(String productUnit)
/*     */   {
/* 435 */     this.productUnit = productUnit;
/*     */   }
/*     */ 
/*     */   @Column(name="PRODUCT_OWNER", nullable=true, length=32)
/*     */   public String getProductOwner()
/*     */   {
/* 443 */     return this.productOwner;
/*     */   }
/*     */ 
/*     */   public void setProductOwner(String productOwner)
/*     */   {
/* 451 */     this.productOwner = productOwner;
/*     */   }
/*     */ 
/*     */   @Column(name="BRAND", nullable=true, length=32)
/*     */   public String getBrand()
/*     */   {
/* 459 */     return this.brand;
/*     */   }
/*     */ 
/*     */   public void setBrand(String brand)
/*     */   {
/* 467 */     this.brand = brand;
/*     */   }
/*     */ 
/*     */   @Column(name="ANNUAL", nullable=true, length=32)
/*     */   public String getAnnual()
/*     */   {
/* 475 */     return this.annual;
/*     */   }
/*     */ 
/*     */   public void setAnnual(String annual)
/*     */   {
/* 483 */     this.annual = annual;
/*     */   }
/*     */ 
/*     */   @Column(name="SEASON", nullable=true, length=32)
/*     */   public String getSeason()
/*     */   {
/* 491 */     return this.season;
/*     */   }
/*     */ 
/*     */   public void setSeason(String season)
/*     */   {
/* 499 */     this.season = season;
/*     */   }
/*     */ 
/*     */   @Column(name="PRODUCT_TYPE", nullable=true, length=32)
/*     */   public String getProductType()
/*     */   {
/* 507 */     return this.productType;
/*     */   }
/*     */ 
/*     */   public void setProductType(String productType)
/*     */   {
/* 515 */     this.productType = productType;
/*     */   }
/*     */ 
/*     */   @Column(name="SERIES_NAME", nullable=true, length=32)
/*     */   public String getSeriesName()
/*     */   {
/* 523 */     return this.seriesName;
/*     */   }
/*     */ 
/*     */   public void setSeriesName(String seriesName)
/*     */   {
/* 531 */     this.seriesName = seriesName;
/*     */   }
/*     */ 
/*     */   @Column(name="SIZE_LENGTH", nullable=true, length=32)
/*     */   public Double getSizeLength()
/*     */   {
/* 539 */     return this.sizeLength;
/*     */   }
/*     */ 
/*     */   public void setSizeLength(Double sizeLength)
/*     */   {
/* 547 */     this.sizeLength = sizeLength;
/*     */   }
/*     */ 
/*     */   @Column(name="SIZE_WIDTH", nullable=true, length=32)
/*     */   public Double getSizeWidth()
/*     */   {
/* 555 */     return this.sizeWidth;
/*     */   }
/*     */ 
/*     */   public void setSizeWidth(Double sizeWidth)
/*     */   {
/* 563 */     this.sizeWidth = sizeWidth;
/*     */   }
/*     */ 
/*     */   @Column(name="SIZE_HEIGHT", nullable=true, length=32)
/*     */   public Double getSizeHeight()
/*     */   {
/* 571 */     return this.sizeHeight;
/*     */   }
/*     */ 
/*     */   public void setSizeHeight(Double sizeHeight)
/*     */   {
/* 579 */     this.sizeHeight = sizeHeight;
/*     */   }
/*     */ 
/*     */   @Column(name="SIZE_VOLUME", nullable=true, length=32)
/*     */   public Double getSizeVolume()
/*     */   {
/* 587 */     return this.sizeVolume;
/*     */   }
/*     */ 
/*     */   public void setSizeVolume(Double sizeVolume)
/*     */   {
/* 595 */     this.sizeVolume = sizeVolume;
/*     */   }
/*     */ 
/*     */   @Column(name="TIME_TO_MARKET", nullable=true, length=32)
/*     */   public Date getTimeToMarket()
/*     */   {
/* 603 */     return this.timeToMarket;
/*     */   }
/*     */ 
/*     */   public void setTimeToMarket(Date timeToMarket)
/*     */   {
/* 611 */     this.timeToMarket = timeToMarket;
/*     */   }
/*     */ 
/*     */   @Column(name="PRICE_COST", nullable=true, length=32)
/*     */   public Double getPriceCost()
/*     */   {
/* 619 */     return this.priceCost;
/*     */   }
/*     */ 
/*     */   public void setPriceCost(Double priceCost)
/*     */   {
/* 627 */     this.priceCost = priceCost;
/*     */   }
/*     */ 
/*     */   @Column(name="PRICE_DROP", nullable=true, length=32)
/*     */   public Double getPriceDrop()
/*     */   {
/* 635 */     return this.priceDrop;
/*     */   }
/*     */ 
/*     */   public void setPriceDrop(Double priceDrop)
/*     */   {
/* 643 */     this.priceDrop = priceDrop;
/*     */   }
/*     */ 
/*     */   @Column(name="PRICE_STANDARD_SELL", nullable=true, length=32)
/*     */   public Double getPriceStandardSell()
/*     */   {
/* 651 */     return this.priceStandardSell;
/*     */   }
/*     */ 
/*     */   public void setPriceStandardSell(Double priceStandardSell)
/*     */   {
/* 659 */     this.priceStandardSell = priceStandardSell;
/*     */   }
/*     */ 
/*     */   @Column(name="PRICE_STANDARD_BID", nullable=true, length=32)
/*     */   public Double getPriceStandardBid()
/*     */   {
/* 667 */     return this.priceStandardBid;
/*     */   }
/*     */ 
/*     */   public void setPriceStandardBid(Double priceStandardBid)
/*     */   {
/* 675 */     this.priceStandardBid = priceStandardBid;
/*     */   }
/*     */ 
/*     */   @Column(name="PRICE_TRADE", nullable=true, length=32)
/*     */   public Double getPriceTrade()
/*     */   {
/* 683 */     return this.priceTrade;
/*     */   }
/*     */ 
/*     */   public void setPriceTrade(Double priceTrade)
/*     */   {
/* 691 */     this.priceTrade = priceTrade;
/*     */   }
/*     */ 
/*     */   @Column(name="PRICE_PROXY", nullable=true, length=32)
/*     */   public Double getPriceProxy()
/*     */   {
/* 699 */     return this.priceProxy;
/*     */   }
/*     */ 
/*     */   public void setPriceProxy(Double priceProxy)
/*     */   {
/* 707 */     this.priceProxy = priceProxy;
/*     */   }
/*     */ 
/*     */   @Column(name="PRICE_PLATFORM", nullable=true, length=32)
/*     */   public Double getPricePlatform()
/*     */   {
/* 715 */     return this.pricePlatform;
/*     */   }
/*     */ 
/*     */   public void setPricePlatform(Double pricePlatform)
/*     */   {
/* 723 */     this.pricePlatform = pricePlatform;
/*     */   }
/*     */ 
/*     */   @Column(name="GIFT", nullable=true, length=32)
/*     */   public String getGift()
/*     */   {
/* 731 */     return this.gift;
/*     */   }
/*     */ 
/*     */   public void setGift(String gift)
/*     */   {
/* 739 */     this.gift = gift;
/*     */   }
/*     */ 
/*     */   @Column(name="PRODUCT_VIRTUAL", nullable=true, length=32)
/*     */   public String getProductVirtual()
/*     */   {
/* 747 */     return this.productVirtual;
/*     */   }
/*     */ 
/*     */   public void setProductVirtual(String productVirtual)
/*     */   {
/* 755 */     this.productVirtual = productVirtual;
/*     */   }
/*     */ 
/*     */   @Column(name="PRODUCT_COST", nullable=true, length=32)
/*     */   public String getProductCost()
/*     */   {
/* 763 */     return this.productCost;
/*     */   }
/*     */ 
/*     */   public void setProductCost(String productCost)
/*     */   {
/* 771 */     this.productCost = productCost;
/*     */   }
/*     */ 
/*     */   @Column(name="POINT_PACK", nullable=true, length=32)
/*     */   public String getPointPack()
/*     */   {
/* 779 */     return this.pointPack;
/*     */   }
/*     */ 
/*     */   public void setPointPack(String pointPack)
/*     */   {
/* 787 */     this.pointPack = pointPack;
/*     */   }
/*     */ 
/*     */   @Column(name="POINT_SELL", nullable=true, length=32)
/*     */   public String getPointSell()
/*     */   {
/* 795 */     return this.pointSell;
/*     */   }
/*     */ 
/*     */   public void setPointSell(String pointSell)
/*     */   {
/* 803 */     this.pointSell = pointSell;
/*     */   }
/*     */ 
/*     */   @Column(name="PRODUCT_UNIQUENESS_CODE", nullable=true, length=32)
/*     */   public String getProductUniquenessCode()
/*     */   {
/* 811 */     return this.productUniquenessCode;
/*     */   }
/*     */ 
/*     */   public void setProductUniquenessCode(String productUniquenessCode)
/*     */   {
/* 819 */     this.productUniquenessCode = productUniquenessCode;
/*     */   }
/*     */ 
/*     */   @Column(name="BATCH_MANAGE", nullable=true, length=32)
/*     */   public String getBatchManage()
/*     */   {
/* 827 */     return this.batchManage;
/*     */   }
/*     */ 
/*     */   public void setBatchManage(String batchManage)
/*     */   {
/* 835 */     this.batchManage = batchManage;
/*     */   }
/*     */ 
/*     */   @Column(name="PRODUCT_SINGLE_CODE", nullable=true, length=32)
/*     */   public String getProductSingleCode()
/*     */   {
/* 843 */     return this.productSingleCode;
/*     */   }
/*     */ 
/*     */   public void setProductSingleCode(String productSingleCode)
/*     */   {
/* 851 */     this.productSingleCode = productSingleCode;
/*     */   }
/*     */ 
/*     */   @Column(name="EXPIRATION_DATE", nullable=true, length=32)
/*     */   public String getExpirationDate()
/*     */   {
/* 859 */     return this.expirationDate;
/*     */   }
/*     */ 
/*     */   public void setExpirationDate(String expirationDate)
/*     */   {
/* 867 */     this.expirationDate = expirationDate;
/*     */   }
/*     */ 
/*     */   @Column(name="SUPPLY_OF_MATERIAL_ROUND", nullable=true, length=32)
/*     */   public String getSupplyOfMaterialRound()
/*     */   {
/* 875 */     return this.supplyOfMaterialRound;
/*     */   }
/*     */ 
/*     */   public void setSupplyOfMaterialRound(String supplyOfMaterialRound)
/*     */   {
/* 883 */     this.supplyOfMaterialRound = supplyOfMaterialRound;
/*     */   }
/*     */ 
/*     */   @Column(name="SAFETY_INVENTORY", nullable=true, length=32)
/*     */   public String getSafetyInventory()
/*     */   {
/* 891 */     return this.safetyInventory;
/*     */   }
/*     */ 
/*     */   public void setSafetyInventory(String safetyInventory)
/*     */   {
/* 899 */     this.safetyInventory = safetyInventory;
/*     */   }
/*     */ 
/*     */   @Column(name="INTERNATIONAL_CODE", nullable=true, length=32)
/*     */   public String getInternationalCode()
/*     */   {
/* 907 */     return this.internationalCode;
/*     */   }
/*     */ 
/*     */   public void setInternationalCode(String internationalCode)
/*     */   {
/* 915 */     this.internationalCode = internationalCode;
/*     */   }
/*     */ 
/*     */   @Column(name="REMARK", nullable=true, length=200)
/*     */   public String getRemark()
/*     */   {
/* 923 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 931 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   @Column(name="PRODUCT_STATE", nullable=true, length=32)
/*     */   public String getProductState()
/*     */   {
/* 939 */     return this.productState;
/*     */   }
/*     */ 
/*     */   public void setProductState(String productState)
/*     */   {
/* 947 */     this.productState = productState;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.goods.GoodsEntity
 * JD-Core Version:    0.6.2
 */