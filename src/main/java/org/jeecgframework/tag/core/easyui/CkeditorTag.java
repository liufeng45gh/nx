/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.PropertiesUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ 
/*     */ public class CkeditorTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String name;
/*     */   protected String value;
/*     */   protected boolean isfinder;
/*     */   protected String type;
/*     */ 
/*     */   public boolean isIsfinder()
/*     */   {
/*  26 */     return this.isfinder;
/*     */   }
/*     */ 
/*     */   public void setIsfinder(boolean isfinder) {
/*  30 */     this.isfinder = isfinder;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  34 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  38 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getValue() {
/*  42 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(String value) {
/*  46 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public String getType() {
/*  50 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  54 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException {
/*  58 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*  62 */     JspWriter out = null;
/*     */     try {
/*  64 */       out = this.pageContext.getOut();
/*  65 */       out.print(end().toString());
/*  66 */       out.flush();
/*     */     } catch (IOException e) {
/*  68 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  71 */         out.clear();
/*  72 */         out.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  71 */         out.clear();
/*  72 */         out.close();
/*     */       } catch (Exception localException1) {
/*     */       }
/*     */     }
/*  76 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/*  80 */     StringBuffer sb = new StringBuffer();
/*     */ 
/*  82 */     sb.append("<textarea id=\"" + this.name + "_text\" name=\"" + this.name + "\">" + 
/*  83 */       this.value + "</textarea>");
/*  84 */     sb.append("<script type=\"text/javascript\">var ckeditor_" + this.name + 
/*  85 */       "=CKEDITOR.replace(\"" + this.name + "_text\",{");
/*  86 */     if (this.isfinder) {
/*  87 */       PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
/*  88 */       sb.append("filebrowserBrowseUrl:" + 
/*  89 */         util.readProperty("filebrowserBrowseUrl") + ",");
/*  90 */       sb.append("filebrowserImageBrowseUrl:" + 
/*  91 */         util.readProperty("filebrowserImageBrowseUrl") + ",");
/*  92 */       sb.append("filebrowserFlashBrowseUrl:" + 
/*  93 */         util.readProperty("filebrowserFlashBrowseUrl") + ",");
/*  94 */       sb.append("filebrowserUploadUrl:" + 
/*  95 */         util.readProperty("filebrowserUploadUrl") + ",");
/*  96 */       sb.append("filebrowserImageUploadUrl:" + 
/*  97 */         util.readProperty("filebrowserImageUploadUrl") + ",");
/*  98 */       sb.append("filebrowserFlashUploadUrl:" + 
/*  99 */         util.readProperty("filebrowserFlashUploadUrl"));
/*     */     }
/* 101 */     if ((this.isfinder) && (StringUtil.isNotEmpty(this.type)))
/* 102 */       sb.append(",");
/* 103 */     if (StringUtil.isNotEmpty(this.type))
/* 104 */       sb.append(this.type);
/* 105 */     sb.append("});");
/* 106 */     if (this.isfinder) {
/* 107 */       sb.append("CKFinder.SetupCKEditor(ckeditor_" + this.name + ");");
/*     */     }
/* 109 */     sb.append("</script>");
/* 110 */     return sb;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.CkeditorTag
 * JD-Core Version:    0.6.2
 */