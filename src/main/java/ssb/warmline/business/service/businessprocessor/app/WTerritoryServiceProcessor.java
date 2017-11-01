/*    */ package ssb.warmline.business.service.businessprocessor.app;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import ssb.warmline.business.commons.config.ResponseObject;
/*    */ import ssb.warmline.business.commons.utils.CommonUtils;
/*    */ import ssb.warmline.business.entity.wterritory.WTerritoryBusiness;
/*    */ import ssb.warmline.business.service.businessprocessor.BaseInterface;
/*    */ import ssb.warmline.business.service.wterritory.WTerritoryServiceI;
/*    */ 
/*    */ @Service
/*    */ public class WTerritoryServiceProcessor extends BaseInterface
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private WTerritoryServiceI territoryServiceI;
/*    */ 
/*    */   public ResponseObject phoneAreaCodes(Integer page, Integer rows)
/*    */   {
/* 34 */     Map jsonMap = new HashMap();
/* 35 */     String sql = "SELECT w.ID AS id,w.territorycode AS territorycode,w.territorylevel AS territorylevel,w.territory_pinyin AS territoryPinyin,w.territorysort AS territorysort,w.xwgs84 AS xWgs84,w.ywgs84 AS yWgs84,w.territoryparentid AS territoryparentid,w.territoryname AS territoryname,w.isparent AS isparent,w.`open` AS open,w.area_code AS areacode FROM w_territory w where w.area_code IS NOT NULL";
/* 36 */     List wTerritory = this.territoryServiceI.findObjForJdbc(sql, page.intValue(), rows.intValue(), WTerritoryBusiness.class);
/* 37 */     List areaList = new ArrayList();
/* 38 */     if (wTerritory.size() > 0) {
/* 39 */       for (int a = 0; a < wTerritory.size(); a++) {
/* 40 */         Map map = new HashMap();
/* 41 */         WTerritoryBusiness wTerritoryEntity = (WTerritoryBusiness)wTerritory.get(a);
/* 42 */         map.put("id", wTerritoryEntity.getId());
/* 43 */         map.put("territorylevel", wTerritoryEntity.getTerritorylevel());
/* 44 */         map.put("territorycode", wTerritoryEntity.getTerritorycode());
/* 45 */         map.put("territoryPinyin", wTerritoryEntity.getTerritoryPinyin());
/* 46 */         map.put("territorysort", wTerritoryEntity.getTerritorysort());
/* 47 */         map.put("xWgs84", wTerritoryEntity.getXwgs84());
/* 48 */         map.put("yWgs84", wTerritoryEntity.getYwgs84());
/* 49 */         map.put("open", wTerritoryEntity.getOpen());
/* 50 */         map.put("areacode", wTerritoryEntity.getAreacode());
/* 51 */         map.put("isparent", wTerritoryEntity.getIsparent());
/* 52 */         map.put("territoryparentid", wTerritoryEntity.getTerritoryparentid());
/* 53 */         map.put("territoryname", wTerritoryEntity.getTerritoryname());
/* 54 */         areaList.add(map);
/*    */       }
/*    */     }
/* 57 */     jsonMap.put("areaList", areaList);
/* 58 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*    */   }
/*    */ 
/*    */   public ResponseObject thailandhotCity(String uid, String token, String id)
/*    */   {
/* 67 */     Map jsonMap = new HashMap();
/* 68 */     String hql = "FROM WTerritoryBusiness where ishotcity='1' and out_in='Thailand' and territoryparentid='" + id + "' ORDER BY territorysort asc";
/* 69 */     List hotCityList = this.territoryServiceI.findByQueryString(hql);
/* 70 */     jsonMap.put("uid", uid);
/* 71 */     jsonMap.put("token", token);
/* 72 */     jsonMap.put("hotCityList", hotCityList);
/*    */ 
/* 74 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*    */   }
/*    */ 
/*    */   public ResponseObject hierarchyLoadCity(String territoryParentId, String uid, String token)
/*    */   {
/* 86 */     Map jsonMap = new HashMap();
/* 87 */     if ((territoryParentId == null) || ("".equals(territoryParentId))) {
/* 88 */       territoryParentId = "10000";
/*    */     }
/* 90 */     String hql = "FROM WTerritoryBusiness where territoryparentid='" + territoryParentId + "'  and out_in='Thailand'  ORDER BY territorysort asc";
/* 91 */     List hierarchyCityList = this.territoryServiceI.findByQueryString(hql);
/* 92 */     jsonMap.put("uid", uid);
/* 93 */     jsonMap.put("token", token);
/* 94 */     jsonMap.put("hierarchyCityList", hierarchyCityList);
/*    */ 
/* 96 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.app.WTerritoryServiceProcessor
 * JD-Core Version:    0.6.2
 */