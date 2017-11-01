/*     */ package ssb.warmline.business.service.impl.tsbaseuser;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*     */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
/*     */ 
/*     */ @Service("tSBaseUserService")
/*     */ @Transactional
/*     */ public class TSBaseUserServiceImpl extends CommonServiceImpl
/*     */   implements TSBaseUserServiceI
/*     */ {
/*     */   public <T> void delete(T entity)
/*     */   {
/*  20 */     super.delete(entity);
/*     */ 
/*  22 */     doDelSql((TSBaseUser)entity);
/*     */   }
/*     */ 
/*     */   public <T> Serializable save(T entity) {
/*  26 */     Serializable t = super.save(entity);
/*     */ 
/*  28 */     doAddSql((TSBaseUser)entity);
/*  29 */     return t;
/*     */   }
/*     */ 
/*     */   public <T> void saveOrUpdate(T entity) {
/*  33 */     super.saveOrUpdate(entity);
/*     */ 
/*  35 */     doUpdateSql((TSBaseUser)entity);
/*     */   }
/*     */ 
/*     */   public boolean doAddSql(TSBaseUser t)
/*     */   {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doUpdateSql(TSBaseUser t)
/*     */   {
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doDelSql(TSBaseUser t)
/*     */   {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   public JSONObject tsBaseUserJupsh(TSBaseUser tsBaseUser, DataGrid dataGrid)
/*     */   {
/*  71 */     String sql = "SELECT tsbu.id AS id, tsbu.realname AS realName, tsbu.username AS userName, tsbu.phone AS phone,  tsbu.age AS age, tsbu.userkey AS userkey FROM t_s_base_user tsbu where tsbu.userkey in('普通用户','区域负责人') ";
/*     */ 
/*  74 */     String sqlCnt = "select count(*) from (" + sql + ") a";
/*  75 */     Long iCount = getCountForJdbcParam(sqlCnt, null);
/*     */ 
/*  77 */     List mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
/*     */ 
/*  79 */     Db2Page[] db2Pages = { new Db2Page("id"), new Db2Page("realname", "realname", null), 
/*  80 */       new Db2Page("username", "username", null), new Db2Page("phone", "phone", null), 
/*  81 */       new Db2Page("userkey", "userkey", null), new Db2Page("age", "age", null) };
/*  82 */     JSONObject jObject = getJsonDatagridEasyUI(mapList, iCount.intValue(), db2Pages);
/*  83 */     return jObject;
/*     */   }
/*     */ 
/*     */   public JSONObject tsBaseUserDatagrid(TSBaseUser tsBaseUser, DataGrid dataGrid)
/*     */   {
/*  89 */     String sql = "SELECT tsbu.id AS id,tsbu.realname AS realName,tsbu.username AS userName,tsbu.phone AS phone,tsbu.age AS age,tsbu.userkey AS userkey FROM t_s_base_user tsbu WHERE tsbu.id NOT IN ( SELECT whm.help_people_id AS helpPeopleId FROM w_help_message whm WHERE whm.message_type IN ('APPLICATION', 'AGREE'))  AND tsbu.certification = 1 AND tsbu.userkey = '区域负责人' AND tsbu.territoryid = '" + 
/*  92 */       tsBaseUser.getTerritoryId() + "'";
/*     */ 
/*  95 */     String sqlCnt = "select count(*) from (" + sql + ") a";
/*  96 */     Long iCount = getCountForJdbcParam(sqlCnt, null);
/*     */ 
/*  98 */     List mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
/*     */ 
/* 100 */     Db2Page[] db2Pages = { new Db2Page("id"), new Db2Page("realname", "realname", null), 
/* 101 */       new Db2Page("username", "username", null), 
/* 102 */       new Db2Page("phone", "phone", null), 
/* 103 */       new Db2Page("userkey", "userkey", null), 
/* 104 */       new Db2Page("age", "age", null) };
/* 105 */     JSONObject jObject = getJsonDatagridEasyUI(mapList, iCount.intValue(), db2Pages);
/* 106 */     return jObject;
/*     */   }
/*     */ 
/*     */   public JSONObject getJsonDatagridEasyUI(List<Map<String, Object>> mapList, int iTotalCnt, Db2Page[] dataExchanger)
/*     */   {
/* 114 */     String jsonTemp = "{'total':" + iTotalCnt + ",'rows':[";
/* 115 */     for (int j = 0; j < mapList.size(); j++) {
/* 116 */       Map m = (Map)mapList.get(j);
/* 117 */       if (j > 0) {
/* 118 */         jsonTemp = jsonTemp + ",";
/*     */       }
/* 120 */       jsonTemp = jsonTemp + "{";
/* 121 */       for (int i = 0; i < dataExchanger.length; i++) {
/* 122 */         if (i > 0) {
/* 123 */           jsonTemp = jsonTemp + ",";
/*     */         }
/* 125 */         jsonTemp = jsonTemp + "'" + dataExchanger[i].getKey() + "'" + ":";
/* 126 */         Object objValue = dataExchanger[i].getData(m);
/* 127 */         if (objValue == null)
/* 128 */           jsonTemp = jsonTemp + "null";
/*     */         else {
/* 130 */           jsonTemp = jsonTemp + "'" + objValue + "'";
/*     */         }
/*     */       }
/* 133 */       jsonTemp = jsonTemp + "}";
/*     */     }
/* 135 */     jsonTemp = jsonTemp + "]}";
/* 136 */     JSONObject jObject = JSONObject.fromObject(jsonTemp);
/* 137 */     return jObject;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.tsbaseuser.TSBaseUserServiceImpl
 * JD-Core Version:    0.6.2
 */