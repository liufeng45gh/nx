/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.Inheritance;
/*     */ import javax.persistence.InheritanceType;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jeecg_demo")
/*     */ @Inheritance(strategy=InheritanceType.JOINED)
/*     */ public class JeecgDemo extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String mobilePhone;
/*     */   private String officePhone;
/*     */   private String email;
/*     */   private Integer age;
/*     */   private BigDecimal salary;
/*     */   private Date birthday;
/*     */   private Date createDate;
/*     */   private String sex;
/*     */   private String depId;
/*     */   private String userName;
/*     */   private String status;
/*     */   private String content;
/*     */ 
/*     */   public String getStatus()
/*     */   {
/*  51 */     return this.status;
/*     */   }
/*     */   @Column(name="status", nullable=true)
/*     */   public void setStatus(String status) {
/*  55 */     this.status = status;
/*     */   }
/*     */ 
/*     */   @Column(name="MOBILE_PHONE", nullable=true)
/*     */   public String getMobilePhone()
/*     */   {
/*  64 */     return this.mobilePhone;
/*     */   }
/*     */ 
/*     */   public void setMobilePhone(String mobilePhone)
/*     */   {
/*  72 */     this.mobilePhone = mobilePhone;
/*     */   }
/*     */ 
/*     */   @Column(name="OFFICE_PHONE", nullable=true)
/*     */   public String getOfficePhone()
/*     */   {
/*  80 */     return this.officePhone;
/*     */   }
/*     */ 
/*     */   public void setOfficePhone(String officePhone)
/*     */   {
/*  88 */     this.officePhone = officePhone;
/*     */   }
/*     */ 
/*     */   @Column(name="EMAIL", nullable=true)
/*     */   public String getEmail()
/*     */   {
/*  96 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 104 */     this.email = email;
/*     */   }
/*     */ 
/*     */   @Column(name="AGE", nullable=true)
/*     */   public Integer getAge()
/*     */   {
/* 112 */     return this.age;
/*     */   }
/*     */ 
/*     */   public void setAge(Integer age)
/*     */   {
/* 120 */     this.age = age;
/*     */   }
/*     */ 
/*     */   @Column(name="SALARY", nullable=true)
/*     */   public BigDecimal getSalary()
/*     */   {
/* 128 */     return this.salary;
/*     */   }
/*     */ 
/*     */   public void setSalary(BigDecimal salary)
/*     */   {
/* 136 */     this.salary = salary;
/*     */   }
/*     */ 
/*     */   @Column(name="BIRTHDAY", nullable=true)
/*     */   public Date getBirthday()
/*     */   {
/* 144 */     return this.birthday;
/*     */   }
/*     */ 
/*     */   public void setBirthday(Date birthday)
/*     */   {
/* 152 */     this.birthday = birthday;
/*     */   }
/*     */ 
/*     */   @Column(name="create_date", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/* 160 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 168 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="SEX", nullable=true)
/*     */   public String getSex()
/*     */   {
/* 176 */     return this.sex;
/*     */   }
/*     */ 
/*     */   public void setSex(String sex)
/*     */   {
/* 184 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */   @Column(name="DEP_ID", nullable=true)
/*     */   public String getDepId()
/*     */   {
/* 192 */     return this.depId;
/*     */   }
/*     */ 
/*     */   public void setDepId(String depId)
/*     */   {
/* 200 */     this.depId = depId;
/*     */   }
/*     */ 
/*     */   @Column(name="USER_NAME", nullable=false)
/*     */   public String getUserName()
/*     */   {
/* 208 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName)
/*     */   {
/* 216 */     this.userName = userName;
/*     */   }
/*     */   public String getContent() {
/* 219 */     return this.content;
/*     */   }
/*     */   public void setContent(String content) {
/* 222 */     this.content = content;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.JeecgDemo
 * JD-Core Version:    0.6.2
 */