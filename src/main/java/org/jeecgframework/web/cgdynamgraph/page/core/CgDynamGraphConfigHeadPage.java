/*     */ package org.jeecgframework.web.cgdynamgraph.page.core;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigItemEntity;
/*     */ import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigParamEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jform_cgdynamgraph_head", schema="")
/*     */ public class CgDynamGraphConfigHeadPage
/*     */   implements Serializable
/*     */ {
/*  29 */   private List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList = new ArrayList();
/*     */ 
/*  31 */   private List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList = new ArrayList();
/*     */   private String id;
/*     */   private String code;
/*     */   private String name;
/*     */   private String cgrSql;
/*     */   private String content;
/*     */   private String graphType;
/*     */   private String dataStructure;
/*     */   private String isPagination;
/*     */ 
/*     */   public List<CgDynamGraphConfigItemEntity> getCgDynamGraphConfigItemList()
/*     */   {
/*  34 */     return this.cgDynamGraphConfigItemList;
/*     */   }
/*     */   public void setCgDynamGraphConfigItemList(List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList) {
/*  37 */     this.cgDynamGraphConfigItemList = cgDynamGraphConfigItemList;
/*     */   }
/*     */   public List<CgDynamGraphConfigParamEntity> getCgDynamGraphConfigParamList() {
/*  40 */     return this.cgDynamGraphConfigParamList;
/*     */   }
/*     */ 
/*     */   public void setCgDynamGraphConfigParamList(List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList) {
/*  44 */     this.cgDynamGraphConfigParamList = cgDynamGraphConfigParamList;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  79 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  87 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CODE", nullable=false, length=36)
/*     */   public String getCode()
/*     */   {
/*  95 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 103 */     this.code = code;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=false, length=100)
/*     */   public String getName()
/*     */   {
/* 111 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 119 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="CGR_SQL", nullable=false, length=2000)
/*     */   public String getCgrSql()
/*     */   {
/* 127 */     return this.cgrSql;
/*     */   }
/*     */ 
/*     */   public void setCgrSql(String cgrSql)
/*     */   {
/* 135 */     this.cgrSql = cgrSql;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=false, length=1000)
/*     */   public String getContent()
/*     */   {
/* 143 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 151 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="graph_type", length=36)
/*     */   public String getGraphType() {
/* 156 */     return this.graphType;
/*     */   }
/*     */ 
/*     */   public void setGraphType(String graphType) {
/* 160 */     this.graphType = graphType;
/*     */   }
/*     */ 
/*     */   @Column(name="data_structure", length=36)
/*     */   public String getDataStructure() {
/* 165 */     return this.dataStructure;
/*     */   }
/*     */ 
/*     */   public void setDataStructure(String dataStructure) {
/* 169 */     this.dataStructure = dataStructure;
/*     */   }
/*     */ 
/*     */   @Column(name="is_pagination", length=2)
/*     */   public String getIsPagination() {
/* 174 */     return this.isPagination;
/*     */   }
/*     */ 
/*     */   public void setIsPagination(String isPagination) {
/* 178 */     this.isPagination = isPagination;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgdynamgraph.page.core.CgDynamGraphConfigHeadPage
 * JD-Core Version:    0.6.2
 */