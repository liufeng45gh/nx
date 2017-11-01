/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.ListtoMenu;
/*     */ import org.jeecgframework.web.system.pojo.base.TSFunction;
/*     */ 
/*     */ public class MenuTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  26 */   protected String style = "easyui";
/*     */   protected List<TSFunction> parentFun;
/*     */   protected List<TSFunction> childFun;
/*     */   protected Map<Integer, List<TSFunction>> menuFun;
/*     */ 
/*     */   public void setParentFun(List<TSFunction> parentFun)
/*     */   {
/*  33 */     this.parentFun = parentFun;
/*     */   }
/*     */ 
/*     */   public void setChildFun(List<TSFunction> childFun) {
/*  37 */     this.childFun = childFun;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException {
/*  41 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*  45 */     JspWriter out = null;
/*     */     try
/*     */     {
/*  48 */       out = this.pageContext.getOut();
/*  49 */       out.print(end().toString());
/*  50 */       out.flush();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  63 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  66 */         out.clearBuffer(); } catch (Exception localException) {  } } finally { try { out.clearBuffer();
/*     */       } catch (Exception localException1)
/*     */       {
/*     */       } }
/*  70 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/*  74 */     StringBuffer sb = new StringBuffer();
/*     */ 
/*  76 */     if (this.style.equals("easyui")) {
/*  77 */       sb.append("<ul id=\"nav\" class=\"easyui-tree tree-lines\" fit=\"true\" border=\"false\">");
/*  78 */       sb.append(ListtoMenu.getEasyuiMultistageTree(this.menuFun, this.style));
/*  79 */       sb.append("</ul>");
/*     */     }
/*  81 */     if (this.style.equals("shortcut"))
/*     */     {
/*  85 */       sb.append("<div id=\"nav\" style=\"display:block;\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">");
/*     */ 
/*  87 */       sb.append(ListtoMenu.getEasyuiMultistageTree(this.menuFun, this.style));
/*  88 */       sb.append("</div>");
/*     */     }
/*     */ 
/*  91 */     if (this.style.equals("bootstrap"))
/*     */     {
/*  93 */       sb.append(ListtoMenu.getBootMenu(this.parentFun, this.childFun));
/*     */     }
/*  95 */     if (this.style.equals("json"))
/*     */     {
/*  97 */       sb.append("<script type=\"text/javascript\">");
/*  98 */       sb.append("var _menus=" + ListtoMenu.getMenu(this.parentFun, this.childFun));
/*  99 */       sb.append("</script>");
/*     */     }
/* 101 */     if (this.style.equals("june_bootstrap"))
/*     */     {
/* 103 */       sb.append(ListtoMenu.getBootstrapMenu(this.menuFun));
/*     */     }
/* 105 */     if (this.style.equals("ace"))
/*     */     {
/* 107 */       sb.append(ListtoMenu.getAceMultistageTree(this.menuFun));
/*     */     }
/* 109 */     if (this.style.equals("diy"))
/*     */     {
/* 111 */       sb.append(ListtoMenu.getDIYMultistageTree(this.menuFun));
/*     */     }
/* 113 */     if (this.style.equals("hplus")) {
/* 114 */       sb.append(ListtoMenu.getHplusMultistageTree(this.menuFun));
/*     */     }
/* 116 */     return sb;
/*     */   }
/*     */   public void setStyle(String style) {
/* 119 */     this.style = style;
/*     */   }
/*     */ 
/*     */   public void setMenuFun(Map<Integer, List<TSFunction>> menuFun) {
/* 123 */     this.menuFun = menuFun;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.MenuTag
 * JD-Core Version:    0.6.2
 */