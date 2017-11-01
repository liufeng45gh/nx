/*    */ package ssb.warmline.business.service.impl.wversionupdatemanagement;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wversionupdatemanagement.WVersionUpdateManagementEntity;
/*    */ import ssb.warmline.business.service.wversionupdatemanagement.WVersionUpdateManagementServiceI;
/*    */ 
/*    */ @Service("wVersionUpdateManagementService")
/*    */ @Transactional
/*    */ public class WVersionUpdateManagementServiceImpl extends CommonServiceImpl
/*    */   implements WVersionUpdateManagementServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WVersionUpdateManagementEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WVersionUpdateManagementEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WVersionUpdateManagementEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WVersionUpdateManagementEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WVersionUpdateManagementEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WVersionUpdateManagementEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WVersionUpdateManagementEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
/* 67 */     sql = sql.replace("#{version_name}", String.valueOf(t.getVersionName()));
/* 68 */     sql = sql.replace("#{version_number}", String.valueOf(t.getVersionNumber()));
/* 69 */     sql = sql.replace("#{version_description}", String.valueOf(t.getVersionDescription()));
/* 70 */     sql = sql.replace("#{upload_url}", String.valueOf(t.getUploadUrl()));
/* 71 */     sql = sql.replace("#{download_url}", String.valueOf(t.getDownloadUrl()));
/* 72 */     sql = sql.replace("#{current_field}", String.valueOf(t.getCurrentField()));
/* 73 */     sql = sql.replace("#{appid}", String.valueOf(t.getAppid()));
/* 74 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 75 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wversionupdatemanagement.WVersionUpdateManagementServiceImpl
 * JD-Core Version:    0.6.2
 */