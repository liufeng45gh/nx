/*     */ package org.jeecgframework.web.cgform.entity.config;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jeecgframework.poi.excel.annotation.ExcelCollection;
/*     */ import org.jeecgframework.poi.excel.annotation.ExcelEntity;
/*     */ import org.jeecgframework.poi.excel.annotation.ExcelIgnore;
/*     */ import org.jeecgframework.poi.excel.annotation.ExcelTarget;
/*     */ 
/*     */ @ExcelTarget("jformGraphreportHeadPage")
/*     */ public class JformGraphreportHeadPage
/*     */   implements Serializable
/*     */ {
/*     */ 
/*     */   @ExcelCollection(name="图表配置", orderNum="9")
/*  25 */   private List<JformGraphreportItemEntity> jformGraphreportItemList = new ArrayList();
/*     */ 
/*     */   @ExcelEntity
/*     */   private JformGraphreportHeadEntity jformGraphreportHeadEntity;
/*     */ 
/*     */   @ExcelIgnore
/*     */   private String id;
/*     */ 
/*     */   @ExcelIgnore
/*     */   private String name;
/*     */ 
/*     */   @ExcelIgnore
/*     */   private String code;
/*     */ 
/*     */   @ExcelIgnore
/*     */   private String cgrSql;
/*     */ 
/*     */   @ExcelIgnore
/*     */   private String content;
/*     */ 
/*     */   @ExcelIgnore
/*     */   private String ytext;
/*     */ 
/*     */   @ExcelIgnore
/*     */   private String categories;
/*     */ 
/*     */   @ExcelIgnore
/*     */   private String isShowList;
/*     */ 
/*     */   @ExcelIgnore
/*     */   private String xPageJs;
/*     */ 
/*  27 */   public List<JformGraphreportItemEntity> getJformGraphreportItemList() { return this.jformGraphreportItemList; }
/*     */ 
/*     */   public void setJformGraphreportItemList(List<JformGraphreportItemEntity> jformGraphreportItemList) {
/*  30 */     this.jformGraphreportItemList = jformGraphreportItemList;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  67 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  75 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  82 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  90 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  97 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 105 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getCgrSql()
/*     */   {
/* 112 */     return this.cgrSql;
/*     */   }
/*     */ 
/*     */   public void setCgrSql(String cgrSql)
/*     */   {
/* 120 */     this.cgrSql = cgrSql;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 127 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 135 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getYtext()
/*     */   {
/* 142 */     return this.ytext;
/*     */   }
/*     */ 
/*     */   public void setYtext(String ytext)
/*     */   {
/* 150 */     this.ytext = ytext;
/*     */   }
/*     */ 
/*     */   public String getCategories()
/*     */   {
/* 157 */     return this.categories;
/*     */   }
/*     */ 
/*     */   public void setCategories(String categories)
/*     */   {
/* 165 */     this.categories = categories;
/*     */   }
/*     */ 
/*     */   public String getIsShowList()
/*     */   {
/* 172 */     return this.isShowList;
/*     */   }
/*     */ 
/*     */   public void setIsShowList(String isShowList)
/*     */   {
/* 180 */     this.isShowList = isShowList;
/*     */   }
/*     */ 
/*     */   public String getXPageJs()
/*     */   {
/* 187 */     return this.xPageJs;
/*     */   }
/*     */ 
/*     */   public void setXPageJs(String xPageJs)
/*     */   {
/* 195 */     this.xPageJs = xPageJs;
/*     */   }
/*     */ 
/*     */   public JformGraphreportHeadPage() {
/*     */   }
/*     */ 
/*     */   public JformGraphreportHeadEntity getJformGraphreportHeadEntity() {
/* 202 */     return this.jformGraphreportHeadEntity;
/*     */   }
/*     */ 
/*     */   public void setJformGraphreportHeadEntity(JformGraphreportHeadEntity jformGraphreportHeadEntity) {
/* 206 */     this.jformGraphreportHeadEntity = jformGraphreportHeadEntity;
/*     */   }
/*     */ 
/*     */   public JformGraphreportHeadPage(List<JformGraphreportItemEntity> jformGraphreportItemList, JformGraphreportHeadEntity jformGraphreportHeadEntity) {
/* 210 */     this.jformGraphreportItemList = jformGraphreportItemList;
/* 211 */     this.jformGraphreportHeadEntity = jformGraphreportHeadEntity;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.config.JformGraphreportHeadPage
 * JD-Core Version:    0.6.2
 */