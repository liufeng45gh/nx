/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_config")
/*    */ public class TSConfig extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private TSUser TSUser;
/*    */   private String code;
/*    */   private String name;
/*    */   private String contents;
/*    */   private String note;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="userid")
/*    */   public TSUser getTSUser()
/*    */   {
/* 34 */     return this.TSUser;
/*    */   }
/*    */ 
/*    */   public void setTSUser(TSUser TSUser) {
/* 38 */     this.TSUser = TSUser;
/*    */   }
/*    */   @Column(name="code", length=100)
/*    */   public String getCode() {
/* 42 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 46 */     this.code = code;
/*    */   }
/*    */ 
/*    */   @Column(name="name", nullable=false, length=100)
/*    */   public String getName() {
/* 51 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 55 */     this.name = name;
/*    */   }
/*    */ 
/*    */   @Column(name="content", length=300)
/*    */   public String getContents() {
/* 60 */     return this.contents;
/*    */   }
/*    */ 
/*    */   public void setContents(String contents) {
/* 64 */     this.contents = contents;
/*    */   }
/*    */ 
/*    */   @Column(name="note", length=300)
/*    */   public String getNote() {
/* 69 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 73 */     this.note = note;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSConfig
 * JD-Core Version:    0.6.2
 */