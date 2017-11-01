/*    */ package ssb.warmline.business.service.impl.wcomment;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wcomment.WCommentEntity;
/*    */ import ssb.warmline.business.service.wcomment.WCommentServiceI;
/*    */ 
/*    */ @Service("wCommentService")
/*    */ @Transactional
/*    */ public class WCommentServiceImpl extends CommonServiceImpl
/*    */   implements WCommentServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WCommentEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WCommentEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WCommentEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WCommentEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WCommentEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WCommentEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WCommentEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{comment_star}", String.valueOf(t.getCommentStar()));
/* 67 */     sql = sql.replace("#{comment_time}", String.valueOf(t.getCommentTime()));
/* 68 */     sql = sql.replace("#{content}", String.valueOf(t.getContent()));
/* 69 */     sql = sql.replace("#{issuer}", String.valueOf(t.getIssuer()));
/* 70 */     sql = sql.replace("#{issuer_id}", String.valueOf(t.getIssuerId()));
/* 71 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 72 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wcomment.WCommentServiceImpl
 * JD-Core Version:    0.6.2
 */