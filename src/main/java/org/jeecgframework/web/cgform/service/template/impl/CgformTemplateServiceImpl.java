/*    */ package org.jeecgframework.web.cgform.service.template.impl;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
/*    */ import org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("cgformTemplateService")
/*    */ @Transactional
/*    */ public class CgformTemplateServiceImpl extends CommonServiceImpl
/*    */   implements CgformTemplateServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 19 */     super.delete(entity);
/*    */ 
/* 21 */     doDelSql((CgformTemplateEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 25 */     Serializable t = super.save(entity);
/*    */ 
/* 27 */     doAddSql((CgformTemplateEntity)entity);
/* 28 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 32 */     super.saveOrUpdate(entity);
/*    */ 
/* 34 */     doUpdateSql((CgformTemplateEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(CgformTemplateEntity t)
/*    */   {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(CgformTemplateEntity t)
/*    */   {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(CgformTemplateEntity t)
/*    */   {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, CgformTemplateEntity t)
/*    */   {
/* 68 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 69 */     sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
/* 70 */     sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
/* 71 */     sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
/* 72 */     sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
/* 73 */     sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
/* 74 */     sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
/* 75 */     sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
/* 76 */     sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
/* 77 */     sql = sql.replace("#{template_name}", String.valueOf(t.getTemplateName()));
/* 78 */     sql = sql.replace("#{template_code}", String.valueOf(t.getTemplateCode()));
/* 79 */     sql = sql.replace("#{template_type}", String.valueOf(t.getTemplateType()));
/* 80 */     sql = sql.replace("#{template_share}", String.valueOf(t.getTemplateShare()));
/* 81 */     sql = sql.replace("#{template_pic}", String.valueOf(t.getTemplatePic()));
/* 82 */     sql = sql.replace("#{template_comment}", String.valueOf(t.getTemplateComment()));
/* 83 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 84 */     return sql;
/*    */   }
/*    */ 
/*    */   public CgformTemplateEntity findByCode(String code)
/*    */   {
/* 89 */     return (CgformTemplateEntity)findUniqueByProperty(CgformTemplateEntity.class, "templateCode", code);
/*    */   }
/*    */ 
/*    */   public List<CgformTemplateEntity> getTemplateListByType(String type)
/*    */   {
/* 94 */     return findByProperty(CgformTemplateEntity.class, "templateType", type);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.template.impl.CgformTemplateServiceImpl
 * JD-Core Version:    0.6.2
 */