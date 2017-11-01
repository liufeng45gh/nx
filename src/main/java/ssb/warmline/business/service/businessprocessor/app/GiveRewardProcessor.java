/*     */ package ssb.warmline.business.service.businessprocessor.app;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import ssb.warmline.business.commons.config.ResponseObject;
/*     */ import ssb.warmline.business.commons.enums.capitalType;
/*     */ import ssb.warmline.business.commons.utils.CommonUtils;
/*     */ import ssb.warmline.business.commons.utils.JsonReturn;
/*     */ import ssb.warmline.business.commons.utils.UUIDUtil;
/*     */ import ssb.warmline.business.entity.wcapital.WCapitalEntity;
/*     */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*     */ import ssb.warmline.business.service.businessprocessor.BaseInterface;
/*     */ import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
/*     */ import ssb.warmline.business.service.wcapital.WCapitalServiceI;
/*     */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*     */ 
/*     */ @Service
/*     */ public class GiveRewardProcessor extends BaseInterface
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private WeChatPayServiceProcessor weChatPayService;
/*     */ 
/*     */   @Autowired
/*     */   private AlipayServiceProcessor alipayService;
/*     */ 
/*     */   @Autowired
/*     */   private WOrderServiceI wOrderService;
/*     */ 
/*     */   @Autowired
/*     */   private TSBaseUserServiceI tSBaseUserService;
/*     */ 
/*     */   @Autowired
/*     */   private WCapitalServiceI wCapitalService;
/*  40 */   private static final Logger log = Logger.getLogger(GiveRewardProcessor.class);
/*     */ 
/*     */   public JsonReturn giveReward(String orderId, String uid, String token, String remoteIp, String payType, String totalFee)
/*     */   {
/*  54 */     JsonReturn toSucResult = null;
/*  55 */     Map resultMap = new HashMap();
/*     */     try {
/*  57 */       if ("alipay".equals(payType)) {
/*  58 */         return this.alipayService.giveReward_appAlipay(orderId, uid, token, remoteIp, totalFee);
/*     */       }
/*  60 */       if ("weChat".equals(payType))
/*     */       {
/*  62 */         return this.weChatPayService.giveReward_weChatAppPay(remoteIp, orderId, uid, totalFee);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  67 */       e.printStackTrace();
/*     */     }
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */   public ResponseObject walletGiveReward(String orderId, String uid, String token, String totalFee)
/*     */   {
/*  81 */     Map jsonMap = new HashMap();
/*  82 */     WOrderEntity orderEntity = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, orderId);
/*  83 */     String orderPersonId = orderEntity.getOrderPersonId();
/*  84 */     String issuerId = orderEntity.getIssuerId();
/*  85 */     TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.getEntity(TSBaseUser.class, orderPersonId);
/*  86 */     TSBaseUser tSBaseUser_issuer = (TSBaseUser)this.tSBaseUserService.getEntity(TSBaseUser.class, issuerId);
/*  87 */     String balance_temp = tSBaseUser.getBalance();
/*  88 */     String balance_issuer = tSBaseUser_issuer.getBalance();
/*  89 */     if ((balance_temp == null) || ("".equals(balance_temp))) {
/*  90 */       balance_temp = "0";
/*     */     }
/*  92 */     if ((balance_issuer != null) && (!"".equals(balance_issuer))) {
/*  93 */       BigDecimal balanceIssuer = new BigDecimal(balance_issuer);
/*  94 */       BigDecimal balanceTemp = new BigDecimal(balance_temp);
/*  95 */       BigDecimal totalFee_temp = new BigDecimal(totalFee);
/*     */ 
/*  97 */       int a = balanceIssuer.compareTo(totalFee_temp);
/*     */ 
/*  99 */       if (a > -1)
/*     */       {
/* 101 */         String balance = balanceTemp.add(totalFee_temp).toString();
/* 102 */         tSBaseUser.setBalance(balance);
/* 103 */         this.tSBaseUserService.saveOrUpdate(tSBaseUser);
/*     */ 
/* 105 */         String issuerFee = balanceIssuer.subtract(totalFee_temp).toString();
/* 106 */         tSBaseUser_issuer.setBalance(issuerFee);
/* 107 */         this.tSBaseUserService.saveOrUpdate(tSBaseUser_issuer);
/*     */ 
/* 110 */         WCapitalEntity wCapital = new WCapitalEntity();
/* 111 */         wCapital.setId(UUIDUtil.getId());
/* 112 */         wCapital.setAmout(totalFee_temp.toString());
/* 113 */         wCapital.setUserId(orderPersonId);
/* 114 */         wCapital.setUserName(tSBaseUser.getUserName());
/* 115 */         wCapital.setPhone(tSBaseUser.getPhone());
/* 116 */         wCapital.setTradeTime(new Date());
/* 117 */         wCapital.setDescription("打赏收入");
/* 118 */         wCapital.setOrderId(orderId);
/* 119 */         wCapital.setType(capitalType.income.toString());
/* 120 */         this.wCapitalService.save(wCapital);
/*     */ 
/* 122 */         WCapitalEntity wCapitalA = new WCapitalEntity();
/* 123 */         wCapitalA.setId(UUIDUtil.getId());
/* 124 */         wCapitalA.setAmout(totalFee_temp.toString());
/* 125 */         wCapitalA.setUserId(issuerId);
/* 126 */         wCapitalA.setUserName(tSBaseUser_issuer.getUserName());
/* 127 */         wCapitalA.setPhone(tSBaseUser_issuer.getPhone());
/* 128 */         wCapitalA.setTradeTime(new Date());
/* 129 */         wCapitalA.setDescription("打赏支出");
/* 130 */         wCapitalA.setOrderId(orderId);
/* 131 */         wCapitalA.setType(capitalType.expenditure.toString());
/* 132 */         this.wCapitalService.save(wCapitalA);
/* 133 */         jsonMap.put("status", "success");
/* 134 */         jsonMap.put("msg", "打赏成功！");
/* 135 */         return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */       }
/*     */ 
/* 138 */       return CommonUtils.repsonseOjbectToClientWithBody("20046", null, new String[0]);
/*     */     }
/*     */ 
/* 141 */     return CommonUtils.repsonseOjbectToClientWithBody("20046", null, new String[0]);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.app.GiveRewardProcessor
 * JD-Core Version:    0.6.2
 */