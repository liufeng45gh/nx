/*    */ package ssb.warmline.business.service.impl.mobileloginlog;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.mobileloginlog.MobileLoginLogEntity;
/*    */ import ssb.warmline.business.service.mobileloginlog.MobileLoginLogServiceI;
/*    */ 
/*    */ @Service("mobileLoginLogService")
/*    */ @Transactional
/*    */ public class MobileLoginLogServiceImpl extends CommonServiceImpl
/*    */   implements MobileLoginLogServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((MobileLoginLogEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((MobileLoginLogEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((MobileLoginLogEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(MobileLoginLogEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(MobileLoginLogEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(MobileLoginLogEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, MobileLoginLogEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{username}", String.valueOf(t.getUsername()));
/* 67 */     sql = sql.replace("#{depart_name}", String.valueOf(t.getDepartName()));
/* 68 */     sql = sql.replace("#{departid}", String.valueOf(t.getDepartid()));
/* 69 */     sql = sql.replace("#{ip}", String.valueOf(t.getIp()));
/* 70 */     sql = sql.replace("#{login_time}", String.valueOf(t.getLoginTime()));
/* 71 */     sql = sql.replace("#{user_agent}", String.valueOf(t.getUserAgent()));
/* 72 */     sql = sql.replace("#{realname}", String.valueOf(t.getRealname()));
/* 73 */     sql = sql.replace("#{agent_type}", String.valueOf(t.getAgentType()));
/* 74 */     sql = sql.replace("#{app_type}", String.valueOf(t.getAppType()));
/* 75 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 76 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.mobileloginlog.MobileLoginLogServiceImpl
 * JD-Core Version:    0.6.2
 */