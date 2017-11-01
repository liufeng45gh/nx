/*    */ package org.jeecgframework.web.cgform.service.impl.autoform;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.cgform.entity.autoform.AutoFormStyleEntity;
/*    */ import org.jeecgframework.web.cgform.service.autoform.AutoFormStyleServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("autoFormStyleService")
/*    */ @Transactional
/*    */ public class AutoFormStyleServiceImpl extends CommonServiceImpl
/*    */   implements AutoFormStyleServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 17 */     super.delete(entity);
/*    */ 
/* 19 */     doDelSql((AutoFormStyleEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 23 */     Serializable t = super.save(entity);
/*    */ 
/* 25 */     doAddSql((AutoFormStyleEntity)entity);
/* 26 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 30 */     super.saveOrUpdate(entity);
/*    */ 
/* 32 */     doUpdateSql((AutoFormStyleEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(AutoFormStyleEntity t)
/*    */   {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(AutoFormStyleEntity t)
/*    */   {
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(AutoFormStyleEntity t)
/*    */   {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, AutoFormStyleEntity t)
/*    */   {
/* 66 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 67 */     sql = sql.replace("#{style_desc}", String.valueOf(t.getStyleDesc()));
/* 68 */     sql = sql.replace("#{style_content}", String.valueOf(t.getStyleContent()));
/* 69 */     sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
/* 70 */     sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
/* 71 */     sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
/* 72 */     sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
/* 73 */     sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
/* 74 */     sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
/* 75 */     sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
/* 76 */     sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
/* 77 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 78 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.autoform.AutoFormStyleServiceImpl
 * JD-Core Version:    0.6.2
 */