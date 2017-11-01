/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import org.jeecgframework.core.common.exception.BusinessException;
/*    */ 
/*    */ public class ExceptionUtils
/*    */ {
/*    */   public static void throwIfNull(Object target, String errorMessage)
/*    */   {
/* 20 */     if (target == null)
/* 21 */       throw new BusinessException(errorMessage);
/*    */   }
/*    */ 
/*    */   public static void throwIfEmpty(String target, String errorMessage)
/*    */   {
/* 34 */     if ((target == null) || (target.equals("")))
/* 35 */       throw new BusinessException(errorMessage);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.ExceptionUtils
 * JD-Core Version:    0.6.2
 */