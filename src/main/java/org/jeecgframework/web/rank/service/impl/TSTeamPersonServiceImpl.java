/*    */ package org.jeecgframework.web.rank.service.impl;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.rank.entity.TSTeamPersonEntity;
/*    */ import org.jeecgframework.web.rank.service.TSTeamPersonServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("tSTeamPersonService")
/*    */ @Transactional
/*    */ public class TSTeamPersonServiceImpl extends CommonServiceImpl
/*    */   implements TSTeamPersonServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((TSTeamPersonEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((TSTeamPersonEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((TSTeamPersonEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(TSTeamPersonEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(TSTeamPersonEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(TSTeamPersonEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, TSTeamPersonEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
/* 67 */     sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
/* 68 */     sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
/* 69 */     sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
/* 70 */     sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
/* 71 */     sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
/* 72 */     sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
/* 73 */     sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
/* 74 */     sql = sql.replace("#{name}", String.valueOf(t.getName()));
/* 75 */     sql = sql.replace("#{img_src}", String.valueOf(t.getImgSrc()));
/* 76 */     sql = sql.replace("#{introduction}", String.valueOf(t.getIntroduction()));
/* 77 */     sql = sql.replace("#{jion_date}", String.valueOf(t.getJionDate()));
/* 78 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 79 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.rank.service.impl.TSTeamPersonServiceImpl
 * JD-Core Version:    0.6.2
 */