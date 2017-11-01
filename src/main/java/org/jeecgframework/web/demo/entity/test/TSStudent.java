/*    */ package org.jeecgframework.web.demo.entity.test;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_student")
/*    */ public class TSStudent extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String name;
/*    */   private String sex;
/*    */   private String className;
/*    */ 
/*    */   @Column(name="name")
/*    */   public String getName()
/*    */   {
/* 17 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 20 */     this.name = name;
/*    */   }
/*    */   @Column(name="sex")
/*    */   public String getSex() {
/* 24 */     return this.sex;
/*    */   }
/*    */   public void setSex(String sex) {
/* 27 */     this.sex = sex;
/*    */   }
/*    */   @Column(name="classname")
/*    */   public String getClassName() {
/* 31 */     return this.className;
/*    */   }
/*    */   public void setClassName(String className) {
/* 34 */     this.className = className;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.TSStudent
 * JD-Core Version:    0.6.2
 */