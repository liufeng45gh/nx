/*    */ package org.jeecgframework.web.cgform.util;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TableJson
/*    */ {
/* 15 */   private boolean success = true;
/* 16 */   private String msg = "操作成功";
/*    */   private Integer tableType;
/* 18 */   private Object tableData = null;
/*    */   private Map<String, Object> subTableDate;
/*    */ 
/*    */   public String getMsg()
/*    */   {
/* 22 */     return this.msg;
/*    */   }
/*    */ 
/*    */   public void setMsg(String msg) {
/* 26 */     this.msg = msg;
/*    */   }
/*    */ 
/*    */   public boolean isSuccess() {
/* 30 */     return this.success;
/*    */   }
/*    */ 
/*    */   public void setSuccess(boolean success) {
/* 34 */     this.success = success;
/*    */   }
/*    */ 
/*    */   public Integer getTableType() {
/* 38 */     return this.tableType;
/*    */   }
/*    */ 
/*    */   public void setTableType(Integer tableType) {
/* 42 */     this.tableType = tableType;
/*    */   }
/*    */ 
/*    */   public Object getTableData() {
/* 46 */     return this.tableData;
/*    */   }
/*    */ 
/*    */   public void setTableData(Object tableData) {
/* 50 */     this.tableData = tableData;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getSubTableDate() {
/* 54 */     return this.subTableDate;
/*    */   }
/*    */ 
/*    */   public void setSubTableDate(Map<String, Object> subTableDate) {
/* 58 */     this.subTableDate = subTableDate;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 63 */     return "TableJson [success=" + this.success + ", msg=" + this.msg + 
/* 64 */       ", tableType=" + this.tableType + ", tableData=" + this.tableData + 
/* 65 */       ", subTableDate=" + this.subTableDate + "]";
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.util.TableJson
 * JD-Core Version:    0.6.2
 */