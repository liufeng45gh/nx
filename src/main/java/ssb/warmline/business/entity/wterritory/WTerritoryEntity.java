/*     */ package ssb.warmline.business.entity.wterritory;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.ForeignKey;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="w_territory", schema="")
/*     */ public class WTerritoryEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="区域编码")
/*     */   private String territorycode;
/*     */ 
/*     */   @Excel(name="区域等级")
/*     */   private Integer territorylevel;
/*     */ 
/*     */   @Excel(name="区域名字")
/*     */   private String territoryname;
/*     */ 
/*     */   @Excel(name="区域名字拼音或英文")
/*     */   private String territoryPinyin;
/*     */ 
/*     */   @Excel(name="区域名称方言")
/*     */   private String territorydialects;
/*     */ 
/*     */   @Excel(name="区域排序")
/*     */   private Integer territorysort;
/*     */ 
/*     */   @Excel(name="坐标纬线")
/*     */   private Double xwgs84;
/*     */ 
/*     */   @Excel(name="坐标经线")
/*     */   private Double ywgs84;
/*     */ 
/*     */   @Excel(name="是否为父节点")
/*     */   private String isparent;
/*     */ 
/*     */   @Excel(name="是否展开节点")
/*     */   private String open;
/*     */ 
/*     */   @Excel(name="电话区号")
/*     */   private String areacode;
/*     */ 
/*     */   @Excel(name="区域首字母")
/*     */   private String first;
/*     */ 
/*     */   @Excel(name="境内或者境外标识")
/*     */   private String out_in;
/*     */ 
/*     */   @Excel(name="是否为热门城市")
/*     */   private String ishotcity;
/*     */   private WTerritoryEntity WTerritoryEntity;
/*  81 */   private List<WTerritoryEntity> WTerritoryEntitys = new ArrayList();
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  93 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 103 */     this.id = id;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="territoryparentid")
/*     */   @ForeignKey(name="null")
/*     */   public WTerritoryEntity getWTerritoryEntity() {
/* 111 */     return this.WTerritoryEntity;
/*     */   }
/*     */ 
/*     */   public void setWTerritoryEntity(WTerritoryEntity WTerritoryEntity) {
/* 115 */     this.WTerritoryEntity = WTerritoryEntity;
/*     */   }
/*     */ 
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="WTerritoryEntity")
/*     */   public List<WTerritoryEntity> getWTerritoryEntitys() {
/* 120 */     return this.WTerritoryEntitys;
/*     */   }
/*     */ 
/*     */   public void setWTerritoryEntitys(List<WTerritoryEntity> WTerritoryEntitys) {
/* 124 */     this.WTerritoryEntitys = WTerritoryEntitys;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORYCODE", nullable=false, length=50)
/*     */   public String getTerritorycode()
/*     */   {
/* 134 */     return this.territorycode;
/*     */   }
/*     */ 
/*     */   public void setTerritorycode(String territorycode)
/*     */   {
/* 144 */     this.territorycode = territorycode;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORYLEVEL", nullable=false, length=10)
/*     */   public Integer getTerritorylevel()
/*     */   {
/* 154 */     return this.territorylevel;
/*     */   }
/*     */ 
/*     */   public void setTerritorylevel(Integer territorylevel)
/*     */   {
/* 164 */     this.territorylevel = territorylevel;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORYNAME", nullable=false, length=50)
/*     */   public String getTerritoryname()
/*     */   {
/* 174 */     return this.territoryname;
/*     */   }
/*     */ 
/*     */   public void setTerritoryname(String territoryname)
/*     */   {
/* 184 */     this.territoryname = territoryname;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORY_PINYIN", nullable=true, length=40)
/*     */   public String getTerritoryPinyin()
/*     */   {
/* 194 */     return this.territoryPinyin;
/*     */   }
/*     */ 
/*     */   public void setTerritoryPinyin(String territoryPinyin)
/*     */   {
/* 204 */     this.territoryPinyin = territoryPinyin;
/*     */   }
/*     */   @Column(name="TERRITORY_DIALECTS", nullable=true, length=40)
/*     */   public String getTerritorydialects() {
/* 208 */     return this.territorydialects;
/*     */   }
/*     */ 
/*     */   public void setTerritorydialects(String territorydialects)
/*     */   {
/* 217 */     this.territorydialects = territorydialects;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORYSORT", nullable=false, length=50)
/*     */   public Integer getTerritorysort()
/*     */   {
/* 227 */     return this.territorysort;
/*     */   }
/*     */ 
/*     */   public void setTerritorysort(Integer territorysort)
/*     */   {
/* 237 */     this.territorysort = territorysort;
/*     */   }
/*     */ 
/*     */   @Column(name="ISPARENT", nullable=true, length=50)
/*     */   public String getIsparent()
/*     */   {
/* 247 */     return this.isparent;
/*     */   }
/*     */ 
/*     */   public void setIsparent(String isparent)
/*     */   {
/* 257 */     this.isparent = isparent;
/*     */   }
/*     */ 
/*     */   @Column(name="OPEN", nullable=true, length=32)
/*     */   public String getOpen()
/*     */   {
/* 267 */     return this.open;
/*     */   }
/*     */ 
/*     */   public void setOpen(String open)
/*     */   {
/* 277 */     this.open = open;
/*     */   }
/*     */ 
/*     */   @Column(name="AREA_CODE", nullable=true, length=30)
/*     */   public String getAreacode()
/*     */   {
/* 287 */     return this.areacode;
/*     */   }
/*     */ 
/*     */   public void setAreacode(String areacode)
/*     */   {
/* 296 */     this.areacode = areacode;
/*     */   }
/*     */ 
/*     */   @Column(name="XWGS84", nullable=false, length=22)
/*     */   public Double getXwgs84()
/*     */   {
/* 306 */     return this.xwgs84;
/*     */   }
/*     */ 
/*     */   public void setXwgs84(Double xwgs84)
/*     */   {
/* 316 */     this.xwgs84 = xwgs84;
/*     */   }
/*     */ 
/*     */   @Column(name="YWGS84", nullable=false, length=22)
/*     */   public Double getYwgs84()
/*     */   {
/* 326 */     return this.ywgs84;
/*     */   }
/*     */ 
/*     */   public void setYwgs84(Double ywgs84)
/*     */   {
/* 335 */     this.ywgs84 = ywgs84;
/*     */   }
/*     */ 
/*     */   @Column(name="FIRST", nullable=true, length=30)
/*     */   public String getFirst() {
/* 340 */     return this.first;
/*     */   }
/*     */ 
/*     */   public void setFirst(String first) {
/* 344 */     this.first = first;
/*     */   }
/*     */ 
/*     */   @Column(name="OUT_IN", nullable=true, length=30)
/*     */   public String getOut_in() {
/* 349 */     return this.out_in;
/*     */   }
/*     */ 
/*     */   public void setOut_in(String out_in) {
/* 353 */     this.out_in = out_in;
/*     */   }
/*     */ 
/*     */   @Column(name="ishotcity", length=30)
/*     */   public String getIshotcity() {
/* 358 */     return this.ishotcity;
/*     */   }
/*     */ 
/*     */   public void setIshotcity(String ishotcity) {
/* 362 */     this.ishotcity = ishotcity;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wterritory.WTerritoryEntity
 * JD-Core Version:    0.6.2
 */