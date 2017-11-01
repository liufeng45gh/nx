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
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.demo.entity.test.JeecgJdbcEntity;
/*     */ import org.jeecgframework.web.demo.service.test.JeecgJdbcServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/jeecgJdbcController"})
/*     */ public class JeecgJdbcController extends BaseController
/*     */ {
/*  42 */   private static final Logger logger = Logger.getLogger(JeecgJdbcController.class);
/*     */ 
/*     */   @Autowired
/*     */   private JeecgJdbcServiceI jeecgJdbcService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"jeecgJdbc"})
/*     */   public ModelAndView jeecgJdbc(HttpServletRequest request)
/*     */   {
/*  57 */     return new ModelAndView("jeecg/demo/test/jeecgJdbcList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  81 */     JSONObject jObject = this.jeecgJdbcService.getDatagrid3(jeecgJdbc, dataGrid);
/*  82 */     responseDatagrid(response, jObject);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request)
/*     */   {
/*  94 */     String message = null;
/*  95 */     AjaxJson j = new AjaxJson();
/*     */ 
/*  97 */     String sql = "delete from jeecg_demo where id='" + jeecgJdbc.getId() + "'";
/*  98 */     this.jeecgJdbcService.executeSql(sql, new Object[0]);
/*     */ 
/* 100 */     message = "删除成功";
/* 101 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */ 
/* 103 */     j.setMsg(message);
/* 104 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request)
/*     */   {
/* 117 */     String message = null;
/* 118 */     AjaxJson j = new AjaxJson();
/* 119 */     if (StringUtil.isNotEmpty(jeecgJdbc.getId())) {
/* 120 */       message = "更新成功";
/* 121 */       JeecgJdbcEntity t = (JeecgJdbcEntity)this.jeecgJdbcService.get(JeecgJdbcEntity.class, jeecgJdbc.getId());
/*     */       try {
/* 123 */         MyBeanUtils.copyBeanNotNull2Bean(jeecgJdbc, t);
/* 124 */         this.jeecgJdbcService.saveOrUpdate(t);
/* 125 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 127 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 130 */       message = "添加成功";
/* 131 */       this.jeecgJdbcService.save(jeecgJdbc);
/* 132 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/*     */ 
/* 135 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(JeecgJdbcEntity jeecgJdbc, HttpServletRequest req)
/*     */   {
/* 145 */     if (StringUtil.isNotEmpty(jeecgJdbc.getId())) {
/* 146 */       jeecgJdbc = (JeecgJdbcEntity)this.jeecgJdbcService.getEntity(JeecgJdbcEntity.class, jeecgJdbc.getId());
/* 147 */       req.setAttribute("jeecgJdbcPage", jeecgJdbc);
/*     */     }
/* 149 */     return new ModelAndView("jeecg/demo/test/jeecgJdbc");
/*     */   }
/*     */ 
/*     */   public void responseDatagrid(HttpServletResponse response, JSONObject jObject) {
/* 153 */     response.setContentType("application/json");
/* 154 */     response.setHeader("Cache-Control", "no-store");
/* 155 */     PrintWriter pw = null;
/*     */     try {
/* 157 */       pw = response.getWriter();
/* 158 */       pw.write(jObject.toString());
/* 159 */       pw.flush();
/*     */     } catch (IOException e) {
/* 161 */       e.printStackTrace();
/*     */       try
/*     */       {
/* 164 */         pw.close(); } catch (Exception localException) {  } } finally { try { pw.close();
/*     */       } catch (Exception localException1)
/*     */       {
/*     */       } }
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"dictParameter"})
/*     */   public String dictParameter() {
/* 172 */     return "jeecg/demo/base/jdbc/jdbc-list";
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"listAllDictParaByJdbc"})
/*     */   public void listAllDictParaByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 183 */     this.jeecgJdbcService.listAllByJdbc(dataGrid);
/* 184 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.JeecgJdbcController
 * JD-Core Version:    0.6.2
 */