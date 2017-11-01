/*     */ package org.jeecgframework.web.system.controller.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.exception.BusinessException;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*     */ import org.jeecgframework.core.util.MutiLangUtil;
/*     */ import org.jeecgframework.core.util.MyBeanUtils;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSNotice;
/*     */ import org.jeecgframework.web.system.pojo.base.TSNoticeReadUser;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*     */ import org.jeecgframework.web.system.service.NoticeService;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/noticeController"})
/*     */ public class NoticeController extends BaseController
/*     */ {
/*     */   private SystemService systemService;
/*     */ 
/*     */   @Autowired
/*     */   private NoticeService noticeService;
/*     */ 
/*     */   @Autowired
/*     */   public void setSystemService(SystemService systemService)
/*     */   {
/*  56 */     this.systemService = systemService;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getNoticeList"})
/*     */   @ResponseBody
/*     */   public AjaxJson getNoticeList(HttpServletRequest req)
/*     */   {
/*  69 */     AjaxJson j = new AjaxJson();
/*     */     try {
/*  71 */       TSUser user = ResourceUtil.getSessionUserName();
/*     */ 
/*  73 */       String sql = "";
/*  74 */       sql = sql + " SELECT * FROM  t_s_notice t WHERE";
/*  75 */       sql = sql + " (t.notice_level = '1' ";
/*  76 */       sql = sql + " OR (t.notice_level = '2' AND EXISTS (SELECT 1 FROM t_s_notice_authority_role r,t_s_role_user ru WHERE r.role_id = ru.roleid AND t.id = r.notice_id AND ru.userid = '" + user.getId() + "'))";
/*  77 */       sql = sql + " OR (t.notice_level = '3' AND EXISTS (SELECT 1 FROM t_s_notice_authority_user u WHERE t.id = u.notice_id AND u.user_id = '" + user.getId() + "'))";
/*  78 */       sql = sql + " ) AND NOT EXISTS (SELECT 1 FROM t_s_notice_read_user rd WHERE t.id = rd.notice_id AND rd.user_id = '" + user.getId() + "')";
/*     */ 
/*  80 */       sql = sql + " ORDER BY t.create_time DESC ";
/*  81 */       List noticeList = this.systemService.findForJdbc(sql, 1, 10);
/*     */ 
/*  85 */       JSONArray result = new JSONArray();
/*  86 */       if ((noticeList != null) && (noticeList.size() > 0)) {
/*  87 */         for (int i = 0; i < noticeList.size(); i++) {
/*  88 */           JSONObject jsonParts = new JSONObject();
/*  89 */           jsonParts.put("id", ((Map)noticeList.get(i)).get("id"));
/*  90 */           jsonParts.put("noticeTitle", ((Map)noticeList.get(i)).get("notice_title"));
/*  91 */           result.add(jsonParts);
/*     */         }
/*     */       }
/*     */ 
/*  95 */       Map attrs = new HashMap();
/*  96 */       attrs.put("noticeList", result);
/*     */ 
/*  98 */       String tip = MutiLangUtil.getMutiLangInstance().getLang("notice.tip");
/*  99 */       attrs.put("tip", tip);
/* 100 */       String seeAll = MutiLangUtil.getMutiLangInstance().getLang("notice.seeAll");
/* 101 */       attrs.put("seeAll", seeAll);
/* 102 */       j.setAttributes(attrs);
/*     */ 
/* 105 */       String sql2 = "";
/* 106 */       sql2 = sql2 + " SELECT count(*) as count FROM  t_s_notice t WHERE";
/* 107 */       sql2 = sql2 + " (t.notice_level = '1' ";
/* 108 */       sql2 = sql2 + " OR (t.notice_level = '2' AND EXISTS (SELECT 1 FROM t_s_notice_authority_role r,t_s_role_user ru WHERE r.role_id = ru.roleid AND t.id = r.notice_id AND ru.userid = '" + user.getId() + "'))";
/* 109 */       sql2 = sql2 + " OR (t.notice_level = '3' AND EXISTS (SELECT 1 FROM t_s_notice_authority_user u WHERE t.id = u.notice_id AND u.user_id = '" + user.getId() + "'))";
/* 110 */       sql2 = sql2 + " ) AND NOT EXISTS (SELECT 1 FROM t_s_notice_read_user rd WHERE t.id = rd.notice_id AND rd.user_id = '" + user.getId() + "')";
/* 111 */       List resultList2 = this.systemService.findForJdbc(sql2, new Object[0]);
/* 112 */       Object count = ((Map)resultList2.get(0)).get("count");
/* 113 */       j.setObj(count);
/*     */     } catch (Exception e) {
/* 115 */       j.setSuccess(false);
/* 116 */       e.printStackTrace();
/*     */     }
/* 118 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"noticeList"})
/*     */   public ModelAndView noticeList(HttpServletRequest request)
/*     */   {
/* 128 */     return new ModelAndView("system/user/noticeList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goNotice"})
/*     */   public ModelAndView noticeInfo(TSNotice notice, HttpServletRequest request)
/*     */   {
/* 138 */     if (StringUtil.isNotEmpty(notice.getId())) {
/* 139 */       notice = (TSNotice)this.systemService.getEntity(TSNotice.class, notice.getId());
/* 140 */       request.setAttribute("notice", notice);
/*     */     }
/* 142 */     return new ModelAndView("system/user/noticeinfo");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(TSNotice notice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 160 */     TSUser user = ResourceUtil.getSessionUserName();
/* 161 */     String sql = "";
/* 162 */     sql = sql + " SELECT * FROM  t_s_notice t WHERE";
/* 163 */     sql = sql + " t.notice_level = '1' ";
/* 164 */     sql = sql + " OR (t.notice_level = '2' AND EXISTS (SELECT 1 FROM t_s_notice_authority_role r,t_s_role_user ru WHERE r.role_id = ru.roleid AND t.id = r.notice_id AND ru.userid = '" + user.getId() + "'))";
/* 165 */     sql = sql + " OR (t.notice_level = '3' AND EXISTS (SELECT 1 FROM t_s_notice_authority_user u WHERE t.id = u.notice_id AND u.user_id = '" + user.getId() + "'))";
/* 166 */     sql = sql + " ORDER BY t.create_time DESC";
/*     */ 
/* 168 */     List resultList = this.systemService.findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
/*     */ 
/* 171 */     List noticeList = new ArrayList();
/* 172 */     if ((resultList != null) && (resultList.size() > 0)) {
/* 173 */       for (int i = 0; i < resultList.size(); i++) {
/* 174 */         Map obj = (Map)resultList.get(i);
/* 175 */         Map n = new HashMap();
/* 176 */         n.put("id", String.valueOf(obj.get("id")));
/* 177 */         n.put("noticeTitle", String.valueOf(obj.get("notice_title")));
/* 178 */         n.put("noticeContent", String.valueOf(obj.get("notice_content")));
/* 179 */         n.put("createTime", String.valueOf(obj.get("create_time")));
/*     */ 
/* 181 */         List readinfo = this.systemService.findForJdbc("select * from t_s_notice_read_user where notice_id = ? and user_id = ? ", new Object[] { n.get("id"), user.getId() });
/* 182 */         if ((readinfo != null) && (readinfo.size() > 0))
/* 183 */           n.put("isRead", "1");
/*     */         else {
/* 185 */           n.put("isRead", "0");
/*     */         }
/* 187 */         noticeList.add(n);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 192 */     dataGrid.setResults(noticeList);
/* 193 */     String sql2 = "";
/* 194 */     sql2 = sql2 + " SELECT count(*) AS count FROM  t_s_notice t WHERE";
/* 195 */     sql2 = sql2 + " t.notice_level = '1' ";
/* 196 */     sql2 = sql2 + " OR (t.notice_level = '2' AND EXISTS (SELECT 1 FROM t_s_notice_authority_role r,t_s_role_user ru WHERE r.role_id = ru.roleid AND t.id = r.notice_id AND ru.userid = '" + user.getId() + "'))";
/* 197 */     sql2 = sql2 + " OR (t.notice_level = '3' AND EXISTS (SELECT 1 FROM t_s_notice_authority_user u WHERE t.id = u.notice_id AND u.user_id = '" + user.getId() + "'))";
/* 198 */     List resultList2 = this.systemService.findForJdbc(sql2, new Object[0]);
/* 199 */     Object count = ((Map)resultList2.get(0)).get("count");
/* 200 */     dataGrid.setTotal(Integer.valueOf(count.toString()).intValue());
/* 201 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"updateNoticeRead"})
/*     */   @ResponseBody
/*     */   public AjaxJson updateNoticeRead(String noticeId, HttpServletRequest req)
/*     */   {
/* 212 */     AjaxJson j = new AjaxJson();
/*     */     try {
/* 214 */       TSUser user = ResourceUtil.getSessionUserName();
/* 215 */       TSNoticeReadUser readUser = new TSNoticeReadUser();
/* 216 */       readUser.setNoticeId(noticeId);
/* 217 */       readUser.setUserId(user.getId());
/* 218 */       readUser.setCreateTime(new Date());
/* 219 */       this.systemService.saveOrUpdate(readUser);
/*     */     } catch (Exception e) {
/* 221 */       e.printStackTrace();
/*     */     }
/* 223 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"tSNotice"})
/*     */   public ModelAndView tSNotice(HttpServletRequest request)
/*     */   {
/* 234 */     return new ModelAndView("system/user/tSNoticeList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid2"})
/*     */   public void datagrid2(TSNotice tSNotice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 248 */     CriteriaQuery cq = new CriteriaQuery(TSNotice.class, dataGrid);
/*     */ 
/* 250 */     HqlGenerateUtil.installHql(cq, tSNotice, request.getParameterMap());
/*     */ 
/* 256 */     cq.add();
/* 257 */     this.noticeService.getDataGridReturn(cq, true);
/* 258 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doDel(TSNotice tSNotice, HttpServletRequest request)
/*     */   {
/* 269 */     String message = null;
/* 270 */     AjaxJson j = new AjaxJson();
/* 271 */     tSNotice = (TSNotice)this.systemService.getEntity(TSNotice.class, tSNotice.getId());
/* 272 */     message = "通知公告删除成功";
/*     */     try {
/* 274 */       this.noticeService.delete(tSNotice);
/* 275 */       this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 277 */       e.printStackTrace();
/* 278 */       message = "通知公告删除失败";
/* 279 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 281 */     j.setMsg(message);
/* 282 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doBatchDel"})
/*     */   @ResponseBody
/*     */   public AjaxJson doBatchDel(String ids, HttpServletRequest request)
/*     */   {
/* 293 */     String message = null;
/* 294 */     AjaxJson j = new AjaxJson();
/* 295 */     message = "通知公告删除成功";
/*     */     try {
/* 297 */       for (String id : ids.split(",")) {
/* 298 */         TSNotice tSNotice = (TSNotice)this.systemService.getEntity(TSNotice.class, 
/* 299 */           id);
/*     */ 
/* 301 */         this.noticeService.delete(tSNotice);
/* 302 */         this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*     */       }
/*     */     } catch (Exception e) {
/* 305 */       e.printStackTrace();
/* 306 */       message = "通知公告删除失败";
/* 307 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 309 */     j.setMsg(message);
/* 310 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doAdd"})
/*     */   @ResponseBody
/*     */   public AjaxJson doAdd(TSNotice tSNotice, HttpServletRequest request)
/*     */   {
/* 323 */     String message = null;
/* 324 */     AjaxJson j = new AjaxJson();
/* 325 */     message = "通知公告添加成功";
/*     */     try {
/* 327 */       this.noticeService.save(tSNotice);
/* 328 */       this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 330 */       e.printStackTrace();
/* 331 */       message = "通知公告添加失败";
/* 332 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 334 */     j.setMsg(message);
/* 335 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doUpdate"})
/*     */   @ResponseBody
/*     */   public AjaxJson doUpdate(TSNotice tSNotice, HttpServletRequest request)
/*     */   {
/* 347 */     String message = null;
/* 348 */     AjaxJson j = new AjaxJson();
/* 349 */     message = "通知公告更新成功";
/* 350 */     TSNotice t = (TSNotice)this.noticeService.get(TSNotice.class, tSNotice.getId());
/*     */     try {
/* 352 */       MyBeanUtils.copyBeanNotNull2Bean(tSNotice, t);
/* 353 */       this.noticeService.saveOrUpdate(t);
/* 354 */       this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 356 */       e.printStackTrace();
/* 357 */       message = "通知公告更新失败";
/* 358 */       throw new BusinessException(e.getMessage());
/*     */     }
/* 360 */     j.setMsg(message);
/* 361 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goAdd"})
/*     */   public ModelAndView goAdd(TSNotice tSNotice, HttpServletRequest req)
/*     */   {
/* 372 */     if (StringUtil.isNotEmpty(tSNotice.getId())) {
/* 373 */       tSNotice = (TSNotice)this.noticeService.getEntity(TSNotice.class, tSNotice.getId());
/* 374 */       req.setAttribute("tSNoticePage", tSNotice);
/*     */     }
/* 376 */     return new ModelAndView("system/user/tSNotice-add");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goUpdate"})
/*     */   public ModelAndView goUpdate(TSNotice tSNotice, HttpServletRequest req)
/*     */   {
/* 385 */     if (StringUtil.isNotEmpty(tSNotice.getId())) {
/* 386 */       tSNotice = (TSNotice)this.noticeService.getEntity(TSNotice.class, tSNotice.getId());
/* 387 */       if (tSNotice.getNoticeTerm() == null) {
/* 388 */         tSNotice.setNoticeTerm(new Date());
/*     */       }
/* 390 */       req.setAttribute("tSNoticePage", tSNotice);
/*     */     }
/* 392 */     return new ModelAndView("system/user/tSNotice-update");
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.NoticeController
 * JD-Core Version:    0.6.2
 */