/*    */ package ssb.warmline.business.service.impl.territoryandcategory;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.territoryandcategory.WTerritoryCategoryEntity;
/*    */ import ssb.warmline.business.service.territoryandcategory.WTerritoryCategoryServiceI;
/*    */ 
/*    */ @Service("wTerritoryCategoryService")
/*    */ @Transactional
/*    */ public class WTerritoryCategoryServiceImpl extends CommonServiceImpl
/*    */   implements WTerritoryCategoryServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WTerritoryCategoryEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WTerritoryCategoryEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WTerritoryCategoryEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WTerritoryCategoryEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WTerritoryCategoryEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WTerritoryCategoryEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WTerritoryCategoryEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{category_id}", String.valueOf(t.getCategoryId()));
/* 67 */     sql = sql.replace("#{territory_id}", String.valueOf(t.getTerritoryId()));
/* 68 */     sql = sql.replace("#{price}", String.valueOf(t.getPrice()));
/* 69 */     sql = sql.replace("#{category_image}", String.valueOf(t.getCategoryImage()));
/* 70 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 71 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.territoryandcategory.WTerritoryCategoryServiceImpl
 * JD-Core Version:    0.6.2
 */