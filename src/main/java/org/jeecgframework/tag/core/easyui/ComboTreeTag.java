/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class ComboTreeTag extends TagSupport
/*    */ {
/*    */   protected String id;
/*    */   protected String url;
/*    */   protected String name;
/*    */   protected String width;
/*    */   protected String value;
/* 23 */   private boolean multiple = false;
/*    */ 
/* 25 */   public int doStartTag() throws JspTagException { return 6; }
/*    */ 
/*    */   public int doEndTag() throws JspTagException
/*    */   {
/* 29 */     JspWriter out = null;
/*    */     try {
/* 31 */       out = this.pageContext.getOut();
/* 32 */       out.print(end().toString());
/* 33 */       out.flush();
/*    */     } catch (IOException e) {
/* 35 */       e.printStackTrace();
/*    */       try
/*    */       {
/* 38 */         out.clear();
/* 39 */         out.close();
/*    */       }
/*    */       catch (Exception localException)
/*    */       {
/*    */       }
/*    */     }
/*    */     finally
/*    */     {
/*    */       try
/*    */       {
/* 38 */         out.clear();
/* 39 */         out.close();
/*    */       } catch (Exception localException1) {
/*    */       }
/*    */     }
/* 43 */     return 6;
/*    */   }
/*    */ 
/*    */   public StringBuffer end() {
/* 47 */     StringBuffer sb = new StringBuffer();
/* 48 */     this.width = (this.width == null ? "140" : this.width);
/* 49 */     sb.append("<script type=\"text/javascript\">$(function() { $('#" + 
/* 50 */       this.id + "').combotree({\t\t " + 
/* 51 */       "url :'" + this.url + "'," + 
/* 52 */       "width :'" + this.width + "'," + 
/* 53 */       "multiple:" + this.multiple + 
/* 54 */       "});\t\t" + 
/* 55 */       "});\t" + 
/* 56 */       "</script>");
/* 57 */     sb.append("<input  name=\"" + this.name + "\" id=\"" + this.id + "\" ");
/* 58 */     if (this.value != null)
/*    */     {
/* 60 */       sb.append("value=" + this.value);
/*    */     }
/* 62 */     sb.append(">");
/* 63 */     return sb;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 67 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 71 */     this.url = url;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 75 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public void setWidth(String width) {
/* 79 */     this.width = width;
/*    */   }
/*    */ 
/*    */   public void setValue(String value) {
/* 83 */     this.value = value;
/*    */   }
/*    */   public void setMultiple(boolean multiple) {
/* 86 */     this.multiple = multiple;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.ComboTreeTag
 * JD-Core Version:    0.6.2
 */