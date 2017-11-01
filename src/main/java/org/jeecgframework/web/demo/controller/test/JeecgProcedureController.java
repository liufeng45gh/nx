/*    */ package org.jeecgframework.web.demo.controller.test;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.jeecgframework.core.common.controller.BaseController;
/*    */ import org.jeecgframework.core.common.model.json.DataGrid;
/*    */ import org.jeecgframework.core.extend.sqlsearch.SqlGenerateUtil;
/*    */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgDemo;
/*    */ import org.jeecgframework.web.demo.service.test.JeecgProcedureServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/jeecgProcedureController"})
/*    */ public class JeecgProcedureController extends BaseController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private JeecgProcedureServiceI jeecgProcedureService;
/*    */ 
/*    */   @RequestMapping(params={"procedure"})
/*    */   public String procudure(HttpServletRequest request)
/*    */   {
/* 31 */     return "jeecg/demo/base/procedure/procedure";
/*    */   }
/*    */ 
/*    */   @RequestMapping(params={"datagrid"})
/*    */   public void datagrid(JeecgDemo demo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
/* 36 */     List dealFields = new ArrayList();
/* 37 */     String field = dataGrid.getField();
/* 38 */     if (field.length() > 0) field = field.substring(0, field.length() - 1);
/*    */ 
/* 40 */     String tableName = SqlGenerateUtil.generateTable(demo);
/* 41 */     StringBuffer dbFields = SqlGenerateUtil.generateDBFields(demo, field, dealFields);
/* 42 */     StringBuffer whereSql = SqlGenerateUtil.generateWhere(demo, request.getParameterMap());
/*    */ 
/* 44 */     List datas = null;
/*    */     try
/*    */     {
/* 47 */       datas = this.jeecgProcedureService.queryDataByProcedure(tableName, dbFields.toString(), whereSql.toString());
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/*    */     }
/* 52 */     response.setContentType("application/json");
/* 53 */     response.setHeader("Cache-Control", "no-store");
/* 54 */     PrintWriter pw = null;
/*    */     try {
/* 56 */       pw = response.getWriter();
/* 57 */       pw.write(TagUtil.getJsonByMap(dealFields, datas));
/*    */     } catch (IOException e) {
/* 59 */       e.printStackTrace();
/*    */       try
/*    */       {
/* 62 */         pw.close(); } catch (Exception localException1) {  } } finally { try { pw.close(); }
/*    */       catch (Exception localException2)
/*    */       {
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.JeecgProcedureController
 * JD-Core Version:    0.6.2
 */