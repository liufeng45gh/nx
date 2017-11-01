/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class PropertiesUtil
/*     */ {
/*  16 */   private String properiesName = "";
/*     */ 
/*     */   public PropertiesUtil() {
/*     */   }
/*     */ 
/*     */   public PropertiesUtil(String fileName) {
/*  22 */     this.properiesName = fileName;
/*     */   }
/*     */   public String readProperty(String key) {
/*  25 */     String value = "";
/*  26 */     InputStream is = null;
/*     */     try {
/*  28 */       is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
/*  29 */         this.properiesName);
/*  30 */       Properties p = new Properties();
/*  31 */       p.load(is);
/*  32 */       value = p.getProperty(key);
/*     */     }
/*     */     catch (IOException e) {
/*  35 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  38 */         is.close();
/*     */       }
/*     */       catch (IOException ex) {
/*  41 */         ex.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  38 */         is.close();
/*     */       }
/*     */       catch (IOException e) {
/*  41 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  44 */     return value;
/*     */   }
/*     */ 
/*     */   public Properties getProperties() {
/*  48 */     Properties p = new Properties();
/*  49 */     InputStream is = null;
/*     */     try {
/*  51 */       is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
/*  52 */         this.properiesName);
/*  53 */       p.load(is);
/*     */     }
/*     */     catch (IOException e) {
/*  56 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  59 */         is.close();
/*     */       }
/*     */       catch (IOException ex) {
/*  62 */         ex.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  59 */         is.close();
/*     */       }
/*     */       catch (IOException e) {
/*  62 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  65 */     return p;
/*     */   }
/*     */ 
/*     */   public void writeProperty(String key, String value) {
/*  69 */     InputStream is = null;
/*  70 */     OutputStream os = null;
/*  71 */     Properties p = new Properties();
/*     */     try {
/*  73 */       is = new FileInputStream(this.properiesName);
/*  74 */       p.load(is);
/*  75 */       os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(this.properiesName).getFile());
/*     */ 
/*  77 */       p.setProperty(key, value);
/*  78 */       p.store(os, key);
/*  79 */       os.flush();
/*  80 */       os.close();
/*     */     }
/*     */     catch (Exception e) {
/*  83 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  86 */         if (is != null)
/*  87 */           is.close();
/*  88 */         if (os != null)
/*  89 */           os.close();
/*     */       }
/*     */       catch (IOException ex) {
/*  92 */         ex.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  86 */         if (is != null)
/*  87 */           is.close();
/*  88 */         if (os != null)
/*  89 */           os.close();
/*     */       }
/*     */       catch (IOException e) {
/*  92 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  99 */     PropertiesUtil p = new PropertiesUtil("sysConfig.properties");
/* 100 */     p.writeProperty("namess", "wang");
/* 101 */     LogUtil.info(p.readProperty("namess"));
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.PropertiesUtil
 * JD-Core Version:    0.6.2
 */