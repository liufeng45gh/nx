/*    */ package org.jeecgframework.core.enums;
/*    */ 
/*    */ import org.jeecgframework.core.util.StringUtil;
/*    */ 
/*    */ public enum SysDatabaseEnum
/*    */ {
/* 12 */   MYSQL("mysql", "jdbc:mysql://SERVERADDRESS:PORT/YOURDATABASENAME?useUnicode=true&characterEncoding=UTF-8", "com.mysql.jdbc.Driver"), 
/* 13 */   SQLSERVER2008("sqlserver2008", "jdbc:sqlserver://SERVERADDRESS:PORT;DatabaseName=YOURDATABASENAME", "com.microsoft.sqlserver.jdbc.SQLServerDriver"), 
/* 14 */   ORACLE("oracle", "jdbc:oracle:thin:@SERVERADDRESS:PORT:YOURDATABASENAME", "oracle.jdbc.driver.OracleDriver");
/*    */ 
/*    */   private String dbtype;
/*    */   private String url;
/*    */   private String driverClass;
/*    */ 
/*    */   private SysDatabaseEnum(String dbtype, String url, String driverClass)
/*    */   {
/* 33 */     this.dbtype = dbtype;
/* 34 */     this.url = url;
/* 35 */     this.driverClass = driverClass;
/*    */   }
/*    */ 
/*    */   public String getDbtype() {
/* 39 */     return this.dbtype;
/*    */   }
/*    */ 
/*    */   public void setDbtype(String dbtype) {
/* 43 */     this.dbtype = dbtype;
/*    */   }
/*    */ 
/*    */   public String getUrl() {
/* 47 */     return this.url;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 51 */     this.url = url;
/*    */   }
/*    */ 
/*    */   public String getDriverClass() {
/* 55 */     return this.driverClass;
/*    */   }
/*    */ 
/*    */   public void setDriverClass(String driverClass) {
/* 59 */     this.driverClass = driverClass;
/*    */   }
/*    */ 
/*    */   public static SysDatabaseEnum toEnum(String dbtype) {
/* 63 */     if (StringUtil.isEmpty(dbtype)) {
/* 64 */       return null;
/*    */     }
/* 66 */     for (SysDatabaseEnum item : values()) {
/* 67 */       if (item.getDbtype().equals(dbtype)) {
/* 68 */         return item;
/*    */       }
/*    */     }
/* 71 */     return null;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 75 */     return "{dbtype: " + this.dbtype + ", url: " + this.url + ", driverClass: " + this.driverClass + "}";
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.enums.SysDatabaseEnum
 * JD-Core Version:    0.6.2
 */