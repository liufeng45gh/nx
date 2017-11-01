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
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jg_person", schema="")
/*     */ public class JpPersonEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="年龄", width=10.0D)
/*     */   private Integer age;
/*     */ 
/*     */   @Excel(name="生日", format="yyyy-MM-dd", width=30.0D)
/*     */   private Date birthday;
/*     */ 
/*     */   @Excel(name="出生日期", format="yyyy-MM-dd HH:mm:ss", width=30.0D)
/*     */   private Date createdt;
/*     */ 
/*     */   @Excel(name="用户名", height=20.0D)
/*     */   private String name;
/*     */ 
/*     */   @Excel(name="工资")
/*     */   private BigDecimal salary;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  56 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  64 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="AGE", nullable=false, precision=10, scale=0)
/*     */   public Integer getAge()
/*     */   {
/*  72 */     return this.age;
/*     */   }
/*     */ 
/*     */   public void setAge(Integer age)
/*     */   {
/*  80 */     this.age = age;
/*     */   }
/*     */ 
/*     */   @Column(name="BIRTHDAY", nullable=true)
/*     */   public Date getBirthday()
/*     */   {
/*  88 */     return this.birthday;
/*     */   }
/*     */ 
/*     */   public void setBirthday(Date birthday)
/*     */   {
/*  96 */     this.birthday = birthday;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATEDT", nullable=false)
/*     */   public Date getCreatedt()
/*     */   {
/* 104 */     return this.createdt;
/*     */   }
/*     */ 
/*     */   public void setCreatedt(Date createdt)
/*     */   {
/* 112 */     this.createdt = createdt;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=true, length=255)
/*     */   public String getName()
/*     */   {
/* 120 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 128 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="SALARY", nullable=false, precision=19, scale=2)
/*     */   public BigDecimal getSalary()
/*     */   {
/* 136 */     return this.salary;
/*     */   }
/*     */ 
/*     */   public void setSalary(BigDecimal salary)
/*     */   {
/* 144 */     this.salary = salary;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JpPersonEntity
 * JD-Core Version:    0.6.2
 */