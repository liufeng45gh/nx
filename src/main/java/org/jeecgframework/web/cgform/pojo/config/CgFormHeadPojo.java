/*     */ package org.jeecgframework.web.cgform.pojo.config;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CgFormHeadPojo
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String tableName;
/*     */   private String isTree;
/*     */   private String isPagination;
/*     */   private String isDbsynch;
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
/*     */ 
/*     */   public String getId()
/*     */   {
/*  62 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  70 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getTableName()
/*     */   {
/*  77 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   public void setTableName(String tableName)
/*     */   {
/*  84 */     this.tableName = tableName;
/*     */   }
/*     */ 
/*     */   public String getIsTree()
/*     */   {
/*  91 */     return this.isTree;
/*     */   }
/*     */ 
/*     */   public void setIsTree(String isTree)
/*     */   {
/*  99 */     this.isTree = isTree;
/*     */   }
/*     */ 
/*     */   public String getIsPagination()
/*     */   {
/* 106 */     return this.isPagination;
/*     */   }
/*     */ 
/*     */   public void setIsPagination(String isPagination)
/*     */   {
/* 114 */     this.isPagination = isPagination;
/*     */   }
/*     */ 
/*     */   public String getIsDbsynch()
/*     */   {
/* 121 */     return this.isDbsynch;
/*     */   }
/*     */ 
/*     */   public void setIsDbsynch(String isDbsynch)
/*     */   {
/* 129 */     this.isDbsynch = isDbsynch;
/*     */   }
/*     */ 
/*     */   public String getIsCheckbox()
/*     */   {
/* 136 */     return this.isCheckbox;
/*     */   }
/*     */ 
/*     */   public void setIsCheckbox(String isCheckbox)
/*     */   {
/* 144 */     this.isCheckbox = isCheckbox;
/*     */   }
/*     */ 
/*     */   public String getQuerymode()
/*     */   {
/* 151 */     return this.querymode;
/*     */   }
/*     */ 
/*     */   public void setQuerymode(String querymode)
/*     */   {
/* 159 */     this.querymode = querymode;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 166 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 174 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 181 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 189 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 196 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 204 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public String getCreateName()
/*     */   {
/* 211 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 219 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 226 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 234 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 241 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 249 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public String getUpdateName()
/*     */   {
/* 256 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 264 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   public String getJformVersion()
/*     */   {
/* 272 */     return this.jformVersion;
/*     */   }
/*     */ 
/*     */   public void setJformVersion(String jformVersion) {
/* 276 */     this.jformVersion = jformVersion;
/*     */   }
/*     */ 
/*     */   public Integer getJformType()
/*     */   {
/* 284 */     return this.jformType;
/*     */   }
/*     */ 
/*     */   public void setJformType(Integer jformType) {
/* 288 */     this.jformType = jformType;
/*     */   }
/*     */ 
/*     */   public String getJformPkType()
/*     */   {
/* 296 */     return this.jformPkType;
/*     */   }
/*     */ 
/*     */   public void setJformPkType(String jformPkType) {
/* 300 */     this.jformPkType = jformPkType;
/*     */   }
/*     */ 
/*     */   public String getJformPkSequence()
/*     */   {
/* 308 */     return this.jformPkSequence;
/*     */   }
/*     */ 
/*     */   public void setJformPkSequence(String jformPkSequence) {
/* 312 */     this.jformPkSequence = jformPkSequence;
/*     */   }
/*     */ 
/*     */   public String getSubTableStr() {
/* 316 */     return this.subTableStr;
/*     */   }
/*     */ 
/*     */   public void setSubTableStr(String subTableStr) {
/* 320 */     this.subTableStr = subTableStr;
/*     */   }
/*     */ 
/*     */   public Integer getRelationType()
/*     */   {
/* 329 */     return this.relationType;
/*     */   }
/*     */ 
/*     */   public void setRelationType(Integer relationType) {
/* 333 */     this.relationType = relationType;
/*     */   }
/*     */ 
/*     */   public Integer getTabOrder() {
/* 337 */     return this.tabOrder;
/*     */   }
/*     */ 
/*     */   public void setTabOrder(Integer tabOrder) {
/* 341 */     this.tabOrder = tabOrder;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.pojo.config.CgFormHeadPojo
 * JD-Core Version:    0.6.2
 */