/*    */ package org.jeecgframework.core.common.model.common;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DBTable<T>
/*    */   implements Serializable
/*    */ {
/*    */   public String tableName;
/*    */   public String entityName;
/*    */   public String tableTitle;
/*    */   public Class<T> tableEntityClass;
/*    */   public List<T> tableData;
/*    */ 
/*    */   public String getTableName()
/*    */   {
/* 21 */     return this.tableName;
/*    */   }
/*    */   public void setTableName(String tableName) {
/* 24 */     this.tableName = tableName;
/*    */   }
/*    */   public String getEntityName() {
/* 27 */     return this.entityName;
/*    */   }
/*    */   public void setEntityName(String entityName) {
/* 30 */     this.entityName = entityName;
/*    */   }
/*    */   public String getTableTitle() {
/* 33 */     return this.tableTitle;
/*    */   }
/*    */   public void setTableTitle(String tableTitle) {
/* 36 */     this.tableTitle = tableTitle;
/*    */   }
/*    */   public Class<T> getClass1() {
/* 39 */     return this.tableEntityClass;
/*    */   }
/*    */   public void setClass1(Class<T> class1) {
/* 42 */     this.tableEntityClass = class1;
/*    */   }
/*    */   public List<T> getTableData() {
/* 45 */     return this.tableData;
/*    */   }
/*    */   public void setTableData(List<T> tableData) {
/* 48 */     this.tableData = tableData;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.common.DBTable
 * JD-Core Version:    0.6.2
 */