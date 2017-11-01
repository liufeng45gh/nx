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
/*     */ import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityUser;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.NoticeAuthorityUserServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/noticeAuthorityUserController"})
/*     */ public class NoticeAuthorityUserController extends BaseController
/*     */ {
/*  41 */   private static final Logger logger = Logger.getLogger(NoticeAuthorityUserController.class);
/*     */ 
/*     */   @Autowired
/*     */   private NoticeAuthorityUserServiceI noticeAuthorityUserService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"noticeAuthorityUser"})
/*     */   public ModelAndView noticeAuthorityUser(String noticeId, HttpServletRequest request)
/*     */   {
/*  56 */     request.setAttribute("noticeId", noticeId);
/*  57 */     return new ModelAndView("system/user/noticeAuthorityUserList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(TSNoticeAuthorityUser noticeAuthorityUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  71 */     CriteriaQuery cq = new CriteriaQuery(TSNoticeAuthorityUser.class, dataGrid);
/*     */ 
/*  73 */     HqlGenerateUtil.installHql(cq, noticeAuthorityUser, request.getParameterMap());
/*     */ 
/*  79 */     cq.add();
/*  80 */     this.noticeAuthorityUserService.getDataGridReturn(cq, true);
/*  81 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDel(TSNoticeAuthorityUser noticeAuthorityUser, HttpServletRequest request)
/*     */   {
/*  92 */     String message = null;
/*  93 */     AjaxJson j = new AjaxJson();
/*  94 */     noticeAuthorityUser = (TSNoticeAuthorityUser)this.systemService.getEntity(TSNoticeAuthorityUser.class, noticeAuthorityUser.getId());
/*  95 */     message = "通知公告用户授权删除成功";
/*     */     try {
/*  97 */       this.noticeAuthorityUserService.delete(noticeAuthorityUser);
/*  98 */       this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */       message = "通知公告用户授权删除失败";
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
/* 118 */     message = "通知公告用户授权删除成功";
/*     */     try {
/* 120 */       for (String id : ids.split(",")) {
/* 121 */         TSNoticeAuthorityUser noticeAuthorityUser = (TSNoticeAuthorityUser)this.systemService.getEntity(TSNoticeAuthorityUser.class, 
/* 122 */           id);
/*     */ 
/* 124 */         this.noticeAuthorityUserService.delete(noticeAuthorityUser);
/* 125 */         this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 128 */       e.printStackTrace();
/* 129 */       message = "通知公告用户授权删除失败";
/* 130 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 132 */     j.setMsg(message);
/* 133 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doAdd"})
/*     */   @ResponseBody
/*     */   public AjaxJson doAdd(TSNoticeAuthorityUser noticeAuthorityUser, HttpServletRequest request)
/*     */   {
/* 146 */     String message = null;
/* 147 */     AjaxJson j = new AjaxJson();
/* 148 */     message = "通知公告用户授权添加成功";
/*     */     try {
/* 150 */       this.noticeAuthorityUserService.save(noticeAuthorityUser);
/* 151 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 153 */       e.printStackTrace();
/* 154 */       message = "通知公告用户授权添加失败";
/* 155 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 157 */     j.setMsg(message);
/* 158 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doSave"})
/*     */   @ResponseBody
/*     */   public AjaxJson doSave(TSNoticeAuthorityUser noticeAuthorityUser, HttpServletRequest request)
/*     */   {
/* 170 */     String message = null;
/* 171 */     AjaxJson j = new AjaxJson();
/* 172 */     message = "通知公告用户授权保存成功";
/*     */     try {
/* 174 */       if (this.noticeAuthorityUserService.checkAuthorityUser(noticeAuthorityUser.getNoticeId(), noticeAuthorityUser.getUser().getId())) {
/* 175 */         message = "该用户已授权，请勿重复操作。";
/*     */       } else {
/* 177 */         this.noticeAuthorityUserService.save(noticeAuthorityUser);
/* 178 */         this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 181 */       e.printStackTrace();
/* 182 */       message = "通知公告用户授权保存失败";
/* 183 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 185 */     j.setMsg(message);
/* 186 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doUpdate"})
/*     */   @ResponseBody
/*     */   public AjaxJson doUpdate(TSNoticeAuthorityUser noticeAuthorityUser, HttpServletRequest request)
/*     */   {
/* 198 */     String message = null;
/* 199 */     AjaxJson j = new AjaxJson();
/* 200 */     message = "通知公告用户授权更新成功";
/* 201 */     TSNoticeAuthorityUser t = (TSNoticeAuthorityUser)this.noticeAuthorityUserService.get(TSNoticeAuthorityUser.class, noticeAuthorityUser.getId());
/*     */     try {
/* 203 */       MyBeanUtils.copyBeanNotNull2Bean(noticeAuthorityUser, t);
/* 204 */       this.noticeAuthorityUserService.saveOrUpdate(t);
/* 205 */       this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 207 */       e.printStackTrace();
/* 208 */       message = "通知公告用户授权更新失败";
/* 209 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 211 */     j.setMsg(message);
/* 212 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goAdd"})
/*     */   public ModelAndView goAdd(TSNoticeAuthorityUser noticeAuthorityUser, HttpServletRequest req)
/*     */   {
/* 223 */     if (StringUtil.isNotEmpty(noticeAuthorityUser.getId())) {
/* 224 */       noticeAuthorityUser = (TSNoticeAuthorityUser)this.noticeAuthorityUserService.getEntity(TSNoticeAuthorityUser.class, noticeAuthorityUser.getId());
/* 225 */       req.setAttribute("noticeAuthorityUserPage", noticeAuthorityUser);
/*     */     }
/* 227 */     return new ModelAndView("system/user/noticeAuthorityUser-add");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goUpdate"})
/*     */   public ModelAndView goUpdate(TSNoticeAuthorityUser noticeAuthorityUser, HttpServletRequest req)
/*     */   {
/* 236 */     if (StringUtil.isNotEmpty(noticeAuthorityUser.getId())) {
/* 237 */       noticeAuthorityUser = (TSNoticeAuthorityUser)this.noticeAuthorityUserService.getEntity(TSNoticeAuthorityUser.class, noticeAuthorityUser.getId());
/* 238 */       req.setAttribute("noticeAuthorityUserPage", noticeAuthorityUser);
/*     */     }
/* 240 */     return new ModelAndView("system/user/noticeAuthorityUser-update");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"selectUser"})
/*     */   public ModelAndView selectUser(HttpServletRequest request)
/*     */   {
/* 250 */     return new ModelAndView("system/user/userList-select");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.NoticeAuthorityUserController
 * JD-Core Version:    0.6.2
 */