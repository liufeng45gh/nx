/*    */ package org.jeecgframework.web.demo.controller.test;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*    */ import org.jeecgframework.core.common.model.json.DataGrid;
/*    */ import org.jeecgframework.core.constant.Globals;
/*    */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*    */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*    */ import org.jeecgframework.web.system.controller.core.UserController;
/*    */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*    */ import org.jeecgframework.web.system.service.SystemService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/userNoPageController"})
/*    */ public class UserNoPageController
/*    */ {
/* 32 */   private static final Logger logger = Logger.getLogger(UserController.class);
/*    */   private SystemService systemService;
/*    */ 
/*    */   @Autowired
/*    */   public void setSystemService(SystemService systemService)
/*    */   {
/* 38 */     this.systemService = systemService;
/*    */   }
/*    */ 
/*    */   @RequestMapping(params={"user"})
/*    */   public String user(HttpServletRequest request)
/*    */   {
/* 47 */     String departsReplace = "";
/* 48 */     request.setAttribute("departsReplace", departsReplace);
/* 49 */     return "jeecg/demo/base/nopage/userList";
/*    */   }
/*    */   @RequestMapping(params={"datagridNoPage"})
/*    */   public void datagridNoPage(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
/* 53 */     CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
/*    */ 
/* 55 */     HqlGenerateUtil.installHql(cq, user);
/*    */ 
/* 57 */     Short[] userstate = { Globals.User_Normal, Globals.User_ADMIN };
/* 58 */     cq.in("status", userstate);
/* 59 */     cq.add();
/* 60 */     this.systemService.getDataGridReturn(cq, false);
/* 61 */     TagUtil.datagrid(response, dataGrid);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.UserNoPageController
 * JD-Core Version:    0.6.2
 */