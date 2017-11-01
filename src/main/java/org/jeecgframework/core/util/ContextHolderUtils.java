/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.springframework.web.context.request.RequestContextHolder;
/*    */ import org.springframework.web.context.request.ServletRequestAttributes;
/*    */ 
/*    */ public class ContextHolderUtils
/*    */ {
/*    */   public static HttpServletRequest getRequest()
/*    */   {
/* 22 */     HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
/* 23 */     return request;
/*    */   }
/*    */ 
/*    */   public static HttpSession getSession()
/*    */   {
/* 32 */     HttpSession session = getRequest().getSession();
/* 33 */     return session;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.ContextHolderUtils
 * JD-Core Version:    0.6.2
 */