/*     */ package org.jeecgframework.web.cgform.controller.button;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;
/*     */ import org.jeecgframework.web.cgform.service.button.CgformButtonServiceI;
/*     */ import org.jeecgframework.web.cgform.service.button.CgformButtonSqlServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/cgformButtonSqlController"})
/*     */ public class CgformButtonSqlController extends BaseController
/*     */ {
/*  46 */   private static final Logger logger = Logger.getLogger(CgformButtonSqlController.class);
/*     */ 
/*     */   @Autowired
/*     */   private CgformButtonSqlServiceI cgformButtonSqlService;
/*     */ 
/*     */   @Autowired
/*     */   private CgformButtonServiceI cgformButtonService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"cgformButtonSql"})
/*     */   public ModelAndView cgformButtonSql(HttpServletRequest request)
/*     */   {
/*  63 */     String formId = request.getParameter("formId");
/*  64 */     String tableName = request.getParameter("tableName");
/*  65 */     request.setAttribute("formId", formId);
/*  66 */     request.setAttribute("tableName", tableName);
/*  67 */     return new ModelAndView("jeecg/cgform/button/cgformButtonSqlList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(CgformButtonSqlEntity cgformButtonSql, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  82 */     CriteriaQuery cq = new CriteriaQuery(CgformButtonSqlEntity.class, dataGrid);
/*     */ 
/*  84 */     HqlGenerateUtil.installHql(cq, cgformButtonSql, request.getParameterMap());
/*  85 */     this.cgformButtonSqlService.getDataGridReturn(cq, true);
/*  86 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(CgformButtonSqlEntity cgformButtonSql, HttpServletRequest request)
/*     */   {
/*  97 */     String message = null;
/*  98 */     AjaxJson j = new AjaxJson();
/*  99 */     cgformButtonSql = (CgformButtonSqlEntity)this.systemService.getEntity(CgformButtonSqlEntity.class, cgformButtonSql.getId());
/* 100 */     message = "删除成功";
/* 101 */     this.cgformButtonSqlService.delete(cgformButtonSql);
/* 102 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */ 
/* 104 */     j.setMsg(message);
/* 105 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doCgformButtonSql"})
/*     */   @ResponseBody
/*     */   public AjaxJson doCgformButtonSql(CgformButtonSqlEntity cgformButtonSql, HttpServletRequest request)
/*     */   {
/* 116 */     AjaxJson j = new AjaxJson();
/* 117 */     CgformButtonSqlEntity cgformSql = this.cgformButtonSqlService.getCgformButtonSqlByCodeFormId(cgformButtonSql.getButtonCode(), cgformButtonSql.getFormId());
/* 118 */     if (cgformSql != null) {
/* 119 */       j.setObj(cgformSql);
/* 120 */       j.setSuccess(true);
/*     */     } else {
/* 122 */       j.setSuccess(false);
/*     */     }
/* 124 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(CgformButtonSqlEntity cgformButtonSql, HttpServletRequest request)
/*     */   {
/* 137 */     String message = null;
/* 138 */     AjaxJson j = new AjaxJson();
/* 139 */     List list = this.cgformButtonSqlService.checkCgformButtonSql(cgformButtonSql);
/* 140 */     if ((list != null) && (list.size() > 0)) {
/* 141 */       message = "按钮编码已经存在";
/* 142 */       j.setMsg(message);
/* 143 */       return j;
/*     */     }
/* 145 */     if (StringUtil.isNotEmpty(cgformButtonSql.getId())) {
/* 146 */       message = "更新成功";
/* 147 */       CgformButtonSqlEntity t = (CgformButtonSqlEntity)this.cgformButtonSqlService.get(CgformButtonSqlEntity.class, cgformButtonSql.getId());
/*     */       try {
/* 149 */         MyBeanUtils.copyBeanNotNull2Bean(cgformButtonSql, t);
/* 150 */         this.cgformButtonSqlService.saveOrUpdate(t);
/* 151 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 153 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 156 */       message = "添加成功";
/* 157 */       this.cgformButtonSqlService.save(cgformButtonSql);
/* 158 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/* 160 */     j.setMsg(message);
/* 161 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(CgformButtonSqlEntity cgformButtonSql, HttpServletRequest req)
/*     */   {
/* 172 */     cgformButtonSql.setButtonCode("add");
/* 173 */     if ((StringUtil.isNotEmpty(cgformButtonSql.getButtonCode())) && (StringUtil.isNotEmpty(cgformButtonSql.getFormId()))) {
/* 174 */       CgformButtonSqlEntity cgformButtonSqlVo = this.cgformButtonSqlService.getCgformButtonSqlByCodeFormId(cgformButtonSql.getButtonCode(), cgformButtonSql.getFormId());
/* 175 */       if (cgformButtonSqlVo != null) {
/* 176 */         cgformButtonSql = cgformButtonSqlVo;
/*     */       }
/*     */     }
/* 179 */     List list = this.cgformButtonService.getCgformButtonListByFormId(cgformButtonSql.getFormId());
/* 180 */     if (list == null) {
/* 181 */       list = new ArrayList();
/*     */     }
/* 183 */     req.setAttribute("buttonList", list);
/* 184 */     req.setAttribute("cgformButtonSqlPage", cgformButtonSql);
/* 185 */     return new ModelAndView("jeecg/cgform/button/cgformButtonSql");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.controller.button.CgformButtonSqlController
 * JD-Core Version:    0.6.2
 */