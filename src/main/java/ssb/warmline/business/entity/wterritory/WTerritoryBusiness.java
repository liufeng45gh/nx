/*     */ package ssb.warmline.business.entity.wterritory;
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
/*     */ @Table(name="w_territory", schema="")
/*     */ public class WTerritoryBusiness
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
/*     */   @Excel(name="区域父id")
/*     */   private String territoryparentid;
/*     */ 
/*     */   @Excel(name="区域首字母")
/*     */   private String first;
/*     */ 
/*     */   @Excel(name="境内或者境外标识")
/*     */   private String out_in;
/*     */ 
/*     */   @Excel(name="是否为热门城市")
/*     */   private String ishotcity;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  82 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  92 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORYCODE", nullable=false, length=10)
/*     */   public String getTerritorycode()
/*     */   {
/* 102 */     return this.territorycode;
/*     */   }
/*     */ 
/*     */   public void setTerritorycode(String territorycode)
/*     */   {
/* 112 */     this.territorycode = territorycode;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORYLEVEL", nullable=false, length=10)
/*     */   public Integer getTerritorylevel()
/*     */   {
/* 122 */     return this.territorylevel;
/*     */   }
/*     */ 
/*     */   public void setTerritorylevel(Integer territorylevel)
/*     */   {
/* 132 */     this.territorylevel = territorylevel;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORYNAME", nullable=false, length=50)
/*     */   public String getTerritoryname()
/*     */   {
/* 142 */     return this.territoryname;
/*     */   }
/*     */ 
/*     */   public void setTerritoryname(String territoryname)
/*     */   {
/* 152 */     this.territoryname = territoryname;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORY_PINYIN", nullable=true, length=40)
/*     */   public String getTerritoryPinyin()
/*     */   {
/* 162 */     return this.territoryPinyin;
/*     */   }
/*     */ 
/*     */   public void setTerritoryPinyin(String territoryPinyin)
/*     */   {
/* 172 */     this.territoryPinyin = territoryPinyin;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORY_DIALECTS", nullable=true, length=40)
/*     */   public String getTerritorydialects() {
/* 177 */     return this.territorydialects;
/*     */   }
/*     */ 
/*     */   public void setTerritorydialects(String territorydialects)
/*     */   {
/* 186 */     this.territorydialects = territorydialects;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORYSORT", nullable=false, length=50)
/*     */   public Integer getTerritorysort()
/*     */   {
/* 196 */     return this.territorysort;
/*     */   }
/*     */ 
/*     */   public void setTerritorysort(Integer territorysort)
/*     */   {
/* 206 */     this.territorysort = territorysort;
/*     */   }
/*     */ 
/*     */   @Column(name="ISPARENT", nullable=true, length=50)
/*     */   public String getIsparent()
/*     */   {
/* 216 */     return this.isparent;
/*     */   }
/*     */ 
/*     */   public void setIsparent(String isparent)
/*     */   {
/* 226 */     this.isparent = isparent;
/*     */   }
/*     */ 
/*     */   @Column(name="OPEN", nullable=true, length=32)
/*     */   public String getOpen()
/*     */   {
/* 236 */     return this.open;
/*     */   }
/*     */ 
/*     */   public void setOpen(String open)
/*     */   {
/* 246 */     this.open = open;
/*     */   }
/*     */ 
/*     */   @Column(name="AREA_CODE", nullable=true, length=30)
/*     */   public String getAreacode()
/*     */   {
/* 256 */     return this.areacode;
/*     */   }
/*     */ 
/*     */   public void setAreacode(String areacode)
/*     */   {
/* 265 */     this.areacode = areacode;
/*     */   }
/*     */ 
/*     */   @Column(name="XWGS84", nullable=false, length=22)
/*     */   public Double getXwgs84()
/*     */   {
/* 275 */     return this.xwgs84;
/*     */   }
/*     */ 
/*     */   public void setXwgs84(Double xwgs84)
/*     */   {
/* 285 */     this.xwgs84 = xwgs84;
/*     */   }
/*     */ 
/*     */   @Column(name="YWGS84", nullable=false, length=22)
/*     */   public Double getYwgs84()
/*     */   {
/* 295 */     return this.ywgs84;
/*     */   }
/*     */ 
/*     */   public void setYwgs84(Double ywgs84)
/*     */   {
/* 304 */     this.ywgs84 = ywgs84;
/*     */   }
/*     */ 
/*     */   @Column(name="FIRST", nullable=true, length=30)
/*     */   public String getFirst() {
/* 309 */     return this.first;
/*     */   }
/*     */ 
/*     */   public void setFirst(String first) {
/* 313 */     this.first = first;
/*     */   }
/*     */ 
/*     */   @Column(name="OUT_IN", nullable=true, length=30)
/*     */   public String getOut_in() {
/* 318 */     return this.out_in;
/*     */   }
/*     */ 
/*     */   public void setOut_in(String out_in) {
/* 322 */     this.out_in = out_in;
/*     */   }
/*     */ 
/*     */   @Column(name="TERRITORYPARENTID", nullable=false, length=32)
/*     */   public String getTerritoryparentid() {
/* 327 */     return this.territoryparentid;
/*     */   }
/*     */ 
/*     */   public void setTerritoryparentid(String territoryparentid)
/*     */   {
/* 337 */     this.territoryparentid = territoryparentid;
/*     */   }
/*     */ 
/*     */   @Column(name="ishotcity", length=30)
/*     */   public String getIshotcity() {
/* 342 */     return this.ishotcity;
/*     */   }
/*     */ 
/*     */   public void setIshotcity(String ishotcity) {
/* 346 */     this.ishotcity = ishotcity;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wterritory.WTerritoryBusiness
 * JD-Core Version:    0.6.2
 */