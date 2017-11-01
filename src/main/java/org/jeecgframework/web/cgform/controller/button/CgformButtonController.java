/*     */ package org.jeecgframework.web.cgform.controller.button;
/*     */ 
/*     */ import java.util.List;
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
/*     */ import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
/*     */ import org.jeecgframework.web.cgform.service.button.CgformButtonServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/cgformButtonController"})
/*     */ public class CgformButtonController extends BaseController
/*     */ {
/*  43 */   private static final Logger logger = Logger.getLogger(CgformButtonController.class);
/*     */ 
/*     */   @Autowired
/*     */   private CgformButtonServiceI cgformButtonService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"cgformButton"})
/*     */   public ModelAndView cgformButton(HttpServletRequest request)
/*     */   {
/*  58 */     String formId = request.getParameter("formId");
/*  59 */     String tableName = request.getParameter("tableName");
/*  60 */     request.setAttribute("formId", formId);
/*  61 */     request.setAttribute("tableName", tableName);
/*  62 */     return new ModelAndView("jeecg/cgform/button/cgformButtonList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(CgformButtonEntity cgformButton, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  77 */     CriteriaQuery cq = new CriteriaQuery(CgformButtonEntity.class, dataGrid);
/*     */ 
/*  79 */     HqlGenerateUtil.installHql(cq, cgformButton, request.getParameterMap());
/*  80 */     this.cgformButtonService.getDataGridReturn(cq, true);
/*  81 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(CgformButtonEntity cgformButton, HttpServletRequest request)
/*     */   {
/*  92 */     String message = null;
/*  93 */     AjaxJson j = new AjaxJson();
/*  94 */     cgformButton = (CgformButtonEntity)this.systemService.getEntity(CgformButtonEntity.class, cgformButton.getId());
/*  95 */     message = "删除成功";
/*  96 */     this.cgformButtonService.delete(cgformButton);
/*  97 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */ 
/*  99 */     j.setMsg(message);
/* 100 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(CgformButtonEntity cgformButton, HttpServletRequest request)
/*     */   {
/* 113 */     String message = null;
/* 114 */     AjaxJson j = new AjaxJson();
/* 115 */     if (("add".equalsIgnoreCase(cgformButton.getButtonCode())) || 
/* 116 */       ("update".equalsIgnoreCase(cgformButton.getButtonCode())) || 
/* 117 */       ("delete".equalsIgnoreCase(cgformButton.getButtonCode()))) {
/* 118 */       message = "按钮编码不能是add/update/delete";
/* 119 */       j.setMsg(message);
/* 120 */       return j;
/*     */     }
/* 122 */     List list = this.cgformButtonService.checkCgformButton(cgformButton);
/* 123 */     if ((list != null) && (list.size() > 0)) {
/* 124 */       message = "按钮编码已经存在";
/* 125 */       j.setMsg(message);
/* 126 */       return j;
/*     */     }
/* 128 */     if (StringUtil.isNotEmpty(cgformButton.getId())) {
/* 129 */       message = "更新成功";
/* 130 */       CgformButtonEntity t = (CgformButtonEntity)this.cgformButtonService.get(CgformButtonEntity.class, cgformButton.getId());
/*     */       try {
/* 132 */         MyBeanUtils.copyBeanNotNull2Bean(cgformButton, t);
/* 133 */         this.cgformButtonService.saveOrUpdate(t);
/* 134 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 136 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 139 */       message = "添加成功";
/* 140 */       this.cgformButtonService.save(cgformButton);
/* 141 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/* 143 */     j.setMsg(message);
/* 144 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(CgformButtonEntity cgformButton, HttpServletRequest req)
/*     */   {
/* 154 */     if (StringUtil.isNotEmpty(cgformButton.getId())) {
/* 155 */       cgformButton = (CgformButtonEntity)this.cgformButtonService.getEntity(CgformButtonEntity.class, cgformButton.getId());
/*     */     }
/* 157 */     req.setAttribute("cgformButtonPage", cgformButton);
/* 158 */     return new ModelAndView("jeecg/cgform/button/cgformButton");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.controller.button.CgformButtonController
 * JD-Core Version:    0.6.2
 */