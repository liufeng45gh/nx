/*     */ package org.jeecgframework.web.system.controller.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.ComboTree;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.common.model.json.TreeGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*     */ import org.jeecgframework.core.util.MutiLangUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
/*     */ import org.jeecgframework.tag.vo.easyui.TreeGridModel;
/*     */ import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
/*     */ import org.jeecgframework.web.system.pojo.base.TSIcon;
/*     */ import org.jeecgframework.web.system.service.CategoryServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/categoryController"})
/*     */ public class CategoryController extends BaseController
/*     */ {
/*  48 */   private static final Logger logger = Logger.getLogger(CategoryController.class);
/*     */   private static final String CATEGORY_LIST = "system/category/categoryList";
/*     */   private static final String CATEGORY_ADD_OR_UPDATE = "system/category/category";
/*     */ 
/*     */   @Autowired
/*     */   private CategoryServiceI categoryService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"category"})
/*     */   public String category(HttpServletRequest request)
/*     */   {
/*  66 */     return "system/category/categoryList";
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   @ResponseBody
/*     */   public List<TreeGrid> datagrid(TSCategoryEntity category, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  84 */     CriteriaQuery cq = new CriteriaQuery(TSCategoryEntity.class, dataGrid);
/*  85 */     if ((category.getId() == null) || (StringUtils.isEmpty(category.getId()))) {
/*  86 */       cq.isNull("parent");
/*     */     } else {
/*  88 */       cq.eq("parent.code", category.getId());
/*  89 */       category.setId(null);
/*     */     }
/*     */ 
/*  92 */     HqlGenerateUtil.installHql(cq, 
/*  93 */       category, request.getParameterMap());
/*  94 */     List list = this.categoryService
/*  95 */       .getListByCriteriaQuery(cq, Boolean.valueOf(false));
/*  96 */     List treeGrids = new ArrayList();
/*  97 */     TreeGridModel treeGridModel = new TreeGridModel();
/*  98 */     treeGridModel.setIdField("code");
/*  99 */     treeGridModel.setSrc("id");
/* 100 */     treeGridModel.setTextField("name");
/* 101 */     treeGridModel.setIcon("icon_iconPath");
/* 102 */     treeGridModel.setParentText("parent_name");
/* 103 */     treeGridModel.setParentId("parent_code");
/* 104 */     treeGridModel.setChildList("list");
/* 105 */     treeGrids = this.systemService.treegrid(list, treeGridModel);
/* 106 */     return treeGrids;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"del"})
/*     */   @ResponseBody
/*     */   public AjaxJson del(TSCategoryEntity tSCategory, HttpServletRequest request)
/*     */   {
/* 117 */     AjaxJson j = new AjaxJson();
/* 118 */     tSCategory = (TSCategoryEntity)this.systemService.getEntity(TSCategoryEntity.class, 
/* 119 */       tSCategory.getId());
/* 120 */     j.setMsg("分类管理删除成功");
/* 121 */     this.categoryService.delete(tSCategory);
/* 122 */     this.systemService.addLog(j.getMsg(), Globals.Log_Type_DEL, 
/* 123 */       Globals.Log_Leavel_INFO);
/* 124 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"save"})
/*     */   @ResponseBody
/*     */   public AjaxJson save(TSCategoryEntity category, HttpServletRequest request)
/*     */   {
/* 136 */     AjaxJson j = new AjaxJson();
/* 137 */     if (StringUtil.isNotEmpty(category.getId())) {
/* 138 */       j.setMsg("分类管理更新成功");
/* 139 */       TSCategoryEntity t = (TSCategoryEntity)this.categoryService.get(TSCategoryEntity.class, 
/* 140 */         category.getId());
/*     */ 
/* 142 */       category.getParent().setCode("".equals(t.getParent().getCode()) ? null : t.getParent().getCode());
/*     */       try
/*     */       {
/* 145 */         MyBeanUtils.copyBeanNotNull2Bean(category, t);
/* 146 */         this.categoryService.saveOrUpdate(t);
/* 147 */         this.systemService.addLog(j.getMsg(), Globals.Log_Type_UPDATE, 
/* 148 */           Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 150 */         logger.error(e.getMessage(), e.fillInStackTrace());
/* 151 */         j.setMsg("分类管理更新失败");
/*     */       }
/*     */     } else {
/* 154 */       j.setMsg("分类管理添加成功");
/* 155 */       this.categoryService.saveCategory(category);
/* 156 */       this.systemService.addLog(j.getMsg(), Globals.Log_Type_INSERT, 
/* 157 */         Globals.Log_Leavel_INFO);
/*     */     }
/* 159 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"addorupdate"})
/*     */   public String addorupdate(ModelMap map, TSCategoryEntity category)
/*     */   {
/* 169 */     if (StringUtil.isNotEmpty(category.getCode())) {
/* 170 */       category = (TSCategoryEntity)this.categoryService.findUniqueByProperty(TSCategoryEntity.class, 
/* 171 */         "code", category.getCode());
/* 172 */       map.put("categoryPage", category);
/*     */     }
/* 174 */     map.put("iconlist", this.systemService.findByProperty(TSIcon.class, 
/* 175 */       "iconType", Short.valueOf((short)1)));
/* 176 */     if ((category.getParent() != null) && 
/* 177 */       (StringUtil.isNotEmpty(category.getParent().getCode()))) {
/* 178 */       TSCategoryEntity parent = (TSCategoryEntity)this.categoryService.findUniqueByProperty(TSCategoryEntity.class, "code", category.getParent().getCode());
/* 179 */       category.setParent(parent);
/* 180 */       map.put("categoryPage", category);
/*     */     }
/* 182 */     return "system/category/category";
/*     */   }
/*     */   @RequestMapping(params={"combotree"})
/*     */   @ResponseBody
/*     */   public List<ComboTree> combotree(String selfCode, ComboTree comboTree) {
/* 188 */     CriteriaQuery cq = new CriteriaQuery(TSCategoryEntity.class);
/* 189 */     if (StringUtils.isNotEmpty(comboTree.getId())) {
/* 190 */       cq.createAlias("parent", "parent");
/* 191 */       cq.eq("parent.code", comboTree.getId());
/* 192 */     } else if (StringUtils.isNotEmpty(selfCode)) {
/* 193 */       cq.eq("code", selfCode);
/*     */     } else {
/* 195 */       cq.isNull("parent");
/*     */     }
/* 197 */     cq.add();
/* 198 */     List categoryList = this.systemService
/* 199 */       .getListByCriteriaQuery(cq, Boolean.valueOf(false));
/* 200 */     List comboTrees = new ArrayList();
/* 201 */     ComboTreeModel comboTreeModel = new ComboTreeModel("code", "name", "list");
/* 202 */     comboTrees = this.systemService.ComboTree(categoryList, comboTreeModel, 
/* 203 */       null, false);
/* 204 */     MutiLangUtil.setMutiTree(comboTrees);
/* 205 */     return comboTrees;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"tree"})
/*     */   @ResponseBody
/*     */   public List<ComboTree> tree(String selfCode, ComboTree comboTree, boolean isNew)
/*     */   {
/* 217 */     CriteriaQuery cq = new CriteriaQuery(TSCategoryEntity.class);
/* 218 */     if (StringUtils.isNotEmpty(comboTree.getId())) {
/* 219 */       cq.createAlias("parent", "parent");
/* 220 */       cq.eq("parent.code", comboTree.getId());
/* 221 */     } else if (StringUtils.isNotEmpty(selfCode)) {
/* 222 */       cq.eq("code", selfCode);
/*     */     } else {
/* 224 */       cq.isNull("parent");
/*     */     }
/* 226 */     cq.add();
/* 227 */     List categoryList = this.systemService
/* 228 */       .getListByCriteriaQuery(cq, Boolean.valueOf(false));
/* 229 */     List comboTrees = new ArrayList();
/* 230 */     for (int i = 0; i < categoryList.size(); i++) {
/* 231 */       comboTrees.add(categoryConvertToTree((TSCategoryEntity)categoryList.get(i)));
/*     */     }
/* 233 */     return comboTrees;
/*     */   }
/*     */ 
/*     */   private ComboTree categoryConvertToTree(TSCategoryEntity entity) {
/* 237 */     ComboTree tree = new ComboTree();
/* 238 */     tree.setId(entity.getCode());
/* 239 */     tree.setText(entity.getName());
/* 240 */     tree.setIconCls(entity.getIcon().getIconClas());
/* 241 */     if ((entity.getList() != null) && (entity.getList().size() > 0)) {
/* 242 */       List comboTrees = new ArrayList();
/* 243 */       for (int i = 0; i < entity.getList().size(); i++) {
/* 244 */         comboTrees.add(categoryConvertToTree((TSCategoryEntity)entity.getList().get(
/* 245 */           i)));
/*     */       }
/* 247 */       tree.setChildren(comboTrees);
/*     */     }
/* 249 */     return tree;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.CategoryController
 * JD-Core Version:    0.6.2
 */