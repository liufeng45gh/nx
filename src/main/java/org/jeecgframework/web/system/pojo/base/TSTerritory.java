/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.ForeignKey;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_territory")
/*     */ public class TSTerritory extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private TSTerritory TSTerritory;
/*     */   private String territoryName;
/*     */   private Short territoryLevel;
/*     */   private String territorySort;
/*     */   private String territoryCode;
/*     */   private String territoryPinyin;
/*     */   private double xwgs84;
/*     */   private double ywgs84;
/*  32 */   private List<TSTerritory> TSTerritorys = new ArrayList();
/*     */ 
/*  38 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="territoryparentid")
/*     */   @ForeignKey(name="null")
/*     */   public TSTerritory getTSTerritory() { return this.TSTerritory; }
/*     */ 
/*     */   public void setTSTerritory(TSTerritory TSTerritory) {
/*  41 */     this.TSTerritory = TSTerritory;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TSTerritory")
/*     */   public List<TSTerritory> getTSTerritorys() {
/*  45 */     return this.TSTerritorys;
/*     */   }
/*     */   public void setTSTerritorys(List<TSTerritory> TSTerritorys) {
/*  48 */     this.TSTerritorys = TSTerritorys;
/*     */   }
/*     */   @Column(name="territoryname", nullable=false, length=50)
/*     */   public String getTerritoryName() {
/*  52 */     return this.territoryName;
/*     */   }
/*     */ 
/*     */   public void setTerritoryName(String territoryName) {
/*  56 */     this.territoryName = territoryName;
/*     */   }
/*     */ 
/*     */   @Column(name="territorysort", nullable=false, length=3)
/*     */   public String getTerritorySort() {
/*  61 */     return this.territorySort;
/*     */   }
/*     */ 
/*     */   public void setTerritorySort(String territorySort) {
/*  65 */     this.territorySort = territorySort;
/*     */   }
/*     */   @Column(name="territorylevel", nullable=false, length=1)
/*     */   public Short getTerritoryLevel() {
/*  69 */     return this.territoryLevel;
/*     */   }
/*     */   public void setTerritoryLevel(Short territoryLevel) {
/*  72 */     this.territoryLevel = territoryLevel;
/*     */   }
/*     */   @Column(name="territorycode", nullable=false, length=10)
/*     */   public String getTerritoryCode() {
/*  76 */     return this.territoryCode;
/*     */   }
/*     */   public void setTerritoryCode(String territoryCode) {
/*  79 */     this.territoryCode = territoryCode;
/*     */   }
/*     */   @Column(name="territory_pinyin", nullable=true, length=40)
/*     */   public String getTerritoryPinyin() {
/*  83 */     return this.territoryPinyin;
/*     */   }
/*     */   public void setTerritoryPinyin(String territoryPinyin) {
/*  86 */     this.territoryPinyin = territoryPinyin;
/*     */   }
/*     */   @Column(name="x_wgs84", nullable=false, length=40)
/*     */   public double getXwgs84() {
/*  90 */     return this.xwgs84;
/*     */   }
/*     */   public void setXwgs84(double xwgs84) {
/*  93 */     this.xwgs84 = xwgs84;
/*     */   }
/*     */   @Column(name="y_wgs84", nullable=false, length=40)
/*     */   public double getYwgs84() {
/*  97 */     return this.ywgs84;
/*     */   }
/*     */   public void setYwgs84(double ywgs84) {
/* 100 */     this.ywgs84 = ywgs84;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSTerritory
 * JD-Core Version:    0.6.2
 */