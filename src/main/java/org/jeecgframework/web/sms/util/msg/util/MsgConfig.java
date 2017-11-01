/*    */ package org.jeecgframework.web.sms.util.msg.util;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class MsgConfig
/*    */ {
/* 13 */   private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("MsgConfig");
/*    */ 
/*    */   public static String get(String key)
/*    */   {
/* 23 */     return resourceBundle.getString(key);
/*    */   }
/*    */ 
/*    */   public static String getIsmgIp()
/*    */   {
/* 32 */     return get("ismgIp");
/*    */   }
/*    */ 
/*    */   public static int getIsmgPort()
/*    */   {
/* 41 */     return Integer.parseInt(get("ismgPort"));
/*    */   }
/*    */ 
/*    */   public static String getSpId()
/*    */   {
/* 50 */     return get("spId");
/*    */   }
/*    */ 
/*    */   public static String getSpCode()
/*    */   {
/* 59 */     return get("spCode");
/*    */   }
/*    */ 
/*    */   public static String getSpSharedSecret()
/*    */   {
/* 68 */     return get("sharedSecret");
/*    */   }
/*    */ 
/*    */   public static int getConnectCount()
/*    */   {
/* 77 */     return Integer.parseInt(get("connectCount"));
/*    */   }
/*    */ 
/*    */   public static int getTimeOut()
/*    */   {
/* 86 */     return Integer.parseInt(get("timeOut"));
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.util.MsgConfig
 * JD-Core Version:    0.6.2
 */