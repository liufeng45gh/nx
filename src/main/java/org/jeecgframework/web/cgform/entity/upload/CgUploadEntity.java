/*    */ package org.jeecgframework.web.cgform.entity.upload;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.web.system.pojo.base.TSAttachment;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="cgform_uploadfiles", schema="")
/*    */ @PrimaryKeyJoinColumn(name="id")
/*    */ public class CgUploadEntity extends TSAttachment
/*    */   implements Serializable
/*    */ {
/*    */   private String cgformName;
/*    */   private String cgformId;
/*    */   private String cgformField;
/*    */ 
/*    */   @Column(name="CGFORM_ID", nullable=false, length=36)
/*    */   public String getCgformId()
/*    */   {
/* 33 */     return this.cgformId;
/*    */   }
/*    */   public void setCgformId(String cgformId) {
/* 36 */     this.cgformId = cgformId;
/*    */   }
/*    */   @Column(name="CGFORM_NAME", nullable=false, length=100)
/*    */   public String getCgformName() {
/* 40 */     return this.cgformName;
/*    */   }
/*    */   public void setCgformName(String cgformName) {
/* 43 */     this.cgformName = cgformName;
/*    */   }
/*    */   @Column(name="CGFORM_FIELD", nullable=false, length=100)
/*    */   public String getCgformField() {
/* 47 */     return this.cgformField;
/*    */   }
/*    */   public void setCgformField(String cgformField) {
/* 50 */     this.cgformField = cgformField;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.upload.CgUploadEntity
 * JD-Core Version:    0.6.2
 */