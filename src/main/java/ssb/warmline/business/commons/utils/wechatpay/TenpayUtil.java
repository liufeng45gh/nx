/*     */ package ssb.warmline.business.commons.utils.wechatpay;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ public class TenpayUtil
/*     */ {
/*     */   private static Object Server;
/*     */   private static String QRfromGoogle;
/*     */ 
/*     */   public static String toString(Object obj)
/*     */   {
/*  20 */     if (obj == null) {
/*  21 */       return "";
/*     */     }
/*  23 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   public static int toInt(Object obj)
/*     */   {
/*  34 */     int a = 0;
/*     */     try {
/*  36 */       if (obj != null)
/*  37 */         a = Integer.parseInt(obj.toString());
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/*  41 */     return a;
/*     */   }
/*     */ 
/*     */   public static String getCurrTime()
/*     */   {
/*  49 */     Date now = new Date();
/*  50 */     SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
/*  51 */     String s = outFormat.format(now);
/*  52 */     return s;
/*     */   }
/*     */ 
/*     */   public static String formatDate(Date date)
/*     */   {
/*  61 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
/*  62 */     String strDate = formatter.format(date);
/*  63 */     return strDate;
/*     */   }
/*     */ 
/*     */   public static int buildRandom(int length)
/*     */   {
/*  74 */     int num = 1;
/*  75 */     double random = Math.random();
/*  76 */     if (random < 0.1D) {
/*  77 */       random += 0.1D;
/*     */     }
/*  79 */     for (int i = 0; i < length; i++) {
/*  80 */       num *= 10;
/*     */     }
/*  82 */     return (int)(random * num);
/*     */   }
/*     */ 
/*     */   public static String getCharacterEncoding(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  95 */     if ((request == null) || (response == null)) {
/*  96 */       return "gbk";
/*     */     }
/*     */ 
/*  99 */     String enc = request.getCharacterEncoding();
/* 100 */     if ((enc == null) || ("".equals(enc))) {
/* 101 */       enc = response.getCharacterEncoding();
/*     */     }
/*     */ 
/* 104 */     if ((enc == null) || ("".equals(enc))) {
/* 105 */       enc = "gbk";
/*     */     }
/*     */ 
/* 108 */     return enc;
/*     */   }
/*     */ 
/*     */   public static String URLencode(String content)
/*     */   {
/* 115 */     String URLencode = replace(Server.equals(content), "+", "%20");
/*     */ 
/* 117 */     return URLencode;
/*     */   }
/*     */ 
/*     */   private static String replace(boolean equals, String string, String string2) {
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */   public static long getUnixTime(Date date)
/*     */   {
/* 130 */     if (date == null) {
/* 131 */       return 0L;
/*     */     }
/*     */ 
/* 134 */     return date.getTime() / 1000L;
/*     */   }
/*     */ 
/*     */   public static String date2String(Date date, String formatType)
/*     */   {
/* 157 */     SimpleDateFormat sdf = new SimpleDateFormat(formatType);
/* 158 */     return sdf.format(date);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.wechatpay.TenpayUtil
 * JD-Core Version:    0.6.2
 */