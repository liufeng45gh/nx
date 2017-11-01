/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.MutiLangUtil;
/*     */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*     */ 
/*     */ public class UploadTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String id;
/*     */   protected String uploader;
/*     */   protected String name;
/*     */   protected String formData;
/*  25 */   protected String extend = "";
/*  26 */   protected String buttonText = "浏览";
/*  27 */   protected boolean multi = true;
/*  28 */   protected String queueID = "filediv";
/*  29 */   protected boolean dialog = true;
/*     */   protected String callback;
/*  31 */   protected boolean auto = false;
/*     */   protected String onUploadSuccess;
/*  33 */   protected boolean view = false;
/*     */   protected String formId;
/*     */ 
/*     */   public String getFormId()
/*     */   {
/*  38 */     return this.formId;
/*     */   }
/*     */   public void setFormId(String formId) {
/*  41 */     this.formId = formId;
/*     */   }
/*     */ 
/*     */   public void setView(boolean view)
/*     */   {
/*  46 */     this.view = view;
/*     */   }
/*     */   public void setOnUploadSuccess(String onUploadSuccess) {
/*  49 */     this.onUploadSuccess = onUploadSuccess;
/*     */   }
/*     */   public void setAuto(boolean auto) {
/*  52 */     this.auto = auto;
/*     */   }
/*     */   public void setCallback(String callback) {
/*  55 */     this.callback = callback;
/*     */   }
/*     */   public void setDialog(boolean dialog) {
/*  58 */     this.dialog = dialog;
/*     */   }
/*     */   public void setQueueID(String queueID) {
/*  61 */     this.queueID = queueID;
/*     */   }
/*     */   public void setButtonText(String buttonText) {
/*  64 */     this.buttonText = buttonText;
/*     */   }
/*     */   public void setMulti(boolean multi) {
/*  67 */     this.multi = multi;
/*     */   }
/*     */   public void setUploader(String uploader) {
/*  70 */     this.uploader = uploader;
/*     */   }
/*     */   public void setName(String name) {
/*  73 */     this.name = name;
/*     */   }
/*     */   public int doStartTag() throws JspTagException {
/*  76 */     return 6;
/*     */   }
/*     */   public int doEndTag() throws JspTagException {
/*  79 */     JspWriter out = null;
/*     */     try {
/*  81 */       out = this.pageContext.getOut();
/*  82 */       out.print(end().toString());
/*  83 */       out.flush();
/*     */     } catch (IOException e) {
/*  85 */       e.printStackTrace();
/*     */       try
/*     */       {
/*  88 */         out.clear();
/*  89 */         out.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  88 */         out.clear();
/*  89 */         out.close();
/*     */       } catch (Exception localException1) {
/*     */       }
/*     */     }
/*  93 */     return 6;
/*     */   }
/*     */   public StringBuffer end() {
/*  96 */     StringBuffer sb = new StringBuffer();
/*  97 */     if ("pic".equals(this.extend))
/*     */     {
/*  99 */       this.extend = "*.jpg;*,jpeg;*.png;*.gif;*.bmp;*.ico;*.tif";
/*     */     }
/* 101 */     if (this.extend.equals("office"))
/*     */     {
/* 103 */       this.extend = "*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm";
/*     */     }
/* 105 */     sb.append("<link rel=\"stylesheet\" href=\"plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>");
/* 106 */     sb.append("<script type=\"text/javascript\" src=\"plug-in/uploadify/jquery.uploadify-3.1.js\"></script>");
/* 107 */     sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/Map.js\"></script>");
/* 108 */     sb.append("<script type=\"text/javascript\">var flag = false;var fileitem=\"\";var fileKey=\"\";var serverMsg=\"\";var m = new Map();$(function(){$('#" + 
/* 115 */       this.id + "').uploadify({" + 
/* 116 */       "buttonText:'" + MutiLangUtil.getMutiLangInstance().getLang(this.buttonText) + "'," + 
/* 117 */       "auto:" + this.auto + "," + 
/* 118 */       "progressData:'speed'," + 
/* 119 */       "multi:" + this.multi + "," + 
/* 120 */       "height:25," + 
/* 121 */       "overrideEvents:['onDialogClose']," + 
/* 122 */       "fileTypeDesc:'文件格式:'," + 
/* 123 */       "queueID:'" + this.queueID + "'," + 
/* 124 */       "fileTypeExts:'" + this.extend + "'," + 
/* 125 */       "fileSizeLimit:'15MB'," + 
/* 126 */       "swf:'plug-in/uploadify/uploadify.swf',\t" + 
/* 127 */       "uploader:'" + getUploader() + 
/* 128 */       "onUploadStart : function(file) { ");
/* 129 */     if (this.formData != null) {
/* 130 */       String[] paramnames = this.formData.split(",");
/* 131 */       for (int i = 0; i < paramnames.length; i++) {
/* 132 */         String paramname = paramnames[i];
/* 133 */         sb.append("var " + paramname + "=$('#" + paramname + "').val();");
/*     */       }
/* 135 */       sb.append("$('#" + this.id + "').uploadify(\"settings\", \"formData\", {");
/* 136 */       for (int i = 0; i < paramnames.length; i++) {
/* 137 */         String paramname = paramnames[i];
/* 138 */         if (i == paramnames.length - 1)
/* 139 */           sb.append("'" + paramname + "':" + paramname);
/*     */         else {
/* 141 */           sb.append("'" + paramname + "':" + paramname + ",");
/*     */         }
/*     */       }
/* 144 */       sb.append("});");
/*     */     }
/* 146 */     else if (this.formId != null) {
/* 147 */       sb.append(" var o = {};");
/* 148 */       sb.append("    var _array = $('#" + this.formId + "').serializeArray();");
/* 149 */       sb.append("    $.each(_array, function() {");
/* 150 */       sb.append("        if (o[this.name]) {");
/* 151 */       sb.append("            if (!o[this.name].push) {");
/* 152 */       sb.append("                o[this.name] = [ o[this.name] ];");
/* 153 */       sb.append("            }");
/* 154 */       sb.append("            o[this.name].push(this.value || '');");
/* 155 */       sb.append("        } else {");
/* 156 */       sb.append("            o[this.name] = this.value || '';");
/* 157 */       sb.append("        }");
/* 158 */       sb.append("    });");
/* 159 */       sb.append("$('#" + this.id + "').uploadify(\"settings\", \"formData\", o);");
/*     */     }
/*     */ 
/* 162 */     sb.append("} ,onQueueComplete : function(queueData) { ");
/*     */ 
/* 164 */     if (this.dialog)
/*     */     {
/* 166 */       sb.append("var win = frameElement.api.opener;win.reloadTable();win.tip(serverMsg);frameElement.api.close();");
/*     */     }
/* 173 */     else if (this.callback != null) {
/* 174 */       sb.append(this.callback + "();");
/*     */     }
/* 176 */     if (this.view)
/*     */     {
/* 178 */       sb.append("$(\"#viewmsg\").html(m.toString());");
/* 179 */       sb.append("$(\"#fileKey\").val(fileKey);");
/*     */     }
/* 181 */     sb.append("},");
/*     */ 
/* 183 */     sb.append("onUploadSuccess : function(file, data, response) {");
/* 184 */     sb.append("var d=$.parseJSON(data);");
/* 185 */     if (this.view)
/*     */     {
/* 187 */       sb.append("var fileitem=\"<span id='\"+d.attributes.id+\"'><a href='#' onclick=openwindow('文件查看','\"+d.attributes.viewhref+\"','70%','80%') title='查看'>\"+d.attributes.name+\"</a><img border='0' onclick=confuploadify('\"+d.attributes.delurl+\"','\"+d.attributes.id+\"') title='删除' src='plug-in/uploadify/img/uploadify-cancel.png' widht='15' height='15'>&nbsp;&nbsp;</span>\";");
/*     */ 
/* 189 */       sb.append(" m=new Map(); ");
/*     */ 
/* 191 */       sb.append("m.put(d.attributes.id,fileitem);");
/* 192 */       sb.append("fileKey=d.attributes.fileKey;");
/*     */     }
/* 194 */     if (this.onUploadSuccess != null)
/*     */     {
/* 196 */       sb.append(this.onUploadSuccess + "(d,file,response);");
/*     */     }
/* 198 */     sb.append("if(d.success){");
/* 199 */     sb.append("var win = frameElement.api.opener;");
/*     */ 
/* 201 */     sb.append("serverMsg = d.msg;");
/* 202 */     sb.append("}");
/* 203 */     sb.append("},");
/* 204 */     sb.append("onFallback : function(){tip(\"您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试\")},");
/* 205 */     sb.append("onSelectError : function(file, errorCode, errorMsg){");
/* 206 */     sb.append("switch(errorCode) {");
/* 207 */     sb.append("case -100:");
/* 208 */     sb.append("tip(\"上传的文件数量已经超出系统限制的\"+$('#" + this.id + "').uploadify('settings','queueSizeLimit')+\"个文件！\");");
/* 209 */     sb.append("break;");
/* 210 */     sb.append("case -110:tip(\"文件 [\"+file.name+\"] 大小超出系统限制的\"+$('#" + 
/* 211 */       this.id + "').uploadify('settings','fileSizeLimit')+\"大小！\");" + 
/* 212 */       "break;" + 
/* 213 */       "case -120:" + 
/* 214 */       "tip(\"文件 [\"+file.name+\"] 大小异常！\");" + 
/* 215 */       "break;" + 
/* 216 */       "case -130:" + 
/* 217 */       "tip(\"文件 [\"+file.name+\"] 类型不正确！\");" + 
/* 218 */       "break;" + 
/* 219 */       "}");
/* 220 */     sb.append("},onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { }});});function upload() {\t$('#" + 
/* 227 */       this.id + "').uploadify('upload', '*');" + 
/* 228 */       "\t\treturn flag;" + 
/* 229 */       "}" + 
/* 230 */       "function cancel() {" + 
/* 231 */       "$('#" + this.id + "').uploadify('cancel', '*');" + 
/* 232 */       "}" + 
/* 233 */       "</script>");
/* 234 */     sb.append("<span id=\"" + this.id + "span\"><input type=\"file\" name=\"" + this.name + "\" id=\"" + this.id + "\" /></span>");
/* 235 */     if (this.view)
/*     */     {
/* 237 */       sb.append("<span id=\"viewmsg\"></span>");
/* 238 */       sb.append("<input type=\"hidden\" name=\"fileKey\" id=\"fileKey\" />");
/*     */     }
/*     */ 
/* 241 */     return sb;
/*     */   }
/*     */ 
/*     */   private String getUploader()
/*     */   {
/* 249 */     return this.uploader + "&sessionId=" + this.pageContext.getSession().getId() + "',";
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/* 253 */     this.id = id;
/*     */   }
/*     */   public void setFormData(String formData) {
/* 256 */     this.formData = formData;
/*     */   }
/*     */   public void setExtend(String extend) {
/* 259 */     this.extend = extend;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.UploadTag
 * JD-Core Version:    0.6.2
 */