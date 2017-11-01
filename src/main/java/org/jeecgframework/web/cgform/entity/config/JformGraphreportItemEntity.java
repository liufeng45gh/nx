/*     */ package org.jeecgframework.web.cgform.entity.config;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jform_graphreport_item", schema="")
/*     */ public class JformGraphreportItemEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="字段名")
/*     */   private String fieldName;
/*     */ 
/*     */   @Excel(name="字段文本")
/*     */   private String fieldTxt;
/*     */ 
/*     */   @Excel(name="排序")
/*     */   private Integer orderNum;
/*     */ 
/*     */   @Excel(name="字段类型")
/*     */   private String fieldType;
/*     */ 
/*     */   @Excel(name="是否显示")
/*     */   private String isShow;
/*     */ 
/*     */   @Excel(name="是否查询")
/*     */   private String searchFlag;
/*     */ 
/*     */   @Excel(name="查询模式")
/*     */   private String searchMode;
/*     */ 
/*     */   @Excel(name="字典Code")
/*     */   private String dictCode;
/*     */ 
/*     */   @Excel(name="显示图表")
/*     */   private String isGraph;
/*     */ 
/*     */   @Excel(name="图表类型")
/*     */   private String graphType;
/*     */ 
/*     */   @Excel(name="图表名称")
/*     */   private String graphName;
/*     */ 
/*     */   @Excel(name="标签名称")
/*     */   private String tabName;
/*     */ 
/*     */   @Excel(name="字段href")
/*     */   private String fieldHref;
/*     */ 
/*     */   @Excel(name="取值表达式")
/*     */   private String replaceVa;
/*     */   private String cgreportHeadId;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  89 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  97 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="FIELD_NAME", nullable=true, length=36)
/*     */   public String getFieldName()
/*     */   {
/* 105 */     return this.fieldName;
/*     */   }
/*     */ 
/*     */   public void setFieldName(String fieldName)
/*     */   {
/* 113 */     this.fieldName = fieldName;
/*     */   }
/*     */ 
/*     */   @Column(name="FIELD_TXT", nullable=true, length=1000)
/*     */   public String getFieldTxt()
/*     */   {
/* 121 */     return this.fieldTxt;
/*     */   }
/*     */ 
/*     */   public void setFieldTxt(String fieldTxt)
/*     */   {
/* 129 */     this.fieldTxt = fieldTxt;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_NUM", nullable=true, length=10)
/*     */   public Integer getOrderNum()
/*     */   {
/* 137 */     return this.orderNum;
/*     */   }
/*     */ 
/*     */   public void setOrderNum(Integer orderNum)
/*     */   {
/* 145 */     this.orderNum = orderNum;
/*     */   }
/*     */ 
/*     */   @Column(name="FIELD_TYPE", nullable=false, length=10)
/*     */   public String getFieldType()
/*     */   {
/* 153 */     return this.fieldType;
/*     */   }
/*     */ 
/*     */   public void setFieldType(String fieldType)
/*     */   {
/* 161 */     this.fieldType = fieldType;
/*     */   }
/*     */ 
/*     */   @Column(name="IS_SHOW", nullable=true, length=5)
/*     */   public String getIsShow()
/*     */   {
/* 169 */     return this.isShow;
/*     */   }
/*     */ 
/*     */   public void setIsShow(String isShow)
/*     */   {
/* 177 */     this.isShow = isShow;
/*     */   }
/*     */ 
/*     */   @Column(name="SEARCH_FLAG", nullable=true, length=2)
/*     */   public String getSearchFlag()
/*     */   {
/* 185 */     return this.searchFlag;
/*     */   }
/*     */ 
/*     */   public void setSearchFlag(String searchFlag)
/*     */   {
/* 193 */     this.searchFlag = searchFlag;
/*     */   }
/*     */ 
/*     */   @Column(name="SEARCH_MODE", nullable=true, length=10)
/*     */   public String getSearchMode()
/*     */   {
/* 201 */     return this.searchMode;
/*     */   }
/*     */ 
/*     */   public void setSearchMode(String searchMode)
/*     */   {
/* 209 */     this.searchMode = searchMode;
/*     */   }
/*     */ 
/*     */   @Column(name="DICT_CODE", nullable=true, length=500)
/*     */   public String getDictCode()
/*     */   {
/* 217 */     return this.dictCode;
/*     */   }
/*     */ 
/*     */   public void setDictCode(String dictCode)
/*     */   {
/* 225 */     this.dictCode = dictCode;
/*     */   }
/*     */ 
/*     */   @Column(name="IS_GRAPH", nullable=true, length=5)
/*     */   public String getIsGraph()
/*     */   {
/* 233 */     return this.isGraph;
/*     */   }
/*     */ 
/*     */   public void setIsGraph(String isGraph)
/*     */   {
/* 241 */     this.isGraph = isGraph;
/*     */   }
/*     */ 
/*     */   @Column(name="GRAPH_TYPE", nullable=true, length=50)
/*     */   public String getGraphType()
/*     */   {
/* 249 */     return this.graphType;
/*     */   }
/*     */ 
/*     */   public void setGraphType(String graphType)
/*     */   {
/* 257 */     this.graphType = graphType;
/*     */   }
/*     */ 
/*     */   @Column(name="GRAPH_NAME", nullable=true, length=100)
/*     */   public String getGraphName()
/*     */   {
/* 265 */     return this.graphName;
/*     */   }
/*     */ 
/*     */   public void setGraphName(String graphName)
/*     */   {
/* 273 */     this.graphName = graphName;
/*     */   }
/*     */ 
/*     */   @Column(name="TAB_NAME", nullable=true, length=50)
/*     */   public String getTabName()
/*     */   {
/* 281 */     return this.tabName;
/*     */   }
/*     */ 
/*     */   public void setTabName(String tabName)
/*     */   {
/* 289 */     this.tabName = tabName;
/*     */   }
/*     */ 
/*     */   @Column(name="FIELD_HREF", nullable=true, length=120)
/*     */   public String getFieldHref()
/*     */   {
/* 297 */     return this.fieldHref;
/*     */   }
/*     */ 
/*     */   public void setFieldHref(String fieldHref)
/*     */   {
/* 305 */     this.fieldHref = fieldHref;
/*     */   }
/*     */ 
/*     */   @Column(name="REPLACE_VA", nullable=true, length=36)
/*     */   public String getReplaceVa()
/*     */   {
/* 313 */     return this.replaceVa;
/*     */   }
/*     */ 
/*     */   public void setReplaceVa(String replaceVa)
/*     */   {
/* 321 */     this.replaceVa = replaceVa;
/*     */   }
/*     */ 
/*     */   @Column(name="CGREPORT_HEAD_ID", nullable=true, length=36)
/*     */   public String getCgreportHeadId()
/*     */   {
/* 329 */     return this.cgreportHeadId;
/*     */   }
/*     */ 
/*     */   public void setCgreportHeadId(String cgreportHeadId)
/*     */   {
/* 337 */     this.cgreportHeadId = cgreportHeadId;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.config.JformGraphreportItemEntity
 * JD-Core Version:    0.6.2
 */