package org.jeecgframework.web.demo.dao.test;

import java.util.List;
import java.util.Map;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;

@MiniDao
public abstract interface JeecgMinidaoDao
{
  @Arguments({"jeecgMinidao", "page", "rows"})
  public abstract List<Map> getAllEntities(JeecgMinidaoEntity paramJeecgMinidaoEntity, int paramInt1, int paramInt2);

  @Arguments({"jeecgMinidao", "page", "rows"})
  @ResultType(JeecgMinidaoEntity.class)
  public abstract List<JeecgMinidaoEntity> getAllEntities2(JeecgMinidaoEntity paramJeecgMinidaoEntity, int paramInt1, int paramInt2);

  @Sql("SELECT count(*) FROM jeecg_minidao")
  public abstract Integer getCount();

  @Sql("SELECT SUM(salary) FROM jeecg_minidao")
  public abstract Integer getSumSalary();
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.dao.test.JeecgMinidaoDao
 * JD-Core Version:    0.6.2
 */