/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.tag.vo.easyui.Tab;
/*     */ 
/*     */ public class TabsTag extends TagSupport
/*     */ {
/*     */   private String id;
/*     */   private String width;
/*     */   private String heigth;
/*     */   private boolean plain;
/*  28 */   private boolean fit = true;
/*     */   private boolean border;
/*     */   private String scrollIncrement;
/*     */   private String scrollDuration;
/*     */   private boolean tools;
/*  33 */   private boolean tabs = true;
/*  34 */   private boolean iframe = true;
/*  35 */   private String tabPosition = "top";
/*     */ 
/*  89 */   private List<Tab> tabList = new ArrayList();
/*     */ 
/*     */   public void setIframe(boolean iframe)
/*     */   {
/*  38 */     this.iframe = iframe;
/*     */   }
/*     */ 
/*     */   public void setTabs(boolean tabs) {
/*  42 */     this.tabs = tabs;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  46 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/*  50 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public void setHeigth(String heigth) {
/*  54 */     this.heigth = heigth;
/*     */   }
/*     */ 
/*     */   public void setPlain(boolean plain) {
/*  58 */     this.plain = plain;
/*     */   }
/*     */ 
/*     */   public void setFit(boolean fit) {
/*  62 */     this.fit = fit;
/*     */   }
/*     */ 
/*     */   public void setBorder(boolean border) {
/*  66 */     this.border = border;
/*     */   }
/*     */ 
/*     */   public void setScrollIncrement(String scrollIncrement) {
/*  70 */     this.scrollIncrement = scrollIncrement;
/*     */   }
/*     */ 
/*     */   public void setScrollDuration(String scrollDuration) {
/*  74 */     this.scrollDuration = scrollDuration;
/*     */   }
/*     */ 
/*     */   public void setTools(boolean tools) {
/*  78 */     this.tools = tools;
/*     */   }
/*     */ 
/*     */   public void setTabPosition(String tabPosition) {
/*  82 */     this.tabPosition = tabPosition;
/*     */   }
/*     */ 
/*     */   public void setTabList(List<Tab> tabList) {
/*  86 */     this.tabList = tabList;
/*     */   }
/*     */ 
/*     */   public int doStartTag()
/*     */     throws JspTagException
/*     */   {
/*  92 */     this.tabList.clear();
/*  93 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*  97 */     JspWriter out = null;
/*     */     try {
/*  99 */       out = this.pageContext.getOut();
/* 100 */       out.print(end().toString());
/* 101 */       out.flush();
/*     */     } catch (IOException e) {
/* 103 */       e.printStackTrace();
/*     */       try
/*     */       {
/* 106 */         out.clear();
/* 107 */         out.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 106 */         out.clear();
/* 107 */         out.close();
/*     */       } catch (Exception localException1) {
/*     */       }
/*     */     }
/* 111 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/* 115 */     StringBuffer sb = new StringBuffer();
/* 116 */     if (this.iframe) {
/* 117 */       sb.append("<script type=\"text/javascript\">");
/* 118 */       sb.append("$(function(){");
/* 119 */       if (this.tabList.size() > 0) {
/* 120 */         for (Tab tab : this.tabList) {
/* 121 */           sb.append("add" + this.id + "('" + tab.getTitle() + "','" + tab.getHref() + "','" + tab.getId() + "','" + tab.getIcon() + "','" + tab.isClosable() + "');");
/*     */         }
/*     */       }
/* 124 */       sb.append("function add" + this.id + "(title,url,id,icon,closable) {");
/* 125 */       sb.append("$('#" + this.id + "').tabs('add',{");
/* 126 */       sb.append("id:id,");
/* 127 */       sb.append("title:title,");
/* 128 */       if (this.iframe)
/* 129 */         sb.append("content:createFrame" + this.id + "(id),");
/*     */       else {
/* 131 */         sb.append("href:url,");
/*     */       }
/* 133 */       sb.append("closable:closable=(closable =='false')?false : true,");
/* 134 */       sb.append("icon:icon");
/* 135 */       sb.append("});");
/* 136 */       sb.append("}");
/* 137 */       sb.append("$('#" + this.id + "').tabs(");
/* 138 */       sb.append("{");
/* 139 */       sb.append("onSelect : function(title) {");
/* 140 */       sb.append("var p = $(this).tabs('getTab', title);");
/* 141 */       if (this.tabList.size() > 0) {
/* 142 */         for (Tab tab : this.tabList) {
/* 143 */           sb.append("if (title == '" + tab.getTitle() + "') {");
/* 144 */           sb.append("p.find('iframe').attr('src',");
/* 145 */           sb.append("'" + tab.getHref() + "');}");
/*     */         }
/*     */       }
/* 148 */       sb.append("}");
/* 149 */       sb.append("});");
/*     */ 
/* 151 */       sb.append("function createFrame" + this.id + "(id)");
/* 152 */       sb.append("{");
/*     */ 
/* 154 */       sb.append("var s = '<iframe id=\"+id+\" scrolling=\"no\" frameborder=\"0\"  src=\"about:jeecg\" width=\"" + oConvertUtils.getString(this.width, "100%") + "\" height=\"" + oConvertUtils.getString(this.heigth, "99.5%") + "\"></iframe>';");
/*     */ 
/* 156 */       sb.append("return s;");
/* 157 */       sb.append("}");
/* 158 */       sb.append("});");
/* 159 */       sb.append("</script>");
/*     */     }
/* 161 */     if (this.tabs)
/*     */     {
/* 163 */       sb.append("<div id=\"" + this.id + "\" tabPosition=\"" + this.tabPosition + "\" border=flase style=\"margin:0px;padding:0px;overflow:hidden;width:" + oConvertUtils.getString(this.width, "auto") + ";\" class=\"easyui-tabs\" fit=\"" + this.fit + "\">");
/* 164 */       if (!this.iframe) {
/* 165 */         for (Tab tab : this.tabList) {
/* 166 */           if (tab.getHref() != null) {
/* 167 */             sb.append("<div title=\"" + tab.getTitle() + "\" href=\"" + tab.getHref() + "\" style=\"margin:0px;padding:0px;overflow:hidden;\"></div>");
/*     */           } else {
/* 169 */             sb.append("<div title=\"" + tab.getTitle() + "\"  style=\"margin:0px;padding:0px;overflow:hidden;\">");
/*     */ 
/* 171 */             sb.append("<iframe id=\"" + tab.getId() + "\" scrolling=\"no\" frameborder=\"0\"  src=\"" + tab.getIframe() + "\" width=\"" + oConvertUtils.getString(tab.getWidth(), "100%") + "\" height=\"" + oConvertUtils.getString(tab.getHeigth(), "99.5%") + "\"></iframe>");
/*     */ 
/* 173 */             sb.append("</div>");
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 178 */       sb.append("</div>");
/*     */     }
/*     */ 
/* 181 */     return sb;
/*     */   }
/*     */ 
/*     */   public void setTab(String id, String title, String iframe, String href, String iconCls, boolean cache, String content, String width, String heigth, boolean closable) {
/* 185 */     Tab tab = new Tab();
/* 186 */     tab.setId(id);
/* 187 */     tab.setTitle(title);
/* 188 */     tab.setHref(href);
/* 189 */     tab.setCache(cache);
/* 190 */     tab.setIframe(iframe);
/* 191 */     tab.setContent(content);
/* 192 */     tab.setHeigth(heigth);
/* 193 */     tab.setIcon(iconCls);
/* 194 */     tab.setWidth(width);
/* 195 */     tab.setClosable(closable);
/* 196 */     this.tabList.add(tab);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.TabsTag
 * JD-Core Version:    0.6.2
 */