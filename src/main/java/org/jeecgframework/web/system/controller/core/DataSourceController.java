/*    */ package org.jeecgframework.web.system.controller.core;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ import org.jeecgframework.core.common.controller.BaseController;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/dataSourceController"})
/*    */ public class DataSourceController extends BaseController
/*    */ {
/* 24 */   private static final Logger logger = Logger.getLogger(DataSourceController.class);
/*    */ 
/*    */   @RequestMapping(params={"goDruid"})
/*    */   public ModelAndView goDruid()
/*    */   {
/* 33 */     return new ModelAndView("/system/druid/index");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.DataSourceController
 * JD-Core Version:    0.6.2
 */