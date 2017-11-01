/*    */ package org.jeecgframework.core.common.model.json;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class DataGridReturn
/*    */ {
/*    */   private Integer total;
/*    */   private List rows;
/*    */   private List footer;
/*    */ 
/*    */   public DataGridReturn(Integer total, List rows)
/*    */   {
/* 14 */     this.total = total;
/* 15 */     this.rows = rows;
/*    */   }
/*    */ 
/*    */   public Integer getTotal()
/*    */   {
/* 23 */     return this.total;
/*    */   }
/*    */ 
/*    */   public void setTotal(Integer total) {
/* 27 */     this.total = total;
/*    */   }
/*    */ 
/*    */   public List getRows() {
/* 31 */     return this.rows;
/*    */   }
/*    */ 
/*    */   public void setRows(List rows) {
/* 35 */     this.rows = rows;
/*    */   }
/*    */ 
/*    */   public List getFooter() {
/* 39 */     return this.footer;
/*    */   }
/*    */ 
/*    */   public void setFooter(List footer) {
/* 43 */     this.footer = footer;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.json.DataGridReturn
 * JD-Core Version:    0.6.2
 */