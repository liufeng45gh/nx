/*     */ package org.jeecgframework.web.demo.controller.test;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.common.UploadFile;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.demo.entity.test.TFinanceEntity;
/*     */ import org.jeecgframework.web.demo.entity.test.TFinanceFilesEntity;
/*     */ import org.jeecgframework.web.demo.service.test.TFinanceServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/tFinanceController"})
/*     */ public class TFinanceController extends BaseController
/*     */ {
/*  51 */   private static final Logger logger = Logger.getLogger(TFinanceController.class);
/*     */ 
/*     */   @Autowired
/*     */   private TFinanceServiceI tFinanceService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"tFinance"})
/*     */   public ModelAndView tFinance(HttpServletRequest request)
/*     */   {
/*  66 */     return new ModelAndView("jeecg/demo/test/tFinanceList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(TFinanceEntity tFinance, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  80 */     CriteriaQuery cq = new CriteriaQuery(TFinanceEntity.class, dataGrid);
/*     */ 
/*  82 */     HqlGenerateUtil.installHql(cq, tFinance, request.getParameterMap());
/*  83 */     this.tFinanceService.getDataGridReturn(cq, true);
/*  84 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"saveFiles"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, TFinanceFilesEntity financeFile)
/*     */   {
/*  98 */     AjaxJson j = new AjaxJson();
/*  99 */     Map attributes = new HashMap();
/* 100 */     String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
/*     */ 
/* 102 */     String financeId = oConvertUtils.getString(request.getParameter("financeId"));
/*     */ 
/* 104 */     if (StringUtil.isNotEmpty(fileKey)) {
/* 105 */       financeFile.setId(fileKey);
/* 106 */       financeFile = (TFinanceFilesEntity)this.systemService.getEntity(TFinanceFilesEntity.class, fileKey);
/*     */     }
/*     */ 
/* 109 */     TFinanceEntity finance = (TFinanceEntity)this.systemService.getEntity(TFinanceEntity.class, financeId);
/* 110 */     financeFile.setFinance(finance);
/* 111 */     UploadFile uploadFile = new UploadFile(request, financeFile);
/* 112 */     uploadFile.setCusPath("files");
/* 113 */     uploadFile.setSwfpath("swfpath");
/* 114 */     uploadFile.setByteField(null);
/* 115 */     financeFile = (TFinanceFilesEntity)this.systemService.uploadFile(uploadFile);
/* 116 */     attributes.put("fileKey", financeFile.getId());
/* 117 */     attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + financeFile.getId());
/* 118 */     attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + financeFile.getId());
/* 119 */     j.setMsg("文件添加成功");
/* 120 */     j.setAttributes(attributes);
/*     */ 
/* 122 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(TFinanceEntity tFinance, HttpServletRequest request)
/*     */   {
/* 133 */     String message = null;
/* 134 */     AjaxJson j = new AjaxJson();
/* 135 */     tFinance = (TFinanceEntity)this.systemService.getEntity(TFinanceEntity.class, tFinance.getId());
/* 136 */     message = "删除成功";
/* 137 */     this.tFinanceService.deleteFinance(tFinance);
/* 138 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/* 139 */     j.setMsg(message);
/* 140 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(TFinanceEntity tFinance, HttpServletRequest request)
/*     */   {
/* 153 */     String message = null;
/* 154 */     AjaxJson j = new AjaxJson();
/* 155 */     if (StringUtil.isNotEmpty(tFinance.getId())) {
/* 156 */       message = "更新成功";
/* 157 */       TFinanceEntity t = (TFinanceEntity)this.tFinanceService.get(TFinanceEntity.class, tFinance.getId());
/*     */       try {
/* 159 */         MyBeanUtils.copyBeanNotNull2Bean(tFinance, t);
/* 160 */         this.tFinanceService.saveOrUpdate(t);
/* 161 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 163 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 166 */       message = "添加成功";
/* 167 */       this.tFinanceService.save(tFinance);
/*     */ 
/* 169 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/* 171 */     j.setObj(tFinance);
/* 172 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"delFile"})
/*     */   @ResponseBody
/*     */   public AjaxJson delFile(HttpServletRequest request)
/*     */   {
/* 185 */     String message = null;
/* 186 */     AjaxJson j = new AjaxJson();
/* 187 */     String id = request.getParameter("id");
/* 188 */     TFinanceFilesEntity file = (TFinanceFilesEntity)this.systemService.getEntity(TFinanceFilesEntity.class, id);
/* 189 */     message = file.getAttachmenttitle() + "被删除成功";
/* 190 */     this.tFinanceService.deleteFile(file);
/* 191 */     this.systemService.addLog(message, Globals.Log_Type_DEL, 
/* 192 */       Globals.Log_Leavel_INFO);
/* 193 */     j.setSuccess(true);
/* 194 */     j.setMsg(message);
/* 195 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(TFinanceEntity tFinance, HttpServletRequest req)
/*     */   {
/* 205 */     if (StringUtil.isNotEmpty(tFinance.getId())) {
/* 206 */       tFinance = (TFinanceEntity)this.tFinanceService.getEntity(TFinanceEntity.class, tFinance.getId());
/* 207 */       req.setAttribute("tFinancePage", tFinance);
/*     */     }
/* 209 */     return new ModelAndView("jeecg/demo/test/tFinance");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.TFinanceController
 * JD-Core Version:    0.6.2
 */