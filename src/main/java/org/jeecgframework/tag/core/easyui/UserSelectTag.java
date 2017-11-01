/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.jeecgframework.core.util.MutiLangUtil;
/*     */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*     */ 
/*     */ public class UserSelectTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String readonly;
/*     */   private String title;
/*     */   private String lblName;
/*     */   private String selectedNamesInputId;
/*     */   private String userNameInputWidth;
/*     */   private String windowWidth;
/*     */   private String windowHeight;
/*     */   private String userName;
/*     */ 
/*     */   public String getReadonly()
/*     */   {
/*  25 */     return this.readonly;
/*     */   }
/*     */   public void setReadonly(String readonly) {
/*  28 */     this.readonly = readonly;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/*  34 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  38 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getLblName()
/*     */   {
/*  44 */     return this.lblName;
/*     */   }
/*     */ 
/*     */   public void setLblName(String lblName) {
/*  48 */     this.lblName = lblName;
/*     */   }
/*     */ 
/*     */   public String getSelectedNamesInputId()
/*     */   {
/*  53 */     return this.selectedNamesInputId;
/*     */   }
/*     */ 
/*     */   public void setSelectedNamesInputId(String _selectedNamesInputId) {
/*  57 */     this.selectedNamesInputId = _selectedNamesInputId;
/*     */   }
/*     */ 
/*     */   public String getUserNameInputWidth()
/*     */   {
/*  62 */     return this.userNameInputWidth;
/*     */   }
/*     */ 
/*     */   public void setUserNameInputWidth(String userNameInputWidth)
/*     */   {
/*  67 */     this.userNameInputWidth = userNameInputWidth;
/*     */   }
/*     */ 
/*     */   public String getWindowWidth()
/*     */   {
/*  72 */     return this.windowWidth;
/*     */   }
/*     */ 
/*     */   public void setWindowWidth(String windowWidth) {
/*  76 */     this.windowWidth = windowWidth;
/*     */   }
/*     */ 
/*     */   public String getWindowHeight()
/*     */   {
/*  81 */     return this.windowHeight;
/*     */   }
/*     */ 
/*     */   public void setWindowHeight(String windowHeight) {
/*  85 */     this.windowHeight = windowHeight;
/*     */   }
/*     */ 
/*     */   public String getUserName()
/*     */   {
/*  91 */     return this.userName;
/*     */   }
/*     */   public void setUserName(String userName) {
/*  94 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException {
/*  98 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/* 102 */     JspWriter out = null;
/*     */     try {
/* 104 */       out = this.pageContext.getOut();
/* 105 */       out.print(end().toString());
/* 106 */       out.flush();
/*     */     } catch (IOException e) {
/* 108 */       e.printStackTrace();
/*     */       try
/*     */       {
/* 111 */         out.clear();
/* 112 */         out.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 111 */         out.clear();
/* 112 */         out.close();
/*     */       } catch (Exception localException1) {
/*     */       }
/*     */     }
/* 116 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end()
/*     */   {
/* 121 */     StringBuffer sb = new StringBuffer();
/* 122 */     if (StringUtils.isBlank(this.selectedNamesInputId)) {
/* 123 */       this.selectedNamesInputId = "realName";
/*     */     }
/*     */ 
/* 126 */     if (StringUtils.isBlank(this.lblName)) {
/* 127 */       this.lblName = "真实姓名";
/*     */     }
/*     */ 
/* 130 */     if (StringUtils.isBlank(this.userNameInputWidth)) {
/* 131 */       this.userNameInputWidth = "80px";
/*     */     }
/*     */ 
/* 134 */     if (StringUtils.isBlank(this.windowWidth)) {
/* 135 */       this.windowWidth = "400px";
/*     */     }
/*     */ 
/* 138 */     if (StringUtils.isBlank(this.windowHeight)) {
/* 139 */       this.windowHeight = "350px";
/*     */     }
/*     */ 
/* 142 */     sb.append("<span style=\"display:-moz-inline-box;display:inline-block;\">");
/* 143 */     sb.append("<span style=\"vertical-align:middle;display:-moz-inline-box;display:inline-block;width: " + this.userNameInputWidth + ";text-align:right;\" title=\"" + this.lblName + "\"/>");
/* 144 */     sb.append(this.lblName + "：");
/* 145 */     sb.append("</span>");
/* 146 */     sb.append("<input readonly=\"true\" type=\"text\" id=\"" + this.selectedNamesInputId + "\" name=\"" + this.selectedNamesInputId + "\" style=\"width: 200px\" onclick=\"openUserSelect()\" ");
/*     */ 
/* 156 */     sb.append("</span>");
/*     */ 
/* 159 */     String commonConfirm = MutiLangUtil.getMutiLangInstance().getLang("common.confirm");
/* 160 */     String commonCancel = MutiLangUtil.getMutiLangInstance().getLang("common.cancel");
/*     */ 
/* 162 */     sb.append("<script type=\"text/javascript\">");
/* 163 */     sb.append("function openUserSelect() {");
/* 164 */     sb.append("    $.dialog.setting.zIndex = 9999; ");
/* 165 */     sb.append("    $.dialog({content: 'url:userController.do?userSelect', zIndex: 2100, title: '" + this.title + "', lock: true, width: '" + this.windowWidth + "', height: '" + this.windowHeight + "', opacity: 0.4, button: [");
/* 166 */     sb.append("       {name: '" + commonConfirm + "', callback: callbackUserSelect, focus: true},");
/* 167 */     sb.append("       {name: '" + commonCancel + "', callback: function (){}}");
/* 168 */     sb.append("   ]}).zindex();");
/* 169 */     sb.append("}");
/*     */ 
/* 171 */     sb.append("function callbackUserSelect() {");
/* 172 */     sb.append("var iframe = this.iframe.contentWindow;");
/*     */ 
/* 174 */     sb.append("var rowsData = iframe.$('#userList1').datagrid('getSelections');");
/* 175 */     sb.append("if (!rowsData || rowsData.length==0) {");
/* 176 */     sb.append("tip('<t:mutiLang langKey=\"common.please.select.edit.item\"/>');");
/* 177 */     sb.append("return;");
/* 178 */     sb.append("}");
/* 179 */     sb.append("var names=rowsData[0].realName;");
/* 180 */     sb.append("$('#" + this.selectedNamesInputId + "').val(names);");
/* 181 */     sb.append("$('#" + this.selectedNamesInputId + "').blur();");
/*     */ 
/* 183 */     sb.append("}");
/* 184 */     sb.append("</script>");
/* 185 */     return sb;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.UserSelectTag
 * JD-Core Version:    0.6.2
 */