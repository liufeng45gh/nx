/*     */ package org.jeecgframework.web.cgform.util;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.jeecgframework.core.util.DBTypeUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
/*     */ 
/*     */ public class QueryParamUtil
/*     */ {
/*     */   public static void loadQueryParams(HttpServletRequest request, CgFormFieldEntity b, Map params)
/*     */   {
/*  38 */     if ("N".equalsIgnoreCase(b.getIsQuery())) {
/*  39 */       return;
/*     */     }
/*     */ 
/*  43 */     if ("single".equals(b.getQueryMode()))
/*     */     {
/*  45 */       String value = request.getParameter(b.getFieldName());
/*     */       try {
/*  47 */         if (StringUtil.isEmpty(value)) {
/*  48 */           return;
/*     */         }
/*  50 */         String uri = request.getQueryString();
/*  51 */         if (uri.contains(b.getFieldName() + "=")) {
/*  52 */           String contiansChinesevalue = new String(value.getBytes("ISO-8859-1"), "UTF-8");
/*  53 */           value = contiansChinesevalue;
/*     */         }
/*     */       } catch (UnsupportedEncodingException e) {
/*  56 */         e.printStackTrace();
/*  57 */         return;
/*     */       }
/*  59 */       sql_inj_throw(value);
/*  60 */       value = applyType(b.getType(), value);
/*  61 */       if (!StringUtil.isEmpty(value))
/*  62 */         if (value.contains("*"))
/*     */         {
/*  64 */           value = value.replaceAll("\\*", "%");
/*  65 */           params.put(b.getFieldName(), " LIKE " + value);
/*     */         } else {
/*  67 */           params.put(b.getFieldName(), " = " + value);
/*     */         }
/*     */     }
/*  70 */     else if ("group".equals(b.getQueryMode()))
/*     */     {
/*  72 */       String begin = request.getParameter(b.getFieldName() + "_begin");
/*  73 */       sql_inj_throw(begin);
/*  74 */       begin = applyType(b.getType(), begin);
/*  75 */       String end = request.getParameter(b.getFieldName() + "_end");
/*  76 */       sql_inj_throw(end);
/*  77 */       end = applyType(b.getType(), end);
/*  78 */       if (!StringUtil.isEmpty(begin)) {
/*  79 */         String re = " >= " + begin;
/*  80 */         if (!StringUtil.isEmpty(end)) {
/*  81 */           re = re + " AND " + b.getFieldName() + " <= " + end;
/*     */         }
/*  83 */         params.put(b.getFieldName(), re);
/*  84 */       } else if (!StringUtil.isEmpty(end)) {
/*  85 */         String re = " <= " + end;
/*  86 */         params.put(b.getFieldName(), re);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String applyType(String fieldType, String value)
/*     */   {
/*  97 */     if (!StringUtil.isEmpty(value)) {
/*  98 */       String result = "";
/*  99 */       if ("String".equalsIgnoreCase(fieldType))
/*     */       {
/* 105 */         result = "'" + value + "'";
/* 106 */       } else if ("Date".equalsIgnoreCase(fieldType))
/* 107 */         result = getDateFunction(value, "yyyy-MM-dd");
/* 108 */       else if ("Double".equalsIgnoreCase(fieldType))
/* 109 */         result = value;
/* 110 */       else if ("Integer".equalsIgnoreCase(fieldType))
/* 111 */         result = value;
/*     */       else {
/* 113 */         result = value;
/*     */       }
/* 115 */       return result;
/*     */     }
/* 117 */     return "";
/*     */   }
/*     */ 
/*     */   public static boolean sql_inj(String str)
/*     */   {
/* 127 */     if (StringUtil.isEmpty(str)) {
/* 128 */       return false;
/*     */     }
/* 130 */     String inj_str = "'|and|exec|insert|select|delete|update|count|chr|mid|master|truncate|char|declare|;|or|+|,";
/*     */ 
/* 132 */     String[] inj_stra = inj_str.split("\\|");
/* 133 */     for (int i = 0; i < inj_stra.length; i++) {
/* 134 */       if (str.indexOf(" " + inj_stra[i] + " ") >= 0) {
/* 135 */         return true;
/*     */       }
/*     */     }
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   public static void sql_inj_throw(String str)
/*     */   {
/* 145 */     if (sql_inj(str))
/* 146 */       throw new RuntimeException("请注意,填入的参数可能存在SQL注入!");
/*     */   }
/*     */ 
/*     */   public static String getDBType()
/*     */   {
/* 154 */     return DBTypeUtil.getDBType();
/*     */   }
/*     */ 
/*     */   public static String getDateFunction(String dateStr, String dateFormat)
/*     */   {
/* 163 */     String dbType = getDBType();
/* 164 */     String dateFunction = "";
/* 165 */     if ("mysql".equalsIgnoreCase(dbType))
/*     */     {
/* 167 */       dateFunction = "'" + dateStr + "'";
/* 168 */     } else if ("oracle".equalsIgnoreCase(dbType))
/*     */     {
/* 170 */       dateFunction = "TO_DATE('" + dateStr + "','" + dateFormat + "')";
/* 171 */     } else if ("sqlserver".equalsIgnoreCase(dbType))
/*     */     {
/* 173 */       dateFunction = "(CONVERT(VARCHAR,'" + dateStr + "') as DATETIME)";
/* 174 */     } else if ("postgres".equalsIgnoreCase(dbType))
/*     */     {
/* 176 */       dateFunction = "'" + dateStr + "'::date ";
/*     */     }
/* 178 */     else dateFunction = dateStr;
/*     */ 
/* 180 */     return dateFunction;
/*     */   }
/*     */ 
/*     */   public static String getJson(List<Map<String, Object>> result, Long size)
/*     */   {
/* 190 */     JSONObject main = new JSONObject();
/* 191 */     JSONArray rows = new JSONArray();
/* 192 */     main.put("total", size);
/* 193 */     for (Map m : result) {
/* 194 */       JSONObject item = new JSONObject();
/* 195 */       Iterator it = m.keySet().iterator();
/* 196 */       while (it.hasNext()) {
/* 197 */         String key = (String)it.next();
/* 198 */         String value = String.valueOf(m.get(key));
/* 199 */         key = key.toLowerCase();
/* 200 */         if ((key.contains("time")) || (key.contains("date"))) {
/* 201 */           value = datatimeFormat(value);
/*     */         }
/* 203 */         item.put(key, value);
/*     */       }
/* 205 */       rows.add(item);
/*     */     }
/* 207 */     main.put("rows", rows);
/* 208 */     return main.toString();
/*     */   }
/*     */ 
/*     */   public static String getJson(List<Map<String, Object>> result)
/*     */   {
/* 219 */     JSONArray rows = new JSONArray();
/* 220 */     for (Map m : result) {
/* 221 */       JSONObject item = new JSONObject();
/* 222 */       Iterator it = m.keySet().iterator();
/* 223 */       while (it.hasNext()) {
/* 224 */         String key = (String)it.next();
/* 225 */         String value = String.valueOf(m.get(key));
/* 226 */         key = key.toLowerCase();
/* 227 */         if ((key.contains("time")) || (key.contains("date"))) {
/* 228 */           value = datatimeFormat(value);
/*     */         }
/* 230 */         item.put(key, value);
/*     */       }
/* 232 */       rows.add(item);
/*     */     }
/* 234 */     return rows.toString();
/*     */   }
/*     */ 
/*     */   public static String datatimeFormat(String datetime)
/*     */   {
/* 243 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
/* 244 */     SimpleDateFormat dateFormatTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 245 */     Date d = null;
/*     */     try {
/* 247 */       d = dateFormat.parse(datetime);
/* 248 */       return dateFormatTo.format(d); } catch (Exception e) {
/*     */     }
/* 250 */     return datetime;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.util.QueryParamUtil
 * JD-Core Version:    0.6.2
 */