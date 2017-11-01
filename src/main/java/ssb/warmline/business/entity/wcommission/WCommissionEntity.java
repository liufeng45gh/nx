/*     */ package ssb.warmline.business.entity.wcommission;
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
/*     */ @Table(name="w_commission", schema="")
/*     */ public class WCommissionEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="用户id")
/*     */   private String userId;
/*     */ 
/*     */   @Excel(name="用户名称")
/*     */   private String userName;
/*     */ 
/*     */   @Excel(name="手机号")
/*     */   private String phone;
/*     */ 
/*     */   @Excel(name="金额")
/*     */   private String amount;
/*     */ 
/*     */   @Excel(name="创建时间", format="yyyy-MM-dd")
/*     */   private Date createTime;
/*     */ 
/*     */   @Excel(name="订单号")
/*     */   private String orderNumber;
/*     */ 
/*     */   @Excel(name="佣金类别")
/*     */   private String commissionType;
/*     */ 
/*     */   @Excel(name="接单人名称")
/*     */   private String orderPersonName;
/*     */ 
/*     */   @Excel(name="接单人id")
/*     */   private String orderPersonId;
/*     */ 
/*     */   @Excel(name="订单类型")
/*     */   private String seekStatus;
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
/*     */   @Column(name="USER_ID", nullable=true, length=36)
/*     */   public String getUserId()
/*     */   {
/*  89 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(String userId)
/*     */   {
/*  97 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   @Column(name="USER_NAME", nullable=true, length=50)
/*     */   public String getUserName()
/*     */   {
/* 105 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName)
/*     */   {
/* 113 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   @Column(name="PHONE", nullable=true, length=30)
/*     */   public String getPhone()
/*     */   {
/* 121 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/* 129 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   @Column(name="AMOUNT", nullable=true, length=10)
/*     */   public String getAmount()
/*     */   {
/* 137 */     return this.amount;
/*     */   }
/*     */ 
/*     */   public void setAmount(String amount)
/*     */   {
/* 145 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_TIME", nullable=true)
/*     */   public Date getCreateTime()
/*     */   {
/* 153 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 161 */     this.createTime = createTime;
/*     */   }
/*     */   @Column(name="order_Number", nullable=true, length=50)
/*     */   public String getOrderNumber() {
/* 165 */     return this.orderNumber;
/*     */   }
/*     */ 
/*     */   public void setOrderNumber(String orderNumber) {
/* 169 */     this.orderNumber = orderNumber;
/*     */   }
/*     */ 
/*     */   @Column(name="commission_type", nullable=true, length=50)
/*     */   public String getCommissionType() {
/* 174 */     return this.commissionType;
/*     */   }
/*     */ 
/*     */   public void setCommissionType(String commissionType) {
/* 178 */     this.commissionType = commissionType;
/*     */   }
/*     */ 
/*     */   @Column(name="order_person_name", nullable=true, length=50)
/*     */   public String getOrderPersonName() {
/* 183 */     return this.orderPersonName;
/*     */   }
/*     */ 
/*     */   public void setOrderPersonName(String orderPersonName) {
/* 187 */     this.orderPersonName = orderPersonName;
/*     */   }
/*     */ 
/*     */   @Column(name="order_person_id", nullable=true, length=50)
/*     */   public String getOrderPersonId() {
/* 192 */     return this.orderPersonId;
/*     */   }
/*     */ 
/*     */   public void setOrderPersonId(String orderPersonId) {
/* 196 */     this.orderPersonId = orderPersonId;
/*     */   }
/*     */   @Column(name="seek_status", nullable=true, length=50)
/*     */   public String getSeekStatus() {
/* 200 */     return this.seekStatus;
/*     */   }
/*     */ 
/*     */   public void setSeekStatus(String seekStatus) {
/* 204 */     this.seekStatus = seekStatus;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wcommission.WCommissionEntity
 * JD-Core Version:    0.6.2
 */