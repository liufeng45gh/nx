/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_document")
/*    */ @PrimaryKeyJoinColumn(name="id")
/*    */ public class TSDocument extends TSAttachment
/*    */   implements Serializable
/*    */ {
/*    */   private String documentTitle;
/*    */   private byte[] pictureIndex;
/*    */   private Short documentState;
/*    */   private Short showHome;
/*    */   private TSType TSType;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="typeid")
/*    */   public TSType getTSType()
/*    */   {
/* 28 */     return this.TSType;
/*    */   }
/*    */   public void setTSType(TSType tSType) {
/* 31 */     this.TSType = tSType;
/*    */   }
/*    */   @Column(name="documenttitle", length=100)
/*    */   public String getDocumentTitle() {
/* 35 */     return this.documentTitle;
/*    */   }
/*    */   public void setDocumentTitle(String documentTitle) {
/* 38 */     this.documentTitle = documentTitle;
/*    */   }
/*    */   @Column(name="pictureindex", length=3000)
/*    */   public byte[] getPictureIndex() {
/* 42 */     return this.pictureIndex;
/*    */   }
/*    */   public void setPictureIndex(byte[] pictureIndex) {
/* 45 */     this.pictureIndex = pictureIndex;
/*    */   }
/*    */   @Column(name="documentstate")
/*    */   public Short getDocumentState() {
/* 49 */     return this.documentState;
/*    */   }
/*    */   public void setDocumentState(Short documentState) {
/* 52 */     this.documentState = documentState;
/*    */   }
/*    */   @Column(name="showhome")
/*    */   public Short getShowHome() {
/* 56 */     return this.showHome;
/*    */   }
/*    */   public void setShowHome(Short showHome) {
/* 59 */     this.showHome = showHome;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSDocument
 * JD-Core Version:    0.6.2
 */