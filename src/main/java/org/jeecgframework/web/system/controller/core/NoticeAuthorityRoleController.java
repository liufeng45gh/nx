/*     */ package org.jeecgframework.web.system.controller.core;
/*     */ 
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
/*     */ import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityRole;
/*     */ import org.jeecgframework.web.system.pojo.base.TSRole;
/*     */ import org.jeecgframework.web.system.service.NoticeAuthorityRoleServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/noticeAuthorityRoleController"})
/*     */ public class NoticeAuthorityRoleController extends BaseController
/*     */ {
/*  41 */   private static final Logger logger = Logger.getLogger(NoticeAuthorityRoleController.class);
/*     */ 
/*     */   @Autowired
/*     */   private NoticeAuthorityRoleServiceI noticeAuthorityRoleService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"noticeAuthorityRole"})
/*     */   public ModelAndView noticeAuthorityRole(String noticeId, HttpServletRequest request)
/*     */   {
/*  56 */     request.setAttribute("noticeId", noticeId);
/*  57 */     return new ModelAndView("system/user/noticeAuthorityRoleList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(TSNoticeAuthorityRole noticeAuthorityRole, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  71 */     CriteriaQuery cq = new CriteriaQuery(TSNoticeAuthorityRole.class, dataGrid);
/*     */ 
/*  73 */     HqlGenerateUtil.installHql(cq, noticeAuthorityRole, request.getParameterMap());
/*     */ 
/*  79 */     cq.add();
/*  80 */     this.noticeAuthorityRoleService.getDataGridReturn(cq, true);
/*  81 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDel(TSNoticeAuthorityRole noticeAuthorityRole, HttpServletRequest request)
/*     */   {
/*  92 */     String message = null;
/*  93 */     AjaxJson j = new AjaxJson();
/*  94 */     noticeAuthorityRole = (TSNoticeAuthorityRole)this.systemService.getEntity(TSNoticeAuthorityRole.class, noticeAuthorityRole.getId());
/*  95 */     message = "通知公告角色授权删除成功";
/*     */     try {
/*  97 */       this.noticeAuthorityRoleService.delete(noticeAuthorityRole);
/*  98 */       this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */       message = "通知公告角色授权删除失败";
/* 102 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 104 */     j.setMsg(message);
/* 105 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doBatchDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doBatchDel(String ids, HttpServletRequest request)
/*     */   {
/* 116 */     String message = null;
/* 117 */     AjaxJson j = new AjaxJson();
/* 118 */     message = "通知公告角色授权删除成功";
/*     */     try {
/* 120 */       for (String id : ids.split(",")) {
/* 121 */         TSNoticeAuthorityRole noticeAuthorityRole = (TSNoticeAuthorityRole)this.systemService.getEntity(TSNoticeAuthorityRole.class, 
/* 122 */           id);
/*     */ 
/* 124 */         this.noticeAuthorityRoleService.delete(noticeAuthorityRole);
/* 125 */         this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 128 */       e.printStackTrace();
/* 129 */       message = "通知公告角色授权删除失败";
/* 130 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 132 */     j.setMsg(message);
/* 133 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doAdd"})
/*     */   @ResponseBody
/*     */   public AjaxJson doAdd(TSNoticeAuthorityRole noticeAuthorityRole, HttpServletRequest request)
/*     */   {
/* 146 */     String message = null;
/* 147 */     AjaxJson j = new AjaxJson();
/* 148 */     message = "通知公告角色授权添加成功";
/*     */     try {
/* 150 */       this.noticeAuthorityRoleService.save(noticeAuthorityRole);
/* 151 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 153 */       e.printStackTrace();
/* 154 */       message = "通知公告角色授权添加失败";
/* 155 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 157 */     j.setMsg(message);
/* 158 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doUpdate"})
/*     */   @ResponseBody
/*     */   public AjaxJson doUpdate(TSNoticeAuthorityRole noticeAuthorityRole, HttpServletRequest request)
/*     */   {
/* 170 */     String message = null;
/* 171 */     AjaxJson j = new AjaxJson();
/* 172 */     message = "通知公告角色授权更新成功";
/* 173 */     TSNoticeAuthorityRole t = (TSNoticeAuthorityRole)this.noticeAuthorityRoleService.get(TSNoticeAuthorityRole.class, noticeAuthorityRole.getId());
/*     */     try {
/* 175 */       MyBeanUtils.copyBeanNotNull2Bean(noticeAuthorityRole, t);
/* 176 */       this.noticeAuthorityRoleService.saveOrUpdate(t);
/* 177 */       this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 179 */       e.printStackTrace();
/* 180 */       message = "通知公告角色授权更新失败";
/* 181 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 183 */     j.setMsg(message);
/* 184 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goAdd"})
/*     */   public ModelAndView goAdd(TSNoticeAuthorityRole noticeAuthorityRole, HttpServletRequest req)
/*     */   {
/* 195 */     if (StringUtil.isNotEmpty(noticeAuthorityRole.getId())) {
/* 196 */       noticeAuthorityRole = (TSNoticeAuthorityRole)this.noticeAuthorityRoleService.getEntity(TSNoticeAuthorityRole.class, noticeAuthorityRole.getId());
/* 197 */       req.setAttribute("noticeAuthorityRolePage", noticeAuthorityRole);
/*     */     }
/* 199 */     return new ModelAndView("system/user/noticeAuthorityRole-add");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goUpdate"})
/*     */   public ModelAndView goUpdate(TSNoticeAuthorityRole noticeAuthorityRole, HttpServletRequest req)
/*     */   {
/* 208 */     if (StringUtil.isNotEmpty(noticeAuthorityRole.getId())) {
/* 209 */       noticeAuthorityRole = (TSNoticeAuthorityRole)this.noticeAuthorityRoleService.getEntity(TSNoticeAuthorityRole.class, noticeAuthorityRole.getId());
/* 210 */       req.setAttribute("noticeAuthorityRolePage", noticeAuthorityRole);
/*     */     }
/* 212 */     return new ModelAndView("system/user/noticeAuthorityRole-update");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"selectRole"})
/*     */   public ModelAndView selectUser(HttpServletRequest request)
/*     */   {
/* 222 */     return new ModelAndView("system/role/roleList-select");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doSave"})
/*     */   @ResponseBody
/*     */   public AjaxJson doSave(TSNoticeAuthorityRole noticeAuthorityRole, HttpServletRequest request)
/*     */   {
/* 234 */     String message = null;
/* 235 */     AjaxJson j = new AjaxJson();
/* 236 */     message = "通知公告角色授权保存成功";
/*     */     try {
/* 238 */       if (this.noticeAuthorityRoleService.checkAuthorityRole(noticeAuthorityRole.getNoticeId(), noticeAuthorityRole.getRole().getId())) {
/* 239 */         message = "该角色已授权，请勿重复操作。";
/*     */       } else {
/* 241 */         this.noticeAuthorityRoleService.save(noticeAuthorityRole);
/* 242 */         this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 245 */       e.printStackTrace();
/* 246 */       message = "通知公告角色授权保存失败";
/* 247 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 249 */     j.setMsg(message);
/* 250 */     return j;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.NoticeAuthorityRoleController
 * JD-Core Version:    0.6.2
 */