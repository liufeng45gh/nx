/*     */ package org.jeecgframework.web.cgform.service.impl.config.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.engine.spi.SessionFactoryImplementor;
/*     */ import org.hibernate.internal.SessionImpl;
/*     */ import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
/*     */ import org.jeecgframework.web.cgform.service.config.DbTableServiceI;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.DbTableMysqlHandleImpl;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.DbTableOracleHandleImpl;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.DbTablePostgresHandleImpl;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.DbTableServiceMysqlImpl;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.TableSQLServerHandleImpl;
/*     */ 
/*     */ public class DbTableUtil
/*     */ {
/*     */   public static Map<String, Object> getColumnMap(List<Map<String, Object>> queryForList)
/*     */   {
/*  33 */     Map columnMap = new HashMap();
/*  34 */     for (int i = 0; i < queryForList.size(); i++) {
/*  35 */       columnMap.put(((Map)queryForList.get(i)).get("column_name").toString(), queryForList.get(i));
/*     */     }
/*  37 */     return columnMap;
/*     */   }
/*     */ 
/*     */   public static String translatorToDbField(String fileName)
/*     */   {
/*  48 */     return fileName;
/*     */   }
/*     */ 
/*     */   public static DbTableServiceI getTableUtil(Session session)
/*     */   {
/*  62 */     DbTableServiceI tableUtil = null;
/*  63 */     String dialect = ((SessionImpl)session).getFactory().getDialect()
/*  64 */       .getClass().getName();
/*  65 */     if (dialect.equals("org.hibernate.dialect.MySQLDialect")) {
/*  66 */       tableUtil = new DbTableServiceMysqlImpl();
/*     */     }
/*  68 */     return tableUtil;
/*     */   }
/*     */ 
/*     */   public static DbTableHandleI getTableHandle(Session session) {
/*  72 */     DbTableHandleI dbTableHandle = null;
/*  73 */     String dialect = ((SessionImpl)session).getFactory().getDialect()
/*  74 */       .getClass().getName();
/*  75 */     if (dialect.equals("org.hibernate.dialect.MySQLDialect"))
/*  76 */       dbTableHandle = new DbTableMysqlHandleImpl();
/*  77 */     else if (dialect.contains("Oracle"))
/*  78 */       dbTableHandle = new DbTableOracleHandleImpl();
/*  79 */     else if (dialect.equals("org.hibernate.dialect.PostgreSQLDialect"))
/*  80 */       dbTableHandle = new DbTablePostgresHandleImpl();
/*  81 */     else if (dialect.equals("org.hibernate.dialect.SQLServerDialect")) {
/*  82 */       dbTableHandle = new TableSQLServerHandleImpl();
/*     */     }
/*  84 */     return dbTableHandle;
/*     */   }
/*     */ 
/*     */   public static String getDataType(Session session)
/*     */   {
/*  94 */     String dataType = "MYSQL";
/*  95 */     String dialect = ((SessionImpl)session).getFactory().getDialect()
/*  96 */       .getClass().getName();
/*  97 */     if (dialect.equals("org.hibernate.dialect.MySQLDialect"))
/*  98 */       dataType = "MYSQL";
/*  99 */     else if (dialect.contains("Oracle"))
/* 100 */       dataType = "ORACLE";
/* 101 */     else if (dialect.equals("org.hibernate.dialect.PostgreSQLDialect"))
/* 102 */       dataType = "POSTGRESQL";
/* 103 */     else if (dialect.equals("org.hibernate.dialect.SQLServerDialect")) {
/* 104 */       dataType = "SQLSERVER";
/*     */     }
/* 106 */     return dataType;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.config.util.DbTableUtil
 * JD-Core Version:    0.6.2
 */