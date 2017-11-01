/*     */ package ssb.warmline.business.entity.wip;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="w_ip", schema="")
/*     */ public class WIpEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="phone")
/*     */   private String phone;
/*     */ 
/*     */   @Excel(name="ip")
/*     */   private String ip;
/*     */ 
/*     */   @Excel(name="count")
/*     */   private Integer count;
/*     */ 
/*     */   @Excel(name="date", format="yyyy-MM-dd")
/*     */   private Date date;
/*     */ 
/*     */   @Excel(name="type")
/*     */   private String type;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.AUTO)
/*     */   @Column(name="ID", nullable=false, length=10)
/*     */   public String getId()
/*     */   {
/*  59 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  67 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="PHONE", nullable=true, length=20)
/*     */   public String getPhone()
/*     */   {
/*  75 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/*  83 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   @Column(name="IP", nullable=true, length=20)
/*     */   public String getIp()
/*     */   {
/*  91 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip)
/*     */   {
/*  99 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   @Column(name="COUNT", nullable=true, length=10)
/*     */   public Integer getCount()
/*     */   {
/* 107 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(Integer count)
/*     */   {
/* 115 */     this.count = count;
/*     */   }
/*     */ 
/*     */   @Column(name="DATE", nullable=true)
/*     */   public Date getDate()
/*     */   {
/* 123 */     return this.date;
/*     */   }
/*     */ 
/*     */   public void setDate(Date date)
/*     */   {
/* 131 */     this.date = date;
/*     */   }
/*     */ 
/*     */   @Column(name="TYPE", nullable=true, length=255)
/*     */   public String getType()
/*     */   {
/* 139 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 147 */     this.type = type;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wip.WIpEntity
 * JD-Core Version:    0.6.2
 */