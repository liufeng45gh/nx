/*    */ package ssb.warmline.business.utils;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class PropertiesUtil
/*    */ {
/*    */   public static String getProperties()
/*    */   {
/*  8 */     Properties prop = new Properties();
/*    */     try {
/* 10 */       prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("sysConfig.properties"));
/*    */     } catch (IOException e) {
/* 12 */       e.printStackTrace();
/*    */     }
/* 14 */     String id = prop.getProperty("zuopengfeiId");
/* 15 */     return id;
/*    */   }
/*    */   public static String getPropertie() {
/* 18 */     Properties prop = new Properties();
/*    */     try {
/* 20 */       prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("sysConfig.properties"));
/*    */     } catch (IOException e) {
/* 22 */       e.printStackTrace();
/*    */     }
/* 24 */     String id = prop.getProperty("zhangfengId");
/* 25 */     return id;
/*    */   }
/*    */   public static String getPropertieIsOnline() {
/* 28 */     Properties prop = new Properties();
/*    */     try {
/* 30 */       prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("sysConfig.properties"));
/*    */     } catch (IOException e) {
/* 32 */       e.printStackTrace();
/*    */     }
/* 34 */     String isOnline = prop.getProperty("isOnline");
/* 35 */     return isOnline;
/*    */   }
/*    */   public static String getProperties(String key) {
/* 38 */     Properties prop = new Properties();
/*    */     try {
/* 40 */       prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("sysConfig.properties"));
/*    */     } catch (IOException e) {
/* 42 */       e.printStackTrace();
/*    */     }
/* 44 */     return prop.getProperty(key);
/*    */   }
/*    */   public static void main(String[] args) {
/* 47 */     System.out.println(getProperties("downloadUrl"));
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.utils.PropertiesUtil
 * JD-Core Version:    0.6.2
 */