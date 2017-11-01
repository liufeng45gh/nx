/*    */ package ssb.warmline.business.service.impl.wcapital;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wcapital.WCapitalEntity;
/*    */ import ssb.warmline.business.service.wcapital.WCapitalServiceI;
/*    */ 
/*    */ @Service("wCapitalService")
/*    */ @Transactional
/*    */ public class WCapitalServiceImpl extends CommonServiceImpl
/*    */   implements WCapitalServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WCapitalEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WCapitalEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WCapitalEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WCapitalEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WCapitalEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WCapitalEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WCapitalEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{user_id}", String.valueOf(t.getUserId()));
/* 67 */     sql = sql.replace("#{user_name}", String.valueOf(t.getUserName()));
/* 68 */     sql = sql.replace("#{trade_time}", String.valueOf(t.getTradeTime()));
/* 69 */     sql = sql.replace("#{pay_method}", String.valueOf(t.getPayMethod()));
/* 70 */     sql = sql.replace("#{description}", String.valueOf(t.getDescription()));
/* 71 */     sql = sql.replace("#{amout}", String.valueOf(t.getAmout()));
/* 72 */     sql = sql.replace("#{type}", String.valueOf(t.getType()));
/* 73 */     sql = sql.replace("#{order_id}", String.valueOf(t.getOrderId()));
/* 74 */     sql = sql.replace("#{approval_time}", String.valueOf(t.getApprovalTime()));
/* 75 */     sql = sql.replace("#{approval_name}", String.valueOf(t.getApprovalName()));
/* 76 */     sql = sql.replace("#{approval_type}", String.valueOf(t.getApprovalType()));
/* 77 */     sql = sql.replace("#{remarks}", String.valueOf(t.getRemarks()));
/* 78 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 79 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wcapital.WCapitalServiceImpl
 * JD-Core Version:    0.6.2
 */