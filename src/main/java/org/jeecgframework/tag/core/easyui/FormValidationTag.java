/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.enums.SysThemesEnum;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.SysThemesUtil;
/*     */ 
/*     */ public class FormValidationTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 8360534826228271024L;
/*  21 */   protected String formid = "formobj";
/*  22 */   protected Boolean refresh = Boolean.valueOf(true);
/*     */   protected String callback;
/*     */   protected String beforeSubmit;
/*  25 */   protected String btnsub = "btn_sub";
/*  26 */   protected String btnreset = "btn_reset";
/*  27 */   protected String layout = "div";
/*     */   protected String usePlugin;
/*  29 */   protected boolean dialog = true;
/*     */   protected String action;
/*     */   protected String tabtitle;
/*  32 */   protected String tiptype = "4";
/*     */   protected String styleClass;
/*     */   protected String cssTheme;
/*     */ 
/*     */   public String getCssTheme()
/*     */   {
/*  39 */     return this.cssTheme;
/*     */   }
/*     */ 
/*     */   public void setCssTheme(String cssTheme) {
/*  43 */     this.cssTheme = cssTheme;
/*     */   }
/*     */ 
/*     */   public String getStyleClass() {
/*  47 */     return this.styleClass;
/*     */   }
/*     */ 
/*     */   public void setStyleClass(String styleClass) {
/*  51 */     this.styleClass = styleClass;
/*     */   }
/*     */ 
/*     */   public void setTabtitle(String tabtitle) {
/*  55 */     this.tabtitle = tabtitle;
/*     */   }
/*     */ 
/*     */   public void setDialog(boolean dialog) {
/*  59 */     this.dialog = dialog;
/*     */   }
/*     */ 
/*     */   public void setBtnsub(String btnsub) {
/*  63 */     this.btnsub = btnsub;
/*     */   }
/*     */ 
/*     */   public void setRefresh(Boolean refresh) {
/*  67 */     this.refresh = refresh;
/*     */   }
/*     */ 
/*     */   public void setBtnreset(String btnreset) {
/*  71 */     this.btnreset = btnreset;
/*     */   }
/*     */ 
/*     */   public void setFormid(String formid) {
/*  75 */     this.formid = formid;
/*     */   }
/*     */ 
/*     */   public void setAction(String action) {
/*  79 */     this.action = action;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspException
/*     */   {
/*  84 */     JspWriter out = null;
/*  85 */     StringBuffer sb = new StringBuffer();
/*     */     try {
/*  87 */       out = this.pageContext.getOut();
/*     */ 
/* 101 */       if ("div".equals(this.layout)) {
/* 102 */         sb.append("<div id=\"content\">");
/* 103 */         sb.append("<div id=\"wrapper\">");
/* 104 */         sb.append("<div id=\"steps\">");
/*     */       }
/* 106 */       sb.append("<form id=\"" + this.formid + "\" ");
/*     */ 
/* 108 */       if (getStyleClass() != null) {
/* 109 */         sb.append("class=\"" + getStyleClass() + "\" ");
/*     */       }
/*     */ 
/* 112 */       sb.append(" action=\"" + this.action + "\" name=\"" + this.formid + "\" method=\"post\">");
/* 113 */       if (("btn_sub".equals(this.btnsub)) && (this.dialog))
/* 114 */         sb.append("<input type=\"hidden\" id=\"" + this.btnsub + "\" class=\"" + this.btnsub + "\"/>");
/* 115 */       out.print(sb.toString());
/* 116 */       out.flush();
/*     */     } catch (IOException e) {
/* 118 */       e.printStackTrace();
/*     */     }
/* 120 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspException
/*     */   {
/* 125 */     StringBuffer sb = new StringBuffer();
/* 126 */     JspWriter out = null;
/*     */     try {
/* 128 */       String lang = (String)((HttpServletRequest)this.pageContext.getRequest()).getSession().getAttribute("lang");
/* 129 */       SysThemesEnum sysThemesEnum = null;
/* 130 */       if ((StringUtil.isEmpty(this.cssTheme)) || ("null".equals(this.cssTheme)))
/* 131 */         sysThemesEnum = SysThemesUtil.getSysTheme((HttpServletRequest)this.pageContext.getRequest());
/*     */       else {
/* 133 */         sysThemesEnum = SysThemesEnum.toEnum(this.cssTheme);
/*     */       }
/* 135 */       out = this.pageContext.getOut();
/* 136 */       if (this.layout.equals("div"))
/*     */       {
/* 144 */         sb.append(SysThemesUtil.getValidformDivfromTheme(sysThemesEnum));
/* 145 */         if (this.tabtitle != null) {
/* 146 */           sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/form.js\"></script>");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 156 */       sb.append(SysThemesUtil.getValidformStyleTheme(sysThemesEnum));
/*     */ 
/* 158 */       sb.append(SysThemesUtil.getValidformTablefrom(sysThemesEnum));
/*     */ 
/* 160 */       sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min_{0}.js\"></script>", "{0}", lang));
/* 161 */       sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype_{0}.js\"></script>", "{0}", lang));
/* 162 */       sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype_{0}.js\"></script>", "{0}", lang));
/*     */ 
/* 164 */       if (this.usePlugin != null) {
/* 165 */         if (this.usePlugin.indexOf("jqtransform") >= 0) {
/* 166 */           sb.append("<SCRIPT type=\"text/javascript\" src=\"plug-in/Validform/plugin/jqtransform/jquery.jqtransform.js\"></SCRIPT>");
/* 167 */           sb.append("<LINK rel=\"stylesheet\" href=\"plug-in/Validform/plugin/jqtransform/jqtransform.css\" type=\"text/css\"></LINK>");
/*     */         }
/* 169 */         if (this.usePlugin.indexOf("password") >= 0) {
/* 170 */           sb.append("<SCRIPT type=\"text/javascript\" src=\"plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></SCRIPT>");
/*     */         }
/*     */       }
/* 173 */       sb.append("<script type=\"text/javascript\">");
/* 174 */       sb.append("$(function(){");
/* 175 */       sb.append("$(\"#" + this.formid + "\").Validform({");
/* 176 */       if ((getTiptype() != null) && (!"".equals(getTiptype())))
/* 177 */         sb.append("tiptype:" + getTiptype() + ",");
/*     */       else {
/* 179 */         sb.append("tiptype:1,");
/*     */       }
/*     */ 
/* 203 */       sb.append("btnSubmit:\"#" + this.btnsub + "\",");
/* 204 */       sb.append("btnReset:\"#" + this.btnreset + "\",");
/* 205 */       sb.append("ajaxPost:true,");
/* 206 */       if (this.beforeSubmit != null) {
/* 207 */         sb.append("beforeSubmit:function(curform){var tag=false;");
/* 208 */         sb.append("return " + this.beforeSubmit);
/* 209 */         if (this.beforeSubmit.indexOf("(") < 0) {
/* 210 */           sb.append("(curform);");
/*     */         }
/* 212 */         sb.append("},");
/*     */       }
/* 214 */       if (this.usePlugin != null) {
/* 215 */         StringBuffer passsb = new StringBuffer();
/* 216 */         if (this.usePlugin.indexOf("password") >= 0) {
/* 217 */           passsb.append("passwordstrength:{");
/* 218 */           passsb.append("minLen:6,");
/* 219 */           passsb.append("maxLen:18,");
/* 220 */           passsb.append("trigger:function(obj,error)");
/* 221 */           passsb.append("{");
/* 222 */           passsb.append("if(error)");
/* 223 */           passsb.append("{");
/* 224 */           passsb.append("obj.parent().next().find(\".Validform_checktip\").show();");
/* 225 */           passsb.append("obj.find(\".passwordStrength\").hide();");
/* 226 */           passsb.append("}");
/* 227 */           passsb.append("else");
/* 228 */           passsb.append("{");
/* 229 */           passsb.append("$(\".passwordStrength\").show();");
/* 230 */           passsb.append("obj.parent().next().find(\".Validform_checktip\").hide();");
/* 231 */           passsb.append("}");
/* 232 */           passsb.append("}");
/* 233 */           passsb.append("}");
/*     */         }
/*     */ 
/* 236 */         sb.append("usePlugin:{");
/* 237 */         if (this.usePlugin.indexOf("password") >= 0) {
/* 238 */           sb.append(passsb);
/*     */         }
/* 240 */         StringBuffer jqsb = new StringBuffer();
/* 241 */         if (this.usePlugin.indexOf("jqtransform") >= 0) {
/* 242 */           if (this.usePlugin.indexOf("password") >= 0) {
/* 243 */             sb.append(",");
/*     */           }
/* 245 */           jqsb.append("jqtransform :{selector:\"select\"}");
/*     */         }
/*     */ 
/* 248 */         if (this.usePlugin.indexOf("jqtransform") >= 0) {
/* 249 */           sb.append(jqsb);
/*     */         }
/* 251 */         sb.append("},");
/*     */       }
/* 253 */       sb.append("callback:function(data){");
/* 254 */       if (this.dialog) {
/* 255 */         if ((this.callback != null) && (this.callback.contains("@Override"))) {
/* 256 */           sb.append(this.callback.replaceAll("@Override", "") + "(data);");
/*     */         } else {
/* 258 */           sb.append("var win = frameElement.api.opener;");
/*     */ 
/* 261 */           sb.append("if(data.success==true){frameElement.api.close();win.tip(data.msg);}else{if(data.responseText==''||data.responseText==undefined){$.messager.alert('错误', data.msg);$.Hidemsg();}else{try{var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息')); $.messager.alert('错误',emsg);$.Hidemsg();}catch(ex){$.messager.alert('错误',data.responseText+\"\");$.Hidemsg();}} return false;}");
/*     */ 
/* 263 */           if (this.refresh.booleanValue()) {
/* 264 */             sb.append("win.reloadTable();");
/*     */           }
/* 266 */           if (StringUtil.isNotEmpty(this.callback)) {
/* 267 */             sb.append("win." + this.callback + "(data);");
/*     */           }
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 273 */         sb.append(this.callback + "(data);");
/*     */       }
/* 275 */       sb.append("}});});</script>");
/* 276 */       sb.append("");
/* 277 */       sb.append("</form>");
/* 278 */       if ("div".equals(this.layout)) {
/* 279 */         sb.append("</div>");
/* 280 */         if (this.tabtitle != null) {
/* 281 */           String[] tabtitles = this.tabtitle.split(",");
/* 282 */           sb.append("<div id=\"navigation\" style=\"display: none;\">");
/* 283 */           sb.append("<ul>");
/* 284 */           for (String string : tabtitles) {
/* 285 */             sb.append("<li>");
/* 286 */             sb.append("<a href=\"#\">" + string + "</a>");
/* 287 */             sb.append("</li>");
/*     */           }
/* 289 */           sb.append("</ul>");
/* 290 */           sb.append("</div>");
/*     */         }
/* 292 */         sb.append("</div></div>");
/*     */       }
/* 294 */       out.print(sb.toString());
/* 295 */       out.flush();
/*     */     } catch (IOException e) {
/* 297 */       e.printStackTrace();
/*     */       try
/*     */       {
/* 301 */         sb.setLength(0);
/*     */ 
/* 303 */         out.clearBuffer();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 301 */         sb.setLength(0);
/*     */ 
/* 303 */         out.clearBuffer();
/*     */       } catch (Exception localException1) {
/*     */       }
/*     */     }
/* 307 */     return 6;
/*     */   }
/*     */ 
/*     */   public void setUsePlugin(String usePlugin) {
/* 311 */     this.usePlugin = usePlugin;
/*     */   }
/*     */ 
/*     */   public void setLayout(String layout) {
/* 315 */     this.layout = layout;
/*     */   }
/*     */ 
/*     */   public void setBeforeSubmit(String beforeSubmit) {
/* 319 */     this.beforeSubmit = beforeSubmit;
/*     */   }
/*     */ 
/*     */   public void setCallback(String callback) {
/* 323 */     this.callback = callback;
/*     */   }
/*     */ 
/*     */   public String getTiptype() {
/* 327 */     return this.tiptype;
/*     */   }
/*     */ 
/*     */   public void setTiptype(String tiptype) {
/* 331 */     this.tiptype = tiptype;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.FormValidationTag
 * JD-Core Version:    0.6.2
 */