/*    */ package org.jeecgframework.core.interceptors;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.web.servlet.HandlerInterceptor;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ public class EncodingInterceptor
/*    */   implements HandlerInterceptor
/*    */ {
/*    */   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ 
/*    */   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
/*    */     throws Exception
/*    */   {
/* 32 */     request.setCharacterEncoding("UTF-8");
/* 33 */     response.setCharacterEncoding("UTF-8");
/* 34 */     return true;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.interceptors.EncodingInterceptor
 * JD-Core Version:    0.6.2
 */