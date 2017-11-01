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
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSConfig;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/configController"})
/*     */ public class ConfigController extends BaseController
/*     */ {
/*  40 */   private static final Logger logger = Logger.getLogger(ConfigController.class);
/*     */   private SystemService systemService;
/*     */ 
/*     */   @Autowired
/*     */   public void setSystemService(SystemService systemService)
/*     */   {
/*  46 */     this.systemService = systemService;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"config"})
/*     */   public ModelAndView config()
/*     */   {
/*  56 */     return new ModelAndView("system/config/configList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  69 */     CriteriaQuery cq = new CriteriaQuery(TSConfig.class, dataGrid);
/*  70 */     this.systemService.getDataGridReturn(cq, true);
/*  71 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(TSConfig config, HttpServletRequest request)
/*     */   {
/*  84 */     String message = null;
/*  85 */     AjaxJson j = new AjaxJson();
/*  86 */     config = (TSConfig)this.systemService.getEntity(TSConfig.class, config.getId());
/*  87 */     message = "配置信息: " + config.getName() + "被删除 成功";
/*  88 */     this.systemService.delete(config);
/*  89 */     this.systemService.addLog(message, Globals.Log_Type_DEL, 
/*  90 */       Globals.Log_Leavel_INFO);
/*     */ 
/*  92 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(TSConfig tsConfig, HttpServletRequest request)
/*     */   {
/* 105 */     String message = null;
/* 106 */     if (StringUtil.isEmpty(tsConfig.getId())) {
/* 107 */       TSConfig tsConfig2 = (TSConfig)this.systemService.findUniqueByProperty(TSConfig.class, "code", tsConfig.getCode());
/* 108 */       if (tsConfig2 != null) {
/* 109 */         message = "编码为: " + tsConfig.getCode() + "的配置信息已存在";
/*     */       } else {
/* 111 */         tsConfig.setTSUser(ResourceUtil.getSessionUserName());
/* 112 */         this.systemService.save(tsConfig);
/* 113 */         message = "配置信息: " + tsConfig.getName() + "被添加成功";
/* 114 */         this.systemService.addLog(message, Globals.Log_Type_INSERT, 
/* 115 */           Globals.Log_Leavel_INFO);
/*     */       }
/*     */     }
/*     */     else {
/* 119 */       message = "配置信息: " + tsConfig.getName() + "被修改成功";
/* 120 */       this.systemService.updateEntitie(tsConfig);
/* 121 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, 
/* 122 */         Globals.Log_Leavel_INFO);
/*     */     }
/* 124 */     AjaxJson j = new AjaxJson();
/* 125 */     j.setMsg(message);
/*     */ 
/* 127 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(TSConfig config, HttpServletRequest req)
/*     */   {
/* 139 */     if (StringUtil.isNotEmpty(config.getId())) {
/* 140 */       config = (TSConfig)this.systemService.getEntity(TSConfig.class, 
/* 141 */         config.getId());
/* 142 */       req.setAttribute("config", config);
/*     */     }
/* 144 */     return new ModelAndView("system/config/config");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.ConfigController
 * JD-Core Version:    0.6.2
 */