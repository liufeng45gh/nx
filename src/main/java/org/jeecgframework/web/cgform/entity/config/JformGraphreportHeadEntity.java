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
/*     */ @Table(name="jform_graphreport_head", schema="")
/*     */ public class JformGraphreportHeadEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="名称")
/*     */   private String name;
/*     */ 
/*     */   @Excel(name="编码")
/*     */   private String code;
/*     */ 
/*     */   @Excel(name="查询数据SQL")
/*     */   private String cgrSql;
/*     */ 
/*     */   @Excel(name="描述")
/*     */   private String content;
/*     */ 
/*     */   @Excel(name="y轴文字")
/*     */   private String ytext;
/*     */ 
/*     */   @Excel(name="x轴数据")
/*     */   private String categories;
/*     */ 
/*     */   @Excel(name="是否显示明细")
/*     */   private String isShowList;
/*     */ 
/*     */   @Excel(name="扩展JS")
/*     */   private String xpageJs;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="id", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  70 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  78 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=100)
/*     */   public String getName()
/*     */   {
/*  88 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  96 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="code", nullable=false, length=36)
/*     */   public String getCode()
/*     */   {
/* 106 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 114 */     this.code = code;
/*     */   }
/*     */ 
/*     */   @Column(name="cgr_sql", nullable=false, length=2000)
/*     */   public String getCgrSql()
/*     */   {
/* 124 */     return this.cgrSql;
/*     */   }
/*     */ 
/*     */   public void setCgrSql(String cgrSql)
/*     */   {
/* 132 */     this.cgrSql = cgrSql;
/*     */   }
/*     */ 
/*     */   @Column(name="content", nullable=false, length=1000)
/*     */   public String getContent()
/*     */   {
/* 142 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 150 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="ytext", nullable=false, length=100)
/*     */   public String getYtext()
/*     */   {
/* 160 */     return this.ytext;
/*     */   }
/*     */ 
/*     */   public void setYtext(String ytext)
/*     */   {
/* 168 */     this.ytext = ytext;
/*     */   }
/*     */ 
/*     */   @Column(name="categories", nullable=false, length=1000)
/*     */   public String getCategories()
/*     */   {
/* 178 */     return this.categories;
/*     */   }
/*     */ 
/*     */   public void setCategories(String categories)
/*     */   {
/* 186 */     this.categories = categories;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show_list", nullable=true, length=5)
/*     */   public String getIsShowList()
/*     */   {
/* 196 */     return this.isShowList;
/*     */   }
/*     */ 
/*     */   public void setIsShowList(String isShowList)
/*     */   {
/* 204 */     this.isShowList = isShowList;
/*     */   }
/*     */ 
/*     */   @Column(name="x_page_js", nullable=true, length=1000)
/*     */   public String getXpageJs()
/*     */   {
/* 213 */     return this.xpageJs;
/*     */   }
/*     */ 
/*     */   public void setXpageJs(String xpageJs) {
/* 217 */     this.xpageJs = xpageJs;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.config.JformGraphreportHeadEntity
 * JD-Core Version:    0.6.2
 */