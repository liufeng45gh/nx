/*     */ package ssb.warmline.business.service.businessprocessor.app;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.commons.lang.xwork.StringUtils;
/*     */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import ssb.warmline.business.commons.config.ResponseObject;
/*     */ import ssb.warmline.business.commons.enums.OrderType;
/*     */ import ssb.warmline.business.commons.enums.Orderstatu;
/*     */ import ssb.warmline.business.commons.enums.PayStatus;
/*     */ import ssb.warmline.business.commons.utils.BaiDuUtil;
/*     */ import ssb.warmline.business.commons.utils.CommonUtils;
/*     */ import ssb.warmline.business.commons.utils.Google.GoogleMap;
/*     */ import ssb.warmline.business.commons.utils.Google.Item;
/*     */ import ssb.warmline.business.commons.utils.GoogleUtil;
/*     */ import ssb.warmline.business.commons.utils.NewDate;
/*     */ import ssb.warmline.business.commons.utils.keyword.SensitivewordFilter;
/*     */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*     */ import ssb.warmline.business.entity.worderphoto.WOrderPhotoEntity;
/*     */ import ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity;
/*     */ import ssb.warmline.business.entity.worderrecord.WOrderRecordEntity;
/*     */ import ssb.warmline.business.entity.wterritory.WTerritoryBusiness;
/*     */ import ssb.warmline.business.service.businessprocessor.BaseInterface;
/*     */ import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
/*     */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*     */ import ssb.warmline.business.service.worderphoto.WOrderPhotoServiceI;
/*     */ import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;
/*     */ import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;
/*     */ import ssb.warmline.business.service.wterritory.WTerritoryBusinessServiceI;
/*     */ 
/*     */ @Service
/*     */ public class UrgentOrderServiceProcessor extends BaseInterface
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private WOrderServiceI wOrderService;
/*     */ 
/*     */   @Autowired
/*     */   private TSBaseUserServiceI tsBaseUserServiceI;
/*     */ 
/*     */   @Autowired
/*     */   private WOrderRecordServiceI orderRecordService;
/*     */ 
/*     */   @Autowired
/*     */   private HttpServletRequest request;
/*     */ 
/*     */   @Autowired
/*     */   private WOrderPhotoServiceI wOrderPhotoService;
/*     */ 
/*     */   @Autowired
/*     */   private WOrderPhotoMainServiceI wOrderPhotoMainService;
/*     */ 
/*     */   @Autowired
/*     */   private WTerritoryBusinessServiceI wTerritoryBusinessService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*  69 */   static final String separator = File.separator;
/*     */ 
/*     */   public ResponseObject releaseUrgentOrder(String uid, String token)
/*     */   {
/*  75 */     Map jsonMap = new HashMap();
/*     */ 
/*  77 */     String sql = "from WOrderPhotoEntity where uid='" + uid + "' and photoType ='PHOTOTYPE_001'";
/*  78 */     List orderPhoto = this.wOrderPhotoService.findByQueryString(sql);
/*  79 */     if ((orderPhoto != null) && (orderPhoto.size() > 0)) {
/*  80 */       for (int i = 0; i < orderPhoto.size(); i++) {
/*  81 */         WOrderPhotoEntity wOrderPhoto = (WOrderPhotoEntity)orderPhoto.get(i);
/*  82 */         String path_temp = this.request.getSession().getServletContext().getRealPath("");
/*  83 */         String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
/*  84 */         String imgPath = separator + "upload" + separator + "orderImage";
/*     */ 
/*  86 */         String filePath = path + imgPath;
/*  87 */         File fileDir = new File(filePath + separator + wOrderPhoto.getPhotoName());
/*  88 */         fileDir.delete();
/*  89 */         this.wOrderPhotoService.delete(wOrderPhoto);
/*     */       }
/*     */     }
/*     */ 
/*  93 */     String orderSql = "from WOrderEntity where issuerId='" + uid + "' and buyStatus ='PAY_001' and orderStatus='ORDERSTATU_000' ";
/*  94 */     List order = this.wOrderService.findByQueryString(orderSql);
/*  95 */     if ((order != null) && (order.size() > 0)) {
/*  96 */       for (int i = 0; i < order.size(); i++) {
/*  97 */         WOrderEntity wOrder1 = (WOrderEntity)order.get(i);
/*  98 */         this.wOrderService.delete(wOrder1);
/*     */       }
/*     */     }
/*     */ 
/* 102 */     TSBaseUser tsBaseUser = (TSBaseUser)this.tsBaseUserServiceI.get(TSBaseUser.class, uid);
/* 103 */     if (tsBaseUser == null) {
/* 104 */       return CommonUtils.repsonseOjbectToClientWithBody("20012", null, new String[0]);
/*     */     }
/* 106 */     if ("0".equals(tsBaseUser.getCertification())) {
/* 107 */       return CommonUtils.repsonseOjbectToClientWithBody("20006", null, new String[0]);
/*     */     }
/*     */ 
/* 110 */     jsonMap.put("uid", uid);
/* 111 */     jsonMap.put("token", token);
/* 112 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */   }
/*     */ 
/*     */   public ResponseObject urgentOrder(String uid, String token, String photoId, String title, String latitude, String longitude, String category, String price, String territoryId, String orderId, String stateTag, String hotCityName, String allopatryTag, String location)
/*     */   {
/* 122 */     Map jsonMap = new HashMap();
/* 123 */     TSBaseUser tsBaseUser = (TSBaseUser)this.tsBaseUserServiceI.get(TSBaseUser.class, uid);
/* 124 */     if (tsBaseUser != null) {
/* 125 */       if ("0".equals(tsBaseUser.getCertification())) {
/* 126 */         return CommonUtils.repsonseOjbectToClientWithBody("20006", null, new String[0]);
/*     */       }
/* 128 */       WOrderEntity wOrderTemp = (WOrderEntity)this.wOrderService.findUniqueByProperty(WOrderEntity.class, "orderNumber", orderId);
/*     */ 
/* 131 */       if ((wOrderTemp != null) && (!"PAY_001".equals(wOrderTemp.getBuyStatus())))
/* 132 */         return CommonUtils.repsonseOjbectToClientWithBody("20045", null, new String[0]);
/* 133 */       if ((wOrderTemp != null) && ("PAY_001".equals(wOrderTemp.getBuyStatus())))
/*     */       {
/* 135 */         if (!price.equals(wOrderTemp.getPrice())) {
/* 136 */           wOrderTemp.setPrice(price);
/* 137 */           this.wOrderService.saveOrUpdate(wOrderTemp);
/*     */         }
/*     */ 
/* 140 */         jsonMap.put("orderId", wOrderTemp.getOrderNumber());
/* 141 */         jsonMap.put("uid", uid);
/* 142 */         jsonMap.put("token", token);
/* 143 */         return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */       }
/*     */ 
/* 146 */       WOrderEntity wOrder = new WOrderEntity();
/*     */ 
/* 149 */       if ("Thailand".equals(stateTag))
/*     */       {
/* 151 */         if ((hotCityName != null) && (!"".equals(hotCityName))) {
/* 152 */           WTerritoryBusiness wTerritory = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", territoryId);
/* 153 */           WTerritoryBusiness territoryparent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", wTerritory.getTerritoryparentid());
/*     */ 
/* 155 */           wOrder.setCity(wTerritory.getTerritoryname());
/* 156 */           wOrder.setState(territoryparent.getTerritoryname());
/* 157 */           wOrder.setTerritoryId(wTerritory.getId());
/* 158 */           wOrder.setLatitude(wTerritory.getXwgs84().toString());
/* 159 */           wOrder.setLongitude(wTerritory.getYwgs84().toString());
/* 160 */           wOrder.setLocation(territoryparent.getTerritoryname() + hotCityName);
/* 161 */           wOrder.setTerritoryId(wTerritory.getTerritoryparentid());
/*     */         }
/* 163 */         else if ("yes".equals(allopatryTag)) {
/* 164 */           WTerritoryBusiness wTerritory = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", territoryId);
/* 165 */           WTerritoryBusiness territoryparent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", wTerritory.getTerritoryparentid());
/*     */ 
/* 167 */           wOrder.setCity(wTerritory.getTerritoryname());
/* 168 */           wOrder.setState(territoryparent.getTerritoryname());
/* 169 */           wOrder.setTerritoryId(wTerritory.getId());
/* 170 */           wOrder.setLatitude(latitude);
/* 171 */           wOrder.setLongitude(longitude);
/* 172 */           wOrder.setLocation(location);
/* 173 */           wOrder.setTerritoryId(territoryId);
/*     */         }
/*     */         else {
/* 176 */           GoogleMap map = GoogleUtil.getAdreess(latitude, longitude);
/* 177 */           if ("OK".equals(map.getStatus())) {
/* 178 */             WTerritoryBusiness wTerritory = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", territoryId);
/* 179 */             WTerritoryBusiness territoryparent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", wTerritory.getTerritoryparentid());
/*     */ 
/* 181 */             wOrder.setCity(wTerritory.getTerritoryname());
/* 182 */             wOrder.setState(territoryparent.getTerritoryname());
/* 183 */             wOrder.setTerritoryId(wTerritory.getId());
/* 184 */             wOrder.setLatitude(latitude);
/* 185 */             wOrder.setLongitude(longitude);
/* 186 */             String address = ((Item)map.getResults().get(0)).getFormatted_address();
/* 187 */             wOrder.setLocation(address);
/* 188 */             wOrder.setTerritoryId(territoryId);
/*     */           } else {
/* 190 */             return CommonUtils.repsonseOjbectToClientWithBody("20055", null, new String[0]);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/* 195 */       else if ("China".equals(stateTag))
/*     */       {
/* 198 */         JSONObject result = BaiDuUtil.getLocationInfo(latitude, longitude);
/* 199 */         JSONObject addressComponent = (JSONObject)result.get("result");
/* 200 */         JSONObject district = (JSONObject)addressComponent.get("addressComponent");
/* 201 */         Object object = district.get("district");
/* 202 */         if ((object == null) || ("".equals(object))) {
/* 203 */           wOrder.setCity(district.get("city").toString());
/* 204 */           wOrder.setState(district.get("country").toString());
/* 205 */           String provinceName = district.get("province").toString();
/*     */ 
/* 209 */           WTerritoryBusiness territoryParent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "territoryname", provinceName);
/*     */ 
/* 215 */           wOrder.setTerritoryId(territoryParent.getId());
/*     */         }
/*     */         else {
/* 218 */           wOrder.setCity(district.getString("city").toString() + " " + object.toString());
/* 219 */           wOrder.setState(district.get("country").toString());
/* 220 */           String provinceName = district.get("province").toString();
/*     */ 
/* 224 */           WTerritoryBusiness territoryParent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "territoryname", provinceName);
/*     */ 
/* 230 */           wOrder.setTerritoryId(territoryParent.getId());
/*     */         }
/*     */ 
/* 234 */         wOrder.setLatitude(latitude);
/* 235 */         wOrder.setLongitude(longitude);
/* 236 */         wOrder.setLocation(district.get("country").toString() + addressComponent.get("formatted_address").toString() + addressComponent.get("sematic_description").toString());
/*     */       }
/*     */ 
/* 241 */       String id = UUID.randomUUID().toString();
/* 242 */       wOrder.setId(id);
/* 243 */       wOrder.setOrderNumber(orderId);
/* 244 */       wOrder.setTitle(title);
/* 245 */       wOrder.setCategory(category);
/* 246 */       wOrder.setPrice(price);
/* 247 */       wOrder.setOrderStatus(Orderstatu.ORDERSTATU_000.toString());
/* 248 */       wOrder.setSeekStatus(Integer.toString(0));
/* 249 */       wOrder.setIssuerId(uid);
/* 250 */       wOrder.setPhotos(photoId);
/* 251 */       wOrder.setIssuer(tsBaseUser.getUserName());
/* 252 */       wOrder.setPhone(tsBaseUser.getPhone());
/* 253 */       wOrder.setOrderTime(new Date());
/* 254 */       wOrder.setBuyStatus(PayStatus.PAY_001.toString());
/*     */ 
/* 257 */       String path_temp = this.request.getSession().getServletContext().getRealPath("");
/* 258 */       String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
/* 259 */       Set set = SensitivewordFilter.verifyWord(path, title);
/*     */ 
/* 261 */       if (set.size() > 0) {
/* 262 */         return CommonUtils.repsonseOjbectToClientWithBody("20054", null, new String[] { set.toString() });
/*     */       }
/* 264 */       this.wOrderService.save(wOrder);
/*     */ 
/* 267 */       List wOrderPhoto = this.wOrderPhotoService.findByProperty(WOrderPhotoEntity.class, "id", photoId);
/*     */ 
/* 269 */       if ((wOrderPhoto != null) && (wOrderPhoto.size() > 0))
/*     */       {
/* 271 */         for (int i = 0; i < wOrderPhoto.size(); i++) {
/* 272 */           WOrderPhotoEntity wOrderPhotoEntity = (WOrderPhotoEntity)wOrderPhoto.get(i);
/* 273 */           WOrderPhotoMainEntity wOrderPhotoMain = new WOrderPhotoMainEntity();
/* 274 */           wOrderPhotoMain.setId(wOrderPhotoEntity.getId());
/* 275 */           wOrderPhotoMain.setOrderId(wOrder.getId());
/* 276 */           wOrderPhotoMain.setUid(uid);
/* 277 */           wOrderPhotoMain.setCreateTime(new Date());
/* 278 */           wOrderPhotoMain.setPhotoType(wOrderPhotoEntity.getPhotoType());
/* 279 */           wOrderPhotoMain.setPhotoUrl(wOrderPhotoEntity.getPhotoUrl());
/* 280 */           wOrderPhotoMain.setPhotoName(wOrderPhotoEntity.getPhotoName());
/* 281 */           this.wOrderPhotoMainService.save(wOrderPhotoMain);
/*     */         }
/*     */       }
/* 284 */       else return CommonUtils.repsonseOjbectToClientWithBody("20022", null, new String[0]);
/*     */ 
/* 287 */       WOrderRecordEntity wJournal = new WOrderRecordEntity();
/* 288 */       wJournal.setId(UUID.randomUUID().toString());
/* 289 */       wJournal.setOrderId(wOrder.getId());
/* 290 */       wJournal.setOrderNumber(wOrder.getOrderNumber());
/* 291 */       wJournal.setAmount(price);
/* 292 */       wJournal.setOrderType(OrderType.ORDERTYPE_000.toString());
/* 293 */       wJournal.setCreateTime(new Date());
/* 294 */       wJournal.setDescription("紧急订单购买");
/* 295 */       wJournal.setPhone(tsBaseUser.getPhone());
/* 296 */       wJournal.setIssuerId(uid);
/* 297 */       wJournal.setIssuer(tsBaseUser.getUserName());
/* 298 */       wJournal.setBuyStatus(PayStatus.PAY_001.toString());
/* 299 */       wJournal.setOrderStatus(Orderstatu.ORDERSTATU_000.toString());
/* 300 */       this.orderRecordService.save(wJournal);
/* 301 */       jsonMap.put("orderId", wOrder.getOrderNumber());
/* 302 */       jsonMap.put("uid", uid);
/* 303 */       jsonMap.put("token", token);
/* 304 */       return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */     }
/*     */ 
/* 308 */     jsonMap.put("orderId", null);
/* 309 */     return CommonUtils.repsonseOjbectToClientWithBody("20012", null, new String[0]);
/*     */   }
/*     */ 
/*     */   public ResponseObject releaseOrdinaryOrder(String uid, String token)
/*     */   {
/* 317 */     Map jsonMap = new HashMap();
/*     */ 
/* 319 */     String sql = "from WOrderPhotoEntity where uid='" + uid + "' and photoType ='PHOTOTYPE_001' ";
/* 320 */     List orderPhoto = this.wOrderPhotoService.findByQueryString(sql);
/* 321 */     if ((orderPhoto != null) && (orderPhoto.size() > 0)) {
/* 322 */       for (int i = 0; i < orderPhoto.size(); i++) {
/* 323 */         WOrderPhotoEntity wOrderPhoto = (WOrderPhotoEntity)orderPhoto.get(i);
/* 324 */         String path_temp = this.request.getSession().getServletContext().getRealPath("");
/* 325 */         String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
/* 326 */         String imgPath = separator + "upload" + separator + "orderImage";
/*     */ 
/* 328 */         String filePath = path + imgPath;
/* 329 */         File fileDir = new File(filePath + separator + wOrderPhoto.getPhotoName());
/* 330 */         fileDir.delete();
/* 331 */         this.tsBaseUserServiceI.delete(wOrderPhoto);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 336 */     String orderSql = "from WOrderEntity where issuerId='" + uid + "' and buyStatus ='PAY_001' and orderStatus='ORDERSTATU_000' ";
/* 337 */     List order = this.wOrderService.findByQueryString(orderSql);
/* 338 */     if ((order != null) && (order.size() > 0)) {
/* 339 */       for (int i = 0; i < order.size(); i++) {
/* 340 */         WOrderEntity wOrder1 = (WOrderEntity)order.get(i);
/* 341 */         this.wOrderService.delete(wOrder1);
/*     */       }
/*     */     }
/* 344 */     TSBaseUser tsBaseUser = (TSBaseUser)this.tsBaseUserServiceI.get(TSBaseUser.class, uid);
/* 345 */     if (tsBaseUser == null) {
/* 346 */       return CommonUtils.repsonseOjbectToClientWithBody("20012", null, new String[0]);
/*     */     }
/* 348 */     if ("0".equals(tsBaseUser.getCertification())) {
/* 349 */       return CommonUtils.repsonseOjbectToClientWithBody("20007", null, new String[0]);
/*     */     }
/* 351 */     jsonMap.put("uid", uid);
/* 352 */     jsonMap.put("token", token);
/* 353 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */   }
/*     */ 
/*     */   public ResponseObject ordinaryOrder(String uid, String token, String title, String latitude, String longitude, String subtitle, String description, String photosId, String category, String price, String startTime, String endTime, String territoryId, String orderId, String stateTag, String hotCityName, String allopatryTag, String location, String orderPhone)
/*     */     throws ParseException
/*     */   {
/* 364 */     Map jsonMap = new HashMap();
/* 365 */     TSBaseUser tsBaseUser = (TSBaseUser)this.tsBaseUserServiceI.get(TSBaseUser.class, uid);
/* 366 */     if (tsBaseUser != null) {
/* 367 */       if ("0".equals(tsBaseUser.getCertification())) {
/* 368 */         return CommonUtils.repsonseOjbectToClientWithBody("20007", null, new String[0]);
/*     */       }
/* 370 */       WOrderEntity wOrderTemp = (WOrderEntity)this.wOrderService.findUniqueByProperty(WOrderEntity.class, "orderNumber", orderId);
/*     */ 
/* 372 */       if ((wOrderTemp != null) && (!"PAY_001".equals(wOrderTemp.getBuyStatus())))
/* 373 */         return CommonUtils.repsonseOjbectToClientWithBody("20045", null, new String[0]);
/* 374 */       if ((wOrderTemp != null) && ("PAY_001".equals(wOrderTemp.getBuyStatus())))
/*     */       {
/* 376 */         if (!price.equals(wOrderTemp.getPrice())) {
/* 377 */           wOrderTemp.setPrice(price);
/* 378 */           this.wOrderService.saveOrUpdate(wOrderTemp);
/*     */         }
/*     */ 
/* 381 */         jsonMap.put("orderId", wOrderTemp.getOrderNumber());
/* 382 */         jsonMap.put("uid", uid);
/* 383 */         jsonMap.put("token", token);
/* 384 */         return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */       }
/*     */ 
/* 387 */       WOrderEntity wOrder = new WOrderEntity();
/*     */ 
/* 390 */       if ("Thailand".equals(stateTag))
/*     */       {
/* 393 */         if ((hotCityName != null) && (!"".equals(hotCityName))) {
/* 394 */           WTerritoryBusiness wTerritory = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", territoryId);
/* 395 */           WTerritoryBusiness territoryparent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", wTerritory.getTerritoryparentid());
/*     */ 
/* 397 */           wOrder.setCity(wTerritory.getTerritoryname());
/* 398 */           wOrder.setState(territoryparent.getTerritoryname());
/* 399 */           wOrder.setTerritoryId(wTerritory.getId());
/* 400 */           wOrder.setLatitude(wTerritory.getXwgs84().toString());
/* 401 */           wOrder.setLongitude(wTerritory.getYwgs84().toString());
/* 402 */           wOrder.setLocation(territoryparent.getTerritoryname() + hotCityName);
/* 403 */           wOrder.setTerritoryId(wTerritory.getTerritoryparentid());
/*     */         }
/* 405 */         else if ("yes".equals(allopatryTag)) {
/* 406 */           WTerritoryBusiness wTerritory = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", territoryId);
/* 407 */           WTerritoryBusiness territoryparent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", wTerritory.getTerritoryparentid());
/*     */ 
/* 409 */           wOrder.setCity(wTerritory.getTerritoryname());
/* 410 */           wOrder.setState(territoryparent.getTerritoryname());
/* 411 */           wOrder.setTerritoryId(wTerritory.getId());
/* 412 */           wOrder.setLatitude(latitude);
/* 413 */           wOrder.setLongitude(longitude);
/* 414 */           wOrder.setLocation(location);
/* 415 */           wOrder.setTerritoryId(territoryId);
/*     */         }
/*     */         else {
/* 418 */           GoogleMap map = GoogleUtil.getAdreess(latitude, longitude);
/* 419 */           if ("OK".equals(map.getStatus())) {
/* 420 */             WTerritoryBusiness wTerritory = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", territoryId);
/* 421 */             WTerritoryBusiness territoryparent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", wTerritory.getTerritoryparentid());
/*     */ 
/* 423 */             wOrder.setCity(wTerritory.getTerritoryname());
/* 424 */             wOrder.setState(territoryparent.getTerritoryname());
/* 425 */             wOrder.setTerritoryId(wTerritory.getId());
/* 426 */             wOrder.setLatitude(latitude);
/* 427 */             wOrder.setLongitude(longitude);
/* 428 */             String address = ((Item)map.getResults().get(0)).getFormatted_address();
/* 429 */             wOrder.setLocation(address);
/* 430 */             wOrder.setTerritoryId(territoryId);
/*     */           } else {
/* 432 */             return CommonUtils.repsonseOjbectToClientWithBody("20055", null, new String[0]);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/* 437 */       else if ("China".equals(stateTag))
/*     */       {
/* 440 */         JSONObject result = BaiDuUtil.getLocationInfo(latitude, longitude);
/* 441 */         JSONObject addressComponent = (JSONObject)result.get("result");
/* 442 */         JSONObject district = (JSONObject)addressComponent.get("addressComponent");
/* 443 */         Object object = district.get("district");
/* 444 */         if ((object == null) || ("".equals(object))) {
/* 445 */           wOrder.setCity(district.get("city").toString());
/* 446 */           wOrder.setState(district.get("country").toString());
/* 447 */           String provinceName = district.get("province").toString();
/*     */ 
/* 451 */           WTerritoryBusiness territoryParent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "territoryname", provinceName);
/*     */ 
/* 457 */           wOrder.setTerritoryId(territoryParent.getId());
/*     */         }
/*     */         else {
/* 460 */           wOrder.setCity(district.getString("city").toString() + " " + object.toString());
/* 461 */           wOrder.setState(district.get("country").toString());
/* 462 */           String provinceName = district.get("province").toString();
/*     */ 
/* 466 */           WTerritoryBusiness territoryParent = (WTerritoryBusiness)this.wTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "territoryname", provinceName);
/*     */ 
/* 472 */           wOrder.setTerritoryId(territoryParent.getId());
/*     */         }
/*     */ 
/* 476 */         wOrder.setLatitude(latitude);
/* 477 */         wOrder.setLongitude(longitude);
/* 478 */         wOrder.setLocation(district.get("country").toString() + addressComponent.get("formatted_address").toString() + addressComponent.get("sematic_description").toString());
/*     */       }
/*     */ 
/* 481 */       String id = UUID.randomUUID().toString();
/* 482 */       wOrder.setId(id);
/* 483 */       wOrder.setOrderNumber(orderId);
/* 484 */       wOrder.setTitle(title);
/* 485 */       wOrder.setSubtitle(subtitle);
/* 486 */       wOrder.setDescription(description);
/* 487 */       wOrder.setCategory(category);
/* 488 */       wOrder.setPrice(price);
/*     */ 
/* 491 */       int startTimeLength = startTime.length();
/* 492 */       if (startTimeLength == 13) {
/* 493 */         wOrder.setStartTime(NewDate.timeStamp2Date13(startTime));
/* 494 */         wOrder.setEndTime(NewDate.timeStamp2Date13(endTime));
/* 495 */       } else if (startTimeLength == 10) {
/* 496 */         wOrder.setStartTime(NewDate.timeStamp2Date10(startTime));
/* 497 */         wOrder.setEndTime(NewDate.timeStamp2Date10(endTime));
/*     */       }
/* 499 */       wOrder.setIssuerId(uid);
/* 500 */       wOrder.setIssuer(tsBaseUser.getUserName());
/* 501 */       wOrder.setPhone(tsBaseUser.getPhone());
/* 502 */       wOrder.setOrderPhone(orderPhone);
/* 503 */       wOrder.setSeekStatus(Integer.toString(1));
/* 504 */       wOrder.setOrderTime(new Date());
/* 505 */       wOrder.setOrderStatus(Orderstatu.ORDERSTATU_000.toString());
/* 506 */       wOrder.setBuyStatus(PayStatus.PAY_001.toString());
/*     */ 
/* 509 */       String path_temp = this.request.getSession().getServletContext().getRealPath("");
/* 510 */       String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
/*     */ 
/* 512 */       Set set = SensitivewordFilter.verifyWord(path, title + subtitle + description);
/* 513 */       if (set.size() > 0) {
/* 514 */         String str = StringUtils.join(set.toArray(), ",");
/* 515 */         return CommonUtils.repsonseOjbectToClientWithBodyVerifyWord("20054", null, new String[] { str });
/*     */       }
/* 517 */       this.wOrderService.save(wOrder);
/*     */ 
/* 520 */       if ((photosId == null) || ("".equals(photosId))) {
/* 521 */         return CommonUtils.repsonseOjbectToClientWithBody("20022", null, new String[0]);
/*     */       }
/* 523 */       String[] sourceStrArray = photosId.split(",");
/* 524 */       for (int i = 0; i < sourceStrArray.length; i++) {
/* 525 */         String photosid = sourceStrArray[i];
/*     */ 
/* 527 */         List wOrderPhoto = this.wOrderPhotoService.findByProperty(WOrderPhotoEntity.class, "id", photosid);
/*     */ 
/* 529 */         if (wOrderPhoto.size() > 0)
/*     */         {
/* 531 */           for (int j = 0; j < wOrderPhoto.size(); j++) {
/* 532 */             WOrderPhotoEntity wOrderPhotoEntity = (WOrderPhotoEntity)wOrderPhoto.get(j);
/* 533 */             WOrderPhotoMainEntity wOrderPhotoMain = new WOrderPhotoMainEntity();
/* 534 */             wOrderPhotoMain.setId(UUID.randomUUID().toString());
/* 535 */             wOrderPhotoMain.setOrderId(wOrder.getId());
/* 536 */             wOrderPhotoMain.setUid(uid);
/* 537 */             wOrderPhotoMain.setCreateTime(new Date());
/* 538 */             wOrderPhotoMain.setPhotoType(wOrderPhotoEntity.getPhotoType());
/* 539 */             wOrderPhotoMain.setPhotoUrl(wOrderPhotoEntity.getPhotoUrl());
/* 540 */             wOrderPhotoMain.setPhotoName(wOrderPhotoEntity.getPhotoName());
/* 541 */             this.wOrderPhotoMainService.save(wOrderPhotoMain);
/* 542 */             wOrder.setPhotos(wOrderPhotoMain.getId());
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 547 */       this.wOrderService.saveOrUpdate(wOrder);
/*     */ 
/* 550 */       WOrderRecordEntity wJournal = new WOrderRecordEntity();
/* 551 */       wJournal.setId(UUID.randomUUID().toString());
/* 552 */       wJournal.setOrderId(wOrder.getId());
/* 553 */       wJournal.setOrderNumber(wOrder.getOrderNumber());
/* 554 */       wJournal.setAmount(price);
/* 555 */       wJournal.setOrderType(OrderType.ORDERTYPE_001.toString());
/* 556 */       wJournal.setCreateTime(new Date());
/* 557 */       wJournal.setDescription("普通订单购买");
/* 558 */       wJournal.setPhone(tsBaseUser.getPhone());
/* 559 */       wJournal.setIssuerId(uid);
/* 560 */       wJournal.setIssuer(tsBaseUser.getUserName());
/* 561 */       wJournal.setBuyStatus(PayStatus.PAY_001.toString());
/* 562 */       wJournal.setOrderStatus(Orderstatu.ORDERSTATU_000.toString());
/* 563 */       this.orderRecordService.save(wJournal);
/* 564 */       jsonMap.put("orderId", wOrder.getOrderNumber());
/* 565 */       jsonMap.put("uid", uid);
/* 566 */       jsonMap.put("token", token);
/* 567 */       return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */     }
/*     */ 
/* 571 */     jsonMap.put("orderId", null);
/* 572 */     return CommonUtils.repsonseOjbectToClientWithBody("20012", null, new String[0]);
/*     */   }
/*     */ 
/*     */   public ResponseObject TGRegion(String uid, String token)
/*     */   {
/* 578 */     Map jsonMap = new HashMap();
/* 579 */     List findByProperty = this.wTerritoryBusinessService.findByProperty(WTerritoryBusiness.class, "out_in", "Thailand");
/* 580 */     JSONArray JsonListCategory = JSONArray.fromObject(findByProperty);
/* 581 */     JSONArray childMenu = treeList(JsonListCategory, "10000");
/* 582 */     jsonMap.put("childMenu", childMenu);
/* 583 */     return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
/*     */   }
/*     */ 
/*     */   public JSONArray treeList(JSONArray menuList, String parentId) {
/* 587 */     JSONArray childMenu = new JSONArray();
/* 588 */     for (Iterator localIterator = menuList.iterator(); localIterator.hasNext(); ) { Object object = localIterator.next();
/* 589 */       JSONObject jsonMenu = JSONObject.fromObject(object);
/* 590 */       String menuId = (String)jsonMenu.get("id");
/* 591 */       String pid = (String)jsonMenu.get("territoryparentid");
/* 592 */       if (parentId.equals(pid)) {
/* 593 */         JSONArray c_node = treeList(menuList, menuId);
/* 594 */         jsonMenu.put("childNode", c_node);
/* 595 */         childMenu.add(jsonMenu);
/*     */       }
/*     */     }
/* 598 */     return childMenu;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.app.UrgentOrderServiceProcessor
 * JD-Core Version:    0.6.2
 */