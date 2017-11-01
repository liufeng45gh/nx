/*     */ package ssb.warmline.business.commons.utils;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.List;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.commons.httpclient.HttpClient;
/*     */ import org.apache.commons.httpclient.HttpException;
/*     */ import org.apache.commons.httpclient.HttpMethod;
/*     */ import org.apache.commons.httpclient.methods.GetMethod;
/*     */ import ssb.warmline.business.commons.utils.Google.AddressComponents;
/*     */ import ssb.warmline.business.commons.utils.Google.GoogleMap;
/*     */ import ssb.warmline.business.commons.utils.Google.Item;
/*     */ 
/*     */ public class GoogleUtil
/*     */ {
/*     */   private static final String KEY = "AIzaSyDYcwUiPj7MUzk1l-ETmik2jrth-ReZ7qc";
/*     */ 
/*     */   public static GoogleMap getAdreess(String lat, String lng)
/*     */   {
/*  27 */     JSONObject obj = getLocationInfo(lat, lng);
/*  28 */     Gson gson = new Gson();
/*  29 */     GoogleMap map = (GoogleMap)gson.fromJson(obj.toString(), GoogleMap.class);
/*  30 */     return map;
/*     */   }
/*     */ 
/*     */   public static JSONObject getLocationInfo(String lat, String lng)
/*     */   {
/*  35 */     String latlng = lat + "," + lng;
/*  36 */     String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latlng + "&key=" + "AIzaSyDYcwUiPj7MUzk1l-ETmik2jrth-ReZ7qc" + "&language=zh-CN";
/*  37 */     JSONObject obj = JSONObject.fromObject(GeocodingHttpUtil.getRequest(url, new String[0]));
/*     */ 
/* 155 */     return obj;
/*     */   }
/*     */ 
/*     */   public static GoogleMap getLatitude(String adreessStr)
/*     */   {
/*     */     try
/*     */     {
/* 162 */       String adreess = URLEncoder.encode(adreessStr, "utf-8");
/*     */ 
/* 164 */       String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + adreess + "&key=" + "AIzaSyDYcwUiPj7MUzk1l-ETmik2jrth-ReZ7qc" + "&language=zh-CN";
/* 165 */       System.out.println("**************************打印路径***********************" + url + 
/* 166 */         "*****************************************************");
/* 167 */       String resultData = GeocodingHttpUtil.getRequest(url, new String[0]);
/*     */ 
/* 169 */       System.out.println("*******************json格式返回字符串******************************" + resultData + 
/* 170 */         "*****************************************************");
/* 171 */       JSONObject obj = JSONObject.fromObject(resultData);
/* 172 */       Gson gson = new Gson();
/* 173 */       return (GoogleMap)gson.fromJson(obj.toString(), GoogleMap.class);
/*     */     }
/*     */     catch (UnsupportedEncodingException e) {
/* 176 */       e.printStackTrace();
/*     */     }
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */   public static double getDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude)
/*     */   {
/* 191 */     double lat1 = 0.0174532925199433D * startLatitude;
/* 192 */     double lat2 = 0.0174532925199433D * endLatitude;
/*     */ 
/* 194 */     double lon1 = 0.0174532925199433D * startLongitude;
/* 195 */     double lon2 = 0.0174532925199433D * endLongitude;
/*     */ 
/* 198 */     double R = 6371.0039999999999D;
/*     */ 
/* 201 */     double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * 
/* 202 */       R;
/* 203 */     return d;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 212 */     Gson gson = new Gson();
/* 213 */     String json = getLocationInfo("15.843067", "100.282303").toString();
/* 214 */     GoogleMap map = (GoogleMap)gson.fromJson(json, GoogleMap.class);
/* 215 */     System.out.println(map.getStatus() + "  " + ((Item)map.getResults().get(0)).getFormatted_address());
/* 216 */     System.out.println(
/* 217 */       map.getStatus() + "  " + ((AddressComponents)((Item)map.getResults().get(0)).getAddress_components().get(2)).getShort_name());
/*     */ 
/* 221 */     HttpClient httpClient = new HttpClient();
/*     */ 
/* 223 */     String uri = "https://maps.googleapis.com/maps/api/geocode/json?address=曼谷卧佛寺&key=AIzaSyC-2YygRdNqeW9CVwSD9kw9px2-LGVy_M0";
/* 224 */     HttpMethod method = new GetMethod(uri);
/*     */     try
/*     */     {
/* 227 */       httpClient.executeMethod(method);
/* 228 */       System.out.println("********************//服务器返回状态:" + method.getStatusLine());
/* 229 */       InputStream ins = method.getResponseBodyAsStream();
/*     */ 
/* 231 */       BufferedReader br = new BufferedReader(new InputStreamReader(ins, "utf-8"));
/* 232 */       StringBuffer sbf = new StringBuffer();
/* 233 */       String line = null;
/* 234 */       while ((line = br.readLine()) != null) {
/* 235 */         sbf.append(line);
/*     */       }
/* 237 */       br.close();
/*     */ 
/* 239 */       System.out.println("********************* //返回的内容:" + sbf);
/*     */ 
/* 241 */       method.releaseConnection();
/*     */     } catch (HttpException e) {
/* 243 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 245 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.GoogleUtil
 * JD-Core Version:    0.6.2
 */