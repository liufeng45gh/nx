/*     */ package org.jeecgframework.web.cgform.common;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.jeecgframework.core.util.LogUtil;
/*     */ import org.jeecgframework.web.cgform.exception.BusinessException;
/*     */ 
/*     */ public class CommUtils
/*     */ {
/*     */   public static Map attributeMapFilter(Map map, String[] filterName)
/*     */   {
/*  28 */     for (int i = 0; i < filterName.length; i++)
/*     */     {
/*  30 */       if (map.containsKey(filterName[i])) map.remove(filterName[i]);
/*     */     }
/*  32 */     return map;
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> mapConvert(Map map)
/*     */   {
/*  37 */     Map dataMap = new HashMap(0);
/*  38 */     if (map != null) {
/*  39 */       Iterator it = map.entrySet().iterator();
/*  40 */       while (it.hasNext()) {
/*  41 */         Map.Entry entry = (Map.Entry)it.next();
/*  42 */         Object ok = entry.getKey();
/*  43 */         Object ov = entry.getValue() == null ? "" : entry.getValue();
/*  44 */         String key = ok.toString();
/*  45 */         String keyval = "";
/*  46 */         String[] value = new String[1];
/*  47 */         if ((ov instanceof String[]))
/*  48 */           value = (String[])ov;
/*     */         else {
/*  50 */           value[0] = ov.toString();
/*     */         }
/*  52 */         keyval = keyval + value[0];
/*  53 */         for (int k = 1; k < value.length; k++) {
/*  54 */           keyval = keyval + "," + value[k];
/*     */         }
/*  56 */         dataMap.put(key, keyval);
/*     */       }
/*     */     }
/*  59 */     return dataMap;
/*     */   }
/*     */ 
/*     */   public static Map<String, List<Map<String, Object>>> mapConvertMore(Map map, String tableName)
/*     */   {
/*  64 */     Map fanalMap = new HashMap();
/*  65 */     Map dataMap = new HashMap(0);
/*  66 */     Map mapField = null;
/*  67 */     if (map != null) {
/*  68 */       Iterator it = map.entrySet().iterator();
/*  69 */       while (it.hasNext()) {
/*  70 */         Map.Entry entry = (Map.Entry)it.next();
/*  71 */         Object ok = entry.getKey();
/*  72 */         Object ov = entry.getValue() == null ? "" : entry.getValue();
/*  73 */         String key = ok.toString();
/*  74 */         String keyval = "";
/*  75 */         String[] value = new String[1];
/*  76 */         if ((ov instanceof String[]))
/*  77 */           value = (String[])ov;
/*     */         else {
/*  79 */           value[0] = ov.toString();
/*     */         }
/*  81 */         keyval = keyval + value[0];
/*  82 */         for (int k = 1; k < value.length; k++) {
/*  83 */           keyval = keyval + "," + value[k];
/*     */         }
/*  85 */         String[] keyArr = key.split("\\.");
/*  86 */         mapField = new HashMap(0);
/*  87 */         if (keyArr.length == 1) {
/*  88 */           if (dataMap.get(tableName) != null) {
/*  89 */             mapField = (Map)dataMap.get(tableName);
/*     */           }
/*  91 */           mapField.put(keyArr[0], keyval);
/*  92 */           dataMap.put(tableName, mapField);
/*  93 */         } else if (keyArr.length == 2) {
/*  94 */           if (dataMap.get(keyArr[0]) != null) {
/*  95 */             mapField = (Map)dataMap.get(keyArr[0]);
/*     */           }
/*  97 */           mapField.put(keyArr[1], keyval);
/*  98 */           dataMap.put(keyArr[0], mapField);
/*     */         }
/*     */       }
/*     */     }
/* 102 */     List listField = null;
/* 103 */     if (dataMap.size() > 0) {
/* 104 */       Iterator it = dataMap.entrySet().iterator();
/* 105 */       while (it.hasNext()) {
/* 106 */         Map.Entry entry = (Map.Entry)it.next();
/* 107 */         String ok = (String)entry.getKey();
/* 108 */         Map ov = (Map)entry.getValue();
/* 109 */         listField = new ArrayList();
/* 110 */         if (ok.indexOf("[") != -1) {
/* 111 */           ok = ok.substring(0, ok.indexOf("["));
/*     */         }
/* 113 */         if (fanalMap.get(ok) != null) {
/* 114 */           listField = (List)fanalMap.get(ok);
/*     */         }
/* 116 */         listField.add(ov);
/* 117 */         fanalMap.put(ok, listField);
/*     */       }
/*     */     }
/*     */ 
/* 121 */     return fanalMap;
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> convertFKMap(Map<String, Object> fieldMap, Map<String, Object> mainMap, List<Map<String, Object>> fkFieldList) throws BusinessException
/*     */   {
/* 126 */     if (fkFieldList != null) {
/* 127 */       for (Map fkField : fkFieldList) {
/* 128 */         if (mainMap.get((String)fkField.get("main_field")) != null)
/* 129 */           fieldMap.put((String)fkField.get("field_name"), mainMap.get((String)fkField.get("main_field")));
/*     */         else {
/* 131 */           throw new BusinessException("表单中没有外键：" + (String)fkField.get("main_field"));
/*     */         }
/*     */       }
/*     */     }
/* 135 */     return fieldMap;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 139 */     String str = "jform_tbd[0].name";
/* 140 */     String[] strs = str.split("\\.");
/* 141 */     LogUtil.info(strs.length);
/* 142 */     LogUtil.info(strs[0].substring(0, strs[0].indexOf("[")));
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.common.CommUtils
 * JD-Core Version:    0.6.2
 */