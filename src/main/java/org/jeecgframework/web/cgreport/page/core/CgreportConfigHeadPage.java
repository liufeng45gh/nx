/*     */ package org.jeecgframework.web.cgreport.page.core;
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
/*     */ import org.jeecgframework.web.cgreport.entity.core.CgreportConfigItemEntity;
/*     */ import org.jeecgframework.web.cgreport.entity.core.CgreportConfigParamEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jform_cgreport_head", schema="")
/*     */ public class CgreportConfigHeadPage
/*     */   implements Serializable
/*     */ {
/*  33 */   private List<CgreportConfigItemEntity> cgreportConfigItemList = new ArrayList();
/*     */ 
/*  35 */   private List<CgreportConfigParamEntity> cgreportConfigParamList = new ArrayList();
/*     */   private String id;
/*     */   private String code;
/*     */   private String name;
/*     */   private String cgrSql;
/*     */   private String content;
/*     */ 
/*     */   public List<CgreportConfigItemEntity> getCgreportConfigItemList()
/*     */   {
/*  38 */     return this.cgreportConfigItemList;
/*     */   }
/*     */   public void setCgreportConfigItemList(List<CgreportConfigItemEntity> cgreportConfigItemList) {
/*  41 */     this.cgreportConfigItemList = cgreportConfigItemList;
/*     */   }
/*     */   public List<CgreportConfigParamEntity> getCgreportConfigParamList() {
/*  44 */     return this.cgreportConfigParamList;
/*     */   }
/*     */ 
/*     */   public void setCgreportConfigParamList(List<CgreportConfigParamEntity> cgreportConfigParamList) {
/*  48 */     this.cgreportConfigParamList = cgreportConfigParamList;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  73 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  81 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CODE", nullable=false, length=36)
/*     */   public String getCode()
/*     */   {
/*  89 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  97 */     this.code = code;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=false, length=100)
/*     */   public String getName()
/*     */   {
/* 105 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 113 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="CGR_SQL", nullable=false, length=2000)
/*     */   public String getCgrSql()
/*     */   {
/* 121 */     return this.cgrSql;
/*     */   }
/*     */ 
/*     */   public void setCgrSql(String cgrSql)
/*     */   {
/* 129 */     this.cgrSql = cgrSql;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=false, length=1000)
/*     */   public String getContent()
/*     */   {
/* 137 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 145 */     this.content = content;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgreport.page.core.CgreportConfigHeadPage
 * JD-Core Version:    0.6.2
 */