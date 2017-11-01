/*    */ package org.jeecgframework.web.cgform.service.impl.config.util;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
/*    */ 
/*    */ public class FieldNumComparator
/*    */   implements Comparator<CgFormFieldEntity>
/*    */ {
/*    */   public int compare(CgFormFieldEntity o1, CgFormFieldEntity o2)
/*    */   {
/* 15 */     if ((o1 == null) || (o1.getOrderNum() == null) || (o2 == null) || (o2.getOrderNum() == null))
/* 16 */       return -1;
/* 17 */     Integer thisVal = o1.getOrderNum();
/* 18 */     Integer anotherVal = o2.getOrderNum();
/* 19 */     return thisVal == anotherVal ? 0 : thisVal.intValue() < anotherVal.intValue() ? -1 : 1;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.config.util.FieldNumComparator
 * JD-Core Version:    0.6.2
 */