/*    */ package org.jeecgframework.web.system.controller.core;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.jeecgframework.core.common.controller.BaseController;
/*    */ import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
/*    */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*    */ import org.jeecgframework.web.system.pojo.base.DuplicateCheckPage;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/duplicateCheckAction"})
/*    */ public class DuplicateCheckAction extends BaseController
/*    */ {
/* 27 */   private static final Logger logger = Logger.getLogger(DuplicateCheckAction.class);
/*    */ 
/*    */   @Autowired
/*    */   private JdbcDao jdbcDao;
/*    */ 
/*    */   @RequestMapping(params={"doDuplicateCheck"})
/*    */   @ResponseBody
/*    */   public AjaxJson doDuplicateCheck(DuplicateCheckPage duplicateCheckPage, HttpServletRequest request)
/*    */   {
/* 41 */     AjaxJson j = new AjaxJson();
/* 42 */     Long num = null;
/*    */ 
/* 44 */     if (StringUtils.isNotBlank(duplicateCheckPage.getRowObid()))
/*    */     {
/* 46 */       String sql = "SELECT count(*) FROM " + duplicateCheckPage.getTableName() + 
/* 47 */         " WHERE " + duplicateCheckPage.getFieldName() + " =? and id != ?";
/* 48 */       num = this.jdbcDao.getCountForJdbcParam(sql, new Object[] { duplicateCheckPage.getFieldVlaue(), duplicateCheckPage.getRowObid() });
/*    */     }
/*    */     else {
/* 51 */       String sql = "SELECT count(*) FROM " + duplicateCheckPage.getTableName() + 
/* 52 */         " WHERE " + duplicateCheckPage.getFieldName() + " =?";
/* 53 */       num = this.jdbcDao.getCountForJdbcParam(sql, new Object[] { duplicateCheckPage.getFieldVlaue() });
/*    */     }
/*    */ 
/* 56 */     if ((num == null) || (num.longValue() == 0L))
/*    */     {
/* 58 */       j.setSuccess(true);
/* 59 */       j.setMsg("该值可用！");
/*    */     }
/*    */     else {
/* 62 */       j.setSuccess(false);
/* 63 */       j.setMsg("该值不可用，系统中已存在！");
/*    */     }
/* 65 */     return j;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.DuplicateCheckAction
 * JD-Core Version:    0.6.2
 */