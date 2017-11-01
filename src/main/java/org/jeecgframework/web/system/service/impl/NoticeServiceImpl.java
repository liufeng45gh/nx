/*     */ package org.jeecgframework.web.system.service.impl;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.UUID;
/*     */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*     */ import org.jeecgframework.web.system.pojo.base.TSNotice;
/*     */ import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityRole;
/*     */ import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityUser;
/*     */ import org.jeecgframework.web.system.pojo.base.TSNoticeReadUser;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.NoticeService;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("noticeService")
/*     */ public class NoticeServiceImpl extends CommonServiceImpl
/*     */   implements NoticeService
/*     */ {
/*     */   public String addNotice(String noticeTitle, String noticeContent, String noticeType, String noticeLevel, Date noticeTerm, String createUser)
/*     */   {
/*  37 */     String noticeId = null;
/*  38 */     TSNotice notice = new TSNotice();
/*  39 */     notice.setNoticeTitle(noticeTitle);
/*  40 */     notice.setNoticeContent(noticeContent);
/*  41 */     notice.setNoticeType(noticeType);
/*  42 */     notice.setNoticeLevel(noticeLevel);
/*  43 */     notice.setNoticeTerm(noticeTerm);
/*  44 */     notice.setCreateUser(createUser);
/*  45 */     notice.setCreateTime(new Date());
/*  46 */     save(notice);
/*  47 */     noticeId = notice.getId();
/*  48 */     return noticeId;
/*     */   }
/*     */ 
/*     */   public void addNoticeAuthorityUser(String noticeId, String userid)
/*     */   {
/*  55 */     if ((noticeId != null) && (userid != null)) {
/*  56 */       TSNoticeAuthorityUser entity = new TSNoticeAuthorityUser();
/*  57 */       entity.setNoticeId(noticeId);
/*  58 */       TSUser tsuser = new TSUser();
/*  59 */       tsuser.setId(userid);
/*  60 */       entity.setUser(tsuser);
/*  61 */       saveOrUpdate(entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   public <T> void delete(T entity)
/*     */   {
/*  67 */     TSNotice notice = (TSNotice)entity;
/*  68 */     super.deleteAllEntitie(super.findByProperty(TSNoticeReadUser.class, "noticeId", notice.getId()));
/*  69 */     super.deleteAllEntitie(super.findByProperty(TSNoticeAuthorityUser.class, "noticeId", notice.getId()));
/*  70 */     super.deleteAllEntitie(super.findByProperty(TSNoticeAuthorityRole.class, "noticeId", notice.getId()));
/*  71 */     super.delete(notice);
/*     */ 
/*  73 */     doDelSql(notice);
/*     */   }
/*     */ 
/*     */   public <T> Serializable save(T entity)
/*     */   {
/*  78 */     Serializable t = super.save(entity);
/*     */ 
/*  80 */     doAddSql((TSNotice)entity);
/*  81 */     return t;
/*     */   }
/*     */ 
/*     */   public <T> void saveOrUpdate(T entity) {
/*  85 */     super.saveOrUpdate(entity);
/*     */ 
/*  87 */     doUpdateSql((TSNotice)entity);
/*     */   }
/*     */ 
/*     */   public boolean doAddSql(TSNotice t)
/*     */   {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doUpdateSql(TSNotice t)
/*     */   {
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doDelSql(TSNotice t)
/*     */   {
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   public String replaceVal(String sql, TSNotice t)
/*     */   {
/* 121 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 122 */     sql = sql.replace("#{notice_title}", String.valueOf(t.getNoticeTitle()));
/* 123 */     sql = sql.replace("#{notice_content}", String.valueOf(t.getNoticeContent()));
/* 124 */     sql = sql.replace("#{notice_type}", String.valueOf(t.getNoticeType()));
/* 125 */     sql = sql.replace("#{notice_level}", String.valueOf(t.getNoticeLevel()));
/* 126 */     sql = sql.replace("#{notice_term}", String.valueOf(t.getNoticeTerm()));
/* 127 */     sql = sql.replace("#{create_user}", String.valueOf(t.getCreateUser()));
/* 128 */     sql = sql.replace("#{create_time}", String.valueOf(t.getCreateTime()));
/* 129 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 130 */     return sql;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.impl.NoticeServiceImpl
 * JD-Core Version:    0.6.2
 */