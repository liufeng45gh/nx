/*     */ package ssb.warmline.business.entity.wcashaccount;
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
/*     */ @Table(name="w_cash_account", schema="")
/*     */ public class WCashAccountEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="用户id")
/*     */   private String userId;
/*     */ 
/*     */   @Excel(name="用户名")
/*     */   private String userName;
/*     */ 
/*     */   @Excel(name="支付宝状态 0 未绑定  1绑定")
/*     */   private String alipayBindingState;
/*     */ 
/*     */   @Excel(name="支付宝账户")
/*     */   private String alipayAccount;
/*     */ 
/*     */   @Excel(name="微信绑定状态  0 未绑定  1绑定")
/*     */   private String wechatState;
/*     */ 
/*     */   @Excel(name="微信账户")
/*     */   private String wechatAccount;
/*     */ 
/*     */   @Excel(name="银行卡绑定状态 0 未绑定 1绑定")
/*     */   private String bankCardState;
/*     */ 
/*     */   @Excel(name="持卡人")
/*     */   private String cardholder;
/*     */ 
/*     */   @Excel(name="银行卡号")
/*     */   private String bankCard;
/*     */ 
/*     */   @Excel(name="银行卡类型")
/*     */   private String cardType;
/*     */ 
/*     */   @Excel(name="银行卡预留手机号")
/*     */   private String reservePhone;
/*     */ 
/*     */   @Excel(name="真实姓名")
/*     */   private String realName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  79 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  87 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="USER_ID", nullable=true, length=36)
/*     */   public String getUserId()
/*     */   {
/*  95 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(String userId)
/*     */   {
/* 103 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   @Column(name="USER_NAME", nullable=true, length=30)
/*     */   public String getUserName()
/*     */   {
/* 111 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName)
/*     */   {
/* 119 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   @Column(name="ALIPAY_BINDING_STATE", nullable=true, length=10)
/*     */   public String getAlipayBindingState()
/*     */   {
/* 127 */     return this.alipayBindingState;
/*     */   }
/*     */ 
/*     */   public void setAlipayBindingState(String alipayBindingState)
/*     */   {
/* 135 */     this.alipayBindingState = alipayBindingState;
/*     */   }
/*     */ 
/*     */   @Column(name="ALIPAY_ACCOUNT", nullable=true, length=50)
/*     */   public String getAlipayAccount()
/*     */   {
/* 143 */     return this.alipayAccount;
/*     */   }
/*     */ 
/*     */   public void setAlipayAccount(String alipayAccount)
/*     */   {
/* 151 */     this.alipayAccount = alipayAccount;
/*     */   }
/*     */ 
/*     */   @Column(name="WECHAT_STATE", nullable=true, length=10)
/*     */   public String getWechatState()
/*     */   {
/* 159 */     return this.wechatState;
/*     */   }
/*     */ 
/*     */   public void setWechatState(String wechatState)
/*     */   {
/* 167 */     this.wechatState = wechatState;
/*     */   }
/*     */ 
/*     */   @Column(name="WECHAT_ACCOUNT", nullable=true, length=50)
/*     */   public String getWechatAccount()
/*     */   {
/* 175 */     return this.wechatAccount;
/*     */   }
/*     */ 
/*     */   public void setWechatAccount(String wechatAccount)
/*     */   {
/* 183 */     this.wechatAccount = wechatAccount;
/*     */   }
/*     */ 
/*     */   @Column(name="BANK_CARD_STATE", nullable=true, length=10)
/*     */   public String getBankCardState()
/*     */   {
/* 191 */     return this.bankCardState;
/*     */   }
/*     */ 
/*     */   public void setBankCardState(String bankCardState)
/*     */   {
/* 199 */     this.bankCardState = bankCardState;
/*     */   }
/*     */ 
/*     */   @Column(name="CARDHOLDER", nullable=true, length=50)
/*     */   public String getCardholder()
/*     */   {
/* 207 */     return this.cardholder;
/*     */   }
/*     */ 
/*     */   public void setCardholder(String cardholder)
/*     */   {
/* 215 */     this.cardholder = cardholder;
/*     */   }
/*     */ 
/*     */   @Column(name="BANK_CARD", nullable=true, length=100)
/*     */   public String getBankCard()
/*     */   {
/* 223 */     return this.bankCard;
/*     */   }
/*     */ 
/*     */   public void setBankCard(String bankCard)
/*     */   {
/* 231 */     this.bankCard = bankCard;
/*     */   }
/*     */ 
/*     */   @Column(name="CARD_TYPE", nullable=true, length=50)
/*     */   public String getCardType()
/*     */   {
/* 239 */     return this.cardType;
/*     */   }
/*     */ 
/*     */   public void setCardType(String cardType)
/*     */   {
/* 247 */     this.cardType = cardType;
/*     */   }
/*     */ 
/*     */   @Column(name="RESERVE_PHONE", nullable=true, length=50)
/*     */   public String getReservePhone()
/*     */   {
/* 255 */     return this.reservePhone;
/*     */   }
/*     */ 
/*     */   public void setReservePhone(String reservePhone)
/*     */   {
/* 263 */     this.reservePhone = reservePhone;
/*     */   }
/*     */ 
/*     */   @Column(name="real_name", nullable=true, length=50)
/*     */   public String getRealName() {
/* 268 */     return this.realName;
/*     */   }
/*     */ 
/*     */   public void setRealName(String realName) {
/* 272 */     this.realName = realName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wcashaccount.WCashAccountEntity
 * JD-Core Version:    0.6.2
 */