/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import org.jeecgframework.core.util.MutiLangUtil;
/*    */ 
/*    */ public class DataGridDelOptTag extends TagSupport
/*    */ {
/*    */   protected String url;
/*    */   protected String title;
/*    */   private String message;
/*    */   private String exp;
/*    */   private String funname;
/*    */   private String operationCode;
/*    */   private String langArg;
/*    */   private String urlStyle;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 29 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 32 */     this.title = MutiLangUtil.doMutiLang(this.title, this.langArg);
/*    */ 
/* 34 */     Tag t = findAncestorWithClass(this, DataGridTag.class);
/* 35 */     DataGridTag parent = (DataGridTag)t;
/* 36 */     parent.setDelUrl(this.url, this.title, this.message, this.exp, this.funname, this.operationCode, this.urlStyle);
/* 37 */     return 6;
/*    */   }
/*    */   public void setFunname(String funname) {
/* 40 */     this.funname = funname;
/*    */   }
/*    */   public void setExp(String exp) {
/* 43 */     this.exp = exp;
/*    */   }
/*    */   public void setUrl(String url) {
/* 46 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 49 */     this.title = title;
/*    */   }
/*    */   public void setMessage(String message) {
/* 52 */     this.message = message;
/*    */   }
/*    */   public void setOperationCode(String operationCode) {
/* 55 */     this.operationCode = operationCode;
/*    */   }
/*    */ 
/*    */   public void setLangArg(String langArg) {
/* 59 */     this.langArg = langArg;
/*    */   }
/*    */   public void setUrlStyle(String urlStyle) {
/* 62 */     this.urlStyle = urlStyle;
/*    */   }
/*    */   public String getUrlStyle() {
/* 65 */     return this.urlStyle;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.DataGridDelOptTag
 * JD-Core Version:    0.6.2
 */