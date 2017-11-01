/*     */ package ssb.warmline.business.commons.config;
/*     */ 
/*     */ import com.alipay.api.AlipayApiException;
/*     */ import com.alipay.api.internal.util.AlipaySignature;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*     */ import ssb.warmline.business.commons.enums.Orderstatu;
/*     */ import ssb.warmline.business.commons.enums.PayStatus;
/*     */ import ssb.warmline.business.commons.enums.PaymentMode;
/*     */ import ssb.warmline.business.commons.enums.capitalType;
/*     */ import ssb.warmline.business.commons.shortMessage.PhoneCode;
/*     */ import ssb.warmline.business.entity.wcapital.WCapitalEntity;
/*     */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*     */ import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
/*     */ import ssb.warmline.business.service.wcapital.WCapitalServiceI;
/*     */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*     */ import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;
/*     */ import ssb.warmline.business.service.wterritory.WTerritoryBusinessServiceI;
/*     */ 
/*     */ public class AppAliPayServlet extends HttpServlet
/*     */ {
/*  51 */   protected static final Logger log = LoggerFactory.getLogger(AppAliPayServlet.class);
/*     */ 
/*  53 */   static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp9WAfqfYC9EBGO5J/UaRpHuWO5BuKBmSFS42YwKbsZVJN66C/N7i4Dz7+aW2scGM+3Y6GfNaCMOJFbSAAK8C756EohURRecIzdDtRY8VBXArq5TYWVHlJKNqZXfd5fKUd3MaccsVcNrbSkrf/zUOxK6jxLPK14frhmdPjsONZzqcQGJ3hqcdznr8RcgZOT2lw58945ixhIBQO+YQUlwf/d3ENlXljJQILqJFQZ0MES5XJFPFe22G+zpaTKfHNrMXnItcodgCMt423tgBjJJ/rK4ykvAp1rqd1u4HAWaQ7vrozxa1xoJ713RjpiDpzFnhpWpbz5KejPhxkH5V13uqlQIDAQAB";
/*     */ 
/*  55 */   private ApplicationContext context = null;
/*     */ 
/*     */   public void init() throws ServletException
/*     */   {
/*  59 */     this.context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
/*     */   }
/*     */ 
/*     */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*     */   {
/*  64 */     doPost(req, resp);
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*     */   {
/*  69 */     appNotify(req, resp);
/*     */   }
/*     */ 
/*     */   public void appNotify(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  81 */     Map params = new HashMap();
/*  82 */     Map requestParams = request.getParameterMap();
/*  83 */     for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
/*  84 */       String name = (String)iter.next();
/*  85 */       String[] values = (String[])requestParams.get(name);
/*  86 */       String valueStr = "";
/*  87 */       for (int i = 0; i < values.length; i++) {
/*  88 */         valueStr = valueStr + values[i] + ",";
/*     */       }
/*     */ 
/*  92 */       params.put(name, valueStr);
/*     */     }
/*  94 */     String orderNumber = request.getParameter("out_trade_no");
/*  95 */     boolean signVerified = false;
/*     */     try
/*     */     {
/*  99 */       PrintWriter out = response.getWriter();
/*     */       try {
/* 101 */         signVerified = AlipaySignature.rsaCheckV1(params, publicKey, "utf-8", "RSA2");
/*     */       } catch (AlipayApiException e) {
/* 103 */         e.printStackTrace();
/* 104 */         out.print("failure");
/*     */       }
/*     */ 
/* 107 */       if (signVerified)
/*     */       {
/* 110 */         String result = updateALiPayOrderStatus(orderNumber);
/* 111 */         if (result.equals("success"))
/*     */         {
/* 113 */           out.print("success");
/*     */         }
/*     */         else
/* 116 */           out.print("failure");
/*     */       }
/*     */       else
/*     */       {
/* 120 */         System.out.println("验证失败,不去更新状态");
/* 121 */         out.print("failure");
/*     */       }
/*     */     } catch (IOException e1) {
/* 124 */       e1.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private String updateALiPayOrderStatus(String orderNumber)
/*     */   {
/* 131 */     WOrderServiceI wOrderService = (WOrderServiceI)this.context.getBean(WOrderServiceI.class);
/* 132 */     WOrderRecordServiceI orderRecordService = (WOrderRecordServiceI)this.context.getBean(WOrderRecordServiceI.class);
/* 133 */     WCapitalServiceI wCapitalService = (WCapitalServiceI)this.context.getBean(WCapitalServiceI.class);
/* 134 */     WTerritoryBusinessServiceI wTerritoryBusinessService = (WTerritoryBusinessServiceI)this.context.getBean(WTerritoryBusinessServiceI.class);
/* 135 */     TSBaseUserServiceI tsBaseUserService = (TSBaseUserServiceI)this.context.getBean(TSBaseUserServiceI.class);
/* 136 */     String result = "error";
/*     */ 
/* 138 */     WOrderEntity orderEntity = (WOrderEntity)wOrderService.findUniqueByProperty(WOrderEntity.class, "orderNumber", orderNumber);
/*     */     try {
/* 140 */       if (orderEntity != null) {
/* 141 */         orderEntity.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
/* 142 */         orderEntity.setPaymentMode(PaymentMode.alipay.toString());
/* 143 */         orderEntity.setBuyStatus(PayStatus.PAY_002.toString());
/* 144 */         orderEntity.setPaymentTime(new Date());
/*     */ 
/* 146 */         wOrderService.saveOrUpdate(orderEntity);
/*     */ 
/* 148 */         orderRecordService.updateBySqlString("update w_order_record set buy_status='" + 
/* 149 */           PayStatus.PAY_002.toString() + "', payment_mode='" + PaymentMode.alipay.toString() + 
/* 150 */           "',order_status='" + Orderstatu.ORDERSTATU_001.toString() + 
/* 151 */           "',status_time=now() where order_Number='" + orderNumber + "'");
/*     */ 
/* 153 */         WCapitalEntity capital = new WCapitalEntity();
/*     */ 
/* 155 */         capital.setUserId(orderEntity.getIssuerId());
/*     */ 
/* 157 */         capital.setUserName(orderEntity.getIssuer());
/*     */ 
/* 159 */         capital.setPhone(orderEntity.getPhone());
/*     */ 
/* 161 */         capital.setOrderId(orderEntity.getId());
/*     */ 
/* 163 */         capital.setOrderNumber(orderEntity.getOrderNumber());
/*     */ 
/* 165 */         capital.setAmout(orderEntity.getPrice());
/*     */ 
/* 167 */         capital.setPayMethod(PaymentMode.alipay.toString());
/*     */ 
/* 169 */         capital.setType(capitalType.expenditure.toString());
/* 170 */         capital.setDescription("发布订单支出");
/* 171 */         capital.setTradeTime(new Date());
/* 172 */         wCapitalService.save(capital);
/*     */ 
/* 175 */         if ("0".equals(orderEntity.getSeekStatus()))
/*     */         {
/* 177 */           String city = orderEntity.getCity();
/* 178 */           if ((!"".equals(city)) && (city != null)) {
/* 179 */             String territoryId = orderEntity.getTerritoryId();
/*     */ 
/* 181 */             List tsBaseUser = tsBaseUserService.findByProperty(TSBaseUser.class, "territoryId", territoryId);
/* 182 */             for (int i = 0; i < tsBaseUser.size(); i++) {
/* 183 */               TSBaseUser tsBaseUser2 = (TSBaseUser)tsBaseUser.get(i);
/* 184 */               if ((!"".equals(tsBaseUser2.getPhone())) && (tsBaseUser2.getPhone() != null))
/*     */               {
/* 186 */                 if ((tsBaseUser2.getAreaCode() != null) && (!"".equals(tsBaseUser2.getAreaCode()))) {
/* 187 */                   String phones = tsBaseUser2.getAreaCode() + tsBaseUser2.getPhone();
/* 188 */                   String content = "【暖行】 你所负责的" + city + "区有人发布紧急订单寻求帮助，订单号为： " + orderNumber + ", 详情请登录系统进行查看。";
/* 189 */                   PhoneCode.getLoginPhoneOrderCode(phones, content);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 195 */         result = "success";
/*     */       }
/*     */     } catch (Exception e) {
/* 198 */       e.printStackTrace();
/*     */     }
/* 200 */     return result;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.AppAliPayServlet
 * JD-Core Version:    0.6.2
 */