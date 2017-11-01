/*     */ package org.jeecgframework.web.demo.controller.test;
/*     */ 
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.demo.entity.test.JeecgDemoCkfinderEntity;
/*     */ import org.jeecgframework.web.demo.service.test.JeecgDemoCkfinderServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/jeecgDemoCkfinderController"})
/*     */ public class JeecgDemoCkfinderController extends BaseController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private JeecgDemoCkfinderServiceI jeecgDemoCkfinderService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"jeecgDemoCkfinder"})
/*     */   public ModelAndView jeecgDemoCkfinder(HttpServletRequest request)
/*     */   {
/*  48 */     return new ModelAndView("jeecg/demo/test/jeecgDemoCkfinderList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(JeecgDemoCkfinderEntity jeecgDemoCkfinder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  64 */     CriteriaQuery cq = new CriteriaQuery(JeecgDemoCkfinderEntity.class, 
/*  65 */       dataGrid);
/*     */ 
/*  67 */     HqlGenerateUtil.installHql(cq, 
/*  68 */       jeecgDemoCkfinder, request.getParameterMap());
/*  69 */     this.jeecgDemoCkfinderService.getDataGridReturn(cq, true);
/*  70 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(JeecgDemoCkfinderEntity jeecgDemoCkfinder, HttpServletRequest request)
/*     */   {
/*  82 */     String message = null;
/*  83 */     AjaxJson j = new AjaxJson();
/*  84 */     jeecgDemoCkfinder = (JeecgDemoCkfinderEntity)this.systemService.getEntity(
/*  85 */       JeecgDemoCkfinderEntity.class, jeecgDemoCkfinder.getId());
/*  86 */     message = "ckeditor+ckfinder例子删除成功";
/*  87 */     this.jeecgDemoCkfinderService.delete(jeecgDemoCkfinder);
/*  88 */     this.systemService.addLog(message, Globals.Log_Type_DEL, 
/*  89 */       Globals.Log_Leavel_INFO);
/*     */ 
/*  91 */     j.setMsg(message);
/*  92 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(JeecgDemoCkfinderEntity jeecgDemoCkfinder, HttpServletRequest request)
/*     */   {
/* 105 */     String message = null;
/* 106 */     AjaxJson j = new AjaxJson();
/* 107 */     if (StringUtil.isNotEmpty(jeecgDemoCkfinder.getId())) {
/* 108 */       message = "ckeditor+ckfinder例子更新成功";
/* 109 */       JeecgDemoCkfinderEntity t = (JeecgDemoCkfinderEntity)this.jeecgDemoCkfinderService.get(
/* 110 */         JeecgDemoCkfinderEntity.class, jeecgDemoCkfinder.getId());
/*     */       try {
/* 112 */         MyBeanUtils.copyBeanNotNull2Bean(jeecgDemoCkfinder, t);
/* 113 */         this.jeecgDemoCkfinderService.saveOrUpdate(t);
/* 114 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, 
/* 115 */           Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 117 */         e.printStackTrace();
/* 118 */         message = "ckeditor+ckfinder例子更新失败";
/*     */       }
/*     */     } else {
/* 121 */       message = "ckeditor+ckfinder例子添加成功";
/* 122 */       this.jeecgDemoCkfinderService.save(jeecgDemoCkfinder);
/* 123 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, 
/* 124 */         Globals.Log_Leavel_INFO);
/*     */     }
/* 126 */     j.setMsg(message);
/* 127 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(JeecgDemoCkfinderEntity jeecgDemoCkfinder, HttpServletRequest req)
/*     */   {
/* 138 */     if (StringUtil.isNotEmpty(jeecgDemoCkfinder.getId())) {
/* 139 */       jeecgDemoCkfinder = (JeecgDemoCkfinderEntity)this.jeecgDemoCkfinderService.getEntity(
/* 140 */         JeecgDemoCkfinderEntity.class, jeecgDemoCkfinder.getId());
/* 141 */       req.setAttribute("jeecgDemoCkfinderPage", jeecgDemoCkfinder);
/*     */     }
/* 143 */     return new ModelAndView("jeecg/demo/test/jeecgDemoCkfinder");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"preview"})
/*     */   public ModelAndView preview(JeecgDemoCkfinderEntity jeecgDemoCkfinder, HttpServletRequest req)
/*     */   {
/* 154 */     if (StringUtil.isNotEmpty(jeecgDemoCkfinder.getId())) {
/* 155 */       jeecgDemoCkfinder = (JeecgDemoCkfinderEntity)this.jeecgDemoCkfinderService.getEntity(
/* 156 */         JeecgDemoCkfinderEntity.class, jeecgDemoCkfinder.getId());
/* 157 */       req.setAttribute("ckfinderPreview", jeecgDemoCkfinder);
/*     */     }
/* 159 */     return new ModelAndView("jeecg/demo/test/jeecgDemoCkfinderPreview");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.JeecgDemoCkfinderController
 * JD-Core Version:    0.6.2
 */