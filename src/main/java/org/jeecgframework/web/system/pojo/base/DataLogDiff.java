/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DataLogDiff
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private String value1;
/*    */   private String value2;
/*    */   private String diff;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 18 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 21 */     this.name = name;
/*    */   }
/*    */   public String getValue1() {
/* 24 */     return this.value1;
/*    */   }
/*    */   public void setValue1(String value1) {
/* 27 */     this.value1 = value1;
/*    */   }
/*    */   public String getValue2() {
/* 30 */     return this.value2;
/*    */   }
/*    */   public void setValue2(String value2) {
/* 33 */     this.value2 = value2;
/*    */   }
/*    */   public void setDiff(String diff) {
/* 36 */     this.diff = diff;
/*    */   }
/*    */   public String getDiff() {
/* 39 */     return this.diff;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.DataLogDiff
 * JD-Core Version:    0.6.2
 */