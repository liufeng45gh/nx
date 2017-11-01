/*     */ package org.jeecgframework.tag.vo.easyui;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TreeGridModel
/*     */   implements Serializable
/*     */ {
/*     */   private String idField;
/*     */   private String textField;
/*     */   private String childList;
/*     */   private String parentId;
/*     */   private String parentText;
/*     */   private String code;
/*     */   private String src;
/*     */   private String roleid;
/*     */   private String icon;
/*     */   private String order;
/*     */   private String functionType;
/*     */   private String isparent;
/*     */   private Map<String, Object> fieldMap;
/*     */ 
/*     */   public String getFunctionType()
/*     */   {
/*  28 */     return this.functionType;
/*     */   }
/*     */   public void setFunctionType(String functionType) {
/*  31 */     this.functionType = functionType;
/*     */   }
/*     */ 
/*     */   public String getOrder()
/*     */   {
/*  37 */     return this.order;
/*     */   }
/*     */   public void setOrder(String order) {
/*  40 */     this.order = order;
/*     */   }
/*     */   public String getIcon() {
/*  43 */     return this.icon;
/*     */   }
/*     */   public void setIcon(String icon) {
/*  46 */     this.icon = icon;
/*     */   }
/*     */   public String getRoleid() {
/*  49 */     return this.roleid;
/*     */   }
/*     */   public void setRoleid(String roleid) {
/*  52 */     this.roleid = roleid;
/*     */   }
/*     */   public String getParentText() {
/*  55 */     return this.parentText;
/*     */   }
/*     */   public void setParentText(String parentText) {
/*  58 */     this.parentText = parentText;
/*     */   }
/*     */   public String getCode() {
/*  61 */     return this.code;
/*     */   }
/*     */   public void setCode(String code) {
/*  64 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getSrc() {
/*  68 */     return this.src;
/*     */   }
/*     */   public void setSrc(String src) {
/*  71 */     this.src = src;
/*     */   }
/*     */   public String getParentId() {
/*  74 */     return this.parentId;
/*     */   }
/*     */   public void setParentId(String parentId) {
/*  77 */     this.parentId = parentId;
/*     */   }
/*     */   public String getIdField() {
/*  80 */     return this.idField;
/*     */   }
/*     */   public void setIdField(String idField) {
/*  83 */     this.idField = idField;
/*     */   }
/*     */   public String getTextField() {
/*  86 */     return this.textField;
/*     */   }
/*     */   public void setTextField(String textField) {
/*  89 */     this.textField = textField;
/*     */   }
/*     */   public String getChildList() {
/*  92 */     return this.childList;
/*     */   }
/*     */   public void setChildList(String childList) {
/*  95 */     this.childList = childList;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getFieldMap() {
/*  99 */     return this.fieldMap;
/*     */   }
/*     */ 
/*     */   public void setFieldMap(Map<String, Object> fieldMap) {
/* 103 */     this.fieldMap = fieldMap;
/*     */   }
/*     */   public String getIsparent() {
/* 106 */     return this.isparent;
/*     */   }
/*     */   public void setIsparent(String isparent) {
/* 109 */     this.isparent = isparent;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.vo.easyui.TreeGridModel
 * JD-Core Version:    0.6.2
 */