/*    */ package org.jeecgframework.web.cgform.entity.autolist;
/*    */ 
/*    */ public class AutoFieldEntity
/*    */ {
/*    */   private String name;
/*    */   private String title;
/*    */   private boolean isHidden;
/*    */   private boolean isQuery;
/*    */   private String queryMode;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 19 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 22 */     this.name = name;
/*    */   }
/*    */   public String getTitle() {
/* 25 */     return this.title;
/*    */   }
/*    */   public void setTitle(String title) {
/* 28 */     this.title = title;
/*    */   }
/*    */   public boolean isQuery() {
/* 31 */     return this.isQuery;
/*    */   }
/*    */   public void setQuery(boolean isQuery) {
/* 34 */     this.isQuery = isQuery;
/*    */   }
/*    */   public String getQueryMode() {
/* 37 */     return this.queryMode;
/*    */   }
/*    */   public void setQueryMode(String queryMode) {
/* 40 */     this.queryMode = queryMode;
/*    */   }
/*    */ 
/*    */   public AutoFieldEntity(String name, String title, boolean isQuery, String queryMode) {
/* 44 */     this.name = name;
/* 45 */     this.title = title;
/* 46 */     this.isQuery = isQuery;
/* 47 */     this.queryMode = queryMode;
/*    */   }
/*    */   public boolean isHidden() {
/* 50 */     return this.isHidden;
/*    */   }
/*    */   public void setHidden(boolean isHidden) {
/* 53 */     this.isHidden = isHidden;
/*    */   }
/*    */ 
/*    */   public AutoFieldEntity(String name, String title, boolean isHidden, boolean isQuery, String queryMode) {
/* 57 */     this.name = name;
/* 58 */     this.title = title;
/* 59 */     this.isHidden = isHidden;
/* 60 */     this.isQuery = isQuery;
/* 61 */     this.queryMode = queryMode;
/*    */   }
/*    */ 
/*    */   public AutoFieldEntity()
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.autolist.AutoFieldEntity
 * JD-Core Version:    0.6.2
 */