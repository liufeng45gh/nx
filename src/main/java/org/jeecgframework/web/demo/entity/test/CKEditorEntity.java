/*    */ package org.jeecgframework.web.demo.entity.test;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="ck_editor", schema="")
/*    */ public class CKEditorEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String id;
/*    */   private byte[] contents;
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="paymentableGenerator")
/*    */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*    */   @Column(name="ID", nullable=false, length=32)
/*    */   public String getId()
/*    */   {
/* 42 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id)
/*    */   {
/* 50 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="CONTENTS", nullable=true, length=65535)
/*    */   public byte[] getContents()
/*    */   {
/* 58 */     return this.contents;
/*    */   }
/*    */ 
/*    */   public void setContents(byte[] contents)
/*    */   {
/* 66 */     this.contents = contents;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.CKEditorEntity
 * JD-Core Version:    0.6.2
 */