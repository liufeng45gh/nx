/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class DataGridDefOptTag extends TagSupport
/*    */ {
/*    */   protected String url;
/*    */   protected String title;
/*    */   private String exp;
/*    */   private String operationCode;
/*    */   private String urlStyle;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 22 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 25 */     Tag t = findAncestorWithClass(this, DataGridTag.class);
/* 26 */     DataGridTag parent = (DataGridTag)t;
/* 27 */     parent.setDefUrl(this.url, this.title, this.exp, this.operationCode, this.urlStyle);
/* 28 */     return 6;
/*    */   }
/*    */ 
/*    */   public void setExp(String exp) {
/* 32 */     this.exp = exp;
/*    */   }
/*    */   public void setUrl(String url) {
/* 35 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 38 */     this.title = title;
/*    */   }
/*    */   public void setOperationCode(String operationCode) {
/* 41 */     this.operationCode = operationCode;
/*    */   }
/*    */   public void setUrlStyle(String urlStyle) {
/* 44 */     this.urlStyle = urlStyle;
/*    */   }
/*    */   public String getUrlStyle() {
/* 47 */     return this.urlStyle;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.DataGridDefOptTag
 * JD-Core Version:    0.6.2
 */