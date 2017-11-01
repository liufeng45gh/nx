/*     */ package org.jeecgframework.web.demo.entity.test;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.DynamicInsert;
/*     */ import org.hibernate.annotations.DynamicUpdate;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ import org.jeecgframework.poi.excel.annotation.ExcelCollection;
/*     */ import org.jeecgframework.poi.excel.annotation.ExcelEntity;
/*     */ import org.jeecgframework.poi.excel.annotation.ExcelTarget;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jeecg_demo_course", schema="")
/*     */ @DynamicUpdate(true)
/*     */ @DynamicInsert(true)
/*     */ @ExcelTarget("courseEntity")
/*     */ public class CourseEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="课程名称", orderNum="1", needMerge=true)
/*     */   private String name;
/*     */ 
/*     */   @ExcelEntity
/*     */   private TeacherEntity teacher;
/*     */ 
/*     */   @ExcelCollection(name="选课学生", orderNum="4")
/*     */   private List<StudentEntity> students;
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
/*     */   @Column(name="NAME", nullable=true, length=25)
/*     */   public String getName()
/*     */   {
/*  76 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  84 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @ManyToOne(cascade={javax.persistence.CascadeType.REMOVE})
/*     */   public TeacherEntity getTeacher()
/*     */   {
/*  92 */     return this.teacher;
/*     */   }
/*     */ 
/*     */   public void setTeacher(TeacherEntity teacher)
/*     */   {
/*  99 */     this.teacher = teacher;
/*     */   }
/*     */ 
/*     */   @OneToMany(mappedBy="course", cascade={javax.persistence.CascadeType.REMOVE})
/*     */   public List<StudentEntity> getStudents() {
/* 104 */     return this.students;
/*     */   }
/*     */ 
/*     */   public void setStudents(List<StudentEntity> students) {
/* 108 */     this.students = students;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.CourseEntity
 * JD-Core Version:    0.6.2
 */