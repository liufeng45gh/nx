/*    */ package ssb.warmline.business.commons.config;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import ssb.warmline.business.commons.utils.CommonUtils;
/*    */ import ssb.warmline.business.commons.utils.Google.Geometry;
/*    */ import ssb.warmline.business.commons.utils.Google.GoogleMap;
/*    */ import ssb.warmline.business.commons.utils.Google.Item;
/*    */ import ssb.warmline.business.commons.utils.Google.Location;
/*    */ import ssb.warmline.business.commons.utils.GoogleUtil;
/*    */ 
/*    */ public class WTerritoryServlet extends HttpServlet
/*    */ {
/* 28 */   protected static final Logger logger = LoggerFactory.getLogger(WTerritoryServlet.class);
/*    */ 
/*    */   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
/*    */     throws ServletException, IOException
/*    */   {
/* 34 */     doPost(req, resp);
/*    */   }
/*    */ 
/*    */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*    */   {
/* 39 */     searchAdreess(req, resp);
/*    */   }
/*    */ 
/*    */   public void searchAdreess(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 51 */     String adreess = request.getParameter("adreess");
/* 52 */     String uid = request.getParameter("uid");
/* 53 */     String token = request.getParameter("token");
/* 54 */     Map jsonMap = new HashMap();
/* 55 */     List resultsList = new ArrayList();
/*    */     try {
/* 57 */       GoogleMap map = GoogleUtil.getLatitude(adreess);
/* 58 */       if ("OK".equals(map.getStatus())) {
/* 59 */         List results = map.getResults();
/* 60 */         for (int i = 0; results.size() > i; i++) {
/* 61 */           Map mapSon = new HashMap();
/* 62 */           Geometry geometry = ((Item)results.get(i)).getGeometry();
/* 63 */           mapSon.put("lat", geometry.getLocation().getLat());
/* 64 */           mapSon.put("lng", geometry.getLocation().getLng());
/*    */ 
/* 70 */           mapSon.put("address", ((Item)results.get(i)).getFormatted_address());
/* 71 */           resultsList.add(mapSon);
/*    */         }
/* 73 */         if ((resultsList == null) || (resultsList.size() <= 0))
/*    */         {
/* 75 */           response.setCharacterEncoding("utf-8");
/* 76 */           CommonUtils.repsonseToClientWithBody(response, "20055", jsonMap, new String[] { "error" });
/*    */         }
/* 78 */         jsonMap.put("uid", uid);
/* 79 */         jsonMap.put("token", token);
/* 80 */         jsonMap.put("resultsList", resultsList);
/* 81 */         System.out.println("**************根据地址查询泰国经纬度**************" + jsonMap + "*************************************");
/*    */ 
/* 83 */         response.setCharacterEncoding("utf-8");
/* 84 */         CommonUtils.repsonseToClientWithBody(response, "10000", jsonMap, new String[] { "success" });
/*    */       } else {
/* 86 */         response.setCharacterEncoding("utf-8");
/* 87 */         CommonUtils.repsonseToClientWithBody(response, "20055", jsonMap, new String[] { "error" });
/*    */       }
/*    */     } catch (Exception e) {
/* 90 */       response.setCharacterEncoding("utf-8");
/* 91 */       CommonUtils.repsonseToClientWithBody(response, "20055", jsonMap, new String[] { "error" });
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.WTerritoryServlet
 * JD-Core Version:    0.6.2
 */