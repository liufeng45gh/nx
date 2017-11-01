/*    */ package ssb.warmline.business.commons.config;
/*    */ 
/*    */ import com.alipay.api.AlipayApiException;
/*    */ import com.alipay.api.internal.util.AlipaySignature;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*    */ import ssb.warmline.business.commons.enums.capitalType;
/*    */ import ssb.warmline.business.commons.utils.UUIDUtil;
/*    */ import ssb.warmline.business.entity.wcapital.WCapitalEntity;
/*    */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*    */ import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
/*    */ import ssb.warmline.business.service.wcapital.WCapitalServiceI;
/*    */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*    */ 
/*    */ public class GiveRewardAppAliPayServlet extends HttpServlet
/*    */ {
/* 37 */   protected static final Logger log = LoggerFactory.getLogger(GiveRewardAppAliPayServlet.class);
/*    */ 
/* 39 */   static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp9WAfqfYC9EBGO5J/UaRpHuWO5BuKBmSFS42YwKbsZVJN66C/N7i4Dz7+aW2scGM+3Y6GfNaCMOJFbSAAK8C756EohURRecIzdDtRY8VBXArq5TYWVHlJKNqZXfd5fKUd3MaccsVcNrbSkrf/zUOxK6jxLPK14frhmdPjsONZzqcQGJ3hqcdznr8RcgZOT2lw58945ixhIBQO+YQUlwf/d3ENlXljJQILqJFQZ0MES5XJFPFe22G+zpaTKfHNrMXnItcodgCMt423tgBjJJ/rK4ykvAp1rqd1u4HAWaQ7vrozxa1xoJ713RjpiDpzFnhpWpbz5KejPhxkH5V13uqlQIDAQAB";
/*    */ 
/* 41 */   private ApplicationContext context = null;
/*    */ 
/*    */   public void init() throws ServletException
/*    */   {
/* 45 */     this.context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
/*    */   }
/*    */ 
/*    */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*    */   {
/* 50 */     doPost(req, resp);
/*    */   }
/*    */ 
/*    */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*    */   {
/* 55 */     giveReward_appNotify(req, resp);
/*    */   }
/*    */ 
/*    */   public void giveReward_appNotify(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 66 */     WOrderServiceI wOrderService = (WOrderServiceI)this.context.getBean(WOrderServiceI.class);
/* 67 */     WCapitalServiceI wCapitalService = (WCapitalServiceI)this.context.getBean(WCapitalServiceI.class);
/* 68 */     TSBaseUserServiceI tSBaseUserService = (TSBaseUserServiceI)this.context.getBean(TSBaseUserServiceI.class);
/*    */ 
/* 70 */     Map params = new HashMap();
/* 71 */     Map requestParams = request.getParameterMap();
/* 72 */     for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
/* 73 */       String name = (String)iter.next();
/* 74 */       String[] values = (String[])requestParams.get(name);
/* 75 */       String valueStr = "";
/* 76 */       for (int i = 0; i < values.length; i++) {
/* 77 */         valueStr = valueStr + values[i] + ",";
/*    */       }
/*    */ 
/* 81 */       params.put(name, valueStr);
/*    */     }
/*    */ 
/*    */     try
/*    */     {
/* 86 */       PrintWriter out = response.getWriter();
/*    */ 
/* 88 */       boolean signVerified = false;
/*    */       try {
/* 90 */         signVerified = AlipaySignature.rsaCheckV1(params, publicKey, "UTF-8", "RSA2");
/*    */       } catch (AlipayApiException e) {
/* 92 */         e.printStackTrace();
/* 93 */         out.print("failure");
/*    */       }
/*    */ 
/* 99 */       if (signVerified)
/*    */       {
/* 102 */         String out_trade_no = request.getParameter("passback_params");
/* 103 */         String total_amount = request.getParameter("total_amount");
/* 104 */         WOrderEntity orderEntity = (WOrderEntity)wOrderService.getEntity(WOrderEntity.class, out_trade_no);
/* 105 */         String orderPersonId = orderEntity.getOrderPersonId();
/* 106 */         String issuerId = orderEntity.getIssuerId();
/* 107 */         TSBaseUser tSBaseUser = (TSBaseUser)tSBaseUserService.getEntity(TSBaseUser.class, orderPersonId);
/* 108 */         TSBaseUser tSBaseUser_issuer = (TSBaseUser)tSBaseUserService.getEntity(TSBaseUser.class, issuerId);
/* 109 */         String balance_temp = tSBaseUser.getBalance();
/* 110 */         if ((balance_temp == null) || ("".equals(balance_temp))) {
/* 111 */           balance_temp = "0";
/*    */         }
/* 113 */         BigDecimal totalAmount = new BigDecimal(total_amount);
/* 114 */         BigDecimal balanceTemp = new BigDecimal(balance_temp);
/* 115 */         String balance = balanceTemp.add(totalAmount).toString();
/* 116 */         tSBaseUser.setBalance(balance);
/* 117 */         tSBaseUserService.saveOrUpdate(tSBaseUser);
/*    */ 
/* 119 */         WCapitalEntity wCapital = new WCapitalEntity();
/* 120 */         wCapital.setId(UUIDUtil.getId());
/* 121 */         wCapital.setAmout(totalAmount.toString());
/* 122 */         wCapital.setUserId(orderPersonId);
/* 123 */         wCapital.setUserName(tSBaseUser.getUserName());
/* 124 */         wCapital.setPhone(tSBaseUser.getPhone());
/* 125 */         wCapital.setTradeTime(new Date());
/* 126 */         wCapital.setDescription("打赏收入");
/* 127 */         wCapital.setOrderId(out_trade_no);
/* 128 */         wCapital.setType(capitalType.income.toString());
/* 129 */         wCapitalService.save(wCapital);
/*    */ 
/* 131 */         WCapitalEntity wCapitalA = new WCapitalEntity();
/* 132 */         wCapitalA.setId(UUIDUtil.getId());
/* 133 */         wCapitalA.setAmout(totalAmount.toString());
/* 134 */         wCapitalA.setUserId(issuerId);
/* 135 */         wCapitalA.setUserName(tSBaseUser_issuer.getUserName());
/* 136 */         wCapitalA.setPhone(tSBaseUser_issuer.getPhone());
/* 137 */         wCapitalA.setTradeTime(new Date());
/* 138 */         wCapitalA.setDescription("打赏支出");
/* 139 */         wCapitalA.setOrderId(out_trade_no);
/* 140 */         wCapitalA.setType(capitalType.expenditure.toString());
/* 141 */         wCapitalService.save(wCapitalA);
/* 142 */         out.print("success");
/*    */       }
/*    */       else
/*    */       {
/* 149 */         System.out.println("验证失败,不去更新状态");
/* 150 */         out.print("failure");
/*    */       }
/*    */ 
/*    */     }
/*    */     catch (IOException e1)
/*    */     {
/* 156 */       e1.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.GiveRewardAppAliPayServlet
 * JD-Core Version:    0.6.2
 */