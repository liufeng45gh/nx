/*     */ package org.jeecgframework.core.common.hibernate.qbc;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.hibernate.type.Type;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ 
/*     */ public class HqlQuery
/*     */ {
/*  10 */   private int curPage = 1;
/*  11 */   private int pageSize = 10;
/*     */   private String myaction;
/*     */   private String myform;
/*     */   private String queryString;
/*     */   private Object[] param;
/*     */   private Type[] types;
/*     */   private Map<String, Object> map;
/*     */   private DataGrid dataGrid;
/*  19 */   private String field = "";
/*     */   private Class class1;
/*     */   private List results;
/*     */   private int total;
/*     */ 
/*     */   public List getResults()
/*     */   {
/*  24 */     return this.results;
/*     */   }
/*     */ 
/*     */   public void setResults(List rsults) {
/*  28 */     this.results = this.results;
/*     */   }
/*     */ 
/*     */   public int getTotal() {
/*  32 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(int total) {
/*  36 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public Class getClass1() {
/*  40 */     return this.class1;
/*     */   }
/*     */ 
/*     */   public void setClass1(Class class1) {
/*  44 */     this.class1 = class1;
/*     */   }
/*     */   public DataGrid getDataGrid() {
/*  47 */     return this.dataGrid;
/*     */   }
/*     */ 
/*     */   public void setDataGrid(DataGrid dataGrid) {
/*  51 */     this.dataGrid = dataGrid;
/*     */   }
/*     */ 
/*     */   public String getField() {
/*  55 */     return this.field;
/*     */   }
/*     */ 
/*     */   public void setField(String field) {
/*  59 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getMap() {
/*  63 */     return this.map;
/*     */   }
/*     */ 
/*     */   public void setMap(Map<String, Object> map) {
/*  67 */     this.map = map;
/*     */   }
/*     */ 
/*     */   public HqlQuery(String queryString, Object[] param, Map<String, Object> map) {
/*  71 */     this.queryString = queryString;
/*  72 */     this.param = param;
/*  73 */     this.map = map;
/*     */   }
/*     */ 
/*     */   public HqlQuery(String queryString, Map<String, Object> map) {
/*  77 */     this.queryString = queryString;
/*  78 */     this.map = map;
/*     */   }
/*     */ 
/*     */   public HqlQuery(String myaction) {
/*  82 */     this.myaction = myaction;
/*     */   }
/*     */ 
/*     */   public Object[] getParam() {
/*  86 */     return this.param;
/*     */   }
/*     */ 
/*     */   public HqlQuery(String myaction, String queryString, Object[] param, Type[] types) {
/*  90 */     this.myaction = myaction;
/*  91 */     this.queryString = queryString;
/*  92 */     this.param = param;
/*  93 */     this.types = types;
/*     */   }
/*     */   public HqlQuery(Class class1, String hqlString, DataGrid dataGrid) {
/*  96 */     this.dataGrid = dataGrid;
/*  97 */     this.queryString = hqlString;
/*  98 */     this.pageSize = dataGrid.getRows();
/*  99 */     this.field = dataGrid.getField();
/* 100 */     this.class1 = class1;
/*     */   }
/*     */ 
/*     */   public void setParam(Object[] param) {
/* 104 */     this.param = param;
/*     */   }
/*     */   public int getCurPage() {
/* 107 */     return this.curPage;
/*     */   }
/*     */ 
/*     */   public void setCurPage(int curPage) {
/* 111 */     this.curPage = curPage;
/*     */   }
/*     */ 
/*     */   public String getMyaction() {
/* 115 */     return this.myaction;
/*     */   }
/*     */ 
/*     */   public void setMyaction(String myaction) {
/* 119 */     this.myaction = myaction;
/*     */   }
/*     */ 
/*     */   public String getMyform() {
/* 123 */     return this.myform;
/*     */   }
/*     */ 
/*     */   public void setMyform(String myform) {
/* 127 */     this.myform = myform;
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/* 131 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 135 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public String getQueryString() {
/* 139 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString) {
/* 143 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public Type[] getTypes() {
/* 147 */     return this.types;
/*     */   }
/*     */ 
/*     */   public void setTypes(Type[] types) {
/* 151 */     this.types = types;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.hibernate.qbc.HqlQuery
 * JD-Core Version:    0.6.2
 */