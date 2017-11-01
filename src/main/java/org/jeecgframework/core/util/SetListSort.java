/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.jeecgframework.web.system.pojo.base.TSFunction;
/*    */ 
/*    */ public class SetListSort
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2)
/*    */   {
/* 20 */     TSFunction c1 = (TSFunction)o1;
/* 21 */     TSFunction c2 = (TSFunction)o2;
/* 22 */     if ((c1.getFunctionOrder() != null) && (c2.getFunctionOrder() != null)) {
/* 23 */       int c1order = oConvertUtils.getInt(c1.getFunctionOrder().substring(c1.getFunctionOrder().indexOf("fun") + 3));
/* 24 */       int c2order = oConvertUtils.getInt(c2.getFunctionOrder().substring(c2.getFunctionOrder().indexOf("fun")) + 3);
/* 25 */       if (c1order > c2order) {
/* 26 */         return 1;
/*    */       }
/* 28 */       return -1;
/*    */     }
/*    */ 
/* 31 */     return 1;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.SetListSort
 * JD-Core Version:    0.6.2
 */