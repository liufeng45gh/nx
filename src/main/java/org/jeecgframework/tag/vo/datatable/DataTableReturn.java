/*    */ package org.jeecgframework.tag.vo.datatable;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class DataTableReturn
/*    */ {
/*    */   private Integer iTotalRecords;
/*    */   private Integer iTotalDisplayRecords;
/*    */   private Integer sEcho;
/*    */   private List<?> aaData;
/*    */ 
/*    */   public Integer getiTotalRecords()
/*    */   {
/* 34 */     return this.iTotalRecords;
/*    */   }
/*    */ 
/*    */   public void setiTotalRecords(Integer iTotalRecords)
/*    */   {
/* 42 */     this.iTotalRecords = iTotalRecords;
/*    */   }
/*    */ 
/*    */   public Integer getiTotalDisplayRecords()
/*    */   {
/* 49 */     return this.iTotalDisplayRecords;
/*    */   }
/*    */ 
/*    */   public void setiTotalDisplayRecords(Integer iTotalDisplayRecords)
/*    */   {
/* 57 */     this.iTotalDisplayRecords = iTotalDisplayRecords;
/*    */   }
/*    */ 
/*    */   public Integer getsEcho()
/*    */   {
/* 64 */     return this.sEcho;
/*    */   }
/*    */ 
/*    */   public void setsEcho(Integer sEcho)
/*    */   {
/* 72 */     this.sEcho = sEcho;
/*    */   }
/*    */ 
/*    */   public List<?> getAaData()
/*    */   {
/* 79 */     return this.aaData;
/*    */   }
/*    */ 
/*    */   public void setAaData(List<?> aaData)
/*    */   {
/* 87 */     this.aaData = aaData;
/*    */   }
/*    */ 
/*    */   public DataTableReturn(Integer iTotalRecords, Integer iTotalDisplayRecords, Integer sEcho, List<?> aaData) {
/* 91 */     this.iTotalRecords = iTotalRecords;
/* 92 */     this.iTotalDisplayRecords = iTotalDisplayRecords;
/* 93 */     this.sEcho = sEcho;
/* 94 */     this.aaData = aaData;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.vo.datatable.DataTableReturn
 * JD-Core Version:    0.6.2
 */