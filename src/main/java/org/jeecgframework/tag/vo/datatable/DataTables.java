/*     */ package org.jeecgframework.tag.vo.datatable;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class DataTables
/*     */ {
/*     */   private HttpServletRequest request;
/*   9 */   private String sEchoParameter = "sEcho";
/*     */ 
/*  12 */   private String iDisplayStartParameter = "iDisplayStart";
/*  13 */   private String iDisplayLengthParameter = "iDisplayLength";
/*     */ 
/*  16 */   private String iColumnsParameter = "iColumns";
/*  17 */   private String sColumnsParameter = "sColumns";
/*     */   private String sColumns;
/*  21 */   private String iSortingColsParameter = "iSortingCols";
/*  22 */   private String iSortColPrefixParameter = "iSortCol_";
/*  23 */   private String sSortDirPrefixParameter = "sSortDir_";
/*     */ 
/*  27 */   private String bSortablePrefixParameter = "bSortable_";
/*     */ 
/*  30 */   private String sSearchParameter = "sSearch";
/*  31 */   private String bRegexParameter = "bRegex";
/*     */ 
/*  34 */   private String bSearchablePrefixParameter = "bSearchable_";
/*  35 */   private String sSearchPrefixParameter = "sSearch_";
/*  36 */   private String bEscapeRegexPrefixParameter = "bRegex_";
/*     */   private Integer echo;
/*     */   private int displayStart;
/*     */   private int displayLength;
/*     */   private int sortingCols;
/*     */   public int iSortingCols;
/*     */   private SortInfo[] sortColumns;
/*     */   private int ColumnCount;
/*     */   private ColumnInfo[] columns;
/*     */   private String search;
/*     */   private Boolean regex;
/*     */ 
/*     */   public SortInfo[] getSortColumns()
/*     */   {
/*  39 */     return this.sortColumns;
/*     */   }
/*     */ 
/*     */   public void setSortColumns(SortInfo[] sortColumns) {
/*  43 */     this.sortColumns = sortColumns;
/*     */   }
/*     */ 
/*     */   public int getColumnCount() {
/*  47 */     return this.ColumnCount;
/*     */   }
/*     */ 
/*     */   public void setColumnCount(int columnCount) {
/*  51 */     this.ColumnCount = columnCount;
/*     */   }
/*     */ 
/*     */   public ColumnInfo[] getColumns() {
/*  55 */     return this.columns;
/*     */   }
/*     */ 
/*     */   public void setColumns(ColumnInfo[] columns) {
/*  59 */     this.columns = columns;
/*     */   }
/*     */ 
/*     */   public String getSearch() {
/*  63 */     return this.search;
/*     */   }
/*     */ 
/*     */   public void setSearch(String search) {
/*  67 */     this.search = search;
/*     */   }
/*     */ 
/*     */   public Boolean getRegex() {
/*  71 */     return this.regex;
/*     */   }
/*     */ 
/*     */   public void setRegex(Boolean regex) {
/*  75 */     this.regex = regex;
/*     */   }
/*     */ 
/*     */   public Integer getEcho() {
/*  79 */     return this.echo;
/*     */   }
/*     */ 
/*     */   public int getDisplayStart() {
/*  83 */     return this.displayStart;
/*     */   }
/*     */ 
/*     */   public int getDisplayLength() {
/*  87 */     return this.displayLength;
/*     */   }
/*     */ 
/*     */   public int getSortingCols() {
/*  91 */     return this.sortingCols;
/*     */   }
/*     */ 
/*     */   public void DataTablePram(HttpServletRequest httpRequest)
/*     */   {
/* 116 */     this.request = httpRequest;
/*     */   }
/*     */ 
/*     */   public DataTables(HttpServletRequest request)
/*     */   {
/* 121 */     this.request = request;
/*     */ 
/* 123 */     this.echo = Integer.valueOf(ParseIntParameter(this.sEchoParameter));
/* 124 */     this.displayStart = ParseIntParameter(this.iDisplayStartParameter);
/* 125 */     this.displayLength = ParseIntParameter(this.iDisplayLengthParameter);
/* 126 */     this.sortingCols = ParseIntParameter(this.iSortingColsParameter);
/*     */ 
/* 128 */     this.search = ParseStringParameter(this.sSearchParameter);
/* 129 */     this.regex = Boolean.valueOf(ParseStringParameter(this.bRegexParameter) == "true");
/*     */ 
/* 132 */     int count = this.sortingCols;
/* 133 */     this.sortColumns = new SortInfo[count];
/* 134 */     MessageFormat formatter = new MessageFormat("");
/* 135 */     for (int i = 0; i < count; i++) {
/* 136 */       SortInfo sortInfo = new SortInfo();
/* 137 */       sortInfo.setColumnId(Integer.valueOf(ParseIntParameter(MessageFormat.format("iSortCol_{0}", new Object[] { Integer.valueOf(i) }))));
/* 138 */       String aString = ParseStringParameter(MessageFormat.format("sSortDir_{0}", new Object[] { Integer.valueOf(i) }));
/* 139 */       if (ParseStringParameter(MessageFormat.format("sSortDir_{0}", new Object[] { Integer.valueOf(i) })).equals("desc"))
/* 140 */         sortInfo.setSortOrder(SortDirection.asc);
/*     */       else {
/* 142 */         sortInfo.setSortOrder(SortDirection.desc);
/*     */       }
/* 144 */       this.sortColumns[i] = sortInfo;
/*     */     }
/*     */ 
/* 147 */     this.ColumnCount = ParseIntParameter(this.iColumnsParameter);
/*     */ 
/* 149 */     count = this.ColumnCount;
/* 150 */     this.columns = new ColumnInfo[count];
/*     */ 
/* 152 */     String[] names = ParseStringParameter(this.sColumnsParameter).split(",");
/* 153 */     this.sColumns = ParseStringParameter(this.sColumnsParameter);
/*     */ 
/* 155 */     for (int i = 0; i < names.length; i++) {
/* 156 */       ColumnInfo col = new ColumnInfo();
/* 157 */       col.setName(names[i]);
/* 158 */       col.setSortable(ParseBooleanParameter(MessageFormat.format("bSortable_{0}", new Object[] { Integer.valueOf(i) })));
/* 159 */       col.setSearchable(ParseBooleanParameter(MessageFormat.format("bSearchable_{0}", new Object[] { Integer.valueOf(i) })));
/* 160 */       col.setSearch(ParseStringParameter(MessageFormat.format("sSearch_{0}", new Object[] { Integer.valueOf(i) })));
/* 161 */       col.setRegex(Boolean.valueOf(ParseStringParameter(MessageFormat.format("bRegex_{0}", new Object[] { Integer.valueOf(i) })) == "true"));
/* 162 */       this.columns[i] = col;
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getsColumns() {
/* 167 */     return this.sColumns;
/*     */   }
/*     */ 
/*     */   public void setsColumns(String sColumns) {
/* 171 */     this.sColumns = sColumns;
/*     */   }
/*     */ 
/*     */   private int ParseIntParameter(String name)
/*     */   {
/* 176 */     int result = 0;
/* 177 */     String parameter = this.request.getParameter(name);
/* 178 */     if (parameter != null) {
/* 179 */       result = Integer.parseInt(parameter);
/*     */     }
/* 181 */     return result;
/*     */   }
/*     */ 
/*     */   private String ParseStringParameter(String name)
/*     */   {
/* 186 */     return this.request.getParameter(name);
/*     */   }
/*     */ 
/*     */   private Boolean ParseBooleanParameter(String name)
/*     */   {
/* 191 */     Boolean result = Boolean.valueOf(false);
/* 192 */     String parameter = this.request.getParameter(name);
/* 193 */     if (parameter != null) {
/* 194 */       result = Boolean.valueOf(Boolean.parseBoolean(parameter));
/*     */     }
/* 196 */     return result;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.vo.datatable.DataTables
 * JD-Core Version:    0.6.2
 */