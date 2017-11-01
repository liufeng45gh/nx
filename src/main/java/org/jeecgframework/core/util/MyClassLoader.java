/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.net.URL;
/*    */ import java.net.URLDecoder;
/*    */ 
/*    */ public class MyClassLoader extends ClassLoader
/*    */ {
/*    */   public static Class getClassByScn(String className)
/*    */   {
/* 10 */     Class myclass = null;
/*    */     try {
/* 12 */       myclass = Class.forName(className);
/*    */     } catch (ClassNotFoundException e) {
/* 14 */       e.printStackTrace();
/*    */     }
/* 16 */     return myclass;
/*    */   }
/*    */ 
/*    */   public static String getPackPath(Object object)
/*    */   {
/* 22 */     if (object == null) {
/* 23 */       throw new IllegalArgumentException("参数不能为空！");
/*    */     }
/* 25 */     String clsName = object.getClass().getName();
/* 26 */     return clsName;
/*    */   }
/*    */ 
/*    */   public static String getAppPath(Class cls)
/*    */   {
/* 31 */     if (cls == null)
/* 32 */       throw new IllegalArgumentException("参数不能为空！");
/* 33 */     ClassLoader loader = cls.getClassLoader();
/*    */ 
/* 35 */     String clsName = cls.getName() + ".class";
/*    */ 
/* 37 */     Package pack = cls.getPackage();
/* 38 */     String path = "";
/*    */ 
/* 40 */     if (pack != null) {
/* 41 */       String packName = pack.getName();
/*    */ 
/* 43 */       if ((packName.startsWith("java.")) || (packName.startsWith("javax."))) {
/* 44 */         throw new IllegalArgumentException("不要传送系统类！");
/*    */       }
/* 46 */       clsName = clsName.substring(packName.length() + 1);
/*    */ 
/* 48 */       if (packName.indexOf(".") < 0) {
/* 49 */         path = packName + "/";
/*    */       } else {
/* 51 */         int start = 0; int end = 0;
/* 52 */         end = packName.indexOf(".");
/* 53 */         while (end != -1) {
/* 54 */           path = path + packName.substring(start, end) + "/";
/* 55 */           start = end + 1;
/* 56 */           end = packName.indexOf(".", start);
/*    */         }
/* 58 */         path = path + packName.substring(start) + "/";
/*    */       }
/*    */     }
/*    */ 
/* 62 */     URL url = loader.getResource(path + clsName);
/*    */ 
/* 64 */     String realPath = url.getPath();
/*    */ 
/* 66 */     int pos = realPath.indexOf("file:");
/* 67 */     if (pos > -1) {
/* 68 */       realPath = realPath.substring(pos + 5);
/*    */     }
/* 70 */     pos = realPath.indexOf(path + clsName);
/* 71 */     realPath = realPath.substring(0, pos - 1);
/*    */ 
/* 73 */     if (realPath.endsWith("!")) {
/* 74 */       realPath = realPath.substring(0, realPath.lastIndexOf("/"));
/*    */     }
/*    */ 
/*    */     try
/*    */     {
/* 82 */       realPath = URLDecoder.decode(realPath, "utf-8");
/*    */     } catch (Exception e) {
/* 84 */       throw new RuntimeException(e);
/*    */     }
/* 86 */     return realPath;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.MyClassLoader
 * JD-Core Version:    0.6.2
 */