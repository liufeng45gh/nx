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
/*     */ import org.jeecgframework.core.util.LogUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.demo.entity.test.OptimisticLockingEntity;
/*     */ import org.jeecgframework.web.demo.service.test.OptimisticLockingServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/optimisticLockingController"})
/*     */ public class OptimisticLockingController extends BaseController
/*     */ {
/*  38 */   private static final Logger logger = Logger.getLogger(OptimisticLockingController.class);
/*     */ 
/*     */   @Autowired
/*     */   private OptimisticLockingServiceI optimisticLockingService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"optimisticLocking"})
/*     */   public ModelAndView optimisticLocking(HttpServletRequest request)
/*     */   {
/*  53 */     return new ModelAndView("jeecg/demo/test/optimisticLockingList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(OptimisticLockingEntity optimisticLocking, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  67 */     CriteriaQuery cq = new CriteriaQuery(OptimisticLockingEntity.class, dataGrid);
/*     */ 
/*  69 */     HqlGenerateUtil.installHql(cq, optimisticLocking, request.getParameterMap());
/*  70 */     this.optimisticLockingService.getDataGridReturn(cq, true);
/*  71 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(OptimisticLockingEntity optimisticLocking, HttpServletRequest request)
/*     */   {
/*  82 */     String message = null;
/*  83 */     AjaxJson j = new AjaxJson();
/*  84 */     optimisticLocking = (OptimisticLockingEntity)this.systemService.getEntity(OptimisticLockingEntity.class, optimisticLocking.getId());
/*  85 */     message = "删除成功";
/*  86 */     this.optimisticLockingService.delete(optimisticLocking);
/*  87 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */ 
/*  89 */     j.setMsg(message);
/*  90 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(OptimisticLockingEntity optimisticLocking, HttpServletRequest request)
/*     */   {
/* 103 */     AjaxJson j = new AjaxJson();
/* 104 */     String message = null;
/*     */ 
/* 106 */     if (StringUtil.isNotEmpty(optimisticLocking.getId())) {
/* 107 */       message = "更新成功";
/* 108 */       OptimisticLockingEntity t = (OptimisticLockingEntity)this.optimisticLockingService.get(OptimisticLockingEntity.class, optimisticLocking.getId());
/*     */       try {
/* 110 */         LogUtil.info("提交的版本号：" + optimisticLocking.getVer() + "，当前版本号：" + t.getVer());
/* 111 */         if (optimisticLocking.getVer().intValue() < t.getVer().intValue()) {
/* 112 */           j.setSuccess(false);
/* 113 */           j.setMsg("提交的数据已过期");
/* 114 */           throw new Exception("提交的数据已过期");
/*     */         }
/* 116 */         MyBeanUtils.copyBeanNotNull2Bean(optimisticLocking, t);
/* 117 */         this.optimisticLockingService.updateEntitie(t);
/* 118 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 120 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 123 */       message = "添加成功";
/* 124 */       this.optimisticLockingService.save(optimisticLocking);
/* 125 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/*     */ 
/* 128 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(OptimisticLockingEntity optimisticLocking, HttpServletRequest req)
/*     */   {
/* 138 */     if (StringUtil.isNotEmpty(optimisticLocking.getId())) {
/* 139 */       optimisticLocking = (OptimisticLockingEntity)this.optimisticLockingService.getEntity(OptimisticLockingEntity.class, optimisticLocking.getId());
/* 140 */       req.setAttribute("optimisticLockingPage", optimisticLocking);
/*     */     }
/* 142 */     return new ModelAndView("jeecg/demo/test/optimisticLocking");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.OptimisticLockingController
 * JD-Core Version:    0.6.2
 */