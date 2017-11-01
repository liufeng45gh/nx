/*    */ package org.jeecgframework.web.system.listener;
/*    */ 
/*    */ import javax.servlet.ServletContextEvent;
/*    */ import javax.servlet.ServletContextListener;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import javax.servlet.http.HttpSessionEvent;
/*    */ import javax.servlet.http.HttpSessionListener;
/*    */ import org.jeecgframework.web.system.manager.ClientManager;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*    */ 
/*    */ public class OnlineListener
/*    */   implements ServletContextListener, HttpSessionListener
/*    */ {
/* 21 */   private static ApplicationContext ctx = null;
/*    */ 
/*    */   public void sessionCreated(HttpSessionEvent httpSessionEvent)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
/*    */   {
/*    */     try
/*    */     {
/* 34 */       ClientManager.getInstance().removeClinet(httpSessionEvent.getSession().getId());
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/*    */     }
/*    */   }
/*    */ 
/*    */   public void contextInitialized(ServletContextEvent evt)
/*    */   {
/* 45 */     ctx = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
/*    */   }
/*    */ 
/*    */   public static ApplicationContext getCtx() {
/* 49 */     return ctx;
/*    */   }
/*    */ 
/*    */   public void contextDestroyed(ServletContextEvent paramServletContextEvent)
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.listener.OnlineListener
 * JD-Core Version:    0.6.2
 */