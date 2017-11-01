/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_notice_authority_user")
/*    */ public class TSNoticeAuthorityUser extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String noticeId;
/*    */   private TSUser user;
/*    */ 
/*    */   public void setNoticeId(String noticeId)
/*    */   {
/* 25 */     this.noticeId = noticeId;
/*    */   }
/*    */ 
/*    */   @Column(name="notice_id", nullable=true)
/*    */   public String getNoticeId() {
/* 30 */     return this.noticeId;
/*    */   }
/*    */ 
/*    */   public void setUser(TSUser user) {
/* 34 */     this.user = user;
/*    */   }
/*    */   @ManyToOne
/*    */   @JoinColumn(name="user_id")
/*    */   public TSUser getUser() {
/* 40 */     return this.user;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityUser
 * JD-Core Version:    0.6.2
 */