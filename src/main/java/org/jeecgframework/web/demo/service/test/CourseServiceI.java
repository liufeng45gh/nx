package org.jeecgframework.web.demo.service.test;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.demo.entity.test.CourseEntity;

public abstract interface CourseServiceI extends CommonService
{
  public abstract void saveCourse(CourseEntity paramCourseEntity);

  public abstract void updateCourse(CourseEntity paramCourseEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.test.CourseServiceI
 * JD-Core Version:    0.6.2
 */