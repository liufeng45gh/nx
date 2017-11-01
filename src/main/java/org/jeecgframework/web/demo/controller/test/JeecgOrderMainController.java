/*     */ package org.jeecgframework.web.demo.controller.test;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.demo.entity.test.JeecgOrderCustomEntity;
/*     */ import org.jeecgframework.web.demo.entity.test.JeecgOrderMainEntity;
/*     */ import org.jeecgframework.web.demo.entity.test.JeecgOrderProductEntity;
/*     */ import org.jeecgframework.web.demo.page.JeecgOrderMainPage;
/*     */ import org.jeecgframework.web.demo.service.test.JeecgOrderMainServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/jeecgOrderMainController"})
/*     */ public class JeecgOrderMainController extends BaseController
/*     */ {
/*  42 */   private static final Logger logger = Logger.getLogger(JeecgOrderMainController.class);
/*     */ 
/*     */   @Autowired
/*     */   private JeecgOrderMainServiceI jeecgOrderMainService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"jeecgOrderMain"})
/*     */   public ModelAndView jeecgOrderMain(HttpServletRequest request)
/*     */   {
/*  57 */     return new ModelAndView("jeecg/demo/test/jeecgOrderMainList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  71 */     CriteriaQuery cq = new CriteriaQuery(JeecgOrderMainEntity.class, dataGrid);
/*  72 */     this.jeecgOrderMainService.getDataGridReturn(cq, true);
/*  73 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest request)
/*     */   {
/*  84 */     String message = null;
/*  85 */     AjaxJson j = new AjaxJson();
/*  86 */     jeecgOrderMain = (JeecgOrderMainEntity)this.systemService.getEntity(JeecgOrderMainEntity.class, jeecgOrderMain.getId());
/*  87 */     message = "删除成功";
/*  88 */     this.jeecgOrderMainService.delMain(jeecgOrderMain);
/*  89 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*  90 */     j.setMsg(message);
/*  91 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(JeecgOrderMainEntity jeecgOrderMain, JeecgOrderMainPage jeecgOrderMainPage, HttpServletRequest request)
/*     */   {
/* 105 */     String message = null;
/* 106 */     List jeecgOrderProducList = jeecgOrderMainPage.getJeecgOrderProductList();
/* 107 */     List jeecgOrderCustomList = jeecgOrderMainPage.getJeecgOrderCustomList();
/* 108 */     Boolean jeecgOrderCustomShow = Boolean.valueOf("true".equals(request.getParameter("jeecgOrderCustomShow")));
/* 109 */     AjaxJson j = new AjaxJson();
/* 110 */     if (StringUtil.isNotEmpty(jeecgOrderMain.getId())) {
/* 111 */       message = "更新成功";
/* 112 */       this.jeecgOrderMainService.updateMain(jeecgOrderMain, jeecgOrderProducList, jeecgOrderCustomList, jeecgOrderCustomShow.booleanValue());
/* 113 */       this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } else {
/* 115 */       message = "添加成功";
/* 116 */       this.jeecgOrderMainService.addMain(jeecgOrderMain, jeecgOrderProducList, jeecgOrderCustomList);
/* 117 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/* 119 */     j.setMsg(message);
/* 120 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest req)
/*     */   {
/* 129 */     if (StringUtil.isNotEmpty(jeecgOrderMain.getId())) {
/* 130 */       jeecgOrderMain = (JeecgOrderMainEntity)this.jeecgOrderMainService.getEntity(JeecgOrderMainEntity.class, jeecgOrderMain.getId());
/* 131 */       req.setAttribute("jeecgOrderMainPage", jeecgOrderMain);
/*     */     }
/* 133 */     return new ModelAndView("jeecg/demo/test/jeecgOrderMain");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"jeecgOrderProductList"})
/*     */   public ModelAndView jeecgOrderProductList(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest req)
/*     */   {
/* 142 */     if (StringUtil.isNotEmpty(jeecgOrderMain.getGoOrderCode())) {
/* 143 */       List jeecgOrderProductEntityList = this.jeecgOrderMainService.findByProperty(JeecgOrderProductEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode());
/* 144 */       req.setAttribute("jeecgOrderProductList", jeecgOrderProductEntityList);
/*     */     }
/* 146 */     return new ModelAndView("jeecg/demo/test/jeecgOrderProductList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"jeecgOrderCustomList"})
/*     */   public ModelAndView jeecgOrderCustomList(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest req)
/*     */   {
/* 156 */     if (StringUtil.isNotEmpty(jeecgOrderMain.getGoOrderCode())) {
/* 157 */       List jeecgJeecgOrderCustomEntityList = this.jeecgOrderMainService.findByProperty(JeecgOrderCustomEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode());
/* 158 */       req.setAttribute("jeecgOrderCustomList", jeecgJeecgOrderCustomEntityList);
/*     */     }
/* 160 */     return new ModelAndView("jeecg/demo/test/jeecgOrderCustomList");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.JeecgOrderMainController
 * JD-Core Version:    0.6.2
 */