/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.ApplicationContextAware;
/*    */ 
/*    */ public class ApplicationContextUtil
/*    */   implements ApplicationContextAware
/*    */ {
/*    */   private static ApplicationContext context;
/*    */ 
/*    */   public void setApplicationContext(ApplicationContext context)
/*    */     throws BeansException
/*    */   {
/* 19 */     context = context;
/*    */   }
/*    */ 
/*    */   public static ApplicationContext getContext() {
/* 23 */     return context;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.ApplicationContextUtil
 * JD-Core Version:    0.6.2
 */