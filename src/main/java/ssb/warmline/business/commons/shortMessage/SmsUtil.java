/*    */ package ssb.warmline.business.commons.shortMessage;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.PrintStream;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import java.net.URLEncoder;
/*    */ 
/*    */ public abstract class SmsUtil
/*    */ {
/*    */   public static void sendSMS(String mobile, String content, String name)
/*    */   {
/* 31 */     System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
/* 32 */     System.setProperty("sun.net.client.defaultReadTimeout", "30000");
/*    */ 
/* 34 */     StringBuffer buffer = new StringBuffer();
/*    */ 
/* 37 */     String encode = "GBK";
/* 38 */     String username = "xnkj";
/*    */ 
/* 40 */     String password_md5 = "1adbb3178591fd5bb0c248518f39bf6d";
/*    */ 
/* 42 */     String apikey = "10ef83eb3a17312af4d5e79eb7a502f7";
/*    */     try {
/* 44 */       String contentUrlEncode = URLEncoder.encode(content, encode);
/*    */ 
/* 46 */       buffer.append("http://m.5c.com.cn/api/send/index.php?username=" + username + "&password_md5=" + password_md5 + "&mobile=" + mobile + "&apikey=" + apikey + "&content=" + contentUrlEncode + "&encode=" + encode);
/*    */ 
/* 49 */       URL url = new URL(buffer.toString());
/*    */ 
/* 51 */       HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/*    */ 
/* 53 */       connection.setRequestMethod("POST");
/*    */ 
/* 55 */       connection.setRequestProperty("Connection", "Keep-Alive");
/*    */ 
/* 57 */       BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
/*    */ 
/* 59 */       String result = reader.readLine();
/*    */ 
/* 61 */       System.out.println(result);
/* 62 */       System.out.println(content);
/*    */     } catch (Exception e) {
/* 64 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 70 */     Integer mobileCode = Integer.valueOf((int)((Math.random() * 9.0D + 1.0D) * 1000.0D));
/*    */ 
/* 73 */     String content = new String("【暖行】您的验证码是：" + mobileCode + "，一分钟内有效。");
/*    */ 
/* 75 */     sendSMS("+8613681181338", content, null);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.shortMessage.SmsUtil
 * JD-Core Version:    0.6.2
 */