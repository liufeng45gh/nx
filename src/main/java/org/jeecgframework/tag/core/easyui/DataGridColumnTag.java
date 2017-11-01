/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.tagext.Tag;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.MutiLangUtil;
/*     */ 
/*     */ public class DataGridColumnTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String title;
/*     */   protected String field;
/*     */   protected Integer width;
/*     */   protected Integer showLen;
/*     */   protected String rowspan;
/*     */   protected String colspan;
/*     */   protected String align;
/*  28 */   protected boolean sortable = true;
/*     */   protected boolean checkbox;
/*     */   protected String formatter;
/*     */   protected String formatterjs;
/*  33 */   protected boolean hidden = false;
/*     */   protected String replace;
/*     */   protected String treefield;
/*     */   protected boolean image;
/*  38 */   protected boolean query = false;
/*  39 */   private String queryMode = "single";
/*     */ 
/*  42 */   private boolean frozenColumn = false;
/*  43 */   protected boolean bSearchable = true;
/*     */   protected String url;
/*  45 */   protected String funname = "openwindow";
/*     */   protected String arg;
/*     */   protected String dictionary;
/*  48 */   protected boolean popup = false;
/*     */   protected String extend;
/*     */   protected String style;
/*     */   protected String imageSize;
/*     */   protected String downloadName;
/*  53 */   private boolean autocomplete = false;
/*     */   private String extendParams;
/*     */   private String langArg;
/*     */   protected String editor;
/*     */ 
/*     */   public String getEditor()
/*     */   {
/*  58 */     return this.editor;
/*     */   }
/*     */ 
/*     */   public void setEditor(String editor) {
/*  62 */     this.editor = editor;
/*     */   }
/*     */   public int doEndTag() throws JspTagException {
/*  65 */     this.title = MutiLangUtil.doMutiLang(this.title, this.langArg);
/*     */ 
/*  67 */     Tag t = findAncestorWithClass(this, DataGridTag.class);
/*  68 */     DataGridTag parent = (DataGridTag)t;
/*  69 */     parent.setColumn(this.title, this.field, this.width, this.showLen, this.rowspan, this.colspan, this.align, this.sortable, this.checkbox, this.formatter, this.formatterjs, this.hidden, this.replace, this.treefield, this.image, this.imageSize, this.query, this.url, this.funname, this.arg, this.queryMode, this.dictionary, this.popup, this.frozenColumn, this.extend, this.style, this.downloadName, this.autocomplete, this.extendParams, this.editor);
/*  70 */     return 6;
/*     */   }
/*     */ 
/*     */   public void setDownloadName(String downloadName) {
/*  74 */     this.downloadName = downloadName;
/*     */   }
/*     */ 
/*     */   public void setImageSize(String imageSize) {
/*  78 */     this.imageSize = imageSize;
/*     */   }
/*     */ 
/*     */   public void setArg(String arg) {
/*  82 */     this.arg = arg;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/*  86 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public void setFunname(String funname) {
/*  90 */     this.funname = funname;
/*     */   }
/*     */ 
/*     */   public void setbSearchable(boolean bSearchable) {
/*  94 */     this.bSearchable = bSearchable;
/*     */   }
/*     */ 
/*     */   public void setQuery(boolean query) {
/*  98 */     this.query = query;
/*     */   }
/*     */ 
/*     */   public void setImage(boolean image) {
/* 102 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public void setTreefield(String treefield) {
/* 106 */     this.treefield = treefield;
/*     */   }
/*     */ 
/*     */   public void setReplace(String replace) {
/* 110 */     this.replace = replace;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 114 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public void setField(String field) {
/* 118 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public void setWidth(Integer width) {
/* 122 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public void setRowspan(String rowspan) {
/* 126 */     this.rowspan = rowspan;
/*     */   }
/*     */ 
/*     */   public void setColspan(String colspan) {
/* 130 */     this.colspan = colspan;
/*     */   }
/*     */ 
/*     */   public void setAlign(String align) {
/* 134 */     this.align = align;
/*     */   }
/*     */ 
/*     */   public void setSortable(boolean sortable) {
/* 138 */     this.sortable = sortable;
/*     */   }
/*     */ 
/*     */   public void setCheckbox(boolean checkbox) {
/* 142 */     this.checkbox = checkbox;
/*     */   }
/*     */ 
/*     */   public void setFormatter(String formatter) {
/* 146 */     this.formatter = formatter;
/*     */   }
/*     */ 
/*     */   public void setHidden(boolean hidden) {
/* 150 */     this.hidden = hidden;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException {
/* 154 */     return 6;
/*     */   }
/*     */ 
/*     */   public void setDictionary(String dictionary)
/*     */   {
/* 159 */     this.dictionary = dictionary;
/*     */   }
/*     */   public String getQueryMode() {
/* 162 */     return this.queryMode;
/*     */   }
/*     */ 
/*     */   public boolean isPopup() {
/* 166 */     return this.popup;
/*     */   }
/*     */ 
/*     */   public void setPopup(boolean popup) {
/* 170 */     this.popup = popup;
/*     */   }
/*     */ 
/*     */   public void setQueryMode(String queryMode) {
/* 174 */     this.queryMode = queryMode;
/*     */   }
/*     */ 
/*     */   public boolean isFrozenColumn() {
/* 178 */     return this.frozenColumn;
/*     */   }
/*     */ 
/*     */   public void setFrozenColumn(boolean frozenColumn) {
/* 182 */     this.frozenColumn = frozenColumn;
/*     */   }
/*     */ 
/*     */   public String getExtend() {
/* 186 */     return this.extend;
/*     */   }
/*     */ 
/*     */   public void setExtend(String extend) {
/* 190 */     this.extend = extend;
/*     */   }
/*     */ 
/*     */   public void setStyle(String style) {
/* 194 */     this.style = style;
/*     */   }
/*     */ 
/*     */   public void setAutocomplete(boolean autocomplete) {
/* 198 */     this.autocomplete = autocomplete;
/*     */   }
/*     */ 
/*     */   public void setExtendParams(String extendParams) {
/* 202 */     this.extendParams = extendParams;
/*     */   }
/*     */ 
/*     */   public void setLangArg(String langArg) {
/* 206 */     this.langArg = langArg;
/*     */   }
/*     */ 
/*     */   public void setFormatterjs(String formatterjs) {
/* 210 */     this.formatterjs = formatterjs;
/*     */   }
/*     */ 
/*     */   public Integer getShowLen() {
/* 214 */     return this.showLen;
/*     */   }
/*     */ 
/*     */   public void setShowLen(Integer showLen) {
/* 218 */     this.showLen = showLen;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.DataGridColumnTag
 * JD-Core Version:    0.6.2
 */