/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ 
/*     */ public class CkfinderTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String name;
/*     */   protected String value;
/*     */   protected String width;
/*     */   protected String height;
/*     */   protected String buttonClass;
/*     */   protected String buttonValue;
/*     */   protected String uploadType;
/*     */ 
/*     */   public String getButtonValue()
/*     */   {
/*  29 */     return this.buttonValue;
/*     */   }
/*     */ 
/*     */   public void setButtonValue(String buttonValue) {
/*  33 */     this.buttonValue = buttonValue;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  37 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  41 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getValue() {
/*  45 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(String value) {
/*  49 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public String getWidth() {
/*  53 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/*  57 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public String getHeight() {
/*  61 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void setHeight(String height) {
/*  65 */     this.height = height;
/*     */   }
/*     */ 
/*     */   public String getButtonClass() {
/*  69 */     return this.buttonClass;
/*     */   }
/*     */ 
/*     */   public void setButtonClass(String buttonClass) {
/*  73 */     this.buttonClass = buttonClass;
/*     */   }
/*     */ 
/*     */   public String getUploadType() {
/*  77 */     return this.uploadType;
/*     */   }
/*     */ 
/*     */   public void setUploadType(String uploadType) {
/*  81 */     this.uploadType = uploadType;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException {
/*  85 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*  89 */     JspWriter out = null;
/*     */     try {
/*  91 */       out = this.pageContext.getOut();
/*  92 */       out.print(end().toString());
/*  93 */       out.flush();
/*     */     } catch (IOException e) {
/*  95 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  98 */         out.clear();
/*  99 */         out.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  98 */         out.clear();
/*  99 */         out.close();
/*     */       } catch (Exception localException1) {
/*     */       }
/*     */     }
/* 103 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/* 107 */     StringBuffer sb = new StringBuffer();
/* 108 */     if ("Images".equals(this.uploadType)) {
/* 109 */       sb.append("<img src=\"" + this.value + "\" width=\"" + this.width + 
/* 110 */         "\" height=\"" + this.height + "\" id=\"" + this.name + "_herf\" ");
/* 111 */       if (StringUtil.isEmpty(this.value))
/* 112 */         sb.append("hidden=\"hidden\"");
/* 113 */       sb.append(" />");
/*     */     } else {
/* 115 */       sb.append("<a href=\"" + this.value + "\" id=\"" + this.name + "_herf\"></a>");
/* 116 */       sb.append("<script type=\"text/javascript\">decode(\"" + this.value + 
/* 117 */         "\", \"" + this.name + "_herf\")</script>");
/*     */     }
/* 119 */     sb.append("<input type=\"hidden\" id=\"" + this.name + "_input\" name=\"" + 
/* 120 */       this.name + "\" value=\"" + this.value + "\">");
/* 121 */     sb.append("<input class=\"" + this.buttonClass + 
/* 122 */       "\" type=\"button\" value=\"" + this.buttonValue + 
/* 123 */       "\" onclick=\"browse('" + this.name + "_input','" + this.name + 
/* 124 */       "_herf','" + this.uploadType + "')\">");
/* 125 */     return sb;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.CkfinderTag
 * JD-Core Version:    0.6.2
 */