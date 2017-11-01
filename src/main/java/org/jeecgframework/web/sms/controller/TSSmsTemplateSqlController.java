/*     */ package org.jeecgframework.web.sms.controller;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ import org.jeecgframework.web.sms.entity.TSSmsTemplateSqlEntity;
/*     */ import org.jeecgframework.web.sms.service.TSSmsTemplateSqlServiceI;
/*     */ import org.jeecgframework.web.sms.util.TuiSongMsgUtil;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/tSSmsTemplateSqlController"})
/*     */ public class TSSmsTemplateSqlController extends BaseController
/*     */ {
/*  49 */   private static final Logger logger = Logger.getLogger(TSSmsTemplateSqlController.class);
/*     */ 
/*     */   @Autowired
/*     */   private TSSmsTemplateSqlServiceI tSSmsTemplateSqlService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */   private String message;
/*     */ 
/*  58 */   public String getMessage() { return this.message; }
/*     */ 
/*     */   public void setMessage(String message)
/*     */   {
/*  62 */     this.message = message;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"pushMsg"})
/*     */   @ResponseBody
/*     */   public AjaxJson pushMsg(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest request)
/*     */   {
/*  73 */     AjaxJson j = new AjaxJson();
/*  74 */     if (StringUtils.isBlank(tSSmsTemplateSql.getCode())) {
/*  75 */       j.setSuccess(false);
/*  76 */       j.setMsg("配置CODE不能为空");
/*     */     } else {
/*  78 */       Map map = new HashMap();
/*  79 */       map.put("id", "4028d881436d514601436d521ae80165");
/*  80 */       String r = TuiSongMsgUtil.sendMessage("消息推送测试333", "2", tSSmsTemplateSql.getCode(), map, "411944058@qq.com");
/*  81 */       if (!"success".equals(r)) {
/*  82 */         j.setSuccess(false);
/*  83 */         j.setMsg(r);
/*     */       }
/*     */     }
/*  86 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"tSSmsTemplateSql"})
/*     */   public ModelAndView tSSmsTemplateSql(HttpServletRequest request)
/*     */   {
/*  96 */     return new ModelAndView("system/sms/tSSmsTemplateSqlList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 110 */     CriteriaQuery cq = new CriteriaQuery(TSSmsTemplateSqlEntity.class, dataGrid);
/*     */ 
/* 112 */     HqlGenerateUtil.installHql(cq, tSSmsTemplateSql, request.getParameterMap());
/*     */ 
/* 118 */     cq.add();
/* 119 */     this.tSSmsTemplateSqlService.getDataGridReturn(cq, true);
/* 120 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDel(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest request)
/*     */   {
/* 131 */     AjaxJson j = new AjaxJson();
/* 132 */     tSSmsTemplateSql = (TSSmsTemplateSqlEntity)this.systemService.getEntity(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
/* 133 */     this.message = "消息模板_业务SQL配置表删除成功";
/*     */     try {
/* 135 */       this.tSSmsTemplateSqlService.delete(tSSmsTemplateSql);
/* 136 */       this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 138 */       e.printStackTrace();
/* 139 */       this.message = "消息模板_业务SQL配置表删除失败";
/* 140 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 142 */     j.setMsg(this.message);
/* 143 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doBatchDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doBatchDel(String ids, HttpServletRequest request)
/*     */   {
/* 154 */     AjaxJson j = new AjaxJson();
/* 155 */     this.message = "消息模板_业务SQL配置表删除成功";
/*     */     try {
/* 157 */       for (String id : ids.split(",")) {
/* 158 */         TSSmsTemplateSqlEntity tSSmsTemplateSql = (TSSmsTemplateSqlEntity)this.systemService.getEntity(TSSmsTemplateSqlEntity.class, 
/* 159 */           id);
/*     */ 
/* 161 */         this.tSSmsTemplateSqlService.delete(tSSmsTemplateSql);
/* 162 */         this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 165 */       e.printStackTrace();
/* 166 */       this.message = "消息模板_业务SQL配置表删除失败";
/* 167 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 169 */     j.setMsg(this.message);
/* 170 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doAdd"})
/*     */   @ResponseBody
/*     */   public AjaxJson doAdd(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest request)
/*     */   {
/* 183 */     AjaxJson j = new AjaxJson();
/* 184 */     this.message = "消息模板_业务SQL配置表添加成功";
/*     */     try {
/* 186 */       this.tSSmsTemplateSqlService.save(tSSmsTemplateSql);
/* 187 */       this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 189 */       e.printStackTrace();
/* 190 */       this.message = "消息模板_业务SQL配置表添加失败";
/* 191 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 193 */     j.setMsg(this.message);
/* 194 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doUpdate"})
/*     */   @ResponseBody
/*     */   public AjaxJson doUpdate(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest request)
/*     */   {
/* 206 */     AjaxJson j = new AjaxJson();
/* 207 */     this.message = "消息模板_业务SQL配置表更新成功";
/* 208 */     TSSmsTemplateSqlEntity t = (TSSmsTemplateSqlEntity)this.tSSmsTemplateSqlService.get(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
/*     */     try {
/* 210 */       MyBeanUtils.copyBeanNotNull2Bean(tSSmsTemplateSql, t);
/* 211 */       this.tSSmsTemplateSqlService.saveOrUpdate(t);
/* 212 */       this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 214 */       e.printStackTrace();
/* 215 */       this.message = "消息模板_业务SQL配置表更新失败";
/* 216 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 218 */     j.setMsg(this.message);
/* 219 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goAdd"})
/*     */   public ModelAndView goAdd(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest req)
/*     */   {
/* 230 */     if (StringUtil.isNotEmpty(tSSmsTemplateSql.getId())) {
/* 231 */       tSSmsTemplateSql = (TSSmsTemplateSqlEntity)this.tSSmsTemplateSqlService.getEntity(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
/* 232 */       req.setAttribute("tSSmsTemplateSqlPage", tSSmsTemplateSql);
/*     */     }
/* 234 */     return new ModelAndView("system/sms/tSSmsTemplateSql-add");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goUpdate"})
/*     */   public ModelAndView goUpdate(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest req)
/*     */   {
/* 243 */     if (StringUtil.isNotEmpty(tSSmsTemplateSql.getId())) {
/* 244 */       tSSmsTemplateSql = (TSSmsTemplateSqlEntity)this.tSSmsTemplateSqlService.getEntity(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
/* 245 */       req.setAttribute("tSSmsTemplateSqlPage", tSSmsTemplateSql);
/*     */     }
/* 247 */     return new ModelAndView("system/sms/tSSmsTemplateSql-update");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"upload"})
/*     */   public ModelAndView upload(HttpServletRequest req)
/*     */   {
/* 257 */     return new ModelAndView("system/sms/tSSmsTemplateSqlUpload");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"importExcel"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 355 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 383 */     return j;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.controller.TSSmsTemplateSqlController
 * JD-Core Version:    0.6.2
 */