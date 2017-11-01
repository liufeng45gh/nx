/*    */ package ssb.warmline.business.commons.utils;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class NewDate
/*    */ {
/*    */   public static Date timeStamp2Date10(String seconds)
/*    */     throws ParseException
/*    */   {
/* 11 */     String format = "yyyy-MM-dd HH:mm:ss";
/* 12 */     SimpleDateFormat sdf = new SimpleDateFormat(format);
/*    */ 
/* 14 */     String format2 = sdf.format(new Date(Long.valueOf(seconds + "000").longValue()));
/* 15 */     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 16 */     return sdf1.parse(format2);
/*    */   }
/*    */ 
/*    */   public static Date timeStamp2Date13(String seconds)
/*    */     throws ParseException
/*    */   {
/* 22 */     Long timeLong = Long.valueOf(Long.parseLong(seconds));
/* 23 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 24 */     return sdf.parse(sdf.format(timeLong));
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */     throws ParseException
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.NewDate
 * JD-Core Version:    0.6.2
 */