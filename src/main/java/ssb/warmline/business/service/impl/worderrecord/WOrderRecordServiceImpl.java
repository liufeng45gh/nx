/*    */ package ssb.warmline.business.service.impl.worderrecord;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.worderrecord.WOrderRecordEntity;
/*    */ import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;
/*    */ 
/*    */ @Service("wOrderRecordService")
/*    */ @Transactional
/*    */ public class WOrderRecordServiceImpl extends CommonServiceImpl
/*    */   implements WOrderRecordServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WOrderRecordEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WOrderRecordEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WOrderRecordEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WOrderRecordEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WOrderRecordEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WOrderRecordEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WOrderRecordEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{order_id}", String.valueOf(t.getOrderId()));
/* 67 */     sql = sql.replace("#{amount}", String.valueOf(t.getAmount()));
/* 68 */     sql = sql.replace("#{order_type}", String.valueOf(t.getOrderType()));
/* 69 */     sql = sql.replace("#{create_time}", String.valueOf(t.getCreateTime()));
/* 70 */     sql = sql.replace("#{description}", String.valueOf(t.getDescription()));
/* 71 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 72 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.worderrecord.WOrderRecordServiceImpl
 * JD-Core Version:    0.6.2
 */