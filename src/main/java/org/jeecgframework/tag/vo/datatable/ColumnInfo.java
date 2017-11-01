/*    */ package org.jeecgframework.tag.vo.datatable;
/*    */ 
/*    */ public class ColumnInfo
/*    */ {
/*    */   private String name;
/*    */   private Boolean regex;
/*    */   private Boolean searchable;
/*    */   private String search;
/*    */   private Boolean sortable;
/*    */ 
/*    */   public String getSearch()
/*    */   {
/* 28 */     return this.search;
/*    */   }
/*    */ 
/*    */   public void setSearch(String search) {
/* 32 */     this.search = search;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 44 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 52 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public Boolean getRegex()
/*    */   {
/* 59 */     return this.regex;
/*    */   }
/*    */ 
/*    */   public void setRegex(Boolean regex)
/*    */   {
/* 67 */     this.regex = regex;
/*    */   }
/*    */ 
/*    */   public Boolean getSearchable()
/*    */   {
/* 74 */     return this.searchable;
/*    */   }
/*    */ 
/*    */   public void setSearchable(Boolean searchable)
/*    */   {
/* 82 */     this.searchable = searchable;
/*    */   }
/*    */ 
/*    */   public Boolean getSortable()
/*    */   {
/* 89 */     return this.sortable;
/*    */   }
/*    */ 
/*    */   public void setSortable(Boolean sortable)
/*    */   {
/* 97 */     this.sortable = sortable;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.vo.datatable.ColumnInfo
 * JD-Core Version:    0.6.2
 */