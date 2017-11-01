/*    */ package ssb.warmline.business.service.impl.wjpush;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wjpush.WJpushEntity;
/*    */ import ssb.warmline.business.service.wjpush.WJpushServiceI;
/*    */ 
/*    */ @Service("wJpushService")
/*    */ @Transactional
/*    */ public class WJpushServiceImpl extends CommonServiceImpl
/*    */   implements WJpushServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WJpushEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WJpushEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WJpushEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WJpushEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WJpushEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WJpushEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WJpushEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{create_time}", String.valueOf(t.getCreateTime()));
/* 67 */     sql = sql.replace("#{jpush_type}", String.valueOf(t.getJpushType()));
/* 68 */     sql = sql.replace("#{content}", String.valueOf(t.getContent()));
/* 69 */     sql = sql.replace("#{send_status}", String.valueOf(t.getSendStatus()));
/* 70 */     sql = sql.replace("#{page_whereabouts}", String.valueOf(t.getPageWhereabouts()));
/* 71 */     sql = sql.replace("#{main_title}", String.valueOf(t.getMainTitle()));
/* 72 */     sql = sql.replace("#{title}", String.valueOf(t.getTitle()));
/* 73 */     sql = sql.replace("#{photo_path}", String.valueOf(t.getPhotoPath()));
/* 74 */     sql = sql.replace("#{edit_link}", String.valueOf(t.getEditLink()));
/* 75 */     sql = sql.replace("#{text}", String.valueOf(t.getText()));
/* 76 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 77 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wjpush.WJpushServiceImpl
 * JD-Core Version:    0.6.2
 */