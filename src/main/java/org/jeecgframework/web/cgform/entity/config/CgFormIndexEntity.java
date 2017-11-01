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
/*     */ import org.codehaus.jackson.annotate.JsonIgnore;
/*     */ import org.hibernate.annotations.ForeignKey;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="cgform_index", schema="")
/*     */ public class CgFormIndexEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private Date updateDate;
/*     */ 
/*     */   @Excel(name="索引名称")
/*     */   private String indexName;
/*     */ 
/*     */   @Excel(name="索引栏位")
/*     */   private String indexField;
/*     */ 
/*     */   @Excel(name="索引类型")
/*     */   private String indexType;
/*     */   private CgFormHeadEntity table;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  78 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  86 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  94 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 102 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/* 110 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 118 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 126 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 134 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 142 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 150 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 158 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 166 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 174 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 182 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="INDEX_NAME", nullable=true, length=100)
/*     */   public String getIndexName()
/*     */   {
/* 190 */     return this.indexName;
/*     */   }
/*     */ 
/*     */   public void setIndexName(String indexName)
/*     */   {
/* 198 */     this.indexName = indexName;
/*     */   }
/*     */ 
/*     */   @Column(name="INDEX_FIELD", nullable=true, length=500)
/*     */   public String getIndexField()
/*     */   {
/* 206 */     return this.indexField;
/*     */   }
/*     */ 
/*     */   public void setIndexField(String indexField)
/*     */   {
/* 214 */     this.indexField = indexField;
/*     */   }
/*     */ 
/*     */   @Column(name="INDEX_TYPE", nullable=true, length=32)
/*     */   public String getIndexType()
/*     */   {
/* 222 */     return this.indexType;
/*     */   }
/*     */ 
/*     */   public void setIndexType(String indexType)
/*     */   {
/* 230 */     this.indexType = indexType;
/*     */   }
/*     */ 
/*     */   @ManyToOne
/*     */   @JoinColumn(name="table_id", nullable=false, referencedColumnName="id")
/*     */   @JsonIgnore
/*     */   @ForeignKey(name="null")
/*     */   public CgFormHeadEntity getTable() {
/* 241 */     return this.table;
/*     */   }
/*     */ 
/*     */   public void setTable(CgFormHeadEntity table)
/*     */   {
/* 249 */     this.table = table;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.config.CgFormIndexEntity
 * JD-Core Version:    0.6.2
 */