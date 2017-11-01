/*    */ package org.jeecgframework.web.system.service.impl;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityUser;
/*    */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*    */ import org.jeecgframework.web.system.service.NoticeAuthorityUserServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("noticeAuthorityUserService")
/*    */ @Transactional
/*    */ public class NoticeAuthorityUserServiceImpl extends CommonServiceImpl
/*    */   implements NoticeAuthorityUserServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 19 */     super.delete(entity);
/*    */ 
/* 21 */     doDelSql((TSNoticeAuthorityUser)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 25 */     Serializable t = super.save(entity);
/*    */ 
/* 27 */     doAddSql((TSNoticeAuthorityUser)entity);
/* 28 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 32 */     super.saveOrUpdate(entity);
/*    */ 
/* 34 */     doUpdateSql((TSNoticeAuthorityUser)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(TSNoticeAuthorityUser t)
/*    */   {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(TSNoticeAuthorityUser t)
/*    */   {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(TSNoticeAuthorityUser t)
/*    */   {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, TSNoticeAuthorityUser t)
/*    */   {
/* 68 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 69 */     sql = sql.replace("#{notice_id}", String.valueOf(t.getNoticeId()));
/* 70 */     sql = sql.replace("#{user_id}", String.valueOf(t.getUser().getId()));
/* 71 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 72 */     return sql;
/*    */   }
/*    */ 
/*    */   public boolean checkAuthorityUser(String noticeId, String userid)
/*    */   {
/* 79 */     CriteriaQuery cq = new CriteriaQuery(TSNoticeAuthorityUser.class);
/* 80 */     cq.eq("user.id", userid);
/* 81 */     cq.eq("noticeId", noticeId);
/* 82 */     cq.add();
/* 83 */     List rlist = getListByCriteriaQuery(cq, Boolean.valueOf(false));
/* 84 */     if (rlist.size() == 0) {
/* 85 */       return false;
/*    */     }
/* 87 */     return true;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.impl.NoticeAuthorityUserServiceImpl
 * JD-Core Version:    0.6.2
 */