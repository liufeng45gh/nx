/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.MutiLangUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.UUIDGenerator;
/*     */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*     */ 
/*     */ public class ChooseTag extends TagSupport
/*     */ {
/*     */   protected String hiddenName;
/*     */   protected String textname;
/*     */   protected String icon;
/*     */   protected String title;
/*     */   protected String url;
/*     */   protected String top;
/*     */   protected String left;
/*     */   protected String width;
/*     */   protected String height;
/*     */   protected String name;
/*     */   protected String hiddenid;
/*  34 */   protected Boolean isclear = Boolean.valueOf(false);
/*     */   protected String fun;
/*     */   protected String inputTextname;
/*     */   protected String langArg;
/*  38 */   protected Boolean isInit = Boolean.valueOf(false);
/*     */ 
/*     */   public int doStartTag() throws JspTagException {
/*  41 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*  45 */     JspWriter out = null;
/*     */     try {
/*  47 */       this.title = MutiLangUtil.doMutiLang(this.title, this.langArg);
/*  48 */       out = this.pageContext.getOut();
/*  49 */       out.print(end().toString());
/*  50 */       out.flush();
/*     */     } catch (IOException e) {
/*  52 */       e.printStackTrace();
/*     */     }
/*  54 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/*  58 */     String confirm = MutiLangUtil.getMutiLangInstance().getLang("common.confirm");
/*  59 */     String cancel = MutiLangUtil.getMutiLangInstance().getLang("common.cancel");
/*  60 */     String methodname = UUIDGenerator.generate().replaceAll("-", "");
/*  61 */     StringBuffer sb = new StringBuffer();
/*  62 */     sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + this.icon + "\" onClick=\"choose_" + methodname + StringUtil.replace("()\">{0}</a>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.select", this.langArg)));
/*  63 */     if ((this.isclear.booleanValue()) && (StringUtil.isNotEmpty(this.textname))) {
/*  64 */       sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-redo\" onClick=\"clearAll_" + methodname + StringUtil.replace("();\">{0}</a>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.clear", this.langArg)));
/*     */     }
/*  66 */     sb.append("<script type=\"text/javascript\">");
/*  67 */     sb.append("var windowapi = frameElement.api, W = windowapi.opener;");
/*  68 */     sb.append("function choose_" + methodname + "(){");
/*     */ 
/*  70 */     sb.append("var url = ").append("'").append(this.url).append("';");
/*  71 */     if (this.isInit.booleanValue()) {
/*  72 */       sb.append("var initValue = ").append("$('#" + this.hiddenName + "').val();");
/*  73 */       sb.append("url += ").append("'&ids='+initValue;");
/*     */     }
/*     */ 
/*  76 */     sb.append("if(typeof(windowapi) == 'undefined'){");
/*  77 */     sb.append("$.dialog({");
/*  78 */     sb.append("content: 'url:'+url,");
/*  79 */     sb.append("zIndex: 2100,");
/*  80 */     if (this.title != null) {
/*  81 */       sb.append("title: '" + this.title + "',");
/*     */     }
/*  83 */     sb.append("lock : true,");
/*  84 */     if (this.width != null)
/*  85 */       sb.append("width :'" + this.width + "',");
/*     */     else {
/*  87 */       sb.append("width :400,");
/*     */     }
/*  89 */     if (this.height != null)
/*  90 */       sb.append("height :'" + this.height + "',");
/*     */     else {
/*  92 */       sb.append("height :350,");
/*     */     }
/*  94 */     if (this.left != null)
/*  95 */       sb.append("left :'" + this.left + "',");
/*     */     else {
/*  97 */       sb.append("left :'85%',");
/*     */     }
/*  99 */     if (this.top != null)
/* 100 */       sb.append("top :'" + this.top + "',");
/*     */     else {
/* 102 */       sb.append("top :'65%',");
/*     */     }
/* 104 */     sb.append("opacity : 0.4,");
/* 105 */     sb.append("button : [ {");
/* 106 */     sb.append(StringUtil.replace("name : '{0}',", "{0}", confirm));
/* 107 */     sb.append("callback : clickcallback_" + methodname + ",");
/* 108 */     sb.append("focus : true");
/* 109 */     sb.append("}, {");
/* 110 */     sb.append(StringUtil.replace("name : '{0}',", "{0}", cancel));
/* 111 */     sb.append("callback : function() {");
/* 112 */     sb.append("}");
/* 113 */     sb.append("} ]");
/* 114 */     sb.append("});");
/* 115 */     sb.append("}else{");
/* 116 */     sb.append("$.dialog({");
/* 117 */     sb.append("content: 'url:'+url,");
/* 118 */     sb.append("zIndex: 2100,");
/* 119 */     if (this.title != null) {
/* 120 */       sb.append("title: '" + this.title + "',");
/*     */     }
/* 122 */     sb.append("lock : true,");
/* 123 */     sb.append("parent:windowapi,");
/* 124 */     if (this.width != null)
/* 125 */       sb.append("width :'" + this.width + "',");
/*     */     else {
/* 127 */       sb.append("width :400,");
/*     */     }
/* 129 */     if (this.height != null)
/* 130 */       sb.append("height :'" + this.height + "',");
/*     */     else {
/* 132 */       sb.append("height :350,");
/*     */     }
/* 134 */     if (this.left != null)
/* 135 */       sb.append("left :'" + this.left + "',");
/*     */     else {
/* 137 */       sb.append("left :'85%',");
/*     */     }
/* 139 */     if (this.top != null)
/* 140 */       sb.append("top :'" + this.top + "',");
/*     */     else {
/* 142 */       sb.append("top :'65%',");
/*     */     }
/* 144 */     sb.append("opacity : 0.4,");
/* 145 */     sb.append("button : [ {");
/* 146 */     sb.append(StringUtil.replace("name : '{0}',", "{0}", confirm));
/* 147 */     sb.append("callback : clickcallback_" + methodname + ",");
/* 148 */     sb.append("focus : true");
/* 149 */     sb.append("}, {");
/* 150 */     sb.append(StringUtil.replace("name : '{0}',", "{0}", cancel));
/* 151 */     sb.append("callback : function() {");
/* 152 */     sb.append("}");
/* 153 */     sb.append("} ]");
/* 154 */     sb.append("});");
/* 155 */     sb.append("}");
/* 156 */     sb.append("}");
/* 157 */     clearAll(sb, methodname);
/* 158 */     callback(sb, methodname);
/* 159 */     sb.append("</script>");
/* 160 */     return sb;
/*     */   }
/*     */ 
/*     */   private void clearAll(StringBuffer sb, String methodname)
/*     */   {
/* 167 */     String[] textnames = null;
/* 168 */     String[] inputTextnames = null;
/*     */ 
/* 170 */     if (!StringUtil.isEmpty(this.textname)) {
/* 171 */       textnames = this.textname.split(",");
/*     */     }
/*     */ 
/* 174 */     if (StringUtil.isNotEmpty(this.inputTextname))
/* 175 */       inputTextnames = this.inputTextname.split(",");
/*     */     else {
/* 177 */       inputTextnames = textnames;
/*     */     }
/* 179 */     if ((this.isclear.booleanValue()) && (StringUtil.isNotEmpty(this.textname))) {
/* 180 */       sb.append("function clearAll_" + methodname + "(){");
/* 181 */       for (int i = 0; i < textnames.length; i++) {
/* 182 */         inputTextnames[i] = inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
/* 183 */         sb.append("if($('#" + inputTextnames[i] + "').length>=1){");
/* 184 */         sb.append("$('#" + inputTextnames[i] + "').val('');");
/* 185 */         sb.append("$('#" + inputTextnames[i] + "').blur();");
/* 186 */         sb.append("}");
/* 187 */         sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
/* 188 */         sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val('');");
/* 189 */         sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
/* 190 */         sb.append("}");
/*     */       }
/* 192 */       sb.append("$('#" + this.hiddenName + "').val(\"\");");
/* 193 */       sb.append("}");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void callback(StringBuffer sb, String methodname)
/*     */   {
/* 201 */     sb.append("function clickcallback_" + methodname + "(){");
/* 202 */     sb.append("iframe = this.iframe.contentWindow;");
/* 203 */     String[] textnames = null;
/* 204 */     String[] inputTextnames = null;
/* 205 */     if (StringUtil.isNotEmpty(this.textname))
/*     */     {
/* 207 */       textnames = this.textname.split(",");
/* 208 */       if (StringUtil.isNotEmpty(this.inputTextname))
/* 209 */         inputTextnames = this.inputTextname.split(",");
/*     */       else {
/* 211 */         inputTextnames = textnames;
/*     */       }
/* 213 */       for (int i = 0; i < textnames.length; i++) {
/* 214 */         inputTextnames[i] = inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
/* 215 */         sb.append("var " + textnames[i] + "=iframe.get" + this.name + "Selections('" + textnames[i] + "');\t");
/* 216 */         sb.append("if($('#" + inputTextnames[i] + "').length>=1){");
/* 217 */         sb.append("$('#" + inputTextnames[i] + "').val(" + textnames[i] + ");");
/* 218 */         sb.append("$('#" + inputTextnames[i] + "').blur();");
/* 219 */         sb.append("}");
/* 220 */         sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
/* 221 */         sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val(" + textnames[i] + ");");
/* 222 */         sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
/* 223 */         sb.append("}");
/*     */       }
/*     */     }
/* 226 */     if (StringUtil.isNotEmpty(this.hiddenName)) {
/* 227 */       sb.append("var id =iframe.get" + this.name + "Selections('" + this.hiddenid + "');");
/* 228 */       sb.append("if (id!== undefined &&id!=\"\"){");
/* 229 */       sb.append("$('#" + this.hiddenName + "').val(id);");
/* 230 */       sb.append("}");
/*     */     }
/* 232 */     if (StringUtil.isNotEmpty(this.fun))
/*     */     {
/* 234 */       sb.append(this.fun + "();");
/*     */     }
/* 236 */     sb.append("}");
/*     */   }
/*     */ 
/*     */   public void setHiddenName(String hiddenName)
/*     */   {
/* 241 */     this.hiddenName = hiddenName;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 245 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public void setIcon(String icon) {
/* 249 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   public void setTextname(String textname) {
/* 253 */     this.textname = textname;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 257 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 261 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public void setTop(String top) {
/* 265 */     this.top = top;
/*     */   }
/*     */ 
/*     */   public void setLeft(String left) {
/* 269 */     this.left = left;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/* 273 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public void setHeight(String height) {
/* 277 */     this.height = height;
/*     */   }
/*     */ 
/*     */   public void setIsclear(Boolean isclear) {
/* 281 */     this.isclear = isclear;
/*     */   }
/*     */ 
/*     */   public void setHiddenid(String hiddenid) {
/* 285 */     this.hiddenid = hiddenid;
/*     */   }
/*     */   public void setFun(String fun) {
/* 288 */     this.fun = fun;
/*     */   }
/*     */ 
/*     */   public String getInputTextname() {
/* 292 */     return this.inputTextname;
/*     */   }
/*     */ 
/*     */   public void setInputTextname(String inputTextname) {
/* 296 */     this.inputTextname = inputTextname;
/*     */   }
/*     */ 
/*     */   public String getLangArg() {
/* 300 */     return this.langArg;
/*     */   }
/*     */ 
/*     */   public void setLangArg(String langArg) {
/* 304 */     this.langArg = langArg;
/*     */   }
/*     */ 
/*     */   public void setIsInit(Boolean isInit) {
/* 308 */     this.isInit = isInit;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.ChooseTag
 * JD-Core Version:    0.6.2
 */