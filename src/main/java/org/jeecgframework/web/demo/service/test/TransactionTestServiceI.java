package org.jeecgframework.web.demo.service.test;

import java.util.Map;
import org.jeecgframework.web.demo.entity.test.JeecgDemo;
import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;
import org.jeecgframework.web.demo.entity.test.JeecgOrderCustomEntity;

public abstract interface TransactionTestServiceI
{
  public abstract Map<String, Integer> getCounts();

  public abstract Map<String, Integer> insertData(JeecgMinidaoEntity paramJeecgMinidaoEntity, JeecgDemo paramJeecgDemo, JeecgOrderCustomEntity paramJeecgOrderCustomEntity, boolean paramBoolean)
    throws Exception;
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.test.TransactionTestServiceI
 * JD-Core Version:    0.6.2
 */