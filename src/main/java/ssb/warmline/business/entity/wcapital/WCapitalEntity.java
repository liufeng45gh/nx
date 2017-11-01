/*     */ package ssb.warmline.business.entity.wcapital;
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
/*     */ @Table(name="w_capital", schema="")
/*     */ public class WCapitalEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="交易用户id")
/*     */   private String userId;
/*     */ 
/*     */   @Excel(name="交易用户名称")
/*     */   private String userName;
/*     */ 
/*     */   @Excel(name="交易类型")
/*     */   private String transferType;
/*     */ 
/*     */   @Excel(name="用户手机号")
/*     */   private String phone;
/*     */ 
/*     */   @Excel(name="转入账户")
/*     */   private String intoAccount;
/*     */ 
/*     */   @Excel(name="交易时间", format="yyyy-MM-dd")
/*     */   private Date tradeTime;
/*     */ 
/*     */   @Excel(name="支付方式")
/*     */   private String payMethod;
/*     */ 
/*     */   @Excel(name="描述")
/*     */   private String description;
/*     */ 
/*     */   @Excel(name="交易金额")
/*     */   private String amout;
/*     */ 
/*     */   @Excel(name="资金流水类型")
/*     */   private String type;
/*     */ 
/*     */   @Excel(name="订单id")
/*     */   private String orderId;
/*     */ 
/*     */   @Excel(name="订单号")
/*     */   private String orderNumber;
/*     */ 
/*     */   @Excel(name="审批时间", format="yyyy-MM-dd")
/*     */   private Date approvalTime;
/*     */ 
/*     */   @Excel(name="审批人")
/*     */   private String approvalName;
/*     */ 
/*     */   @Excel(name="审批状态")
/*     */   private String approvalType;
/*     */ 
/*     */   @Excel(name="备注")
/*     */   private String remarks;
/*     */   private String realName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  86 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  94 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="USER_ID", nullable=true, length=36)
/*     */   public String getUserId()
/*     */   {
/* 102 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(String userId)
/*     */   {
/* 110 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   @Column(name="USER_NAME", nullable=true, length=50)
/*     */   public String getUserName()
/*     */   {
/* 118 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName)
/*     */   {
/* 126 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   @Column(name="transfer_type", nullable=true, length=50)
/*     */   public String getTransferType() {
/* 131 */     return this.transferType;
/*     */   }
/*     */   public void setTransferType(String transferType) {
/* 134 */     this.transferType = transferType;
/*     */   }
/*     */ 
/*     */   @Column(name="into_account", nullable=true, length=50)
/*     */   public String getIntoAccount() {
/* 139 */     return this.intoAccount;
/*     */   }
/*     */   public void setIntoAccount(String intoAccount) {
/* 142 */     this.intoAccount = intoAccount;
/*     */   }
/*     */ 
/*     */   @Column(name="TRADE_TIME", nullable=true)
/*     */   public Date getTradeTime()
/*     */   {
/* 151 */     return this.tradeTime;
/*     */   }
/*     */ 
/*     */   public void setTradeTime(Date tradeTime)
/*     */   {
/* 159 */     this.tradeTime = tradeTime;
/*     */   }
/*     */ 
/*     */   @Column(name="PAY_METHOD", nullable=true, length=50)
/*     */   public String getPayMethod()
/*     */   {
/* 167 */     return this.payMethod;
/*     */   }
/*     */ 
/*     */   public void setPayMethod(String payMethod)
/*     */   {
/* 175 */     this.payMethod = payMethod;
/*     */   }
/*     */ 
/*     */   @Column(name="DESCRIPTION", nullable=true, length=1000)
/*     */   public String getDescription()
/*     */   {
/* 183 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 191 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="AMOUT", nullable=true, length=20)
/*     */   public String getAmout()
/*     */   {
/* 199 */     return this.amout;
/*     */   }
/*     */ 
/*     */   public void setAmout(String amout)
/*     */   {
/* 207 */     this.amout = amout;
/*     */   }
/*     */ 
/*     */   @Column(name="TYPE", nullable=true, length=255)
/*     */   public String getType()
/*     */   {
/* 215 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 223 */     this.type = type;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_ID", nullable=true, length=36)
/*     */   public String getOrderId()
/*     */   {
/* 231 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(String orderId)
/*     */   {
/* 239 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   @Column(name="APPROVAL_TIME", nullable=true)
/*     */   public Date getApprovalTime()
/*     */   {
/* 248 */     return this.approvalTime;
/*     */   }
/*     */ 
/*     */   public void setApprovalTime(Date approvalTime)
/*     */   {
/* 256 */     this.approvalTime = approvalTime;
/*     */   }
/*     */ 
/*     */   @Column(name="APPROVAL_NAME", nullable=true, length=50)
/*     */   public String getApprovalName()
/*     */   {
/* 264 */     return this.approvalName;
/*     */   }
/*     */ 
/*     */   public void setApprovalName(String approvalName)
/*     */   {
/* 272 */     this.approvalName = approvalName;
/*     */   }
/*     */ 
/*     */   @Column(name="APPROVAL_TYPE", nullable=true, length=50)
/*     */   public String getApprovalType()
/*     */   {
/* 280 */     return this.approvalType;
/*     */   }
/*     */ 
/*     */   public void setApprovalType(String approvalType)
/*     */   {
/* 288 */     this.approvalType = approvalType;
/*     */   }
/*     */ 
/*     */   @Column(name="REMARKS", nullable=true, length=1000)
/*     */   public String getRemarks()
/*     */   {
/* 296 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks)
/*     */   {
/* 304 */     this.remarks = remarks;
/*     */   }
/*     */   @Column(name="order_Number", nullable=true, length=50)
/*     */   public String getOrderNumber() {
/* 308 */     return this.orderNumber;
/*     */   }
/*     */ 
/*     */   public void setOrderNumber(String orderNumber) {
/* 312 */     this.orderNumber = orderNumber;
/*     */   }
/*     */   @Column(name="phone", nullable=true, length=50)
/*     */   public String getPhone() {
/* 316 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone) {
/* 320 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getRealName() {
/* 325 */     return this.realName;
/*     */   }
/*     */ 
/*     */   public void setRealName(String realName) {
/* 329 */     this.realName = realName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wcapital.WCapitalEntity
 * JD-Core Version:    0.6.2
 */