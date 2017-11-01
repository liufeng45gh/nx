/*    */ package ssb.warmline.business.service.impl.worderphoto;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.worderphoto.WOrderPhotoEntity;
/*    */ import ssb.warmline.business.service.worderphoto.WOrderPhotoServiceI;
/*    */ 
/*    */ @Service("wOrderPhotoService")
/*    */ @Transactional
/*    */ public class WOrderPhotoServiceImpl extends CommonServiceImpl
/*    */   implements WOrderPhotoServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WOrderPhotoEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WOrderPhotoEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WOrderPhotoEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WOrderPhotoEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WOrderPhotoEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WOrderPhotoEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WOrderPhotoEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{order_id}", String.valueOf(t.getOrderId()));
/* 67 */     sql = sql.replace("#{photo_name}", String.valueOf(t.getPhotoName()));
/* 68 */     sql = sql.replace("#{photo_url}", String.valueOf(t.getPhotoUrl()));
/* 69 */     sql = sql.replace("#{create_time}", String.valueOf(t.getCreateTime()));
/* 70 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 71 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.worderphoto.WOrderPhotoServiceImpl
 * JD-Core Version:    0.6.2
 */