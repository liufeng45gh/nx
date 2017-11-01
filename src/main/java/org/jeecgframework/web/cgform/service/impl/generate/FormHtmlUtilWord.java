/*     */ package org.jeecgframework.web.cgform.service.impl.generate;
/*     */ 
/*     */ import org.jeecgframework.codegenerate.database.JeecgReadTable;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
/*     */ 
/*     */ public class FormHtmlUtilWord
/*     */ {
/*     */   public static String getFormHTML(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/*  19 */     String html = "";
/*  20 */     if (cgFormFieldEntity.getShowType().equals("text"))
/*  21 */       html = getTextFormHtml(cgFormFieldEntity);
/*  22 */     else if (cgFormFieldEntity.getShowType().equals("password"))
/*  23 */       html = getPwdFormHtml(cgFormFieldEntity);
/*  24 */     else if (cgFormFieldEntity.getShowType().equals("radio"))
/*  25 */       html = getRadioFormHtml(cgFormFieldEntity);
/*  26 */     else if (cgFormFieldEntity.getShowType().equals("checkbox"))
/*  27 */       html = getCheckboxFormHtml(cgFormFieldEntity);
/*  28 */     else if (cgFormFieldEntity.getShowType().equals("list"))
/*  29 */       html = getListFormHtml(cgFormFieldEntity);
/*  30 */     else if (cgFormFieldEntity.getShowType().equals("date"))
/*  31 */       html = getDateFormHtml(cgFormFieldEntity);
/*  32 */     else if (cgFormFieldEntity.getShowType().equals("datetime"))
/*  33 */       html = getDatetimeFormHtml(cgFormFieldEntity);
/*  34 */     else if (cgFormFieldEntity.getShowType().equals("file"))
/*  35 */       html = getFileFormHtml(cgFormFieldEntity);
/*  36 */     else if (cgFormFieldEntity.getShowType().equals("textarea"))
/*  37 */       html = getTextAreaFormHtml(cgFormFieldEntity);
/*  38 */     else if (cgFormFieldEntity.getShowType().equals("popup")) {
/*  39 */       html = getPopupFormHtml(cgFormFieldEntity);
/*     */     }
/*     */     else {
/*  42 */       html = getTextFormHtml(cgFormFieldEntity);
/*     */     }
/*  44 */     return html;
/*     */   }
/*     */ 
/*     */   private static String getTextAreaFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/*  53 */     StringBuilder html = new StringBuilder("");
/*  54 */     html.append("<textarea  style=\"width: 300px\" rows=\"6\" ");
/*  55 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/*  56 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/*  57 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/*  58 */       html.append("ignore=\"ignore\" ");
/*     */     }
/*  60 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/*  61 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/*  63 */       html.append("datatype=\"*\" ");
/*     */     }
/*  65 */     html.append("\\>");
/*  66 */     html.append("\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}</textarea> ");
/*  67 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getTextFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/*  75 */     StringBuilder html = new StringBuilder("");
/*  76 */     html.append("<input type=\"text\" ");
/*  77 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/*  78 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/*  79 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/*  80 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/*     */ 
/*  83 */     html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
/*     */ 
/*  85 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/*  86 */       html.append("ignore=\"ignore\" ");
/*     */     }
/*  88 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0)) {
/*  89 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     }
/*  91 */     else if ("int".equals(cgFormFieldEntity.getType()))
/*  92 */       html.append("datatype=\"n\" ");
/*  93 */     else if ("double".equals(cgFormFieldEntity.getType()))
/*  94 */       html.append("datatype=\"\\/^(-?\\\\d+)(\\\\.\\\\d+)?\\$\\/\" ");
/*     */     else {
/*  96 */       html.append("datatype=\"*\" ");
/*     */     }
/*     */ 
/*  99 */     html.append("\\/>");
/* 100 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getPwdFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 108 */     StringBuilder html = new StringBuilder("");
/* 109 */     html.append("<input type=\"password\" ");
/* 110 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 111 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 112 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 113 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 115 */     html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
/* 116 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/* 117 */       html.append("ignore=\"ignore\" ");
/*     */     }
/* 119 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/* 120 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/* 122 */       html.append("datatype=\"*\" ");
/*     */     }
/* 124 */     html.append("\\/>");
/* 125 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getRadioFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 135 */     if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
/* 136 */       return getTextFormHtml(cgFormFieldEntity);
/*     */     }
/* 138 */     StringBuilder html = new StringBuilder("");
/* 139 */     html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
/* 140 */     if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
/* 141 */       html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
/*     */     }
/* 143 */     html.append(" var=\"dictDataList\">");
/* 144 */     html.append("<#list dictDataList as dictdata>");
/* 145 */     html.append(" <input type=\"radio\" value=\"\\${dictdata.typecode?if_exists?html}\" name=\"" + cgFormFieldEntity.getFieldName() + "\" ");
/*     */ 
/* 147 */     html.append("<c:if test=\"@@@{onlineCodeGenereateEntityKey." + cgFormFieldEntity.getFieldName() + "=='\\${dictdata.typecode?if_exists?html}'}\" >");
/* 148 */     html.append(" checked=\"true\" ");
/* 149 */     html.append("</c:if>");
/*     */ 
/* 151 */     html.append(">");
/* 152 */     html.append("\\${dictdata.typename?if_exists?html}");
/* 153 */     html.append("</#list> ");
/* 154 */     html.append("</@DictData> ");
/* 155 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getCheckboxFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 165 */     if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
/* 166 */       return getTextFormHtml(cgFormFieldEntity);
/*     */     }
/* 168 */     StringBuilder html = new StringBuilder("");
/* 169 */     html.append("<#assign checkboxstr>\\${data['").append(cgFormFieldEntity.getFieldName()).append("']?if_exists?html}</#assign>");
/* 170 */     html.append("<#assign checkboxlist=checkboxstr?split(\",\")> ");
/* 171 */     html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
/* 172 */     if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
/* 173 */       html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
/*     */     }
/* 175 */     html.append(" var=\"dictDataList\">");
/* 176 */     html.append("<#list dictDataList as dictdata>");
/* 177 */     html.append(" <input type=\"checkbox\" value=\"\\${dictdata.typecode?if_exists?html}\" name=\"" + cgFormFieldEntity.getFieldName() + "\" ");
/*     */ 
/* 179 */     html.append("<c:if test=\"@@@{onlineCodeGenereateEntityKey." + cgFormFieldEntity.getFieldName() + "=='\\${dictdata.typecode?if_exists?html}'}\" >");
/* 180 */     html.append(" checked=\"true\" ");
/* 181 */     html.append("</c:if>");
/*     */ 
/* 183 */     html.append("<#if dictdata.typecode=='\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}'>");
/* 184 */     html.append(" checked=\"true\" ");
/* 185 */     html.append("</#if> ");
/*     */ 
/* 187 */     html.append(">");
/* 188 */     html.append("\\${dictdata.typename?if_exists?html}");
/* 189 */     html.append("</#list> ");
/* 190 */     html.append("</@DictData> ");
/* 191 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getListFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 201 */     if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
/* 202 */       return getTextFormHtml(cgFormFieldEntity);
/*     */     }
/* 204 */     StringBuilder html = new StringBuilder("");
/* 205 */     html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
/* 206 */     if (!StringUtil.isEmpty(cgFormFieldEntity.getDictText())) {
/* 207 */       html.append(" text=\"" + cgFormFieldEntity.getDictText() + "\"");
/*     */     }
/* 209 */     if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
/* 210 */       html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
/*     */     }
/* 212 */     html.append(" var=\"dictDataList\">");
/* 213 */     html.append("<select name=\"" + JeecgReadTable.formatField(cgFormFieldEntity.getFieldName()) + "\" id=\"" + JeecgReadTable.formatField(cgFormFieldEntity.getFieldName()) + "\"> ");
/* 214 */     html.append("<#list dictDataList as dictdata>");
/* 215 */     html.append(" <option value=\"\\${dictdata.typecode?if_exists?html}\" ");
/*     */ 
/* 217 */     html.append("<c:if test=\"@@@{onlineCodeGenereateEntityKey." + JeecgReadTable.formatField(cgFormFieldEntity.getFieldName()) + "=='\\${dictdata.typecode?if_exists?html}'}\" >");
/* 218 */     html.append(" selected=\"selected\" ");
/* 219 */     html.append("</c:if>");
/*     */ 
/* 221 */     html.append(">");
/* 222 */     html.append("\\${dictdata.typename?if_exists?html}");
/* 223 */     html.append("</option> ");
/* 224 */     html.append("</#list> ");
/* 225 */     html.append("</select>");
/* 226 */     html.append("</@DictData> ");
/* 227 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getDateFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 237 */     StringBuilder html = new StringBuilder("");
/* 238 */     html.append("<input type=\"text\" ");
/* 239 */     html.append("id=\"").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("\" ");
/* 240 */     html.append("name=\"").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("\" ");
/* 241 */     html.append("class=\"Wdate\" ");
/* 242 */     html.append("onClick=\"WdatePicker()\" ");
/* 243 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 244 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 246 */     html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("}\" ");
/* 247 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/* 248 */       html.append("ignore=\"ignore\" ");
/*     */     }
/* 250 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/* 251 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/* 253 */       html.append("datatype=\"*\" ");
/*     */     }
/* 255 */     html.append("\\/>");
/* 256 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getDatetimeFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 264 */     StringBuilder html = new StringBuilder("");
/* 265 */     html.append("<input type=\"text\" ");
/* 266 */     html.append("id=\"").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("\" ");
/* 267 */     html.append("name=\"").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("\" ");
/* 268 */     html.append("class=\"Wdate\" ");
/* 269 */     html.append("onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" ");
/* 270 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 271 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 273 */     html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("}\" ");
/* 274 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/* 275 */       html.append("ignore=\"ignore\" ");
/*     */     }
/* 277 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/* 278 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/* 280 */       html.append("datatype=\"*\" ");
/*     */     }
/* 282 */     html.append("\\/>");
/* 283 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getFileFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 291 */     StringBuilder html = new StringBuilder("");
/* 292 */     html.append("<input type=\"text\" ");
/* 293 */     html.append("id=\"").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("\" ");
/* 294 */     html.append("name=\"").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("\" ");
/* 295 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 296 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 298 */     html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("}\" ");
/* 299 */     html.append("\\/>");
/* 300 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getPopupFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 309 */     StringBuilder html = new StringBuilder("");
/* 310 */     html.append("<input type=\"text\" readonly=\"readonly\" class=\"searchbox-inputtext\" ");
/* 311 */     html.append("id=\"").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("\" ");
/* 312 */     html.append("name=\"").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("\" ");
/* 313 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 314 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 316 */     html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(JeecgReadTable.formatField(cgFormFieldEntity.getFieldName())).append("}\" ");
/* 317 */     html.append("onclick=\"inputClick(this,'" + cgFormFieldEntity.getDictText() + "','" + cgFormFieldEntity.getDictTable() + "');\" ");
/* 318 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/* 319 */       html.append("ignore=\"ignore\" ");
/*     */     }
/* 321 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/* 322 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/* 324 */       html.append("datatype=\"*\" ");
/*     */     }
/* 326 */     html.append("\\/>");
/* 327 */     return html.toString();
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.generate.FormHtmlUtilWord
 * JD-Core Version:    0.6.2
 */