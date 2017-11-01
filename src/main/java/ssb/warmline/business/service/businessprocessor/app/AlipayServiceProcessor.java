/*     */ package ssb.warmline.business.service.businessprocessor.app;
/*     */ 
/*     */ import com.alipay.api.AlipayApiException;
/*     */ import com.alipay.api.AlipayClient;
/*     */ import com.alipay.api.DefaultAlipayClient;
/*     */ import com.alipay.api.domain.AlipayTradeAppPayModel;
/*     */ import com.alipay.api.request.AlipayTradeAppPayRequest;
/*     */ import com.alipay.api.response.AlipayTradeAppPayResponse;
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ResourceBundle;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import ssb.warmline.business.commons.utils.JsonReturn;
/*     */ import ssb.warmline.business.commons.utils.UUIDUtil;
/*     */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*     */ import ssb.warmline.business.service.businessprocessor.BaseInterface;
/*     */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*     */ 
/*     */ @Service
/*     */ public class AlipayServiceProcessor extends BaseInterface
/*     */ {
/*  26 */   private static final Logger log = Logger.getLogger(AlipayServiceProcessor.class);
/*     */ 
/*     */   @Autowired
/*     */   private WOrderServiceI wOrderService;
/*  29 */   static String appid = "2017022305828239";
/*     */ 
/*  31 */   static String privateKey = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCconPR8d87fJ15hygLPPXKFp6Im5tDo9XCwoYaIl/oT9TkuNOLMg31ikafI8vW7MIdCWxLSflQdHoJgAO5j7vqoPLpQafUai4huf/T/QpdNOosNDKQaPEBWfgSGvydray8bFqD9CKwoAlXD2zmqArxjg8EtwBEmrSxTaL6c+mhjJlqj53w0rlNk+xhGGMxj3RwCxWlsBS+3KBUmCTjK+XsznogWRqw3F/3WAdgHyssJbILx0kopWAvXx1tYKZnaeItCjyDZ24SQKr2jQ7kBlrP5uvZjgleVx0002BwCOjCI4ke76j04KBKnNAJXiugzhMQYiNbyku8xBqvc20TBeoDAgMBAAECggEBAIHGi0WIK9eioOqe08uz30VnxkQTAOpi5Onvt6IRlvroodA7XnI2r6Afx/F0fB//m7jpJMOgb6N8TdxvJyboct6sKXafgkkZDE2/SLpALgUZoWese7RwbjYbb9C4cVLHIZYgBELN1KwYfISYcDcPRbnrZIAYi21L7Kf2VjFCNnE0u7/Z8m39vnJbOvey/vm5Jij1fu/1GAQMrPn5TLWorvTeKcsFZja9GivQrzbdsp9I8RvA0cHbxP6KnPXiOlSQRj1COo5yjM7E3TptHmdx0kDT2FsYBAp32KWQxPcrlpoo5/IPVLpHHT8MYcegaYJ64t9ibN22oroOhCZ9zNN49fECgYEAzTGlpJ2bXMR44V9i7x5xAdSAF6nv1sxyBRtCxd5vnS2enCqXo51/vjU1F7cbWj8KQn6gylFqS2cwokRpzjZJwSDff88Y9gfzDRZDDss8aOwEKANo7hnexNwnsMRl0it4tuuLWNIUP5gb/2XTMAaZ8XgA6TXoYzoPHqt4Jwi098kCgYEAw2rWsvzaTUrtAzHiFDfRK69QIpXwMKCec/xWxKgklUlNOgbJ7nfqApQYEZzH5UBGgIjwx3sMOR2+n75FabNft2A6CEM5uSquHao6uwBN/6AlPFtlzNPp1dREf8sqesCN7edzsD7hJKpBG2ANTxDpXWiWDHoFrkp+Q/EmycZ+EWsCgYEAostfyqGMMGZkLRI7cNDrAXOxiJS3hug/EdQV/txCGyalburP0exT+/lEvSL13XykTCsgsEM1nMsqp6K1evhRPZsyFrmc6R1XdKuT2jneHLQbfA9wgW8e8XplNvYqfjslE6L+tCJAFFA+7jq/+ZtTKWN2/Y9aZtcaRc/ujLF4uhkCgYEApxov96RCZD/Cxq2cPl+wjON/6eM/HcXrWREZfVau2b1xvUHa4QFHaOcMYdsoEABTiUwQYPP2cszdmanRn15UZfFyDmi17oHxm8paKFhNWl6uGJurRGidl0zJOmIXtBn+A8acZcSPL5Qq0mbjWo4Cj+6ECK07OFvY9u46leYXHXsCgYEAwTQ4JH50R1jWZ3lr0MwWs8zDkM6xEQa2N6FqBJZGMM3m60efLmO5HdRRlfY8n6gS9yQpf0L3LqPODP3Y0f2lZQUdAp6KE8hH1dZvsLxdE6Q3ZtBFuvRS7DxpT9IjvaU+tJMu6Qm1h19HE5LO0T0GXPvM9E1mVbsnvuxYAZh9t5w=";
/*     */ 
/*  33 */   static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp9WAfqfYC9EBGO5J/UaRpHuWO5BuKBmSFS42YwKbsZVJN66C/N7i4Dz7+aW2scGM+3Y6GfNaCMOJFbSAAK8C756EohURRecIzdDtRY8VBXArq5TYWVHlJKNqZXfd5fKUd3MaccsVcNrbSkrf/zUOxK6jxLPK14frhmdPjsONZzqcQGJ3hqcdznr8RcgZOT2lw58945ixhIBQO+YQUlwf/d3ENlXljJQILqJFQZ0MES5XJFPFe22G+zpaTKfHNrMXnItcodgCMt423tgBjJJ/rK4ykvAp1rqd1u4HAWaQ7vrozxa1xoJ713RjpiDpzFnhpWpbz5KejPhxkH5V13uqlQIDAQAB";
/*     */ 
/*  35 */   String result = null;
/*     */ 
/*     */   public JsonReturn aliAppPay(String orderId, String uid, String token, String remoteIp)
/*     */   {
/*  55 */     WOrderEntity orderEntity = (WOrderEntity)this.wOrderService.findUniqueByProperty(WOrderEntity.class, "orderNumber", orderId);
/*  56 */     String orderStr = "";
/*  57 */     String out_trade_no = orderEntity.getOrderNumber();
/*  58 */     String subject = orderEntity.getTitle();
/*  59 */     String total_amount = orderEntity.getPrice();
/*  60 */     String body = orderEntity.getDescription();
/*  61 */     if (body == null) {
/*  62 */       body = "紧急订单";
/*     */     }
/*     */ 
/*  65 */     AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appid, 
/*  66 */       privateKey, "json", "utf-8", publicKey, "RSA2");
/*     */ 
/*  68 */     AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
/*     */ 
/*  70 */     AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
/*  71 */     model.setPassbackParams(URLEncoder.encode(body));
/*  72 */     model.setSubject(subject);
/*  73 */     model.setOutTradeNo(out_trade_no);
/*  74 */     model.setTimeoutExpress("30m");
/*  75 */     model.setTotalAmount(new BigDecimal(total_amount).toString());
/*  76 */     model.setProductCode("QUICK_MSECURITY_PAY");
/*  77 */     request.setBizModel(model);
/*     */ 
/*  80 */     ResourceBundle resourceBundle = ResourceBundle.getBundle("commission");
/*  81 */     String serviceIp = resourceBundle.getString("serviceIp");
/*  82 */     request.setNotifyUrl("http://" + serviceIp + "/appNotify.action");
/*     */     try
/*     */     {
/*  86 */       AlipayTradeAppPayResponse response = (AlipayTradeAppPayResponse)alipayClient.sdkExecute(request);
/*  87 */       orderStr = response.getBody();
/*  88 */       System.out.println(orderStr);
/*     */     } catch (AlipayApiException e) {
/*  90 */       e.printStackTrace();
/*     */     }
/*  92 */     return JsonReturn.toSucResult(orderStr);
/*     */   }
/*     */ 
/*     */   public JsonReturn giveReward_appAlipay(String orderId, String uid, String token, String remoteIp, String totalFee)
/*     */   {
/* 116 */     WOrderEntity orderEntity = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, orderId);
/* 117 */     String out_trade_no = orderEntity.getId();
/* 118 */     String subject = orderEntity.getTitle();
/*     */ 
/* 120 */     AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", appid, 
/* 121 */       privateKey, "json", "utf-8", publicKey, "RSA2");
/*     */ 
/* 123 */     AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
/*     */ 
/* 125 */     AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
/* 126 */     model.setPassbackParams(URLEncoder.encode(out_trade_no));
/* 127 */     model.setSubject(subject);
/* 128 */     model.setOutTradeNo(UUIDUtil.getId());
/* 129 */     model.setTimeoutExpress("30m");
/* 130 */     model.setTotalAmount(new BigDecimal(totalFee).toString());
/* 131 */     model.setProductCode("QUICK_MSECURITY_PAY");
/* 132 */     request.setBizModel(model);
/*     */ 
/* 135 */     ResourceBundle resourceBundle = ResourceBundle.getBundle("commission");
/* 136 */     String serviceIp = resourceBundle.getString("serviceIp");
/* 137 */     request.setNotifyUrl("http://" + serviceIp + "/giveReward_appNotify.action");
/* 138 */     String orderStr = "";
/*     */     try
/*     */     {
/* 141 */       AlipayTradeAppPayResponse response = (AlipayTradeAppPayResponse)alipayClient.sdkExecute(request);
/* 142 */       orderStr = response.getBody();
/* 143 */       System.out.println(orderStr);
/*     */     } catch (AlipayApiException e) {
/* 145 */       e.printStackTrace();
/*     */     }
/* 147 */     return JsonReturn.toSucResult(orderStr);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.app.AlipayServiceProcessor
 * JD-Core Version:    0.6.2
 */