/*     */ package org.jeecgframework.web.system.controller.core;
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
/*     */ import org.jeecgframework.core.util.MutiLangUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.MutiLangEntity;
/*     */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/mutiLangController"})
/*     */ public class MutiLangController extends BaseController
/*     */ {
/*  40 */   private static final Logger logger = Logger.getLogger(MutiLangController.class);
/*     */ 
/*     */   @Autowired
/*     */   private MutiLangServiceI mutiLangService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"mutiLang"})
/*     */   public ModelAndView mutiLang(HttpServletRequest request)
/*     */   {
/*  54 */     return new ModelAndView("system/mutilang/mutiLangList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(MutiLangEntity mutiLang, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  68 */     CriteriaQuery cq = new CriteriaQuery(MutiLangEntity.class, dataGrid);
/*     */ 
/*  70 */     HqlGenerateUtil.installHql(cq, mutiLang, request.getParameterMap());
/*  71 */     this.mutiLangService.getDataGridReturn(cq, true);
/*  72 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(MutiLangEntity mutiLang, HttpServletRequest request)
/*     */   {
/*  83 */     String message = null;
/*  84 */     AjaxJson j = new AjaxJson();
/*  85 */     mutiLang = (MutiLangEntity)this.systemService.getEntity(MutiLangEntity.class, mutiLang.getId());
/*  86 */     message = MutiLangUtil.paramDelSuccess("common.language");
/*  87 */     this.mutiLangService.delete(mutiLang);
/*  88 */     this.mutiLangService.initAllMutiLang();
/*  89 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*  90 */     j.setMsg(message);
/*  91 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(MutiLangEntity mutiLang, HttpServletRequest request)
/*     */   {
/* 103 */     String message = null;
/* 104 */     AjaxJson j = new AjaxJson();
/* 105 */     if (StringUtil.isNotEmpty(mutiLang.getId())) {
/* 106 */       message = MutiLangUtil.paramUpdSuccess("common.language");
/* 107 */       MutiLangEntity t = (MutiLangEntity)this.mutiLangService.get(MutiLangEntity.class, mutiLang.getId());
/*     */       try {
/* 109 */         MyBeanUtils.copyBeanNotNull2Bean(mutiLang, t);
/* 110 */         this.mutiLangService.saveOrUpdate(t);
/* 111 */         this.mutiLangService.initAllMutiLang();
/* 112 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 114 */         e.printStackTrace();
/* 115 */         message = MutiLangUtil.paramUpdFail("common.language");
/*     */       }
/*     */     }
/*     */     else {
/* 119 */       if (MutiLangUtil.existLangKey(mutiLang.getLangKey(), mutiLang.getLangCode()))
/*     */       {
/* 121 */         message = this.mutiLangService.getLang("common.langkey.exist");
/*     */       }
/*     */ 
/* 124 */       if (StringUtil.isEmpty(message))
/*     */       {
/* 126 */         this.mutiLangService.save(mutiLang);
/* 127 */         message = MutiLangUtil.paramAddSuccess("common.language");
/* 128 */         this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     }
/*     */ 
/* 132 */     this.mutiLangService.putMutiLang(mutiLang);
/* 133 */     j.setMsg(message);
/* 134 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(MutiLangEntity mutiLang, HttpServletRequest req)
/*     */   {
/* 145 */     if (StringUtil.isNotEmpty(mutiLang.getId())) {
/* 146 */       mutiLang = (MutiLangEntity)this.mutiLangService.getEntity(MutiLangEntity.class, mutiLang.getId());
/* 147 */       req.setAttribute("mutiLangPage", mutiLang);
/* 148 */       this.mutiLangService.putMutiLang(mutiLang);
/*     */     }
/* 150 */     return new ModelAndView("system/mutilang/mutiLang");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"refreshCach"})
/*     */   @ResponseBody
/*     */   public AjaxJson refreshCach(HttpServletRequest request)
/*     */   {
/* 161 */     String message = null;
/* 162 */     AjaxJson j = new AjaxJson();
/*     */     try {
/* 164 */       this.mutiLangService.refleshMutiLangCach();
/* 165 */       message = this.mutiLangService.getLang("common.refresh.success");
/*     */     } catch (Exception e) {
/* 167 */       message = this.mutiLangService.getLang("common.refresh.fail");
/*     */     }
/* 169 */     j.setMsg(message);
/* 170 */     return j;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.MutiLangController
 * JD-Core Version:    0.6.2
 */