/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_log")
/*    */ public class TSLog extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private TSUser TSUser;
/*    */   private Short loglevel;
/*    */   private Timestamp operatetime;
/*    */   private Short operatetype;
/*    */   private String logcontent;
/*    */   private String broswer;
/*    */   private String note;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="userid")
/*    */   public TSUser getTSUser()
/*    */   {
/* 32 */     return this.TSUser;
/*    */   }
/*    */ 
/*    */   public void setTSUser(TSUser TSUser) {
/* 36 */     this.TSUser = TSUser;
/*    */   }
/*    */ 
/*    */   @Column(name="loglevel")
/*    */   public Short getLoglevel() {
/* 41 */     return this.loglevel;
/*    */   }
/*    */ 
/*    */   public void setLoglevel(Short loglevel) {
/* 45 */     this.loglevel = loglevel;
/*    */   }
/*    */ 
/*    */   @Column(name="operatetime", nullable=false, length=35)
/*    */   public Timestamp getOperatetime() {
/* 50 */     return this.operatetime;
/*    */   }
/*    */ 
/*    */   public void setOperatetime(Timestamp operatetime) {
/* 54 */     this.operatetime = operatetime;
/*    */   }
/*    */ 
/*    */   @Column(name="operatetype")
/*    */   public Short getOperatetype() {
/* 59 */     return this.operatetype;
/*    */   }
/*    */ 
/*    */   public void setOperatetype(Short operatetype) {
/* 63 */     this.operatetype = operatetype;
/*    */   }
/*    */ 
/*    */   @Column(name="logcontent", nullable=false, length=2000)
/*    */   public String getLogcontent() {
/* 68 */     return this.logcontent;
/*    */   }
/*    */ 
/*    */   public void setLogcontent(String logcontent) {
/* 72 */     this.logcontent = logcontent;
/*    */   }
/*    */ 
/*    */   @Column(name="note", length=300)
/*    */   public String getNote() {
/* 77 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 81 */     this.note = note;
/*    */   }
/*    */   @Column(name="broswer", length=100)
/*    */   public String getBroswer() {
/* 85 */     return this.broswer;
/*    */   }
/*    */ 
/*    */   public void setBroswer(String broswer) {
/* 89 */     this.broswer = broswer;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSLog
 * JD-Core Version:    0.6.2
 */