package org.jeecgframework.web.demo.service.test;

import java.util.List;
import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;

public abstract interface JeecgMinidaoServiceI
{
  public abstract List<JeecgMinidaoEntity> listAll(JeecgMinidaoEntity paramJeecgMinidaoEntity, int paramInt1, int paramInt2);

  public abstract Integer getCount();

  public abstract Integer getSumSalary();
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.test.JeecgMinidaoServiceI
 * JD-Core Version:    0.6.2
 */