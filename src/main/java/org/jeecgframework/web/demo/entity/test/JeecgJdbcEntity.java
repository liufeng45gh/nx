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
/*     */ @Table(name="jeecg_demo", schema="")
/*     */ public class JeecgJdbcEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private Integer age;
/*     */   private Date birthday;
/*     */   private Date createTime;
/*     */   private String depId;
/*     */   private String email;
/*     */   private String mobilePhone;
/*     */   private String officePhone;
/*     */   private BigDecimal salary;
/*     */   private String sex;
/*     */   private String userName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  60 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  68 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="AGE", nullable=true, precision=10, scale=0)
/*     */   public Integer getAge()
/*     */   {
/*  76 */     return this.age;
/*     */   }
/*     */ 
/*     */   public void setAge(Integer age)
/*     */   {
/*  84 */     this.age = age;
/*     */   }
/*     */ 
/*     */   @Column(name="BIRTHDAY", nullable=true)
/*     */   public Date getBirthday()
/*     */   {
/*  92 */     return this.birthday;
/*     */   }
/*     */ 
/*     */   public void setBirthday(Date birthday)
/*     */   {
/* 100 */     this.birthday = birthday;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_TIME", nullable=true)
/*     */   public Date getCreateTime()
/*     */   {
/* 108 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 116 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   @Column(name="DEP_ID", nullable=true, length=255)
/*     */   public String getDepId()
/*     */   {
/* 124 */     return this.depId;
/*     */   }
/*     */ 
/*     */   public void setDepId(String depId)
/*     */   {
/* 132 */     this.depId = depId;
/*     */   }
/*     */ 
/*     */   @Column(name="EMAIL", nullable=true, length=255)
/*     */   public String getEmail()
/*     */   {
/* 140 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 148 */     this.email = email;
/*     */   }
/*     */ 
/*     */   @Column(name="MOBILE_PHONE", nullable=true, length=255)
/*     */   public String getMobilePhone()
/*     */   {
/* 156 */     return this.mobilePhone;
/*     */   }
/*     */ 
/*     */   public void setMobilePhone(String mobilePhone)
/*     */   {
/* 164 */     this.mobilePhone = mobilePhone;
/*     */   }
/*     */ 
/*     */   @Column(name="OFFICE_PHONE", nullable=true, length=255)
/*     */   public String getOfficePhone()
/*     */   {
/* 172 */     return this.officePhone;
/*     */   }
/*     */ 
/*     */   public void setOfficePhone(String officePhone)
/*     */   {
/* 180 */     this.officePhone = officePhone;
/*     */   }
/*     */ 
/*     */   @Column(name="SALARY", nullable=true, precision=19, scale=2)
/*     */   public BigDecimal getSalary()
/*     */   {
/* 188 */     return this.salary;
/*     */   }
/*     */ 
/*     */   public void setSalary(BigDecimal salary)
/*     */   {
/* 196 */     this.salary = salary;
/*     */   }
/*     */ 
/*     */   @Column(name="SEX", nullable=true, length=4)
/*     */   public String getSex()
/*     */   {
/* 204 */     return this.sex;
/*     */   }
/*     */ 
/*     */   public void setSex(String sex)
/*     */   {
/* 212 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */   @Column(name="USER_NAME", nullable=false, length=255)
/*     */   public String getUserName()
/*     */   {
/* 220 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName)
/*     */   {
/* 228 */     this.userName = userName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgJdbcEntity
 * JD-Core Version:    0.6.2
 */