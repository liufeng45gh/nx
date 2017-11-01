/*     */ package org.jeecgframework.web.cgform.controller.autoform;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang3.StringUtils;
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
/*     */ import org.jeecgframework.web.cgform.entity.autoform.AutoFormStyleEntity;
/*     */ import org.jeecgframework.web.cgform.service.autoform.AutoFormStyleServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/autoFormStyleController"})
/*     */ public class AutoFormStyleController extends BaseController
/*     */ {
/*  51 */   private static final Logger logger = Logger.getLogger(AutoFormStyleController.class);
/*     */ 
/*     */   @Autowired
/*     */   private AutoFormStyleServiceI autoFormStyleService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"autoFormStyle"})
/*     */   public ModelAndView autoFormStyle(HttpServletRequest request)
/*     */   {
/*  66 */     return new ModelAndView("jeecg/cgform/autoform/autoFormStyleList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(AutoFormStyleEntity autoFormStyle, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  80 */     CriteriaQuery cq = new CriteriaQuery(AutoFormStyleEntity.class, dataGrid);
/*     */ 
/*  82 */     HqlGenerateUtil.installHql(cq, autoFormStyle, request.getParameterMap());
/*     */ 
/*  88 */     cq.add();
/*  89 */     this.autoFormStyleService.getDataGridReturn(cq, true);
/*  90 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDel(AutoFormStyleEntity autoFormStyle, HttpServletRequest request)
/*     */   {
/* 102 */     AjaxJson j = new AjaxJson();
/* 103 */     autoFormStyle = (AutoFormStyleEntity)this.systemService.getEntity(AutoFormStyleEntity.class, autoFormStyle.getId());
/* 104 */     String message = "表单样式表删除成功";
/*     */     try {
/* 106 */       this.autoFormStyleService.delete(autoFormStyle);
/* 107 */       this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 109 */       e.printStackTrace();
/* 110 */       message = "表单样式表删除失败";
/* 111 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 113 */     j.setMsg(message);
/* 114 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doBatchDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doBatchDel(String ids, HttpServletRequest request)
/*     */   {
/* 126 */     AjaxJson j = new AjaxJson();
/* 127 */     String message = "表单样式表删除成功";
/*     */     try {
/* 129 */       for (String id : ids.split(",")) {
/* 130 */         AutoFormStyleEntity autoFormStyle = (AutoFormStyleEntity)this.systemService.getEntity(AutoFormStyleEntity.class, 
/* 131 */           id);
/*     */ 
/* 133 */         this.autoFormStyleService.delete(autoFormStyle);
/* 134 */         this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 137 */       e.printStackTrace();
/* 138 */       message = "表单样式表删除失败";
/* 139 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 141 */     j.setMsg(message);
/* 142 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doAdd"})
/*     */   @ResponseBody
/*     */   public AjaxJson doAdd(AutoFormStyleEntity autoFormStyle, HttpServletRequest request)
/*     */   {
/* 156 */     AjaxJson j = new AjaxJson();
/* 157 */     String message = "表单样式表添加成功";
/*     */     try {
/* 159 */       this.autoFormStyleService.save(autoFormStyle);
/* 160 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 162 */       e.printStackTrace();
/* 163 */       message = "表单样式表添加失败";
/* 164 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 166 */     j.setMsg(message);
/* 167 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doUpdate"})
/*     */   @ResponseBody
/*     */   public AjaxJson doUpdate(AutoFormStyleEntity autoFormStyle, HttpServletRequest request)
/*     */   {
/* 180 */     AjaxJson j = new AjaxJson();
/* 181 */     String message = "表单样式表更新成功";
/* 182 */     AutoFormStyleEntity t = (AutoFormStyleEntity)this.autoFormStyleService.get(AutoFormStyleEntity.class, autoFormStyle.getId());
/*     */     try {
/* 184 */       MyBeanUtils.copyBeanNotNull2Bean(autoFormStyle, t);
/* 185 */       this.autoFormStyleService.saveOrUpdate(t);
/* 186 */       this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 188 */       e.printStackTrace();
/* 189 */       message = "表单样式表更新失败";
/* 190 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 192 */     j.setMsg(message);
/* 193 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goAdd"})
/*     */   public ModelAndView goAdd(AutoFormStyleEntity autoFormStyle, HttpServletRequest req)
/*     */   {
/* 204 */     if (StringUtil.isNotEmpty(autoFormStyle.getId())) {
/* 205 */       autoFormStyle = (AutoFormStyleEntity)this.autoFormStyleService.getEntity(AutoFormStyleEntity.class, autoFormStyle.getId());
/* 206 */       req.setAttribute("autoFormStylePage", autoFormStyle);
/*     */     }
/* 208 */     return new ModelAndView("jeecg/cgform/autoform/autoFormStyle-add");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goUpdate"})
/*     */   public ModelAndView goUpdate(AutoFormStyleEntity autoFormStyle, HttpServletRequest req)
/*     */   {
/* 217 */     if (StringUtil.isNotEmpty(autoFormStyle.getId())) {
/* 218 */       autoFormStyle = (AutoFormStyleEntity)this.autoFormStyleService.getEntity(AutoFormStyleEntity.class, autoFormStyle.getId());
/* 219 */       req.setAttribute("autoFormStylePage", autoFormStyle);
/*     */     }
/* 221 */     return new ModelAndView("jeecg/cgform/autoform/autoFormStyle-update");
/*     */   }
/*     */ 
/*     */   @ResponseBody
/*     */   @RequestMapping(params={"checkStyleNm"})
/*     */   public JSONObject checkStyleNm(AutoFormStyleEntity autoFormStyle, HttpServletRequest req) {
/* 228 */     JSONObject jsonObject = new JSONObject();
/*     */ 
/* 230 */     String param = req.getParameter("param");
/*     */ 
/* 232 */     List list = new ArrayList();
/* 233 */     String hql = "";
/* 234 */     if (StringUtils.isNotBlank(autoFormStyle.getId())) {
/* 235 */       hql = "from AutoFormStyleEntity t where t.id != ? and t.styleDesc = ?";
/* 236 */       list = this.systemService.findHql(hql, new Object[] { autoFormStyle.getId(), param });
/*     */     }
/*     */     else {
/* 239 */       hql = "from AutoFormStyleEntity t where t.styleDesc = ?";
/* 240 */       list = this.systemService.findHql(hql, new Object[] { param });
/*     */     }
/*     */ 
/* 243 */     if (list.size() > 0) {
/* 244 */       jsonObject.put("status", "n");
/* 245 */       jsonObject.put("info", "样式名称重复，请重新输入！");
/* 246 */       return jsonObject;
/*     */     }
/* 248 */     jsonObject.put("status", "y");
/* 249 */     return jsonObject;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.controller.autoform.AutoFormStyleController
 * JD-Core Version:    0.6.2
 */