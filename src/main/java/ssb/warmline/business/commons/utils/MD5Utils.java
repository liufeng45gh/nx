/*    */ package ssb.warmline.business.commons.utils;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.security.MessageDigest;
/*    */ 
/*    */ public final class MD5Utils
/*    */ {
/*    */   public static final String MD5(String s)
/*    */   {
/* 15 */     char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*    */     try {
/* 17 */       byte[] btInput = s.getBytes();
/*    */ 
/* 19 */       MessageDigest mdInst = MessageDigest.getInstance("MD5");
/*    */ 
/* 21 */       mdInst.update(btInput);
/*    */ 
/* 23 */       byte[] md = mdInst.digest();
/*    */ 
/* 25 */       int j = md.length;
/* 26 */       char[] str = new char[j * 2];
/* 27 */       int k = 0;
/* 28 */       for (int i = 0; i < j; i++) {
/* 29 */         byte byte0 = md[i];
/* 30 */         str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
/* 31 */         str[(k++)] = hexDigits[(byte0 & 0xF)];
/*    */       }
/* 33 */       return new String(str);
/*    */     } catch (Exception e) {
/* 35 */       e.printStackTrace();
/* 36 */     }return null;
/*    */   }
/*    */ 
/*    */   public static void main(String[] arg)
/*    */   {
/* 42 */     System.out.println(MD5("asdf1234"));
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.MD5Utils
 * JD-Core Version:    0.6.2
 */