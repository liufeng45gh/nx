/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ public class ImportUtil
/*    */ {
/*    */   public static Class getEntityClass(String fullentity)
/*    */   {
/* 15 */     Class entityClass = null;
/*    */     try {
/* 17 */       entityClass = Class.forName(fullentity);
/*    */     } catch (ClassNotFoundException e) {
/* 19 */       e.printStackTrace();
/*    */     }
/* 21 */     return entityClass;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.ImportUtil
 * JD-Core Version:    0.6.2
 */