/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.jeecgframework.web.system.listener.OnlineListener;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
/*    */ 
/*    */ public class DBTypeUtil
/*    */ {
/* 17 */   private static Logger log = Logger.getLogger(DBTypeUtil.class);
/*    */ 
/*    */   public static String getDBType()
/*    */   {
/* 23 */     String retStr = "";
/* 24 */     ApplicationContext ctx = OnlineListener.getCtx();
/* 25 */     if (ctx == null) {
/* 26 */       return retStr;
/*    */     }
/* 28 */     LocalSessionFactoryBean sf = (LocalSessionFactoryBean)ctx.getBean("&sessionFactory");
/* 29 */     String dbdialect = sf.getHibernateProperties().getProperty("hibernate.dialect");
/* 30 */     log.debug(dbdialect);
/* 31 */     if (dbdialect.equals("org.hibernate.dialect.MySQLDialect"))
/* 32 */       retStr = "mysql";
/* 33 */     else if (dbdialect.contains("Oracle"))
/* 34 */       retStr = "oracle";
/* 35 */     else if (dbdialect.equals("org.hibernate.dialect.SQLServerDialect"))
/* 36 */       retStr = "sqlserver";
/* 37 */     else if (dbdialect.equals("org.hibernate.dialect.PostgreSQLDialect")) {
/* 38 */       retStr = "postgres";
/*    */     }
/* 40 */     return retStr;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.DBTypeUtil
 * JD-Core Version:    0.6.2
 */