/*     */ package ssb.warmline.business.service.businessprocessor.app;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import ssb.warmline.business.commons.config.ResponseObject;
/*     */ import ssb.warmline.business.commons.enums.Orderstatu;
/*     */ import ssb.warmline.business.commons.enums.PayStatus;
/*     */ import ssb.warmline.business.commons.enums.PaymentMode;
/*     */ import ssb.warmline.business.commons.enums.capitalType;
/*     */ import ssb.warmline.business.commons.shortMessage.PhoneCode;
/*     */ import ssb.warmline.business.commons.utils.CommonUtils;
/*     */ import ssb.warmline.business.entity.wcapital.WCapitalEntity;
/*     */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*     */ import ssb.warmline.business.service.businessprocessor.BaseInterface;
/*     */ import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
/*     */ import ssb.warmline.business.service.wcapital.WCapitalServiceI;
/*     */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*     */ import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;
/*     */ import ssb.warmline.business.service.wterritory.WTerritoryBusinessServiceI;
/*     */ 
/*     */ @Service
/*     */ public class WalletPayServiceProcessor extends BaseInterface
/*     */ {
/*  33 */   private static final Logger log = Logger.getLogger(WalletPayServiceProcessor.class);
/*     */ 
/*     */   @Autowired
/*     */   private WOrderServiceI wOrderService;
/*     */ 
/*     */   @Autowired
/*     */   private WOrderRecordServiceI wOrderRecordService;
/*     */ 
/*     */   @Autowired
/*     */   private TSBaseUserServiceI tSBaseUserService;
/*     */ 
/*     */   @Autowired
/*     */   WCapitalServiceI wCapitalService;
/*     */ 
/*     */   @Autowired
/*     */   WTerritoryBusinessServiceI wTerritoryBusinessService;
/*     */ 
/*  54 */   public ResponseObject walletPay(String orderNumber, String uid, String token) { TSBaseUser TSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
/*  55 */     WOrderEntity orderEntity = (WOrderEntity)this.wOrderService.findUniqueByProperty(WOrderEntity.class, "orderNumber", orderNumber.toString());
/*  56 */     Map jsonMap = new HashMap();
/*     */ 
/*  58 */     if (TSBaseUser != null)
/*     */     {
/*  60 */       if (orderEntity != null)
/*     */         try {
/*  62 */           String userWallet = TSBaseUser.getBalance();
/*  63 */           String orderPrice = orderEntity.getPrice();
/*  64 */           BigDecimal userWalletTemp = new BigDecimal(userWallet);
/*  65 */           BigDecimal orderPriceTemp = new BigDecimal(orderPrice);
/*     */ 
/*  67 */           int a = userWalletTemp.compareTo(orderPriceTemp);
/*     */ 
/*  70 */           if (a > -1)
/*     */           {
/*  72 */             BigDecimal balanceTemp = userWalletTemp.subtract(orderPriceTemp);
/*  73 */             TSBaseUser.setBalance(balanceTemp.toString());
/*  74 */             this.tSBaseUserService.saveOrUpdate(TSBaseUser);
/*     */ 
/*  76 */             orderEntity.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
/*  77 */             orderEntity.setPaymentMode(PaymentMode.wallet.toString());
/*  78 */             orderEntity.setBuyStatus(PayStatus.PAY_002.toString());
/*  79 */             orderEntity.setPaymentTime(new Date());
/*  80 */             this.wOrderService.saveOrUpdate(orderEntity);
/*     */ 
/*  82 */             this.wOrderRecordService.updateBySqlString("update w_order_record set buy_status='" + 
/*  83 */               PayStatus.PAY_002.toString() + "', payment_mode='" + PaymentMode.wallet.toString() + 
/*  84 */               "',order_status='" + Orderstatu.ORDERSTATU_001.toString() + 
/*  85 */               "',status_time=now() where order_Number='" + orderNumber + "'");
/*     */ 
/*  88 */             WCapitalEntity capital = new WCapitalEntity();
/*     */ 
/*  90 */             capital.setUserId(orderEntity.getIssuerId());
/*     */ 
/*  92 */             capital.setUserName(orderEntity.getIssuer());
/*     */ 
/*  94 */             capital.setPhone(orderEntity.getPhone());
/*     */ 
/*  96 */             capital.setOrderId(orderEntity.getId());
/*     */ 
/*  98 */             capital.setOrderNumber(orderEntity.getOrderNumber());
/*     */ 
/* 100 */             capital.setAmout(orderEntity.getPrice());
/*     */ 
/* 102 */             capital.setPayMethod(PaymentMode.weChat.toString());
/*     */ 
/* 104 */             capital.setType(capitalType.expenditure.toString());
/* 105 */             capital.setDescription("发布订单支出");
/* 106 */             capital.setTradeTime(new Date());
/* 107 */             this.wCapitalService.save(capital);
/*     */ 
/* 110 */             if ("0".equals(orderEntity.getSeekStatus()))
/*     */             {
/* 112 */               String city = orderEntity.getCity();
/* 113 */               if ((city != null) && (!"".equals(city))) {
/* 114 */                 String territoryId = orderEntity.getTerritoryId();
/*     */ 
/* 116 */                 List tsBaseUser = this.tSBaseUserService.findByProperty(TSBaseUser.class, "territoryId", territoryId);
/* 117 */                 for (int i = 0; i < tsBaseUser.size(); i++) {
/* 118 */                   TSBaseUser tsBaseUser2 = (TSBaseUser)tsBaseUser.get(i);
/* 119 */                   if ((!"".equals(tsBaseUser2.getPhone())) && (tsBaseUser2.getPhone() != null))
/*     */                   {
/* 121 */                     if ((tsBaseUser2.getAreaCode() != null) && (!"".equals(tsBaseUser2.getAreaCode()))) {
/* 122 */                       String phones = tsBaseUser2.getAreaCode() + tsBaseUser2.getPhone();
/* 123 */                       String content = "【暖行】 你所负责的" + city + "有人发布紧急订单寻求帮助，订单号为： " + orderNumber + ", 详情请登录系统进行查看。";
/* 124 */                       PhoneCode.getLoginPhoneOrderCode(phones, content);
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/* 130 */             jsonMap.put("orderNumber", orderNumber);
/* 131 */             jsonMap.put("uid", uid);
/* 132 */             return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */           }
/* 134 */           return CommonUtils.repsonseOjbectToClientWithBody("20046", null, new String[0]);
/*     */         }
/*     */         catch (Exception e) {
/* 137 */           e.printStackTrace();
/*     */         }
/*     */       else {
/* 140 */         return CommonUtils.repsonseOjbectToClientWithBody("20048", null, new String[0]);
/*     */       }
/*     */     }
/*     */     else {
/* 144 */       return CommonUtils.repsonseOjbectToClientWithBody("20047", null, new String[0]);
/*     */     }
/* 146 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.app.WalletPayServiceProcessor
 * JD-Core Version:    0.6.2
 */