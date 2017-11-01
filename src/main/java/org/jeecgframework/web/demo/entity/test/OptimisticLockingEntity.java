/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Version;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="OPTIMISTIC_LOCKING", schema="")
/*     */ public class OptimisticLockingEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String name;
/*     */   private Integer age;
/*     */   private Integer account;
/*     */   private Integer ver;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  52 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  60 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=true, length=85)
/*     */   public String getName()
/*     */   {
/*  68 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  76 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="AGE", nullable=true, precision=10, scale=0)
/*     */   public Integer getAge()
/*     */   {
/*  84 */     return this.age;
/*     */   }
/*     */ 
/*     */   public void setAge(Integer age)
/*     */   {
/*  92 */     this.age = age;
/*     */   }
/*     */ 
/*     */   @Column(name="ACCOUNT", nullable=true, precision=10, scale=0)
/*     */   public Integer getAccount()
/*     */   {
/* 100 */     return this.account;
/*     */   }
/*     */ 
/*     */   public void setAccount(Integer account)
/*     */   {
/* 108 */     this.account = account;
/*     */   }
/*     */ 
/*     */   @Version
/*     */   @Column(name="VER")
/*     */   public Integer getVer()
/*     */   {
/* 117 */     return this.ver;
/*     */   }
/*     */ 
/*     */   public void setVer(Integer ver)
/*     */   {
/* 125 */     this.ver = ver;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.OptimisticLockingEntity
 * JD-Core Version:    0.6.2
 */