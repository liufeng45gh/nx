/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class IpUtil
/*    */ {
/*    */   public static String getIpAddr(HttpServletRequest request)
/*    */   {
/* 13 */     String ip = request.getHeader("x-forwarded-for");
/* 14 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 15 */       ip = request.getHeader("Proxy-Client-IP");
/*    */     }
/* 17 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 18 */       ip = request.getHeader("WL-Proxy-Client-IP");
/*    */     }
/* 20 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 21 */       ip = request.getRemoteAddr();
/*    */     }
/* 23 */     if (ip.equals("0:0:0:0:0:0:0:1")) {
/* 24 */       ip = "本地";
/*    */     }
/* 26 */     return ip;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.IpUtil
 * JD-Core Version:    0.6.2
 */