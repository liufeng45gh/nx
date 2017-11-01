/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ 
/*    */ public class ExceptionUtil
/*    */ {
/*    */   public static String getExceptionMessage(Exception ex)
/*    */   {
/* 20 */     StringWriter sw = new StringWriter();
/* 21 */     PrintWriter pw = new PrintWriter(sw);
/* 22 */     ex.printStackTrace(pw);
/* 23 */     return sw.toString();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.ExceptionUtil
 * JD-Core Version:    0.6.2
 */