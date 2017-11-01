package org.jeecgframework.core.common.model.json;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface LogAnnotation
{
  public abstract String operateModelNm();

  public abstract String operateFuncNm();

  public abstract String operateDescribe();
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.json.LogAnnotation
 * JD-Core Version:    0.6.2
 */