/*     */ package org.jeecgframework.web.demo.controller.test;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.ComboTree;
/*     */ import org.jeecgframework.core.common.model.json.TreeGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
/*     */ import org.jeecgframework.tag.vo.easyui.TreeGridModel;
/*     */ import org.jeecgframework.web.demo.entity.test.JeecgMatterBom;
/*     */ import org.jeecgframework.web.demo.service.test.JeecgMatterBomServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/jeecgMatterBomController"})
/*     */ public class JeecgMatterBomController extends BaseController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private JeecgMatterBomServiceI jeecgMatterBomService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"goList"})
/*     */   public ModelAndView goList(HttpServletRequest request)
/*     */   {
/*  62 */     return new ModelAndView("jeecg/demo/test/jeecgMatterBomList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doTreeGrid"})
/*     */   @ResponseBody
/*     */   public List<TreeGrid> doTreeGrid(JeecgMatterBom entity, HttpServletRequest request, HttpServletResponse response, TreeGrid treegrid)
/*     */   {
/*  82 */     CriteriaQuery cq = new CriteriaQuery(JeecgMatterBom.class);
/*  83 */     if ("yes".equals(request.getParameter("isSearch"))) {
/*  84 */       treegrid.setId(null);
/*  85 */       entity.setId(null);
/*     */     }
/*  87 */     if (entity.getCode() != null) {
/*  88 */       HqlGenerateUtil.installHql(cq, entity);
/*     */     }
/*  90 */     if (treegrid.getId() != null)
/*  91 */       cq.eq("parent.id", treegrid.getId());
/*     */     else {
/*  93 */       cq.isNull("parent");
/*     */     }
/*  95 */     cq.add();
/*  96 */     List list = this.jeecgMatterBomService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
/*  97 */     if ((list.size() == 0) && (entity.getCode() != null)) {
/*  98 */       cq = new CriteriaQuery(JeecgMatterBom.class);
/*  99 */       JeecgMatterBom parent = new JeecgMatterBom();
/* 100 */       entity.setParent(parent);
/* 101 */       HqlGenerateUtil.installHql(cq, entity);
/* 102 */       list = this.jeecgMatterBomService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
/*     */     }
/* 104 */     List treeGrids = new ArrayList();
/* 105 */     TreeGridModel treeGridModel = new TreeGridModel();
/* 106 */     treeGridModel.setTextField("name");
/* 107 */     treeGridModel.setParentText("parent_name");
/* 108 */     treeGridModel.setParentId("parent_id");
/* 109 */     treeGridModel.setSrc("code");
/* 110 */     treeGridModel.setIdField("id");
/* 111 */     treeGridModel.setChildList("children");
/* 112 */     treeGrids = this.jeecgMatterBomService.treegrid(list, treeGridModel);
/* 113 */     return treeGrids;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goEdit"})
/*     */   public ModelAndView goEdit(JeecgMatterBom entity, HttpServletRequest request)
/*     */   {
/* 130 */     if (StringUtil.isNotEmpty(entity.getId())) {
/* 131 */       entity = (JeecgMatterBom)this.jeecgMatterBomService.getEntity(JeecgMatterBom.class, entity.getId());
/* 132 */       request.setAttribute("entity", entity);
/*     */     }
/* 134 */     return new ModelAndView("jeecg/demo/test/jeecgMatterBom");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getChildren"})
/*     */   @ResponseBody
/*     */   public List<ComboTree> getChildren(HttpServletRequest request, ComboTree comboTree)
/*     */   {
/* 152 */     CriteriaQuery cq = new CriteriaQuery(JeecgMatterBom.class);
/* 153 */     if (comboTree.getId() != null)
/* 154 */       cq.eq("parent.id", comboTree.getId());
/*     */     else {
/* 156 */       cq.isNull("parent");
/*     */     }
/* 158 */     cq.add();
/* 159 */     List list = this.jeecgMatterBomService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
/* 160 */     ComboTreeModel comboTreeModel = new ComboTreeModel("id", "name", "children");
/* 161 */     List comboTrees = this.systemService.ComboTree(list, comboTreeModel, null, false);
/* 162 */     return comboTrees;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doSave"})
/*     */   @ResponseBody
/*     */   public AjaxJson doSave(JeecgMatterBom entity, HttpServletRequest request)
/*     */   {
/* 181 */     String message = null;
/* 182 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 184 */     String parentId = request.getParameter("parent.id");
/* 185 */     if (StringUtil.isEmpty(parentId)) {
/* 186 */       entity.setParent(null);
/*     */     }
/* 188 */     if (StringUtil.isNotEmpty(entity.getId())) {
/* 189 */       message = "更新成功";
/* 190 */       JeecgMatterBom t = (JeecgMatterBom)this.jeecgMatterBomService.get(JeecgMatterBom.class, entity.getId());
/*     */       try {
/* 192 */         MyBeanUtils.copyBeanNotNull2Bean(entity, t);
/* 193 */         this.jeecgMatterBomService.saveOrUpdate(t);
/* 194 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 196 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 199 */       message = "添加成功";
/* 200 */       this.jeecgMatterBomService.save(entity);
/* 201 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     }
/*     */ 
/* 204 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDelete"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDelete(JeecgMatterBom entity, HttpServletRequest request)
/*     */   {
/* 222 */     String message = null;
/* 223 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 225 */     this.jeecgMatterBomService.deleteEntityById(JeecgMatterBom.class, entity.getId());
/*     */ 
/* 227 */     message = "删除成功";
/* 228 */     this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */ 
/* 230 */     j.setMsg(message);
/* 231 */     return j;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.JeecgMatterBomController
 * JD-Core Version:    0.6.2
 */