/*     */ package org.jeecgframework.web.sms.controller;
/*     */ 
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.exception.BusinessException;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.sms.entity.TSSmsSqlEntity;
/*     */ import org.jeecgframework.web.sms.service.TSSmsSqlServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/tSSmsSqlController"})
/*     */ public class TSSmsSqlController extends BaseController
/*     */ {
/*  44 */   private static final Logger logger = Logger.getLogger(TSSmsSqlController.class);
/*     */ 
/*     */   @Autowired
/*     */   private TSSmsSqlServiceI tSSmsSqlService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"tSSmsSql"})
/*     */   public ModelAndView tSSmsSql(HttpServletRequest request)
/*     */   {
/*  59 */     return new ModelAndView("system/sms/tSSmsSqlList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(TSSmsSqlEntity tSSmsSql, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  73 */     CriteriaQuery cq = new CriteriaQuery(TSSmsSqlEntity.class, dataGrid);
/*     */ 
/*  75 */     HqlGenerateUtil.installHql(cq, tSSmsSql, request.getParameterMap());
/*     */ 
/*  81 */     cq.add();
/*  82 */     this.systemService.getDataGridReturn(cq, true);
/*  83 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDel(TSSmsSqlEntity tSSmsSql, HttpServletRequest request)
/*     */   {
/*  94 */     String message = null;
/*  95 */     AjaxJson j = new AjaxJson();
/*  96 */     tSSmsSql = (TSSmsSqlEntity)this.systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
/*  97 */     message = "业务SQL表删除成功";
/*     */     try {
/*  99 */       this.systemService.delete(tSSmsSql);
/* 100 */       this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 102 */       e.printStackTrace();
/* 103 */       message = "业务SQL表删除失败";
/* 104 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 106 */     j.setMsg(message);
/* 107 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doBatchDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doBatchDel(String ids, HttpServletRequest request)
/*     */   {
/* 118 */     String message = null;
/* 119 */     AjaxJson j = new AjaxJson();
/* 120 */     message = "业务SQL表删除成功";
/*     */     try {
/* 122 */       for (String id : ids.split(",")) {
/* 123 */         TSSmsSqlEntity tSSmsSql = (TSSmsSqlEntity)this.systemService.getEntity(TSSmsSqlEntity.class, 
/* 124 */           id);
/*     */ 
/* 126 */         this.systemService.delete(tSSmsSql);
/* 127 */         this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 130 */       e.printStackTrace();
/* 131 */       message = "业务SQL表删除失败";
/* 132 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 134 */     j.setMsg(message);
/* 135 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doAdd"})
/*     */   @ResponseBody
/*     */   public AjaxJson doAdd(TSSmsSqlEntity tSSmsSql, HttpServletRequest request)
/*     */   {
/* 148 */     String message = null;
/* 149 */     AjaxJson j = new AjaxJson();
/* 150 */     message = "业务SQL表添加成功";
/*     */     try {
/* 152 */       this.tSSmsSqlService.save(tSSmsSql);
/* 153 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 155 */       e.printStackTrace();
/* 156 */       message = "业务SQL表添加失败";
/* 157 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 159 */     j.setMsg(message);
/* 160 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doUpdate"})
/*     */   @ResponseBody
/*     */   public AjaxJson doUpdate(TSSmsSqlEntity tSSmsSql, HttpServletRequest request)
/*     */   {
/* 172 */     String message = null;
/* 173 */     AjaxJson j = new AjaxJson();
/* 174 */     message = "业务SQL表更新成功";
/* 175 */     TSSmsSqlEntity t = (TSSmsSqlEntity)this.systemService.get(TSSmsSqlEntity.class, tSSmsSql.getId());
/*     */     try {
/* 177 */       MyBeanUtils.copyBeanNotNull2Bean(tSSmsSql, t);
/* 178 */       this.systemService.saveOrUpdate(t);
/* 179 */       this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 181 */       e.printStackTrace();
/* 182 */       message = "业务SQL表更新失败";
/* 183 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 185 */     j.setMsg(message);
/* 186 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goAdd"})
/*     */   public ModelAndView goAdd(TSSmsSqlEntity tSSmsSql, HttpServletRequest req)
/*     */   {
/* 197 */     if (StringUtil.isNotEmpty(tSSmsSql.getId())) {
/* 198 */       tSSmsSql = (TSSmsSqlEntity)this.systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
/* 199 */       req.setAttribute("tSSmsSqlPage", tSSmsSql);
/*     */     }
/* 201 */     return new ModelAndView("system/sms/tSSmsSql-add");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goUpdate"})
/*     */   public ModelAndView goUpdate(TSSmsSqlEntity tSSmsSql, HttpServletRequest req)
/*     */   {
/* 210 */     if (StringUtil.isNotEmpty(tSSmsSql.getId())) {
/* 211 */       tSSmsSql = (TSSmsSqlEntity)this.systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
/* 212 */       req.setAttribute("tSSmsSqlPage", tSSmsSql);
/*     */     }
/* 214 */     return new ModelAndView("system/sms/tSSmsSql-update");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"upload"})
/*     */   public ModelAndView upload(HttpServletRequest req)
/*     */   {
/* 224 */     return new ModelAndView("system/sms/tSSmsSqlUpload");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"importExcel"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 322 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 350 */     return j;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.controller.TSSmsSqlController
 * JD-Core Version:    0.6.2
 */