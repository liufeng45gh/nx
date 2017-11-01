/*     */ package org.jeecgframework.web.demo.controller.test;
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
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.demo.entity.test.JeecgNoteEntity;
/*     */ import org.jeecgframework.web.demo.service.test.JeecgNoteServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/jeecgNoteController"})
/*     */ public class JeecgNoteController extends BaseController
/*     */ {
/*  37 */   private static final Logger logger = Logger.getLogger(JeecgNoteController.class);
/*     */ 
/*     */   @Autowired
/*     */   private JeecgNoteServiceI jeecgNoteService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"jeecgNote"})
/*     */   public ModelAndView jeecgNote(HttpServletRequest request)
/*     */   {
/*  52 */     return new ModelAndView("jeecg/demo/test/jeecgNoteList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"jeecgNote2"})
/*     */   public ModelAndView jeecgNote2(HttpServletRequest request)
/*     */   {
/*  64 */     return new ModelAndView("jeecg/demo/test/jeecgNoteList2");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(JeecgNoteEntity jeecgNote, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  79 */     CriteriaQuery cq = new CriteriaQuery(JeecgNoteEntity.class, dataGrid);
/*     */ 
/*  81 */     HqlGenerateUtil.installHql(cq, jeecgNote);
/*  82 */     this.jeecgNoteService.getDataGridReturn(cq, true);
/*  83 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(JeecgNoteEntity jeecgNote, HttpServletRequest request)
/*     */   {
/*  94 */     String message = null;
/*  95 */     AjaxJson j = new AjaxJson();
/*  96 */     jeecgNote = (JeecgNoteEntity)this.systemService.getEntity(JeecgNoteEntity.class, jeecgNote.getId());
/*  97 */     message = "删除成功";
/*  98 */     this.jeecgNoteService.delete(jeecgNote);
/*  99 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */ 
/* 101 */     j.setMsg(message);
/* 102 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(JeecgNoteEntity jeecgNote, HttpServletRequest request)
/*     */   {
/* 115 */     String message = null;
/* 116 */     AjaxJson j = new AjaxJson();
/* 117 */     if (StringUtil.isNotEmpty(jeecgNote.getId())) {
/* 118 */       message = "更新成功";
/* 119 */       this.jeecgNoteService.saveOrUpdate(jeecgNote);
/* 120 */       this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } else {
/* 122 */       message = "添加成功";
/* 123 */       this.jeecgNoteService.save(jeecgNote);
/* 124 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/*     */ 
/* 127 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(JeecgNoteEntity jeecgNote, HttpServletRequest req)
/*     */   {
/* 137 */     if (StringUtil.isNotEmpty(jeecgNote.getId())) {
/* 138 */       jeecgNote = (JeecgNoteEntity)this.jeecgNoteService.getEntity(JeecgNoteEntity.class, jeecgNote.getId());
/* 139 */       req.setAttribute("jeecgNotePage", jeecgNote);
/*     */     }
/* 141 */     return new ModelAndView("jeecg/demo/test/jeecgNote");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate2"})
/*     */   public ModelAndView addorupdate2(JeecgNoteEntity jeecgNote, HttpServletRequest req)
/*     */   {
/* 152 */     if (StringUtil.isNotEmpty(jeecgNote.getId())) {
/* 153 */       jeecgNote = (JeecgNoteEntity)this.jeecgNoteService.getEntity(JeecgNoteEntity.class, jeecgNote.getId());
/* 154 */       req.setAttribute("jeecgNotePage", jeecgNote);
/*     */     }
/* 156 */     return new ModelAndView("jeecg/demo/test/jeecgNote2");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdateNoBtn"})
/*     */   public ModelAndView addorupdateNoBtn(JeecgNoteEntity jeecgNote, HttpServletRequest req)
/*     */   {
/* 166 */     if (StringUtil.isNotEmpty(jeecgNote.getId())) {
/* 167 */       jeecgNote = (JeecgNoteEntity)this.jeecgNoteService.getEntity(JeecgNoteEntity.class, jeecgNote.getId());
/* 168 */       req.setAttribute("jeecgNotePage", jeecgNote);
/*     */     }
/* 170 */     return new ModelAndView("jeecg/demo/test/jeecgNoteNoBtn");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.JeecgNoteController
 * JD-Core Version:    0.6.2
 */