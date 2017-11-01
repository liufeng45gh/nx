/*     */ package ssb.warmline.business.entity.wphonecode;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="w_phone_code", schema="")
/*     */ public class WPhoneCodeEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="phone")
/*     */   private String phone;
/*     */ 
/*     */   @Excel(name="次数")
/*     */   private Integer count;
/*     */ 
/*     */   @Excel(name="ip")
/*     */   private String ip;
/*     */ 
/*     */   @Excel(name="创建时间", format="yyyy-MM-dd")
/*     */   private Date creatDate;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  57 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  65 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="PHONE", nullable=true, length=30)
/*     */   public String getPhone()
/*     */   {
/*  73 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/*  81 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   @Column(name="COUNT", nullable=true, length=10)
/*     */   public Integer getCount()
/*     */   {
/*  89 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(Integer count)
/*     */   {
/*  97 */     this.count = count;
/*     */   }
/*     */ 
/*     */   @Column(name="IP", nullable=true, length=20)
/*     */   public String getIp()
/*     */   {
/* 105 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip)
/*     */   {
/* 113 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   @Column(name="CREAT_DATE", nullable=true)
/*     */   public Date getCreatDate()
/*     */   {
/* 121 */     return this.creatDate;
/*     */   }
/*     */ 
/*     */   public void setCreatDate(Date creatDate)
/*     */   {
/* 129 */     this.creatDate = creatDate;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wphonecode.WPhoneCodeEntity
 * JD-Core Version:    0.6.2
 */