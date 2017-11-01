/*    */ package ssb.warmline.business.commons.utils.wechatpay;
/*    */ 
/*    */ import java.security.MessageDigest;
/*    */ 
/*    */ public class MD5Util
/*    */ {
/* 40 */   private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", 
/* 41 */     "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
/*    */ 
/*    */   private static String byteArrayToHexString(byte[] b)
/*    */   {
/*  8 */     StringBuffer resultSb = new StringBuffer();
/*  9 */     for (int i = 0; i < b.length; i++) {
/* 10 */       resultSb.append(byteToHexString(b[i]));
/*    */     }
/* 12 */     return resultSb.toString();
/*    */   }
/*    */ 
/*    */   private static String byteToHexString(byte b) {
/* 16 */     int n = b;
/* 17 */     if (n < 0)
/* 18 */       n += 256;
/* 19 */     int d1 = n / 16;
/* 20 */     int d2 = n % 16;
/* 21 */     return hexDigits[d1] + hexDigits[d2];
/*    */   }
/*    */ 
/*    */   public static String MD5Encode(String origin, String charsetname) {
/* 25 */     String resultString = null;
/*    */     try {
/* 27 */       resultString = new String(origin);
/* 28 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 29 */       if ((charsetname == null) || ("".equals(charsetname)))
/* 30 */         resultString = byteArrayToHexString(md.digest(resultString
/* 31 */           .getBytes()));
/*    */       else
/* 33 */         resultString = byteArrayToHexString(md.digest(resultString
/* 34 */           .getBytes(charsetname)));
/*    */     } catch (Exception localException) {
/*    */     }
/* 37 */     return resultString;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.wechatpay.MD5Util
 * JD-Core Version:    0.6.2
 */