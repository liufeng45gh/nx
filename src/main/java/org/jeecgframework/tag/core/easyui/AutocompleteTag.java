/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ 
/*     */ public class AutocompleteTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String name;
/*  23 */   private String dataSource = "commonController.do?getAutoList";
/*  24 */   private Integer minLength = Integer.valueOf(2);
/*     */   private String labelField;
/*     */   private String searchField;
/*     */   private String valueField;
/*     */   private String entityName;
/*     */   private String selectfun;
/*     */   private String label;
/*     */   private String value;
/*  32 */   private String datatype = "*";
/*  33 */   private String nullmsg = "";
/*  34 */   private String errormsg = "输入格式不对";
/*     */   private String closefun;
/*     */   private String parse;
/*     */   private String formatItem;
/*     */   private String result;
/*  39 */   private Integer maxRows = Integer.valueOf(10);
/*     */ 
/*     */   public void setClosefun(String closefun) {
/*  42 */     this.closefun = closefun;
/*     */   }
/*     */   public void setDatatype(String datatype) {
/*  45 */     this.datatype = datatype;
/*     */   }
/*     */   public void setNullmsg(String nullmsg) {
/*  48 */     this.nullmsg = nullmsg;
/*     */   }
/*     */   public void setErrormsg(String errormsg) {
/*  51 */     this.errormsg = errormsg;
/*     */   }
/*     */   public void setLabel(String label) {
/*  54 */     this.label = label;
/*     */   }
/*     */   public void setValue(String value) {
/*  57 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException {
/*  61 */     return 6;
/*     */   }
/*     */   public int doEndTag() throws JspTagException {
/*  64 */     JspWriter out = null;
/*     */     try {
/*  66 */       out = this.pageContext.getOut();
/*  67 */       out.print(end().toString());
/*  68 */       out.flush();
/*     */     } catch (IOException e) {
/*  70 */       e.printStackTrace();
/*     */     }
/*  72 */     return 6;
/*     */   }
/*     */   public StringBuffer end() {
/*  75 */     StringBuffer nsb = new StringBuffer();
/*  76 */     nsb.append("<script type=\"text/javascript\">");
/*  77 */     nsb.append("$(document).ready(function() {")
/*  78 */       .append("$(\"#" + this.name + "\").autocomplete(\"" + this.dataSource + "\",{")
/*  79 */       .append("max: 5,minChars: " + this.minLength + ",width: 200,scrollHeight: 100,matchContains: true,autoFill: false,extraParams:{")
/*  80 */       .append("featureClass : \"P\",style : \"full\",\tmaxRows : " + this.maxRows + ",labelField : \"" + this.labelField + "\",valueField : \"" + this.valueField + "\",")
/*  81 */       .append("searchField : \"" + this.searchField + "\",entityName : \"" + this.entityName + "\",trem: getTremValue" + this.name + "}");
/*  82 */     if (StringUtil.isNotEmpty(this.parse)) {
/*  83 */       nsb.append(",parse:function(data){return " + this.parse + ".call(this,data);}");
/*     */     }
/*  85 */     if (StringUtil.isNotEmpty(this.formatItem)) {
/*  86 */       nsb.append(",formatItem:function(row, i, max){return " + this.formatItem + ".call(this,row, i, max);} ");
/*     */     }
/*  88 */     nsb.append("}).result(function (event, row, formatted) {");
/*  89 */     if (StringUtil.isNotEmpty(this.result)) {
/*  90 */       nsb.append(this.result + ".call(this,row); ");
/*     */     }
/*  92 */     nsb.append("}); });")
/*  93 */       .append("function getTremValue" + this.name + "(){return $(\"#" + this.name + "\").val();}")
/*  94 */       .append("</script>")
/*  95 */       .append("<input type=\"text\" id=\"" + this.name + "\" datatype=\"" + this.datatype + "\" nullmsg=\"" + this.nullmsg + "\" errormsg=\"" + this.errormsg + "\"/>");
/*  96 */     if (StringUtil.isNotEmpty(this.label)) {
/*  97 */       nsb.append(" value=" + this.label + " readonly=true");
/*     */     }
/*  99 */     nsb.append("<input type=\"hidden\" id=\"" + this.valueField + "\" name=\"" + this.valueField + "\"/>");
/* 100 */     return nsb;
/*     */   }
/*     */   public void setName(String name) {
/* 103 */     this.name = name;
/*     */   }
/*     */   public void setParse(String parse) {
/* 106 */     this.parse = parse;
/*     */   }
/*     */   public void setFormatItem(String formatItem) {
/* 109 */     this.formatItem = formatItem;
/*     */   }
/*     */   public void setResult(String result) {
/* 112 */     this.result = result;
/*     */   }
/*     */   public void setDataSource(String dataSource) {
/* 115 */     this.dataSource = dataSource;
/*     */   }
/*     */   public void setMinLength(Integer minLength) {
/* 118 */     this.minLength = minLength;
/*     */   }
/*     */   public void setLabelField(String labelField) {
/* 121 */     this.labelField = labelField;
/*     */   }
/*     */   public void setValueField(String valueField) {
/* 124 */     this.valueField = valueField;
/*     */   }
/*     */   public void setEntityName(String entityName) {
/* 127 */     this.entityName = entityName;
/*     */   }
/*     */   public void setSearchField(String searchField) {
/* 130 */     this.searchField = searchField;
/*     */   }
/*     */   public void setSelectfun(String selectfun) {
/* 133 */     this.selectfun = selectfun;
/*     */   }
/*     */   public void setMaxRows(Integer maxRows) {
/* 136 */     if (maxRows == null) {
/* 137 */       maxRows = Integer.valueOf(10);
/*     */     }
/* 139 */     this.maxRows = maxRows;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.AutocompleteTag
 * JD-Core Version:    0.6.2
 */