/*    */ package org.jeecgframework.web.cgform.service.impl.button;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;
/*    */ import org.jeecgframework.web.cgform.service.button.CgformButtonSqlServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("cgformButtonSqlService")
/*    */ @Transactional
/*    */ public class CgformButtonSqlServiceImpl extends CommonServiceImpl
/*    */   implements CgformButtonSqlServiceI
/*    */ {
/*    */   public List<CgformButtonSqlEntity> checkCgformButtonSql(CgformButtonSqlEntity cgformButtonSqlEntity)
/*    */   {
/* 24 */     StringBuilder hql = new StringBuilder("");
/* 25 */     hql.append(" from CgformButtonSqlEntity t");
/* 26 */     hql.append(" where t.formId='").append(cgformButtonSqlEntity.getFormId()).append("'");
/* 27 */     hql.append(" and  t.buttonCode ='").append(cgformButtonSqlEntity.getButtonCode()).append("'");
/* 28 */     if (cgformButtonSqlEntity.getId() != null) {
/* 29 */       hql.append(" and t.id !='").append(cgformButtonSqlEntity.getId()).append("'");
/*    */     }
/* 31 */     List list = findHql(hql.toString(), new Object[0]);
/* 32 */     return list;
/*    */   }
/*    */ 
/*    */   public CgformButtonSqlEntity getCgformButtonSqlByCodeFormId(String buttonCode, String formId)
/*    */   {
/* 37 */     StringBuilder hql = new StringBuilder("");
/* 38 */     hql.append(" from CgformButtonSqlEntity t");
/* 39 */     hql.append(" where t.formId='").append(formId).append("'");
/* 40 */     hql.append(" and  t.buttonCode ='").append(buttonCode).append("'");
/* 41 */     List list = findHql(hql.toString(), new Object[0]);
/* 42 */     if ((list != null) && (list.size() > 0)) {
/* 43 */       return (CgformButtonSqlEntity)list.get(0);
/*    */     }
/* 45 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.button.CgformButtonSqlServiceImpl
 * JD-Core Version:    0.6.2
 */