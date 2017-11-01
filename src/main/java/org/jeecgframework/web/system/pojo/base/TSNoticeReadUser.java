/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_notice_read_user")
/*    */ public class TSNoticeReadUser extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String noticeId;
/*    */   private String userId;
/*    */   private Date createTime;
/*    */ 
/*    */   public void setNoticeId(String noticeId)
/*    */   {
/* 26 */     this.noticeId = noticeId;
/*    */   }
/*    */ 
/*    */   @Column(name="notice_id", nullable=true)
/*    */   public String getNoticeId() {
/* 31 */     return this.noticeId;
/*    */   }
/*    */ 
/*    */   public void setUserId(String userId)
/*    */   {
/* 36 */     this.userId = userId;
/*    */   }
/*    */ 
/*    */   @Column(name="user_id", nullable=true)
/*    */   public String getUserId() {
/* 41 */     return this.userId;
/*    */   }
/*    */   @Column(name="create_time", nullable=true)
/*    */   public Date getCreateTime() {
/* 45 */     return this.createTime;
/*    */   }
/*    */   public void setCreateTime(Date createTime) {
/* 48 */     this.createTime = createTime;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSNoticeReadUser
 * JD-Core Version:    0.6.2
 */