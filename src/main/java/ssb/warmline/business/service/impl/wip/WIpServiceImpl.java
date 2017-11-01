/*    */ package ssb.warmline.business.service.impl.wip;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wip.WIpEntity;
/*    */ import ssb.warmline.business.service.wip.WIpServiceI;
/*    */ 
/*    */ @Service("wIpService")
/*    */ @Transactional
/*    */ public class WIpServiceImpl extends CommonServiceImpl
/*    */   implements WIpServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WIpEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WIpEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WIpEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WIpEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WIpEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WIpEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WIpEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{phone}", String.valueOf(t.getPhone()));
/* 67 */     sql = sql.replace("#{ip}", String.valueOf(t.getIp()));
/* 68 */     sql = sql.replace("#{count}", String.valueOf(t.getCount()));
/* 69 */     sql = sql.replace("#{date}", String.valueOf(t.getDate()));
/* 70 */     sql = sql.replace("#{type}", String.valueOf(t.getType()));
/* 71 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 72 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wip.WIpServiceImpl
 * JD-Core Version:    0.6.2
 */