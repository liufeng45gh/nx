/*    */ package ssb.warmline.business.commons.utils;
/*    */ 
/*    */ import java.net.InetAddress;
/*    */ import java.net.UnknownHostException;
/*    */ import java.util.Map;
/*    */ import java.util.ResourceBundle;
/*    */ import javax.servlet.http.Cookie;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import org.springframework.web.util.WebUtils;
/*    */ 
/*    */ public final class WebUtil
/*    */ {
/*    */   public static final String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue)
/*    */   {
/* 31 */     Cookie cookie = WebUtils.getCookie(request, cookieName);
/*    */ 
/* 33 */     if (cookie == null) {
/* 34 */       return defaultValue;
/*    */     }
/* 36 */     return cookie.getValue();
/*    */   }
/*    */ 
/*    */   public static final void setCookieValue(HttpServletResponse response, String cookieName, String value) {
/* 40 */     Cookie cookie = new Cookie(cookieName, value);
/* 41 */     response.addCookie(new Cookie(cookieName, value));
/* 42 */     cookie.setMaxAge(31536000);
/*    */   }
/*    */ 
/*    */   public static final String getApplicationResource(String key, HttpServletRequest request)
/*    */   {
/* 54 */     ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
/* 55 */     return resourceBundle.getString(key);
/*    */   }
/*    */ 
/*    */   public static final Map<String, Object> getParameterMap(HttpServletRequest request)
/*    */   {
/* 65 */     return WebUtils.getParametersStartingWith(request, null);
/*    */   }
/*    */ 
/*    */   public static final String getHost(HttpServletRequest request)
/*    */   {
/* 70 */     String ip = request.getRemoteAddr();
/*    */ 
/* 72 */     if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
/* 73 */       ip = request.getHeader("Proxy-Client-IP");
/*    */     }
/* 75 */     if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
/* 76 */       ip = request.getHeader("WL-Proxy-Client-IP");
/*    */     }
/* 78 */     if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
/* 79 */       ip = request.getHeader("X-Real-IP");
/*    */     }
/* 81 */     if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
/* 82 */       ip = request.getHeader("X-Forwarded-For");
/*    */     }
/* 84 */     if ("127.0.0.1".equals(ip)) {
/* 85 */       InetAddress inet = null;
/*    */       try {
/* 87 */         inet = InetAddress.getLocalHost();
/*    */       } catch (UnknownHostException e) {
/* 89 */         e.printStackTrace();
/*    */       }
/* 91 */       ip = inet.getHostAddress();
/*    */     }
/*    */ 
/* 94 */     if ((ip != null) && (ip.length() > 15) && 
/* 95 */       (ip.indexOf(",") > 0)) {
/* 96 */       ip = ip.substring(0, ip.indexOf(","));
/*    */     }
/*    */ 
/* 99 */     return ip;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.WebUtil
 * JD-Core Version:    0.6.2
 */