/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import org.jeecgframework.core.util.MutiLangUtil;
/*    */ 
/*    */ public class DataGridToolBarTag extends TagSupport
/*    */ {
/*    */   protected String url;
/*    */   protected String title;
/*    */   private String exp;
/*    */   private String funname;
/*    */   private String icon;
/*    */   private String onclick;
/*    */   private String width;
/*    */   private String height;
/*    */   private String operationCode;
/*    */   private String langArg;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 27 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 30 */     this.title = MutiLangUtil.doMutiLang(this.title, this.langArg);
/*    */ 
/* 32 */     Tag t = findAncestorWithClass(this, DataGridTag.class);
/* 33 */     DataGridTag parent = (DataGridTag)t;
/* 34 */     parent.setToolbar(this.url, this.title, this.icon, this.exp, this.onclick, this.funname, this.operationCode, this.width, this.height);
/* 35 */     return 6;
/*    */   }
/*    */ 
/*    */   public void setFunname(String funname) {
/* 39 */     this.funname = funname;
/*    */   }
/*    */   public void setExp(String exp) {
/* 42 */     this.exp = exp;
/*    */   }
/*    */   public void setUrl(String url) {
/* 45 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 48 */     this.title = title;
/*    */   }
/*    */   public void setIcon(String icon) {
/* 51 */     this.icon = icon;
/*    */   }
/*    */   public void setOnclick(String onclick) {
/* 54 */     this.onclick = onclick;
/*    */   }
/*    */   public void setOperationCode(String operationCode) {
/* 57 */     this.operationCode = operationCode;
/*    */   }
/*    */   public String getWidth() {
/* 60 */     return this.width;
/*    */   }
/*    */   public void setWidth(String width) {
/* 63 */     this.width = width;
/*    */   }
/*    */   public String getHeight() {
/* 66 */     return this.height;
/*    */   }
/*    */   public void setHeight(String height) {
/* 69 */     this.height = height;
/*    */   }
/*    */   public void setLangArg(String langArg) {
/* 72 */     this.langArg = langArg;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.DataGridToolBarTag
 * JD-Core Version:    0.6.2
 */