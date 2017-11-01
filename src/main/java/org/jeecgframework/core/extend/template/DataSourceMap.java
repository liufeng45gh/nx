/*    */ package org.jeecgframework.core.extend.template;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class DataSourceMap
/*    */ {
/*    */   private static Map<Object, Object> dsm;
/*    */ 
/*    */   static
/*    */   {
/* 15 */     InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dataSourceMap.properties");
/* 16 */     Properties p = new Properties();
/*    */     try {
/* 18 */       p.load(is);
/* 19 */       dsm = new HashMap();
/* 20 */       Set ps = p.entrySet();
/* 21 */       for (Iterator iterator = ps.iterator(); iterator.hasNext(); ) {
/* 22 */         Map.Entry entry = (Map.Entry)iterator.next();
/* 23 */         dsm.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString().trim());
/*    */       }
/* 25 */       is.close();
/* 26 */       is = null;
/*    */     } catch (IOException e) {
/* 28 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/* 32 */   public static Map<Object, Object> getDataSourceMap() { Map datadsm = new HashMap();
/* 33 */     datadsm.putAll(dsm);
/* 34 */     return datadsm;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.template.DataSourceMap
 * JD-Core Version:    0.6.2
 */