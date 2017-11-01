/*    */ package org.jeecgframework.web.system.service.impl;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityRole;
/*    */ import org.jeecgframework.web.system.pojo.base.TSRole;
/*    */ import org.jeecgframework.web.system.service.NoticeAuthorityRoleServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("noticeAuthorityRoleService")
/*    */ @Transactional
/*    */ public class NoticeAuthorityRoleServiceImpl extends CommonServiceImpl
/*    */   implements NoticeAuthorityRoleServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 19 */     super.delete(entity);
/*    */ 
/* 21 */     doDelSql((TSNoticeAuthorityRole)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 25 */     Serializable t = super.save(entity);
/*    */ 
/* 27 */     doAddSql((TSNoticeAuthorityRole)entity);
/* 28 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 32 */     super.saveOrUpdate(entity);
/*    */ 
/* 34 */     doUpdateSql((TSNoticeAuthorityRole)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(TSNoticeAuthorityRole t)
/*    */   {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(TSNoticeAuthorityRole t)
/*    */   {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(TSNoticeAuthorityRole t)
/*    */   {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, TSNoticeAuthorityRole t)
/*    */   {
/* 68 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 69 */     sql = sql.replace("#{notice_id}", String.valueOf(t.getNoticeId()));
/* 70 */     sql = sql.replace("#{role_id}", String.valueOf(t.getRole().getId()));
/* 71 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 72 */     return sql;
/*    */   }
/*    */ 
/*    */   public boolean checkAuthorityRole(String noticeId, String roleid)
/*    */   {
/* 79 */     CriteriaQuery cq = new CriteriaQuery(TSNoticeAuthorityRole.class);
/*    */ 
/* 81 */     cq.eq("role.id", roleid);
/* 82 */     cq.eq("noticeId", noticeId);
/* 83 */     cq.add();
/* 84 */     List rlist = getListByCriteriaQuery(cq, Boolean.valueOf(false));
/* 85 */     if (rlist.size() == 0) {
/* 86 */       return false;
/*    */     }
/* 88 */     return true;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.impl.NoticeAuthorityRoleServiceImpl
 * JD-Core Version:    0.6.2
 */