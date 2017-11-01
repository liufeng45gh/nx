/*     */ package org.jeecgframework.web.cgform.service.impl.enhance;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*     */ import org.jeecgframework.core.util.ApplicationContextUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
/*     */ import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJavaServiceI;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("cgformEnhanceJavaService")
/*     */ @Transactional
/*     */ public class CgformEnhanceJavaServiceImpl extends CommonServiceImpl
/*     */   implements CgformEnhanceJavaServiceI
/*     */ {
/*     */   public <T> void delete(T entity)
/*     */   {
/*  20 */     super.delete(entity);
/*     */ 
/*  22 */     doDelSql((CgformEnhanceJavaEntity)entity);
/*     */   }
/*     */ 
/*     */   public <T> Serializable save(T entity) {
/*  26 */     Serializable t = super.save(entity);
/*     */ 
/*  28 */     doAddSql((CgformEnhanceJavaEntity)entity);
/*  29 */     return t;
/*     */   }
/*     */ 
/*     */   public <T> void saveOrUpdate(T entity) {
/*  33 */     super.saveOrUpdate(entity);
/*     */ 
/*  35 */     doUpdateSql((CgformEnhanceJavaEntity)entity);
/*     */   }
/*     */ 
/*     */   public boolean doAddSql(CgformEnhanceJavaEntity t)
/*     */   {
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doUpdateSql(CgformEnhanceJavaEntity t)
/*     */   {
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doDelSql(CgformEnhanceJavaEntity t)
/*     */   {
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   public String replaceVal(String sql, CgformEnhanceJavaEntity t)
/*     */   {
/*  69 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/*  70 */     sql = sql.replace("#{cg_java_type}", String.valueOf(t.getCgJavaType()));
/*  71 */     sql = sql.replace("#{cg_java_value}", String.valueOf(t.getCgJavaValue()));
/*  72 */     sql = sql.replace("#{form_id}", String.valueOf(t.getFormId()));
/*  73 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/*  74 */     return sql;
/*     */   }
/*     */ 
/*     */   public CgformEnhanceJavaEntity getCgformEnhanceJavaEntityByCodeFormId(String buttonCode, String formId)
/*     */   {
/*  80 */     StringBuilder hql = new StringBuilder("");
/*  81 */     hql.append(" from CgformEnhanceJavaEntity t");
/*  82 */     hql.append(" where t.formId='").append(formId).append("'");
/*  83 */     hql.append(" and  t.buttonCode ='").append(buttonCode).append("'");
/*  84 */     List list = findHql(hql.toString(), new Object[0]);
/*  85 */     if ((list != null) && (list.size() > 0)) {
/*  86 */       return (CgformEnhanceJavaEntity)list.get(0);
/*     */     }
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */   public List<CgformEnhanceJavaEntity> checkCgformEnhanceJavaEntity(CgformEnhanceJavaEntity cgformEnhanceJavaEntity)
/*     */   {
/*  94 */     StringBuilder hql = new StringBuilder("");
/*  95 */     hql.append(" from CgformEnhanceJavaEntity t");
/*  96 */     hql.append(" where t.formId='").append(cgformEnhanceJavaEntity.getFormId()).append("'");
/*  97 */     hql.append(" and  t.buttonCode ='").append(cgformEnhanceJavaEntity.getButtonCode()).append("'");
/*  98 */     if (cgformEnhanceJavaEntity.getId() != null) {
/*  99 */       hql.append(" and t.id !='").append(cgformEnhanceJavaEntity.getId()).append("'");
/*     */     }
/* 101 */     List list = findHql(hql.toString(), new Object[0]);
/* 102 */     return list;
/*     */   }
/*     */ 
/*     */   public boolean checkClassOrSpringBeanIsExist(CgformEnhanceJavaEntity cgformEnhanceJavaEntity)
/*     */   {
/* 107 */     String cgJavaType = cgformEnhanceJavaEntity.getCgJavaType();
/* 108 */     String cgJavaValue = cgformEnhanceJavaEntity.getCgJavaValue();
/*     */ 
/* 110 */     if (StringUtil.isNotEmpty(cgJavaValue)) {
/*     */       try {
/* 112 */         if ("class".equals(cgJavaType)) {
/* 113 */           Class clazz = Class.forName(cgJavaValue);
/* 114 */           if ((clazz == null) || (clazz.newInstance() == null)) {
/* 115 */             return false;
/*     */           }
/*     */         }
/* 118 */         if ("spring".equals(cgJavaType)) {
/* 119 */           Object obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
/* 120 */           if (obj == null)
/* 121 */             return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 124 */         e.printStackTrace();
/* 125 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 129 */     return true;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.enhance.CgformEnhanceJavaServiceImpl
 * JD-Core Version:    0.6.2
 */