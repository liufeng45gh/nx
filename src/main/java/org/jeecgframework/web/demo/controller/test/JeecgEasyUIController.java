/*     */ package org.jeecgframework.web.demo.controller.test;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.web.demo.entity.test.JeecgJdbcEntity;
/*     */ import org.jeecgframework.web.demo.service.test.JeecgJdbcServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/jeecgEasyUIController"})
/*     */ public class JeecgEasyUIController extends BaseController
/*     */ {
/*  42 */   private static final Logger logger = Logger.getLogger(JeecgEasyUIController.class);
/*     */ 
/*     */   @Autowired
/*     */   private JeecgJdbcServiceI jeecgJdbcService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  71 */     JSONObject jObject = this.jeecgJdbcService.getDatagrid3(jeecgJdbc, dataGrid);
/*  72 */     responseDatagrid(response, jObject);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request)
/*     */   {
/*  84 */     String message = null;
/*  85 */     AjaxJson j = new AjaxJson();
/*     */ 
/*  87 */     String sql = "delete from jeecg_demo where id='" + jeecgJdbc.getId() + "'";
/*  88 */     this.jeecgJdbcService.executeSql(sql, new Object[0]);
/*     */ 
/*  90 */     message = "删除成功";
/*  91 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */ 
/*  93 */     j.setMsg(message);
/*  94 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request)
/*     */   {
/* 107 */     String message = null;
/* 108 */     AjaxJson j = new AjaxJson();
/* 109 */     if (StringUtil.isNotEmpty(jeecgJdbc.getId())) {
/* 110 */       message = "更新成功";
/* 111 */       JeecgJdbcEntity t = (JeecgJdbcEntity)this.jeecgJdbcService.get(JeecgJdbcEntity.class, jeecgJdbc.getId());
/*     */       try {
/* 113 */         MyBeanUtils.copyBeanNotNull2Bean(jeecgJdbc, t);
/* 114 */         this.jeecgJdbcService.saveOrUpdate(t);
/* 115 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 117 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 120 */       message = "添加成功";
/* 121 */       this.jeecgJdbcService.save(jeecgJdbc);
/* 122 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/*     */ 
/* 125 */     return j;
/*     */   }
/*     */ 
/*     */   public void responseDatagrid(HttpServletResponse response, JSONObject jObject)
/*     */   {
/* 130 */     response.setContentType("application/json");
/* 131 */     response.setHeader("Cache-Control", "no-store");
/* 132 */     PrintWriter pw = null;
/*     */     try {
/* 134 */       pw = response.getWriter();
/* 135 */       pw.write(jObject.toString());
/* 136 */       pw.flush();
/*     */     } catch (IOException e) {
/* 138 */       e.printStackTrace();
/*     */       try
/*     */       {
/* 141 */         pw.close(); } catch (Exception localException) {  } } finally { try { pw.close(); }
/*     */       catch (Exception localException1)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.JeecgEasyUIController
 * JD-Core Version:    0.6.2
 */