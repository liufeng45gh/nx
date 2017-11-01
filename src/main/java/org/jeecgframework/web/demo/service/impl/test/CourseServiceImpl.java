/*    */ package org.jeecgframework.web.demo.service.impl.test;
/*    */ 
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.demo.entity.test.CourseEntity;
/*    */ import org.jeecgframework.web.demo.entity.test.StudentEntity;
/*    */ import org.jeecgframework.web.demo.service.test.CourseServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("courseService")
/*    */ @Transactional
/*    */ public class CourseServiceImpl extends CommonServiceImpl
/*    */   implements CourseServiceI
/*    */ {
/*    */   public void saveCourse(CourseEntity course)
/*    */   {
/* 17 */     save(course.getTeacher());
/* 18 */     save(course);
/* 19 */     for (StudentEntity s : course.getStudents()) {
/* 20 */       s.setCourse(course);
/*    */     }
/* 22 */     batchSave(course.getStudents());
/*    */   }
/*    */ 
/*    */   public void updateCourse(CourseEntity course)
/*    */   {
/* 28 */     updateEntitie(course);
/* 29 */     updateEntitie(course.getTeacher());
/* 30 */     for (StudentEntity s : course.getStudents()) {
/* 31 */       s.setCourse(course);
/* 32 */       saveOrUpdate(s);
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.test.CourseServiceImpl
 * JD-Core Version:    0.6.2
 */