/*     */ package ssb.warmline.business.entity.worder;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Transient;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="w_order", schema="")
/*     */ public class WOrderEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="订单号")
/*     */   private String orderNumber;
/*     */ 
/*     */   @Excel(name="标题")
/*     */   private String title;
/*     */ 
/*     */   @Excel(name="副标题")
/*     */   private String subtitle;
/*     */ 
/*     */   @Excel(name="描述")
/*     */   private String description;
/*     */ 
/*     */   @Excel(name="类别")
/*     */   private String category;
/*     */ 
/*     */   @Excel(name="相片")
/*     */   private String photos;
/*     */ 
/*     */   @Excel(name="价格")
/*     */   private String price;
/*     */ 
/*     */   @Excel(name="发布人")
/*     */   private String issuer;
/*     */ 
/*     */   @Excel(name="联系电话")
/*     */   private String phone;
/*     */ 
/*     */   @Excel(name="位置信息")
/*     */   private String location;
/*     */ 
/*     */   @Excel(name="国家")
/*     */   private String state;
/*     */ 
/*     */   @Excel(name="城市")
/*     */   private String city;
/*     */ 
/*     */   @Excel(name="订单状态")
/*     */   private String orderStatus;
/*     */ 
/*     */   @Excel(name="接单人姓名")
/*     */   private String orderPersonName;
/*     */ 
/*     */   @Excel(name="接单人联系电话")
/*     */   private String orderPersonPhone;
/*     */ 
/*     */   @Excel(name="求助类型")
/*     */   private String seekStatus;
/*     */ 
/*     */   @Excel(name="发布人id")
/*     */   private String issuerId;
/*     */ 
/*     */   @Excel(name="接单人id")
/*     */   private String orderPersonId;
/*     */ 
/*     */   @Excel(name="订单开始时间", format="yyyy-MM-dd HH:mm:ss")
/*     */   private Date startTime;
/*     */ 
/*     */   @Excel(name="订单结束订单", format="yyyy-MM-dd HH:mm:ss")
/*     */   private Date endTime;
/*     */ 
/*     */   @Excel(name="订单创建时间", format="yyyy-MM-dd")
/*     */   private Date orderTime;
/*     */ 
/*     */   @Excel(name="支付类型")
/*     */   private String buyStatus;
/*     */ 
/*     */   @Excel(name="支付方式")
/*     */   private String paymentMode;
/*     */ 
/*     */   @Excel(name="备注")
/*     */   private String remarks;
/*     */ 
/*     */   @Excel(name="订单类型")
/*     */   private String orderType;
/*     */ 
/*     */   @Excel(name="经度")
/*     */   private String latitude;
/*     */ 
/*     */   @Excel(name="纬度")
/*     */   private String longitude;
/*     */ 
/*     */   @Excel(name="距离")
/*     */   private String distance;
/*     */ 
/*     */   @Excel(name="区域id")
/*     */   private String territoryId;
/*     */   private String territoryName;
/*     */ 
/*     */   @Excel(name="支付的时间", format="yyyy-MM-dd HH:mm:ss")
/*     */   private Date paymentTime;
/*     */ 
/*     */   @Excel(name="确定服务时间", format="yyyy-MM-dd HH:mm:ss")
/*     */   private Date determineServiceTime;
/*     */ 
/*     */   @Excel(name="服务完成时间", format="yyyy-MM-dd HH:mm:ss")
/*     */   private Date serviceCompletionTime;
/*     */ 
/*     */   @Excel(name="完成时间", format="yyyy-MM-dd HH:mm:ss")
/*     */   private Date completionTime;
/*     */ 
/*     */   @Excel(name="取消订单时间", format="yyyy-MM-dd HH:mm:ss")
/*     */   private Date cancellationTime;
/*     */   private String isVirtualUser;
/*     */   private String subordinateAdmin;
/*     */   private String orderPhone;
/*     */   private String isRefund;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/* 171 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 179 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="order_Number", nullable=true, length=100)
/*     */   public String getOrderNumber()
/*     */   {
/* 185 */     return this.orderNumber;
/*     */   }
/*     */ 
/*     */   public void setOrderNumber(String orderNumber) {
/* 189 */     this.orderNumber = orderNumber;
/*     */   }
/*     */ 
/*     */   @Column(name="TITLE", nullable=true, length=255)
/*     */   public String getTitle()
/*     */   {
/* 198 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 206 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="SUBTITLE", nullable=true, length=255)
/*     */   public String getSubtitle()
/*     */   {
/* 214 */     return this.subtitle;
/*     */   }
/*     */ 
/*     */   public void setSubtitle(String subtitle)
/*     */   {
/* 222 */     this.subtitle = subtitle;
/*     */   }
/*     */ 
/*     */   @Column(name="DESCRIPTION", nullable=true, length=500)
/*     */   public String getDescription()
/*     */   {
/* 230 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 238 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="CATEGORY", nullable=true, length=50)
/*     */   public String getCategory()
/*     */   {
/* 246 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(String category)
/*     */   {
/* 254 */     this.category = category;
/*     */   }
/*     */ 
/*     */   @Column(name="PHOTOS", nullable=true, length=255)
/*     */   public String getPhotos()
/*     */   {
/* 262 */     return this.photos;
/*     */   }
/*     */ 
/*     */   public void setPhotos(String photos)
/*     */   {
/* 270 */     this.photos = photos;
/*     */   }
/*     */ 
/*     */   @Column(name="PRICE", nullable=true, length=32)
/*     */   public String getPrice()
/*     */   {
/* 278 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(String price)
/*     */   {
/* 286 */     this.price = price;
/*     */   }
/*     */ 
/*     */   @Column(name="ISSUER", nullable=true, length=255)
/*     */   public String getIssuer()
/*     */   {
/* 294 */     return this.issuer;
/*     */   }
/*     */ 
/*     */   public void setIssuer(String issuer)
/*     */   {
/* 302 */     this.issuer = issuer;
/*     */   }
/*     */ 
/*     */   @Column(name="PHONE", nullable=true, length=50)
/*     */   public String getPhone()
/*     */   {
/* 310 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/* 318 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   @Column(name="LOCATION", nullable=true, length=255)
/*     */   public String getLocation()
/*     */   {
/* 326 */     return this.location;
/*     */   }
/*     */ 
/*     */   public void setLocation(String location)
/*     */   {
/* 334 */     this.location = location;
/*     */   }
/*     */ 
/*     */   @Column(name="STATE", nullable=true, length=50)
/*     */   public String getState()
/*     */   {
/* 342 */     return this.state;
/*     */   }
/*     */ 
/*     */   public void setState(String state)
/*     */   {
/* 350 */     this.state = state;
/*     */   }
/*     */ 
/*     */   @Column(name="CITY", nullable=true, length=50)
/*     */   public String getCity()
/*     */   {
/* 358 */     return this.city;
/*     */   }
/*     */ 
/*     */   public void setCity(String city)
/*     */   {
/* 366 */     this.city = city;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_STATUS", nullable=true, length=20)
/*     */   public String getOrderStatus()
/*     */   {
/* 375 */     return this.orderStatus;
/*     */   }
/*     */ 
/*     */   public void setOrderStatus(String orderStatus)
/*     */   {
/* 383 */     this.orderStatus = orderStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_PERSON_NAME", nullable=true, length=50)
/*     */   public String getOrderPersonName()
/*     */   {
/* 391 */     return this.orderPersonName;
/*     */   }
/*     */ 
/*     */   public void setOrderPersonName(String orderPersonName)
/*     */   {
/* 399 */     this.orderPersonName = orderPersonName;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_PERSON_PHONE", nullable=true, length=50)
/*     */   public String getOrderPersonPhone()
/*     */   {
/* 407 */     return this.orderPersonPhone;
/*     */   }
/*     */ 
/*     */   public void setOrderPersonPhone(String orderPersonPhone)
/*     */   {
/* 415 */     this.orderPersonPhone = orderPersonPhone;
/*     */   }
/*     */ 
/*     */   @Column(name="SEEK_STATUS", nullable=true, length=10)
/*     */   public String getSeekStatus()
/*     */   {
/* 423 */     return this.seekStatus;
/*     */   }
/*     */ 
/*     */   public void setSeekStatus(String seekStatus)
/*     */   {
/* 431 */     this.seekStatus = seekStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="ISSUER_ID", nullable=true, length=36)
/*     */   public String getIssuerId()
/*     */   {
/* 439 */     return this.issuerId;
/*     */   }
/*     */ 
/*     */   public void setIssuerId(String issuerId)
/*     */   {
/* 447 */     this.issuerId = issuerId;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_PERSON_ID", nullable=true, length=36)
/*     */   public String getOrderPersonId()
/*     */   {
/* 455 */     return this.orderPersonId;
/*     */   }
/*     */ 
/*     */   public void setOrderPersonId(String orderPersonId)
/*     */   {
/* 463 */     this.orderPersonId = orderPersonId;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_TIME", nullable=true)
/*     */   public Date getOrderTime()
/*     */   {
/* 471 */     return this.orderTime;
/*     */   }
/*     */ 
/*     */   public void setOrderTime(Date orderTime)
/*     */   {
/* 479 */     this.orderTime = orderTime;
/*     */   }
/*     */   @Column(name="buy_status", nullable=true, length=50)
/*     */   public String getBuyStatus() {
/* 483 */     return this.buyStatus;
/*     */   }
/*     */ 
/*     */   public void setBuyStatus(String buyStatus) {
/* 487 */     this.buyStatus = buyStatus;
/*     */   }
/*     */   @Column(name="payment_mode", nullable=true, length=50)
/*     */   public String getPaymentMode() {
/* 491 */     return this.paymentMode;
/*     */   }
/*     */ 
/*     */   public void setPaymentMode(String paymentMode) {
/* 495 */     this.paymentMode = paymentMode;
/*     */   }
/*     */   @Column(name="remarks", nullable=true, length=500)
/*     */   public String getRemarks() {
/* 499 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks) {
/* 503 */     this.remarks = remarks;
/*     */   }
/*     */ 
/*     */   @Column(name="start_time", nullable=true)
/*     */   public Date getStartTime() {
/* 508 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime) {
/* 512 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   @Column(name="end_time", nullable=true)
/*     */   public Date getEndTime() {
/* 517 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime) {
/* 521 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   @Column(name="order_type", nullable=true, length=50)
/*     */   public String getOrderType() {
/* 526 */     return this.orderType;
/*     */   }
/*     */ 
/*     */   public void setOrderType(String orderType) {
/* 530 */     this.orderType = orderType;
/*     */   }
/*     */ 
/*     */   @Column(name="latitude", nullable=true, length=50)
/*     */   public String getLatitude() {
/* 535 */     return this.latitude;
/*     */   }
/*     */ 
/*     */   public void setLatitude(String latitude) {
/* 539 */     this.latitude = latitude;
/*     */   }
/*     */   @Column(name="longitude", nullable=true, length=50)
/*     */   public String getLongitude() {
/* 543 */     return this.longitude;
/*     */   }
/*     */ 
/*     */   public void setLongitude(String longitude) {
/* 547 */     this.longitude = longitude;
/*     */   }
/*     */   @Column(name="distance", nullable=true, length=50)
/*     */   public String getDistance() {
/* 551 */     return this.distance;
/*     */   }
/*     */ 
/*     */   public void setDistance(String distance) {
/* 555 */     this.distance = distance;
/*     */   }
/*     */ 
/*     */   @Column(name="payment_time", nullable=true)
/*     */   public Date getPaymentTime() {
/* 560 */     return this.paymentTime;
/*     */   }
/*     */ 
/*     */   public void setPaymentTime(Date paymentTime) {
/* 564 */     this.paymentTime = paymentTime;
/*     */   }
/*     */ 
/*     */   @Column(name="determine_service_time", nullable=true)
/*     */   public Date getDetermineServiceTime() {
/* 569 */     return this.determineServiceTime;
/*     */   }
/*     */ 
/*     */   public void setDetermineServiceTime(Date determineServiceTime) {
/* 573 */     this.determineServiceTime = determineServiceTime;
/*     */   }
/*     */ 
/*     */   @Column(name="service_completion_time", nullable=true)
/*     */   public Date getServiceCompletionTime() {
/* 578 */     return this.serviceCompletionTime;
/*     */   }
/*     */ 
/*     */   public void setServiceCompletionTime(Date serviceCompletionTime) {
/* 582 */     this.serviceCompletionTime = serviceCompletionTime;
/*     */   }
/*     */ 
/*     */   @Column(name="completion_time", nullable=true)
/*     */   public Date getCompletionTime() {
/* 587 */     return this.completionTime;
/*     */   }
/*     */ 
/*     */   public void setCompletionTime(Date completionTime) {
/* 591 */     this.completionTime = completionTime;
/*     */   }
/*     */   @Column(name="cancellation_time", nullable=true)
/*     */   public Date getCancellationTime() {
/* 595 */     return this.cancellationTime;
/*     */   }
/*     */ 
/*     */   public void setCancellationTime(Date cancellationTime) {
/* 599 */     this.cancellationTime = cancellationTime;
/*     */   }
/*     */   @Column(name="is_virtual_user", nullable=true, length=50)
/*     */   public String getIsVirtualUser() {
/* 603 */     return this.isVirtualUser;
/*     */   }
/*     */   public void setIsVirtualUser(String isVirtualUser) {
/* 606 */     this.isVirtualUser = isVirtualUser;
/*     */   }
/*     */   @Column(name="territory_id", nullable=true, length=50)
/*     */   public String getTerritoryId() {
/* 610 */     return this.territoryId;
/*     */   }
/*     */   public void setTerritoryId(String territoryId) {
/* 613 */     this.territoryId = territoryId;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTerritoryName() {
/* 618 */     return this.territoryName;
/*     */   }
/*     */ 
/*     */   public void setTerritoryName(String territoryName) {
/* 622 */     this.territoryName = territoryName;
/*     */   }
/*     */ 
/*     */   @Column(name="subordinate_admin", length=100)
/*     */   public String getSubordinateAdmin() {
/* 627 */     return this.subordinateAdmin;
/*     */   }
/*     */   public void setSubordinateAdmin(String subordinateAdmin) {
/* 630 */     this.subordinateAdmin = subordinateAdmin;
/*     */   }
/*     */ 
/*     */   @Column(name="order_phone", length=50)
/*     */   public String getOrderPhone() {
/* 635 */     return this.orderPhone;
/*     */   }
/*     */ 
/*     */   public void setOrderPhone(String orderPhone) {
/* 639 */     this.orderPhone = orderPhone;
/*     */   }
/*     */ 
/*     */   @Column(name="is_refund", length=36)
/*     */   public String getIsRefund() {
/* 644 */     return this.isRefund;
/*     */   }
/*     */ 
/*     */   public void setIsRefund(String isRefund) {
/* 648 */     this.isRefund = isRefund;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.worder.WOrderEntity
 * JD-Core Version:    0.6.2
 */