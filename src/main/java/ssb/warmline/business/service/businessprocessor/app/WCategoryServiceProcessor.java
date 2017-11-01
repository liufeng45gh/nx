/*     */ package ssb.warmline.business.service.businessprocessor.app;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import ssb.warmline.business.commons.config.ResponseObject;
/*     */ import ssb.warmline.business.commons.utils.BaiDuUtil;
/*     */ import ssb.warmline.business.commons.utils.CommonUtils;
/*     */ import ssb.warmline.business.entity.territoryandcategory.WTerritoryCategoryEntity;
/*     */ import ssb.warmline.business.entity.wterritory.WTerritoryBusiness;
/*     */ import ssb.warmline.business.service.businessprocessor.BaseInterface;
/*     */ import ssb.warmline.business.service.wcategory.WCategoryServiceI;
/*     */ import ssb.warmline.business.service.wterritory.WTerritoryServiceI;
/*     */ 
/*     */ @Service
/*     */ public class WCategoryServiceProcessor extends BaseInterface
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @Autowired
/*     */   private WCategoryServiceI WCategoryService;
/*     */ 
/*     */   @Autowired
/*     */   private WTerritoryServiceI territoryService;
/*     */ 
/*     */   public ResponseObject categoryList(String uid, String token, String latitude, String longitude, String territoryName, String stateTag, String hontCityName)
/*     */     throws ClientProtocolException, IOException
/*     */   {
/*  49 */     Map jsonMap = new HashMap();
/*  50 */     HashMap hsPrice = new HashMap();
/*  51 */     if ("Thailand".equals(stateTag))
/*     */     {
/*  54 */       String sql_territory_city = " FROM WTerritoryBusiness t where t.territoryname = '" + territoryName + "'";
/*  55 */       WTerritoryBusiness werritory_city = null;
/*  56 */       List werritoryList = this.territoryService.findByQueryString(sql_territory_city);
/*  57 */       if ((werritoryList != null) && (werritoryList.size() > 0)) {
/*  58 */         werritory_city = (WTerritoryBusiness)werritoryList.get(0);
/*     */ 
/*  60 */         if ((hontCityName != null) && (!"".equals(hontCityName))) {
/*  61 */           sql_territory_city = " FROM WTerritoryBusiness t where t.territoryname = '" + hontCityName + "'";
/*  62 */           List hontCityList = this.territoryService.findByQueryString(sql_territory_city);
/*  63 */           WTerritoryBusiness hont_city = (WTerritoryBusiness)hontCityList.get(0);
/*  64 */           jsonMap.put("territoryId", hont_city.getId());
/*     */         } else {
/*  66 */           jsonMap.put("territoryId", werritory_city.getId());
/*     */         }
/*     */       } else {
/*  69 */         return CommonUtils.repsonseOjbectToClientWithBody("20055", null, new String[0]);
/*     */       }
/*  71 */       List territoryCategoryList = this.systemService.findByProperty(WTerritoryCategoryEntity.class, "territoryId", werritory_city.getId());
/*     */ 
/*  73 */       HashSet hs = new HashSet();
/*  74 */       if ((territoryCategoryList != null) && (territoryCategoryList.size() > 0))
/*     */       {
/*  76 */         for (int i = 0; i < territoryCategoryList.size(); i++) {
/*  77 */           WTerritoryCategoryEntity tc = (WTerritoryCategoryEntity)territoryCategoryList.get(i);
/*     */ 
/*  79 */           hsPrice.put(tc.getCategoryId(), tc.getPrice().toString());
/*  80 */           if ((tc.getCategoryparentid() != null) && (!"0".equals(tc.getCategoryparentid()))) {
/*  81 */             hs.add("'" + tc.getCategoryparentid() + "'");
/*     */           }
/*  83 */           hs.add("'" + tc.getCategoryId() + "'");
/*     */         }
/*  85 */         String categoryIdsTemp = hs.toString();
/*     */ 
/*  87 */         String categoryIds = categoryIdsTemp.substring(1, categoryIdsTemp.length() - 1);
/*     */ 
/*  89 */         String sql = " FROM WCategoryEntity c where c.id in (" + categoryIds + ")";
/*  90 */         List listCategoryTemp = this.WCategoryService.findByQueryString(sql);
/*  91 */         JSONArray JsonListCategory = JSONArray.fromObject(listCategoryTemp);
/*     */ 
/*  93 */         JSONArray childMenu = treeList(JsonListCategory, "0", hsPrice);
/*  94 */         jsonMap.put("childMenu", childMenu);
/*     */ 
/*  96 */         jsonMap.put("uid", uid);
/*  97 */         jsonMap.put("token", token);
/*  98 */         return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */       }
/* 100 */       return CommonUtils.repsonseOjbectToClientWithBody("20026", null, new String[0]);
/*     */     }
/*     */ 
/* 105 */     if ("China".equals(stateTag))
/*     */     {
/* 107 */       JSONObject objAll = BaiDuUtil.getAdreess(latitude, longitude);
/* 108 */       System.out.println("********************************objAll**************objAll*******objAll**********************" + objAll.toString());
/* 109 */       String status = objAll.getString("status");
/* 110 */       if ("0".equals(status)) {
/* 111 */         JSONObject addressComponent = objAll.getJSONObject("result").getJSONObject("addressComponent");
/* 112 */         String city = addressComponent.getString("city");
/* 113 */         String district = addressComponent.getString("district");
/*     */ 
/* 115 */         String sql_territory_city = " FROM WTerritoryBusiness t where t.territoryname = '" + city + "'";
/* 116 */         List werritoryList = this.territoryService.findByQueryString(sql_territory_city);
/* 117 */         WTerritoryBusiness werritory_district = null;
/* 118 */         if ((werritoryList != null) && (werritoryList.size() > 0)) {
/* 119 */           WTerritoryBusiness werritory_city = (WTerritoryBusiness)werritoryList.get(0);
/*     */ 
/* 121 */           if (("1".equals(werritory_city.getTerritoryparentid())) || ("19".equals(werritory_city.getTerritoryparentid())) || ("2323".equals(werritory_city.getTerritoryparentid())) || ("801".equals(werritory_city.getTerritoryparentid()))) {
/* 122 */             String sql_territory_district = " FROM WTerritoryBusiness t where t.territoryname = '" + district + "'  and  t.territoryparentid= '" + werritory_city.getId() + "'";
/* 123 */             List werritory_List_district = this.territoryService.findByQueryString(sql_territory_district);
/* 124 */             werritory_district = (WTerritoryBusiness)werritory_List_district.get(0);
/*     */           } else {
/* 126 */             werritory_district = werritory_city;
/*     */           }
/*     */         }
/* 129 */         List territoryCategoryList = this.systemService.findByProperty(WTerritoryCategoryEntity.class, "territoryId", werritory_district.getId());
/* 130 */         HashSet hs = new HashSet();
/* 131 */         if ((territoryCategoryList != null) && (territoryCategoryList.size() > 0))
/*     */         {
/* 133 */           for (int i = 0; i < territoryCategoryList.size(); i++) {
/* 134 */             WTerritoryCategoryEntity tc = (WTerritoryCategoryEntity)territoryCategoryList.get(i);
/*     */ 
/* 136 */             hsPrice.put(tc.getCategoryId(), tc.getPrice().toString());
/* 137 */             if ((tc.getCategoryparentid() != null) && (!"0".equals(tc.getCategoryparentid()))) {
/* 138 */               hs.add("'" + tc.getCategoryparentid() + "'");
/*     */             }
/* 140 */             hs.add("'" + tc.getCategoryId() + "'");
/*     */           }
/* 142 */           String categoryIdsTemp = hs.toString();
/*     */ 
/* 144 */           String categoryIds = categoryIdsTemp.substring(1, categoryIdsTemp.length() - 1);
/*     */ 
/* 146 */           String sql = " FROM WCategoryEntity c where c.id in (" + categoryIds + ")";
/* 147 */           List listCategoryTemp = this.WCategoryService.findByQueryString(sql);
/* 148 */           JSONArray JsonListCategory = JSONArray.fromObject(listCategoryTemp);
/*     */ 
/* 150 */           JSONArray childMenu = treeList(JsonListCategory, "0", hsPrice);
/* 151 */           jsonMap.put("childMenu", childMenu);
/* 152 */           jsonMap.put("uid", uid);
/* 153 */           jsonMap.put("token", token);
/* 154 */           return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */         }
/* 156 */         return CommonUtils.repsonseOjbectToClientWithBody("20026", null, new String[0]);
/*     */       }
/*     */ 
/* 160 */       jsonMap.put("status", status);
/* 161 */       return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */     }
/*     */ 
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */   public JSONArray treeList(JSONArray menuList, String parentId, Map hsPrice)
/*     */   {
/* 208 */     JSONArray childMenu = new JSONArray();
/*     */ 
/* 210 */     for (Iterator localIterator = menuList.iterator(); localIterator.hasNext(); ) { Object object = localIterator.next();
/*     */ 
/* 212 */       JSONObject jsonMenu_temp = JSONObject.fromObject(object);
/*     */ 
/* 214 */       JSONObject jsonMenu = mapAddJson(jsonMenu_temp, hsPrice);
/* 215 */       String menuId = (String)jsonMenu.get("id");
/* 216 */       String pid = (String)jsonMenu.get("categoryParentid");
/*     */ 
/* 218 */       if (parentId.equals(pid)) {
/* 219 */         JSONArray c_node = treeList(menuList, menuId, hsPrice);
/* 220 */         jsonMenu.put("childNode", c_node);
/* 221 */         childMenu.add(jsonMenu);
/*     */       }
/*     */     }
/* 224 */     return childMenu;
/*     */   }
/*     */ 
/*     */   public JSONObject mapAddJson(JSONObject jsonMenu, Map hsPrice) {
/* 228 */     String menuId = (String)jsonMenu.get("id");
/* 229 */     Set entries = hsPrice.entrySet();
/* 230 */     if (entries != null) {
/* 231 */       Iterator iterator = entries.iterator();
/* 232 */       while (iterator.hasNext()) {
/* 233 */         Map.Entry entry = (Map.Entry)iterator.next();
/* 234 */         String key = (String)entry.getKey();
/* 235 */         String value = (String)entry.getValue();
/* 236 */         if (menuId.equals(key)) {
/* 237 */           jsonMenu.put("price", value);
/*     */         }
/*     */       }
/*     */     }
/* 241 */     return jsonMenu;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.app.WCategoryServiceProcessor
 * JD-Core Version:    0.6.2
 */