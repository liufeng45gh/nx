/*    */ package org.jeecgframework.web.system.listener;
/*    */ 
/*    */ import com.ckfinder.connector.configuration.Configuration;
/*    */ import javax.servlet.ServletConfig;
/*    */ import javax.servlet.ServletContext;
/*    */ import org.jeecgframework.core.util.ResourceUtil;
/*    */ 
/*    */ public class CkfinderConfiguration extends Configuration
/*    */ {
/* 19 */   String path = "";
/*    */ 
/*    */   public CkfinderConfiguration(ServletConfig servletConfig) {
/* 22 */     super(servletConfig);
/* 23 */     this.path = servletConfig.getServletContext().getContextPath();
/*    */   }
/*    */ 
/*    */   public void init() throws Exception
/*    */   {
/* 28 */     super.init();
/* 29 */     String files = ResourceUtil.getConfigByName("ck.userfiles");
/* 30 */     if (files.contains("http://"))
/* 31 */       this.baseURL = files;
/*    */     else
/* 33 */       this.baseURL = (this.path + "/" + files + "/");
/* 34 */     this.baseDir = ResourceUtil.getConfigByName("ck.baseDir");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.listener.CkfinderConfiguration
 * JD-Core Version:    0.6.2
 */