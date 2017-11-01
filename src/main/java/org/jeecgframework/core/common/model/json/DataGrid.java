/*     */ package org.jeecgframework.core.common.model.json;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jeecgframework.core.util.ContextHolderUtils;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.tag.vo.datatable.SortDirection;
/*     */ 
/*     */ public class DataGrid
/*     */ {
/*  18 */   private int page = 1;
/*  19 */   private int rows = 10;
/*  20 */   private String sort = null;
/*  21 */   private SortDirection order = SortDirection.asc;
/*     */   private String field;
/*     */   private String treefield;
/*     */   private List results;
/*     */   private int total;
/*     */   private String footer;
/*     */   private String sqlbuilder;
/*     */ 
/*     */   public String getSqlbuilder()
/*     */   {
/*  30 */     return this.sqlbuilder;
/*     */   }
/*     */ 
/*     */   public void setSqlbuilder(String sqlbuilder) {
/*  34 */     this.sqlbuilder = sqlbuilder;
/*     */   }
/*     */ 
/*     */   public int getTotal() {
/*  38 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(int total) {
/*  42 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public String getField() {
/*  46 */     return this.field;
/*     */   }
/*     */ 
/*     */   public List getResults() {
/*  50 */     return this.results;
/*     */   }
/*     */ 
/*     */   public void setResults(List results) {
/*  54 */     this.results = results;
/*     */   }
/*     */ 
/*     */   public void setField(String field) {
/*  58 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public int getPage() {
/*  62 */     return this.page;
/*     */   }
/*     */ 
/*     */   public void setPage(int page) {
/*  66 */     this.page = page;
/*     */   }
/*     */ 
/*     */   public int getRows() {
/*  70 */     if ((ContextHolderUtils.getRequest() != null) && (ResourceUtil.getParameter("rows") != null)) {
/*  71 */       return this.rows;
/*     */     }
/*  73 */     return 10000;
/*     */   }
/*     */ 
/*     */   public void setRows(int rows) {
/*  77 */     this.rows = rows;
/*     */   }
/*     */ 
/*     */   public String getSort() {
/*  81 */     return this.sort;
/*     */   }
/*     */ 
/*     */   public void setSort(String sort) {
/*  85 */     this.sort = sort;
/*     */   }
/*     */ 
/*     */   public SortDirection getOrder() {
/*  89 */     return this.order;
/*     */   }
/*     */ 
/*     */   public void setOrder(SortDirection order) {
/*  93 */     this.order = order;
/*     */   }
/*     */   public String getTreefield() {
/*  96 */     return this.treefield;
/*     */   }
/*     */ 
/*     */   public void setTreefield(String treefield) {
/* 100 */     this.treefield = treefield;
/*     */   }
/*     */ 
/*     */   public String getFooter() {
/* 104 */     return this.footer;
/*     */   }
/*     */ 
/*     */   public void setFooter(String footer) {
/* 108 */     this.footer = footer;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.json.DataGrid
 * JD-Core Version:    0.6.2
 */