/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DuplicateCheckPage
/*    */   implements Serializable
/*    */ {
/*    */   private String tableName;
/*    */   private String fieldName;
/*    */   private String fieldVlaue;
/*    */   private String rowObid;
/*    */ 
/*    */   public String getRowObid()
/*    */   {
/* 34 */     return this.rowObid;
/*    */   }
/*    */ 
/*    */   public void setRowObid(String rowObid) {
/* 38 */     this.rowObid = rowObid;
/*    */   }
/*    */ 
/*    */   public String getTableName() {
/* 42 */     return this.tableName;
/*    */   }
/*    */ 
/*    */   public String getFieldName() {
/* 46 */     return this.fieldName;
/*    */   }
/*    */ 
/*    */   public String getFieldVlaue() {
/* 50 */     return this.fieldVlaue;
/*    */   }
/*    */ 
/*    */   public void setTableName(String tableName) {
/* 54 */     this.tableName = tableName;
/*    */   }
/*    */ 
/*    */   public void setFieldName(String fieldName) {
/* 58 */     this.fieldName = fieldName;
/*    */   }
/*    */ 
/*    */   public void setFieldVlaue(String fieldVlaue) {
/* 62 */     this.fieldVlaue = fieldVlaue;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.DuplicateCheckPage
 * JD-Core Version:    0.6.2
 */