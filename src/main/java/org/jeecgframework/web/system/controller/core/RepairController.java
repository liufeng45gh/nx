/*    */ package org.jeecgframework.web.system.controller.core;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ import org.jeecgframework.core.common.controller.BaseController;
/*    */ import org.jeecgframework.web.system.service.RepairService;
/*    */ import org.jeecgframework.web.system.service.SystemService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/repairController"})
/*    */ public class RepairController extends BaseController
/*    */ {
/* 28 */   private static final Logger logger = Logger.getLogger(RepairController.class);
/*    */   private SystemService systemService;
/*    */   private RepairService repairService;
/*    */ 
/*    */   @Autowired
/*    */   public void setRepairService(RepairService repairService)
/*    */   {
/* 37 */     this.repairService = repairService;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setSystemService(SystemService systemService) {
/* 42 */     this.systemService = systemService;
/*    */   }
/*    */ 
/*    */   @RequestMapping(params={"repair"})
/*    */   public ModelAndView repair()
/*    */   {
/* 50 */     this.repairService.deleteAndRepair();
/* 51 */     this.systemService.initAllTypeGroups();
/* 52 */     return new ModelAndView("login/login");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.RepairController
 * JD-Core Version:    0.6.2
 */