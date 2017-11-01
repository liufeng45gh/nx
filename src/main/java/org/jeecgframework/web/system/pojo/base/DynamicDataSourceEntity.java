/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.DynamicInsert;
/*     */ import org.hibernate.annotations.DynamicUpdate;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_data_source", schema="")
/*     */ @DynamicUpdate(true)
/*     */ @DynamicInsert(true)
/*     */ public class DynamicDataSourceEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String dbKey;
/*     */   private String description;
/*     */   private String driverClass;
/*     */   private String url;
/*     */   private String dbUser;
/*     */   private String dbPassword;
/*     */   private String dbType;
/*     */   private String dbName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, precision=36, length=36)
/*     */   public String getId()
/*     */   {
/*  58 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  66 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_KEY", nullable=false, precision=50, length=50)
/*     */   public String getDbKey()
/*     */   {
/*  74 */     return this.dbKey;
/*     */   }
/*     */ 
/*     */   public void setDbKey(String dbKey)
/*     */   {
/*  82 */     this.dbKey = dbKey;
/*     */   }
/*     */ 
/*     */   @Column(name="DESCRIPTION", nullable=false, precision=50, length=50)
/*     */   public String getDescription()
/*     */   {
/*  90 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/*  98 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="DRIVER_CLASS", nullable=false, precision=50, length=50)
/*     */   public String getDriverClass()
/*     */   {
/* 106 */     return this.driverClass;
/*     */   }
/*     */ 
/*     */   public void setDriverClass(String driverClass)
/*     */   {
/* 114 */     this.driverClass = driverClass;
/*     */   }
/*     */ 
/*     */   @Column(name="URL", nullable=false, precision=100, length=100)
/*     */   public String getUrl()
/*     */   {
/* 122 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/* 130 */     this.url = url;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_USER", nullable=false, precision=50, length=50)
/*     */   public String getDbUser()
/*     */   {
/* 138 */     return this.dbUser;
/*     */   }
/*     */ 
/*     */   public void setDbUser(String dbUser)
/*     */   {
/* 146 */     this.dbUser = dbUser;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_PASSWORD", nullable=true, precision=50, length=50)
/*     */   public String getDbPassword()
/*     */   {
/* 154 */     return this.dbPassword;
/*     */   }
/*     */ 
/*     */   public void setDbPassword(String dbPassword)
/*     */   {
/* 162 */     this.dbPassword = dbPassword;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_TYPE", nullable=true, precision=50, length=50)
/*     */   public String getDbType()
/*     */   {
/* 170 */     return this.dbType;
/*     */   }
/*     */ 
/*     */   public void setDbType(String dbType)
/*     */   {
/* 178 */     this.dbType = dbType;
/*     */   }
/*     */ 
/*     */   @Column(name="DB_NAME", nullable=true, precision=50, length=50)
/*     */   public String getDbName()
/*     */   {
/* 188 */     return this.dbName;
/*     */   }
/*     */ 
/*     */   public void setDbName(String dbName)
/*     */   {
/* 196 */     this.dbName = dbName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity
 * JD-Core Version:    0.6.2
 */