/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import org.jeecgframework.core.util.ApplicationContextUtil;
/*    */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class TabTag extends TagSupport
/*    */ {
/*    */   private String href;
/*    */   private String iframe;
/*    */   private String id;
/*    */   private String title;
/* 24 */   private String icon = "icon-default";
/*    */   private String width;
/*    */   private String heigth;
/*    */   private boolean cache;
/*    */   private String content;
/* 29 */   private boolean closable = false;
/*    */   private String langArg;
/*    */ 
/*    */   @Autowired
/*    */   private static MutiLangServiceI mutiLangService;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 36 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 39 */     Tag t = findAncestorWithClass(this, TabsTag.class);
/* 40 */     TabsTag parent = (TabsTag)t;
/* 41 */     parent.setTab(this.id, this.title, this.iframe, this.href, this.icon, this.cache, this.content, this.width, this.heigth, this.closable);
/* 42 */     return 6;
/*    */   }
/*    */   public void setHref(String href) {
/* 45 */     this.href = href;
/*    */   }
/*    */   public void setId(String id) {
/* 48 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public void setTitle(String title) {
/* 52 */     if (mutiLangService == null)
/*    */     {
/* 54 */       mutiLangService = (MutiLangServiceI)ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
/*    */     }
/*    */ 
/* 57 */     String lang_context = mutiLangService.getLang(title, this.langArg);
/*    */ 
/* 59 */     this.title = lang_context;
/*    */   }
/*    */   public void setIcon(String icon) {
/* 62 */     this.icon = icon;
/*    */   }
/*    */   public void setWidth(String width) {
/* 65 */     this.width = width;
/*    */   }
/*    */   public void setHeigth(String heigth) {
/* 68 */     this.heigth = heigth;
/*    */   }
/*    */   public void setCache(boolean cache) {
/* 71 */     this.cache = cache;
/*    */   }
/*    */   public void setContent(String content) {
/* 74 */     this.content = content;
/*    */   }
/*    */   public void setClosable(boolean closable) {
/* 77 */     this.closable = closable;
/*    */   }
/*    */   public void setIframe(String iframe) {
/* 80 */     this.iframe = iframe;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.TabTag
 * JD-Core Version:    0.6.2
 */