/*     */ package ssb.warmline.business.controller.alipay;
/*     */ 
/*     */ import com.alipay.api.AlipayApiException;
/*     */ import com.alipay.api.internal.util.AlipaySignature;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import ssb.warmline.business.commons.enums.Orderstatu;
/*     */ import ssb.warmline.business.commons.enums.PayStatus;
/*     */ import ssb.warmline.business.commons.enums.PaymentMode;
/*     */ import ssb.warmline.business.commons.enums.capitalType;
/*     */ import ssb.warmline.business.commons.utils.UUIDUtil;
/*     */ import ssb.warmline.business.entity.wcapital.WCapitalEntity;
/*     */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*     */ import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
/*     */ import ssb.warmline.business.service.wcapital.WCapitalServiceI;
/*     */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*     */ import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;
/*     */ 
/*     */ @RequestMapping({"/AppAlipay"})
/*     */ public class AppAlipayController extends BaseController
/*     */ {
/*  41 */   private static final Logger log = Logger.getLogger(AppAlipayController.class);
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
/*     */   private WCapitalServiceI wCapitalService;
/*  52 */   static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp9WAfqfYC9EBGO5J/UaRpHuWO5BuKBmSFS42YwKbsZVJN66C/N7i4Dz7+aW2scGM+3Y6GfNaCMOJFbSAAK8C756EohURRecIzdDtRY8VBXArq5TYWVHlJKNqZXfd5fKUd3MaccsVcNrbSkrf/zUOxK6jxLPK14frhmdPjsONZzqcQGJ3hqcdznr8RcgZOT2lw58945ixhIBQO+YQUlwf/d3ENlXljJQILqJFQZ0MES5XJFPFe22G+zpaTKfHNrMXnItcodgCMt423tgBjJJ/rK4ykvAp1rqd1u4HAWaQ7vrozxa1xoJ713RjpiDpzFnhpWpbz5KejPhxkH5V13uqlQIDAQAB";
/*     */ 
/*     */   @RequestMapping(params={"appNotify"})
/*     */   public String appNotify(HttpServletRequest request)
/*     */     throws UnsupportedEncodingException
/*     */   {
/*  63 */     Map params = new HashMap();
/*  64 */     Map requestParams = request.getParameterMap();
/*  65 */     for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
/*  66 */       String name = (String)iter.next();
/*  67 */       String[] values = (String[])requestParams.get(name);
/*  68 */       String valueStr = "";
/*  69 */       for (int i = 0; i < values.length; i++) {
/*  70 */         valueStr = valueStr + values[i] + ",";
/*     */       }
/*     */ 
/*  74 */       params.put(name, valueStr);
/*     */     }
/*  76 */     String out_trade_no = request.getParameter("out_trade_no");
/*  77 */     boolean signVerified = false;
/*     */     try {
/*  79 */       signVerified = AlipaySignature.rsaCheckV1(params, publicKey, "UTF-8");
/*     */     } catch (AlipayApiException e) {
/*  81 */       e.printStackTrace();
/*  82 */       return "fail";
/*     */     }
/*     */ 
/*  85 */     if (signVerified)
/*     */     {
/*  88 */       String result = updateALiPayOrderStatus(out_trade_no);
/*     */ 
/*  90 */       if (result.equals("success")) {
/*  91 */         return "success";
/*     */       }
/*  93 */       return "fail";
/*     */     }
/*     */ 
/*  97 */     System.out.println("验证失败,不去更新状态");
/*  98 */     return "fail";
/*     */   }
/*     */ 
/*     */   private String updateALiPayOrderStatus(String out_trade_no)
/*     */   {
/* 105 */     String result = "error";
/*     */ 
/* 108 */     WOrderEntity orderEntity = (WOrderEntity)this.wOrderService.findByQueryString("from WOrderEntity where orderNumber= '" + out_trade_no + "'");
/* 109 */     if (orderEntity != null) {
/* 110 */       orderEntity.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
/* 111 */       orderEntity.setPaymentMode(PaymentMode.alipay.toString());
/* 112 */       orderEntity.setBuyStatus(PayStatus.PAY_002.toString());
/*     */ 
/* 114 */       this.wOrderService.saveOrUpdate(orderEntity);
/*     */ 
/* 116 */       this.wOrderRecordService.updateBySqlString("update WOrderRecordEntity set buyStatus='" + PayStatus.PAY_002.toString() + "', paymentMode='" + PaymentMode.alipay.toString() + "' where orderId='" + out_trade_no + "'");
/* 117 */       result = "success";
/*     */     }
/* 119 */     return result;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"giveReward_appNotify"})
/*     */   public String giveReward_appNotify(HttpServletRequest request)
/*     */     throws UnsupportedEncodingException
/*     */   {
/* 132 */     Map params = new HashMap();
/* 133 */     Map requestParams = request.getParameterMap();
/* 134 */     for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
/* 135 */       String name = (String)iter.next();
/* 136 */       String[] values = (String[])requestParams.get(name);
/* 137 */       String valueStr = "";
/* 138 */       for (int i = 0; i < values.length; i++) {
/* 139 */         valueStr = valueStr + values[i] + ",";
/*     */       }
/*     */ 
/* 143 */       params.put(name, valueStr);
/*     */     }
/*     */ 
/* 146 */     boolean signVerified = false;
/*     */     try {
/* 148 */       signVerified = AlipaySignature.rsaCheckV1(params, publicKey, "UTF-8");
/*     */     } catch (AlipayApiException e) {
/* 150 */       e.printStackTrace();
/* 151 */       return "failure";
/*     */     }
/*     */ 
/* 154 */     if (signVerified)
/*     */     {
/* 157 */       String out_trade_no = request.getParameter("body");
/* 158 */       String total_amount = request.getParameter("total_amount");
/* 159 */       WOrderEntity orderEntity = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, out_trade_no);
/* 160 */       String orderPersonId = orderEntity.getOrderPersonId();
/* 161 */       String issuerId = orderEntity.getIssuerId();
/* 162 */       TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.getEntity(TSBaseUser.class, orderPersonId);
/* 163 */       TSBaseUser tSBaseUser_issuer = (TSBaseUser)this.tSBaseUserService.getEntity(TSBaseUser.class, issuerId);
/* 164 */       String balance_temp = tSBaseUser.getBalance();
/* 165 */       if ((balance_temp == null) || ("".equals(balance_temp))) {
/* 166 */         balance_temp = "0";
/*     */       }
/* 168 */       BigDecimal totalAmount = new BigDecimal(total_amount);
/* 169 */       BigDecimal balanceTemp = new BigDecimal(balance_temp);
/* 170 */       String balance = balanceTemp.add(totalAmount).toString();
/* 171 */       tSBaseUser.setBalance(balance);
/* 172 */       this.tSBaseUserService.saveOrUpdate(tSBaseUser);
/*     */ 
/* 174 */       WCapitalEntity wCapital = new WCapitalEntity();
/* 175 */       wCapital.setId(UUIDUtil.getId());
/* 176 */       wCapital.setAmout(totalAmount.toString());
/* 177 */       wCapital.setUserId(orderPersonId);
/* 178 */       wCapital.setUserName(tSBaseUser.getUserName());
/* 179 */       wCapital.setPhone(tSBaseUser.getPhone());
/* 180 */       wCapital.setTradeTime(new Date());
/* 181 */       wCapital.setDescription("打赏收入");
/* 182 */       wCapital.setOrderId(out_trade_no);
/* 183 */       wCapital.setType(capitalType.income.toString());
/* 184 */       this.wCapitalService.save(wCapital);
/*     */ 
/* 186 */       WCapitalEntity wCapitalA = new WCapitalEntity();
/* 187 */       wCapitalA.setId(UUIDUtil.getId());
/* 188 */       wCapitalA.setAmout(totalAmount.toString());
/* 189 */       wCapitalA.setUserId(issuerId);
/* 190 */       wCapitalA.setUserName(tSBaseUser_issuer.getUserName());
/* 191 */       wCapitalA.setPhone(tSBaseUser_issuer.getPhone());
/* 192 */       wCapitalA.setTradeTime(new Date());
/* 193 */       wCapitalA.setDescription("打赏支出");
/* 194 */       wCapitalA.setOrderId(out_trade_no);
/* 195 */       wCapitalA.setType(capitalType.expenditure.toString());
/* 196 */       this.wCapitalService.save(wCapitalA);
/*     */ 
/* 198 */       return "success";
/*     */     }
/*     */ 
/* 202 */     System.out.println("验证失败,不去更新状态");
/* 203 */     return "failure";
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.controller.alipay.AppAlipayController
 * JD-Core Version:    0.6.2
 */