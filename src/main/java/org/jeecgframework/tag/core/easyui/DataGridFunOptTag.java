/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import org.jeecgframework.core.util.MutiLangUtil;
/*    */ 
/*    */ public class DataGridFunOptTag extends TagSupport
/*    */ {
/*    */   protected String title;
/*    */   private String exp;
/*    */   private String funname;
/*    */   private String operationCode;
/*    */   private String langArg;
/*    */   private String urlStyle;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 28 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 31 */     this.title = MutiLangUtil.doMutiLang(this.title, this.langArg);
/*    */ 
/* 33 */     Tag t = findAncestorWithClass(this, DataGridTag.class);
/* 34 */     DataGridTag parent = (DataGridTag)t;
/* 35 */     parent.setFunUrl(this.title, this.exp, this.funname, this.operationCode, this.urlStyle);
/* 36 */     return 6;
/*    */   }
/*    */   public void setFunname(String funname) {
/* 39 */     this.funname = funname;
/*    */   }
/*    */   public void setExp(String exp) {
/* 42 */     this.exp = exp;
/*    */   }
/*    */   public void setTitle(String title) {
/* 45 */     this.title = title;
/*    */   }
/*    */   public void setOperationCode(String operationCode) {
/* 48 */     this.operationCode = operationCode;
/*    */   }
/*    */ 
/*    */   public void setLangArg(String langArg) {
/* 52 */     this.langArg = langArg;
/*    */   }
/*    */   public void setUrlStyle(String urlStyle) {
/* 55 */     this.urlStyle = urlStyle;
/*    */   }
/*    */   public String getUrlStyle() {
/* 58 */     return this.urlStyle;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.DataGridFunOptTag
 * JD-Core Version:    0.6.2
 */