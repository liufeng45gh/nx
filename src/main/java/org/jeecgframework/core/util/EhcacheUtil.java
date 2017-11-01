/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import net.sf.ehcache.Cache;
/*    */ import net.sf.ehcache.CacheManager;
/*    */ import net.sf.ehcache.Element;
/*    */ 
/*    */ public class EhcacheUtil
/*    */ {
/* 14 */   public static CacheManager manager = CacheManager.create();
/*    */ 
/*    */   public static Object get(String cacheName, Object key) {
/* 17 */     Cache cache = manager.getCache(cacheName);
/* 18 */     if (cache != null) {
/* 19 */       Element element = cache.get(key);
/* 20 */       if (element != null) {
/* 21 */         return element.getObjectValue();
/*    */       }
/*    */     }
/* 24 */     return null;
/*    */   }
/*    */ 
/*    */   public static void put(String cacheName, Object key, Object value) {
/* 28 */     Cache cache = manager.getCache(cacheName);
/* 29 */     if (cache != null)
/* 30 */       cache.put(new Element(key, value));
/*    */   }
/*    */ 
/*    */   public static boolean remove(String cacheName, Object key)
/*    */   {
/* 35 */     Cache cache = manager.getCache(cacheName);
/* 36 */     if (cache != null) {
/* 37 */       return cache.remove(key);
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 43 */     String key = "key";
/* 44 */     String value = "hello";
/* 45 */     put("mytest", key, value);
/* 46 */     System.out.println(get("mytest", key));
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.EhcacheUtil
 * JD-Core Version:    0.6.2
 */