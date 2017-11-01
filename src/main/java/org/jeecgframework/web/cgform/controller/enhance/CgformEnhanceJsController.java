/*     */ package org.jeecgframework.web.cgform.controller.enhance;
/*     */ 
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
/*     */ import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJsServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/cgformEnhanceJsController"})
/*     */ public class CgformEnhanceJsController extends BaseController
/*     */ {
/*  41 */   private static final Logger logger = Logger.getLogger(CgformEnhanceJsController.class);
/*     */ 
/*     */   @Autowired
/*     */   private CgformEnhanceJsServiceI cgformenhanceJsService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"cgformenhanceJs"})
/*     */   public ModelAndView cgformenhanceJs(HttpServletRequest request)
/*     */   {
/*  56 */     return new ModelAndView("jeecg/cgform/enhance/cgformenhanceJsList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(CgformEnhanceJsEntity cgformenhanceJs, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  71 */     CriteriaQuery cq = new CriteriaQuery(CgformEnhanceJsEntity.class, dataGrid);
/*     */ 
/*  73 */     HqlGenerateUtil.installHql(cq, cgformenhanceJs, request.getParameterMap());
/*  74 */     this.cgformenhanceJsService.getDataGridReturn(cq, true);
/*  75 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(CgformEnhanceJsEntity cgformenhanceJs, HttpServletRequest request)
/*     */   {
/*  86 */     String message = null;
/*  87 */     AjaxJson j = new AjaxJson();
/*  88 */     cgformenhanceJs = (CgformEnhanceJsEntity)this.systemService.getEntity(CgformEnhanceJsEntity.class, cgformenhanceJs.getId());
/*  89 */     message = "删除成功";
/*  90 */     this.cgformenhanceJsService.delete(cgformenhanceJs);
/*  91 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */ 
/*  93 */     j.setMsg(message);
/*  94 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doCgformEnhanceJs"})
/*     */   @ResponseBody
/*     */   public AjaxJson doCgformEnhanceJs(CgformEnhanceJsEntity cgformenhanceJs, HttpServletRequest request)
/*     */   {
/* 105 */     AjaxJson j = new AjaxJson();
/* 106 */     CgformEnhanceJsEntity cgformenJs = this.cgformenhanceJsService.getCgformEnhanceJsByTypeFormId(cgformenhanceJs.getCgJsType(), cgformenhanceJs.getFormId());
/* 107 */     if (cgformenJs != null) {
/* 108 */       j.setObj(cgformenJs);
/* 109 */       j.setSuccess(true);
/*     */     } else {
/* 111 */       j.setSuccess(false);
/*     */     }
/* 113 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(CgformEnhanceJsEntity cgformenhanceJs, HttpServletRequest request)
/*     */   {
/* 125 */     String message = null;
/* 126 */     AjaxJson j = new AjaxJson();
/* 127 */     if (StringUtil.isNotEmpty(cgformenhanceJs.getId())) {
/* 128 */       message = "更新成功";
/* 129 */       CgformEnhanceJsEntity t = (CgformEnhanceJsEntity)this.cgformenhanceJsService.get(CgformEnhanceJsEntity.class, cgformenhanceJs.getId());
/*     */       try {
/* 131 */         MyBeanUtils.copyBeanNotNull2Bean(cgformenhanceJs, t);
/* 132 */         this.cgformenhanceJsService.saveOrUpdate(t);
/* 133 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 135 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 138 */       message = "添加成功";
/* 139 */       this.cgformenhanceJsService.save(cgformenhanceJs);
/* 140 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/* 142 */     j.setMsg(message);
/* 143 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(CgformEnhanceJsEntity cgformenhanceJs, HttpServletRequest req)
/*     */   {
/* 154 */     cgformenhanceJs.setCgJsType("form");
/* 155 */     if ((StringUtil.isNotEmpty(cgformenhanceJs.getCgJsType())) && (StringUtil.isNotEmpty(cgformenhanceJs.getFormId()))) {
/* 156 */       CgformEnhanceJsEntity cgformenJs = this.cgformenhanceJsService.getCgformEnhanceJsByTypeFormId(cgformenhanceJs.getCgJsType(), cgformenhanceJs.getFormId());
/* 157 */       if (cgformenJs != null) {
/* 158 */         cgformenhanceJs = cgformenJs;
/*     */       }
/*     */     }
/* 161 */     req.setAttribute("cgformenhanceJsPage", cgformenhanceJs);
/* 162 */     return new ModelAndView("jeecg/cgform/enhance/cgformenhanceJs");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.controller.enhance.CgformEnhanceJsController
 * JD-Core Version:    0.6.2
 */