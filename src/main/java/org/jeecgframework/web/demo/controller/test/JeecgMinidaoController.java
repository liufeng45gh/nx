/*     */ package org.jeecgframework.web.demo.controller.test;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;
/*     */ import org.jeecgframework.web.demo.service.test.JeecgMinidaoServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/jeecgMinidaoController"})
/*     */ public class JeecgMinidaoController extends BaseController
/*     */ {
/*  39 */   private static final Logger logger = Logger.getLogger(JeecgMinidaoController.class);
/*     */ 
/*     */   @Autowired
/*     */   private JeecgMinidaoServiceI jeecgMinidaoService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"jeecgMinidao"})
/*     */   public ModelAndView jeecgMinidao(HttpServletRequest request)
/*     */   {
/*  54 */     return new ModelAndView("jeecg/demo/test/jeecgMinidaoList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(JeecgMinidaoEntity jeecgMinidao, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  68 */     List list = this.jeecgMinidaoService.listAll(jeecgMinidao, dataGrid.getPage(), dataGrid.getRows());
/*  69 */     Integer count = this.jeecgMinidaoService.getCount();
/*  70 */     dataGrid.setTotal(count.intValue());
/*  71 */     dataGrid.setResults(list);
/*  72 */     String total_salary = String.valueOf(this.jeecgMinidaoService.getSumSalary());
/*     */ 
/*  76 */     dataGrid.setFooter("salary:" + (total_salary.equalsIgnoreCase("null") ? "0.0" : total_salary) + ",age,email:合计");
/*  77 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(JeecgMinidaoEntity jeecgMinidao, HttpServletRequest request)
/*     */   {
/*  88 */     String message = null;
/*  89 */     AjaxJson j = new AjaxJson();
/*  90 */     jeecgMinidao = (JeecgMinidaoEntity)this.systemService.getEntity(JeecgMinidaoEntity.class, jeecgMinidao.getId());
/*  91 */     message = "Minidao例子删除成功";
/*  92 */     this.systemService.delete(jeecgMinidao);
/*  93 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */ 
/*  95 */     j.setMsg(message);
/*  96 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(JeecgMinidaoEntity jeecgMinidao, HttpServletRequest request)
/*     */   {
/* 109 */     String message = null;
/* 110 */     AjaxJson j = new AjaxJson();
/* 111 */     if (StringUtil.isNotEmpty(jeecgMinidao.getId())) {
/* 112 */       message = "Minidao例子更新成功";
/* 113 */       JeecgMinidaoEntity t = (JeecgMinidaoEntity)this.systemService.getEntity(JeecgMinidaoEntity.class, jeecgMinidao.getId());
/*     */       try {
/* 115 */         MyBeanUtils.copyBeanNotNull2Bean(jeecgMinidao, t);
/* 116 */         this.systemService.updateEntitie(t);
/* 117 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 119 */         e.printStackTrace();
/* 120 */         message = "Minidao例子更新失败";
/*     */       }
/*     */     } else {
/* 123 */       message = "Minidao例子添加成功";
/* 124 */       jeecgMinidao.setStatus("0");
/* 125 */       this.systemService.save(jeecgMinidao);
/* 126 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/* 128 */     j.setMsg(message);
/* 129 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(JeecgMinidaoEntity jeecgMinidao, HttpServletRequest req)
/*     */   {
/* 141 */     if (StringUtil.isNotEmpty(jeecgMinidao.getId())) {
/* 142 */       jeecgMinidao = (JeecgMinidaoEntity)this.systemService.getEntity(JeecgMinidaoEntity.class, jeecgMinidao.getId());
/* 143 */       req.setAttribute("jeecgMinidaoPage", jeecgMinidao);
/*     */     }
/* 145 */     return new ModelAndView("jeecg/demo/test/jeecgMinidao");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.JeecgMinidaoController
 * JD-Core Version:    0.6.2
 */