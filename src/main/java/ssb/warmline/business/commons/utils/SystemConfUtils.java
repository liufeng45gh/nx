/*    */ package ssb.warmline.business.commons.utils;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class SystemConfUtils
/*    */ {
/* 14 */   private static final Properties SYSTEM_PROPERTIES = new Properties();
/*    */ 
/*    */   static {
/* 17 */     InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("system-conf.properties");
/*    */     try {
/* 19 */       SYSTEM_PROPERTIES.load(is);
/*    */     } catch (IOException e) {
/* 21 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String getSystemDomainName()
/*    */   {
/* 30 */     return SYSTEM_PROPERTIES.getProperty("SYSTEM_DOMAIN_NAME");
/*    */   }
/*    */ 
/*    */   public static String getUploadBasePath()
/*    */   {
/* 43 */     return SYSTEM_PROPERTIES.getProperty("FILE_UPLOAD_BASE_PATH");
/*    */   }
/*    */ 
/*    */   public static String getUserName()
/*    */   {
/* 50 */     return SYSTEM_PROPERTIES.getProperty("username");
/*    */   }
/*    */ 
/*    */   public static String getPassword()
/*    */   {
/* 57 */     return SYSTEM_PROPERTIES.getProperty("password");
/*    */   }
/*    */ 
/*    */   public static String getProductId()
/*    */   {
/* 64 */     return SYSTEM_PROPERTIES.getProperty("productid");
/*    */   }
/*    */ 
/*    */   public static String getActivityCode()
/*    */   {
/* 72 */     return SYSTEM_PROPERTIES.getProperty("activity_code");
/*    */   }
/*    */ 
/*    */   public static String getEmsCost()
/*    */   {
/* 78 */     return SYSTEM_PROPERTIES.getProperty("ems_cost");
/*    */   }
/*    */ 
/*    */   public static String getRedMax()
/*    */   {
/* 84 */     return SYSTEM_PROPERTIES.getProperty("red_max");
/*    */   }
/*    */ 
/*    */   public static String getRedMin()
/*    */   {
/* 90 */     return SYSTEM_PROPERTIES.getProperty("red_min");
/*    */   }
/*    */ 
/*    */   public static String getPropertity(String proName)
/*    */   {
/* 98 */     return SYSTEM_PROPERTIES.getProperty(proName);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.SystemConfUtils
 * JD-Core Version:    0.6.2
 */