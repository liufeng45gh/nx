/*    */ package org.jeecgframework.web.cgform.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class AutoFormCommUtil
/*    */ {
/* 13 */   private static final Logger logger = Logger.getLogger(AutoFormCommUtil.class);
/*    */ 
/*    */   public static Map<String, Map<String, Object>> mapConvert(Map map)
/*    */   {
/* 17 */     Map dataMap = new HashMap();
/* 18 */     Map subDataMap = null;
/* 19 */     if (map != null) {
/* 20 */       Iterator it = map.entrySet().iterator();
/* 21 */       while (it.hasNext()) {
/* 22 */         Map.Entry entry = (Map.Entry)it.next();
/* 23 */         Object ok = entry.getKey();
/* 24 */         Object ov = entry.getValue() == null ? "" : entry.getValue();
/* 25 */         String key = ok.toString();
/* 26 */         String keyval = "";
/* 27 */         String[] value = new String[1];
/* 28 */         if ((ov instanceof String[]))
/* 29 */           value = (String[])ov;
/*    */         else {
/* 31 */           value[0] = ov.toString();
/*    */         }
/* 33 */         keyval = keyval + value[0];
/* 34 */         for (int k = 1; k < value.length; k++) {
/* 35 */           keyval = keyval + "," + value[k];
/*    */         }
/* 37 */         String[] keys = key.split("\\.");
/* 38 */         if (keys.length == 2) {
/* 39 */           String index = "";
/* 40 */           if (keys[1].indexOf("[") != -1) {
/* 41 */             index = keys[1].substring(keys[1].indexOf("["));
/* 42 */             keys[0] = (keys[0] + index);
/* 43 */             keys[1] = keys[1].substring(0, keys[1].indexOf("["));
/*    */           }
/* 45 */           subDataMap = (Map)dataMap.get(keys[0]);
/* 46 */           if (subDataMap != null) {
/* 47 */             subDataMap.put(keys[1], keyval);
/*    */           } else {
/* 49 */             subDataMap = new HashMap();
/* 50 */             subDataMap.put(keys[1], keyval);
/* 51 */             dataMap.put(keys[0], subDataMap);
/*    */           }
/* 53 */           logger.info("ds:" + keys[0] + ";name:" + keys[1] + ";value:" + keyval);
/*    */         } else {
/* 55 */           String paramKey = "param";
/* 56 */           subDataMap = (Map)dataMap.get(paramKey);
/* 57 */           if (subDataMap != null) {
/* 58 */             subDataMap.put(keys[0], keyval);
/*    */           } else {
/* 60 */             subDataMap = new HashMap();
/* 61 */             subDataMap.put(keys[0], keyval);
/* 62 */             dataMap.put(paramKey, subDataMap);
/*    */           }
/* 64 */           logger.info("ds:" + paramKey + ";name:" + keys[0] + ";value:" + keyval);
/*    */         }
/*    */       }
/*    */     }
/*    */ 
/* 69 */     return dataMap;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.util.AutoFormCommUtil
 * JD-Core Version:    0.6.2
 */