/*    */ package ssb.warmline.business.commons.config;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.math.BigDecimal;
/*    */ import java.math.RoundingMode;
/*    */ 
/*    */ public class GeoTool
/*    */ {
/*    */   private static final double EARTH_RADIUS = 6378.1369999999997D;
/*    */ 
/*    */   public static double getPointDistance(double lat1, double lng1, double lat2, double lng2)
/*    */   {
/* 13 */     double result = 0.0D;
/* 14 */     double radLat1 = radian(lat1);
/* 15 */     double ratlat2 = radian(lat2);
/* 16 */     double a = radian(lat1) - radian(lat2);
/* 17 */     double b = radian(lng1) - radian(lng2);
/* 18 */     result = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) + Math.cos(radLat1) * Math.cos(ratlat2) * Math.pow(Math.sin(b / 2.0D), 2.0D)));
/* 19 */     result *= 6378.1369999999997D;
/*    */ 
/* 21 */     BigDecimal bg = new BigDecimal(result).setScale(2, RoundingMode.UP);
/* 22 */     return bg.doubleValue();
/*    */   }
/*    */ 
/*    */   private static double radian(double d) {
/* 26 */     return d * 3.141592653589793D / 180.0D;
/*    */   }
/*    */   public static void main(String[] args) {
/* 29 */     GeoTool tool = new GeoTool();
/* 30 */     System.out.println(getPointDistance(39.977358000000002D, 116.38382900000001D, 40.427799999999998D, 116.83425200000001D));
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.GeoTool
 * JD-Core Version:    0.6.2
 */