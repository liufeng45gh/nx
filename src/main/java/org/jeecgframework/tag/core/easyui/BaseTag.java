/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import jodd.util.StringUtil;
/*     */ import org.jeecgframework.core.enums.SysThemesEnum;
/*     */ import org.jeecgframework.core.util.SysThemesUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ 
/*     */ public class BaseTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  24 */   protected String type = "default";
/*     */   protected String cssTheme;
/*     */ 
/*     */   public String getCssTheme()
/*     */   {
/*  29 */     return this.cssTheme;
/*     */   }
/*     */ 
/*     */   public void setCssTheme(String cssTheme)
/*     */   {
/*  34 */     this.cssTheme = cssTheme;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/*  39 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspException
/*     */   {
/*  44 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspException
/*     */   {
/*  49 */     JspWriter out = null;
/*  50 */     StringBuffer sb = new StringBuffer();
/*  51 */     String[] types = this.type.split(",");
/*     */     try {
/*  53 */       out = this.pageContext.getOut();
/*     */ 
/*  71 */       SysThemesEnum sysThemesEnum = null;
/*  72 */       if ((StringUtil.isEmpty(this.cssTheme)) || ("null".equals(this.cssTheme)))
/*  73 */         sysThemesEnum = SysThemesUtil.getSysTheme((HttpServletRequest)this.pageContext.getRequest());
/*     */       else {
/*  75 */         sysThemesEnum = SysThemesEnum.toEnum(this.cssTheme);
/*     */       }
/*     */ 
/*  79 */       String lang = (String)((HttpServletRequest)this.pageContext.getRequest()).getSession().getAttribute("lang");
/*  80 */       String langjs = StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/mutiLang/{0}.js\"></script>", "{0}", lang);
/*  81 */       sb.append(langjs);
/*     */ 
/*  83 */       if (oConvertUtils.isIn("jquery-webos", types)) {
/*  84 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/sliding/js/jquery-1.7.1.min.js\"></script>");
/*  85 */       } else if (oConvertUtils.isIn("jquery", types)) {
/*  86 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>");
/*     */ 
/*  88 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery.cookie.js\" ></script>");
/*  89 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-plugs/storage/jquery.storageapi.min.js\" ></script>");
/*     */       }
/*     */ 
/*  93 */       if (oConvertUtils.isIn("ckeditor", types)) {
/*  94 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/ckeditor/ckeditor.js\"></script>");
/*  95 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/ckeditorTool.js\"></script>");
/*     */       }
/*  97 */       if (oConvertUtils.isIn("ckfinder", types)) {
/*  98 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/ckfinder/ckfinder.js\"></script>");
/*  99 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/ckfinderTool.js\"></script>");
/*     */       }
/* 101 */       if (oConvertUtils.isIn("easyui", types)) {
/* 102 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
/*     */ 
/* 105 */         sb.append(SysThemesUtil.getEasyUiTheme(sysThemesEnum));
/* 106 */         sb.append(SysThemesUtil.getEasyUiMainTheme(sysThemesEnum));
/*     */ 
/* 108 */         sb.append(SysThemesUtil.getEasyUiIconTheme(sysThemesEnum));
/*     */ 
/* 110 */         sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css\">");
/* 111 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
/* 112 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js\"></script>");
/* 113 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
/* 114 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/extends/datagrid-scrollview.js\"></script>");
/*     */       }
/* 116 */       if (oConvertUtils.isIn("DatePicker", types)) {
/* 117 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js\"></script>");
/*     */       }
/* 119 */       if (oConvertUtils.isIn("jqueryui", types)) {
/* 120 */         sb.append("<link rel=\"stylesheet\" href=\"plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css\" type=\"text/css\"></link>");
/* 121 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/jquery-ui-1.9.2.custom.min.js\"></script>");
/*     */       }
/* 123 */       if (oConvertUtils.isIn("jqueryui-sortable", types)) {
/* 124 */         sb.append("<link rel=\"stylesheet\" href=\"plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css\" type=\"text/css\"></link>");
/* 125 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/ui/jquery.ui.core.js\"></script>");
/* 126 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/ui/jquery.ui.widget.js\"></script>");
/* 127 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/ui/jquery.ui.mouse.js\"></script>");
/* 128 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/ui/jquery.ui.sortable.js\"></script>");
/*     */       }
/* 130 */       if (oConvertUtils.isIn("prohibit", types))
/* 131 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/prohibitutil.js\"></script>");
/* 132 */       if (oConvertUtils.isIn("designer", types)) {
/* 133 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/designer/easyui/jquery-1.7.2.min.js\"></script>");
/* 134 */         sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/designer/easyui/easyui.css\" type=\"text/css\"></link>");
/* 135 */         sb.append("<link rel=\"stylesheet\" href=\"plug-in/designer/easyui/icon.css\" type=\"text/css\"></link>");
/* 136 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/designer/easyui/jquery.easyui.min.1.3.0.js\"></script>");
/*     */ 
/* 139 */         sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/designer/easyui/locale/{0}.js\"></script>", "{0}", lang));
/*     */ 
/* 141 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
/* 142 */         sb.append("<script type='text/javascript' src='plug-in/jquery/jquery-autocomplete/lib/jquery.bgiframe.min.js'></script>");
/* 143 */         sb.append("<script type='text/javascript' src='plug-in/jquery/jquery-autocomplete/lib/jquery.ajaxQueue.js'></script>");
/* 144 */         sb.append("<script type='text/javascript' src='plug-in/jquery/jquery-autocomplete/jquery.autocomplete.min.js'></script>");
/* 145 */         sb.append("<link href=\"plug-in/designer/designer.css\" type=\"text/css\" rel=\"stylesheet\" />");
/* 146 */         sb.append("<script src=\"plug-in/designer/draw2d/wz_jsgraphics.js\"></script>");
/* 147 */         sb.append("<script src='plug-in/designer/draw2d/mootools.js'></script>");
/* 148 */         sb.append("<script src='plug-in/designer/draw2d/moocanvas.js'></script>");
/* 149 */         sb.append("<script src='plug-in/designer/draw2d/draw2d.js'></script>");
/* 150 */         sb.append("<script src=\"plug-in/designer/MyCanvas.js\"></script>");
/* 151 */         sb.append("<script src=\"plug-in/designer/ResizeImage.js\"></script>");
/* 152 */         sb.append("<script src=\"plug-in/designer/event/Start.js\"></script>");
/* 153 */         sb.append("<script src=\"plug-in/designer/event/End.js\"></script>");
/* 154 */         sb.append("<script src=\"plug-in/designer/connection/MyInputPort.js\"></script>");
/* 155 */         sb.append("<script src=\"plug-in/designer/connection/MyOutputPort.js\"></script>");
/* 156 */         sb.append("<script src=\"plug-in/designer/connection/DecoratedConnection.js\"></script>");
/* 157 */         sb.append("<script src=\"plug-in/designer/task/Task.js\"></script>");
/* 158 */         sb.append("<script src=\"plug-in/designer/task/UserTask.js\"></script>");
/* 159 */         sb.append("<script src=\"plug-in/designer/task/ManualTask.js\"></script>");
/* 160 */         sb.append("<script src=\"plug-in/designer/task/ServiceTask.js\"></script>");
/* 161 */         sb.append("<script src=\"plug-in/designer/gateway/ExclusiveGateway.js\"></script>");
/* 162 */         sb.append("<script src=\"plug-in/designer/gateway/ParallelGateway.js\"></script>");
/* 163 */         sb.append("<script src=\"plug-in/designer/boundaryevent/TimerBoundary.js\"></script>");
/* 164 */         sb.append("<script src=\"plug-in/designer/boundaryevent/ErrorBoundary.js\"></script>");
/* 165 */         sb.append("<script src=\"plug-in/designer/subprocess/CallActivity.js\"></script>");
/* 166 */         sb.append("<script src=\"plug-in/designer/task/ScriptTask.js\"></script>");
/* 167 */         sb.append("<script src=\"plug-in/designer/task/MailTask.js\"></script>");
/* 168 */         sb.append("<script src=\"plug-in/designer/task/ReceiveTask.js\"></script>");
/* 169 */         sb.append("<script src=\"plug-in/designer/task/BusinessRuleTask.js\"></script>");
/* 170 */         sb.append("<script src=\"plug-in/designer/designer.js\"></script>");
/* 171 */         sb.append("<script src=\"plug-in/designer/mydesigner.js\"></script>");
/*     */       }
/*     */ 
/* 174 */       if (oConvertUtils.isIn("tools", types))
/*     */       {
/* 177 */         sb.append(SysThemesUtil.getCommonTheme(sysThemesEnum));
/*     */ 
/* 180 */         sb.append(SysThemesUtil.getLhgdialogTheme(sysThemesEnum));
/* 181 */         sb.append(SysThemesUtil.getBootstrapTabTheme(sysThemesEnum));
/* 182 */         sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/tools/curdtools_{0}.js\"></script>", "{0}", lang));
/*     */ 
/* 184 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js\"></script>");
/* 185 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-plugs/hftable/jquery-hftable.js\"></script>");
/* 186 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/json2.js\" ></script>");
/*     */       }
/* 188 */       if (oConvertUtils.isIn("toptip", types)) {
/* 189 */         sb.append("<link rel=\"stylesheet\" href=\"plug-in/toptip/css/css.css\" type=\"text/css\"></link>");
/* 190 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/toptip/manhua_msgTips.js\"></script>");
/*     */       }
/* 192 */       if (oConvertUtils.isIn("autocomplete", types)) {
/* 193 */         sb.append("<link rel=\"stylesheet\" href=\"plug-in/jquery/jquery-autocomplete/jquery.autocomplete.css\" type=\"text/css\"></link>");
/* 194 */         sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-autocomplete/jquery.autocomplete.min.js\"></script>");
/*     */       }
/* 196 */       if (oConvertUtils.isIn("jeasyuiextensions", types)) {
/* 197 */         sb.append("<script src=\"plug-in/jquery-extensions/release/jquery.jdirk.min.js\" type=\"text/javascript\"></script>");
/* 198 */         sb.append("<link href=\"plug-in/jquery-extensions/icons/icon-all.css\" rel=\"stylesheet\" type=\"text/css\" />");
/* 199 */         sb.append("<link href=\"plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.css\" rel=\"stylesheet\" type=\"text/css\" />");
/* 200 */         sb.append("<script src=\"plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.js\" type=\"text/javascript\"></script>");
/* 201 */         sb.append("<script src=\"plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.linkbutton.js\" type=\"text/javascript\"></script>");
/* 202 */         sb.append("<script src=\"plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.menu.js\" type=\"text/javascript\"></script>");
/* 203 */         sb.append("<script src=\"plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.panel.js\" type=\"text/javascript\"></script>");
/* 204 */         sb.append("<script src=\"plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.window.js\" type=\"text/javascript\"></script>");
/* 205 */         sb.append("<script src=\"plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.dialog.js\" type=\"text/javascript\"></script>");
/* 206 */         sb.append("<script src=\"plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.datagrid.js\" type=\"text/javascript\"></script>");
/*     */       }
/* 208 */       out.print(sb.toString());
/* 209 */       out.flush();
/*     */     } catch (IOException e) {
/* 211 */       e.printStackTrace();
/*     */ 
/* 213 */       if (out != null)
/*     */         try
/*     */         {
/* 216 */           out.clearBuffer();
/* 217 */           sb.setLength(0);
/*     */         }
/*     */         catch (Exception ex) {
/* 220 */           ex.printStackTrace();
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/* 213 */       if (out != null) {
/*     */         try
/*     */         {
/* 216 */           out.clearBuffer();
/* 217 */           sb.setLength(0);
/*     */         }
/*     */         catch (Exception e) {
/* 220 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 225 */     return 6;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.BaseTag
 * JD-Core Version:    0.6.2
 */