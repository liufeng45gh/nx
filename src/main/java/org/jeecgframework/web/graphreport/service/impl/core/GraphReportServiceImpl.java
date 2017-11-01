/*     */ package org.jeecgframework.web.graphreport.service.impl.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
/*     */ import org.jeecgframework.core.common.exception.BusinessException;
/*     */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.web.cgreport.dao.core.CgReportDao;
/*     */ import org.jeecgframework.web.graphreport.service.core.GraphReportServiceI;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("graphReportService")
/*     */ @Transactional
/*     */ public class GraphReportServiceImpl extends CommonServiceImpl
/*     */   implements GraphReportServiceI
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private JdbcDao jdbcDao;
/*     */ 
/*     */   @Autowired
/*     */   private CgReportDao cgReportDao;
/*     */ 
/*     */   public Map<String, Object> queryCgReportConfig(String reportId)
/*     */   {
/*  35 */     Map cgReportM = new HashMap(0);
/*  36 */     Map mainM = (Map)this.jdbcDao.findForJdbc("SELECT * from jform_graphreport_head where code=?", new Object[] { reportId }).get(0);
/*  37 */     List itemsM = this.jdbcDao.findForJdbc("SELECT * from jform_graphreport_item where cgreport_head_id=? order by order_num asc", new Object[] { mainM.get("id") });
/*  38 */     cgReportM.put("main", mainM);
/*  39 */     cgReportM.put("items", itemsM);
/*  40 */     return cgReportM;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> queryCgReportMainConfig(String reportId)
/*     */   {
/*  50 */     return this.cgReportDao.queryCgReportMainConfig(reportId);
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> queryCgReportItems(String reportId)
/*     */   {
/*  60 */     return this.cgReportDao.queryCgReportItems(reportId);
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> queryByCgReportSql(String sql, Map params, int page, int rows)
/*     */   {
/*  66 */     sql = handleElInSQL(sql, params);
/*  67 */     String querySql = getFullSql(sql, params);
/*  68 */     List result = null;
/*  69 */     if ((page == -1) && (rows == -1))
/*  70 */       result = this.jdbcDao.findForJdbc(querySql, new Object[0]);
/*     */     else {
/*  72 */       result = this.jdbcDao.findForJdbc(querySql, page, rows);
/*     */     }
/*  74 */     return result;
/*     */   }
/*     */ 
/*     */   public String handleElInSQL(String sql, Map params)
/*     */   {
/*  81 */     Pattern p = Pattern.compile("\\{[^}]+\\}");
/*  82 */     Matcher m = p.matcher(sql);
/*     */ 
/*  84 */     while (m.find()) {
/*  85 */       String oel = m.group();
/*  86 */       String el = oel.replace("{", "").replace("}", "").trim();
/*     */ 
/*  88 */       if (el.indexOf(":") != -1) {
/*  89 */         String[] elSplit = el.split(":");
/*  90 */         String elKey = elSplit[0].trim();
/*  91 */         String elValue = elSplit[1].trim();
/*     */ 
/*  93 */         Object condValue = params.get(elSplit[1].trim());
/*  94 */         if (condValue != null)
/*  95 */           sql = sql.replace(oel, elKey + condValue.toString().replace(new StringBuilder(" ").append(elValue).append(" ").toString(), new StringBuilder(" ").append(elKey).append(" ").toString()));
/*     */         else {
/*  97 */           sql = sql.replace(oel, "1=1");
/*     */         }
/*  99 */         params.remove(elValue);
/*     */       }
/*     */       else {
/* 102 */         Object condValue = params.get(el);
/* 103 */         if (condValue != null)
/* 104 */           sql = sql.replace(oel, el + condValue.toString());
/*     */         else {
/* 106 */           sql = sql.replace(oel, "1=1");
/*     */         }
/* 108 */         params.remove(el);
/*     */       }
/*     */     }
/* 111 */     return sql;
/*     */   }
/*     */ 
/*     */   private String getFullSql(String sql, Map params)
/*     */   {
/* 123 */     String orderBy = "";
/* 124 */     Pattern p = Pattern.compile("order +by.*", 2);
/* 125 */     Matcher m = p.matcher(sql);
/* 126 */     if (m.find()) {
/* 127 */       orderBy = " " + m.group();
/* 128 */       sql = sql.replace(orderBy, "");
/*     */     }
/*     */ 
/* 131 */     StringBuilder sqlB = new StringBuilder();
/* 132 */     sqlB.append("SELECT t.* FROM ( ");
/* 133 */     sqlB.append(sql + " ");
/* 134 */     sqlB.append(") t ");
/* 135 */     if (params.size() >= 1) {
/* 136 */       sqlB.append("WHERE 1=1  ");
/* 137 */       Iterator it = params.keySet().iterator();
/* 138 */       while (it.hasNext()) {
/* 139 */         String key = String.valueOf(it.next());
/* 140 */         String value = String.valueOf(params.get(key));
/* 141 */         if ((!StringUtil.isEmpty(value)) && (!"null".equals(value))) {
/* 142 */           sqlB.append(" AND ");
/* 143 */           sqlB.append(" " + key + value);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 148 */     sqlB.append(orderBy);
/* 149 */     return sqlB.toString();
/*     */   }
/*     */ 
/*     */   public long countQueryByCgReportSql(String sql, Map params)
/*     */   {
/* 154 */     String querySql = getFullSql(sql, params);
/* 155 */     querySql = "SELECT COUNT(*) FROM (" + querySql + ") t2";
/* 156 */     long result = this.jdbcDao.findForLong(querySql, new HashMap(0));
/* 157 */     return result;
/*     */   }
/*     */ 
/*     */   public List<String> getSqlFields(String sql)
/*     */   {
/* 162 */     if (oConvertUtils.isEmpty(sql)) {
/* 163 */       return null;
/*     */     }
/* 165 */     List result = this.jdbcDao.findForJdbc(sql, 1, 1);
/* 166 */     if (result.size() < 1) {
/* 167 */       throw new BusinessException("该报表sql没有数据");
/*     */     }
/* 169 */     Set fieldsSet = ((Map)result.get(0)).keySet();
/* 170 */     List fileds = new ArrayList(fieldsSet);
/* 171 */     return fileds;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.graphreport.service.impl.core.GraphReportServiceImpl
 * JD-Core Version:    0.6.2
 */