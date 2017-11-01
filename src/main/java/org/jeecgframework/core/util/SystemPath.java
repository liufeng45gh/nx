/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.net.URL;
/*    */ 
/*    */ public class SystemPath
/*    */ {
/*    */   public static String getSysPath()
/*    */   {
/* 15 */     String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
/* 16 */     String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
/* 17 */     String separator = System.getProperty("file.separator");
/* 18 */     String resultPath = temp.replaceAll("/", separator + separator);
/* 19 */     return resultPath;
/*    */   }
/*    */ 
/*    */   public static String getClassPath() {
/* 23 */     String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
/* 24 */     String temp = path.replaceFirst("file:/", "");
/* 25 */     String separator = System.getProperty("file.separator");
/* 26 */     String resultPath = temp.replaceAll("/", separator + separator);
/* 27 */     return resultPath;
/*    */   }
/*    */ 
/*    */   public static String getSystempPath() {
/* 31 */     return System.getProperty("java.io.tmpdir");
/*    */   }
/*    */ 
/*    */   public static String getSeparator() {
/* 35 */     return System.getProperty("file.separator");
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 39 */     LogUtil.info(getSysPath());
/* 40 */     LogUtil.info(System.getProperty("java.io.tmpdir"));
/* 41 */     LogUtil.info(getSeparator());
/* 42 */     LogUtil.info(getClassPath());
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.SystemPath
 * JD-Core Version:    0.6.2
 */