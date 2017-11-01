/*     */ package org.jeecgframework.core.common.model.json;
/*     */ 
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public class TreeGrid
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String text;
/*     */   private String parentId;
/*     */   private String parentText;
/*     */   private String code;
/*     */   private String src;
/*     */   private String note;
/*     */   private Map<String, String> attributes;
/*     */   private String operations;
/*  18 */   private String state = "open";
/*     */   private String order;
/*     */   private String isparent;
/*     */   private Map<String, Object> fieldMap;
/*     */   private String functionType;
/*     */ 
/*     */   public String getFunctionType()
/*     */   {
/*  26 */     return this.functionType;
/*     */   }
/*     */   public void setFunctionType(String functionType) {
/*  29 */     this.functionType = functionType;
/*     */   }
/*     */ 
/*     */   public String getOrder() {
/*  33 */     return this.order;
/*     */   }
/*     */   public void setOrder(String order) {
/*  36 */     this.order = order;
/*     */   }
/*     */   public String getOperations() {
/*  39 */     return this.operations;
/*     */   }
/*     */   public void setOperations(String operations) {
/*  42 */     this.operations = operations;
/*     */   }
/*     */   public Map<String, String> getAttributes() {
/*  45 */     return this.attributes;
/*     */   }
/*     */   public void setAttributes(Map<String, String> attributes) {
/*  48 */     this.attributes = attributes;
/*     */   }
/*     */   public String getParentText() {
/*  51 */     return this.parentText;
/*     */   }
/*     */   public void setParentText(String parentText) {
/*  54 */     this.parentText = parentText;
/*     */   }
/*     */   public String getCode() {
/*  57 */     return this.code;
/*     */   }
/*     */   public void setCode(String code) {
/*  60 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getSrc() {
/*  64 */     return this.src;
/*     */   }
/*     */   public void setSrc(String src) {
/*  67 */     this.src = src;
/*     */   }
/*     */   public String getNote() {
/*  70 */     return this.note;
/*     */   }
/*     */   public void setNote(String note) {
/*  73 */     this.note = note;
/*     */   }
/*     */ 
/*     */   public String getId() {
/*  77 */     return this.id;
/*     */   }
/*     */   public void setId(String id) {
/*  80 */     this.id = id;
/*     */   }
/*     */   public String getText() {
/*  83 */     return this.text;
/*     */   }
/*     */   public void setText(String text) {
/*  86 */     this.text = text;
/*     */   }
/*     */   public String getParentId() {
/*  89 */     return this.parentId;
/*     */   }
/*     */   public void setParentId(String parentId) {
/*  92 */     this.parentId = parentId;
/*     */   }
/*     */   public String getState() {
/*  95 */     return this.state;
/*     */   }
/*     */   public void setState(String state) {
/*  98 */     this.state = state;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getFieldMap() {
/* 102 */     return this.fieldMap;
/*     */   }
/*     */ 
/*     */   public void setFieldMap(Map<String, Object> fieldMap) {
/* 106 */     this.fieldMap = fieldMap;
/*     */   }
/*     */ 
/*     */   public String getIsparent() {
/* 110 */     return this.isparent;
/*     */   }
/*     */   public void setIsparent(String isparent) {
/* 113 */     this.isparent = isparent;
/*     */   }
/*     */   public String toJson() {
/* 116 */     return "{'id':'" + 
/* 117 */       this.id + '\'' + 
/* 118 */       ", 'text':'" + this.text + '\'' + 
/* 119 */       ", 'parentId':'" + this.parentId + '\'' + 
/* 120 */       ", 'parentText':'" + this.parentText + '\'' + 
/* 121 */       ", 'code':'" + this.code + '\'' + 
/* 122 */       ", 'src':'" + this.src + '\'' + 
/* 123 */       ", 'note':'" + this.note + '\'' + 
/* 124 */       ", 'attributes':" + this.attributes + 
/* 125 */       ", 'operations':'" + this.operations + '\'' + 
/* 126 */       ", 'state':'" + this.state + '\'' + 
/* 127 */       ", 'order':'" + this.order + '\'' + 
/* 128 */       ", 'isparent':'" + this.isparent + '\'' + 
/* 129 */       assembleFieldsJson() + 
/* 130 */       '}';
/*     */   }
/*     */ 
/*     */   private String assembleFieldsJson() {
/* 134 */     String fieldsJson = ", 'fieldMap':" + this.fieldMap;
/* 135 */     if ((this.fieldMap != null) && (this.fieldMap.size() > 0)) {
/* 136 */       Map resultMap = new HashMap();
/* 137 */       for (Map.Entry entry : this.fieldMap.entrySet()) {
/* 138 */         resultMap.put("fieldMap." + (String)entry.getKey(), entry.getValue());
/*     */       }
/* 140 */       fieldsJson = ", " + JSON.toJSON(resultMap).toString().replace("{", "").replace("}", "");
/*     */     }
/* 142 */     return fieldsJson;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.json.TreeGrid
 * JD-Core Version:    0.6.2
 */