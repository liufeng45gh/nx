/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ public class ComboBoxTag extends TagSupport
/*     */ {
/*     */   protected String id;
/*     */   protected String text;
/*     */   protected String url;
/*     */   protected String name;
/*     */   protected Integer width;
/*     */   protected Integer listWidth;
/*     */   protected Integer listHeight;
/*     */   protected boolean editable;
/*     */   protected String selectedValue;
/*  32 */   private boolean multiple = true;
/*     */ 
/*  34 */   public int doStartTag() throws JspTagException { return 6; }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*  37 */     JspWriter out = null;
/*     */     try {
/*  39 */       out = this.pageContext.getOut();
/*  40 */       out.print(end().toString());
/*  41 */       out.flush();
/*     */     } catch (IOException e) {
/*  43 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  46 */         out.clear();
/*  47 */         out.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  46 */         out.clear();
/*  47 */         out.close();
/*     */       } catch (Exception localException1) {
/*     */       }
/*     */     }
/*  51 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end()
/*     */   {
/*  95 */     StringBuffer sb = new StringBuffer();
/*     */ 
/* 100 */     String jqueryIdName = this.name;
/* 101 */     if (this.name.indexOf('.') > -1) {
/* 102 */       jqueryIdName = StringUtils.replace(jqueryIdName, ".", "\\\\.");
/*     */     }
/* 104 */     sb.append("<script type=\"text/javascript\">")
/* 105 */       .append("$(function() {")
/* 106 */       .append("$('#" + jqueryIdName + "').combobox({")
/* 107 */       .append("url:'" + this.url + "&id=" + this.id + "&text=" + this.text + "',")
/* 108 */       .append("editable:'false',")
/* 109 */       .append("multiple:").append(Boolean.toString(this.multiple)).append(",")
/* 110 */       .append("valueField:'id',")
/* 111 */       .append("textField:'text',")
/* 112 */       .append("width:'" + this.width + "',")
/* 113 */       .append("listWidth:'" + this.listWidth + "',")
/* 114 */       .append("listHeight:'" + this.listWidth + "',")
/* 115 */       .append("onChange:function(){")
/* 116 */       .append("var val = $('#" + jqueryIdName + 
/* 117 */       "').combobox('getValues');")
/* 118 */       .append("$('#" + jqueryIdName + "hidden').val(val);").append("},");
/*     */ 
/* 120 */     sb.append("onLoadSuccess:function(data){");
/* 121 */     if (StringUtil.isNotEmpty(this.selectedValue)) {
/* 122 */       sb.append("$('#").append(jqueryIdName).append("').combobox('setValue','").append(this.selectedValue).append("');");
/*     */     }
/* 124 */     sb.append("}").append("});});");
/* 125 */     sb.append("</script>");
/* 126 */     sb.append(
/* 127 */       "<input type=\"hidden\" name=\"").append(this.name).append("\" id=\"").append(this.name).append("hidden\"");
/*     */ 
/* 129 */     if (StringUtil.isNotEmpty(this.selectedValue)) {
/* 130 */       sb.append(" value=\"").append(this.selectedValue).append("\"");
/*     */     }
/* 132 */     sb.append(" > ");
/*     */ 
/* 134 */     sb.append("<input class=\"easyui-combobox\" ")
/* 135 */       .append(" panelHeight=\"auto\" name=\"" + this.name + "name\" id=\"" + 
/* 136 */       this.name + "\" >");
/* 137 */     return sb;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 147 */     this.id = id;
/*     */   }
/*     */   public void setText(String text) {
/* 150 */     this.text = text;
/*     */   }
/*     */   public void setUrl(String url) {
/* 153 */     this.url = url;
/*     */   }
/*     */   public void setName(String name) {
/* 156 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public boolean isEditable() {
/* 160 */     return this.editable;
/*     */   }
/*     */ 
/*     */   public void setEditable(boolean editable) {
/* 164 */     this.editable = editable;
/*     */   }
/*     */   public void setSelectedValue(String selectedValue) {
/* 167 */     this.selectedValue = selectedValue;
/*     */   }
/*     */   public void setMultiple(boolean multiple) {
/* 170 */     this.multiple = multiple;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.ComboBoxTag
 * JD-Core Version:    0.6.2
 */