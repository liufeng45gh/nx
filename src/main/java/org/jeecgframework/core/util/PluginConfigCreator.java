/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PluginConfigCreator
/*    */ {
/*    */   public void print(String path)
/*    */   {
/* 17 */     List list = getFileList(path);
/* 18 */     if (list == null) {
/* 19 */       return;
/*    */     }
/* 21 */     int length = list.size();
/* 22 */     for (int i = 0; i < length; i++) {
/* 23 */       String result = "";
/* 24 */       String thePath = getFormatPath(getString(list.get(i)));
/* 25 */       File file = new File(thePath);
/* 26 */       if (file.isDirectory()) {
/* 27 */         String fileName = file.getName();
/* 28 */         if (fileName.indexOf("_") < 0) {
/* 29 */           print(thePath);
/*    */         }
/*    */         else {
/* 32 */           String[] filenames = fileName.split("_");
/* 33 */           String filename1 = filenames[0];
/* 34 */           String filename2 = filenames[1];
/* 35 */           result = filename1 + "," + filename2 + ",file:/" + path + "/" + fileName + "//,4,false";
/* 36 */           LogUtil.info(result);
/*    */         } } else if (file.isFile()) {
/* 38 */         String fileName = file.getName();
/* 39 */         if (fileName.indexOf("_") >= 0)
/*    */         {
/* 42 */           int last = fileName.lastIndexOf("_");
/* 43 */           String filename1 = fileName.substring(0, last);
/* 44 */           String filename2 = fileName.substring(last + 1, fileName.length() - 4);
/* 45 */           result = filename1 + "," + filename2 + ",file:/" + path + "/" + fileName + ",4,false";
/* 46 */           LogUtil.info(result);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/* 52 */   public List<String> getFileList(String path) { path = getFormatPath(path);
/* 53 */     path = path + "/";
/* 54 */     File filePath = new File(path);
/* 55 */     if (!filePath.isDirectory()) {
/* 56 */       return null;
/*    */     }
/* 58 */     String[] filelist = filePath.list();
/* 59 */     List filelistFilter = new ArrayList();
/* 60 */     for (int i = 0; i < filelist.length; i++) {
/* 61 */       String tempfilename = getFormatPath(path + filelist[i]);
/* 62 */       filelistFilter.add(tempfilename);
/*    */     }
/* 64 */     return filelistFilter; }
/*    */ 
/*    */   public String getString(Object object)
/*    */   {
/* 68 */     if (object == null) {
/* 69 */       return "";
/*    */     }
/* 71 */     return String.valueOf(object);
/*    */   }
/*    */ 
/*    */   public String getFormatPath(String path) {
/* 75 */     path = path.replaceAll("////", "/");
/* 76 */     path = path.replaceAll("//", "/");
/* 77 */     return path;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 82 */     String plugin = "D:\\worksoft\\MyEclipse\\myplugins\\svn";
/* 83 */     new PluginConfigCreator().print(plugin);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.PluginConfigCreator
 * JD-Core Version:    0.6.2
 */