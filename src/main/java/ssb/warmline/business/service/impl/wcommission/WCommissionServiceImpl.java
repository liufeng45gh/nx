/*    */ package ssb.warmline.business.service.impl.wcommission;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wcommission.WCommissionEntity;
/*    */ import ssb.warmline.business.service.wcommission.WCommissionServiceI;
/*    */ 
/*    */ @Service("wCommissionService")
/*    */ @Transactional
/*    */ public class WCommissionServiceImpl extends CommonServiceImpl
/*    */   implements WCommissionServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WCommissionEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WCommissionEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WCommissionEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WCommissionEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WCommissionEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WCommissionEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WCommissionEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{user_id}", String.valueOf(t.getUserId()));
/* 67 */     sql = sql.replace("#{user_name}", String.valueOf(t.getUserName()));
/* 68 */     sql = sql.replace("#{phone}", String.valueOf(t.getPhone()));
/* 69 */     sql = sql.replace("#{amount}", String.valueOf(t.getAmount()));
/* 70 */     sql = sql.replace("#{create_time}", String.valueOf(t.getCreateTime()));
/* 71 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 72 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wcommission.WCommissionServiceImpl
 * JD-Core Version:    0.6.2
 */