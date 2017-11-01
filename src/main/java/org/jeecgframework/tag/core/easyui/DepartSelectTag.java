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
/*     */ public class DepartSelectTag extends TagSupport
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String readonly;
/*     */   private String selectedNamesInputId;
/*     */   private String selectedIdsInputId;
/*     */   private String departNameInputWidth;
/*     */   private String windowWidth;
/*     */   private String windowHeight;
/*     */   private String departId;
/*     */   private String departName;
/*     */ 
/*     */   public String getReadonly()
/*     */   {
/*  26 */     return this.readonly;
/*     */   }
/*     */   public void setReadonly(String readonly) {
/*  29 */     this.readonly = readonly;
/*     */   }
/*     */ 
/*     */   public String getSelectedNamesInputId()
/*     */   {
/*  34 */     return this.selectedNamesInputId;
/*     */   }
/*     */ 
/*     */   public void setSelectedNamesInputId(String _selectedNamesInputId) {
/*  38 */     this.selectedNamesInputId = _selectedNamesInputId;
/*     */   }
/*     */ 
/*     */   public String getSelectedIdsInputId()
/*     */   {
/*  43 */     return this.selectedIdsInputId;
/*     */   }
/*     */ 
/*     */   public void setSelectedIdsInputId(String _selectedIdsInputId) {
/*  47 */     this.selectedIdsInputId = _selectedIdsInputId;
/*     */   }
/*     */ 
/*     */   public String getDepartNameInputWidth()
/*     */   {
/*  52 */     return this.departNameInputWidth;
/*     */   }
/*     */ 
/*     */   public void setDepartNameInputWidth(String departNameInputWidth)
/*     */   {
/*  57 */     this.departNameInputWidth = departNameInputWidth;
/*     */   }
/*     */ 
/*     */   public String getWindowWidth()
/*     */   {
/*  62 */     return this.windowWidth;
/*     */   }
/*     */ 
/*     */   public void setWindowWidth(String windowWidth) {
/*  66 */     this.windowWidth = windowWidth;
/*     */   }
/*     */ 
/*     */   public String getWindowHeight()
/*     */   {
/*  71 */     return this.windowHeight;
/*     */   }
/*     */ 
/*     */   public void setWindowHeight(String windowHeight) {
/*  75 */     this.windowHeight = windowHeight;
/*     */   }
/*     */ 
/*     */   public String getDepartId()
/*     */   {
/*  82 */     return this.departId;
/*     */   }
/*     */   public void setDepartId(String departId) {
/*  85 */     this.departId = departId;
/*     */   }
/*     */   public String getDepartName() {
/*  88 */     return this.departName;
/*     */   }
/*     */   public void setDepartName(String departName) {
/*  91 */     this.departName = departName;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException {
/*  95 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*  99 */     JspWriter out = null;
/*     */     try {
/* 101 */       out = this.pageContext.getOut();
/* 102 */       out.print(end().toString());
/* 103 */       out.flush();
/*     */     } catch (IOException e) {
/* 105 */       e.printStackTrace();
/*     */       try
/*     */       {
/* 108 */         out.clear();
/* 109 */         out.close();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 108 */         out.clear();
/* 109 */         out.close();
/*     */       } catch (Exception localException1) {
/*     */       }
/*     */     }
/* 113 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end()
/*     */   {
/* 118 */     StringBuffer sb = new StringBuffer();
/* 119 */     if (StringUtils.isBlank(this.selectedNamesInputId)) {
/* 120 */       this.selectedNamesInputId = "departname";
/*     */     }
/* 122 */     if (StringUtils.isBlank(this.selectedIdsInputId)) {
/* 123 */       this.selectedIdsInputId = "orgIds";
/*     */     }
/* 125 */     String lblDepartment = MutiLangUtil.getMutiLangInstance().getLang("common.department");
/* 126 */     if (StringUtils.isBlank(lblDepartment)) {
/* 127 */       lblDepartment = "组织机构";
/*     */     }
/*     */ 
/* 130 */     if (StringUtils.isBlank(this.departNameInputWidth)) {
/* 131 */       this.departNameInputWidth = "80px";
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
/* 143 */     sb.append("<span style=\"vertical-align:middle;display:-moz-inline-box;display:inline-block;width: " + this.departNameInputWidth + ";text-align:right;\" title=\"" + lblDepartment + "\"/>");
/* 144 */     sb.append(lblDepartment + "：");
/* 145 */     sb.append("</span>");
/* 146 */     sb.append("<input readonly=\"true\" type=\"text\" id=\"" + this.selectedNamesInputId + "\" name=\"" + this.selectedNamesInputId + "\" style=\"width: 300px\" onclick=\"openDepartmentSelect()\" ");
/* 147 */     if (StringUtils.isNotBlank(this.departId)) {
/* 148 */       sb.append(" value=\"" + this.departName + "\"");
/*     */     }
/* 150 */     sb.append(" />");
/* 151 */     sb.append("<input id=\"" + this.selectedIdsInputId + "\" name=\"" + this.selectedIdsInputId + "\" type=\"hidden\" ");
/* 152 */     if (StringUtils.isNotBlank(this.departName)) {
/* 153 */       sb.append(" value=\"" + this.departId + "\"");
/*     */     }
/* 155 */     sb.append(">");
/* 156 */     sb.append("</span>");
/*     */ 
/* 158 */     String commonDepartmentList = MutiLangUtil.getMutiLangInstance().getLang("common.department.list");
/* 159 */     String commonConfirm = MutiLangUtil.getMutiLangInstance().getLang("common.confirm");
/* 160 */     String commonCancel = MutiLangUtil.getMutiLangInstance().getLang("common.cancel");
/*     */ 
/* 162 */     sb.append("<script type=\"text/javascript\">");
/* 163 */     sb.append("function openDepartmentSelect() {");
/* 164 */     sb.append("    $.dialog.setting.zIndex = 9999; ");
/* 165 */     sb.append("    $.dialog({content: 'url:departController.do?departSelect', zIndex: 2100, title: '" + commonDepartmentList + "', lock: true, width: '" + this.windowWidth + "', height: '" + this.windowHeight + "', opacity: 0.4, button: [");
/* 166 */     sb.append("       {name: '" + commonConfirm + "', callback: callbackDepartmentSelect, focus: true},");
/* 167 */     sb.append("       {name: '" + commonCancel + "', callback: function (){}}");
/* 168 */     sb.append("   ]}).zindex();");
/* 169 */     sb.append("}");
/*     */ 
/* 171 */     sb.append("function callbackDepartmentSelect() {");
/* 172 */     sb.append("    var iframe = this.iframe.contentWindow;");
/*     */ 
/* 175 */     sb.append(" var treeObj = iframe.$.fn.zTree.getZTreeObj(\"departSelect\");");
/* 176 */     sb.append(" var nodes = treeObj.getCheckedNodes(true);");
/* 177 */     sb.append(" if(nodes.length>0){");
/* 178 */     sb.append(" var ids='',names='';");
/* 179 */     sb.append("for(i=0;i<nodes.length;i++){");
/* 180 */     sb.append(" var node = nodes[i];");
/* 181 */     sb.append(" ids += node.id+',';");
/* 182 */     sb.append(" names += node.name+',';");
/* 183 */     sb.append("}");
/* 184 */     sb.append(" $('#" + this.selectedNamesInputId + "').val(names);");
/* 185 */     sb.append(" $('#" + this.selectedNamesInputId + "').blur();");
/* 186 */     sb.append(" $('#" + this.selectedIdsInputId + "').val(ids);");
/* 187 */     sb.append("}");
/*     */ 
/* 189 */     sb.append("}");
/* 190 */     sb.append("</script>");
/* 191 */     return sb;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.DepartSelectTag
 * JD-Core Version:    0.6.2
 */