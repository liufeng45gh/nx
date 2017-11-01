/*    */ package ssb.warmline.business.service.impl.wcategory;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wcategory.WCategoryEntity;
/*    */ import ssb.warmline.business.service.wcategory.WCategoryServiceI;
/*    */ 
/*    */ @Service("wCategoryService")
/*    */ @Transactional
/*    */ public class WCategoryServiceImpl extends CommonServiceImpl
/*    */   implements WCategoryServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 18 */     super.delete(entity);
/*    */ 
/* 20 */     doDelSql((WCategoryEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 24 */     Serializable t = super.save(entity);
/*    */ 
/* 26 */     doAddSql((WCategoryEntity)entity);
/* 27 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 31 */     super.saveOrUpdate(entity);
/*    */ 
/* 33 */     doUpdateSql((WCategoryEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WCategoryEntity t)
/*    */   {
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WCategoryEntity t)
/*    */   {
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WCategoryEntity t)
/*    */   {
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WCategoryEntity t)
/*    */   {
/* 68 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 69 */     sql = sql.replace("#{category_name}", String.valueOf(t.getCategoryName()));
/* 70 */     sql = sql.replace("#{category_code}", String.valueOf(t.getCategoryCode()));
/* 71 */     sql = sql.replace("#{category_sort}", String.valueOf(t.getCategorySort()));
/* 72 */     sql = sql.replace("#{category_parentid}", String.valueOf(t.getCategoryParentid()));
/* 73 */     sql = sql.replace("#{category_level}", String.valueOf(t.getCategoryLevel()));
/* 74 */     sql = sql.replace("#{isparent}", String.valueOf(t.getIsParent()));
/* 75 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 76 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wcategory.WCategoryServiceImpl
 * JD-Core Version:    0.6.2
 */