/*     */ package org.jeecgframework.web.cgform.entity.config;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import org.codehaus.jackson.annotate.JsonAutoDetect;
/*     */ import org.codehaus.jackson.annotate.JsonIgnore;
/*     */ import org.hibernate.annotations.DynamicInsert;
/*     */ import org.hibernate.annotations.DynamicUpdate;
/*     */ import org.hibernate.annotations.ForeignKey;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cgform_field", schema="")
/*     */ @DynamicUpdate(true)
/*     */ @DynamicInsert(true)
/*     */ @JsonAutoDetect
/*     */ public class CgFormFieldEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String fieldName;
/*     */   private CgFormHeadEntity table;
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
/*     */   private String extendJson;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="id", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/* 112 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 120 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="field_name", nullable=false, length=32)
/*     */   public String getFieldName()
/*     */   {
/* 128 */     return this.fieldName;
/*     */   }
/*     */ 
/*     */   public void setFieldName(String fieldName)
/*     */   {
/* 136 */     this.fieldName = fieldName;
/*     */   }
/*     */ 
/*     */   @ManyToOne
/*     */   @JoinColumn(name="table_id", nullable=false, referencedColumnName="id")
/*     */   @JsonIgnore
/*     */   @ForeignKey(name="null")
/*     */   public CgFormHeadEntity getTable() {
/* 147 */     return this.table;
/*     */   }
/*     */ 
/*     */   public void setTable(CgFormHeadEntity table)
/*     */   {
/* 155 */     this.table = table;
/*     */   }
/*     */ 
/*     */   @Column(name="length", nullable=false, precision=10, scale=0)
/*     */   public Integer getLength()
/*     */   {
/* 163 */     return this.length;
/*     */   }
/*     */ 
/*     */   public void setLength(Integer length)
/*     */   {
/* 171 */     this.length = length;
/*     */   }
/*     */ 
/*     */   @Column(name="point_length", nullable=true, precision=10, scale=0)
/*     */   public Integer getPointLength()
/*     */   {
/* 179 */     return this.pointLength;
/*     */   }
/*     */ 
/*     */   public void setPointLength(Integer pointLength)
/*     */   {
/* 187 */     this.pointLength = pointLength;
/*     */   }
/*     */ 
/*     */   @Column(name="type", nullable=false, length=32)
/*     */   public String getType()
/*     */   {
/* 195 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 203 */     this.type = type;
/*     */   }
/*     */ 
/*     */   @Column(name="is_null", nullable=true, length=5)
/*     */   public String getIsNull()
/*     */   {
/* 211 */     return this.isNull;
/*     */   }
/*     */ 
/*     */   public void setIsNull(String isNull)
/*     */   {
/* 219 */     this.isNull = isNull;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show", nullable=true, length=5)
/*     */   public String getIsShow()
/*     */   {
/* 227 */     return this.isShow;
/*     */   }
/*     */ 
/*     */   public void setIsShow(String isShow)
/*     */   {
/* 235 */     this.isShow = isShow;
/*     */   }
/*     */ 
/*     */   @Column(name="show_type", nullable=true, length=10)
/*     */   public String getShowType()
/*     */   {
/* 243 */     return this.showType;
/*     */   }
/*     */ 
/*     */   public void setShowType(String showType)
/*     */   {
/* 251 */     this.showType = showType;
/*     */   }
/*     */ 
/*     */   @Column(name="is_query", nullable=true, length=5)
/*     */   public String getIsQuery()
/*     */   {
/* 259 */     return this.isQuery;
/*     */   }
/*     */ 
/*     */   public void setIsQuery(String isQuery)
/*     */   {
/* 267 */     this.isQuery = isQuery;
/*     */   }
/*     */ 
/*     */   @Column(name="query_mode", nullable=true, length=10)
/*     */   public String getQueryMode()
/*     */   {
/* 275 */     return this.queryMode;
/*     */   }
/*     */ 
/*     */   public void setQueryMode(String queryMode)
/*     */   {
/* 283 */     this.queryMode = queryMode;
/*     */   }
/*     */ 
/*     */   @Column(name="content", nullable=false, length=200)
/*     */   public String getContent()
/*     */   {
/* 291 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 299 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="create_date", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/* 307 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 315 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="create_by", nullable=true)
/*     */   public String getCreateBy()
/*     */   {
/* 323 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 331 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="create_name", nullable=true, length=32)
/*     */   public String getCreateName()
/*     */   {
/* 339 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 347 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="update_date", nullable=true)
/*     */   public Date getUpdateDate()
/*     */   {
/* 355 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 363 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="update_by", nullable=true, length=32)
/*     */   public String getUpdateBy()
/*     */   {
/* 371 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 379 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="update_name", nullable=true, length=32)
/*     */   public String getUpdateName()
/*     */   {
/* 387 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 395 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="order_num", nullable=true, length=4)
/*     */   public Integer getOrderNum()
/*     */   {
/* 403 */     return this.orderNum;
/*     */   }
/*     */ 
/*     */   public void setOrderNum(Integer orderNum) {
/* 407 */     this.orderNum = orderNum;
/*     */   }
/*     */ 
/*     */   @Column(name="is_key", nullable=true, length=2)
/*     */   public String getIsKey()
/*     */   {
/* 415 */     return this.isKey;
/*     */   }
/*     */ 
/*     */   public void setIsKey(String isKey) {
/* 419 */     this.isKey = isKey;
/*     */   }
/*     */ 
/*     */   @Column(name="field_length", nullable=true, length=10)
/*     */   public Integer getFieldLength()
/*     */   {
/* 427 */     return this.fieldLength;
/*     */   }
/*     */ 
/*     */   public void setFieldLength(Integer field_length) {
/* 431 */     this.fieldLength = field_length;
/*     */   }
/*     */ 
/*     */   @Column(name="field_href", nullable=true, length=200)
/*     */   public String getFieldHref()
/*     */   {
/* 439 */     return this.fieldHref;
/*     */   }
/*     */ 
/*     */   public void setFieldHref(String field_href) {
/* 443 */     this.fieldHref = field_href;
/*     */   }
/*     */ 
/*     */   @Column(name="field_valid_type", nullable=true, length=10)
/*     */   public String getFieldValidType()
/*     */   {
/* 451 */     return this.fieldValidType;
/*     */   }
/*     */ 
/*     */   public void setFieldValidType(String field_valid_type) {
/* 455 */     this.fieldValidType = field_valid_type;
/*     */   }
/*     */ 
/*     */   @Column(name="dict_field", nullable=true, length=100)
/*     */   public String getDictField()
/*     */   {
/* 463 */     return this.dictField;
/*     */   }
/*     */ 
/*     */   public void setDictField(String dictField) {
/* 467 */     this.dictField = dictField;
/*     */   }
/*     */ 
/*     */   @Column(name="dict_table", nullable=true, length=100)
/*     */   public String getDictTable()
/*     */   {
/* 475 */     return this.dictTable;
/*     */   }
/*     */ 
/*     */   public void setDictTable(String dictTable) {
/* 479 */     this.dictTable = dictTable;
/*     */   }
/*     */ 
/*     */   @Column(name="main_table", nullable=true, length=100)
/*     */   public String getMainTable()
/*     */   {
/* 487 */     return this.mainTable;
/*     */   }
/*     */ 
/*     */   public void setMainTable(String mainTable) {
/* 491 */     this.mainTable = mainTable;
/*     */   }
/*     */ 
/*     */   @Column(name="main_field", nullable=true, length=100)
/*     */   public String getMainField()
/*     */   {
/* 499 */     return this.mainField;
/*     */   }
/*     */ 
/*     */   public void setMainField(String mainField) {
/* 503 */     this.mainField = mainField;
/*     */   }
/*     */ 
/*     */   @Column(name="old_field_name", nullable=true, length=32)
/*     */   public String getOldFieldName()
/*     */   {
/* 512 */     return this.oldFieldName;
/*     */   }
/*     */ 
/*     */   public void setOldFieldName(String oldFieldName) {
/* 516 */     this.oldFieldName = oldFieldName;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show_list", nullable=true, length=5)
/*     */   public String getIsShowList()
/*     */   {
/* 524 */     return this.isShowList;
/*     */   }
/*     */ 
/*     */   public void setIsShowList(String isShowList) {
/* 528 */     this.isShowList = isShowList;
/*     */   }
/*     */ 
/*     */   @Column(name="dict_text", nullable=true, length=100)
/*     */   public String getDictText()
/*     */   {
/* 536 */     return this.dictText;
/*     */   }
/*     */ 
/*     */   public void setDictText(String dictText) {
/* 540 */     this.dictText = dictText;
/*     */   }
/*     */ 
/*     */   @Column(name="field_default", nullable=true, length=20)
/*     */   public String getFieldDefault() {
/* 545 */     if (this.fieldDefault != null) {
/* 546 */       this.fieldDefault = this.fieldDefault.trim();
/*     */     }
/* 548 */     return this.fieldDefault;
/*     */   }
/*     */ 
/*     */   public void setFieldDefault(String fieldDefault) {
/* 552 */     this.fieldDefault = fieldDefault;
/*     */   }
/*     */ 
/*     */   @Column(name="extend_json", nullable=true, length=500)
/*     */   public String getExtendJson()
/*     */   {
/* 558 */     return this.extendJson;
/*     */   }
/*     */ 
/*     */   public void setExtendJson(String extendJson) {
/* 562 */     this.extendJson = extendJson;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity
 * JD-Core Version:    0.6.2
 */