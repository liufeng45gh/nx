/*     */ package org.jeecgframework.web.cgform.common;
/*     */ 
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
/*     */ 
/*     */ public class FormHtmlUtil
/*     */ {
/*     */   public static String getFormHTML(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/*  18 */     String html = "";
/*  19 */     if (cgFormFieldEntity.getShowType().equals("text"))
/*  20 */       html = getTextFormHtml(cgFormFieldEntity);
/*  21 */     else if (cgFormFieldEntity.getShowType().equals("password"))
/*  22 */       html = getPwdFormHtml(cgFormFieldEntity);
/*  23 */     else if (cgFormFieldEntity.getShowType().equals("radio"))
/*  24 */       html = getRadioFormHtml(cgFormFieldEntity);
/*  25 */     else if (cgFormFieldEntity.getShowType().equals("checkbox"))
/*  26 */       html = getCheckboxFormHtml(cgFormFieldEntity);
/*  27 */     else if (cgFormFieldEntity.getShowType().equals("list"))
/*  28 */       html = getListFormHtml(cgFormFieldEntity);
/*  29 */     else if (cgFormFieldEntity.getShowType().equals("date"))
/*  30 */       html = getDateFormHtml(cgFormFieldEntity);
/*  31 */     else if (cgFormFieldEntity.getShowType().equals("datetime"))
/*  32 */       html = getDatetimeFormHtml(cgFormFieldEntity);
/*  33 */     else if (cgFormFieldEntity.getShowType().equals("file"))
/*  34 */       html = getFileFormHtml(cgFormFieldEntity);
/*  35 */     else if (cgFormFieldEntity.getShowType().equals("textarea"))
/*  36 */       html = getTextAreaFormHtml(cgFormFieldEntity);
/*  37 */     else if (cgFormFieldEntity.getShowType().equals("popup")) {
/*  38 */       html = getPopupFormHtml(cgFormFieldEntity);
/*     */     }
/*     */     else {
/*  41 */       html = getTextFormHtml(cgFormFieldEntity);
/*     */     }
/*  43 */     return html;
/*     */   }
/*     */ 
/*     */   private static String getTextAreaFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/*  52 */     StringBuilder html = new StringBuilder("");
/*     */ 
/*  54 */     html.append("<textarea rows=\"6\" ");
/*  55 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/*  56 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/*     */ 
/*  59 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/*  60 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/*  61 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/*  62 */       html.append("ignore=\"ignore\" ");
/*     */     }
/*  64 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/*  65 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/*  67 */       html.append("datatype=\"*\" ");
/*     */     }
/*  69 */     html.append("\\>");
/*  70 */     html.append("\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}</textarea> ");
/*  71 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getTextFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/*  79 */     StringBuilder html = new StringBuilder("");
/*  80 */     html.append("<input type=\"text\" ");
/*  81 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/*  82 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/*  83 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/*  84 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/*  86 */     html.append("value=\"\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}\" ");
/*  87 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/*  88 */       html.append("ignore=\"ignore\" ");
/*     */     }
/*  90 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0)) {
/*  91 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     }
/*  93 */     else if ("int".equals(cgFormFieldEntity.getType()))
/*  94 */       html.append("datatype=\"n\" ");
/*  95 */     else if ("double".equals(cgFormFieldEntity.getType()))
/*  96 */       html.append("datatype=\"\\/^(-?\\\\d+)(\\\\.\\\\d+)?\\$\\/\" ");
/*     */     else {
/*  98 */       html.append("datatype=\"*\" ");
/*     */     }
/*     */ 
/* 101 */     html.append("\\/>");
/* 102 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getPwdFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 110 */     StringBuilder html = new StringBuilder("");
/* 111 */     html.append("<input type=\"password\" ");
/* 112 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 113 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 114 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 115 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 117 */     html.append("value=\"\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}\" ");
/* 118 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/* 119 */       html.append("ignore=\"ignore\" ");
/*     */     }
/* 121 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/* 122 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/* 124 */       html.append("datatype=\"*\" ");
/*     */     }
/* 126 */     html.append("\\/>");
/* 127 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getRadioFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 137 */     if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
/* 138 */       return getTextFormHtml(cgFormFieldEntity);
/*     */     }
/* 140 */     StringBuilder html = new StringBuilder("");
/* 141 */     html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
/* 142 */     if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
/* 143 */       html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
/*     */     }
/* 145 */     html.append(" var=\"dictDataList\">");
/* 146 */     html.append("<#list dictDataList as dictdata>");
/* 147 */     html.append(" <input type=\"radio\" value=\"\\${dictdata.typecode?if_exists?html}\" name=\"" + cgFormFieldEntity.getFieldName() + "\" ");
/* 148 */     html.append("<#if dictdata.typecode=='\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}'>");
/* 149 */     html.append(" checked=\"true\" ");
/* 150 */     html.append("</#if> ");
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
/* 178 */     html.append("<#list checkboxlist as x >");
/* 179 */     html.append("<#if dictdata.typecode=='\\${x?if_exists?html}'>");
/* 180 */     html.append(" checked=\"true\" ");
/* 181 */     html.append("</#if> ");
/* 182 */     html.append("</#list> ");
/* 183 */     html.append(">");
/* 184 */     html.append("\\${dictdata.typename?if_exists?html}");
/* 185 */     html.append("</#list> ");
/* 186 */     html.append("</@DictData> ");
/* 187 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getListFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 197 */     if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
/* 198 */       return getTextFormHtml(cgFormFieldEntity);
/*     */     }
/* 200 */     StringBuilder html = new StringBuilder("");
/* 201 */     html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
/* 202 */     if (!StringUtil.isEmpty(cgFormFieldEntity.getDictText())) {
/* 203 */       html.append(" text=\"" + cgFormFieldEntity.getDictText() + "\"");
/*     */     }
/* 205 */     if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
/* 206 */       html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
/*     */     }
/* 208 */     html.append(" var=\"dictDataList\">");
/* 209 */     html.append("<select name=\"" + cgFormFieldEntity.getFieldName() + "\" id=\"" + cgFormFieldEntity.getFieldName() + "\"> ");
/* 210 */     html.append("<#list dictDataList as dictdata>");
/* 211 */     html.append(" <option value=\"\\${dictdata.typecode?if_exists?html}\" ");
/* 212 */     html.append("<#if dictdata.typecode=='\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}'>");
/* 213 */     html.append(" selected=\"selected\" ");
/* 214 */     html.append("</#if> ");
/* 215 */     html.append(">");
/* 216 */     html.append("\\${dictdata.typename?if_exists?html}");
/* 217 */     html.append("</option> ");
/* 218 */     html.append("</#list> ");
/* 219 */     html.append("</select>");
/* 220 */     html.append("</@DictData> ");
/* 221 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getDateFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 231 */     StringBuilder html = new StringBuilder("");
/* 232 */     html.append("<input type=\"text\" ");
/* 233 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 234 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 235 */     html.append("class=\"Wdate\" ");
/* 236 */     html.append("onClick=\"WdatePicker()\" ");
/* 237 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 238 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 240 */     html.append("value=\"\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}\" ");
/* 241 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/* 242 */       html.append("ignore=\"ignore\" ");
/*     */     }
/* 244 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/* 245 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/* 247 */       html.append("datatype=\"*\" ");
/*     */     }
/* 249 */     html.append("\\/>");
/* 250 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getDatetimeFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 258 */     StringBuilder html = new StringBuilder("");
/* 259 */     html.append("<input type=\"text\" ");
/* 260 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 261 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 262 */     html.append("class=\"Wdate\" ");
/* 263 */     html.append("onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" ");
/* 264 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 265 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 267 */     html.append("value=\"\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}\" ");
/* 268 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/* 269 */       html.append("ignore=\"ignore\" ");
/*     */     }
/* 271 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/* 272 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/* 274 */       html.append("datatype=\"*\" ");
/*     */     }
/* 276 */     html.append("\\/>");
/* 277 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getFileFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 285 */     StringBuilder html = new StringBuilder("");
/* 286 */     html.append("<input type=\"text\" ");
/* 287 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 288 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 289 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 290 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 292 */     html.append("value=\"\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}\" ");
/* 293 */     html.append("\\/>");
/* 294 */     return html.toString();
/*     */   }
/*     */ 
/*     */   private static String getPopupFormHtml(CgFormFieldEntity cgFormFieldEntity)
/*     */   {
/* 303 */     StringBuilder html = new StringBuilder("");
/* 304 */     html.append("<input type=\"text\" readonly=\"readonly\" class=\"searchbox-inputtext\" ");
/* 305 */     html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 306 */     html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 307 */     if ((cgFormFieldEntity.getFieldLength() != null) && (cgFormFieldEntity.getFieldLength().intValue() > 0)) {
/* 308 */       html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
/*     */     }
/* 310 */     html.append("value=\"\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}\" ");
/* 311 */     html.append("onclick=\"inputClick(this,'" + cgFormFieldEntity.getDictText() + "','" + cgFormFieldEntity.getDictTable() + "');\" ");
/* 312 */     if ("Y".equals(cgFormFieldEntity.getIsNull())) {
/* 313 */       html.append("ignore=\"ignore\" ");
/*     */     }
/* 315 */     if ((cgFormFieldEntity.getFieldValidType() != null) && (cgFormFieldEntity.getFieldValidType().length() > 0))
/* 316 */       html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
/*     */     else {
/* 318 */       html.append("datatype=\"*\" ");
/*     */     }
/* 320 */     html.append("\\/>");
/* 321 */     return html.toString();
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.common.FormHtmlUtil
 * JD-Core Version:    0.6.2
 */