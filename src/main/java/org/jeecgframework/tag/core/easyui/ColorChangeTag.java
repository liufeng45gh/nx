/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class ColorChangeTag extends TagSupport
/*    */ {
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 19 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 22 */     JspWriter out = null;
/*    */     try {
/* 24 */       out = this.pageContext.getOut();
/* 25 */       out.print(end().toString());
/* 26 */       out.flush();
/*    */     } catch (IOException e) {
/* 28 */       e.printStackTrace();
/*    */       try
/*    */       {
/* 31 */         out.clear();
/* 32 */         out.close();
/*    */       }
/*    */       catch (Exception localException)
/*    */       {
/*    */       }
/*    */     }
/*    */     finally
/*    */     {
/*    */       try
/*    */       {
/* 31 */         out.clear();
/* 32 */         out.close();
/*    */       } catch (Exception localException1) {
/*    */       }
/*    */     }
/* 36 */     return 6;
/*    */   }
/*    */   public StringBuffer end() {
/* 39 */     StringBuffer sb = new StringBuffer();
/* 40 */     return sb;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.ColorChangeTag
 * JD-Core Version:    0.6.2
 */