/*     */ package org.jeecgframework.web.system.controller.core;
/*     */ 
/*     */ import java.text.ParseException;
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
/*     */ import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.jeecgframework.web.system.service.TimeTaskServiceI;
/*     */ import org.quartz.CronTrigger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/timeTaskController"})
/*     */ public class TimeTaskController extends BaseController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private TimeTaskServiceI timeTaskService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"timeTask"})
/*     */   public ModelAndView timeTask(HttpServletRequest request)
/*     */   {
/*  55 */     return new ModelAndView("system/timetask/timeTaskList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(TSTimeTaskEntity timeTask, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  69 */     CriteriaQuery cq = new CriteriaQuery(TSTimeTaskEntity.class, dataGrid);
/*     */ 
/*  71 */     HqlGenerateUtil.installHql(cq, timeTask, request.getParameterMap());
/*  72 */     this.timeTaskService.getDataGridReturn(cq, true);
/*  73 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(TSTimeTaskEntity timeTask, HttpServletRequest request)
/*     */   {
/*  84 */     String message = null;
/*  85 */     AjaxJson j = new AjaxJson();
/*  86 */     timeTask = (TSTimeTaskEntity)this.systemService.getEntity(TSTimeTaskEntity.class, timeTask.getId());
/*  87 */     message = "定时任务管理删除成功";
/*  88 */     this.timeTaskService.delete(timeTask);
/*  89 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*  90 */     j.setMsg(message);
/*  91 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(TSTimeTaskEntity timeTask, HttpServletRequest request)
/*     */   {
/* 104 */     String message = null;
/* 105 */     AjaxJson j = new AjaxJson();
/* 106 */     CronTrigger trigger = new CronTrigger();
/*     */     try {
/* 108 */       trigger.setCronExpression(timeTask.getCronExpression());
/*     */     } catch (ParseException e) {
/* 110 */       j.setMsg("Cron表达式错误");
/* 111 */       return j;
/*     */     }
/* 113 */     if (StringUtil.isNotEmpty(timeTask.getId())) {
/* 114 */       message = "定时任务管理更新成功";
/* 115 */       TSTimeTaskEntity t = (TSTimeTaskEntity)this.timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
/*     */       try {
/* 117 */         if (!timeTask.getCronExpression().equals(t.getCronExpression())) {
/* 118 */           timeTask.setIsEffect("0");
/*     */         }
/* 120 */         MyBeanUtils.copyBeanNotNull2Bean(timeTask, t);
/* 121 */         this.timeTaskService.saveOrUpdate(t);
/* 122 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 124 */         e.printStackTrace();
/* 125 */         message = "定时任务管理更新失败";
/*     */       }
/*     */     } else {
/* 128 */       message = "定时任务管理添加成功";
/* 129 */       this.timeTaskService.save(timeTask);
/* 130 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/* 132 */     j.setMsg(message);
/* 133 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(TSTimeTaskEntity timeTask, HttpServletRequest req)
/*     */   {
/* 143 */     if (StringUtil.isNotEmpty(timeTask.getId())) {
/* 144 */       timeTask = (TSTimeTaskEntity)this.timeTaskService.getEntity(TSTimeTaskEntity.class, timeTask.getId());
/* 145 */       req.setAttribute("timeTaskPage", timeTask);
/*     */     }
/* 147 */     return new ModelAndView("system/timetask/timeTask");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"updateTime"})
/*     */   @ResponseBody
/*     */   public AjaxJson updateTime(TSTimeTaskEntity timeTask, HttpServletRequest request)
/*     */   {
/* 156 */     AjaxJson j = new AjaxJson();
/* 157 */     timeTask = (TSTimeTaskEntity)this.timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
/* 158 */     boolean isUpdate = false;
/* 159 */     if (isUpdate) {
/* 160 */       timeTask.setIsEffect("1");
/* 161 */       timeTask.setIsStart("1");
/* 162 */       this.timeTaskService.updateEntitie(timeTask);
/*     */     }
/* 164 */     j.setMsg(isUpdate ? "定时任务管理更新成功" : "定时任务管理更新失败");
/* 165 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"startOrStopTask"})
/*     */   @ResponseBody
/*     */   public AjaxJson startOrStopTask(TSTimeTaskEntity timeTask, HttpServletRequest request)
/*     */   {
/* 173 */     AjaxJson j = new AjaxJson();
/* 174 */     boolean isStart = timeTask.getIsStart().equals("1");
/* 175 */     timeTask = (TSTimeTaskEntity)this.timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
/* 176 */     boolean isSuccess = false;
/* 177 */     if (isSuccess) {
/* 178 */       timeTask.setIsStart(isStart ? "1" : "0");
/* 179 */       this.timeTaskService.updateEntitie(timeTask);
/* 180 */       this.systemService.addLog((isStart ? "开启任务" : "停止任务") + timeTask.getTaskId(), 
/* 181 */         Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     }
/* 183 */     j.setMsg(isSuccess ? "定时任务管理更新成功" : "定时任务管理更新失败");
/* 184 */     return j;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.TimeTaskController
 * JD-Core Version:    0.6.2
 */