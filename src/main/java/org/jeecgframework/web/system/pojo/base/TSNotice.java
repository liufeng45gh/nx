/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.Transient;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_notice")
/*    */ public class TSNotice extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String noticeTitle;
/*    */   private String noticeContent;
/*    */   private String noticeType;
/*    */   private String noticeLevel;
/*    */   private Date noticeTerm;
/*    */   private String createUser;
/*    */   private Date createTime;
/*    */   private String isRead;
/*    */ 
/*    */   @Column(name="notice_title", nullable=true)
/*    */   public String getNoticeTitle()
/*    */   {
/* 33 */     return this.noticeTitle;
/*    */   }
/*    */ 
/*    */   public void setNoticeTitle(String noticeTitle) {
/* 37 */     this.noticeTitle = noticeTitle;
/*    */   }
/*    */ 
/*    */   @Column(name="notice_content", nullable=true)
/*    */   public String getNoticeContent() {
/* 42 */     return this.noticeContent;
/*    */   }
/*    */   public void setNoticeContent(String noticeContent) {
/* 45 */     this.noticeContent = noticeContent;
/*    */   }
/*    */ 
/*    */   @Column(name="notice_type", nullable=true)
/*    */   public String getNoticeType() {
/* 50 */     return this.noticeType;
/*    */   }
/*    */   public void setNoticeType(String noticeType) {
/* 53 */     this.noticeType = noticeType;
/*    */   }
/*    */ 
/*    */   @Column(name="notice_level", nullable=true)
/*    */   public String getNoticeLevel() {
/* 58 */     return this.noticeLevel;
/*    */   }
/*    */   public void setNoticeLevel(String noticeLevel) {
/* 61 */     this.noticeLevel = noticeLevel;
/*    */   }
/*    */ 
/*    */   @Column(name="notice_term", nullable=true)
/*    */   public Date getNoticeTerm() {
/* 66 */     return this.noticeTerm;
/*    */   }
/*    */   public void setNoticeTerm(Date noticeTerm) {
/* 69 */     this.noticeTerm = noticeTerm;
/*    */   }
/*    */ 
/*    */   @Column(name="create_user", nullable=true)
/*    */   public String getCreateUser() {
/* 74 */     return this.createUser;
/*    */   }
/*    */   public void setCreateUser(String createUser) {
/* 77 */     this.createUser = createUser;
/*    */   }
/*    */ 
/*    */   @Column(name="create_time", nullable=true)
/*    */   public Date getCreateTime() {
/* 82 */     return this.createTime;
/*    */   }
/*    */   public void setCreateTime(Date createTime) {
/* 85 */     this.createTime = createTime;
/*    */   }
/*    */   public void setIsRead(String isRead) {
/* 88 */     this.isRead = isRead;
/*    */   }
/*    */ 
/*    */   @Transient
/*    */   public String getIsRead() {
/* 93 */     return this.isRead;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSNotice
 * JD-Core Version:    0.6.2
 */