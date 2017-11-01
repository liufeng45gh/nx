/*     */ package org.jeecgframework.core.extend.template;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class Template
/*     */ {
/*   8 */   public HashMap<String, String> teprems = null;
/*     */   public String templatecCode;
/*     */   public String parameterName;
/*     */   public String parameterValue;
/*     */   public String parameterTagName;
/*     */   public String parameterTagValue;
/*     */   public Object templatecObject;
/*     */   public String templatePath;
/*     */   public Class templateClass;
/*     */   public Map<String, String> dataSourceMap;
/*     */   public List dataSet;
/*     */ 
/*     */   public List getDataSet()
/*     */   {
/*  21 */     return this.dataSet;
/*     */   }
/*     */ 
/*     */   public void setDataSet(List dataSet) {
/*  25 */     this.dataSet = dataSet;
/*     */   }
/*     */ 
/*     */   public String getTemplatePath() {
/*  29 */     return this.templatePath;
/*     */   }
/*     */ 
/*     */   public void setTemplatePath(String templatePath) {
/*  33 */     this.templatePath = templatePath;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getDataSourceMap() {
/*  37 */     return this.dataSourceMap;
/*     */   }
/*     */ 
/*     */   public void setDataSourceMap(Map<String, String> dataSourceMap) {
/*  41 */     this.dataSourceMap = dataSourceMap;
/*     */   }
/*     */ 
/*     */   public String getParameterName() {
/*  45 */     return this.parameterName;
/*     */   }
/*     */ 
/*     */   public Class getTemplateClass() {
/*  49 */     return this.templateClass;
/*     */   }
/*     */ 
/*     */   public void setTemplateClass(Class templateClass) {
/*  53 */     this.templateClass = templateClass;
/*     */   }
/*     */ 
/*     */   public void setParameterName(String parameterName) {
/*  57 */     this.parameterName = parameterName;
/*     */   }
/*     */ 
/*     */   public String getParameterTagName() {
/*  61 */     return this.parameterTagName;
/*     */   }
/*     */ 
/*     */   public void setParameterTagName(String parameterTagName) {
/*  65 */     this.parameterTagName = parameterTagName;
/*     */   }
/*     */ 
/*     */   public String getParameterTagValue() {
/*  69 */     return this.parameterTagValue;
/*     */   }
/*     */ 
/*     */   public void setParameterTagValue(String parameterTagValue) {
/*  73 */     this.parameterTagValue = parameterTagValue;
/*     */   }
/*     */ 
/*     */   public String getParameterValue() {
/*  77 */     return this.parameterValue;
/*     */   }
/*     */ 
/*     */   public void setParameterValue(String parameterValue) {
/*  81 */     this.parameterValue = parameterValue;
/*     */   }
/*     */ 
/*     */   public Object getTemplatecObject() {
/*  85 */     return this.templatecObject;
/*     */   }
/*     */ 
/*     */   public void setTemplatecObject(Object templatecObject) {
/*  89 */     this.templatecObject = templatecObject;
/*     */   }
/*     */ 
/*     */   public String getTemplatecCode() {
/*  93 */     return this.templatecCode;
/*     */   }
/*     */ 
/*     */   public void setTemplatecCode(String templatecCode) {
/*  97 */     this.templatecCode = templatecCode;
/*     */   }
/*     */ 
/*     */   public HashMap<String, String> getTeprems() {
/* 101 */     return this.teprems;
/*     */   }
/*     */ 
/*     */   public void setTeprems(HashMap<String, String> teprems) {
/* 105 */     this.teprems = teprems;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.template.Template
 * JD-Core Version:    0.6.2
 */