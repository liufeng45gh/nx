/*    */ package ssb.warmline.business.service.businessprocessor.app;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.stereotype.Service;
/*    */ import ssb.warmline.business.commons.config.ResponseObject;
/*    */ import ssb.warmline.business.commons.utils.CommonUtils;
/*    */ import ssb.warmline.business.commons.utils.Google.Geometry;
/*    */ import ssb.warmline.business.commons.utils.Google.GoogleMap;
/*    */ import ssb.warmline.business.commons.utils.Google.Item;
/*    */ import ssb.warmline.business.commons.utils.Google.Location;
/*    */ import ssb.warmline.business.commons.utils.GoogleUtil;
/*    */ import ssb.warmline.business.service.businessprocessor.BaseInterface;
/*    */ 
/*    */ @Service
/*    */ public class WarmLineServiceProcessor extends BaseInterface
/*    */ {
/*    */   public ResponseObject demo(String uid, String token)
/*    */   {
/* 24 */     Map jsonMap = new HashMap();
/* 25 */     jsonMap.put("name", "1");
/* 26 */     jsonMap.put("uid", uid);
/* 27 */     jsonMap.put("token", token);
/* 28 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*    */   }
/*    */ 
/*    */   public ResponseObject searchAdreess(String adreess, String uid, String token)
/*    */   {
/* 39 */     Map jsonMap = new HashMap();
/* 40 */     List resultsList = new ArrayList();
/* 41 */     GoogleMap map = GoogleUtil.getLatitude(adreess);
/* 42 */     if ("OK".equals(map.getStatus())) {
/* 43 */       List results = map.getResults();
/* 44 */       for (int i = 0; results.size() > i; i++) {
/* 45 */         Map mapSon = new HashMap();
/* 46 */         Geometry geometry = ((Item)results.get(i)).getGeometry();
/* 47 */         mapSon.put("lat", geometry.getLocation().getLat());
/* 48 */         mapSon.put("lng", geometry.getLocation().getLng());
/*    */ 
/* 54 */         mapSon.put("address", ((Item)results.get(i)).getFormatted_address());
/* 55 */         resultsList.add(mapSon);
/*    */       }
/* 57 */       jsonMap.put("uid", uid);
/* 58 */       jsonMap.put("token", token);
/* 59 */       jsonMap.put("resultsList", resultsList);
/* 60 */       System.out.println("**************根据地址查询泰国经纬度**************" + jsonMap + "*************************************");
/* 61 */       return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*    */     }
/* 63 */     return CommonUtils.repsonseOjbectToClientWithBody("20055", null, new String[0]);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.app.WarmLineServiceProcessor
 * JD-Core Version:    0.6.2
 */