/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jg_person", schema="")
/*     */ public class JeecgNoteEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private Integer age;
/*     */   private Date birthday;
/*     */   private Date createdt;
/*     */   private String name;
/*     */   private BigDecimal salary;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  50 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  58 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="AGE", nullable=true)
/*     */   public Integer getAge()
/*     */   {
/*  66 */     return this.age;
/*     */   }
/*     */ 
/*     */   public void setAge(Integer age)
/*     */   {
/*  74 */     this.age = age;
/*     */   }
/*     */ 
/*     */   @Column(name="BIRTHDAY", nullable=true)
/*     */   public Date getBirthday()
/*     */   {
/*  82 */     return this.birthday;
/*     */   }
/*     */ 
/*     */   public void setBirthday(Date birthday)
/*     */   {
/*  90 */     this.birthday = birthday;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATEDT", nullable=true)
/*     */   public Date getCreatedt()
/*     */   {
/*  98 */     return this.createdt;
/*     */   }
/*     */ 
/*     */   public void setCreatedt(Date createdt)
/*     */   {
/* 106 */     this.createdt = createdt;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=false)
/*     */   public String getName()
/*     */   {
/* 114 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 122 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="SALARY", nullable=true)
/*     */   public BigDecimal getSalary()
/*     */   {
/* 130 */     return this.salary;
/*     */   }
/*     */ 
/*     */   public void setSalary(BigDecimal salary)
/*     */   {
/* 138 */     this.salary = salary;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgNoteEntity
 * JD-Core Version:    0.6.2
 */