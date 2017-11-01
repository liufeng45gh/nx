/*    */ package org.jeecgframework.web.cgform.interceptors;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.jeecgframework.core.interceptors.AuthInterceptor;
/*    */ import org.jeecgframework.core.util.ResourceUtil;
/*    */ import org.jeecgframework.core.util.StringUtil;
/*    */ import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.servlet.HandlerInterceptor;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ public class CgFormVersionInterceptor
/*    */   implements HandlerInterceptor
/*    */ {
/*    */   private List<String> includeUrls;
/* 26 */   private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
/*    */ 
/*    */   @Autowired
/*    */   private CgFormFieldServiceI cgFormFieldService;
/*    */ 
/*    */   public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
/* 33 */     String requestPath = ResourceUtil.getRequestPath(arg0);
/* 34 */     if (!this.includeUrls.contains(requestPath)) {
/* 35 */       return;
/*    */     }
/*    */ 
/* 38 */     String formId = arg0.getParameter("formId");
/* 39 */     if (StringUtil.isEmpty(formId))
/* 40 */       return;
/*    */     try
/*    */     {
/* 43 */       this.cgFormFieldService.updateVersion(formId);
/*    */     } catch (Exception e) {
/* 45 */       e.printStackTrace();
/* 46 */       logger.debug(e.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2)
/*    */     throws Exception
/*    */   {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   public List<String> getIncludeUrls() {
/* 63 */     return this.includeUrls;
/*    */   }
/*    */ 
/*    */   public void setIncludeUrls(List<String> includeUrls) {
/* 67 */     this.includeUrls = includeUrls;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.interceptors.CgFormVersionInterceptor
 * JD-Core Version:    0.6.2
 */