/*     */ package org.jeecgframework.web.cgform.pojo.config;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CgFormFieldPojo
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String fieldName;
/*     */   private String tableId;
/*     */   private Integer length;
/*     */   private Integer pointLength;
/*     */   private String type;
/*     */   private String isNull;
/*     */   private Integer orderNum;
/*     */   private String isKey;
/*     */   private String isShow;
/*     */   private String isShowList;
/*     */   private String showType;
/*     */   private String isQuery;
/*     */   private Integer fieldLength;
/*     */   private String fieldHref;
/*     */   private String fieldValidType;
/*     */   private String queryMode;
/*     */   private String content;
/*     */   private Date createDate;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date updateDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*     */   private String dictField;
/*     */   private String dictTable;
/*     */   private String dictText;
/*     */   private String mainTable;
/*     */   private String mainField;
/*     */   private String oldFieldName;
/*     */   private String fieldDefault;
/*     */ 
/*     */   public String getId()
/*     */   {
/*  81 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  89 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getFieldName()
/*     */   {
/*  96 */     return this.fieldName;
/*     */   }
/*     */ 
/*     */   public void setFieldName(String fieldName)
/*     */   {
/* 104 */     this.fieldName = fieldName;
/*     */   }
/*     */ 
/*     */   public String getTableId()
/*     */   {
/* 111 */     return this.tableId;
/*     */   }
/*     */ 
/*     */   public void setTableId(String tableId)
/*     */   {
/* 119 */     this.tableId = tableId;
/*     */   }
/*     */ 
/*     */   public Integer getLength()
/*     */   {
/* 126 */     return this.length;
/*     */   }
/*     */ 
/*     */   public void setLength(Integer length)
/*     */   {
/* 134 */     this.length = length;
/*     */   }
/*     */ 
/*     */   public Integer getPointLength()
/*     */   {
/* 141 */     return this.pointLength;
/*     */   }
/*     */ 
/*     */   public void setPointLength(Integer pointLength)
/*     */   {
/* 149 */     this.pointLength = pointLength;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 156 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 164 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getIsNull()
/*     */   {
/* 171 */     return this.isNull;
/*     */   }
/*     */ 
/*     */   public void setIsNull(String isNull)
/*     */   {
/* 179 */     this.isNull = isNull;
/*     */   }
/*     */ 
/*     */   public String getIsShow()
/*     */   {
/* 186 */     return this.isShow;
/*     */   }
/*     */ 
/*     */   public void setIsShow(String isShow)
/*     */   {
/* 194 */     this.isShow = isShow;
/*     */   }
/*     */ 
/*     */   public String getShowType()
/*     */   {
/* 201 */     return this.showType;
/*     */   }
/*     */ 
/*     */   public void setShowType(String showType)
/*     */   {
/* 209 */     this.showType = showType;
/*     */   }
/*     */ 
/*     */   public String getIsQuery()
/*     */   {
/* 216 */     return this.isQuery;
/*     */   }
/*     */ 
/*     */   public void setIsQuery(String isQuery)
/*     */   {
/* 224 */     this.isQuery = isQuery;
/*     */   }
/*     */ 
/*     */   public String getQueryMode()
/*     */   {
/* 231 */     return this.queryMode;
/*     */   }
/*     */ 
/*     */   public void setQueryMode(String queryMode)
/*     */   {
/* 239 */     this.queryMode = queryMode;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 246 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 254 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 261 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 269 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 276 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 284 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public String getCreateName()
/*     */   {
/* 291 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 299 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 306 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 314 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 321 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 329 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public String getUpdateName()
/*     */   {
/* 336 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 344 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   public Integer getOrderNum()
/*     */   {
/* 351 */     return this.orderNum;
/*     */   }
/*     */ 
/*     */   public void setOrderNum(Integer orderNum) {
/* 355 */     this.orderNum = orderNum;
/*     */   }
/*     */ 
/*     */   public String getIsKey()
/*     */   {
/* 362 */     return this.isKey;
/*     */   }
/*     */ 
/*     */   public void setIsKey(String isKey) {
/* 366 */     this.isKey = isKey;
/*     */   }
/*     */ 
/*     */   public Integer getFieldLength()
/*     */   {
/* 373 */     return this.fieldLength;
/*     */   }
/*     */ 
/*     */   public void setFieldLength(Integer field_length) {
/* 377 */     this.fieldLength = field_length;
/*     */   }
/*     */ 
/*     */   public String getFieldHref()
/*     */   {
/* 384 */     return this.fieldHref;
/*     */   }
/*     */ 
/*     */   public void setFieldHref(String field_href) {
/* 388 */     this.fieldHref = field_href;
/*     */   }
/*     */ 
/*     */   public String getFieldValidType()
/*     */   {
/* 395 */     return this.fieldValidType;
/*     */   }
/*     */ 
/*     */   public void setFieldValidType(String field_valid_type) {
/* 399 */     this.fieldValidType = field_valid_type;
/*     */   }
/*     */ 
/*     */   public String getDictField()
/*     */   {
/* 406 */     return this.dictField;
/*     */   }
/*     */ 
/*     */   public void setDictField(String dictField) {
/* 410 */     this.dictField = dictField;
/*     */   }
/*     */ 
/*     */   public String getDictTable()
/*     */   {
/* 417 */     return this.dictTable;
/*     */   }
/*     */ 
/*     */   public void setDictTable(String dictTable) {
/* 421 */     this.dictTable = dictTable;
/*     */   }
/*     */ 
/*     */   public String getMainTable()
/*     */   {
/* 428 */     return this.mainTable;
/*     */   }
/*     */ 
/*     */   public void setMainTable(String mainTable) {
/* 432 */     this.mainTable = mainTable;
/*     */   }
/*     */ 
/*     */   public String getMainField()
/*     */   {
/* 439 */     return this.mainField;
/*     */   }
/*     */ 
/*     */   public void setMainField(String mainField) {
/* 443 */     this.mainField = mainField;
/*     */   }
/*     */ 
/*     */   public String getOldFieldName()
/*     */   {
/* 451 */     return this.oldFieldName;
/*     */   }
/*     */ 
/*     */   public void setOldFieldName(String oldFieldName) {
/* 455 */     this.oldFieldName = oldFieldName;
/*     */   }
/*     */ 
/*     */   public String getIsShowList()
/*     */   {
/* 462 */     return this.isShowList;
/*     */   }
/*     */ 
/*     */   public void setIsShowList(String isShowList) {
/* 466 */     this.isShowList = isShowList;
/*     */   }
/*     */ 
/*     */   public String getDictText()
/*     */   {
/* 473 */     return this.dictText;
/*     */   }
/*     */ 
/*     */   public void setDictText(String dictText) {
/* 477 */     this.dictText = dictText;
/*     */   }
/*     */ 
/*     */   public String getFieldDefault() {
/* 481 */     if (this.fieldDefault != null) {
/* 482 */       this.fieldDefault = this.fieldDefault.trim();
/*     */     }
/* 484 */     return this.fieldDefault;
/*     */   }
/*     */ 
/*     */   public void setFieldDefault(String fieldDefault) {
/* 488 */     this.fieldDefault = fieldDefault;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.pojo.config.CgFormFieldPojo
 * JD-Core Version:    0.6.2
 */