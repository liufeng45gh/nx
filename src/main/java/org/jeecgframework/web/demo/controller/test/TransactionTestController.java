/*    */ package org.jeecgframework.web.demo.controller.test;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*    */ import org.jeecgframework.core.common.model.json.DataGrid;
/*    */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgDemo;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgOrderCustomEntity;
/*    */ import org.jeecgframework.web.demo.service.test.TransactionTestServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/transactionTestController"})
/*    */ public class TransactionTestController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private TransactionTestServiceI transactionTestService;
/*    */ 
/*    */   @RequestMapping(params={"showPage"})
/*    */   public String showPage()
/*    */   {
/* 33 */     return "jeecg/demo/test/transactionTestList";
/*    */   }
/*    */   @RequestMapping(params={"add"})
/*    */   public String add() {
/* 37 */     return "jeecg/demo/test/transactionTest";
/*    */   }
/*    */ 
/*    */   @RequestMapping(params={"getDataCount"})
/*    */   @ResponseBody
/*    */   public void getDataCount(HttpServletResponse response, DataGrid dataGrid)
/*    */   {
/* 46 */     Map dataMap = this.transactionTestService.getCounts();
/* 47 */     List dataList = new ArrayList();
/* 48 */     dataList.add(dataMap);
/* 49 */     dataGrid.setResults(dataList);
/* 50 */     TagUtil.datagrid(response, dataGrid);
/*    */   }
/*    */ 
/*    */   @RequestMapping(params={"insertData"})
/*    */   @ResponseBody
/*    */   public AjaxJson insertData(JeecgMinidaoEntity entity, JeecgDemo demo, JeecgOrderCustomEntity customEntity, boolean rollback)
/*    */   {
/* 64 */     AjaxJson j = new AjaxJson();
/*    */     try {
/* 66 */       Map dataMap = this.transactionTestService.insertData(entity, demo, customEntity, rollback);
/* 67 */       j.setMsg("保存成功！");
/* 68 */       j.setSuccess(true);
/*    */     } catch (Exception e) {
/* 70 */       j.setMsg("保存失败，数据回滚！");
/* 71 */       j.setSuccess(true);
/*    */     }
/* 73 */     return j;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.TransactionTestController
 * JD-Core Version:    0.6.2
 */