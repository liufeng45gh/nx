/*     */ package ssb.warmline.business.service.impl.worder;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.jeecgframework.core.common.dao.ICommonDao;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSRole;
/*     */ import org.jeecgframework.web.system.pojo.base.TSRoleUser;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*     */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*     */ import ssb.warmline.business.utils.PropertiesUtil;
/*     */ 
/*     */ @Service("wOrderService")
/*     */ @Transactional
/*     */ public class WOrderServiceImpl extends CommonServiceImpl
/*     */   implements WOrderServiceI
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   public <T> void delete(T entity)
/*     */   {
/*  36 */     super.delete(entity);
/*     */ 
/*  38 */     doDelSql((WOrderEntity)entity);
/*     */   }
/*     */ 
/*     */   public <T> Serializable save(T entity) {
/*  42 */     Serializable t = super.save(entity);
/*     */ 
/*  44 */     doAddSql((WOrderEntity)entity);
/*  45 */     return t;
/*     */   }
/*     */ 
/*     */   public <T> void saveOrUpdate(T entity) {
/*  49 */     super.saveOrUpdate(entity);
/*     */ 
/*  51 */     doUpdateSql((WOrderEntity)entity);
/*     */   }
/*     */ 
/*     */   public boolean doAddSql(WOrderEntity t)
/*     */   {
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doUpdateSql(WOrderEntity t)
/*     */   {
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doDelSql(WOrderEntity t)
/*     */   {
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */   public String replaceVal(String sql, WOrderEntity t)
/*     */   {
/*  85 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/*  86 */     sql = sql.replace("#{title}", String.valueOf(t.getTitle()));
/*  87 */     sql = sql.replace("#{subtitle}", String.valueOf(t.getSubtitle()));
/*  88 */     sql = sql.replace("#{description}", String.valueOf(t.getDescription()));
/*  89 */     sql = sql.replace("#{category}", String.valueOf(t.getCategory()));
/*  90 */     sql = sql.replace("#{photos}", String.valueOf(t.getPhotos()));
/*  91 */     sql = sql.replace("#{start_time}", String.valueOf(t.getStartTime()));
/*  92 */     sql = sql.replace("#{price}", String.valueOf(t.getPrice()));
/*  93 */     sql = sql.replace("#{issuer}", String.valueOf(t.getIssuer()));
/*  94 */     sql = sql.replace("#{phone}", String.valueOf(t.getPhone()));
/*  95 */     sql = sql.replace("#{location}", String.valueOf(t.getLocation()));
/*  96 */     sql = sql.replace("#{state}", String.valueOf(t.getState()));
/*  97 */     sql = sql.replace("#{city}", String.valueOf(t.getCity()));
/*  98 */     sql = sql.replace("#{order_status}", String.valueOf(t.getOrderStatus()));
/*  99 */     sql = sql.replace("#{order_person_name}", String.valueOf(t.getOrderPersonName()));
/* 100 */     sql = sql.replace("#{order_person_phone}", String.valueOf(t.getOrderPersonPhone()));
/* 101 */     sql = sql.replace("#{seek_status}", String.valueOf(t.getSeekStatus()));
/* 102 */     sql = sql.replace("#{issuer_id}", String.valueOf(t.getIssuerId()));
/* 103 */     sql = sql.replace("#{order_person_id}", String.valueOf(t.getOrderPersonId()));
/* 104 */     sql = sql.replace("#{order_time}", String.valueOf(t.getOrderTime()));
/* 105 */     sql = sql.replace("#{end_time}", String.valueOf(t.getEndTime()));
/* 106 */     sql = sql.replace("#{order_type}", String.valueOf(t.getEndTime()));
/* 107 */     sql = sql.replace("#{latitude}", String.valueOf(t.getLatitude()));
/* 108 */     sql = sql.replace("#{longitude}", String.valueOf(t.getLongitude()));
/* 109 */     sql = sql.replace("#{distance}", String.valueOf(t.getDistance()));
/* 110 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 111 */     return sql;
/*     */   }
/*     */ 
/*     */   public JSONObject findAllOrderAndBrokerage(WOrderEntity wOrder, DataGrid dataGrid, String territoryId, String orderNumber, String issuer, String seekStatus)
/*     */   {
/* 117 */     TSUser currentUser = ResourceUtil.getSessionUserName();
/* 118 */     List roleUsers = new ArrayList();
/* 119 */     String id = PropertiesUtil.getProperties();
/* 120 */     roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
/* 121 */     TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
/* 122 */     TSRole role = tsRU.getTSRole();
/* 123 */     String sql = null;
/* 124 */     if ("297e47fc5b8ea693015b8ea8c1690001".equals(role.getId())) {
/* 125 */       sql = "SELECT wo.id, wo.order_Number AS orderNumber, wo.title AS title, wo.subtitle AS subtitle, wo.description AS description, \two.category AS category,wo.photos AS photos,wo.price AS price,wo.`issuer` AS issuer,wo.phone AS phone,wo.location AS location, wo.state AS state,wo.city AS city,wo.order_status AS orderStatus,wo.order_person_name AS orderPersonName,wo.order_person_phone AS orderPersonPhone,wo.seek_status AS seekStatus,wo.issuer_id AS issuerId,wo.order_person_id AS orderPersonId,wo.order_time AS orderTime,wo.start_time AS startTime,wt.territoryname AS territoryName , wo.end_time AS endTime,wo.buy_status AS buyStatus,wo.territory_id AS territoryId FROM w_commission wc left JOIN w_order wo ON wc.order_Number=wo.order_Number left JOIN w_territory wt ON wo.territory_id = wt.id  WHERE wo.is_virtual_user is null ";
/*     */ 
/* 132 */       if ((orderNumber != null) && (!"".equals(orderNumber))) {
/* 133 */         sql = sql + " AND wo.order_Number ='" + orderNumber + "' ";
/*     */       }
/* 135 */       if ((issuer != null) && (!"".equals(issuer))) {
/* 136 */         sql = sql + " AND wo.issuer ='" + issuer + "' ";
/*     */       }
/* 138 */       if ((seekStatus != null) && (!"".equals(seekStatus))) {
/* 139 */         sql = sql + " AND wo.seek_status ='" + seekStatus + "' ";
/*     */       }
/*     */ 
/* 144 */       sql = sql + " order by wc.create_time asc";
/*     */     } else {
/* 146 */       sql = "SELECT wo.id, wo.order_Number AS orderNumber, wo.title AS title, wo.subtitle AS subtitle, wo.description AS description, wo.category AS category, \two.photos AS photos, wo.price AS price, wo.`issuer` AS ISSUER, wo.phone AS phone, wo.location AS location, wo.state AS state, wo.city AS city,\two.order_status AS orderStatus, wo.order_person_name AS orderPersonName, wo.order_person_phone AS orderPersonPhone, wo.seek_status AS seekStatus,\two.issuer_id AS issuerId, wo.order_person_id AS orderPersonId, wo.order_time AS orderTime, wo.start_time AS startTime, wt.territoryname AS territoryName,  wo.end_time AS endTime, wo.buy_status AS buyStatus, wo.territory_id AS territoryId FROM w_commission wc LEFT JOIN w_order wo ON wc.order_Number = wo.order_Number  LEFT JOIN w_territory wt ON wo.territory_id = wt.id WHERE wo.territory_id = '" + 
/* 151 */         territoryId + "' and  wo.is_virtual_user is null ";
/*     */ 
/* 153 */       if ((orderNumber != null) && (!"".equals(orderNumber))) {
/* 154 */         sql = sql + " AND wo.order_Number ='" + orderNumber + "' ";
/*     */       }
/* 156 */       if ((issuer != null) && (!"".equals(issuer))) {
/* 157 */         sql = sql + " AND wo.issuer ='" + issuer + "' ";
/*     */       }
/* 159 */       if ((seekStatus != null) && (!"".equals(seekStatus))) {
/* 160 */         sql = sql + " AND wo.seek_status ='" + seekStatus + "' ";
/*     */       }
/*     */ 
/* 165 */       sql = sql + " order by wc.create_time asc";
/*     */     }
/*     */ 
/* 168 */     String sqlCnt = "select count(*) from (" + sql + ") a";
/* 169 */     Long iCount = getCountForJdbcParam(sqlCnt, null);
/*     */ 
/* 172 */     List mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
/*     */ 
/* 174 */     Db2Page[] db2Pages = { 
/* 175 */       new Db2Page("id"), 
/* 176 */       new Db2Page("orderNumber", "orderNumber", null), 
/* 177 */       new Db2Page("title", "title", null), 
/* 178 */       new Db2Page("subtitle", "subtitle", null), 
/* 179 */       new Db2Page("description", "description", null), 
/* 180 */       new Db2Page("category", "category", null), 
/* 181 */       new Db2Page("city", "city", null), 
/* 182 */       new Db2Page("orderStatus", "orderStatus", null), 
/* 183 */       new Db2Page("orderPersonName", "orderPersonName", null), 
/* 184 */       new Db2Page("orderPersonPhone", "orderPersonPhone", null), 
/* 185 */       new Db2Page("seekStatus", "seekStatus", null), 
/* 186 */       new Db2Page("phone", "phone", null), 
/* 187 */       new Db2Page("issuer", "issuer", null), 
/* 188 */       new Db2Page("issuerId", "issuerId", null), 
/* 189 */       new Db2Page("location", "location", null), 
/* 190 */       new Db2Page("price", "price", null), 
/* 191 */       new Db2Page("orderPersonId", "orderPersonId", null), 
/* 192 */       new Db2Page("orderTime", "orderTime", null), 
/* 193 */       new Db2Page("startTime", "startTime", null), 
/* 194 */       new Db2Page("endTime", "endTime", null), 
/* 195 */       new Db2Page("buyStatus", "buyStatus", null), 
/* 196 */       new Db2Page("territoryId", "territoryId", null), 
/* 197 */       new Db2Page("territoryName", "territoryName", null) };
/*     */ 
/* 199 */     JSONObject jObject = getJsonDatagridEasyUI(mapList, iCount.intValue(), db2Pages);
/* 200 */     return jObject;
/*     */   }
/*     */ 
/*     */   public JSONObject getJsonDatagridEasyUI(List<Map<String, Object>> mapList, int iTotalCnt, Db2Page[] dataExchanger)
/*     */   {
/* 208 */     String jsonTemp = "{'total':" + iTotalCnt + ",'rows':[";
/* 209 */     for (int j = 0; j < mapList.size(); j++) {
/* 210 */       Map m = (Map)mapList.get(j);
/* 211 */       if (j > 0) {
/* 212 */         jsonTemp = jsonTemp + ",";
/*     */       }
/* 214 */       jsonTemp = jsonTemp + "{";
/* 215 */       for (int i = 0; i < dataExchanger.length; i++) {
/* 216 */         if (i > 0) {
/* 217 */           jsonTemp = jsonTemp + ",";
/*     */         }
/* 219 */         jsonTemp = jsonTemp + "'" + dataExchanger[i].getKey() + "'" + ":";
/* 220 */         Object objValue = dataExchanger[i].getData(m);
/* 221 */         if (objValue == null)
/* 222 */           jsonTemp = jsonTemp + "null";
/*     */         else {
/* 224 */           jsonTemp = jsonTemp + "'" + objValue + "'";
/*     */         }
/*     */       }
/* 227 */       jsonTemp = jsonTemp + "}";
/*     */     }
/* 229 */     jsonTemp = jsonTemp + "]}";
/* 230 */     JSONObject jObject = JSONObject.fromObject(jsonTemp);
/* 231 */     return jObject;
/*     */   }
/*     */ 
/*     */   public List<WOrderEntity> showTotalOrderNumber(DataGrid dataGrid, String territoryId)
/*     */   {
/* 237 */     List list = this.commonDao.findByQueryString(" FROM  WOrderEntity WHERE  territoryId = '" + territoryId + "' AND orderStatus IN ('ORDERSTATU_003','ORDERSTATU_004','ORDERSTATU_006') order by orderTime asc");
/* 238 */     return list;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.worder.WOrderServiceImpl
 * JD-Core Version:    0.6.2
 */