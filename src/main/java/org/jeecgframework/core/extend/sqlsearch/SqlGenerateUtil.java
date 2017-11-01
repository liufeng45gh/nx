/*     */ package org.jeecgframework.core.extend.sqlsearch;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.jeecgframework.codegenerate.util.CodeResourceUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ 
/*     */ public class SqlGenerateUtil
/*     */ {
/*     */   private static final String END = "_end";
/*     */   private static final String BEGIN = "_begin";
/*  21 */   private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
/*     */ 
/*     */   public static String generateTable(Object searchObj)
/*     */   {
/*  29 */     Table table = (Table)searchObj.getClass().getAnnotation(Table.class);
/*  30 */     if (StringUtil.isEmpty(table.name())) {
/*  31 */       return searchObj.getClass().getSimpleName();
/*     */     }
/*  33 */     return table.name();
/*     */   }
/*     */ 
/*     */   public static StringBuffer generateDBFields(Object searchObj, String fields, List dealfields)
/*     */   {
/*  46 */     StringBuffer dbFields = new StringBuffer();
/*  47 */     PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(searchObj.getClass());
/*  48 */     String[] fileNames = fields.split(",");
/*  49 */     for (int i = 0; i < fileNames.length; i++) {
/*  50 */       for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
/*  51 */         String propertyName = propertyDescriptor.getName();
/*  52 */         if (fileNames[i].equals(propertyName)) {
/*  53 */           dbFields.append(getDbNameByFieldName(propertyDescriptor) + (i == fileNames.length - 1 ? "" : ","));
/*  54 */           dealfields.add(fileNames[i]);
/*  55 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  60 */     return dbFields;
/*     */   }
/*     */ 
/*     */   public static StringBuffer generateWhere(Object searchObj, Map<String, String[]> parameterMap)
/*     */   {
/*  70 */     StringBuffer whereSql = new StringBuffer(" where 1=1 ");
/*     */     try {
/*  72 */       PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(searchObj.getClass());
/*     */ 
/*  75 */       for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
/*  76 */         String propertyType = propertyDescriptor.getPropertyType().toString();
/*  77 */         String propertyName = propertyDescriptor.getName();
/*  78 */         Object propertyValue = PropertyUtils.getSimpleProperty(searchObj, propertyName);
/*     */ 
/*  80 */         String dbColumnName = getDbNameByFieldName(propertyDescriptor);
/*     */ 
/*  82 */         String beginValue = null;
/*  83 */         String endValue = null;
/*     */ 
/*  85 */         if ((parameterMap != null) && ((parameterMap.containsKey(propertyName + "_begin")) || (parameterMap.containsKey(propertyName + "_end")))) {
/*  86 */           if ((parameterMap != null) && (parameterMap.containsKey(propertyName + "_begin"))) {
/*  87 */             beginValue = ((String[])parameterMap.get(propertyName + "_begin"))[0].trim();
/*  88 */             if (StringUtil.isNotEmpty(beginValue)) {
/*  89 */               String beginValueReturn = getValueForType(propertyName + "_begin", beginValue, propertyType);
/*  90 */               if (StringUtil.isNotEmpty(beginValueReturn)) {
/*  91 */                 whereSql.append("and " + dbColumnName + ">=" + beginValueReturn + " ");
/*     */               }
/*     */             }
/*     */           }
/*  95 */           if ((parameterMap != null) && (parameterMap.containsKey(propertyName + "_end"))) {
/*  96 */             endValue = ((String[])parameterMap.get(propertyName + "_end"))[0].trim();
/*  97 */             if (StringUtil.isNotEmpty(endValue)) {
/*  98 */               String endValueReturn = getValueForType(propertyName + "_end", endValue, propertyType);
/*  99 */               if (StringUtil.isNotEmpty(endValueReturn)) {
/* 100 */                 whereSql.append("and " + dbColumnName + "<=" + endValueReturn + " ");
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 105 */         else if (StringUtil.isNotEmpty(propertyValue)) {
/* 106 */           String propertyValueReturn = getValueForType(propertyName, propertyValue, propertyType);
/* 107 */           if (StringUtil.isNotEmpty(propertyValueReturn))
/* 108 */             whereSql.append("and " + dbColumnName + "=" + propertyValueReturn + " ");
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 114 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 117 */     return whereSql;
/*     */   }
/*     */ 
/*     */   public static String getDbNameByFieldName(PropertyDescriptor propertyDescriptor)
/*     */   {
/* 126 */     String propertyName = propertyDescriptor.getName();
/* 127 */     Column column = null;
/* 128 */     Method readMethod = propertyDescriptor.getReadMethod();
/* 129 */     if (readMethod != null) {
/* 130 */       column = (Column)readMethod.getAnnotation(Column.class);
/* 131 */       if (column == null) {
/* 132 */         Method writeMethod = propertyDescriptor.getWriteMethod();
/* 133 */         if (writeMethod != null) {
/* 134 */           column = (Column)writeMethod.getAnnotation(Column.class);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 140 */     if ((column == null) || (StringUtil.isEmpty(column.name()))) {
/* 141 */       return propertyName;
/*     */     }
/* 143 */     return column.name();
/*     */   }
/*     */ 
/*     */   public static String getValueForType(String name, Object value, String type)
/*     */   {
/* 155 */     if (("class java.lang.Integer".equals(type)) || 
/* 156 */       ("class java.lang.Double".equals(type)) || 
/* 157 */       ("class java.lang.Long".equals(type)) || 
/* 158 */       (type.contains("class java.math")))
/* 159 */       return String.valueOf(value);
/* 160 */     if ("class java.util.Date".equals(type)) {
/* 161 */       String dbType = CodeResourceUtil.DATABASE_TYPE;
/*     */ 
/* 163 */       if ("oracle".equals(dbType)) {
/* 164 */         if (name.contains("_begin")) {
/* 165 */           String beginValue = (String)value;
/* 166 */           if (beginValue.length() == 19) {
/* 167 */             return "'date" + beginValue + "'";
/*     */           }
/* 169 */           return "'date" + beginValue + " 00:00:00'";
/*     */         }
/* 171 */         if (name.contains("_end")) {
/* 172 */           String endValue = (String)value;
/* 173 */           if (endValue.length() == 19) {
/* 174 */             return "'date" + endValue + "'";
/*     */           }
/* 176 */           return "'date" + endValue + " 23:23:59'";
/*     */         }
/*     */ 
/* 179 */         return "date'" + time.format(value) + "'";
/*     */       }
/*     */ 
/* 182 */       if (name.contains("_begin")) {
/* 183 */         String beginValue = (String)value;
/* 184 */         if (beginValue.length() == 19) {
/* 185 */           return "'" + beginValue + "'";
/*     */         }
/* 187 */         return "'" + beginValue + " 00:00:00'";
/*     */       }
/* 189 */       if (name.contains("_end")) {
/* 190 */         String endValue = (String)value;
/* 191 */         if (endValue.length() == 19) {
/* 192 */           return "'" + endValue + "'";
/*     */         }
/* 194 */         return "'" + endValue + " 23:23:59'";
/*     */       }
/*     */ 
/* 197 */       return "'" + time.format(value) + "'";
/*     */     }
/*     */ 
/* 201 */     if ("class java.lang.String".equals(type)) {
/* 202 */       return String.valueOf("'" + value + "'");
/*     */     }
/* 204 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.sqlsearch.SqlGenerateUtil
 * JD-Core Version:    0.6.2
 */