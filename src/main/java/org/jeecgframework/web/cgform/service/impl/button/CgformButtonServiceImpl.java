/*    */ package org.jeecgframework.web.cgform.service.impl.button;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
/*    */ import org.jeecgframework.web.cgform.service.button.CgformButtonServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("cgformButtonService")
/*    */ @Transactional
/*    */ public class CgformButtonServiceImpl extends CommonServiceImpl
/*    */   implements CgformButtonServiceI
/*    */ {
/*    */   public List<CgformButtonEntity> getCgformButtonListByFormId(String formId)
/*    */   {
/* 23 */     StringBuilder hql = new StringBuilder("");
/* 24 */     hql.append(" from CgformButtonEntity t");
/* 25 */     hql.append(" where t.formId=? order by t.orderNum asc");
/* 26 */     List list = findHql(hql.toString(), new Object[] { formId });
/* 27 */     return list;
/*    */   }
/*    */ 
/*    */   public List<CgformButtonEntity> checkCgformButton(CgformButtonEntity cgformButtonEntity)
/*    */   {
/* 37 */     StringBuilder hql = new StringBuilder("");
/* 38 */     hql.append(" from CgformButtonEntity t");
/* 39 */     hql.append(" where t.formId='").append(cgformButtonEntity.getFormId()).append("'");
/* 40 */     hql.append(" and  t.buttonCode ='").append(cgformButtonEntity.getButtonCode()).append("'");
/* 41 */     if (cgformButtonEntity.getId() != null) {
/* 42 */       hql.append(" and t.id !='").append(cgformButtonEntity.getId()).append("'");
/*    */     }
/* 44 */     List list = findHql(hql.toString(), new Object[0]);
/* 45 */     return list;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.button.CgformButtonServiceImpl
 * JD-Core Version:    0.6.2
 */