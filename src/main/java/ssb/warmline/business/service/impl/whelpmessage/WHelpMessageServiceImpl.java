/*     */ package ssb.warmline.business.service.impl.whelpmessage;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity;
/*     */ import ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI;
/*     */ 
/*     */ @Service("wHelpMessageService")
/*     */ @Transactional
/*     */ public class WHelpMessageServiceImpl extends CommonServiceImpl
/*     */   implements WHelpMessageServiceI
/*     */ {
/*     */   public <T> void delete(T entity)
/*     */   {
/*  22 */     super.delete(entity);
/*     */ 
/*  24 */     doDelSql((WHelpMessageEntity)entity);
/*     */   }
/*     */ 
/*     */   public <T> Serializable save(T entity) {
/*  28 */     Serializable t = super.save(entity);
/*     */ 
/*  30 */     doAddSql((WHelpMessageEntity)entity);
/*  31 */     return t;
/*     */   }
/*     */ 
/*     */   public <T> void saveOrUpdate(T entity) {
/*  35 */     super.saveOrUpdate(entity);
/*     */ 
/*  37 */     doUpdateSql((WHelpMessageEntity)entity);
/*     */   }
/*     */ 
/*     */   public boolean doAddSql(WHelpMessageEntity t)
/*     */   {
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doUpdateSql(WHelpMessageEntity t)
/*     */   {
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doDelSql(WHelpMessageEntity t)
/*     */   {
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   public String replaceVal(String sql, WHelpMessageEntity t)
/*     */   {
/*  71 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/*  72 */     sql = sql.replace("#{seek_help_people_id}", String.valueOf(t.getSeekHelpPeopleId()));
/*  73 */     sql = sql.replace("#{seek_help_people}", String.valueOf(t.getSeekHelpPeople()));
/*  74 */     sql = sql.replace("#{help_people_id}", String.valueOf(t.getHelpPeopleId()));
/*  75 */     sql = sql.replace("#{help_people}", String.valueOf(t.getHelpPeople()));
/*  76 */     sql = sql.replace("#{content}", String.valueOf(t.getContent()));
/*  77 */     sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
/*  78 */     sql = sql.replace("#{message_type}", String.valueOf(t.getMessageType()));
/*  79 */     sql = sql.replace("#{reading_state}", String.valueOf(t.getReadingState()));
/*  80 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/*  81 */     return sql;
/*     */   }
/*     */ 
/*     */   public JSONObject applicantNumberDatagrid(WHelpMessageEntity wHelpMessage, DataGrid dataGrid, String orderId)
/*     */   {
/*  87 */     String sql = "SELECT whm.id AS id, whm.help_people_id AS help_people_id, whm.help_people AS helpPeople, whm.create_date AS createDate,  tsbu.realname AS realName,tsbu.area_Code AS areaCode,tsbu.phone,tsbu.gender,tsbu.age,whm.message_type AS messageType  FROM w_help_message whm LEFT JOIN t_s_base_user tsbu ON whm.help_people_id = tsbu.id  WHERE whm.message_type IN ('APPLICATION','INVALID','COMPLETE') AND whm.order_id =  '" + 
/*  90 */       orderId + "' " + 
/*  91 */       "ORDER BY whm.message_type ASC";
/*     */ 
/*  93 */     String sqlCnt = "select count(*) from (" + sql + ") a";
/*  94 */     Long iCount = getCountForJdbcParam(sqlCnt, null);
/*     */ 
/*  97 */     List mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
/*     */ 
/*  99 */     Db2Page[] db2Pages = { new Db2Page("id"), 
/* 100 */       new Db2Page("helpPeopleId", "helpPeopleId", null), 
/* 101 */       new Db2Page("helpPeople", "helpPeople", null), 
/* 102 */       new Db2Page("createDate", "createDate", null), 
/* 103 */       new Db2Page("realName", "realName", null), 
/* 104 */       new Db2Page("areaCode", "areaCode", null), 
/* 105 */       new Db2Page("phone", "phone", null), 
/* 106 */       new Db2Page("gender", "gender", null), 
/* 107 */       new Db2Page("age", "age", null), 
/* 108 */       new Db2Page("messageType", "messageType", null) };
/*     */ 
/* 110 */     JSONObject jObject = getJsonDatagridEasyUI(mapList, iCount.intValue(), db2Pages);
/* 111 */     return jObject;
/*     */   }
/*     */ 
/*     */   public JSONObject getJsonDatagridEasyUI(List<Map<String, Object>> mapList, int iTotalCnt, Db2Page[] dataExchanger)
/*     */   {
/* 118 */     String jsonTemp = "{'total':" + iTotalCnt + ",'rows':[";
/* 119 */     for (int j = 0; j < mapList.size(); j++) {
/* 120 */       Map m = (Map)mapList.get(j);
/* 121 */       if (j > 0) {
/* 122 */         jsonTemp = jsonTemp + ",";
/*     */       }
/* 124 */       jsonTemp = jsonTemp + "{";
/* 125 */       for (int i = 0; i < dataExchanger.length; i++) {
/* 126 */         if (i > 0) {
/* 127 */           jsonTemp = jsonTemp + ",";
/*     */         }
/* 129 */         jsonTemp = jsonTemp + "'" + dataExchanger[i].getKey() + "'" + ":";
/* 130 */         Object objValue = dataExchanger[i].getData(m);
/* 131 */         if (objValue == null)
/* 132 */           jsonTemp = jsonTemp + "null";
/*     */         else {
/* 134 */           jsonTemp = jsonTemp + "'" + objValue + "'";
/*     */         }
/*     */       }
/* 137 */       jsonTemp = jsonTemp + "}";
/*     */     }
/* 139 */     jsonTemp = jsonTemp + "]}";
/* 140 */     JSONObject jObject = JSONObject.fromObject(jsonTemp);
/* 141 */     return jObject;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.whelpmessage.WHelpMessageServiceImpl
 * JD-Core Version:    0.6.2
 */