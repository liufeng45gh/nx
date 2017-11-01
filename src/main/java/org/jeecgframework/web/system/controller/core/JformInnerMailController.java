/*     */ package org.jeecgframework.web.system.controller.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.exception.BusinessException;
/*     */ import org.jeecgframework.core.common.model.common.UploadFile;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.web.system.manager.ClientManager;
/*     */ import org.jeecgframework.web.system.pojo.base.Client;
/*     */ import org.jeecgframework.web.system.pojo.base.JformInnerMailAttach;
/*     */ import org.jeecgframework.web.system.pojo.base.JformInnerMailEntity;
/*     */ import org.jeecgframework.web.system.pojo.base.JformInnerMailReceiverEntity;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.JformInnerMailServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import ssb.warmline.business.commons.utils.DateUtils;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/jformInnerMailController"})
/*     */ public class JformInnerMailController extends BaseController
/*     */ {
/*  57 */   private static final Logger logger = Logger.getLogger(JformInnerMailController.class);
/*     */ 
/*     */   @Autowired
/*     */   private JformInnerMailServiceI jformInnerMailService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @RequestMapping(params={"doSave"})
/*     */   @ResponseBody
/*     */   public AjaxJson doSave(JformInnerMailEntity jformInnerMail, HttpServletRequest request)
/*     */   {
/*  73 */     String message = null;
/*  74 */     AjaxJson j = new AjaxJson();
/*  75 */     message = "内部邮件添加成功";
/*     */     try
/*     */     {
/*  78 */       if (!StringUtils.isEmpty(jformInnerMail.getId())) {
/*  79 */         JformInnerMailEntity t = (JformInnerMailEntity)this.jformInnerMailService.get(JformInnerMailEntity.class, jformInnerMail.getId());
/*  80 */         MyBeanUtils.copyBeanNotNull2Bean(jformInnerMail, t);
/*  81 */         this.jformInnerMailService.saveOrUpdate(t);
/*     */       }
/*     */       else
/*     */       {
/*  85 */         this.jformInnerMailService.save(jformInnerMail);
/*     */       }
/*     */ 
/*  88 */       saveMailReceiver(jformInnerMail);
/*     */     } catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */       message = "内部邮件保存失败";
/*  92 */       throw new BusinessException(e.getMessage());
/*     */     }
/*  94 */     j.setMsg(message);
/*  95 */     j.setObj(jformInnerMail);
/*  96 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"receivers"})
/*     */   public ModelAndView roles(HttpServletRequest request)
/*     */   {
/* 106 */     ModelAndView mv = new ModelAndView("system/mail/selectReceiver");
/* 107 */     String ids = oConvertUtils.getString(request.getParameter("ids"));
/* 108 */     mv.addObject("ids", ids);
/* 109 */     return mv;
/*     */   }
/*     */ 
/*     */   protected void saveMailReceiver(JformInnerMailEntity mail)
/*     */   {
/* 117 */     String[] userids = mail.getReceiverIds().split(",");
/*     */ 
/* 119 */     this.systemService.deleteAllEntitie(this.systemService.findByProperty(JformInnerMailReceiverEntity.class, "JMail.id", mail.getId()));
/* 120 */     for (int i = 0; i < userids.length; i++) {
/* 121 */       JformInnerMailReceiverEntity mailReceiver = new JformInnerMailReceiverEntity();
/* 122 */       mailReceiver.setJMail(mail);
/* 123 */       mailReceiver.setCreateDate(new Date());
/* 124 */       mailReceiver.setStatus("00");
/* 125 */       TSUser tsUser = new TSUser();
/* 126 */       tsUser.setId(userids[i]);
/* 127 */       mailReceiver.setTSUser(tsUser);
/* 128 */       this.systemService.save(mailReceiver);
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goAddOrUpdate"})
/*     */   public ModelAndView goAddOrUpdate(JformInnerMailEntity jformInnerMail, HttpServletRequest req)
/*     */   {
/* 139 */     if (StringUtil.isNotEmpty(jformInnerMail.getId())) {
/* 140 */       jformInnerMail = (JformInnerMailEntity)this.jformInnerMailService.getEntity(JformInnerMailEntity.class, jformInnerMail.getId());
/* 141 */       List documents = this.systemService.findByProperty(JformInnerMailAttach.class, "mailid", jformInnerMail.getId());
/* 142 */       req.setAttribute("documents", documents);
/* 143 */       req.setAttribute("jformInnerMailPage", jformInnerMail);
/*     */     }
/* 145 */     String clickFunctionId = req.getParameter("clickFunctionId");
/* 146 */     req.setAttribute("clickFunctionId", clickFunctionId);
/* 147 */     return new ModelAndView("system/mail/jformInnerMail-update");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goDetail"})
/*     */   public ModelAndView goDetail(JformInnerMailEntity jformInnerMail, HttpServletRequest req)
/*     */   {
/* 157 */     if (StringUtil.isNotEmpty(jformInnerMail.getId())) {
/* 158 */       jformInnerMail = (JformInnerMailEntity)this.jformInnerMailService.getEntity(JformInnerMailEntity.class, jformInnerMail.getId());
/* 159 */       List documents = this.systemService.findByProperty(JformInnerMailAttach.class, "mailid", jformInnerMail.getId());
/* 160 */       req.setAttribute("documents", documents);
/* 161 */       req.setAttribute("jformInnerMailPage", jformInnerMail);
/*     */     }
/* 163 */     return new ModelAndView("system/mail/jformInnerMail-detail");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goSendMails"})
/*     */   public ModelAndView goSendMails(HttpServletRequest request, String type)
/*     */   {
/* 172 */     return new ModelAndView("system/mail/jformInnerMailSendList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getSendMails"})
/*     */   @ResponseBody
/*     */   public List<Map<String, Object>> getSendMails(String title)
/*     */   {
/* 181 */     String account = ClientManager.getInstance().getClient().getUser().getUserName();
/*     */ 
/* 183 */     StringBuffer sqlb = new StringBuffer("from JformInnerMailEntity where status ='01' and  createBy ='" + account + "'");
/* 184 */     if (StringUtils.isNotEmpty(title)) {
/* 185 */       sqlb.append(" and title like '%" + title + "%'");
/*     */     }
/*     */ 
/* 188 */     List mails = this.systemService.findByQueryString(sqlb.toString());
/* 189 */     List ret = new ArrayList();
/* 190 */     for (int i = 0; i < mails.size(); i++) {
/* 191 */       Map m = new HashMap();
/* 192 */       m.put("id", ((JformInnerMailEntity)mails.get(i)).getId());
/* 193 */       m.put("title", ((JformInnerMailEntity)mails.get(i)).getTitle());
/* 194 */       m.put("receiverNames", ((JformInnerMailEntity)mails.get(i)).getReceiverNames());
/* 195 */       m.put("createTime", DateUtils.date2Str(((JformInnerMailEntity)mails.get(i)).getCreateDate(), DateUtils.time_sdf));
/* 196 */       m.put("status", ((JformInnerMailEntity)mails.get(i)).getStatus());
/* 197 */       ret.add(m);
/*     */     }
/* 199 */     return ret;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goUnSendMails"})
/*     */   public ModelAndView goUnSendMails(HttpServletRequest request, String type)
/*     */   {
/* 210 */     return new ModelAndView("system/mail/jformInnerMailUnSendList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getUnSendMails"})
/*     */   @ResponseBody
/*     */   public List<Map<String, Object>> getUnSendMails(String title)
/*     */   {
/* 219 */     String account = ClientManager.getInstance().getClient().getUser().getUserName();
/*     */ 
/* 221 */     StringBuffer sqlb = new StringBuffer("from JformInnerMailEntity where status ='00' and  createBy ='" + account + "'");
/* 222 */     if (StringUtils.isNotEmpty(title)) {
/* 223 */       sqlb.append(" and title like '%" + title + "%'");
/*     */     }
/*     */ 
/* 226 */     List mails = this.systemService.findByQueryString(sqlb.toString());
/* 227 */     List ret = new ArrayList();
/* 228 */     for (int i = 0; i < mails.size(); i++) {
/* 229 */       Map m = new HashMap();
/* 230 */       m.put("id", ((JformInnerMailEntity)mails.get(i)).getId());
/* 231 */       m.put("title", ((JformInnerMailEntity)mails.get(i)).getTitle());
/* 232 */       m.put("receiverNames", ((JformInnerMailEntity)mails.get(i)).getReceiverNames());
/* 233 */       m.put("createTime", DateUtils.date2Str(((JformInnerMailEntity)mails.get(i)).getCreateDate(), DateUtils.time_sdf));
/* 234 */       m.put("status", ((JformInnerMailEntity)mails.get(i)).getStatus());
/* 235 */       ret.add(m);
/*     */     }
/* 237 */     sqlb.setLength(0);
/* 238 */     return ret;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDelMail"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDelMail(JformInnerMailEntity innerMailEntity, HttpServletRequest request)
/*     */   {
/* 250 */     String message = null;
/* 251 */     AjaxJson j = new AjaxJson();
/* 252 */     innerMailEntity = (JformInnerMailEntity)this.systemService.getEntity(JformInnerMailEntity.class, innerMailEntity.getId());
/* 253 */     message = "删除成功";
/*     */     try
/*     */     {
/* 256 */       if (innerMailEntity.getStatus().equals("00")) {
/* 257 */         this.systemService.delete(innerMailEntity);
/* 258 */         this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */       }
/*     */       else
/*     */       {
/* 262 */         innerMailEntity.setStatus("02");
/* 263 */         this.systemService.updateEntitie(innerMailEntity);
/* 264 */         this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 267 */       e.printStackTrace();
/* 268 */       message = "删除失败";
/* 269 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 271 */     j.setMsg(message);
/* 272 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDelMails"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDelMails(String ids, HttpServletRequest request)
/*     */   {
/* 283 */     String message = null;
/* 284 */     AjaxJson j = new AjaxJson();
/* 285 */     message = "删除成功";
/*     */     try {
/* 287 */       for (String id : ids.split(",")) {
/* 288 */         JformInnerMailEntity innerMailEntity = (JformInnerMailEntity)this.systemService.getEntity(JformInnerMailEntity.class, id);
/*     */ 
/* 290 */         if (innerMailEntity.getStatus().equals("00")) {
/* 291 */           this.systemService.delete(innerMailEntity);
/*     */         }
/*     */         else
/*     */         {
/* 295 */           innerMailEntity.setStatus("02");
/* 296 */           this.systemService.updateEntitie(innerMailEntity);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 301 */       e.printStackTrace();
/* 302 */       message = "删除失败";
/* 303 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 305 */     j.setMsg(message);
/* 306 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goReceivedMails"})
/*     */   public ModelAndView goReceivedMails(HttpServletRequest request, String type)
/*     */   {
/* 316 */     return new ModelAndView("system/mail/jformInnerMailReceiveList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getReceivedMails"})
/*     */   @ResponseBody
/*     */   public List<Map<String, Object>> getReceivedMails(HttpServletRequest request, String senderName, String title, DataGrid dataGrid)
/*     */   {
/* 326 */     String userId = ClientManager.getInstance().getClient().getUser().getId();
/* 327 */     StringBuffer sqlb = new StringBuffer("from JformInnerMailReceiverEntity where TSUser.id ='" + userId + "'and JMail.status !='" + "00" + "'");
/*     */ 
/* 329 */     if (StringUtils.isNotEmpty(senderName)) {
/* 330 */       sqlb.append(" and JMail.createName like '%" + senderName + "%'");
/*     */     }
/*     */ 
/* 333 */     if (StringUtils.isNotEmpty(title)) {
/* 334 */       sqlb.append(" and JMail.title like '%" + title + "%'");
/*     */     }
/*     */ 
/* 338 */     List receivers = this.systemService.findByQueryString(sqlb.toString());
/* 339 */     List ret = new ArrayList();
/* 340 */     for (int i = 0; i < receivers.size(); i++) {
/* 341 */       Map m = new HashMap();
/* 342 */       m.put("id", ((JformInnerMailReceiverEntity)receivers.get(i)).getId());
/* 343 */       m.put("mailId", ((JformInnerMailReceiverEntity)receivers.get(i)).getJMail().getId());
/* 344 */       m.put("title", ((JformInnerMailReceiverEntity)receivers.get(i)).getJMail().getTitle());
/* 345 */       m.put("senderName", ((JformInnerMailReceiverEntity)receivers.get(i)).getJMail().getCreateName());
/* 346 */       m.put("senderAccount", ((JformInnerMailReceiverEntity)receivers.get(i)).getJMail().getCreateBy());
/* 347 */       m.put("sendTime", DateUtils.date2Str(((JformInnerMailReceiverEntity)receivers.get(i)).getCreateDate(), DateUtils.time_sdf));
/* 348 */       m.put("status", ((JformInnerMailReceiverEntity)receivers.get(i)).getStatus());
/* 349 */       ret.add(m);
/*     */     }
/* 351 */     return ret;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDelReceivedMail"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDelReceivedMail(JformInnerMailReceiverEntity jformInnerMailReceiverEntity, HttpServletRequest request)
/*     */   {
/* 361 */     String message = null;
/* 362 */     AjaxJson j = new AjaxJson();
/* 363 */     jformInnerMailReceiverEntity = (JformInnerMailReceiverEntity)this.systemService.getEntity(JformInnerMailReceiverEntity.class, jformInnerMailReceiverEntity.getId());
/* 364 */     message = "删除成功";
/*     */     try {
/* 366 */       this.systemService.delete(jformInnerMailReceiverEntity);
/* 367 */       this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 369 */       e.printStackTrace();
/* 370 */       message = "删除失败";
/* 371 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 373 */     j.setMsg(message);
/* 374 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDelReceivedMails"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDelReceivedMails(String ids, HttpServletRequest request)
/*     */   {
/* 384 */     String message = null;
/* 385 */     AjaxJson j = new AjaxJson();
/* 386 */     message = "删除成功";
/*     */ 
/* 388 */     for (String id : ids.split(",")) {
/* 389 */       JformInnerMailReceiverEntity jformInnerMailReceiverEntity = (JformInnerMailReceiverEntity)this.systemService.getEntity(JformInnerMailReceiverEntity.class, id);
/* 390 */       this.systemService.delete(jformInnerMailReceiverEntity);
/*     */     }
/*     */ 
/* 393 */     j.setMsg(message);
/* 394 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"delFile"})
/*     */   @ResponseBody
/*     */   public AjaxJson delFile(JformInnerMailAttach jformInnerMailAttach, HttpServletRequest request)
/*     */   {
/* 406 */     String message = null;
/* 407 */     AjaxJson j = new AjaxJson();
/* 408 */     String id = request.getParameter("id");
/* 409 */     JformInnerMailAttach file = (JformInnerMailAttach)this.systemService.getEntity(JformInnerMailAttach.class, id);
/* 410 */     message = file.getAttachmenttitle() + "被删除成功";
/* 411 */     this.jformInnerMailService.deleteFile(file);
/* 412 */     this.systemService.addLog(message, Globals.Log_Type_DEL, 
/* 413 */       Globals.Log_Leavel_INFO);
/* 414 */     j.setSuccess(true);
/* 415 */     j.setMsg(message);
/* 416 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"saveFile"})
/*     */   @ResponseBody
/*     */   public AjaxJson saveFile(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 426 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 428 */     JformInnerMailAttach jformInnerMailAttach = new JformInnerMailAttach();
/*     */ 
/* 430 */     String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
/*     */ 
/* 432 */     String mailId = oConvertUtils.getString(request.getParameter("mailId"));
/*     */ 
/* 434 */     if (StringUtil.isNotEmpty(fileKey)) {
/* 435 */       jformInnerMailAttach.setId(fileKey);
/* 436 */       jformInnerMailAttach = (JformInnerMailAttach)this.systemService.getEntity(JformInnerMailAttach.class, fileKey);
/*     */     }
/*     */ 
/* 439 */     jformInnerMailAttach.setMailid(mailId);
/*     */ 
/* 441 */     UploadFile uploadFile = new UploadFile(request, jformInnerMailAttach);
/* 442 */     uploadFile.setCusPath("files");
/* 443 */     uploadFile.setSwfpath("swfpath");
/* 444 */     uploadFile.setByteField(null);
/* 445 */     jformInnerMailAttach = (JformInnerMailAttach)this.systemService.uploadFile(uploadFile);
/*     */ 
/* 447 */     j.setMsg("文件添加成功");
/* 448 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDel(JformInnerMailEntity jformInnerMail, HttpServletRequest request)
/*     */   {
/* 458 */     String message = null;
/* 459 */     AjaxJson j = new AjaxJson();
/* 460 */     jformInnerMail = (JformInnerMailEntity)this.systemService.getEntity(JformInnerMailEntity.class, jformInnerMail.getId());
/* 461 */     message = "内部邮件删除成功";
/*     */     try {
/* 463 */       this.jformInnerMailService.delete(jformInnerMail);
/* 464 */       this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 466 */       e.printStackTrace();
/* 467 */       message = "内部邮件删除失败";
/* 468 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 470 */     j.setMsg(message);
/* 471 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doBatchDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doBatchDel(String ids, HttpServletRequest request)
/*     */   {
/* 482 */     String message = null;
/* 483 */     AjaxJson j = new AjaxJson();
/* 484 */     message = "内部邮件删除成功";
/*     */     try {
/* 486 */       for (String id : ids.split(",")) {
/* 487 */         JformInnerMailEntity jformInnerMail = (JformInnerMailEntity)this.systemService.getEntity(JformInnerMailEntity.class, 
/* 488 */           id);
/*     */ 
/* 490 */         this.jformInnerMailService.delete(jformInnerMail);
/* 491 */         this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 494 */       e.printStackTrace();
/* 495 */       message = "内部邮件删除失败";
/* 496 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 498 */     j.setMsg(message);
/* 499 */     return j;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.JformInnerMailController
 * JD-Core Version:    0.6.2
 */