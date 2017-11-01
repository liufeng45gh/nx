/*    */ package ssb.warmline.business.commons.utils.wechatpay;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class HttpUtil
/*    */ {
/* 14 */   private static final Logger log = Logger.getLogger(HttpUtil.class);
/*    */   private static final int CONNECT_TIMEOUT = 5000;
/*    */   private static final String DEFAULT_ENCODING = "UTF-8";
/*    */ 
/*    */   public static String postData(String urlStr, String data)
/*    */   {
/* 19 */     return postData(urlStr, data, null);
/*    */   }
/*    */ 
/*    */   public static String postData(String urlStr, String data, String contentType) {
/* 23 */     BufferedReader reader = null;
/*    */     try {
/* 25 */       URL url = new URL(urlStr);
/* 26 */       URLConnection conn = url.openConnection();
/* 27 */       conn.setDoOutput(true);
/* 28 */       conn.setConnectTimeout(5000);
/* 29 */       conn.setReadTimeout(5000);
/* 30 */       if (contentType != null)
/* 31 */         conn.setRequestProperty("content-type", contentType);
/* 32 */       OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
/* 33 */       if (data == null)
/* 34 */         data = "";
/* 35 */       writer.write(data);
/* 36 */       writer.flush();
/* 37 */       writer.close();
/*    */ 
/* 39 */       reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
/* 40 */       StringBuilder sb = new StringBuilder();
/* 41 */       String line = null;
/* 42 */       while ((line = reader.readLine()) != null) {
/* 43 */         sb.append(line);
/* 44 */         sb.append("\r\n");
/*    */       }
/* 46 */       return sb.toString();
/*    */     } catch (IOException e) {
/* 48 */       log.error("Error connecting to " + urlStr + ": " + e.getMessage());
/*    */     } finally {
/*    */       try {
/* 51 */         if (reader != null)
/* 52 */           reader.close();
/*    */       } catch (IOException localIOException3) {
/*    */       }
/*    */     }
/* 56 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.wechatpay.HttpUtil
 * JD-Core Version:    0.6.2
 */