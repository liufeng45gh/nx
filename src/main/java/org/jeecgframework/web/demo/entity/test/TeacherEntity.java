/*    */ package org.jeecgframework.web.demo.entity.test;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.DynamicInsert;
/*    */ import org.hibernate.annotations.DynamicUpdate;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ import org.jeecgframework.poi.excel.annotation.Excel;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="jeecg_demo_teacher", schema="")
/*    */ @DynamicUpdate(true)
/*    */ @DynamicInsert(true)
/*    */ public class TeacherEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String id;
/*    */ 
/*    */   @Excel(name="老师姓名", orderNum="2", needMerge=true)
/*    */   private String name;
/*    */ 
/*    */   @Excel(name="老师照片", orderNum="3", type=2, height=15.0D, width=20.0D)
/*    */   private String pic;
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="paymentableGenerator")
/*    */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*    */   @Column(name="ID", nullable=false, length=32)
/*    */   public String getId()
/*    */   {
/* 54 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id)
/*    */   {
/* 62 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="NAME", nullable=true, length=12)
/*    */   public String getName()
/*    */   {
/* 70 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 78 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getPic()
/*    */   {
/* 85 */     return this.pic;
/*    */   }
/*    */ 
/*    */   public void setPic(String pic) {
/* 89 */     this.pic = pic;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.TeacherEntity
 * JD-Core Version:    0.6.2
 */