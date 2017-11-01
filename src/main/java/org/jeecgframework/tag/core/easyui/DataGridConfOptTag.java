/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import org.jeecgframework.core.util.MutiLangUtil;
/*    */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*    */ 
/*    */ public class DataGridConfOptTag extends TagSupport
/*    */ {
/*    */   protected String url;
/*    */   protected String title;
/*    */   private String message;
/*    */   private String exp;
/*    */   private String operationCode;
/*    */   private String urlStyle;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 25 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 28 */     Tag t = findAncestorWithClass(this, DataGridTag.class);
/* 29 */     DataGridTag parent = (DataGridTag)t;
/* 30 */     parent.setConfUrl(this.url, MutiLangUtil.getMutiLangInstance().getLang(this.title), MutiLangUtil.getMutiLangInstance().getLang(this.message), this.exp, this.operationCode, this.urlStyle);
/* 31 */     return 6;
/*    */   }
/*    */   public void setExp(String exp) {
/* 34 */     this.exp = exp;
/*    */   }
/*    */   public void setUrl(String url) {
/* 37 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 40 */     this.title = title;
/*    */   }
/*    */   public void setMessage(String message) {
/* 43 */     this.message = message;
/*    */   }
/*    */   public void setOperationCode(String operationCode) {
/* 46 */     this.operationCode = operationCode;
/*    */   }
/*    */   public void setUrlStyle(String urlStyle) {
/* 49 */     this.urlStyle = urlStyle;
/*    */   }
/*    */   public String getUrlStyle() {
/* 52 */     return this.urlStyle;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.DataGridConfOptTag
 * JD-Core Version:    0.6.2
 */