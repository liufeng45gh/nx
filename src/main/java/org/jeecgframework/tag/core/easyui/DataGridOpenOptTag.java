/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class DataGridOpenOptTag extends TagSupport
/*    */ {
/*    */   protected String url;
/* 17 */   protected String width = "100%";
/* 18 */   protected String height = "100%";
/*    */   protected String title;
/*    */   private String exp;
/*    */   private String operationCode;
/*    */   private String urlStyle;
/* 24 */   private String openModel = "OpenWin";
/*    */ 
/* 26 */   public int doStartTag() throws JspTagException { return 6; }
/*    */ 
/*    */   public int doEndTag() throws JspTagException {
/* 29 */     Tag t = findAncestorWithClass(this, DataGridTag.class);
/* 30 */     DataGridTag parent = (DataGridTag)t;
/* 31 */     parent.setOpenUrl(this.url, this.title, this.width, this.height, this.exp, this.operationCode, this.openModel, this.urlStyle);
/* 32 */     return 6;
/*    */   }
/*    */   public void setWidth(String width) {
/* 35 */     this.width = width;
/*    */   }
/*    */   public void setHeight(String height) {
/* 38 */     this.height = height;
/*    */   }
/*    */   public void setExp(String exp) {
/* 41 */     this.exp = exp;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 45 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 48 */     this.title = title;
/*    */   }
/*    */   public void setOperationCode(String operationCode) {
/* 51 */     this.operationCode = operationCode;
/*    */   }
/*    */   public void setOpenModel(String openModel) {
/* 54 */     this.openModel = openModel;
/*    */   }
/*    */   public void setUrlStyle(String urlStyle) {
/* 57 */     this.urlStyle = urlStyle;
/*    */   }
/*    */   public String getUrlStyle() {
/* 60 */     return this.urlStyle;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.DataGridOpenOptTag
 * JD-Core Version:    0.6.2
 */