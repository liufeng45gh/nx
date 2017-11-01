/*     */ package org.jeecgframework.web.demo.service.impl.goods;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.UUID;
/*     */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*     */ import org.jeecgframework.web.demo.entity.goods.GoodsEntity;
/*     */ import org.jeecgframework.web.demo.service.goods.GoodsServiceI;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("goodsService")
/*     */ @Transactional
/*     */ public class GoodsServiceImpl extends CommonServiceImpl
/*     */   implements GoodsServiceI
/*     */ {
/*     */   public <T> void delete(T entity)
/*     */   {
/*  16 */     super.delete(entity);
/*     */ 
/*  18 */     doDelSql((GoodsEntity)entity);
/*     */   }
/*     */ 
/*     */   public <T> Serializable save(T entity) {
/*  22 */     Serializable t = super.save(entity);
/*     */ 
/*  24 */     doAddSql((GoodsEntity)entity);
/*  25 */     return t;
/*     */   }
/*     */ 
/*     */   public <T> void saveOrUpdate(T entity) {
/*  29 */     super.saveOrUpdate(entity);
/*     */ 
/*  31 */     doUpdateSql((GoodsEntity)entity);
/*     */   }
/*     */ 
/*     */   public boolean doAddSql(GoodsEntity t)
/*     */   {
/*  40 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doUpdateSql(GoodsEntity t)
/*     */   {
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doDelSql(GoodsEntity t)
/*     */   {
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   public String replaceVal(String sql, GoodsEntity t)
/*     */   {
/*  65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/*  66 */     sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
/*  67 */     sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
/*  68 */     sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
/*  69 */     sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
/*  70 */     sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
/*  71 */     sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
/*  72 */     sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
/*  73 */     sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
/*  74 */     sql = sql.replace("#{bpm_status}", String.valueOf(t.getBpmStatus()));
/*  75 */     sql = sql.replace("#{name}", String.valueOf(t.getName()));
/*  76 */     sql = sql.replace("#{code}", String.valueOf(t.getCode()));
/*  77 */     sql = sql.replace("#{full_name}", String.valueOf(t.getFullName()));
/*  78 */     sql = sql.replace("#{outside_code}", String.valueOf(t.getOutsideCode()));
/*  79 */     sql = sql.replace("#{manufacturers_no}", String.valueOf(t.getManufacturersNo()));
/*  80 */     sql = sql.replace("#{supplier}", String.valueOf(t.getSupplier()));
/*  81 */     sql = sql.replace("#{product_unit}", String.valueOf(t.getProductUnit()));
/*  82 */     sql = sql.replace("#{product_owner}", String.valueOf(t.getProductOwner()));
/*  83 */     sql = sql.replace("#{brand}", String.valueOf(t.getBrand()));
/*  84 */     sql = sql.replace("#{annual}", String.valueOf(t.getAnnual()));
/*  85 */     sql = sql.replace("#{season}", String.valueOf(t.getSeason()));
/*  86 */     sql = sql.replace("#{product_type}", String.valueOf(t.getProductType()));
/*  87 */     sql = sql.replace("#{series_name}", String.valueOf(t.getSeriesName()));
/*  88 */     sql = sql.replace("#{size_length}", String.valueOf(t.getSizeLength()));
/*  89 */     sql = sql.replace("#{size_width}", String.valueOf(t.getSizeWidth()));
/*  90 */     sql = sql.replace("#{size_height}", String.valueOf(t.getSizeHeight()));
/*  91 */     sql = sql.replace("#{size_volume}", String.valueOf(t.getSizeVolume()));
/*  92 */     sql = sql.replace("#{time_to_market}", String.valueOf(t.getTimeToMarket()));
/*  93 */     sql = sql.replace("#{price_cost}", String.valueOf(t.getPriceCost()));
/*  94 */     sql = sql.replace("#{price_drop}", String.valueOf(t.getPriceDrop()));
/*  95 */     sql = sql.replace("#{price_standard_sell}", String.valueOf(t.getPriceStandardSell()));
/*  96 */     sql = sql.replace("#{price_standard_bid}", String.valueOf(t.getPriceStandardBid()));
/*  97 */     sql = sql.replace("#{price_trade}", String.valueOf(t.getPriceTrade()));
/*  98 */     sql = sql.replace("#{price_proxy}", String.valueOf(t.getPriceProxy()));
/*  99 */     sql = sql.replace("#{price_platform}", String.valueOf(t.getPricePlatform()));
/* 100 */     sql = sql.replace("#{gift}", String.valueOf(t.getGift()));
/* 101 */     sql = sql.replace("#{product_virtual}", String.valueOf(t.getProductVirtual()));
/* 102 */     sql = sql.replace("#{product_cost}", String.valueOf(t.getProductCost()));
/* 103 */     sql = sql.replace("#{point_pack}", String.valueOf(t.getPointPack()));
/* 104 */     sql = sql.replace("#{point_sell}", String.valueOf(t.getPointSell()));
/* 105 */     sql = sql.replace("#{product_uniqueness_code}", String.valueOf(t.getProductUniquenessCode()));
/* 106 */     sql = sql.replace("#{batch_manage}", String.valueOf(t.getBatchManage()));
/* 107 */     sql = sql.replace("#{product_single_code}", String.valueOf(t.getProductSingleCode()));
/* 108 */     sql = sql.replace("#{expiration_date}", String.valueOf(t.getExpirationDate()));
/* 109 */     sql = sql.replace("#{supply_of_material_round}", String.valueOf(t.getSupplyOfMaterialRound()));
/* 110 */     sql = sql.replace("#{safety_inventory}", String.valueOf(t.getSafetyInventory()));
/* 111 */     sql = sql.replace("#{international_code}", String.valueOf(t.getInternationalCode()));
/* 112 */     sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
/* 113 */     sql = sql.replace("#{product_state}", String.valueOf(t.getProductState()));
/* 114 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 115 */     return sql;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.goods.GoodsServiceImpl
 * JD-Core Version:    0.6.2
 */