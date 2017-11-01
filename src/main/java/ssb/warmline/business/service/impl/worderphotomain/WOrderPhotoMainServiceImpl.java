/*    */ package ssb.warmline.business.service.impl.worderphotomain;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity;
/*    */ import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;
/*    */ 
/*    */ @Service("wOrderPhotoMainService")
/*    */ @Transactional
/*    */ public class WOrderPhotoMainServiceImpl extends CommonServiceImpl
/*    */   implements WOrderPhotoMainServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WOrderPhotoMainEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WOrderPhotoMainEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WOrderPhotoMainEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WOrderPhotoMainEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WOrderPhotoMainEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WOrderPhotoMainEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WOrderPhotoMainEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{photo_name}", String.valueOf(t.getPhotoName()));
/* 67 */     sql = sql.replace("#{photo_url}", String.valueOf(t.getPhotoUrl()));
/* 68 */     sql = sql.replace("#{order_id}", String.valueOf(t.getOrderId()));
/* 69 */     sql = sql.replace("#{uid}", String.valueOf(t.getUid()));
/* 70 */     sql = sql.replace("#{create_time}", String.valueOf(t.getCreateTime()));
/* 71 */     sql = sql.replace("#{photo_type}", String.valueOf(t.getPhotoType()));
/* 72 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 73 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.worderphotomain.WOrderPhotoMainServiceImpl
 * JD-Core Version:    0.6.2
 */