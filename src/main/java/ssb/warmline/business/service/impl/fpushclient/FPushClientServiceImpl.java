/*    */ package ssb.warmline.business.service.impl.fpushclient;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.fpushclient.FPushClientEntity;
/*    */ import ssb.warmline.business.service.fpushclient.FPushClientServiceI;
/*    */ 
/*    */ @Service("fPushClientService")
/*    */ @Transactional
/*    */ public class FPushClientServiceImpl extends CommonServiceImpl
/*    */   implements FPushClientServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((FPushClientEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((FPushClientEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((FPushClientEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(FPushClientEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(FPushClientEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(FPushClientEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, FPushClientEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{create_time}", String.valueOf(t.getCreateTime()));
/* 67 */     sql = sql.replace("#{uid}", String.valueOf(t.getUid()));
/* 68 */     sql = sql.replace("#{username}", String.valueOf(t.getUsername()));
/* 69 */     sql = sql.replace("#{depart_id}", String.valueOf(t.getDepartId()));
/* 70 */     sql = sql.replace("#{depart_name}", String.valueOf(t.getDepartName()));
/* 71 */     sql = sql.replace("#{token}", String.valueOf(t.getToken()));
/* 72 */     sql = sql.replace("#{client_id}", String.valueOf(t.getClientId()));
/* 73 */     sql = sql.replace("#{app_id}", String.valueOf(t.getAppId()));
/* 74 */     sql = sql.replace("#{app_type}", String.valueOf(t.getAppType()));
/* 75 */     sql = sql.replace("#{devicetoken}", String.valueOf(t.getDevicetoken()));
/* 76 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 77 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.fpushclient.FPushClientServiceImpl
 * JD-Core Version:    0.6.2
 */