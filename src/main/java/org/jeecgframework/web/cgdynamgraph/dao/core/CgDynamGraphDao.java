package org.jeecgframework.web.cgdynamgraph.dao.core;

import java.util.List;
import java.util.Map;
import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

@Repository("cgDynamGraphDao")
public abstract interface CgDynamGraphDao
{
  @Arguments({"configId"})
  public abstract List<Map<String, Object>> queryCgDynamGraphItems(String paramString);

  @Arguments({"id"})
  public abstract Map queryCgDynamGraphMainConfig(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgdynamgraph.dao.core.CgDynamGraphDao
 * JD-Core Version:    0.6.2
 */