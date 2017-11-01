/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="jform_inner_mail_attach")
/*    */ @PrimaryKeyJoinColumn(name="id")
/*    */ public class JformInnerMailAttach extends TSAttachment
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String mailid;
/*    */ 
/*    */   @Column(name="mailid", length=100)
/*    */   public String getMailid()
/*    */   {
/* 28 */     return this.mailid;
/*    */   }
/*    */   public void setMailid(String mailid) {
/* 31 */     this.mailid = mailid;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.JformInnerMailAttach
 * JD-Core Version:    0.6.2
 */