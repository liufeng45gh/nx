/*     */ package org.jeecgframework.web.cgdynamgraph.entity.core;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jform_cgdynamgraph_head", schema="")
/*     */ public class CgDynamGraphConfigHeadEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String code;
/*     */   private String name;
/*     */   private String cgrSql;
/*     */   private String content;
/*     */   private Date createDate;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date updateDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*     */   private String dbSource;
/*     */   private String graphType;
/*     */   private String dataStructure;
/*     */   private String isPagination;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  69 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  77 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CODE", nullable=false, length=36)
/*     */   public String getCode()
/*     */   {
/*  85 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  93 */     this.code = code;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=false, length=100)
/*     */   public String getName()
/*     */   {
/* 101 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 109 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="CGR_SQL", nullable=false, length=2000)
/*     */   public String getCgrSql()
/*     */   {
/* 117 */     return this.cgrSql;
/*     */   }
/*     */ 
/*     */   public void setCgrSql(String cgrSql)
/*     */   {
/* 125 */     this.cgrSql = cgrSql;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=false, length=1000)
/*     */   public String getContent()
/*     */   {
/* 133 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 141 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="create_date", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/* 149 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 157 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="create_by", nullable=true, length=32)
/*     */   public String getCreateBy()
/*     */   {
/* 165 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 173 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="create_name", nullable=true, length=32)
/*     */   public String getCreateName()
/*     */   {
/* 181 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 189 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="update_date", nullable=true)
/*     */   public Date getUpdateDate()
/*     */   {
/* 197 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 205 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="update_by", nullable=true, length=32)
/*     */   public String getUpdateBy()
/*     */   {
/* 213 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 221 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="update_name", nullable=true, length=32)
/*     */   public String getUpdateName()
/*     */   {
/* 229 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 237 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="db_source", length=36)
/*     */   public String getDbSource() {
/* 242 */     return this.dbSource;
/*     */   }
/*     */ 
/*     */   public void setDbSource(String dbSource) {
/* 246 */     this.dbSource = dbSource;
/*     */   }
/*     */ 
/*     */   @Column(name="graph_type", length=36)
/*     */   public String getGraphType()
/*     */   {
/* 252 */     return this.graphType;
/*     */   }
/*     */ 
/*     */   public void setGraphType(String graphType) {
/* 256 */     this.graphType = graphType;
/*     */   }
/*     */ 
/*     */   @Column(name="data_structure", length=36)
/*     */   public String getDataStructure() {
/* 261 */     return this.dataStructure;
/*     */   }
/*     */ 
/*     */   public void setDataStructure(String dataStructure) {
/* 265 */     this.dataStructure = dataStructure;
/*     */   }
/*     */ 
/*     */   @Column(name="is_pagination", length=2)
/*     */   public String getIsPagination() {
/* 270 */     return this.isPagination;
/*     */   }
/*     */ 
/*     */   public void setIsPagination(String isPagination) {
/* 274 */     this.isPagination = isPagination;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigHeadEntity
 * JD-Core Version:    0.6.2
 */