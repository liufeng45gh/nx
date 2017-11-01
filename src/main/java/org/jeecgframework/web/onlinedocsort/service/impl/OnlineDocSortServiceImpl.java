/*    */ package org.jeecgframework.web.onlinedocsort.service.impl;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.onlinedocsort.entity.OnlineDocSortEntity;
/*    */ import org.jeecgframework.web.onlinedocsort.service.OnlineDocSortServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("onlineDocSortService")
/*    */ @Transactional
/*    */ public class OnlineDocSortServiceImpl extends CommonServiceImpl
/*    */   implements OnlineDocSortServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 17 */     super.delete(entity);
/*    */ 
/* 19 */     doDelSql((OnlineDocSortEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 23 */     Serializable t = super.save(entity);
/*    */ 
/* 25 */     doAddSql((OnlineDocSortEntity)entity);
/* 26 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 30 */     super.saveOrUpdate(entity);
/*    */ 
/* 32 */     doUpdateSql((OnlineDocSortEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(OnlineDocSortEntity t)
/*    */   {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(OnlineDocSortEntity t)
/*    */   {
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(OnlineDocSortEntity t)
/*    */   {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, OnlineDocSortEntity t)
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
/* 76 */     sql = sql.replace("#{name}", String.valueOf(t.getName()));
/* 77 */     sql = sql.replace("#{parent}", String.valueOf(t.getParent().getId()));
/* 78 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 79 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.onlinedocsort.service.impl.OnlineDocSortServiceImpl
 * JD-Core Version:    0.6.2
 */