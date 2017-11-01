/*     */ package org.jeecgframework.web.cgform.controller.enhance;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
/*     */ import org.jeecgframework.web.cgform.service.button.CgformButtonServiceI;
/*     */ import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJavaServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/cgformEnhanceJavaController"})
/*     */ public class CgformEnhanceJavaController extends BaseController
/*     */ {
/*  47 */   private static final Logger logger = Logger.getLogger(CgformEnhanceJavaController.class);
/*     */ 
/*     */   @Autowired
/*     */   private CgformEnhanceJavaServiceI cgformEnhanceJavaService;
/*     */ 
/*     */   @Autowired
/*     */   private CgformButtonServiceI cgformButtonService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"cgformEnhanceJava"})
/*     */   public ModelAndView cgformEnhanceJava(HttpServletRequest request)
/*     */   {
/*  64 */     String formId = request.getParameter("formId");
/*  65 */     String tableName = request.getParameter("tableName");
/*  66 */     request.setAttribute("formId", formId);
/*  67 */     request.setAttribute("tableName", tableName);
/*  68 */     return new ModelAndView("jeecg/cgform/enhance/cgformEnhanceJavaList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(CgformEnhanceJavaEntity cgformEnhanceJava, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  82 */     CriteriaQuery cq = new CriteriaQuery(CgformEnhanceJavaEntity.class, dataGrid);
/*     */ 
/*  84 */     HqlGenerateUtil.installHql(cq, cgformEnhanceJava, request.getParameterMap());
/*     */ 
/*  90 */     cq.add();
/*  91 */     this.cgformEnhanceJavaService.getDataGridReturn(cq, true);
/*  92 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDel(CgformEnhanceJavaEntity cgformEnhanceJava, HttpServletRequest request)
/*     */   {
/* 103 */     String message = null;
/* 104 */     AjaxJson j = new AjaxJson();
/* 105 */     cgformEnhanceJava = (CgformEnhanceJavaEntity)this.systemService.getEntity(CgformEnhanceJavaEntity.class, cgformEnhanceJava.getId());
/* 106 */     message = "删除成功";
/*     */     try {
/* 108 */       this.cgformEnhanceJavaService.delete(cgformEnhanceJava);
/* 109 */       this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 111 */       e.printStackTrace();
/* 112 */       message = "删除失败";
/* 113 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 115 */     j.setMsg(message);
/* 116 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doBatchDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doBatchDel(String ids, HttpServletRequest request)
/*     */   {
/* 127 */     String message = null;
/* 128 */     AjaxJson j = new AjaxJson();
/* 129 */     message = "删除成功";
/*     */     try {
/* 131 */       for (String id : ids.split(",")) {
/* 132 */         CgformEnhanceJavaEntity cgformEnhanceJava = (CgformEnhanceJavaEntity)this.systemService.getEntity(CgformEnhanceJavaEntity.class, 
/* 133 */           id);
/*     */ 
/* 135 */         this.cgformEnhanceJavaService.delete(cgformEnhanceJava);
/* 136 */         this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 139 */       e.printStackTrace();
/* 140 */       message = "删除失败";
/* 141 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 143 */     j.setMsg(message);
/* 144 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doCgformEnhanceJava"})
/*     */   @ResponseBody
/*     */   public AjaxJson doCgformEnhanceJava(CgformEnhanceJavaEntity cgformEnhanceJavaEntity, HttpServletRequest request)
/*     */   {
/* 155 */     AjaxJson j = new AjaxJson();
/* 156 */     CgformEnhanceJavaEntity doCgformEnhanceJava = this.cgformEnhanceJavaService.getCgformEnhanceJavaEntityByCodeFormId(cgformEnhanceJavaEntity.getButtonCode(), cgformEnhanceJavaEntity.getFormId());
/* 157 */     if (doCgformEnhanceJava != null) {
/* 158 */       j.setObj(doCgformEnhanceJava);
/* 159 */       j.setSuccess(true);
/*     */     } else {
/* 161 */       j.setSuccess(false);
/*     */     }
/* 163 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(CgformEnhanceJavaEntity cgformEnhanceJavaEntity, HttpServletRequest request)
/*     */   {
/* 175 */     String message = null;
/* 176 */     AjaxJson j = new AjaxJson();
/* 177 */     List list = this.cgformEnhanceJavaService.checkCgformEnhanceJavaEntity(cgformEnhanceJavaEntity);
/* 178 */     if ((list != null) && (list.size() > 0)) {
/* 179 */       message = "按钮编码已经存在";
/* 180 */       j.setMsg(message);
/* 181 */       return j;
/*     */     }
/*     */ 
/* 184 */     if (!this.cgformEnhanceJavaService.checkClassOrSpringBeanIsExist(cgformEnhanceJavaEntity)) {
/* 185 */       message = "类实例化失败，请检查！";
/* 186 */       j.setMsg(message);
/* 187 */       return j;
/*     */     }
/*     */ 
/* 190 */     if (StringUtil.isNotEmpty(cgformEnhanceJavaEntity.getId())) {
/* 191 */       message = "更新成功";
/* 192 */       CgformEnhanceJavaEntity t = (CgformEnhanceJavaEntity)this.cgformEnhanceJavaService.get(CgformEnhanceJavaEntity.class, cgformEnhanceJavaEntity.getId());
/*     */       try {
/* 194 */         MyBeanUtils.copyBeanNotNull2Bean(cgformEnhanceJavaEntity, t);
/* 195 */         this.cgformEnhanceJavaService.saveOrUpdate(t);
/* 196 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 198 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 201 */       message = "添加成功";
/* 202 */       this.cgformEnhanceJavaService.save(cgformEnhanceJavaEntity);
/* 203 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/* 205 */     j.setMsg(message);
/* 206 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(CgformEnhanceJavaEntity cgformEnhanceJavaEntity, HttpServletRequest req)
/*     */   {
/* 217 */     cgformEnhanceJavaEntity.setButtonCode("add");
/* 218 */     if ((StringUtil.isNotEmpty(cgformEnhanceJavaEntity.getButtonCode())) && (StringUtil.isNotEmpty(cgformEnhanceJavaEntity.getFormId()))) {
/* 219 */       CgformEnhanceJavaEntity cgformEnhanceJavaEntityVo = this.cgformEnhanceJavaService.getCgformEnhanceJavaEntityByCodeFormId(cgformEnhanceJavaEntity.getButtonCode(), cgformEnhanceJavaEntity.getFormId());
/* 220 */       if (cgformEnhanceJavaEntityVo != null) {
/* 221 */         cgformEnhanceJavaEntity = cgformEnhanceJavaEntityVo;
/*     */       }
/*     */     }
/*     */ 
/* 225 */     List list = this.cgformButtonService.getCgformButtonListByFormId(cgformEnhanceJavaEntity.getFormId());
/* 226 */     if (list == null) {
/* 227 */       list = new ArrayList();
/*     */     }
/* 229 */     req.setAttribute("buttonList", list);
/* 230 */     req.setAttribute("cgformEnhanceJavaPage", cgformEnhanceJavaEntity);
/* 231 */     return new ModelAndView("jeecg/cgform/enhance/cgformEnhanceJava");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.controller.enhance.CgformEnhanceJavaController
 * JD-Core Version:    0.6.2
 */