/*    */ package org.jeecgframework.core.extend.datasource;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/*    */ import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
/*    */ 
/*    */ public class DynamicDataSource extends AbstractRoutingDataSource
/*    */ {
/*    */   protected Object determineCurrentLookupKey()
/*    */   {
/* 19 */     DataSourceType dataSourceType = DataSourceContextHolder.getDataSourceType();
/* 20 */     return dataSourceType;
/*    */   }
/*    */ 
/*    */   public void setDataSourceLookup(DataSourceLookup dataSourceLookup)
/*    */   {
/* 25 */     super.setDataSourceLookup(dataSourceLookup);
/*    */   }
/*    */ 
/*    */   public void setDefaultTargetDataSource(Object defaultTargetDataSource)
/*    */   {
/* 30 */     super.setDefaultTargetDataSource(defaultTargetDataSource);
/*    */   }
/*    */ 
/*    */   public void setTargetDataSources(Map targetDataSources)
/*    */   {
/* 35 */     super.setTargetDataSources(targetDataSources);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.datasource.DynamicDataSource
 * JD-Core Version:    0.6.2
 */