/*    */ package org.jeecgframework.web.cgform.service.impl.enhance;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
/*    */ import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJsServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("cgformenhanceJsService")
/*    */ @Transactional
/*    */ public class CgformEnhanceJsServiceImpl extends CommonServiceImpl
/*    */   implements CgformEnhanceJsServiceI
/*    */ {
/*    */   public CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(String cgJsType, String formId)
/*    */   {
/* 24 */     StringBuilder hql = new StringBuilder("");
/* 25 */     hql.append(" from CgformEnhanceJsEntity t");
/* 26 */     hql.append(" where t.formId='").append(formId).append("'");
/* 27 */     hql.append(" and  t.cgJsType ='").append(cgJsType).append("'");
/* 28 */     List list = findHql(hql.toString(), new Object[0]);
/* 29 */     if ((list != null) && (list.size() > 0)) {
/* 30 */       return (CgformEnhanceJsEntity)list.get(0);
/*    */     }
/* 32 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.enhance.CgformEnhanceJsServiceImpl
 * JD-Core Version:    0.6.2
 */