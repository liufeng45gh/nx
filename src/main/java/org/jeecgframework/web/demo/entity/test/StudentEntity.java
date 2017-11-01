/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.DynamicInsert;
/*     */ import org.hibernate.annotations.DynamicUpdate;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jeecg_demo_student", schema="")
/*     */ @DynamicUpdate(true)
/*     */ @DynamicInsert(true)
/*     */ public class StudentEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="学生姓名")
/*     */   private String name;
/*     */ 
/*     */   @Excel(name="学生性别", replace={"女生_1", "男生_0"})
/*     */   private String sex;
/*     */ 
/*     */   @Excel(name="出生日期", exportFormat="yyyy-MM-dd HH:mm:ss", importFormat="yyyy-MM-dd HH:mm:ss")
/*     */   private Date birthday;
/*     */   private CourseEntity course;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  61 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  69 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=true, length=32)
/*     */   public String getName()
/*     */   {
/*  77 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  85 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="SEX", nullable=true, length=1)
/*     */   public String getSex()
/*     */   {
/*  93 */     return this.sex;
/*     */   }
/*     */ 
/*     */   public void setSex(String sex)
/*     */   {
/* 101 */     this.sex = sex;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="COURSE_ID")
/*     */   public CourseEntity getCourse() {
/* 107 */     return this.course;
/*     */   }
/*     */ 
/*     */   public void setCourse(CourseEntity course) {
/* 111 */     this.course = course;
/*     */   }
/*     */ 
/*     */   @Column(name="BIRTHDAY", nullable=true)
/*     */   public Date getBirthday() {
/* 116 */     return this.birthday;
/*     */   }
/*     */ 
/*     */   public void setBirthday(Date birthday) {
/* 120 */     this.birthday = birthday;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.StudentEntity
 * JD-Core Version:    0.6.2
 */