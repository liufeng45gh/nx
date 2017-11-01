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
/*     */ import org.hibernate.annotations.DynamicInsert;
/*     */ import org.hibernate.annotations.DynamicUpdate;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jeecg_minidao", schema="")
/*     */ @DynamicUpdate(true)
/*     */ @DynamicInsert(true)
/*     */ public class JeecgMinidaoEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private Integer age;
/*     */   private Date birthday;
/*     */   private String content;
/*     */   private Date createTime;
/*     */   private String depId;
/*     */   private String email;
/*     */   private String mobilePhone;
/*     */   private String officePhone;
/*     */   private BigDecimal salary;
/*     */   private Integer sex;
/*     */   private String status;
/*     */   private String userName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  66 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  74 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="AGE", nullable=true, precision=10, scale=0)
/*     */   public Integer getAge()
/*     */   {
/*  82 */     return this.age;
/*     */   }
/*     */ 
/*     */   public void setAge(Integer age)
/*     */   {
/*  90 */     this.age = age;
/*     */   }
/*     */ 
/*     */   @Column(name="BIRTHDAY", nullable=true)
/*     */   public Date getBirthday()
/*     */   {
/*  98 */     return this.birthday;
/*     */   }
/*     */ 
/*     */   public void setBirthday(Date birthday)
/*     */   {
/* 106 */     this.birthday = birthday;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=true, length=255)
/*     */   public String getContent()
/*     */   {
/* 114 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 122 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_TIME", nullable=true)
/*     */   public Date getCreateTime()
/*     */   {
/* 130 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 138 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   @Column(name="DEP_ID", nullable=true, length=255)
/*     */   public String getDepId()
/*     */   {
/* 146 */     return this.depId;
/*     */   }
/*     */ 
/*     */   public void setDepId(String depId)
/*     */   {
/* 154 */     this.depId = depId;
/*     */   }
/*     */ 
/*     */   @Column(name="EMAIL", nullable=true, length=255)
/*     */   public String getEmail()
/*     */   {
/* 162 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 170 */     this.email = email;
/*     */   }
/*     */ 
/*     */   @Column(name="MOBILE_PHONE", nullable=true, length=255)
/*     */   public String getMobilePhone()
/*     */   {
/* 178 */     return this.mobilePhone;
/*     */   }
/*     */ 
/*     */   public void setMobilePhone(String mobilePhone)
/*     */   {
/* 186 */     this.mobilePhone = mobilePhone;
/*     */   }
/*     */ 
/*     */   @Column(name="OFFICE_PHONE", nullable=true, length=255)
/*     */   public String getOfficePhone()
/*     */   {
/* 194 */     return this.officePhone;
/*     */   }
/*     */ 
/*     */   public void setOfficePhone(String officePhone)
/*     */   {
/* 202 */     this.officePhone = officePhone;
/*     */   }
/*     */ 
/*     */   @Column(name="SALARY", nullable=true, precision=19, scale=2)
/*     */   public BigDecimal getSalary()
/*     */   {
/* 210 */     return this.salary;
/*     */   }
/*     */ 
/*     */   public void setSalary(BigDecimal salary)
/*     */   {
/* 218 */     this.salary = salary;
/*     */   }
/*     */ 
/*     */   @Column(name="SEX", nullable=true, precision=10, scale=0)
/*     */   public Integer getSex()
/*     */   {
/* 226 */     return this.sex;
/*     */   }
/*     */ 
/*     */   public void setSex(Integer sex)
/*     */   {
/* 234 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */   @Column(name="STATUS", nullable=true, length=255)
/*     */   public String getStatus()
/*     */   {
/* 242 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status)
/*     */   {
/* 250 */     this.status = status;
/*     */   }
/*     */ 
/*     */   @Column(name="USER_NAME", nullable=false, length=255)
/*     */   public String getUserName()
/*     */   {
/* 258 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName)
/*     */   {
/* 266 */     this.userName = userName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity
 * JD-Core Version:    0.6.2
 */