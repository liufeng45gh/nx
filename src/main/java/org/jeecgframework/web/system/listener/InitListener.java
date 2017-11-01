/*    */ package org.jeecgframework.web.system.listener;
/*    */ 
/*    */ import javax.servlet.ServletContextEvent;
/*    */ import javax.servlet.ServletContextListener;
/*    */ import org.jeecgframework.web.system.service.DynamicDataSourceServiceI;
/*    */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*    */ import org.jeecgframework.web.system.service.SystemService;
/*    */ import org.springframework.web.context.WebApplicationContext;
/*    */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*    */ 
/*    */ public class InitListener
/*    */   implements ServletContextListener
/*    */ {
/*    */   public void contextDestroyed(ServletContextEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void contextInitialized(ServletContextEvent event)
/*    */   {
/* 26 */     WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
/* 27 */     SystemService systemService = (SystemService)webApplicationContext.getBean("systemService");
/*    */ 
/* 29 */     MutiLangServiceI mutiLangService = (MutiLangServiceI)webApplicationContext.getBean("mutiLangService");
/* 30 */     DynamicDataSourceServiceI dynamicDataSourceService = (DynamicDataSourceServiceI)webApplicationContext.getBean("dynamicDataSourceService");
/*    */ 
/* 35 */     systemService.initAllTypeGroups();
/* 36 */     systemService.initAllTSIcons();
/*    */ 
/* 50 */     mutiLangService.initAllMutiLang();
/*    */ 
/* 55 */     dynamicDataSourceService.initDynamicDataSource();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.listener.InitListener
 * JD-Core Version:    0.6.2
 */