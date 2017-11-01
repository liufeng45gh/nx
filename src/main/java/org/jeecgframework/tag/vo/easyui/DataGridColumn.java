/*     */ package org.jeecgframework.tag.vo.easyui;
/*     */ 
/*     */ public class DataGridColumn
/*     */ {
/*     */   protected String title;
/*     */   protected String field;
/*     */   protected Integer width;
/*     */   protected Integer showLen;
/*     */   protected String rowspan;
/*     */   protected String colspan;
/*     */   protected String align;
/*     */   protected boolean sortable;
/*     */   protected boolean checkbox;
/*     */   protected String formatter;
/*     */   protected String formatterjs;
/*     */   protected boolean hidden;
/*     */   protected String treefield;
/*     */   protected boolean image;
/*     */   protected boolean query;
/*  28 */   protected String queryMode = "single";
/*     */ 
/*  30 */   protected boolean autoLoadData = true;
/*  31 */   private boolean frozenColumn = false;
/*     */   protected String url;
/*  33 */   protected String funname = "openwindow";
/*     */   protected String arg;
/*     */   protected String dictionary;
/*  36 */   protected boolean popup = false;
/*     */   protected String replace;
/*     */   protected String extend;
/*     */   protected String style;
/*     */   protected String imageSize;
/*     */   protected String downloadName;
/*     */   protected boolean autocomplete;
/*     */   protected String extendParams;
/*     */   protected String editor;
/*     */ 
/*     */   public String getEditor()
/*     */   {
/*  46 */     return this.editor;
/*     */   }
/*     */ 
/*     */   public void setEditor(String editor) {
/*  50 */     this.editor = editor;
/*     */   }
/*     */   public String getDownloadName() {
/*  53 */     return this.downloadName;
/*     */   }
/*     */ 
/*     */   public void setDownloadName(String downloadName) {
/*  57 */     this.downloadName = downloadName;
/*     */   }
/*     */ 
/*     */   public String getImageSize() {
/*  61 */     return this.imageSize;
/*     */   }
/*     */ 
/*     */   public void setImageSize(String imageSize) {
/*  65 */     this.imageSize = imageSize;
/*     */   }
/*     */ 
/*     */   public boolean isQuery() {
/*  69 */     return this.query;
/*     */   }
/*     */ 
/*     */   public String getArg() {
/*  73 */     return this.arg;
/*     */   }
/*     */ 
/*     */   public void setArg(String arg) {
/*  77 */     this.arg = arg;
/*     */   }
/*     */ 
/*     */   public void setQuery(boolean query) {
/*  81 */     this.query = query;
/*     */   }
/*     */ 
/*     */   public boolean isImage() {
/*  85 */     return this.image;
/*     */   }
/*     */ 
/*     */   public void setImage(boolean image) {
/*  89 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public String getTreefield() {
/*  93 */     return this.treefield;
/*     */   }
/*     */ 
/*     */   public void setTreefield(String treefield) {
/*  97 */     this.treefield = treefield;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 101 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public void setField(String field) {
/* 105 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public void setWidth(Integer width) {
/* 109 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public Integer getShowLen() {
/* 113 */     return this.showLen;
/*     */   }
/*     */ 
/*     */   public void setShowLen(Integer showLen) {
/* 117 */     this.showLen = showLen;
/*     */   }
/*     */ 
/*     */   public void setRowspan(String rowspan) {
/* 121 */     this.rowspan = rowspan;
/*     */   }
/*     */ 
/*     */   public void setColspan(String colspan) {
/* 125 */     this.colspan = colspan;
/*     */   }
/*     */ 
/*     */   public void setAlign(String align) {
/* 129 */     this.align = align;
/*     */   }
/*     */ 
/*     */   public void setSortable(boolean sortable) {
/* 133 */     this.sortable = sortable;
/*     */   }
/*     */ 
/*     */   public void setCheckbox(boolean checkbox) {
/* 137 */     this.checkbox = checkbox;
/*     */   }
/*     */ 
/*     */   public void setFormatter(String formatter) {
/* 141 */     this.formatter = formatter;
/*     */   }
/*     */   public boolean isHidden() {
/* 144 */     return this.hidden;
/*     */   }
/*     */ 
/*     */   public void setHidden(boolean hidden) {
/* 148 */     this.hidden = hidden;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/* 152 */     return this.title;
/*     */   }
/*     */ 
/*     */   public String getField() {
/* 156 */     return this.field;
/*     */   }
/*     */ 
/*     */   public Integer getWidth() {
/* 160 */     return this.width;
/*     */   }
/*     */ 
/*     */   public String getRowspan() {
/* 164 */     return this.rowspan;
/*     */   }
/*     */ 
/*     */   public String getColspan() {
/* 168 */     return this.colspan;
/*     */   }
/*     */ 
/*     */   public String getAlign() {
/* 172 */     return this.align;
/*     */   }
/*     */ 
/*     */   public boolean isSortable() {
/* 176 */     return this.sortable;
/*     */   }
/*     */ 
/*     */   public boolean isCheckbox() {
/* 180 */     return this.checkbox;
/*     */   }
/*     */ 
/*     */   public String getFormatter() {
/* 184 */     return this.formatter;
/*     */   }
/*     */   public String getUrl() {
/* 187 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 191 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getFunname() {
/* 195 */     return this.funname;
/*     */   }
/*     */ 
/*     */   public void setFunname(String funname) {
/* 199 */     this.funname = funname;
/*     */   }
/*     */ 
/*     */   public String getDictionary() {
/* 203 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */   public void setDictionary(String dictionary) {
/* 207 */     this.dictionary = dictionary;
/*     */   }
/*     */   public boolean isPopup() {
/* 210 */     return this.popup;
/*     */   }
/*     */ 
/*     */   public void setPopup(boolean popup) {
/* 214 */     this.popup = popup;
/*     */   }
/*     */ 
/*     */   public String getQueryMode() {
/* 218 */     return this.queryMode;
/*     */   }
/*     */ 
/*     */   public void setQueryMode(String queryMode) {
/* 222 */     this.queryMode = queryMode;
/*     */   }
/*     */ 
/*     */   public String getReplace() {
/* 226 */     return this.replace;
/*     */   }
/*     */ 
/*     */   public void setReplace(String replace) {
/* 230 */     this.replace = replace;
/*     */   }
/*     */ 
/*     */   public boolean isAutoLoadData() {
/* 234 */     return this.autoLoadData;
/*     */   }
/*     */ 
/*     */   public void setAutoLoadData(boolean autoLoadData) {
/* 238 */     this.autoLoadData = autoLoadData;
/*     */   }
/*     */ 
/*     */   public boolean isFrozenColumn() {
/* 242 */     return this.frozenColumn;
/*     */   }
/*     */ 
/*     */   public void setFrozenColumn(boolean frozenColumn) {
/* 246 */     this.frozenColumn = frozenColumn;
/*     */   }
/*     */ 
/*     */   public String getExtend() {
/* 250 */     return this.extend;
/*     */   }
/*     */ 
/*     */   public void setExtend(String extend) {
/* 254 */     this.extend = extend;
/*     */   }
/*     */ 
/*     */   public String getStyle() {
/* 258 */     return this.style;
/*     */   }
/*     */ 
/*     */   public void setStyle(String style) {
/* 262 */     this.style = style;
/*     */   }
/*     */ 
/*     */   public boolean isAutocomplete() {
/* 266 */     return this.autocomplete;
/*     */   }
/*     */ 
/*     */   public void setAutocomplete(boolean autocomplete) {
/* 270 */     this.autocomplete = autocomplete;
/*     */   }
/*     */ 
/*     */   public String getExtendParams() {
/* 274 */     return this.extendParams;
/*     */   }
/*     */ 
/*     */   public void setExtendParams(String extendParams) {
/* 278 */     this.extendParams = extendParams;
/*     */   }
/*     */ 
/*     */   public String getFormatterjs() {
/* 282 */     return this.formatterjs;
/*     */   }
/*     */ 
/*     */   public void setFormatterjs(String formatterjs) {
/* 286 */     this.formatterjs = formatterjs;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.vo.easyui.DataGridColumn
 * JD-Core Version:    0.6.2
 */