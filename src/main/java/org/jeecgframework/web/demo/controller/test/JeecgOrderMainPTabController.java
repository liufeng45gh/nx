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
/*     */ @RequestMapping({"/jeecgOrderMainPTabController"})
/*     */ public class JeecgOrderMainPTabController extends BaseController
/*     */ {
/*  43 */   private static final Logger logger = Logger.getLogger(JeecgOrderMainPTabController.class);
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
/*  58 */     return new ModelAndView("jeecg/demo/test/one2manyptab/jeecgOrderMainList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  72 */     CriteriaQuery cq = new CriteriaQuery(JeecgOrderMainEntity.class, dataGrid);
/*  73 */     this.jeecgOrderMainService.getDataGridReturn(cq, true);
/*  74 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest request)
/*     */   {
/*  85 */     String message = null;
/*  86 */     AjaxJson j = new AjaxJson();
/*  87 */     jeecgOrderMain = (JeecgOrderMainEntity)this.systemService.getEntity(JeecgOrderMainEntity.class, jeecgOrderMain.getId());
/*  88 */     message = "删除成功";
/*  89 */     this.jeecgOrderMainService.delMain(jeecgOrderMain);
/*  90 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*  91 */     j.setMsg(message);
/*  92 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(JeecgOrderMainEntity jeecgOrderMain, JeecgOrderMainPage jeecgOrderMainPage, HttpServletRequest request)
/*     */   {
/* 106 */     String message = null;
/* 107 */     List jeecgOrderProducList = jeecgOrderMainPage.getJeecgOrderProductList();
/* 108 */     List jeecgOrderCustomList = jeecgOrderMainPage.getJeecgOrderCustomList();
/* 109 */     Boolean jeecgOrderCustomShow = Boolean.valueOf("true".equals(request.getParameter("jeecgOrderCustomShow")));
/* 110 */     AjaxJson j = new AjaxJson();
/* 111 */     if (StringUtil.isNotEmpty(jeecgOrderMain.getId())) {
/* 112 */       message = "更新成功";
/* 113 */       this.jeecgOrderMainService.updateMain(jeecgOrderMain, jeecgOrderProducList, jeecgOrderCustomList, jeecgOrderCustomShow.booleanValue());
/* 114 */       this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } else {
/* 116 */       message = "添加成功";
/* 117 */       this.jeecgOrderMainService.addMain(jeecgOrderMain, jeecgOrderProducList, jeecgOrderCustomList);
/* 118 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/* 120 */     j.setMsg(message);
/* 121 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public ModelAndView addorupdate(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest req)
/*     */   {
/* 130 */     if (StringUtil.isNotEmpty(jeecgOrderMain.getId())) {
/* 131 */       jeecgOrderMain = (JeecgOrderMainEntity)this.jeecgOrderMainService.getEntity(JeecgOrderMainEntity.class, jeecgOrderMain.getId());
/* 132 */       req.setAttribute("jeecgOrderMainPage", jeecgOrderMain);
/*     */     }
/* 134 */     return new ModelAndView("jeecg/demo/test/one2manyptab/jeecgOrderMain");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"jeecgOrderProductList"})
/*     */   public ModelAndView jeecgOrderProductList(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest req)
/*     */   {
/* 143 */     if (StringUtil.isNotEmpty(jeecgOrderMain.getGoOrderCode())) {
/* 144 */       List jeecgOrderProductEntityList = this.jeecgOrderMainService.findByProperty(JeecgOrderProductEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode());
/* 145 */       req.setAttribute("jeecgOrderProductList", jeecgOrderProductEntityList);
/*     */     }
/* 147 */     return new ModelAndView("jeecg/demo/test/one2manyptab/jeecgOrderProductList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"jeecgOrderCustomList"})
/*     */   public ModelAndView jeecgOrderCustomList(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest req)
/*     */   {
/* 157 */     if (StringUtil.isNotEmpty(jeecgOrderMain.getGoOrderCode())) {
/* 158 */       List jeecgJeecgOrderCustomEntityList = this.jeecgOrderMainService.findByProperty(JeecgOrderCustomEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode());
/* 159 */       req.setAttribute("jeecgOrderCustomList", jeecgJeecgOrderCustomEntityList);
/*     */     }
/* 161 */     return new ModelAndView("jeecg/demo/test/one2manyptab/jeecgOrderCustomList");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.JeecgOrderMainPTabController
 * JD-Core Version:    0.6.2
 */