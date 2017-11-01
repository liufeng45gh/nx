/*    */ package org.jeecgframework.tag.vo.easyui;
/*    */ 
/*    */ public class Autocomplete
/*    */ {
/*    */   private String entityName;
/*    */   private String labelField;
/*    */   private String valueField;
/*    */   private String searchField;
/*    */   private String trem;
/*    */   private Integer maxRows;
/*    */   private Integer curPage;
/*    */ 
/*    */   public String getSearchField()
/*    */   {
/* 16 */     return this.searchField;
/*    */   }
/*    */   public void setSearchField(String searchField) {
/* 19 */     this.searchField = searchField;
/*    */   }
/*    */   public String getEntityName() {
/* 22 */     return this.entityName;
/*    */   }
/*    */   public void setEntityName(String entityName) {
/* 25 */     this.entityName = entityName;
/*    */   }
/*    */   public String getTrem() {
/* 28 */     return this.trem;
/*    */   }
/*    */   public void setTrem(String trem) {
/* 31 */     this.trem = trem;
/*    */   }
/*    */   public String getLabelField() {
/* 34 */     return this.labelField;
/*    */   }
/*    */   public void setLabelField(String labelField) {
/* 37 */     this.labelField = labelField;
/*    */   }
/*    */   public String getValueField() {
/* 40 */     return this.valueField;
/*    */   }
/*    */   public void setValueField(String valueField) {
/* 43 */     this.valueField = valueField;
/*    */   }
/*    */   public Integer getMaxRows() {
/* 46 */     return this.maxRows;
/*    */   }
/*    */   public void setMaxRows(Integer maxRows) {
/* 49 */     this.maxRows = maxRows;
/*    */   }
/*    */   public Integer getCurPage() {
/* 52 */     if ((this.curPage == null) || (this.curPage.intValue() < 1)) {
/* 53 */       this.curPage = Integer.valueOf(1);
/*    */     }
/* 55 */     return this.curPage;
/*    */   }
/*    */   public void setCurPage(Integer curPage) {
/* 58 */     this.curPage = curPage;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.vo.easyui.Autocomplete
 * JD-Core Version:    0.6.2
 */