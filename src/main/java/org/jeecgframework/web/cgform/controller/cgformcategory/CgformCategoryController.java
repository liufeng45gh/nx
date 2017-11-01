/*     */ package org.jeecgframework.web.cgform.controller.cgformcategory;
/*     */ 
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.cgform.entity.category.CgformCategoryEntity;
/*     */ import org.jeecgframework.web.cgform.service.cgformcategory.CgformCategoryServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/cgformCategoryController"})
/*     */ public class CgformCategoryController
/*     */ {
/*  35 */   private static final Logger logger = Logger.getLogger(CgformCategoryController.class);
/*     */   private static final String CGFORM_CATEGORY_LIST = "jeecg/cgform/cgformcategory/cgformCategoryList";
/*     */   private static final String CGFORM_CATEGORY = "jeecg/cgform/cgformcategory/cgformCategory";
/*     */ 
/*     */   @Autowired
/*     */   private CgformCategoryServiceI cgformCategoryService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"cgformCategory"})
/*     */   public String cgformCategory(HttpServletRequest request)
/*     */   {
/*  53 */     return "jeecg/cgform/cgformcategory/cgformCategoryList";
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(CgformCategoryEntity cgformCategory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  60 */     CriteriaQuery cq = new CriteriaQuery(CgformCategoryEntity.class, 
/*  61 */       dataGrid);
/*  62 */     HqlGenerateUtil.installHql(cq, 
/*  63 */       cgformCategory);
/*  64 */     this.cgformCategoryService.getDataGridReturn(cq, true);
/*  65 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(CgformCategoryEntity cgformCategory, HttpServletRequest request)
/*     */   {
/*  77 */     AjaxJson j = new AjaxJson();
/*     */     try {
/*  79 */       cgformCategory = (CgformCategoryEntity)this.cgformCategoryService.getEntity(
/*  80 */         CgformCategoryEntity.class, cgformCategory.getId());
/*  81 */       j.setMsg("表单分类管理删除成功");
/*  82 */       this.cgformCategoryService.delete(cgformCategory);
/*  83 */       this.systemService.addLog(j.getMsg(), Globals.Log_Type_DEL, 
/*  84 */         Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/*  86 */       logger.error(e.getMessage(), e.fillInStackTrace());
/*  87 */       j.setMsg("表单分类管理删除失败");
/*     */     }
/*  89 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(CgformCategoryEntity cgformCategory, HttpServletRequest request)
/*     */   {
/* 102 */     AjaxJson j = new AjaxJson();
/*     */     try {
/* 104 */       if (StringUtil.isNotEmpty(cgformCategory.getId())) {
/* 105 */         j.setMsg("表单分类管理更新成功");
/* 106 */         CgformCategoryEntity t = (CgformCategoryEntity)this.cgformCategoryService.get(
/* 107 */           CgformCategoryEntity.class, cgformCategory.getId());
/* 108 */         MyBeanUtils.copyBeanNotNull2Bean(cgformCategory, t);
/* 109 */         this.cgformCategoryService.saveOrUpdate(t);
/* 110 */         this.systemService.addLog(j.getMsg(), Globals.Log_Type_UPDATE, 
/* 111 */           Globals.Log_Leavel_INFO);
/*     */       } else {
/* 113 */         j.setMsg("表单分类管理添加成功");
/* 114 */         this.cgformCategoryService.save(cgformCategory);
/* 115 */         this.systemService.addLog(j.getMsg(), Globals.Log_Type_INSERT, 
/* 116 */           Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 119 */       logger.error(e.getMessage(), e.fillInStackTrace());
/* 120 */       j.setMsg("表单分类管理更新失败");
/*     */     }
/* 122 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public String addorupdate(CgformCategoryEntity cgformCategory, HttpServletRequest req)
/*     */   {
/* 133 */     if (StringUtil.isNotEmpty(cgformCategory.getId())) {
/* 134 */       cgformCategory = (CgformCategoryEntity)this.cgformCategoryService.getEntity(
/* 135 */         CgformCategoryEntity.class, cgformCategory.getId());
/* 136 */       req.setAttribute("cgformCategoryPage", cgformCategory);
/*     */     }
/* 138 */     return "jeecg/cgform/cgformcategory/cgformCategory";
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.controller.cgformcategory.CgformCategoryController
 * JD-Core Version:    0.6.2
 */