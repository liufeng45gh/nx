/*     */ package org.jeecgframework.web.system.controller.core;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.imageio.stream.FileImageOutputStream;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.web.system.pojo.base.TSIcon;
/*     */ 
/*     */ public class IconImageUtil
/*     */ {
/*     */   public static void convertDataGrid(DataGrid dataGrid, HttpServletRequest request)
/*     */   {
/*  24 */     String fileDirName = request.getSession().getServletContext().getRealPath("") + File.separator + "temp";
/*  25 */     delFolder(fileDirName);
/*  26 */     File fileDir = new File(fileDirName);
/*  27 */     if (!fileDir.exists())
/*  28 */       fileDir.mkdirs();
/*     */     try
/*     */     {
/*  31 */       List list = dataGrid.getResults();
/*  32 */       for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
/*  33 */         TSIcon icon = (TSIcon)obj;
/*  34 */         String fileName = "icon" + UUID.randomUUID() + "." + icon.getExtend();
/*  35 */         File tempFile = new File(fileDirName + File.separator + fileName);
/*  36 */         if (icon.getIconContent() != null) {
/*  37 */           byte2image(icon.getIconContent(), tempFile);
/*  38 */           icon.setIconPath("temp/" + fileName);
/*     */         } }
/*     */     }
/*     */     catch (Exception e) {
/*  42 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void byte2image(byte[] data, File file)
/*     */   {
/*  48 */     if (data.length < 3) return;
/*  49 */     FileImageOutputStream imageOutput = null;
/*  50 */     String fileName = null;
/*     */     try {
/*  52 */       imageOutput = new FileImageOutputStream(file);
/*  53 */       imageOutput.write(data, 0, data.length);
/*     */     } catch (Exception ex) {
/*  55 */       ex.printStackTrace();
/*     */       try
/*     */       {
/*  58 */         imageOutput.close();
/*     */       } catch (IOException e) {
/*  60 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  58 */         imageOutput.close();
/*     */       } catch (IOException e) {
/*  60 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void delFolder(String folderPath)
/*     */   {
/*     */     try
/*     */     {
/*  71 */       delAllFile(folderPath);
/*  72 */       File folder = new File(folderPath);
/*  73 */       folder.delete();
/*     */     } catch (Exception e) {
/*  75 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean delAllFile(String path)
/*     */   {
/*  84 */     boolean flag = false;
/*  85 */     File file = new File(path);
/*  86 */     if (!file.exists()) {
/*  87 */       return flag;
/*     */     }
/*  89 */     if (!file.isDirectory()) {
/*  90 */       return flag;
/*     */     }
/*  92 */     String[] tempList = file.list();
/*  93 */     File temp = null;
/*  94 */     for (int i = 0; i < tempList.length; i++) {
/*  95 */       if (path.endsWith(File.separator))
/*  96 */         temp = new File(path + tempList[i]);
/*     */       else {
/*  98 */         temp = new File(path + File.separator + tempList[i]);
/*     */       }
/* 100 */       if (temp.isFile()) {
/* 101 */         temp.delete();
/*     */       }
/* 103 */       if (temp.isDirectory()) {
/* 104 */         delAllFile(path + "/" + tempList[i]);
/* 105 */         delFolder(path + "/" + tempList[i]);
/* 106 */         flag = true;
/*     */       }
/*     */     }
/* 109 */     return flag;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.IconImageUtil
 * JD-Core Version:    0.6.2
 */