/*    */ package org.jeecgframework.core.common.hibernate.qbc;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class PageList
/*    */ {
/*    */   private int curPageNO;
/*    */   private int offset;
/*    */   private String toolBar;
/*    */   private int count;
/* 17 */   private List resultList = null;
/*    */ 
/*    */   public PageList()
/*    */   {
/*    */   }
/*    */ 
/*    */   public PageList(List resultList, String toolBar, int offset, int curPageNO, int count)
/*    */   {
/* 30 */     this.curPageNO = curPageNO;
/* 31 */     this.offset = offset;
/* 32 */     this.toolBar = toolBar;
/* 33 */     this.resultList = resultList;
/* 34 */     this.count = count;
/*    */   }
/*    */ 
/*    */   public PageList(CriteriaQuery cq, List resultList, int offset, int curPageNO, int count)
/*    */   {
/* 45 */     this.curPageNO = curPageNO;
/* 46 */     this.offset = offset;
/* 47 */     this.resultList = resultList;
/* 48 */     this.count = count;
/*    */   }
/*    */   public PageList(HqlQuery cq, List resultList, int offset, int curPageNO, int count) {
/* 51 */     this.curPageNO = curPageNO;
/* 52 */     this.offset = offset;
/* 53 */     this.resultList = resultList;
/* 54 */     this.count = count;
/*    */   }
/*    */   public <T> List<T> getResultList() {
/* 57 */     return this.resultList;
/*    */   }
/*    */ 
/*    */   public void setResultList(List resultList) {
/* 61 */     this.resultList = resultList;
/*    */   }
/*    */ 
/*    */   public String getToolBar() {
/* 65 */     return this.toolBar;
/*    */   }
/*    */ 
/*    */   public int getCount() {
/* 69 */     return this.count;
/*    */   }
/*    */ 
/*    */   public int getCurPageNO() {
/* 73 */     return this.curPageNO;
/*    */   }
/*    */ 
/*    */   public void setCurPageNO(int curPageNO) {
/* 77 */     this.curPageNO = curPageNO;
/*    */   }
/*    */ 
/*    */   public int getOffset() {
/* 81 */     return this.offset;
/*    */   }
/*    */ 
/*    */   public void setOffset(int offset) {
/* 85 */     this.offset = offset;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.hibernate.qbc.PageList
 * JD-Core Version:    0.6.2
 */