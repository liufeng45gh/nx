/*    */ package ssb.warmline.business.service.impl.wphonecode;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wphonecode.WPhoneCodeEntity;
/*    */ import ssb.warmline.business.service.wphonecode.WPhoneCodeServiceI;
/*    */ 
/*    */ @Service("wPhoneCodeService")
/*    */ @Transactional
/*    */ public class WPhoneCodeServiceImpl extends CommonServiceImpl
/*    */   implements WPhoneCodeServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WPhoneCodeEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WPhoneCodeEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WPhoneCodeEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WPhoneCodeEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WPhoneCodeEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WPhoneCodeEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WPhoneCodeEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{phone}", String.valueOf(t.getPhone()));
/* 67 */     sql = sql.replace("#{count}", String.valueOf(t.getCount()));
/* 68 */     sql = sql.replace("#{ip}", String.valueOf(t.getIp()));
/* 69 */     sql = sql.replace("#{creat_date}", String.valueOf(t.getCreatDate()));
/* 70 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 71 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wphonecode.WPhoneCodeServiceImpl
 * JD-Core Version:    0.6.2
 */