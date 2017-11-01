/*    */ package org.jeecgframework.core.extend.datasource;
/*    */ 
/*    */ public class DataSourceContextHolder
/*    */ {
/*  8 */   private static final ThreadLocal contextHolder = new ThreadLocal();
/*    */ 
/*    */   public static void setDataSourceType(DataSourceType dataSourceType) {
/* 11 */     contextHolder.set(dataSourceType);
/*    */   }
/*    */ 
/*    */   public static DataSourceType getDataSourceType() {
/* 15 */     return (DataSourceType)contextHolder.get();
/*    */   }
/*    */ 
/*    */   public static void clearDataSourceType() {
/* 19 */     contextHolder.remove();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.datasource.DataSourceContextHolder
 * JD-Core Version:    0.6.2
 */