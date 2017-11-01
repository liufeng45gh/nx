/*     */ package ssb.warmline.business.entity.worderrecord;
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
/*     */ @Table(name="w_order_record", schema="")
/*     */ public class WOrderRecordEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="订单号")
/*     */   private String orderNumber;
/*     */ 
/*     */   @Excel(name="订单id")
/*     */   private String orderId;
/*     */ 
/*     */   @Excel(name="订单金额")
/*     */   private String amount;
/*     */ 
/*     */   @Excel(name="订单交易类型 ")
/*     */   private String orderType;
/*     */ 
/*     */   @Excel(name="创建时间", format="yyyy-MM-dd")
/*     */   private Date createTime;
/*     */ 
/*     */   @Excel(name="描述")
/*     */   private String description;
/*     */ 
/*     */   @Excel(name="发布人id")
/*     */   private String issuerId;
/*     */ 
/*     */   @Excel(name="发布人")
/*     */   private String issuer;
/*     */ 
/*     */   @Excel(name="手机号")
/*     */   private String phone;
/*     */ 
/*     */   @Excel(name="支付状态")
/*     */   private String buyStatus;
/*     */ 
/*     */   @Excel(name="支付方式")
/*     */   private String paymentMode;
/*     */ 
/*     */   @Excel(name="订单状态")
/*     */   private String orderStatus;
/*     */ 
/*     */   @Excel(name="订单状态更新时间", format="yyyy-MM-dd")
/*     */   private Date statusTime;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=50)
/*     */   public String getId()
/*     */   {
/*  76 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  84 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="order_Number", nullable=true, length=50)
/*     */   public String getOrderNumber()
/*     */   {
/*  91 */     return this.orderNumber;
/*     */   }
/*     */ 
/*     */   public void setOrderNumber(String orderNumber) {
/*  95 */     this.orderNumber = orderNumber;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_ID", nullable=true, length=50)
/*     */   public String getOrderId()
/*     */   {
/* 104 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(String orderId)
/*     */   {
/* 112 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   @Column(name="amount", nullable=true, length=10)
/*     */   public String getAmount()
/*     */   {
/* 120 */     return this.amount;
/*     */   }
/*     */ 
/*     */   public void setAmount(String amount)
/*     */   {
/* 128 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_TYPE", nullable=true, length=255)
/*     */   public String getOrderType()
/*     */   {
/* 136 */     return this.orderType;
/*     */   }
/*     */ 
/*     */   public void setOrderType(String orderType)
/*     */   {
/* 144 */     this.orderType = orderType;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_TIME", nullable=true)
/*     */   public Date getCreateTime()
/*     */   {
/* 152 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 160 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   @Column(name="DESCRIPTION", nullable=true, length=255)
/*     */   public String getDescription()
/*     */   {
/* 168 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 176 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="issuer_id", nullable=true, length=255)
/*     */   public String getIssuerId() {
/* 181 */     return this.issuerId;
/*     */   }
/*     */ 
/*     */   public void setIssuerId(String issuerId) {
/* 185 */     this.issuerId = issuerId;
/*     */   }
/*     */ 
/*     */   @Column(name="issuer", nullable=true, length=255)
/*     */   public String getIssuer() {
/* 190 */     return this.issuer;
/*     */   }
/*     */ 
/*     */   public void setIssuer(String issuer) {
/* 194 */     this.issuer = issuer;
/*     */   }
/*     */ 
/*     */   @Column(name="phone", nullable=true, length=255)
/*     */   public String getPhone() {
/* 199 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone) {
/* 203 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   @Column(name="buy_status", nullable=true, length=50)
/*     */   public String getBuyStatus() {
/* 208 */     return this.buyStatus;
/*     */   }
/*     */ 
/*     */   public void setBuyStatus(String buyStatus) {
/* 212 */     this.buyStatus = buyStatus;
/*     */   }
/*     */   @Column(name="payment_mode", nullable=true, length=50)
/*     */   public String getPaymentMode() {
/* 216 */     return this.paymentMode;
/*     */   }
/*     */ 
/*     */   public void setPaymentMode(String paymentMode) {
/* 220 */     this.paymentMode = paymentMode;
/*     */   }
/*     */   @Column(name="order_status", nullable=true, length=50)
/*     */   public String getOrderStatus() {
/* 224 */     return this.orderStatus;
/*     */   }
/*     */ 
/*     */   public void setOrderStatus(String orderStatus) {
/* 228 */     this.orderStatus = orderStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="status_time", nullable=true)
/*     */   public Date getStatusTime() {
/* 233 */     return this.statusTime;
/*     */   }
/*     */ 
/*     */   public void setStatusTime(Date statusTime) {
/* 237 */     this.statusTime = statusTime;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.worderrecord.WOrderRecordEntity
 * JD-Core Version:    0.6.2
 */