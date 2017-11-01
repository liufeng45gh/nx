/*     */ package org.jeecgframework.web.cgform.entity.config;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.DynamicInsert;
/*     */ import org.hibernate.annotations.DynamicUpdate;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.hibernate.annotations.OrderBy;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cgform_head", schema="")
/*     */ @DynamicUpdate(true)
/*     */ @DynamicInsert(true)
/*     */ public class CgFormHeadEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String tableName;
/*     */   private String isTree;
/*     */   private String isPagination;
/*     */   private String isDbSynch;
/*     */   private String isCheckbox;
/*     */   private String querymode;
/*     */   private String content;
/*     */   private Date createDate;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date updateDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*     */   private String jformVersion;
/*     */   private Integer jformType;
/*     */   private String jformPkType;
/*     */   private String jformPkSequence;
/*     */   private Integer relationType;
/*     */   private String subTableStr;
/*     */   private Integer tabOrder;
/*     */   private List<CgFormFieldEntity> columns;
/*     */   private List<CgFormIndexEntity> indexes;
/*     */   private String treeParentIdFieldName;
/*     */   private String treeIdFieldname;
/*     */   private String treeFieldname;
/*     */   private String jformCategory;
/*     */   private String formTemplate;
/*     */   private String formTemplateMobile;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="id", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/* 115 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 123 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="table_name", nullable=false, length=50)
/*     */   public String getTableName()
/*     */   {
/* 131 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   public void setTableName(String tableName)
/*     */   {
/* 138 */     this.tableName = tableName;
/*     */   }
/*     */ 
/*     */   @Column(name="is_tree", nullable=false, length=5)
/*     */   public String getIsTree()
/*     */   {
/* 146 */     return this.isTree;
/*     */   }
/*     */ 
/*     */   public void setIsTree(String isTree)
/*     */   {
/* 154 */     this.isTree = isTree;
/*     */   }
/*     */ 
/*     */   @Column(name="is_pagination", nullable=false, length=5)
/*     */   public String getIsPagination()
/*     */   {
/* 162 */     return this.isPagination;
/*     */   }
/*     */ 
/*     */   public void setIsPagination(String isPagination)
/*     */   {
/* 170 */     this.isPagination = isPagination;
/*     */   }
/*     */ 
/*     */   @Column(name="is_dbsynch", nullable=false, length=20)
/*     */   public String getIsDbSynch()
/*     */   {
/* 178 */     return this.isDbSynch;
/*     */   }
/*     */ 
/*     */   public void setIsDbSynch(String isDbSynch)
/*     */   {
/* 186 */     this.isDbSynch = isDbSynch;
/*     */   }
/*     */ 
/*     */   @Column(name="is_checkbox", nullable=false, length=5)
/*     */   public String getIsCheckbox()
/*     */   {
/* 194 */     return this.isCheckbox;
/*     */   }
/*     */ 
/*     */   public void setIsCheckbox(String isCheckbox)
/*     */   {
/* 202 */     this.isCheckbox = isCheckbox;
/*     */   }
/*     */ 
/*     */   @Column(name="querymode", nullable=false, length=10)
/*     */   public String getQuerymode()
/*     */   {
/* 210 */     return this.querymode;
/*     */   }
/*     */ 
/*     */   public void setQuerymode(String querymode)
/*     */   {
/* 218 */     this.querymode = querymode;
/*     */   }
/*     */ 
/*     */   @Column(name="content", nullable=false, length=200)
/*     */   public String getContent()
/*     */   {
/* 226 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 234 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="create_date", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/* 242 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 250 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="create_by", nullable=true, length=32)
/*     */   public String getCreateBy()
/*     */   {
/* 258 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 266 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="create_name", nullable=true, length=32)
/*     */   public String getCreateName()
/*     */   {
/* 274 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 282 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="update_date", nullable=true)
/*     */   public Date getUpdateDate()
/*     */   {
/* 290 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 298 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="update_by", nullable=true, length=32)
/*     */   public String getUpdateBy()
/*     */   {
/* 306 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 314 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="update_name", nullable=true, length=32)
/*     */   public String getUpdateName()
/*     */   {
/* 322 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 330 */     this.updateName = updateName;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.REMOVE}, mappedBy="table")
/*     */   @OrderBy(clause="orderNum asc")
/*     */   public List<CgFormFieldEntity> getColumns() {
/* 336 */     return this.columns;
/*     */   }
/*     */ 
/*     */   public void setColumns(List<CgFormFieldEntity> columns) {
/* 340 */     this.columns = columns;
/*     */   }
/*     */ 
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.REMOVE}, mappedBy="table")
/*     */   public List<CgFormIndexEntity> getIndexes() {
/* 345 */     return this.indexes;
/*     */   }
/*     */ 
/*     */   public void setIndexes(List<CgFormIndexEntity> indexes) {
/* 349 */     this.indexes = indexes;
/*     */   }
/*     */ 
/*     */   @Column(name="jform_version", nullable=false, length=10)
/*     */   public String getJformVersion()
/*     */   {
/* 358 */     return this.jformVersion;
/*     */   }
/*     */ 
/*     */   public void setJformVersion(String jformVersion) {
/* 362 */     this.jformVersion = jformVersion;
/*     */   }
/*     */ 
/*     */   @Column(name="jform_type", nullable=false, length=1)
/*     */   public Integer getJformType()
/*     */   {
/* 371 */     return this.jformType;
/*     */   }
/*     */ 
/*     */   public void setJformType(Integer jformType) {
/* 375 */     this.jformType = jformType;
/*     */   }
/*     */ 
/*     */   @Column(name="jform_pk_type", nullable=true, length=100)
/*     */   public String getJformPkType()
/*     */   {
/* 384 */     return this.jformPkType;
/*     */   }
/*     */ 
/*     */   public void setJformPkType(String jformPkType) {
/* 388 */     this.jformPkType = jformPkType;
/*     */   }
/*     */ 
/*     */   @Column(name="jform_pk_sequence", nullable=true, length=200)
/*     */   public String getJformPkSequence()
/*     */   {
/* 397 */     return this.jformPkSequence;
/*     */   }
/*     */ 
/*     */   public void setJformPkSequence(String jformPkSequence) {
/* 401 */     this.jformPkSequence = jformPkSequence;
/*     */   }
/*     */ 
/*     */   @Column(name="sub_table_str", nullable=true, length=1000)
/*     */   public String getSubTableStr() {
/* 406 */     return this.subTableStr;
/*     */   }
/*     */ 
/*     */   public void setSubTableStr(String subTableStr) {
/* 410 */     this.subTableStr = subTableStr;
/*     */   }
/*     */ 
/*     */   @Column(name="relation_type", nullable=true, length=1)
/*     */   public Integer getRelationType()
/*     */   {
/* 420 */     return this.relationType;
/*     */   }
/*     */ 
/*     */   public void setRelationType(Integer relationType) {
/* 424 */     this.relationType = relationType;
/*     */   }
/*     */ 
/*     */   @Column(name="tab_order", nullable=true, length=1)
/*     */   public Integer getTabOrder() {
/* 429 */     return this.tabOrder;
/*     */   }
/*     */ 
/*     */   public void setTabOrder(Integer tabOrder) {
/* 433 */     this.tabOrder = tabOrder;
/*     */   }
/*     */ 
/*     */   @Column(name="tree_parentid_fieldname", nullable=true, length=50)
/*     */   public String getTreeParentIdFieldName() {
/* 438 */     return this.treeParentIdFieldName;
/*     */   }
/*     */ 
/*     */   public void setTreeParentIdFieldName(String treeParentIdFieldName) {
/* 442 */     this.treeParentIdFieldName = treeParentIdFieldName;
/*     */   }
/*     */ 
/*     */   @Column(name="tree_id_fieldname", nullable=true, length=50)
/*     */   public String getTreeIdFieldname() {
/* 447 */     return this.treeIdFieldname;
/*     */   }
/*     */ 
/*     */   public void setTreeIdFieldname(String treeIdFieldname) {
/* 451 */     this.treeIdFieldname = treeIdFieldname;
/*     */   }
/*     */ 
/*     */   @Column(name="tree_fieldname", nullable=true, length=50)
/*     */   public String getTreeFieldname() {
/* 456 */     return this.treeFieldname;
/*     */   }
/*     */ 
/*     */   public void setTreeFieldname(String treeFieldname) {
/* 460 */     this.treeFieldname = treeFieldname;
/*     */   }
/*     */ 
/*     */   @Column(name="jform_category", nullable=false, length=50)
/*     */   public String getJformCategory()
/*     */   {
/* 468 */     return this.jformCategory;
/*     */   }
/*     */ 
/*     */   public void setJformCategory(String jformCategory) {
/* 472 */     this.jformCategory = jformCategory;
/*     */   }
/*     */ 
/*     */   @Column(name="form_template", length=50)
/*     */   public String getFormTemplate() {
/* 477 */     return this.formTemplate;
/*     */   }
/*     */ 
/*     */   public void setFormTemplate(String formTemplate) {
/* 481 */     this.formTemplate = formTemplate;
/*     */   }
/*     */ 
/*     */   @Column(name="form_template_mobile", length=50)
/*     */   public String getFormTemplateMobile() {
/* 486 */     return this.formTemplateMobile;
/*     */   }
/*     */ 
/*     */   public void setFormTemplateMobile(String formTemplateMobile) {
/* 490 */     this.formTemplateMobile = formTemplateMobile;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity
 * JD-Core Version:    0.6.2
 */