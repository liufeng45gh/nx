/*    */ package org.jeecgframework.web.onlinedoc.service.impl;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.onlinedoc.entity.OnlineDocEntity;
/*    */ import org.jeecgframework.web.onlinedoc.service.OnlineDocServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("onlineDocService")
/*    */ @Transactional
/*    */ public class OnlineDocServiceImpl extends CommonServiceImpl
/*    */   implements OnlineDocServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 17 */     super.delete(entity);
/*    */ 
/* 19 */     doDelSql((OnlineDocEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 23 */     Serializable t = super.save(entity);
/*    */ 
/* 25 */     doAddSql((OnlineDocEntity)entity);
/* 26 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 30 */     super.saveOrUpdate(entity);
/*    */ 
/* 32 */     doUpdateSql((OnlineDocEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(OnlineDocEntity t)
/*    */   {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(OnlineDocEntity t)
/*    */   {
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(OnlineDocEntity t)
/*    */   {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, OnlineDocEntity t)
/*    */   {
/* 66 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 67 */     sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
/* 68 */     sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
/* 69 */     sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
/* 70 */     sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
/* 71 */     sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
/* 72 */     sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
/* 73 */     sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
/* 74 */     sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
/* 75 */     sql = sql.replace("#{bpm_status}", String.valueOf(t.getBpmStatus()));
/* 76 */     sql = sql.replace("#{old_name}", String.valueOf(t.getOldName()));
/* 77 */     sql = sql.replace("#{new_name}", String.valueOf(t.getNewName()));
/* 78 */     sql = sql.replace("#{description}", String.valueOf(t.getDescription()));
/* 79 */     sql = sql.replace("#{treeNode}", String.valueOf(t.getTreeNode()));
/* 80 */     sql = sql.replace("#{path}", String.valueOf(t.getPath()));
/* 81 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 82 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.onlinedoc.service.impl.OnlineDocServiceImpl
 * JD-Core Version:    0.6.2
 */