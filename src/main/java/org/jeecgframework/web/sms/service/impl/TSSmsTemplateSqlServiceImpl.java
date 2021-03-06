/*    */ package org.jeecgframework.web.sms.service.impl;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.sms.entity.TSSmsTemplateSqlEntity;
/*    */ import org.jeecgframework.web.sms.service.TSSmsTemplateSqlServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("tSSmsTemplateSqlService")
/*    */ @Transactional
/*    */ public class TSSmsTemplateSqlServiceImpl extends CommonServiceImpl
/*    */   implements TSSmsTemplateSqlServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((TSSmsTemplateSqlEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((TSSmsTemplateSqlEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((TSSmsTemplateSqlEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(TSSmsTemplateSqlEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(TSSmsTemplateSqlEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(TSSmsTemplateSqlEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, TSSmsTemplateSqlEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
/* 67 */     sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
/* 68 */     sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
/* 69 */     sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
/* 70 */     sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
/* 71 */     sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
/* 72 */     sql = sql.replace("#{code}", String.valueOf(t.getCode()));
/* 73 */     sql = sql.replace("#{name}", String.valueOf(t.getName()));
/* 74 */     sql = sql.replace("#{sql_id}", String.valueOf(t.getSqlId()));
/* 75 */     sql = sql.replace("#{template_id}", String.valueOf(t.getTemplateId()));
/* 76 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 77 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.service.impl.TSSmsTemplateSqlServiceImpl
 * JD-Core Version:    0.6.2
 */