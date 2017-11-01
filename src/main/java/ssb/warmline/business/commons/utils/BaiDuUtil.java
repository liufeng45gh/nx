/*    */ package ssb.warmline.business.commons.utils;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import net.sf.json.JSONObject;
/*    */ 
/*    */ public class BaiDuUtil
/*    */ {
/*    */   private static final String AK = "CpmI1xGsPm4c7E5EGQaZU4wQ5KNssxGk";
/*    */   private static final String SERVICE_AK = "YT9nhGfPIWhDsquRXeG9jA60PfUSMZhE";
/*    */   private static final String MCODE = "C2:CC:0E:FF:7F:E7:0A:2F:37:D7:44:EC:23:DE:BC:CD:F0:C9:86:FE;androidapp.jellal.nuanxing";
/*    */ 
/*    */   public static JSONObject getAdreess(String lat, String lng)
/*    */   {
/* 13 */     JSONObject objAll = getLocationInfo(lat, lng);
/* 14 */     return objAll;
/*    */   }
/*    */ 
/*    */   public static JSONObject getLocationInfo(String lat, String lng)
/*    */   {
/* 19 */     String url = "http://api.map.baidu.com/geocoder/v2/?location=" + lat + "," + lng + "&output=json&ak=" + "YT9nhGfPIWhDsquRXeG9jA60PfUSMZhE" + "&pois=0";
/* 20 */     JSONObject obj = JSONObject.fromObject(GeocodingHttpUtil.getRequest(url, new String[0]));
/* 21 */     return obj;
/*    */   }
/*    */ 
/*    */   public static JSONObject getLatitude(String adreess)
/*    */   {
/* 27 */     String url = "http://api.map.baidu.com/geocoder/v2/?address=" + adreess + "&output=json&ak=" + "CpmI1xGsPm4c7E5EGQaZU4wQ5KNssxGk" + "&mcode=" + "C2:CC:0E:FF:7F:E7:0A:2F:37:D7:44:EC:23:DE:BC:CD:F0:C9:86:FE;androidapp.jellal.nuanxing" + "&callback=showLocation";
/* 28 */     JSONObject obj = JSONObject.fromObject(GeocodingHttpUtil.getRequest(url, new String[0]));
/* 29 */     return obj;
/*    */   }
/*    */ 
/*    */   public static double getDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude)
/*    */   {
/* 41 */     double lat1 = 0.0174532925199433D * startLatitude;
/* 42 */     double lat2 = 0.0174532925199433D * endLatitude;
/*    */ 
/* 44 */     double lon1 = 0.0174532925199433D * startLongitude;
/* 45 */     double lon2 = 0.0174532925199433D * endLongitude;
/*    */ 
/* 48 */     double R = 6371.0039999999999D;
/*    */ 
/* 51 */     double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
/* 52 */     return d;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 60 */     System.out.println(getLocationInfo("39.976898", "116.384018"));
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.BaiDuUtil
 * JD-Core Version:    0.6.2
 */