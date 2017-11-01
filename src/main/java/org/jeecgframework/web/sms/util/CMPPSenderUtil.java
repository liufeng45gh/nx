/*     */ package org.jeecgframework.web.sms.util;
/*     */ 
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URLEncoder;
/*     */ import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
/*     */ import org.apache.commons.httpclient.HttpClient;
/*     */ import org.apache.commons.httpclient.methods.PostMethod;
/*     */ import org.apache.commons.httpclient.params.HttpClientParams;
/*     */ import org.apache.commons.httpclient.params.HttpMethodParams;
/*     */ import org.jeecgframework.web.sms.util.msg.util.MsgContainer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class CMPPSenderUtil
/*     */ {
/*  28 */   private static Logger logger = LoggerFactory.getLogger(CMPPSenderUtil.class);
/*     */ 
/*     */   public static String sendMsg(String phone, String content)
/*     */   {
/*     */     try
/*     */     {
/*  41 */       String msg = URLEncoder.encode(content, "utf-8");
/*     */ 
/*  43 */       String url = "";
/*  44 */       HttpClient httpClient = new HttpClient();
/*     */ 
/*  46 */       httpClient.getParams().setAuthenticationPreemptive(true);
/*     */ 
/*  48 */       PostMethod postMethod = new PostMethod(url);
/*     */ 
/*  50 */       postMethod.getParams().setParameter("http.method.retry-handler", 
/*  51 */         new DefaultHttpMethodRetryHandler());
/*  52 */       String result = null;
/*  53 */       byte[] responseBody = null;
/*     */       try
/*     */       {
/*  56 */         int statusCode = httpClient.executeMethod(postMethod);
/*  57 */         if (statusCode != 200) {
/*  58 */           System.err.println("Method failed: " + 
/*  59 */             postMethod.getStatusLine());
/*     */         }
/*     */ 
/*  62 */         responseBody = postMethod.getResponseBody();
/*     */ 
/*  64 */         result = new String(responseBody, "GBK");
/*     */       } catch (IOException e) {
/*  66 */         logger.error(e.toString());
/*     */       }
/*     */       finally
/*     */       {
/*  70 */         postMethod.releaseConnection();
/*     */       }
/*     */ 
/*  73 */       logger.debug("#调用短信发送接口返回数据\n{}", result);
/*  74 */       JSONObject jsonObject = (JSONObject)JSON.parse(result);
/*  75 */       String code = jsonObject.getString("code");
/*  76 */       if ("R0".equals(code))
/*     */       {
/*  78 */         return "0";
/*     */       }
/*     */ 
/*  81 */       return "1";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  85 */       logger.error(e.toString());
/*     */     }
/*  87 */     return "2";
/*     */   }
/*     */ 
/*     */   public static String sendTMsgs(String phone, String content)
/*     */   {
/* 101 */     String[] phoneAddress = phone.split(",");
/* 102 */     String sendResult = "";
/*     */     try {
/* 104 */       for (int i = 0; i < phoneAddress.length; i++) {
/* 105 */         boolean result = MsgContainer.sendMsg(content, phoneAddress[i]);
/* 106 */         if (!result) {
/* 107 */           sendResult = sendResult + "-号码" + phoneAddress[i] + "发送失败";
/*     */         }
/*     */       }
/*     */ 
/* 111 */       return sendResult;
/*     */     } catch (Exception e) {
/* 113 */       e.printStackTrace();
/* 114 */     }return "fasle";
/*     */   }
/*     */ 
/*     */   public static String sendDifferenceNetMsg(String phone, String content)
/*     */   {
/*     */     try
/*     */     {
/* 129 */       String msg = URLEncoder.encode(content, "utf-8");
/*     */ 
/* 131 */       String url = "";
/* 132 */       HttpClient httpClient = new HttpClient();
/*     */ 
/* 134 */       httpClient.getParams().setAuthenticationPreemptive(true);
/*     */ 
/* 136 */       PostMethod postMethod = new PostMethod(url);
/*     */ 
/* 138 */       postMethod.getParams().setParameter("http.method.retry-handler", 
/* 139 */         new DefaultHttpMethodRetryHandler());
/* 140 */       String result = null;
/* 141 */       byte[] responseBody = null;
/*     */       try
/*     */       {
/* 144 */         int statusCode = httpClient.executeMethod(postMethod);
/* 145 */         if (statusCode != 200) {
/* 146 */           System.err.println("Method failed: " + 
/* 147 */             postMethod.getStatusLine());
/*     */         }
/*     */ 
/* 150 */         responseBody = postMethod.getResponseBody();
/*     */ 
/* 152 */         result = new String(responseBody, "GBK");
/*     */       } catch (IOException e) {
/* 154 */         e.printStackTrace();
/*     */       }
/*     */       finally {
/* 157 */         postMethod.releaseConnection();
/*     */       }
/*     */ 
/* 160 */       logger.debug("#调用短信发送接口返回数据\n{}", result);
/* 161 */       JSONObject jsonObject = (JSONObject)JSON.parse(result);
/* 162 */       String code = jsonObject.getString("code");
/* 163 */       if ("R0".equals(code))
/*     */       {
/* 165 */         return "0";
/*     */       }
/*     */ 
/* 168 */       return "1";
/*     */     }
/*     */     catch (Exception e) {
/* 171 */       e.printStackTrace();
/*     */     }
/* 173 */     return "2";
/*     */   }
/*     */ 
/*     */   public static String sendMessage(String phone, String content, String exCode)
/*     */   {
/*     */     try
/*     */     {
/* 190 */       String msg = URLEncoder.encode(content, "utf-8");
/*     */ 
/* 192 */       String url = "";
/* 193 */       HttpClient httpClient = new HttpClient();
/*     */ 
/* 195 */       httpClient.getParams().setAuthenticationPreemptive(true);
/*     */ 
/* 197 */       PostMethod postMethod = new PostMethod(url);
/*     */ 
/* 199 */       postMethod.getParams().setParameter("http.method.retry-handler", 
/* 200 */         new DefaultHttpMethodRetryHandler());
/* 201 */       String result = null;
/* 202 */       byte[] responseBody = null;
/*     */       try
/*     */       {
/* 205 */         int statusCode = httpClient.executeMethod(postMethod);
/* 206 */         if (statusCode != 200) {
/* 207 */           System.err.println("Method failed: " + 
/* 208 */             postMethod.getStatusLine());
/*     */         }
/*     */ 
/* 211 */         responseBody = postMethod.getResponseBody();
/*     */ 
/* 213 */         result = new String(responseBody, "GBK");
/*     */       } catch (IOException e) {
/* 215 */         e.printStackTrace();
/*     */       }
/*     */       finally {
/* 218 */         postMethod.releaseConnection();
/*     */       }
/* 220 */       logger.debug("#调用短信发送接口返回数据\n{}", result);
/* 221 */       JSONObject jsonObject = (JSONObject)JSON.parse(result);
/* 222 */       String code = jsonObject.getString("code");
/* 223 */       if ("R0".equals(code))
/*     */       {
/* 225 */         return "0";
/*     */       }
/*     */ 
/* 228 */       return "1";
/*     */     }
/*     */     catch (Exception e) {
/* 231 */       e.printStackTrace();
/*     */     }
/* 233 */     return "2";
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.CMPPSenderUtil
 * JD-Core Version:    0.6.2
 */